01

DBC(DataBase Connectivity)



> #### 프로그램에서 SQL로 직접 데이터 처리를 안하는 이유

데이터 베이스는 제작 회사가 달라도 수행하는 기능은 대부분 동일하지만 프로그램에서 해당 데이터 베이스와 연동하기 위한 API에서 차이가 존재한다. 
(또한 내장 함수 이름과 같은 부분에서 차이가 존재한다.)

따라서 프로그램에서 데이터 베이스를 사용할 때 해당 데이터 베이스에 맞는 API 사용 코드를 작성이 요구된다.

그리고 해당 프로그램이 다른 데이터 베이스를 사용하려면 기존의 API 사용 코드를 그대로 사용하는 것이 불가능하기 때문에, 새로 사용하려는 데이터 베이스에 맞는 API 사용 코드를 다시 작성해야 한다.

   

> #### DBC란?

이렇게 데이터 베이스마다 존재하는 차이점에서 발생하는 불편함을 줄이고자 만들어 진것이 바로 DBC(DataBase Connectivity)다.

DBC는 프로그래밍 개발 언어(Java, Python 등등)로 데이터 베이스 작업을 수행할 수 있게 만든 것이다. 

이를 통해 프로그램은 데이터 베이스의 종류와 상관없이 자신에게 맞는 DBC만 작성하면 데이터 처리를 수행할 수 있게 된다.  

DBC에는 **여러 데이터 베이스의 연결 및 사용을 위한 실제 코드**가 들어있는데, 사용자는 DBC에 맞는 실행 코드만 작성하면 DBC안에서 해당하는 데이터 베이스의 실제 사용 코드로 변경해 작업을 수행한다.

**※ DBC안에 들어 있는 데이터 베이스 연결 및 사용을 위한 실제 코드를 DBC Driver라고 부른다.**

* 이 DBC Driver는 각 데이터 베이스 사이트에서 다운로드 받아야 한다.

  



> #### JDBC

* 자바를 사용해서 실행시키는 DBC

* 실행 방법

  1) (사용할) Driver 로드하기

  2) (DB와) 연결 생성하기

  3) (쿼리) 문장 실행하기

  4) 결과 집합 사용하기

  



---

02

JDBC 다운로드 및 사용



1) Oracle DB의 버전에 맞는 Oracle JDBC Driver 다운로드



2) 자바 프로젝트 오른쪽 버튼 > Build Path > Configure Build Path > Libraries 탭 > ModulePath 클릭 후 > Add External JARs > 다운로드 받은 Driver 파일(`.jar` 파일) 지정





> #### JDBC 사용 순서

※ 주의 ※

JDBC는 순서대로 각각 기능을 수행하는 객체를 생성하여 작동된다. 

java에서 객체 생성은 보통 `new`를 사용하지만 JDBC에서 사용되는 그 어떤 객체도 `new`를 사용해서 만들지는 않는다.  





1) JDBC 드라이버를 객체화 

```JAVA
Class.forName("oracle.jdbc.driver.OracleDriver");
```

* JDBC 드라이버는 특이하게도 new가 아닌 Class.forName 메소드를 통해서 객체 생성
* 이렇게 JDBC 드라이버를 로드하게 되면 메모리 상에서 드라이버가 생성된다.   



2) DriverManager라는 객체를 통해서 DB와 코드를 연결시키는 연결 객체를 생성 (=연결을 수행)

* DriverManager는 메모리 상에 존재하는 JDBC Driver에 속한 객체 

```java
Connection con = DriverManager.getConnection("서버url","사용자아이디","비밀번호");
```

* `DriverManager.getConnection()`을 통해서 DB와 코드가 연결되고 그 연결 결과를 반환한다.

* 서버 url 작성 예시

  ```java
  String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1"
  ```

  * `jdbc:oracle:thin`은 JDBC 드라이버 사용 명시
  * `@`이후에는 `DB 서버 주소 : 포트번호`
  * `/` 이후에는 접속하려는 `DB명`  



3) SQL 쿼리문을 실행할 도구를 생성

```JAVA
Statement st = con.createStatement();
```

  

4) 쿼리 실행 결과를 읽을 커서를 만든다.  (커서 객체(`ResultSet`)를 생성한다.)

※ 쿼리 실행 **결과 테이블은 서버 측에 만들어지고** 우리는 단지 객체를 통해 한 줄씩 읽어온다.

* 한 줄 씩 읽기 위한 커서 객체가 바로 `ResultSet` 이다.

```java
ResultSet rs = st.executeQuery(sql);
```

* sql 예시

  ```java
  String sql = "SELECT * FROM NOTICE";
  ```

  

  



5) 생성한 모든 객체는 사용 이후 삭제하는 습관을 기르는 것이 좋다.

* 사용 이후 삭제를 해야지 더 이상 자원을 먹지 않게 된다. 

```java
// 생성 후 작업 완료

rs.close();
st.close();
con.close();
```

* 삭제 작업은 생성 작업과 반대 순서로 수행한다. (안쪽에서부터 삭제)



> #### 쿼리 결과 데이터 읽기



**[ 결과 데이터는 한 줄 씩 읽을 수 있다. ]**

JDBC를 통해 쿼리문을 실행시킨 결과 데이터는 한 줄 씩(row by row) 읽어 올 수 있다.

최초의 결과 데이터는 첫 번째 줄 이전(Before of File: BOF) 데이터를 가리키고 있다.

`.next()`를 통해서 다음 줄 데이터를 읽을 수 있다.

( 마지막 줄 데이터 이후 데이터 = End of File: EOF )

  

**[ 코드 ]**

```JAVA
// rs는 결과값을 받은 ResultSet 객체

rs.next();
String title = rs.getString('title');
```

* `rs.getString('title')`: 현재 가리키는 row에서 'title'이라는 column에 해당하는 데이터를 문자열로 가져온다.
  * ※ 주의: 해당 column의 데이터 형식이 문자열(String)이 아닌 경우 에러가 발생한다. 

  

  

---

04

쿼리 실행하기 실습



코드

```JAVA
package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String pwd = "비밀번호";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		
		String sql = "SELECT * FROM NOTICE";
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"newlec",pwd);
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {
			String title = rs.getString("TITLE");
			System.out.println(title);
		}
			
		
		rs.close();
		st.close();
		con.close();

	}

}
```



* 사용자명에 sys 넣는 경우 다음과 같은 에러 발생
  ▶ `ORA-28009: SYS(SYSDBA 또는 SYSOPER)로 접속해야 합니다.`

  ▶ 따라서 sys as sysdba와 같이 role까지 입력해줘야 한다.

  

SQL 코드

* 위에서 조회하려는 NOTICE 테이블에 데이터가 하나도 없는 경우, 조회가 불가능하기 때문에 데이터를 직접 넣어 준 다음 코드를 다시 실행한다.

```SQL
INSERT INTO NOTICE VALUES(1, 'JDBC란 무엇인가?', 'newlec', 'aaa', SYSDATE, 0, '');
COMMIT;
```



  

---

05

혼자 풀어보는 문제



