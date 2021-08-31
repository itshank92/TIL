06

전체 데이터 읽어서 콘솔에 출력하는 코드

```JAVA


// 변수 코드 생략
String sql = "SELECT * FROM NOTICE";

// 연결 코드 생략

while(rs.next()) {
    int id = rs.getInt("ID");
    String title = rs.getString("TITLE");
    String writerid = rs.getString("WRITER_ID");
    Date regDate = rs.getDate("REGDATE");
    String content = rs.getString("CONTENT");
    int hit = rs.getInt("HIT");

    System.out.printf("id:%d, title:%s, writerid:%s, regDate:%s, content:%s, hit:%d\n",
                      id, title, writerid, regDate,content, hit);
}
		
```

  

* 전체 데이터 읽어오는 것이기 때문에 sql문은 `SELECT *` 으로 작성됨  



* `while`문을 사용해서 `rs` 포인터가 유효한 데이터를 가리키는 동안에는 모든 데이터를 읽어 온다.

   

  

---

07

조회수가 10 이상인 글만 읽어오기



※ 주의해서 생각해 볼 것 ※ 

* 자바에서 if문과 같은 조건식을 사용해서 HIT 데이터를 판별하는 경우 문제점

  ▶ 테이블의 모든 데이터를 읽어와서 작업을 수행해야 한다.

* 따라서 애초에 SQL문에서 우리가 원하는 데이터만 걸러서 가지고 오는 것이 더 자원 효율적이다.

  

코드

```JAVA
// 생략

String sql = "SELECT * FROM NOTICE WHERE HIT>10"; 


//생략

while(rs.next()) {
    int id = rs.getInt("ID");
    String title = rs.getString("TITLE");
    String writerid = rs.getString("WRITER_ID");
    Date regDate = rs.getDate("REGDATE");
    String content = rs.getString("CONTENT");
    int hit = rs.getInt("HIT");

    System.out.printf("id:%d, title:%s, writerid:%s, regDate:%s, content:%s, hit:%d\n",
                      id, title, writerid, regDate,content, hit);
}
```

   

---

09

데이터 입력하기



> #### Statement와 PrepareStatement



실행 결과가 존재하는 `SELECT` 쿼리문에 대해서는 JDBC에서는 `Statement`의 메소드인 `executeQuery`를 사용한다.

*  `executeQuery`는 결과 데이터를 읽을 수 있는 포인터 기능을 하는`ResultSet` 객체를 반환한다.  



하지만 특별히 결과가 존재하지 않는 다른 쿼리문인 `INSERT`, `UPDATE`, `DELETE`의 경우, `Statement`의 또 다른 메소드인 `executeUpdate`를 사용한다. 

* `executeUpdate`는 실행된 쿼리가 몇 개의 행에 영향을 미쳤는지를 나타내는 정수(`int`)를 반환한다. 

  

근데 보통 `Statement` 객체가 아니라 `PrepareStatement` 객체를 쓰는 것이 일반적이다. 

* `PrepareStatement` 객체는`Connection`의 메소드로 제공되는 `preparedStatement`를 써서 생성한다.

* 자바에서는 코드 내에 변수를 만들어서 해당 변수를 SQL 문에 삽입하여 데이터를 입력하는 것이 일반적이다.

* 근데 이렇게 변수를 SQL 문에 삽입하는 것은 코드 작성시 좀 귀찮은 작업이 된다. 
  ▶  `" "+ variable_name + " "` 

* 변수를 SQL문에 입력하는 작업에 있어 그냥 `Statement`는 편리한 도구를 제공하지 않는다.



하지만 `PreparedStatement`는 보다 편리한 방법으로 자바 코드 내의 변수를 SQL문에 입력할 수 있다. 

따라서 `Statement`보다 `PreparedStatement`가 많이 사용된다. 

  

**[ PreStatement 사용 ]**

```java
// 생략
Connection con = DriverManageer.getConnection(url, "id", "pwd");
PreparedStatment st = con.prepareStatement(sql)
```

* `Connection` 객체의 `prepareStatement()` 메소드의 인자로 sql문을 넣는다.
  * 이는 SQL문을 실행할 때 넣는 것이 아니라 미리 넣어놓는 것이다.
  * 이렇게 넣어서 만든 것은 `PrepareStatement` 객체다.  

  

```JAVA
// PrepareStatement 만들기 전에 작성되는 부분 (순서상 위의 코드 블럭보다 먼저 오는 것임)
String sql = "INSERT INTO NOTICE(title, writer_id, content, files) VALUES(?,?,?,?)";
```

* `PrepareStatement` 객체 생성시 입력값으로 들어가는 SQL문은 입력값을 `?` 로 작성할 수 있다. 
  * 즉 자바 코드 내의 변수를 입력값으로 직접 넣는 것이 아니라, SQL문에서는 그냥 `?` 로 넣고 이후에 `PrepareStatement`의 편리한 메소드를 통해서 해당 변수를 입력값으로 넣는 것이다.

  

```java
st.setString(1, title);
st.setString(2, writeid);
st.setString(3, content);
st.setString(4, files);
```

* `PrepareStatement` 객체를 만들었으면 해당 객체에 입력된 SQL문에 넣을 값들을 세팅할 수 있다.
  * 입력할 자료형에 따라서 `set` + `자료형` 형태의 다양한 메소드가 제공된다. 
  * 예를 들면 문자열 데이터를 SQL문에 입력하려면 `.setString(입력순서 ,문자열변수)` 형태로 입력하면 된다.
  * '입력순서' 란
    * 해당 입력값이 SQL문에서 몇 번 째로 입력되는지를 나타내는 것으로 인덱스 번호라고 할 수 있다.
    * ※ 주의 ▶ **SQL 입력값의 인덱스는 0이 아닌 1부터 시작한다.**

​    

```JAVA
int row_count = st.executeUpdate();
```

* 이제 SQL과 입력값을 모두 넣었으니 실행을 시킨다.
  * 실행은 어떤 결과 데이터를 받아오는 것이 아니기 때문에 `executeQuery`가 아닌 `executeUpdate`를 사용한다.
    
* 주의해야 할 것은 `PrepareStatement`는 이미 SQL문을 입력해놓았기 때문에 실행 메소드에서 SQL문을 입력값으로 주면 안된다. 
  * 또 SQL문을 입력하는 경우 기존에 입력된 입력값들이 없어지기에 오류가 발생한다.
    
* `executeUpdate()`는 결과값으로 수행한 SQL문의 영향을 받은 레코드(row)의 수를 반환한다.



> #### 코드

```java
package ex1;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Program2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        
		String sql = "INSERT INTO NOTICE(title, writer_id, content, files) "
				+ "VALUES(?,?,?,?)"; 
		
        
        // 입력 데이터 변수
		String title = "TEST2";
		String writerid = "newlec";
		String content = "hahaha";
		String files = "";
        
        
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"아이디","비밀번호");
        
        // PrepareStatement 객체 생성
		PreparedStatement st = con.prepareStatement(sql);
        
        // PrepareStatement 객체에 입력값 입력
		st.setString(1, title);
		st.setString(2, writerid);
		st.setString(3, content);
		st.setString(4, files);
		
        
        // PrepareStatement의 SQL문 실행 후 결과로 영향 받은 레코드(row) 숫자 받음
		int row_cnt = st.executeUpdate();
		
		System.out.println(row_cnt);
        // 1이 출력됨
		
		st.close();
		con.close();
	}
}
```

