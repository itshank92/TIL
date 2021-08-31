21

( 페이지 상단에 전체 게시글 수 보여주기 위한) 게시글 갯수 구하기.



[ 과정 ]

(1) 데이터를 처리하는 NoticeService에서 전체 게시글의 수를 구하고

(2) 출력을 관장하는 NoticeConsole에서 (위에서 구한) 전체 게시글의 수를 출력한다.



[ TOP-DOWN 방식으로 개발 ]

ⅰ. `NoticeConsole.java`에서 전체 게시글 수를 나타내는 변수를 생성

```java
public class NoticeConsole {
	
	// 생략   
	
	// 게시글 수 변수
	private int count;
	
    // 생성자에 count 추가(초기값 0으로 설정)
	public NoticeConsole() {
		service = new NoticeService();
		page = 1;
		count = 0; 
	}
	
    // 콘솔에 출력하는 함수에 count 가지고 오는 과정과 count 출력 추가
	public void printNoticeList() throws ClassNotFoundException, SQLException {	
		List<Notice> list = service.getList(page);

        // count 가지고 오기
		int count = service.getCount();
		
		// count 출력 추가
		System.out.println("──────────────────────────────────────────────────");
		System.out.printf("<공지사항> 총%d 게시글\n", count);
		System.out.println("──────────────────────────────────────────────────");
```

* 아직 `NoticeService`에는 `count`를 가지고 오는 `getCount()` 메소드가 없기에 이를 작성해준다.

  

ⅱ. NoticeService.java에서 `getCount()` 메소드를 작성해준다. 

```java
// 단일값(Scalar 값)을 얻는 함수
public int getCount() throws ClassNotFoundException, SQLException {

    // 초기값 0인 count 생성
    int count = 0;
    
    // 전체 게시글 갯수 얻는 쿼리문 작성
    String sql = "SELECT COUNT(ID) FROM NOTICE"; 

    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = DriverManager.getConnection(url,uid,pwd);
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);

	// 결과 데이터가 있는 경우 count 변수에 담음(없으면 count는 초기값 0으로 남아있음)
    if(rs.next()) {
        count = rs.getInt("COUNT");
    }


    rs.close();
    st.close();
    con.close();
	
    // count를 반환
    return count;
}
```



[ SQL 문 설명]

* NOTICE 테이블에서 전체 게시글의 갯수를 구하는 SQL문

```SQL
SELECT COUNT(ID) FROM NOTICE;
```

* 위 쿼리문을 실행하면 COUNT(ID)라는 이름을 가진 칼럼이 생성된다.

  * 즉, 별칭을 주지 않으면 쿼리문에 작성된 그대로를 칼럼 이름으로 만들어버린다.

  * 여기서는 별칭을 줘서 작성하자.

    ```SQL
    SELECT COUNT(ID) COUNT FROM NOTICE;
    ```

    

---

22

마지막 페이지 구하기

* 화면 하단부에 `현재페이지 / 전체페이지`  표기를 위한 전체 페이지를 구하는 것이다.

* 현재 페이지의 경우 이전에 페이지 데이터를 가져오는 입력값으로 page 변수를 만들어 사용했기에 알 수 있다.

  

  

> #### 코드

  

**[ `NoticeConsole.java`에서 전체 페이지 출력될 수 있도록 작성 ]**

`NoticeConsole.java`에서는 전체 게시글 수를 `getCount()`라는 메소드로 구하는데, 이를 활용하여 전체 페이지를 구할 수 있다.

```java
// NoticeConsole.java

public void printNoticeList() throws ClassNotFoundException, SQLException {

    List<Notice> list = service.getList(page);
    int count = service.getCount();
    
    // count(전체게시글 수)를 사용하여 lastPage를 구한다 >> 단, 나머지가 있는 경우 +1을 해준다.
    int lastPage = count/10;
    lastPage = count%10>0?lastPage+1:lastPage;

    System.out.println("──────────────────────────────────────────────────");
    System.out.printf("<공지사항> 총%d 게시글\n", count);
    System.out.println("──────────────────────────────────────────────────");

    for(Notice n: list) {
        int id = n.getId();
        String title = n.getTitle();
        String writeid = n.getWriterid();
        String regdate = String.valueOf(n.getRegDate());

        System.out.printf("%d. %s / %s / %s \n", id, title,writeid,regdate);
    }
    System.out.println("──────────────────────────────────────────────────");
    
    // "현재 페이지 번호 / 전체 페이지 수" ▶▶▶ page/lastPage로 작성
    System.out.printf("         %d/%d page\n", page,lastPage);
}
```

  

  

 **[ `NoticeConsole.java`에서 다음 버튼 예외 처리 코드 작성 ]**

* 전체 페이지수가 넘어가는 상황에서 다음 버튼 입력 케이스에 대한 예외 처리 코드 작성

```java
// NoticeConsole.java

public void moveNextList() throws ClassNotFoundException, SQLException {
    int count = service.getCount();
    int lastPage = count/10;
    lastPage = count%10>1?lastPage+1:lastPage;
    if(page==lastPage){
        System.out.println("==================================");
        System.out.println("다음 페이지가 없습니다.");
        System.out.println("==================================");

    }
    else {
        page++;
    }
}
```

* 주의 사항 `lastPage` 변수를 맴버변수로 만들지 않고 로컬 변수로 만들어서 함수마다 새로 생성하는 이유는 매번 `lastPage` 값이 달라질 수 있기 때문이다. 
  * 즉, 해당 시점에 유효한 `lastPage`를 구해야 한다는 것이다. 
  * 따라서 `printNoticeList()`에 도 `lastPage` 변수가 사용되고, `moveNextList()`에서도 사용되는데, 이 둘을 맴버 변수로 빼서 공통으로 사용하지 않는 이유가 바로 이것이다.



