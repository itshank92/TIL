27

Session 객체로 상태 값 저장하기(Application 객체와의 차이점)



1) Session 객체 만들기

Request객체의 getSession() 메소드를 통해서 Session 객체 생성하고 이를 HttpSession 변수에 담기

```java
HttpSession session = request.getSession();
```



2) 값 저장, 값 불러오기

앞서 배운 Application 객체와 동일한 메소드를 통해 저장과 불러오기 수행

▶ `getAttribute()`, `setAttribute()`

```java
// 값 불러오기
int x = (Integer)session.getAttribute("value");

// 값 저장
session. setAttribute("value", v);
```

​     

​     



> #### Application 객체와 Session 객체의 차이점

Application객체

* 이름과 같이 Application객체에 저장된 데이터는 application 전역에서 사용할 수 있다.
* 사용자가 달라도 같은 application을 사용하기에 이전에 입력한 값을 사용할 수 있다.

Session 객체

* Session 객체에 저장된 데이터는 Session 범주 내에서만 사용 가능하다.
* Session이란?
  * 현재 접속한 사용자를 의미한다.
  * 사용자별로 Session은 다를 수 있다.

​     

​     

---

28

웹 서버가 사용자(Session)을 구분하는 방식



> #### 요청과 Session ID(SID) 생성

![image-20210719092147393]([Servlet&JSP] 기초(27-).assets/image-20210719092147393.png)

**< 오른쪽 WAS 박스 설명 >**

* **Servlet** 
* 요청을 처리할 Servlet
  
* **application 저장 공간**  ▶ 공용 저장소

  * Servlet간 공유해야 하는 데이터를 저장하는 공간
  * 모든 요청에 대한 Servlet은 application 공간에 데이터를 저장할 수 있다. 
    

* **Session 저장 공간**  ▶ 개인 저장소

  * Session 별로 나눠서 저장해야 하는 데이터를 담아두는 공간
  * Session 저장공간의 경우, 현재 Session에 해당하는 영역에만 저장할 수 있다.

  * 이 때 사용되는 것이 바로 SID(Session ID)로, 이 세션아이디가 있어야 해당 공간에 저장할 수 있다.     

| Session 저장소에 저장할지 Application 저장소에 저장할지는 데이터의 속성에 따라서 적절히 결정하면 된다. |
| :----------------------------------------------------------: |



**< 과정 설명 >**

1) 사용자에게 최초의 요청이 들어온다.

* 최초의 요청이기에 Session ID(SID)가 존재하지 않는다. 
* 따라서 Session 저장공간은 사용 불가능하다. 
* 하지만 Application 저장공간은 사용할 수 있다. 
  

2) 사용자 요청에 대해 WAS가 응답을 하면서 새로운 SID(Session ID)를 생성해서 포함시켜 전달한다.      



3) 다음번 요청부터는 요청에 SID가 포함되어 전달된다.

* SID를 사용해서  Session 저장소를 사용할 수 있다.      



| 요약 | WAS는 요청에 대해 SID를 발급하고, 요청은 SID를 항상 포함시켜 요청을 한다. <br />같은 브라우저의 경우 같은 SID 사용하게 된다.(브라우저 종료시 SID 사라짐) |
| ---- | ------------------------------------------------------------ |



> #### Session 객체가 가지고 있는 메소드들

* Session ID(SID)의 경우 최종 요청 이후에 일정 시간이 흐르면 해당 SID는 WAS측에서 삭제된다.
* 이를 **Session Timeout(세션 타임아웃)**이라고 부른다.

![image-20210719092448011]([Servlet&JSP] 기초(27-).assets/image-20210719092448011.png)

​     

---

29 

쿠키(Cookie)를 이용하여 상태 값 저장하는 방법

(지난 시간에는 application 객체, session 객체를 사용해서 상태값을 저장하는 것을 배웠다면 이번에는 cookie를 사용해서 상태값 저장하는 방법에 대해 배워볼 것이다.)



쿠키가 이전에 배운 Application, Session 저장소와 다른점

![image-20210719100158884]([Servlet&JSP] 기초(27-).assets/image-20210719100158884.png)

* Application, Session 저장소는 모두 WAS 영역에 존재하며 데이터를 저장하였다.
* 하지만 Cookie의 경우 WAS가 아닌 클라이언트 쪽에 저장된다는 특성이  있다.

| 어떤 데이터를 WAS쪽에 저장하기 싫은 경우, Cookie에 데이터를 담아 응답에 포함시켜 보낸다.<br />이렇게 보낸 Cookie 데이터는 클라이언트 쪽에 저장된다. |
| ------------------------------------------------------------ |



쿠키에 대한 이해

![image-20210719100224247]([Servlet&JSP] 기초(27-).assets/image-20210719100224247.png)

