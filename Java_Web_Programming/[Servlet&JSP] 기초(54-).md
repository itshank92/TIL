54

자세한 페이지(detail.jsp)를 MVC Model1으로 변경하기

* 우리가 작성했던 `detail.jsp` 는 스파게티 방식의 복잡한 형태의 코드다.
  * 현재 `detail.jsp`에서는 자바 코드가 전체 분서 곳곳에 산개되어 있는 형태다. 



* 이를 직관적으로 바꾸기 위해 MVC 방식으로 재작성 한다. 
  * 흩어져 있던 자바 코드를 한 곳에 모아서 작성, 관리한다.



> #### 변경 코드



**1) 흩어져 있던 코드를 한 곳에 합친다.**  +  **2) DB에서 가지고 온 데이터를 변수에 저장한다.**

```java
<%

int id = Integer.parseInt(request.getParameter("id"));

String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
String sql = "SELECT * FROM NOTICE WHERE ID=?"; 

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con = DriverManager.getConnection(url,"아이디","비밀번호");
PreparedStatement st = con.prepareStatement(sql);
st.setInt(1, id);
ResultSet rs = st.executeQuery();

rs.next();

String title = rs.getString("TITLE");
Date regdate = rs.getDate("REGDATE");
String writerid = rs.getString("WRITER_ID");
int hit = rs.getInt("HIT");
String files = rs.getString("FILES");
String content = rs.getString("CONTENT");



rs.close();
st.close();
con.close();
%>    
```

* `Date` 데이터 형식은 `java.util`에서 `import` 해오는 것이다. 



**3) HTML 코드 내에 사용하는 데이터를 앞 서 만든 변수로 바꾼다.** 

[ 예시 코드_ 타이틀 ]

```java
<tr>
    <th> 제목 </th>
    <td> <%= title %>  </td>
</tr>
```

  

---

55

MVC Model2 방식으로 변경하기



> #### MVC 기초



**[ MVC란 ]**

* C: 모아 놓은 자바 코드 

* V: 모아 놓은 HTML 코드

* M: C에서 생성한 변수

  

**[ MVC 기초 설명]**

* MVC는 C와 V를 구분해서 따로 분리한다.
* V에서 사용되는 데이터를 C에서 변수로 만들어서 제공하는데 이를 M이라고 부른다.

   

**[ MVC Model1과 Model2의 차이점 ]**

* Model1 방식은 하나의 jsp 파일에서 MVC를 나누는 것이다.
* Model2 방식은 V와 C를 물리적으로 두 개의 파일로 나누고, M의 공유를 통해서 V와 C가 통신하도록 하는 방식이다. 
  * V와 C가 M을 공유하기 위해서 사용할 수 있는 저장소 4가지
    * pageContext, request, session, application



**[ Model2 방식의 장점 ]**

* 나눠서 관리하기에 협업에 유리하다.
* 재사용할 때도 용이하다.
* C의 경우 미리 컴파일을 해놓을 수 있고 이를 통해 V가 호출 될 때, V만 컴파일하면 되기에 성능상 좋다.



 1) detail.jsp에서 상단과 하단의 코드 블록을 복사해서 새로운 파일에 저장한다.

```java
package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 웹서블릿으로 주소 연결(해당 주소로 오는 요청은 이곳으로 오게 된다)
@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE ID=?"; 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"아이디","비밀번호");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			rs.next();
			
			String title = rs.getString("TITLE");
			Date regdate = rs.getDate("REGDATE");
			String writerid = rs.getString("WRITER_ID");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			String content = rs.getString("CONTENT");
			
			// request에 데이터 저장
			// 데이터들이 지역변수이기에 여기서 저장한다. 
			request.setAttribute("title", title);
			request.setAttribute("regdate", regdate);
			request.setAttribute("writerid", writerid);
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("content", content);
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 포워드
		request.getRequestDispatcher("/notice/detail.jsp").forward(request,response);
		
	}
}
```



* `com.newlecture.web`에 `controller`라는 패키지를 만들어주고 `NoticeDetailController`라는 class를 만들어준다.
* `extends HttpServlet`을 통해 상속 받는다.
* `@WebServlet("/notice/detail")` 을 통해 `루트주소/notice/detail`로 들어오는 모든 요청을 해당 Controller가 처리하도록 만든다.  
  * `list.jsp`에서 각 글들의 제목이 가리키는 주소를 `detail.jsp`가 아닌 `detail`로 작성해줘야 한다. 
  * 우리는 각 글의 세부내용을 보여주기 위해 일단 사용자의 요청을 `Controller`로 보내고 그곳에서 데이터를 입력해서 `jsp` 페이지로 보내는 과정을 수행할 것이다. 
  * `Controller`가 받는 요청 주소는 `WebServlet` 어노테이션에서 명시한 것과 같이 `notice/detail`이지 `notice/detail.jsp`가 아니다.
* GET 요청을 전용으로 처리하는 doGet 메소드를 추가한다.
* doGET 메소드 안에 복사한 코드 블록을 붙여넣는다. 

  

  

2) `Controller`를 통해서 데이터를 처리하고, 마지막에 V에 해당하는 jsp 파일로 연결시켜준다.

(해당 코드는 위에 포함되어 있다 (//포워딩 부분) )

* 연결 종류는 2종류(redirect, forward)
* 데이터를 담아서 연결시키는 것은 forward이기에 여기서는 forward를 사용한다.
  * Dispatcher라는 객체를 통해 jsp파일 연결 
    * Dispatcher는 request를 통해서 얻는다.
  * request에 전달할 데이터를 담는다. 
    * `setAttribute()`를 사용해서 담는다. 

  



3) jsp 파일에서는 request에 담긴 데이터를 꺼내서 사용한다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>

<!-- 생략 -->
<tr>
    <th>제목</th>
    <td> <%= request.getAttribute("title") %>  </td>
</tr>
<!-- 생략 -->
```

* `request.getAttribute()`를 통해서 꺼낸다. 