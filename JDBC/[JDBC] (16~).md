16

(15에서 만들던 사용자 인터페이스(콘솔) 이어서)

메뉴 목록 구현 1단계  



> #### 콘솔 파일(NoticeConsole.java)에서 메뉴 받는 함수 작성

```java
// NoticeConsole.java

public class NoticeConsole {
    
    // 데이터 처리(CRUD)를 위함
	private NoticeService service;
	
	public NoticeConsole() {
		service = new NoticeService();
	}
    
    // 출력함수 생략(15강에서 수행)
    
    public int inputNoticeMenu() {
        Scanner scan = new Scanner(System.in); 

        System.out.println("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.종료 >");

        // 입력값이 양의 정수가 아닐 수 도 있기 때문에 일단 임시 변수(_사용해서 표현)로 받음
        String menu_ = scan.nextLine();
        
        // 여기서 menu_에 대한 검증작업(양의 정수인지)이 이뤄져야 한다. (코드 생략)
        
		// 검증 완료시 menu_를 int menu 변수에 담아서 반환한다.
        int menu = Integer.parseInt(menu_);

        return menu;
	}
}


```

  



> #### 콘솔 실행 함수(Program5.java)에서 콘솔 입력 처리 작성

* 사용자가 '5'를 입력하면 종료를 하도록 작성하였다.

```java
package ex1;

import java.sql.SQLException;
import com.newlecture.app.console.NoticeConsole;


public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		NoticeConsole console = new NoticeConsole();
		
		// 사용자가 종료 메뉴를 누르기 전까지는 계속 반복해서 내용을 보여줌
		// 반복문 앞에 라벨을 라벨: 형태로 붙일 수 있다.
		EXIT:while(true) {

            // 콘솔UI 보여주는 함수 
			console.printNoticeList();
			
			// 사용자의 입력값을 입력받아 해당하는 업무를 수행(숫자로 입력받음)
			int menu = console.inputNoticeMenu();
			
			switch(menu) {
			case 1: // 상세조회
				
				break;
				
			case 2:  // 이전
				break;
				
			case 3:  // 다음
				break;
				
			case 4:  //글쓰기
				break;
				
			case 5:  //종료
				System.out.println("Bye~~~~~~~");
				// break 다음에 라벨명이 오면 해당 라벨 단계까지 모두 종료한다.
				break EXIT;
				
			default:
				System.out.println("메뉴는 1에서 4까지만 입력할 수 있습니다.");
				break;
			}
		}
	}
}
```

  

  

---

17

이전, 다음 페이지 버튼을 통해 10개씩 글을 볼 수 있도록 **페이징 쿼리** 만들기



`ROWNUM`을 사용해서 10개씩 가져올 것이다.  



> #### ROWNUM 복습

* 2번부터 10번까지 읽어오는 쿼리
  * 작성일이 최신인 것을 1번부터 붙여서 가져온다.
  * ORDER BY는 SELECT보다 우선순위가 뒤에 있기에 소괄호로 묶어서 우선 수행시킨다.
  * 서브 쿼리 결과를 상위 쿼리에서 사용하려면 해당 결과의 **별칭**을 써줘야 한다.
    * 즉, `SELECT * FROM NOTICE`의 결과는 더이상 `NOTICE`로 부를 수 없다.

```SQL
SELECT * FROM (
    SELECT ROWNUM NUM, N.* FROM (
        SELECT * FROM NOTICE ORDER BY REGDATE DESC
    ) N
) 
WHERE NUM BETWEEN 2 AND 10;
```

  

---

18 

페이징 쿼리를 자바에서 사용하기



> #### 코드 ▶ NoticeService에서 getList 함수 변경하기



```java
public List<Notice> getList(int page) throws ClassNotFoundException, SQLException {
		
		// 페이지 시작 ROWNUM, 끝 ROWNUM을 각각 start와 end로 받는다.
		int start = (page*10)-9 ;
		int end = page*10;
		
    	// sql문에서 페이지 시작과 끝 인자를 ?로 한다.
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC) N) WHERE NUM BETWEEN ? AND ?"; 
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,uid,pwd);
    
    	// 기존 Statement에서 PrepareStatement로 바꾼다.
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, start);
		st.setInt(2, end);
		ResultSet rs = st.executeQuery(); // 실행시에는 sql문 따로 입력x(미리 입력했기에)
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerid = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			String files = rs.getString("FILES");
			int hit = rs.getInt("HIT");
			
			Notice notice = new Notice(id,title,writerid,regDate,content,hit,files);
			
			list.add(notice);

		}
		
		
		rs.close();
		st.close();
		con.close();
		return list;
		
	}
```

* 게시물을 10개씩 끊어서 페이지를 구성할 것이기 때문에 입력값으로 `int page`를 받는다.
* SQL 문에서 페이지를 가져오는 구문의 입력값을 ?로 바꾼다.

   

  



> #### getList() 함수를 실행시키는 콘솔의 출력 함수도 수정해준다.

* `getList()` 함수가 이제 조회할 페이지를 입력값으로 받도록 코드가 변경되었으니 이를 사용하는 코드도 수정을 해준다. 

```java
// NoticeConsole.java 

// 생략

public void printNoticeList() throws ClassNotFoundException, SQLException {
		
	List<Notice> list = service.getList(2);
    
    // 생략
```



