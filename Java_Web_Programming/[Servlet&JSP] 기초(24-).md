24

갯수가 정해지지 않은 입력값들 받기 ▶ 배열(Array)으로 입력값 받기     

​     



> 여러개의 입력값을 여러개의 input으로 받는 것의 문제점

*  만들어지는 input 태그마다 식별자인 name을 달아줘야 하는 한계가 존재한다. 

​     

> 가변적인 입력값을 보낼때는 같은 이름의 input을 사용해서 전달한다.

* 배열을 사용해서 여러개의 입력값을 보내는 방법은 간단하다.

* 여러개의 input 태그를 만들고 그 name값을 통일하면 된다.

  ```html
  <input type="text", name="array"/>
  <input type="text", name="array"/>
  <input type="text", name="array"/>
  <input type="text", name="array"/>
  ```

* 이렇게 같은 이름으로 input 태그를 여러개 만들면, 이들은 모두 하나의 배열로 묶여서 서버에 전달된다.

  * 배열의 이름은 input 태그의 공통된 name 값으로 쓰였던 것이 된다.
  * 이렇게 입력을 받으면 서버에서는 이들을 하나의 배열로 받아, 반복문을 통해 각 요소를 꺼내볼 수 있다. 

  ```java
  // Servlet 파일
  
  String[] num_ = request.getParameterValues("array");
  
  int result = 0; // 초기값 설정
  
  for(int i=0; i<num_.length; i++) {
      // 변수 선언후 배열의 숫자 하나하나씩 할당
      int num = Integer.parseInt(num_[i]);
      result += num;
  }
  ```

  * 기존에 입력 값 하나씩 받아온 메소드: `getParameter()`

  * 여러 개의 입력 값을 받는 메소드: `getParameterValues()`

  * `for`문 안쪽의 변수 선언 코드는 반복해서 수행되는 것이 아니다.

    ▶ 변수 선언은 연산 코드가 아니기 때문에  `for`문 수행시 최초로 한번만 수행된다.

    ▶ 따라서 비효율적인 코드가 아니다.

​    

---

25, 26

주제: 상태를 유지할 필요성



실제 서비스를 만들면 값을 한 번에 입력받는 경우보다 여러 요청에 걸쳐서 값을 입력받고 이후 이들을 한꺼번에 처리하는 경우가 많다. 

예를 들면 아래와 같은 계산기가 그러하다.



![image-20210715090608059]([Servlet&JSP] 기초(24-).assets/image-20210715090608059.png)

​    

* 위 계산기는 한번에 하나의 숫자와 연산자를 입력해서 서버로 보낸다. 
* 최종적으로 사용자가 입력이 끝나면 [계산 ] 버튼을 누르고 그때 서버는 연산을 수행한다.
* 따라서 **서버는 매 번 입력 받은 값을 가지고 있어야 하고** [ 계산 ] 버튼의 요청이 들어오면 그제서야 계산을 수행한다. 

​    

> Servlet은 재시작될때마다 어떤 데이터도 없는 상태로 시작한다.

* Servlet은 요청이 들어올때만 작동하고 요청에 대한 처리가 끝나면 종료된다.
* Servlet의 종료와 동시에 기존에 입력받았던 데이터는 사라진다.

![image-20210715210145315]([Servlet&JSP] 기초(24-).assets/image-20210715210145315.png)

