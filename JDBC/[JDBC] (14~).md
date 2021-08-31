14

CRUD를 자바 함수로 만들기 완성(INSERT, UPDATE, DELETE)

  

> 코드

* 아래 코드는 이전 시간(13)에 작성했던 SELECT 함수( `getList()` )도 포함되어 있다.

```java
package com.newlecture.app.service;

// import 생략


public class NoticeService {
    
    // SELECT 함수
	public List<Notice> getList() throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE HIT>10"; 
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"사용자 아이디","비밀번호");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
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

    // INSERT 함수
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "INSERT INTO NOTICE(title, writer_id, content, files) "
				+ "VALUES(?,?,?,?)"; 
		
		String title = notice.getTitle();
		String writerid = notice.getWriterid(); 
		String content = notice.getContent();
		String files = notice.getFiles();
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"사용자 아이디","비밀번호");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerid);
		st.setString(3, content);
		st.setString(4, files);
		
		int row_cnt = st.executeUpdate();
		
		st.close();
		con.close();
		
		return row_cnt;
	}
	
    // UPDATE 함수
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, FILES = ? WHERE ID=?"; 
		
		String title = notice.getTitle();
		String content = notice.getContent() ;
		String files = notice.getFiles();
		int id = notice.getId();
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"사용자 아이디","비밀번호");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int row_cnt = st.executeUpdate();
		
		st.close();
		con.close();
		return row_cnt;
	}
	
    // DELETE 함수
	public int delete(Integer id) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "DELETE * FROM NOTICE WHERE ID=?"; 
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"사용자 아이디","비밀번호");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		int row_cnt = st.executeUpdate();
		
		
		st.close();
		con.close();
		
		return row_cnt;
	}
}

```

   

위 코드를 보면 알겠지만 모든 함수에 공통되는 부분이 존재한다. 

그 중 공통 부분으로 빼줄 수 있는 것은 `url` 부분이다.

DB와의 연동을 위한 **<u>드라이버 생성</u>** , **<u>연결</u>** , **<u>Statement 생성</u>** , **<u>ResultSet 생성</u>** 부분은 매번 쿼리문을 수행할 때마다 따로 존재해야 하기에 공통 부분에 빼 줄 수 없다.

* 하지만 연결을 위한 인자들인 "사용자 id" , "pwd" 는 공통적으로 빼 줄 수 있다.

[ 코드 ]

```java
public class NoticeService {
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String uid = "사용자 아이디"
	Private String pwd = "비밀번호"
```

  

