31 

쿠키의 `maxAge` 옵션    





> ### 쿠키의 유효기간

쿠키는 기본적으로(by default) 브라우저의 생존 기간만큼 존재한다. 

즉, 브라우저가 종료되는 경우 거기에 속한 쿠키도 모두 종료된다.

브라우저의 생애주기와 달리 쿠키의 유효기간을 설정하고 싶다면 maxAge 옵션을 설정하면 된다.



maxAge가 설정되지 않은 쿠키(=브라우저와 생애주기가 같은 쿠키)는 기본적으로 브라우저의 in memory 공간에 저장된다.

▶ 따라서 브라우저 종료시 데이터가 같이 날라가는 것이다.



하지만 maxAge가 설정된 쿠키의 경우 외부 파일로 저장이 된다. 

▶ 시스템상에서 설정된 특정 경로의 디렉토리 내에 저장되기  때문에 브라우저가 닫힌다고 해도 남아있게 된다.       

​    



maxAge 설정하는 코드

```java
valueCookie.setMaxAge(1000);
// 1000초의 쿠키 유효기간을 설정한 것이다. 
```

* maxAge 인자의 단위는 초(sec)다.
* 위에서 maxAge를 설정한 valueCookie는 브라우저가 닫혀도 1000초까지는 살아있게 된다.



---

32

application, session, cookie의 차이점



application 저장소

* 전역범위에서 사용하는 데이터를 저장
* WAS가 실행되고 종료될 때까지 데이터를 살려둠
* WAS 서버의 메모리에 저장이 된다.





Session 저장소

* Session 범위에서 사용하는 데이터를 저장
* Session이 시작해서 종료될 때까지 데이터를 살려둠
* WAS 서버의 메모리에 저장이 된다. 



Cookie

* 웹브라우저 별로 지정한 path의 범주 내에서 사용하는 데이터를 저장
* 웹 브라우저에 전달한 시간부터 만료 시간까지 데이터를 살려둠
* 웹 브라우저의 메모리 또는 파일에 저장한다. 



문제

1년이라는 기간동안 저장해야하는 데이터의 경우 어디에 저장하여야 하는가?

쿠키

세션은 쿠키에 포함된 세션아이디로 저장된다.

따라서 세션 저장소의 경우 쿠키가 새로 만들어지면 기존의 저장소가 없어진다.

1년이라는 기간동안 서버 측 자원을 점유하는 것은 매우 비효율적이다.



---

33

서버에서 페이지 전환해주기(redirect)

![image-20210719221345341]([Servlet&JSP] 기초(31-).assets/image-20210719221345341.png)요청에 대한 응답으로 다른 html 페이지로 연결해서 전달하는 것



예제 코드

```java
response.sendRedirect("calc2.html");
```

* 위 코드는 calc2.java에서 else 영역의 마지막에 작성한다.
* 이를 통해 기존에 else 구분의 경우 아무런 출력값이 없어 빈 화면이 나왔는데, calc2.html으로 돌아갈 수 있게 되었다. 

​     

---

34, 35

*"이번에만 Servlet을 사용해서 동적인 페이지를 만들어보고 다음부터는 JSP를 사용해서 동적페이지를 만든다."* 

동적인 페이지(=서버 페이지)의 필요성



동적페이지란?

요청에 따른 데이터가 반영된 페이지로, 요청에 따라 만들어지는 내용이 다르다는 특성이 있다.

![image-20210720075643255]([Servlet&JSP] 기초(31-).assets/image-20210720075643255.png)

* `Servlet(/calc)` : 입력받은 값을 가지고 계산을 수행
* `Servlet(/calcpage)` : 동적으로 문서를 작성해서 출력





동적페이지 만들기

1) 정적인 페이지를 만든다.

* 간단한 계산기 페이지를 만든다.

```html
<!-- calc3.html -->

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
input{
	width: 50px;
	height: 50px;
}
.output{
	height: 50px;
	background: #e9e9e9;
	font-size: 24px;
	font-weight: bold;
	text-align: right;
	padding: 0px 5px; 
}

</style>
</head>
<body>
	<form action="calc3", method = "post">	
		<table>
			<tr>
				<td class="output" colspan="4">0</td>
			</tr>
			<tr>
				<td><input type="submit" name="operator" value="CE"></td>
				<td><input type="submit" name="operator" value="C"></td>
				<td><input type="submit" name="operator" value="BS"></td>
				<td><input type="submit" name="operator" value="÷"></td>
			</tr>
			<tr>
				<td><input type="submit" name="value" value="7"></td>
				<td><input type="submit" name="value" value="8"></td>
				<td><input type="submit" name="value" value="9"></td>
				<td><input type="submit" name="operator" value="X"></td>
			</tr>
									<tr>
				<td><input type="submit" name="value" value="4"></td>
				<td><input type="submit" name="value" value="5"></td>
				<td><input type="submit" name="value" value="6"></td>
				<td><input type="submit" name="operator" value="-"></td>
			</tr>
			<tr>
				<td><input type="submit" name="value" value="1"></td>
				<td><input type="submit" name="value" value="2"></td>
				<td><input type="submit" name="value" value="3"></td>
				<td><input type="submit" name="operator" value="+"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="value" value="0"></td>
				<td><input type="submit" name="dot" value="."></td>
				<td><input type="submit" name="operator" value="="></td>
			</tr>		
		</table>
	</form>
</body>
</html>
```



2) 정적인 페이지의 모든 코드를 복사해서 Servlet에 붙여 넣는다.

* 동적인 페이지를 보여주기 위한 기능을 수행하는 새로운 Servlet을 만든다. 

