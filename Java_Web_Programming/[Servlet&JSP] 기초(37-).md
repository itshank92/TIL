37 

쿠키 삭제하기

▶ 지난 시간 만들었던 계산기의 마지만 부분인 C버튼을 완성시키기 위해 쿠키 삭제 방법을 배운다.



operator의 값이 "C"인 경우를 if문으로 빼서 작성한다.

```java
// Calc3.java 

else if(operator != null && operator.equals("C")) {
    exp = "";
}
```

* 쿠키 생성시 값으로 들어가는 변수의 값을 비우는 경우, response에 쿠키가 add되는 과정에서 자동으로 쿠키에 빈값이 들어가게 된다. 
* 위의 과정으로는 쿠키가 완전히 삭제되지는 않는다. 단지 쿠키의 값이 비어있게 된 것일 뿐 쿠키는 존재한다.
  (키값 = "" 형태로 존재) 
* 위 코드와 더불어 setMaxAge(0)을 해줘야 쿠키가 완전히 삭제된다. 
  * 이 때 setMaxAge(0)은 오직 입력값이 "C"인 경우에만 실행되어야 하기에 if문으로 감싸준다. 

```java
// Calc3.java
// 위 코드 아래 부분(else가 끝나고 난 다음)

Cookie expCookie = new Cookie("exp", exp);
if(operator != null && operator.equals("C")) {
	expCookie.setMaxAge(0);    
}
response.addCookie(expCookie);
response.sendRedirect("calcpage")
```



---

38

GET과 POST에 특화된 서비스 함수

* 지금까지 우리는 Servlet 코드를 작성할 때, `service()`라는 메소드 아래 작성했다.
* 이를 GET 요청과 POST요청을 구분해서 처리하는 메소드로 좀 더 디테일한 처리를 할 수 있다.     





> #### request객체의 요청 method를 확인하는 방법

request.getMethod() 메소드를 사용하면 요청 방식(GET or POST)을 문자열 형태로 반환한다. 

따라서 이렇게 반환받은 결과를 .equals()를 사용하면 method를 구분 할 수 있다. 

```java
// Servlet 코드 

// import 생략

public class Calculator extends HttpServlet
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // GET 요청으로 들어온 경우 
    if(req.getMethod().eqauls("GET")){
        
    }
    
    // POST 요청으로 들어온 경우
    else if(req.getMethod().equals("POST")){
        
    }
} 
    
```



위 방식이 아닌 부모 클래스의 service를 사용해서 요청을 구분해서 처리하기

![image-20210720212054481]([Servlet&JSP] 기초(37-).assets/image-20210720212054481.png)

* Servlet은 기본적으로 HttpServlet을 상속받아 만들어지기에 HttpServlet는 모든 Servlet의 부모 클래스다. 

* HttpServlet의 service 메소드는 기본적으로 요청(request)을 method로 나눠서 GET 요청의 경우 doGet() 메소드로, POST 요청의 경우 doPost() 메소드로 보낸다.

* 따라서 Servlet이 부모 클래스의 service함수를 사용하는 경우, 반드시 doGet() 메소드와 doPost() 메소드를 Override해서 작성해야 한다. 

  * 작성을 안한 상태로 부모 클래스의 service()를 사용하는 경우 에러가 발생한다. 

* 부모 클래스의 service() 메소드를 사용하는 코드

  ```java
  super.service(req, resp);
  // 이렇게 작성하는 경우 doGet(), doPost() 오버라이딩 필수
  ```
  * 부모의 service 함수를 통해 doGet(), doPost()를 사용하는 방식으로 요청을 처리하는 경우, Servlet 클래스에 service함수를 굳이 작성할 필요가 없다. 
  * 어짜피 작성을 안하는 함수 중 부모 클래스에 있는 함수는 자동으로 상속받아서 실행되기 때문이다.
  * 그래서 그냥 doGet() 메소드와 doPost() 메소드만 오버라이딩 하면 된다. 





39

쿠키의 메소드인 `setPath(<url>)`는 해당 URL만 쿠키가 전달되도록 하는 것

하나의 path만 설정 가능

현재 페이지의 url이 POST 요청으로 보낼 경로와 같은 경우 form의 action을 생략 가능