클라이언트쪽에서 서버로 요청을 보낼 때 포함되는 데이터는 크게 3가지 종류가 존재

1) 헤더정보: 브라우저가 알아서 세팅해준다.

2) 사용자 데이터: 사용자가 입력한 데이터

3) 쿠키: 클라이언트쪽에 쿠키가 존재하는 경우 브라우저가 이를 요청에 포함시킨다.



서버(WAS)쪽에서 쿠키 처리하는 메소드

* 요청에 대해 getCookies() 메소드를 사용해서 쿠키를 꺼낸다. 

* 응답을 보낼 때는 addCookies() 메소드를 사용해서 응답에 쿠키를 포함시킨다.     





쿠키 사용 코드

> 쿠키 저장하기 

* 쿠키는 키(key)와 값(value)으로 나뉜다. 
  * 쿠키의 이름이 key에 해당하고 해당 쿠키의 값이 value에 해당한다. 
  * ※ 쿠키의 값(value)로 보낼 수 있는 데이터 유형은 반드시 문자열(String)이다. 
  * ▶ ▶ ▶ 따라서 문자열이 아닌 데이터의 경우 형변환을 해서 쿠키에 넣어야 한다.
    * String.valueof() 메소드를 통해 다른 유형의 데이터를 문자열로 바꿀 수 있다.

```java
Cookie cookie = new Cookie("c", String.valueof(result));
response.addCookie(cookie);
```

​       



> 쿠키 읽기

* 요청에는 복수의 쿠키가 포함되어 있을 수 있기에 쿠키를 읽을 때는 항상 배열로 읽는다. 
  * 요청 객체가 쿠키를 읽는 메소드는 getCookies() 다. 
* 쿠키들을 배열로 받기에 원하는 쿠키를 찾기 위해서는 for문으로 순회하면서 key값을 비교해서 찾는다. 
  * 쿠키 객체의 key값은 getName() 메소드를 통해 얻을 수 있다. 

```java
Cookie[] cookies = request.getCookies();
String _c = "";

if(cookies != null){
	for(Cookie cookie : cookies)
        if("c".equals(cookie.getName())){
            _c = cookie.getValue();
			break;            
        }
}
```

​     

​     

> #### 쿠키 사용 실제 코드

```java
package com.newlecture.web;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// ServletContext 객체 생성 >> Request객체의 getServletContext사용해서 연결
		ServletContext application = req.getServletContext();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		Cookie[] cookies = req.getCookies();
		
		int v = Integer.parseInt(req.getParameter("num")); 
		String op = req.getParameter("operator");
		
		if(op.equals("=")) {
			int result = 0;
			
//			int x = (Integer)application.getAttribute("value");
			int x = 0;
			for(Cookie c:cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
				}
			}
			
			
			int y = v;
//			String operator = (String)application.getAttribute("op");
			String operator = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
				}
			}
			
			
			if(operator.equals("+")) {
				result = x + y;
			} else {
				result = x - y;
			}
			out.printf("result is %d\n", result);
		} else {
//			application.setAttribute("value", v);
//			application.setAttribute("op", op);
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			
			resp.addCookie(valueCookie);
			resp.addCookie(opCookie);
		}
		
	}
}
```



---

30

쿠키의 path  옵션



여러개의 서블릿을 사용할 때, 서블릿 별로 쿠키는 달라야 한다.

따라서 우리가 쿠키를 설정할 때 어떤 서블릿에 대한 것인지 설정할 수 있어야 한다.

▶ 쿠키의 path 설정을 통해 이것이 가능하다.      

​      

> #### 쿠키의 path 설정

```java
valueCookie.setPath("/"); 
// valueCookie는 만든 쿠키 객체의 이름
```

▶ 모든 페이지 요청에 대해 `valueCookie`를 가져온다.

```java
valueCookie.setPath("/notice/")
```

▶ URL 경로에서 루트 주소 이후 `/notice`가 오는 페이지를 요청하는 경우에만 `valueCookie`를 가져온다.     



> #### setPath의 사용

어떤 쿠키가 특정한 URL 처리에서만 사용되는 경우 `setPath()`를 통해 해당 URL 요청시에만 쿠키가 (요청 객체에 붙어서) 서버측에 전달되도록 설정할 수 있다. 

모든 URL  요청에 대한 쿠키는 `setPath("/")`의 형태로 작성한다. 





> #### setPath 코드의 위치

`setPath()`를 설정하는 코드는 쿠키 생성코드와 `response` 객체에 쿠키를 add하는 코드 사이에 작성한다.

▶ 즉, 쿠키를 생성해서 `response` 객체에 붙여서 돌려보내기 전에 작성하는 것이다.
