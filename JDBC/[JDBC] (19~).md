19

목록을 위한 VIEW 생성하기



이전시간에는 우리가 게시글을 10개 단위로 끊어서 페이지를 만들어 보여주기 위해 다음과 같은 쿼리문을 작성했다. 

```sql
SELECT * FROM (
    SELECT ROWNUM NUM, N.* FROM (
        SELECT * FROM NOTICE ORDER BY REGDATE DESC
    ) N
) 
WHERE NUM BETWEEN 1 AND 10;
```

* 위와 같이 약간 복잡한 구조의 쿼리를 통해서 우리가 원하는 데이터를 테이블 형태로 구성해 가지고 온 것이다.

  

위와 같이 복잡한 쿼리문이 아닌 우리가 원하는 테이블을 VIEW 형태로 만들어 놓는다면, 쉽게 불러올 수 있다. 

```SQL
CREATE VIEW NOTICE_VIEW
AS
SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC) N); 
```

* VIEW 생성시 계속 바뀌는 조건절(WHERE)는 제외하고 만들어준다.

  

이렇게 만든 VIEW를 사용하면 간단하게 원하는 페이지를 가져오는 쿼리문 작성이 가능하다.

```SQL
SELECT * FROM NOTICE_VIEW WHERE NUM BETWEEN 1 AND 10;
```



> #### 코드에 적용

이렇게 VIEW를 만들면 자바 코드 내에서 SQL문을 매우 간결하게 작성할 수 있게된다.

```JAVA
// NoticeService.java

// 생략

String sql = "SELECT * FROM NOTICE_VIEW WHERE NUM BETWEEN ? AND ?;"

// 생략
```

  

---

20

이전 & 다음 화면 구현하기



`Program5.java`에서 콘솔 객체(`Noticeconsole`)가 현재 보여주려는 page 번호에 대한 데이터를 가지고 있도록 만들자.

**[ 기존 코드 ]**

```java
//Program5.java

// 생략

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		NoticeConsole console = new NoticeConsole();

		EXIT:while(true) {
			console.printNoticeList();
			// 생략
```

  

**[ 변경 코드 ]**

1) `NoticeConsole.java`

* `NoticeConsole`이 page를 인자로해서 생성될 수 있도록 수정한다.
  * 변수 page 작성
  * 생성자에 page를 만들도록 작성

```java
//NoticeConsole.java

// 생략

public class NoticeConsole {
    private int page;
    
    pubilc NoticeConsole() {
        service = new NoticeService();
        page = 1; //페이지의 기본값(default)은 1로 설정한다.
    }
    
    // 생략
}
```



2) `Program5.java`

* 이전, 다음 메뉴 버튼의 작동 과정을 작성한다.
* 이 때, 이전, 다음 메뉴 버튼의 작동은 console (`NoticeConsole` 객체)의 메소드로 수행한다.
* 아직 console의 메소드를 작성하지 않았기에 여기서는 메소드 이름을 만들어서 작성하고 이후 `NoticeConsole.java`에서 해당 메소드를 작성한다. ▶ **TOP-DOWN 방식**
  * 이전 페이지를 불러오는 `movePrevList();`
  * 다음 페이지를 불러오는 `moveNextList();`



```java
//Program5.java

// 생략

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		NoticeConsole console = new NoticeConsole();

		EXIT:while(true) {
			console.printNoticeList();
	
			int menu = console.inputNoticeMenu();
			
			switch(menu) {
			case 1:  // 상세조회			
				break;
				
			case 2:  // 이전
				console.movePrevList();
				break;
				
			case 3:  // 다음
				console.moveNextList();
				break;

		// 생략
```

  

3) 다시 `NoticeConsole.java`

* 새로 작성하는 함수 두 개 작성 ▶  `movePrevList();` ,  `moveNextList();`

```java
public void movePrevList() {
    if(page==1) {
        System.out.println("이전 페이지가 없습니다.");
    }
    page--;

}

public void moveNextList() {
    // 마지막 페이지를 구하는 로직(생성 예정)
    page++;

}
```

  

* 페이지를 읽어와서 콘솔에 출력해주는 메소드( `printNoticeList()` ) 수정
  * `getList()` 인자에 `page`추가
  * 만약 `getList()` 메소드가 인자로 정수를 받지 않고 있다면 해당 부분도 수정해준다. (`NoticeService.java`)

```java
public void printNoticeList() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = service.getList(page);
    	
    	// 생략
```

  