* PrintWriter() 객체를 생성해서 모든 html 코드를 printf로 출력한다.
* 동적으로 생성한 데이터를 출력하고 싶으면 %d와 같은 코드를 사용해서 출력한다. 

```java
// CalcPage.java

// import 생략

@WebServlet("/calcpage")
public class calc3 extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"EUC-KR\">");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("input{");
		out.write("width: 50px;");
		out.write("height: 50px;");
		out.write("}");
		out.write(".output{");
		out.write("height: 50px;");
		out.write("background: #e9e9e9;");
		out.write("font-size: 24px;");
		out.write("font-weight: bold;");
		out.write("text-align: right;");
		out.write("padding: 0px 5px;"); 
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<form action=\"calc3\", method = \"post\">");	
		out.write("<table>");
		out.write("<tr>");
		out.write("<td class=\"output\" colspan=\"4\">0</td>");
		out.write("</tr>");
		out.write("	<tr>");
		out.write("						<td><input type=\"submit\" name=\"operator\" value=\"CE\"></td>");
		out.write("						<td><input type=\"submit\" name=\"operator\" value=\"C\"></td>");
		out.write("						<td><input type=\"submit\" name=\"operator\" value=\"BS\"></td>");
		out.write("						<td><input type=\"submit\" name=\"operator\" value=\"/\"></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("						<td><input type=\"submit\" name=\"value\" value=\"7\"></td>");
		out.write("						<td><input type=\"submit\" name=\"value\" value=\"8\"></td>");
		out.write("						<td><input type=\"submit\" name=\"value\" value=\"9\"></td>");
		out.write("						<td><input type=\"submit\" name=\"operator\" value=\"*\"></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("						<td><input type=\"submit\" name=\"value\" value=\"4\"></td>");
		out.write("						<td><input type=\"submit\" name=\"value\" value=\"5\"></td>");
		out.write("						<td><input type=\"submit\" name=\"value\" value=\"6\"></td>");
		out.write("						<td><input type=\"submit\" name=\"operator\" value=\"-\"></td>");
		out.write("					</tr>");
		out.write("<tr>");
		out.write("						<td><input type=\"submit\" name=\"value\" value=\"1\"></td>");
		out.write("						<td><input type=\"submit\" name=\"value\" value=\"2\"></td>");
		out.write("						<td><input type=\"submit\" name=\"value\" value=\"3\"></td>");
		out.write("						<td><input type=\"submit\" name=\"operator\" value=\"+\"></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("						<td></td>");
		out.write("						<td><input type=\"submit\" name=\"value\" value=\"0\"></td>");
		out.write("						<td><input type=\"submit\" name=\"dot\" value=\".\"></td>");
		out.write("						<td><input type=\"submit\" name=\"operator\" value=\"=\"></td>");
		out.write("</tr>");		
		out.write("				</table>");
		out.write("			</form>");
		out.write("		</body>");
		out.write("		</html>");
	}
}
```

* 출력물에 동적 데이터를 입력하고자 하는 경우, 자바 코드로 쉽게 가능하다.

  ```java
  // 예시
  
  out.write("<td class=\"output\" colspan=\"4\"> %d </td>", 3+4);
   
  ```

  * 위 코드를 실행시키면 화면의 `%d`자리에 `7`이 출력된다.
  * html 문서에서 3+4를 작성해서 출력하는 경우 그냥 3+4 자체가 출력되는 것과는 대조적이다.
    (정적페이지 로딩과 동적 페이지 로딩의 차이)





---



36

계산기 Servlet 완성하기



> ### 만들려는 계산기

숫자와 연산자의 경우 계속 입력받으며 누적해서 쿠키로 만든다.

* 쿠키에 저장되는 것은 누적된 연산식(ex_ `3 + 4 - 2`)

이렇게 만들어진 쿠키 데이터를 계산기 화면에 보여준다.

마지막에 =를 입력받으면 누적된 계산식이 실행되고 최종 결과값만 화면에 보여준다. 



calc3.html: 초기 계산기 페이지

Calc3.java: POST로 받은 데이터를 가지고 expression을 만드는 역할

CalcPage.java: Calc3.java에서 만든 expression을 Cookie를 통해 전달받아서 동적 페이지 생성





> ### JVM에서 자바스크립트를 실행시킬 수 있는 라이브러리 ▶ ScriptEngine

우리가 쿠키에 누적하면서 만든 연산식은 자바스크립트 형태인데, 이를 JVM 환경에서 실행시키기 위한 라이브러리는 바로 ScriptEngine이다. 

1) ScriptEngine 생성

```java
ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
```

* ScriptEngine은 ScriptEngineManager() 객체의 getEngineByName을 통해서 생성된다. 
* 어떤 ScriptEngine을 불러올 것인가는 getEngineByName() 메소드의 인자를 통해서 결정된다. 
* nashorn 엔진의 경우 자바스크립트 코드를 실행시키는 기능의 엔진이다. 



2) 생성한 엔진의 eval() 메소드를 사용해서 코드를 실행시키고 이를 exp에 담는다.

```java
exp = String.valueOf(engine.eval(exp));
```

* 엔진의 eval() 메소드는 자바 스크립트 코드를 인자로 받아서 이를 실행시킨다.
* eval() 메소드의 반환값은 Object 객체이기에 이를 String 변수(exp)에 담으려면 강제 형변환을 시켜야 한다. 



3) 예외처리(try, catch)의 경우 빨간줄로 나온 코드를 클릭해서 자동 완성을 시키면 된다. 

```JAVA
// Calc3.java

if(operator != null && operator.equals("=")) {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    try {
        exp = String.valueOf(engine.eval(exp));
    } catch (ScriptException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
```



※ 주의 ※

ScriptEngine의 경우 향후 서비스가 종료 예정인 라이브러리다.

