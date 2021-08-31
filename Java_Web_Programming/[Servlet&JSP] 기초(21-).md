21

학습과제 수행: 사용자 입력을 통해 계산해서 사용자에게 보여줄 수 있는 화면 만들기

구체적 내용

* 사용자에게 2개의 숫자를 입력받는다.(입력하는 페이지 → `add.html`으로 만들기
* 입력값은 POST 방식으로 서버에게 전달된다. 
* 서버에서는 2개의 숫자를 덧셈해서 그 결과를 "덧셈결과 : 숫자" 형태로 출력해서 보여준다.



> 작성코드

1. add.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Calculator</title>
</head>
<body>
	<div> 숫자를 입력하세요.</div>
	<form action="add" method="post"> 
		<label>숫자1:</label><input	 name="num1" type="text">
		<label>숫자2:</label><input	 name="num2" type="text">
		<input type="submit" value="계산">
	</form>
</body>
</html>
```

​    

2. add.java

```java
package com.newlecture.web;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet {
    
	public static boolean isStringInteger(String stringToCheck, int radix) {
        Scanner sc = new Scanner(stringToCheck.trim());
        if(!sc.hasNextInt(radix)) return false;
        sc.nextInt(radix);
        return !sc.hasNext();
    }
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		
		String num1 = req.getParameter("num1");
		String num2 = req.getParameter("num2");
		
		if(isStringInteger(num1, 10) && isStringInteger(num2, 10)) {
			int n1 = Integer.parseInt(num1);
			int n2 = Integer.parseInt(num2);
			out.println("계산결과" + (n1+n2));
		} else {
			out.println("유효한 숫자를 입력하세요.(정수만 입력 가능)");
		}

	}
}
```

​       

​      



---



22

과제 풀이



1) 이클립스에서는  Servlet을 쉽게 만들 수 있다.

▶ 생성시에 Servlet 객체를 선택하면 쉽게 해당 객체 형태가 만들어진다.

![image-20210714210901022]([Servlet&JSP] 기초(21-).assets/image-20210714210901022.png)

​    

---

23

여러 개의 Submit 버튼

![image-20210714211554362]([Servlet&JSP] 기초(21-).assets/image-20210714211554362.png)

​    

> 문제의 핵심

같은 페이지의 두 개의 버튼이 존재할 때, 각각의 버튼은 서로 다른 기능을 수행한다.

이렇게 서로 다른 기능을 수행하는 버튼들은 하나의 공통된 Servlet으로 전송되어 처리하는 방법

▶ 이 방법이 가능하려면 서로 다른 버튼의 전송에 대해 Servlet에서 구분이 가능해야 한다.

​    



> 버튼에 name값을 줘서 버튼을 구분할 수 있는 정보를 서버에 전달할 수 있다.

```html
<input type="submit" name="operator" value="덧셈">
<input type="submit" name="operator" value="뺄셈">
```

위 방식으로 덧셈 버튼을 누르면 Servlet에 `operator = 덧셈` 값이 매개 변수로 전달된다.

* 즉, name의 값을 key로 value의 값을 value로 하는 데이터가  Servlet에 전달된다.

   

> Servlet에서는 operator(name)의 값을 확인해서 그에 맞는 처리를 해준다.

```java
String op = request.getParameter("operator")
    
if (op.equals("덧셈")){
    result = x + y; 
}
```

​    

> 결과물

1) `calc.html`

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Calculator</title>
</head>
<body>
	<div> 숫자를 입력하세요.</div>
	<form action="calc" method="post"> 
		<label>숫자1:</label><input	 name="num1" type="text">
		<label>숫자2:</label><input	 name="num2" type="text">
		<input type="submit" name="operator"  value="덧셈">
		<input type="submit" name="operator"  value="뺄셈">
	</form>
</body>
</html>
```

​     

2) `Calc.java`

```java
package com.newlecture.web;

//import 생략

@WebServlet("/calc")
public class Calc extends HttpServlet {
    
    // 입력값에 대해 정수인지 아닌지 확인하는 메소드(복사해옴)
	public static boolean isStringInteger(String stringToCheck, int radix) {
        Scanner sc = new Scanner(stringToCheck.trim());
        if(!sc.hasNextInt(radix)) return false;
        sc.nextInt(radix);
        return !sc.hasNext();
    }
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// (참고) Request 객체의 인코딩은 Servlet Filter에서 수행함
        
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
    
		PrintWriter out = resp.getWriter();
		
		String num1 = req.getParameter("num1");
		String num2 = req.getParameter("num2");
		String op = req.getParameter("operator");
		
		if(isStringInteger(num1, 10) && isStringInteger(num2, 10)) {
			
			int n1 = Integer.parseInt(num1);
			int n2 = Integer.parseInt(num2);
			
			if(op.equals("덧셈")) {
				int res = n1 + n2;
				out.println("계산결과" + res);
			} else {
				int res = n1 - n2;
				out.println("계산결과" + res);
			}

		} else {
			out.println("유효한 숫자를 입력하세요.(정수만 입력 가능)");
		}

	}
}
```