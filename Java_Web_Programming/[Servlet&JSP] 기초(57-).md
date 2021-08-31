57

목록페이지(list.jsp)도 MVC로 변경하기





**1) list.jsp에 있는 흩어져 있는 코드 블록을 위쪽에 모은다.**

```jsp
<%
String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
String sql = "SELECT * FROM NOTICE"; 

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con = DriverManager.getConnection(url,"아이디","비밀번호");
PreparedStatement st = con.prepareStatement(sql);
ResultSet rs = st.executeQuery();


// 이 부분에서 데이터를 가지고 Notice 객체를 통해 저장
while(rs.next()){
	int id = rs.getInt("ID");
	String title =  rs.getString("TITLE");
	String writerid =  rs.getString("WRITER_ID");
	Date regdate =  rs.getDate("REGDATE");		
	int hit = rs.getInt("HIT");
	String files = rs.getString("FILES");
	String content = rs.getString("CONTENT");

	Notice notice = new Notice(
			id,
			title,
			writerid,
			regdate,
			content,
			hit,
			files
			);
}

rs.close();
st.close();
con.close();

%>
```



**2) `while(rs.next())` 안에서 불러오는 데이터들을 가지고 `Notice` 객체를 생성한다.**

* 위 코드의 `while` 부분

   

  

**3) Controller 패키지에 `NoticeListController.java`를 만든다.**

* `extends HttpServlet`를 입력

* `@WebServlet("/notice/list")`를 입력

  

  

**4) `doGet()`메소드 만들기**

* `doGet()` 메소드를 오버라이드

  

  

**5) `list`의 컨트롤러는 하나의 `Notice` 객체가 아닌 여러개의 `Notice` 객체를 만들어서 `View`에 전달해야 한다.** 

* 리스트를 만들어서 해당 리스트에 여러 개의 `Notice` 객체를 담는다.

* 이렇게 만든 리스트를 `request` 객체에 저장하면 된다. 

```java
package com.newlecture.web.controller;

// import 생략

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// List 생성
		List<Notice> list = new ArrayList<Notice>(); 
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE"; 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"아이디","비밀번호");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			
			while(rs.next()){
				int id = rs.getInt("ID");
				String title =  rs.getString("TITLE");
				String writerid =  rs.getString("WRITER_ID");
				Date regdate =  rs.getDate("REGDATE");		
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				
                // Notice 객체 생성 
				Notice notice = new Notice(
						id,
						title,
						writerid,
						regdate,
						content,
						hit,
						files
						);
				
				// 생성한 Notice 객체를 list에 담는다.
				list.add(notice);
			}
			
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        // View에게 전달을 위해 list를 request에 담는다
		request.setAttribute("list", list);
		
		//forward
		request.getRequestDispatcher("/notice/list.jsp").forward(request,response);
	}
}
```

  

  

**6) list.jsp에는 EL을 사용해서 데이터를 출력한다.**

`${list[0].title}`



**[ 반복문을 사용하는 방법 ]**

* EL은 단순히 데이터를 꺼내는 작업만 가능하고 반복은 안된다.

* 반복문은 보통 태그 라이브러리를 사용하지만 현재는 안배웠기에 코드 블록으로 한다.

```jsp
<% 

List<Notice> list = (List<Notice>)request.getAttribute("list");

for(Notice n : list) {
    pageContext.setAttribute("n",n);
%>

<tr>

<td></td>

<td>${n.writerid}</td>

<%

}

%>
```

* EL을 통해서 가져 올 수 있는 것은 전역변수 뿐

* 따라서 `for`문을 통해 가져오는 지역변수는 원칙적으로 가져올 수 없다.

* `for`문으로 통해 가져오는 데이터를 EL에서 불러오려면 데이터를 저장소에 담아서 가져와야 한다.

  * 저장소 종류: `page` , `request` , `session` , `application` 
  * 같은 페이지에서만 사용하는 데이터는 `page` 저장소가 적합하다.

* `page` 저장소에 담아서 가져온다.

  ```jsp
  pageContext.setAttribute("n",n);
  ```

  * 이렇게 `pageContext`에 담은 데이터는 해당 페이지내에서 전역변수가 되어 어디에서든지 사용 가능하다.