* 요청이 웹서버로 들어오면 디스크에 있는 Servlet 중 요청을 처리할 Servlet이 메모리 공간에 올라온다.
  (메모리 공간: 우측의 파란색 박스 // Servlet들: Disk 그림 위에 있는 페이지들 )
* 그리고 요청을 처리하고 나면 Servlet은 메모리 공간에서 다시 Disk로 내려가게되고, 관련된 데이터는 소실된다.

* 따라서 다음번 Servlet 실행시 이전에 입력받았던 값을 사용하기 위해서는 어디에 저장을 해두고 다음번에 불러와야 한다. (이러한 것을 상태 저장 혹은 상태 유지라고 부른다.)
* **Servlet Context**는 메모리 공간에 존재하는 저장공간으로 Servlet의 데이터를 저장해 놓는 역할을 수행한다.
  (웹 어플리케이션에서는 **Servlet Context**라는 용어와 함께 **Application 저장소**라고 부르기도 한다.)

![image-20210715210454211]([Servlet&JSP] 기초(24-).assets/image-20210715210454211.png)



> Context가 왜 저장소라는 의미로 쓰일까?

Context의 사전적 정의는 문맥이라는 뜻이다.

우리가 책을 읽을 때, 중간에 어떤 문맥까지 읽었는지를 기록하기 위해서 책갈피를 사용한다.

Servlet Context 역시 Servlet이 어디까지 처리했는지 그 결과 데이터를 저장하고 있다는 측면에서 "Context"라는 단어를 사용하여 표현한 것이다. 



​        





> Servlet에서 상태 유지를 위한 5가지 방법

* Servlet에서 상태 유지를 위해서 5가지 방법이 있다.

* 5가지는 크게 두 덩어리(3가지, 2가지)로 나뉜다.

  ![image-20210715091441529]([Servlet&JSP] 기초(24-).assets/image-20210715091441529.png)

  * application, session, cookie
    - Servlet이 종료가 되기 전에 값을 저장해 놓는 공간이다.
    
  * hidden input, querystring
    * 이후 설명을 하도록 한다.     

​    



> Servlet에서 상태 유지를 가능하게 하는 초 간단한 예제 수행하기

* 한 번에 하나의 값을 입력받고 계산 버튼으로 마지막에 계산 결과를 보여주는 페이지를 만든다.
  (예시: 아래 이미지 형태)
* 단 2개의 값을 받아서 계산을 수행하는 계산기를 만든다.
  * 즉, 저장소에 한번에 하나의 값만 저장할 수 있는 코드로 작성

![image-20210715173552114]([Servlet&JSP] 기초(24-).assets/image-20210715173552114.png)



1) 계산기 html  페이지를 만든다.

* 입력값은 1개만 받고 버튼은 3개다.
* 버튼은 `input` 태그의 `name` 속성을 사용해서 operator로 묶어준다.
  →  Servlet에서 구분해서 인식 가능하도록

```html
<!-- calc2.html -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Calculator</title>
</head>
<body>
	<div> 숫자를 입력하세요.</div>
	<form action="calc" method="post"> 
		<div>		
			<label> 입력 </label>
			<input	 name="num" type="text">
		</div>
		<div>		
			<input type="submit" name="operator"  value="+">
			<input type="submit" name="operator"  value="-">
			<input type="submit" name="operator"  value="=">
		</div>
	</form>
</body>
</html>
```

​    

2) 위 계산기 html의 요청을 처리할 Servlet

* 입력 값(숫자)은 한 개만 전달된다. 
  
* 연산자도 한 개가 전달된다. 
  
* 값을 저장하기 위해서는 `ServletContext`라는 객체를 만들어서 그곳에 값을 저장해야 한다.

  * 어떤 Request에 종속되는 `ServletContext` 객체를 만들기 위해서는 Request 객체의 `GetServletContext()라`는 메소드를 사용해서 객체를 생성해야 한다.

    ```java
    ServletContext "변수이름" = <Request객체이름>.GetServletcontext();
    ```

  * 이렇게 만든 `ServletContext` 객체에 저장할 대상은 총 2가지로 입력받은 숫자와 연산자다.
    

* `ServletContext`객체는 key:value 형태로 값을 저장하는 객체다.

  * 값을 저장하기 위한 메소드는 `setAttribute(key값, value값)`다.

    ```java
    ServletContext변수이름.setAttribute( key값, value값 );
    ```

    

* `ServletContext` 객체에 저장된 값을 꺼내는 메소드는 `getAttribute( key값 )`다.

  * `getAttribute(key값)`은 `key`값에 해당되는 `value`값을 `Object` 객체로 반환한다.
  * 여기서 꺼내려는 value 값이 정수형이라면 앞에 `(Integer)` 를 붙여서 강제형변환 후 꺼내면 된다.

  ```java
  ServletContext변수이름.getAttribute(key값);
  
  int num = (Integer)ServletContext변수이름.getAttribute(key값);
  ```

  



