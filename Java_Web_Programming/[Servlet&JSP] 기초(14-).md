14

한글과 콘텐츠(클라이언트에게 보내는 파일) 형식 출력하기



한글을 포함해서 출력하는 경우 브라우저에서 한글은 ??로 깨져서 나오게 된다.    



> 한글이 깨져서 보이는 이유는?

웹서버에서 클라이언트에게 문서를 보낼 때, 기본적으로(by default) 유럽에서 사용하는 **ISO-8859-1** 방식을 사용해서 인코딩한 다음 보낸다.

이 방식을 수정해서 UTF-8 방식으로 수행해야 한다.     



> 인코딩 방식을 수정하는 방법

출력코드 앞에 인코딩 방식을 명시적으로 지정해 줘야 한다.

```java
<Response객체이름>.setCharacterEncoding("UTF-8");

// 예시코드 ▶ response.setCharacterEncoding("UTF-8");
```



> 전체 코드 (Nana.class)

```java
package com.newlecture.web;

// import 생략

@WebServlet("/hello")
public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		for(int i=0; i<100; i++) 
			out.println((i+1) + " Hello Servlet!!<br>");
	}
}
```

​    

브라우저에서 웹페이지를 열 때, UTF-8 방식으로 읽으면 한글이 보이게 된다.

![image-20210713103652989]([Servlet&JSP] 기초(14-).assets/image-20210713103652989.png)

   

따라서 이렇게 UTF-8 방식으로 인코딩한 것을 클라이언트에게 보낼 때, **UTF-8 방식으로 보낸다는 것을 클라이언트에게 알려줘야** 브라우저가 해당 방식으로 읽을 것이다. 

▶ 클라이언트에게 보내는 Response 객체의 Header 부분에 인코딩 방식을 명시하면 된다.

(인코딩 방식이 명시되어 있지 않으면 브라우저 별로 자의적인 방식으로 읽게된다)    



> 인코딩 방식 명시하는 방법

```java
<response객체이름>.setContentType("text/html; charset=UTF-8");
```

* 해석: 클라이언트에게 보내는 문서의 글자는 UTF-8 방식으로 인코딩되었고 해당 문서는 html 문서다.

  ▶ 글자를 읽을 때는 UTF-8 방식으로 읽기에 한글이 깨지지 않고, `<br>` 태그를 읽을 때 html 코드로 읽게 된다.      



* 위 방식을 사용하면 Response 객체의 Header에 ContentType의 값이 입력된다.

​      

> 전체 코드

```java
package com.newlecture.web;

// import 생략

@WebServlet("/hello")
public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");  
		
		PrintWriter out = resp.getWriter();
		
		for(int i=0; i<100; i++) 
			out.println((i+1) + " Hello Servlet!!<br>");				
	}
}
```

​     

----

15

주제: 사용자의 요청(Request)에 포함되어 있는 입력값을 읽고 처리하는 방법 

​    

> 사용자가 요청하는 방식(2가지)

* 사용자 요청에는 두 가지 종류가 있다  → GET, POST

* 이번시간에는 GET 요청에 대해서 배워본다.     

​     



> GET 요청시 (사용자가) 입력값 주는 방법 → 쿼리 스트링

* 사용자는 보통 문서를 요청한다. 

* 사용자는 문서를 요청할 때, 추가적인 인자를 전달할 수 있다. 

* URL 뒤에 ?를 쓰고 Key값=Value값 형태로 전달한다. 

  (예시) `http://localhost/hello?cnt=3`

* 이를 쿼리 스트링이라고 부른다. 

​    

> 쿼리스트링 예시

**[ 상황 ]**

* 기존에 만든 Nana.class에서는 100번을 반복하면서 텍스트를 출력했다.
* 이를 사용자가 입력한 횟수만큼만 반복해서 출력하는 코드로 바꿔본다.

​    ![image-20210713120348091]([Servlet&JSP] 기초(14-).assets/image-20210713120348091.png)



> 코드

Request 객체의 getParameter 메소드를 사용해서 입력한 쿼리값을 읽는다.

```java
int cnt = Integer.parseInt(<Request객체이름>.getParameter("cnt"));
```

* "cnt"라는 key값으로 전달받은 값을 읽어와서(문자열 형태) 정수형으로 바꿔서 변수 cnt에 저장한다.
* 이 때 쿼리값 없이 Nana.class를 읽어오는 경우 에러가 발생한다.
* 쿼리값이 없어도 에러가 나지 않도록 하기 위해서는 기본값 설정을 사용한다. → 다음 시간에 

​    

> 결과 페이지

![image-20210713120846707]([Servlet&JSP] 기초(14-).assets/image-20210713120846707.png)

