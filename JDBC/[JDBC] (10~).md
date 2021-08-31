10

데이터 수정을 위한 쿼리 준비



```sql
UPDATE NOTICE 
SET
	TITLE = 'TEST3',
	CONTENT = 'HUHHUHHUH',
	FILES = ''
WHERE ID=27;

ROLLBACK;
```

* 위 UPDATE 쿼리문을 JDBC에서 실행시키는 것을 다음 시간에 배운다.

  

---

11

JDBC에서 데이터 수정(UPDATE)을 구현



> #### 기본 내용

* `UPDATE` 역시 `INSERT`와 마찬가지로 `PrepareStatement`를 사용한다.





> #### 코드

```java
package ex1;

// import 생략

public class Program3 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        
        // UPDATE 쿼리문 역시 입력값을 ?로 작성한다.
		String sql = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, FILES = ? WHERE ID=?"; 
		
		String title = "TEST3";
		String content = "hahaha3";
		String files = "";
		int id = 27;
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"아이디","비밀번호");
		PreparedStatement st = con.prepareStatement(sql);
        
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int row_cnt = st.executeUpdate();
		
		System.out.println(row_cnt);
		
		st.close();
		con.close();
	}
}
```

   

---

12

데이터 삭제(DELETE)하기



> #### 코드

* 위 코드와 다 동일하고 단지 SQL문이 다르다.

```JAVA
// 생략 

String sql = "DELETE NOTICE WHERE ID = ?";

Int id = 27;

int row_cnt = st.executeUpdate();
```

  

---

13

CRUD를 담당하는 서비스 클래스 생성하기



CRUD 작업을 편하게 수행하기위해 재사용이 가능한 함수들로 만들어 놓는다.

▶ 이 과정에서는 NoticeService라는 class에 CRUD 함수들을 만든다.





13강에서는 SELECT를 수행하는 함수를 만든다.



SELECT는 데이터를 가져오는 것이기에 해당 테이블 데이터 양식을 저장할 클래스가 필요하다.



우리가 실습에서 가져올 테이블은NOTICE 테이블로, SELECT 함수를 통해 해당 데이터를 ROW별로 LIST에 담아서 가져올 것이다.



이를 수행하기 위해서 일단 NOTICE라는 클래스를 만들어서 데이터를 저장할 수 있도록 하자.



> #### 코드1 _ NOTICE 객체 생성

* NOTICE 테이블에 있는 칼럼들을 변수로서 만들어준다.

* 생성자, Getter, Setter는 코드로 일일히 작성하는 것이 아니라 코드 창에서 마우스 오른쪽 > Source 에서 자동 작성 기능을 사용한다.

```JAVA
package com.newlecture.app.entity;

import java.sql.Date;

public class Notice {
	private int id;
	private String title;
	private String writerid;
	private Date regDate;
	private String content;
	private int hit;
	
	// 생성자
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	// 필드 정보를 담고 있는 생성자
	public Notice(int id, String title, String writerid, Date regDate, String content, int hit) {
		super();
		this.id = id;
		this.title = title;
		this.writerid = writerid;
		this.regDate = regDate;
		this.content = content;
		this.hit = hit;
	}
	
	
	// 모든 요소에 대한 Getter Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriterid() {
		return writerid;
	}
	public void setWriterid(String writerid) {
		this.writerid = writerid;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
```

* NOTICE.class를 작성했으면 원래 만들고자한 SELECT 함수를 작성하자.



> #### 코드2 _ SELECT 기능을 수행하는 함수(getList)

```java
package com.newlecture.app.service;

// import 생략

public class NoticeService {
	public List<Notice> getList() throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE HIT>10"; 
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"사용자계정","비밀번호");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
        
        // NOTICE 객체를 담을 빈 리스트를 만들어 준다.
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerid = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			
            // NOTICE 객체 생성
			Notice notice = new Notice(id,title,writerid,regDate,content,hit);
			
            // list에 NOTICE 객체 추가
			list.add(notice);

		}
		
		rs.close();
		st.close();
		con.close();
        
        // list를 반환
		return list;
		
	}
}
```