**[코드 작성]**

* 연산자의 종류에 따라서 ServletContext에 저장할 수도, 그냥 연산을 수행할 수 도 있다. 

  * 연산자의 "operator"값이 = 인 경우 계산을 수행한다.

  * 연산자의 "operator"값이 +나 -인 경우 값을 저장한다. 

    ```java
    // calc2.class 파일
    
    // 내용 생략...
    
    
    // (버튼의 operator값을 op에 받은 상태)
    // ---------- [ 계 산 ] ----------------
    // * op가 =인 경우 계산을 수행한다. 
    if(op.equals("=")) {
        int result = 0;
        // 계산을 수행하는 경우 현재값과 이전 값을 읽어와야 한다. 
        int x = (Integer)application.getAttribute("value");
        int y = v;
        String operator = (String)application.getAttribute("op");
        
        if(operator.equals("+")){
            result = x + y;
        } else {
            result = x - y;
        }
        // 결과 출력
        response.getWriter().printf("result is %d\n", result)
        
        
    // ---------- [ 값을 저장 ] ---------------    
    // op가 =가 아닌 경우(+나 -인 경우) 값을 ServletContext에 저장한다.
    } else {
        ServletContext application = request.getServletContext();
        application.setAttribute("value", v);
    application.setAttribute("op", op);
        // 화면에 출력하는 코드가 없기 때문에 사용자는 오직 빈화면만 보인다.
}
    ```
    
    * `ServletContext application = request.getServletContext();` 의 경우 해당 변수를 `else` 아래에 작성하였는데 계속 쓸 것이기에 아예 class 초기에 작성하는 것으로 수정하자. 
    
      ```java
      package com.newlecture.web;
      
      // import 생략
      
      @WebServlet("/calc2")
      public class Calc2 extends HttpServlet {
              ServletContext application = request.getServletContext();
          	
          	//... (생략)
      ```



* 값을 저장하는 경우, 어떠한 출력물도 나오지 않기에 사용자의 화면은 하얀색으로 나온다.
  * 테스트용도로 만들고 있으니 이 경우 뒤로가기를 통해 이전 페이지로 돌아가서 다음 값을 입력해준다. 





> 최종 코드(calc2.java)

```java
package com.newlecture.web;

// import 생략

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// ServletContext 객체 생성 >> Request객체의 getServletContext사용해서 연결
		ServletContext application = req.getServletContext();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		
		// 현재 요청객체(req)로 받은 입력값 저장
		int v = Integer.parseInt(req.getParameter("num")); 
		String op = req.getParameter("operator");
		
        // 현재 입력받은 연산자가 =인 경우 ▶ 결과값 계산 후 출력
		if(op.equals("=")) {
            
            // 결과값 담을 변수
			int result = 0;
            
            // ServletContext에 저장된 이전 숫자, 이전 연산자 불러온다.
            // x는 이전 숫자, operator는 이전 연산자
			int x = (Integer)application.getAttribute("value");
			int y = v;
			String operator = (String)application.getAttribute("op");
			
            // 이전 연산자가 + 인 경우 덧셈 수행
			if(operator.equals("+")) {
				result = x + y;
			// 이전 연산자가 - 인 경우 뺄셈 수행                
			} else {
				result = x - y;
			}
			out.printf("result is %d\n", result);

        // 현재 입력받은 연산자가 =가 아닌 경우 (+ 혹은 -인 경우)
        // 현재 입력값(숫자, 연산자)를 ServletContext에 저장
		} else {
			application.setAttribute("value", v);
			application.setAttribute("op", op);
            // 이렇게 종료하는 경우 아무런 출력물이 없기에 사용자에게는 빈화면이 나온다.
            // (뒤로가기를 통해 이전페이지로 돌아가서 다음 값을 입력)
		}
		
	}
}
```







> 결론

예제 수행을 통해 application 저장소 사용법을 배웠지만 사실 적절한 예제가 아니었다.

다음 시간에는 어떠한 경우에 application 저장소를 사용하는 것이 적절한지에 대해서 배워볼 것이다.





