53

자세한 페이지 구현하기

(목록에 있는 글을 클릭하면 해당 글 내용이 나오게 만든다)



목표

1) 현재 존재하는 페이지는 `detail.html` 페이지인데 이를 `detail.jsp`로 연결하게 만든다.

2) 연결 주소를 `localhost:8080/notice/detail.jsp?id=숫자` 형태로 만든다. 

* 글을 클릭할 때 id값을 어떻게 전달하는지에 대해 생각해보자

3) 연결 주소의 `id` 값에 따라 해당하는 페이지가 나오도록 만든다. 

* 하나의 detail.jsp 파일에서 id값을 가지고 해당하는 데이터를 가져와서 보여 줄 수 있도록 코드를 작성한다. 





> #### 2)번부터 해결

list.jsp에 작성된 글 목록 코드 中 글 제목이자 a링크가 되는 코드 부분

```java
<td class="title indent text-align-left"><a href="detail.html"><%= rs.getString("TITLE") %></a></td>
```

* `a` 태그의 `href` 부분을 수정해서 `detail.jsp`로 연결하고 `id` 값을 줄 수 있도록 바꿔보자.

  ```java
  <a href="detail.jsp?id=<%= rs.getInt("ID") %>">
  ```

  

  

> #### 1)번 해결

* `detail.html`을 복사해서 `detail.jsp`로 이름 변경해서 저장한다. 

  * ★ 이 때 코드 내의 한글이 깨진다면 `Alt` + `Enter` 를 눌러서 인코딩 방식을 `UTF-8`로 바꿔야 한다. ★ 

    ▶▶▶ 이건 이클립스 내에서 한글이 안깨지도록 설정하는 것

* `detail.jsp` 파일 상단에 코드 블럭을 사용해서 페이지 양식을 작성한다.
  (여기에는 encoding 방식에 대한 정보도 포함되어 있다.)

  * 코드

    ```java
    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
    ```

    ▶▶▶ 이건 브라우저에게 해당 jsp파일의 인코딩 방식을 알려줘서 브라우저 상에서 한글이 깨지지 않도록 하는 것이다. 

  

  

> #### 3)번 해결

**■ `list.jsp`에서 DB와의 연결을 위해 사용했던 JDBC 코드를 복사해서 `detail.jsp`로 옮긴다.** 

```java
<%
String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
String sql = "SELECT * FROM NOTICE"; 

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con = DriverManager.getConnection(url,"아이디","비밀번호");
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(sql);
%>
    
<%
rs.close();
st.close();
con.close();
%>    
```

* 위 코드에서 SQL문을 현재 detail.jsp에서 사용하려는 데이터를 가지고 올 수 있도록 수정한다.

  ```java
  String sql = "SELECT * FROM NOTICE WHERE ID = ?";
  ```

  

* ★ `import`가 필요한 요소들은 `Ctrl` + `Space` 키를 통해 `import`를 해준다. ★ 

   

  

**■ `ID`에 해당하는 데이터를 가지고 올 수 있도록 `ID` 값을 받아오고, `PrepareStatement`로 코드를 수정한다.** 

* `SQL`문에 사용된 `id = ?` 부분을 채울 수 있도록 해당 페이지의 `id` 값을 변수로 받는 코드를 작성한다. 

```java
// 위에서 작성한 코드 블록 내에 작성(SQL문 보다는 위에 작성)
int id = Integer.parseInt(request.getParameter("id"));
```

  

* `PrepareStatement` 사용

  * [ 주의 ]  `PrepareStatment` 객체를 받을 때는 `PrepareStatement` 변수에 받아야 한다.

  ```java
  // 아래 코드는 모두 위에서 작성한 코드 블록 <% %> 내부에 작성한다. 
  
  PreparedStatement st = con.prepareStatement(sql);
  st.setInt(1, id);
  ResultSet rs = st.executeQuery();
  
  rs.next();
  ```

  * `rs.next()`를 통해서 결과 데이터 중 가장 위에 있는 데이터를 가져온다. 
    (초기 `rs`는 첫 번쨰 데이터 이전 부분을 가리키고 있기 때문)

  

■ 이제 출력을 하는 부분을 `rs`에 해당하는 데이터로 바꾸는 작업을 한다.

[ 타이틀 부분 ]

```java
<%= rs.getString("TITLE") %>
```

[ 작성일 부분 ]

```JAVA
<%= rs.getDate("REGDATE") %>
```

[ 작성자 부분 ]

```java
<%= rs.getString("WRITER_ID") %>
```

[ 조회수 부분 ]

```JAVA
<%= rs.getInt("HIT") %>
```

[ 첨부파일 부분]

```JAVA
<%= rs.getString("FILES") %>
```

[ 컨텐츠 부분 ]

* td 태그 안쪽을 모두 삭제하고 안에 입력

```java
<%= rs.getString("CONTENT") %>
```





**■ 추가 작업**

* `detail.jsp` 페이지에서 목록을 클릭하면 `list.jsp`로 갈 수 있도록 목록 버튼의 `href`를 `list.jsp`로 바꾼다. ( 원래는 `list.html`로 되어 있었음 ) 