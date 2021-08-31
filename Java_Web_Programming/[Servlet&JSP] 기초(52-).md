52

JSP 파일에 JDBC를 작성하여 DB의 데이터를 가지고 와서 사용하기



1) list.jsp 파일 상단에 코드 블록을 열고 JDBC 코드를 작성한다.

* JDBC 과정에서 만들었던 Program1.java 안에 작성된 코드를 가져와서 복사 붙여 넣기를 하자.

  ```java
  // list.jsp
  
  <%
  String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
  String sql = "SELECT * FROM NOTICE WHERE HIT>10"; 
  
  Class.forName("oracle.jdbc.driver.OracleDriver");
  Connection con = DriverManager.getConnection(url,"아이디","비밀번호");
  Statement st = con.createStatement();
  ResultSet rs = st.executeQuery(sql);
  %>
  
  // 생략
  
  <%		
  rs.close();
  st.close();
  con.close();
  %>
  ```

* jsp에서 import는 페이지 지시자 블록`<%@  %>`을 통해서 이뤄진다.

  * **★★ 간단하게 `ctrl` + `space`를 통해서 입력한다. ★★**

    ```java
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.sql.Statement"%>
    <%@page import="java.sql.DriverManager"%>
    <%@page import="java.sql.Connection"%>
    ```



2) list.jsp에서 테이블 위에 JDBC 코드를 작성해서 데이터를 가져오자.

```JAVA
// 생략

<% while(rs.next()){ %>
    <tr>
    <td><%= rs.getInt("ID") %></td>
    <td class="title indent text-align-left"><a href="detail.html"><%= rs.getString("TITLE") %></a></td>
        <td><%= rs.getString("WRITER_ID") %></td>
        <td>
        <%= rs.getString("REGDATE") %>		
        </td>
        <td><%= rs.getInt("HIT") %></td>
        </tr>
        <%} %>
    </tbody>
    </table>
    
// 생략
```





3) 현재 프로젝트에서 JDBC 드라이버를 사용할 수 있도록 등록시켜줘야한다.

**※ 주의 ※**

이 때 이전에 로컬에서만 하던 일반 프로젝트(JDBCPrj)와 달리 웹 프로젝트에서는 External Library에 등록하면 안된다. 

★ 이유: 웹 프로젝트는 프로젝트 폴더에서 컴파일하고 실행되는 것이 아닌, 프로젝트 폴더에서 컴파일한 것이 톰캣으로 배포가 되어 실행된다.

▶ 따라서 톰캣에 배포가 되어 실행될 때, 외부 라이브러리의 Build Path가 달라지게 된다. 

▶ 또한 톰캣이란 것이 로컬에 있을 수 도 있지만, 다른 서버에 있는 경우 역시 Build Path가 달라지게 된다.

▶ 따라서 웹 개발시, 웹 프로젝트에서 사용하는 라이브러리는 같이 가져가야 하기에, 단순히 라이브러리 파일이 어디있는지 경로만 입력해주는 것으로는 불가능하다.

▶ 라이브러리를 같이 배포할 수 있도록 라이브러리 파일을 프로젝트 안에 포함시켜야 한다. 

* 웹 개발 디렉토리를 보면 **WEB-INF 폴더 안에 lib 디렉토리가 존재**한다.

* 해당 디렉토리 안에 웹 프로젝트가 사용하는 라이브러리 파일을 담아주면 된다. 

* ★ 이 때, 파일탐색기로 복사 붙여넣기를 하지 말고 해당 파일을 드래그해서 Eclipse안에 lib 폴더에 넣는 방식으로 넣어준다.

  * 이렇게 넣어야 파일이 이동함과 동시에 해당 웹 프로젝트의 외부 라이브러리로 등록이 된다. 
  * 만약, 이렇게 하지 않고 그냥 파일 탐색기에서 파일을 이동시켰다면, 해당 파일 위치를 기준으로 External Library를 등록해주자.

  



