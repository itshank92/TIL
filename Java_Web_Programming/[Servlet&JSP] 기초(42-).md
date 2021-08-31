42

JSP 내장객체 간단히 알아보기



> #### jsp 파일에는 눈에 보이지 않는 코드가 존재한다. 

* jsp 파일 상에서 우리가 코드 블록( `<% %>` )을 사용해서 작성하는 코드 블록은 jsp 파일의 코드의 전부가 아니다.

* jsp 파일은 실행과 동시에 자동으로 Servlet을 작성, 컴파일한다. 

* 이 과정에서 사용자가 작성한 코드 외에도 기본적으로 작성되는 코드가 존재한다. 

* 따라서 우리는 코드 블록 내에 코드를 작성할 때 해당 코드가 jsp 코드의 전부가 아님을 명심해야 한다. 

  * 어떤 변수를 생성할 때도 해당 변수가 jsp에서 자동으로 생성하는 변수와 이름이 같은 경우 오류가 발생한다. 

  * 대표적인 예가 `page`라는 이름의 변수다. 

  * jsp가 자동적으로 만드는 변수 중에 `page`가 존재하는데, 만약 사용자가 `int page = 0;`과 같이 동일한 이름의 변수를 만드는 코드를 코드 블록에 작성한다면, 에러가 발생하게 된다. 

    ```jsp
    <%
    
    	int page = 0;
    
    %>
    <html>
        <!-- 내용 생략 -->
    </html>
    ```

    * 위의 코드의 경우 page 변수가 중복되어 생성되기에 에러가 발생한다.
      (jsp가 자동 추가하는 코드에 page 생성 코드가 존재)

​     

> jsp에서 자동 생성하는 변수들 목록

![image-20210721142547662]([Servlet&JSP] 기초(42-).assets/image-20210721142547662.png)



* jsp가 자동으로 생성하는 객체들을 **<u>내장객체</u>** 라고 부른다. 
  
* 내장 객체들의 변수명에 무엇이 있는지 알고 있어야 한다.
  * `request` : 입력객체
  * `response`: 출력객체
  * `pageContext` : 해당 페이지만 사용 가능한 저장 공간
  * `application` : 모든 Servlet들이 접근하여 사용할 수 있는 저장 공간
  * `session` : 세션 별로 사용할 수 있는 저장 공간
  * `config`
  * `out`: 출력 도구
  * `page`: 해당 페이지 객체
  *  `_jspx_out` , `_jspx_page_context` (기타 등등)

​    

* **★ 이러한 내장객체들은 우리가 코드 블록 안에서 사용 가능하다. ★**

​    

> 각 내장객체의 대표적인 메소드 

* `request` 객체의 대표적인 메소드 목록

![image-20210721143457317]([Servlet&JSP] 기초(42-).assets/image-20210721143457317.png)

​    

* `response` 객체의 대표적인 메소드 목록

![image-20210721143721616]([Servlet&JSP] 기초(42-).assets/image-20210721143721616.png)

​    

* `out` 객체의 메소드

  ▶ `out` 객체의 메소드들을 직접 쓸일은 많이 없다. (jsp에서는 대부분 자동적으로 출력해주기에)

  



* `session` 객체의 대표적인 메소드

  ![image-20210721144002883]([Servlet&JSP] 기초(42-).assets/image-20210721144002883.png)



* application 객체의 대표적인 메소드

  ![image-20210721144101582]([Servlet&JSP] 기초(42-).assets/image-20210721144101582.png)

   

​     

---

43

JSP로 만드는 Hello 서블릿



※ jsp 파일은 그 이름 자체가 URL 맵핑이 되기 때문에 소문자로 작성하는 게 바람직하다. 

​     

> #### jsp 파일 생성

* Java EE 퍼스펙티브 모드에서 webapp 디렉토리 내부에 [ new ] 버튼으로 [ JSP File ]을 눌러서 생성한다. 
  
* JSP 파일은 기본적으로 html 문서 형식으로 생성된다.     

  



> #### jsp 코드 작성 

"안녕하세요"를 10번 반복해서 출력하는 jsp 코드를 작성하는 방법은 2가지가 있다. 

**1) 코드 블록 안에 모두 작성하기**

```jsp
<!-- 생략 -->

<body>
    
	<%
	for(int i = 0; i <10; i ++){
		out.println("안녕하세요<br>");
	}
	%>
    
</body>
```

​    

**2) 각각 띄어쓰기로 작성하기**

```jsp
<!-- 생략 -->

<body>
    
	<%for(int i = 0; i <10; i++){ %>
	  안녕하세요<br>
	<%} %>
    
</body>  
```



* 위 경우 코드 블록 중간에 있는 "안녕하세요" 는 `out.write("안녕하세요") ;`가 된다.
  
* 코드 블록으로 감싼 영역을 그대로 Servlet 파일 내에 코드로 작성된다. 
  
* 최종적인 Servlet 형태는 다음과 같다.

  ```java
  // jsp가 생성한 Servlet파일
  
  for(int i = 0; i<10; i++) {
      out.write("안녕하세요");
  }
  ```

  

   

> #### jsp 코드 작성2

* url의 parameter를 받는 jsp 페이지 만들기

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

String cnt_ = request.getParameter("cnt");

int cnt = 5;
if(cnt_ != null && !cnt_.equals("")){
	cnt = Integer.parseInt(cnt_);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	for(int i = 0; i <cnt; i ++){
		out.println("안녕하세요<br>");
	}
	%>
</body>
</html>
```



* 위 내용대로 작성하면 `<url경로>?cnt=3`으로 접근시 안녕하세요가 3번만 화면에 출력된다. 











