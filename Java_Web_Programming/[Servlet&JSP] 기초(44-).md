44

스파게티 코드를 만드는 JSP



예제를 작성하면서 jsp 코드의 문제인 코드의 복잡성을 이해해본다.



> #### 예제 

상황: 사용자로부터 숫자를 입력값으로 받아서 홀수인지 짝수인지 보여주는 페이지 제작

```jsp
<%

String cnt_ = request.getParameter("cnt");

int cnt = 5;
if(cnt_ != null && !cnt_.equals("")){
	cnt = Integer.parseInt(cnt_);
}

%> 

<!-- 생략 -->

<body>
	<%if(num%2 != 0){%>
	홀수입니다.
	<%} 
	else
	{%>
	짝수입니다.
	<%} %>
</body>
```

* 벌써부터 코드가 난잡해 보여야 한다.
  
* 이렇게 코드가 여러 곳에 쪼개져서 작성되는 것이 스파게티 코드의 가장 큰 특성이고 피해야 하는 코드 작성 방식이다.

  

* 여러 곳에 분산되어 관리되는 코드를 한 곳에 모아 관리하는 방식에 대해 다음시간에 배워본다.



---

45

JSP MVC Model1



> #### MVC Model1이란

* JSP 상에 코드가 여러 곳에 흩어져서 작성되는 것을 피하고 한 곳에서 코드를 작성 & 관리하는 방식으로 도입된 것이 MVC Model1 이다. 
  
* MVC Model1 방식은 입력과 출력을 기준으로 코드를 나눠서, 입력은 입력 전용 코드 블록에 모아서 작성하고, 출력은 출력 전용 코드 블록에 모아서 작성하는 것을 의미한다.
  
* 이렇게 2개의 코드 블록을 통해서 입력과 출력 코드를 잘 분류해서 작성, 관리할 수 있다. 

![image-20210721154001328]([Servlet&JSP] 기초(44-).assets/image-20210721154001328.png)

[그림 설명]

* 출력할 데이터를 입력 코드 블록에서 미리 만들어 놓는다.
* 출력 부분에서는 단지 출력만 수행한다.
* 위 방식으로 코드를 한 곳(입력 코드)에 모아서 관리할 수 있고, 출력 코드는 간단하게 작성 가능하다.      



▶▶▶▶ **복잡한 로직은 [ 입력 코드 블록 ]에서,  간단히 결과물 출력은 [ 출력 코드 블록 ]에서!** 

​    

> #### MVC Model1의 각 구성 요소

1. **Controller**

![image-20210721154336581]([Servlet&JSP] 기초(44-).assets/image-20210721154336581.png)



2. **View**

![image-20210721154418129]([Servlet&JSP] 기초(44-).assets/image-20210721154418129.png)



3. **Model**

![image-20210721154440800]([Servlet&JSP] 기초(44-).assets/image-20210721154440800.png)



▶▶ 코드를 Model을 이용해서 Controller와 View 부분으로 나눠서 작성하는 것을 MVC 모델이라고 부른다.



이렇게 입력 코드와 출력코드를 나눠놓으면 입력코드를 담당하는 사람과 출력물을 담당하는 사람이 효율적으로 작업을 할 수 있게 된다. 



---

46

MVC Model2방식

​     

> Model1 방식의 특징(한계)

* **Controller**(입력코드블록)와 **View**(출력코드블록)가 **물리적으로 분리되지 않은 방식**
  * Controller와 Model은 자바코드, View는 HTML 코드
  
* 즉, 같은 파일 내부에 위치하여 단지 공간만 나뉘었지 서로 다른 파일로 분리된 것은 아니었다. 
  
* Model2 방식은 **<u>자바 코드로 작성되는 Controller, Model</u>**과 **<u>HTML 코드로 작성되는 View</u>**를 **물리적으로 분리**해서 작성하는 생각에서 출발했다.



> Model2 방식

![image-20210722110511859]([Servlet&JSP] 기초(44-).assets/image-20210722110511859.png)



* 자바 코드로 작성되는 Controller, Model 부분은 미리 complie해서 언제든 사용가능하게 만들어 놓는다.
  
* HTML 코드로 작성되는 View 부분은 jsp로 작성하고 요청이 들어올 때만 생성되는 방식으로 작동한다.
  
* 실행속도 측면에서 더 빨라지고 관리가 더 용이하다.

​       

​      

> Dispatcher

![image-20210722111319678]([Servlet&JSP] 기초(44-).assets/image-20210722111319678.png)

* Dispatcher를 통해 Controller와 View를 연결하기 위해서 사용된다.
  * Controller와  View를 연결하는 것을 포워딩(Forwarding)이라 부른다.
  
* 하나의 Dispatcher는 여러개의 Controller와 연결되어 요청에 맞는 Controller를 동작시킨다.
  * Controller는 보통 servlet이 아닌 그냥 자바(.class)로 작성되어 존재한다.
  
* Dispatcher는 이렇게 Controller를 실행시키고 그 결과를 dispatcher에게 전달하고, dispatcher는 그것으로 view를 작동시키고 사용자에게 응답한다.





> 코드로 Dispatcher 이해

**view 파일과 controller 파일을 각각 만든 다음, controller 파일에서 dispatcher를 생성해서 둘 사이를 연결해준다.**

1) view 파일

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= result %>
</body>
</html>
```



2) controller 파일

```java
package com.newlecture.web;

// import 생략

@WebServlet("/spag")
public class Spag extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;
		
		String num_String = request.getParameter("n");
		if(num_String != null && !num_String.equals("")) {
			num = Integer.parseInt(num_);
		}
		String result;
		
		if(num%2 != 0) {
			result = "홀수";
		}
		else {
			result = "짝수";
		}
	}
}
```

 

3) dispatcher 생성(2번 파일 코드 하단부)

```java
//2번 파일 하단부

// request에 result 데이터 저장
request.setAttribute("result", result);


// dispatcher 생성 후 path 입력
RequestDispatcher dispatcher =  request.getRequestDispatcher("spag.jsp");

// dispather에 request와 response를 붙여서 path에 설정된 곳으로 보내서 페이지 출력
dispatcher.forward(request, response)
```



1번 파일과 2번 파일을 연결해 주기 위해 2번 파일 아래에 dispatcher를 생성한다.

dispatcher를 통해 2번 파일로 들어온 요청(request)을 view로 보내기 위해 path를 설정한다.

그리고 요청(request)에 데이터(result)를 붙여서 view로 보낸다. 

view에서는 출력물을 출력하는데 이 때, 이 view는 controller 안에서 호출한 것이기에 

페이지가 출력되는 URL 주소는 controller의 주소가 된다. 





4) view 페이지 수정

```jsp
<!-- 생략 -->
<body>
	<%= request.getAttribute("result") %>
</body>
```



**★ request에 데이터를 붙여서 전달하는 구조!**



forwarding vs redirecting

f: 현재 작업 중인 것을 이어서 다른 페이지로 보내는 것

r: 새로 페이지를 여는 것

