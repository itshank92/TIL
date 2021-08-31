40

서블릿을 쉽게 개발할 수 있는 아르바이트생 (JSP(제스퍼))     

​       

> #### JSP에 대해서

* JSP는 동적 페이지를 생성하는 Servlet을 대체하기 위해 탄생하였다. 
  
* 따라서 JSP의 기능은 동적 페이지를 생성하는 Servlet.java와 동일하다.
  
* 기능은 동일하지만 JSP를 사용하는 이유는 더 간단한게 동적 페이지를 생성할 수 있기 때문이다. 
  
* JSP 파일을 실행시키면 자동적으로 톰캣 디렉토리 내부의 특정 공간에 jsp 파일의 java 버전이 만들어진다.
  ▶ 그리고 이 것이 컴파일 되어 Servlet처럼 기능을 수행한다. 







> #### JSP를 사용하는 이유 ▶ 출력물이 많은 동적 HTML 페이지를 간단하게 출력하기

* Servlet에서 HTML 페이지를 동적으로 출력하는 과정은 매우 번거로웠다.
  
* PrintWriter() 객체를 만들어서 out.write(); 메소드를 모든 html 코드 한 줄 한 줄 마다 적용시켜야 했다.
  
* JSP는 이 번거로운 작업을 대신해주는 역할을 수행한다.    





> #### JSP 사용방법

1. HTML 코드를 작성하고 확장자만 .jsp를 붙여서 저장한다.
   * 이렇게 저장하면 해당 코드는 동적으로 출력하는 코드로 자동 인식된다. 
     
   
2. jsp 파일은 그 자체로 url 명이 된다.
   * Servlet의 코드의 경우 URL을 따로 지정하기 때문에 저장되는 파일명과 URL이 다를 수 있었다. 
     * `@WebServlet()` 을 사용해서 Servlet 클래스의 URL을 설정했다.
   * 하지만 JSP의 경우 파일명과 URL이 동일하다.
     * `add.jsp`라는 파일의 URL 경로는 `add.jsp`가 된다. 




> #### 계산기를 jsp 로 구현하면서 코드 실습하기



1) 계산기의 html 파일을 복사해서 calculator.jsp 파일로 만든다.



2) calculator.jsp에서 계산기에 출력되는 코드 부분을 동적 데이터가 나올 수 있도록 수정한다.

```jsp
<td class="output" colspan="4"> ${3+4} </td>
```



3) 이렇게 수정하면 계산기의 출력창에 7이 나온다.



**[ 결론 ]**

jsp파일은 기본적으로(by default) 안에 작성된 모든 코드에 out.write();를 붙여준다. 
▶ 따라서 java 코드(Servlet코드)를 작성하고자 하면 특수한 블록을 사용해서 그 블록 안에서 작성해야 한다.      

​      






> #### (이해 TIP) 톰캣의 작동 과정

* 서버를 띄워서 브라우저에서 화면을 볼 때 그 화면은 우리가 작성한 html, java, jsp 파일을 바로 읽는 것이 아니다.
  
* 배포를 하게 되면 파일들이 톰캣의 디렉토리로 옮겨가고 그 디렉토리에서 실행된다.
  * 서버의 구체적인 디렉토리를 보고 싶다면 이클립스에서 [ Server ]에 있는  [ Tomcat ]을 더블 클릭하고 [ Overview ] 탭 내의 [Server Locations ] 블록에 있는 [ Server path ]를 확인하면 된다.
  
* jsp 파일 역시 배포를 하게 되면 Servlet 형태의 파일(.java)로 변환되어 톰캣의 디렉토리로 넘어가게 된다.



> #### jsp 파일 안에 Servlet 코드(Java 코드) 작성하는 방법

* `<% 코드 작성 %>` 형태로 작성한다. 

* `<%`로 묶은 공간에 대해 jsp는 이를 java 코드로 인식해서 작동시키다.

  ```jsp
  <%
  	int x = 3;
  	int y = 4;
  %>
  ```

​      



**[ 결론 ]**

JSP에서는 코드를 작성할 수 있는 다양한 코드 블록이 존재한다. 다음 시간에는 이들에 대해서 학습한다.






---



41

JSP 코드 블록



> #### JSP의 기본 작동 과정

* JSP 파일을 실행시키면 서버 디렉토리에 `파일이름_jsp.java` 형태의 파일이 생성된다.

![image-20210721111323182]([Servlet&JSP] 기초(40-).assets/image-20210721111323182.png)



* 해당 파일을 확인해보면 다음과 같이 구성되어 있다. 

```java
public final class calculator_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {
                     
                     
                     // 영역 1
                     
                     
                     public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {
                     
                      // 영역2
                     
                 }
}
```

* 영역 1: jsp 클래스 바로 아래 영역
  * 이 영역에는 `멤버 메소드` or `멤버 변수`를 선언할 수 있다. 
  
* 영역2: jspService 메소드 아래 영역
  * 이 영역에는 `지역변수`를 선언할 수 있다. 
    

* 사용 예시

![image-20210721125547966]([Servlet&JSP] 기초(40-).assets/image-20210721125547966.png)

* *"y의 값은"* 의 경우 코드 블럭(`<% %>`) 밖에 있기 때문에 out.write() 가 자동으로 씌워져서 Servlet 파일(`.java`)에 입력된다. 
* *"<% out.print( y ) %>"* 의 경우 코드 블럭(`<% %>`) 안에 있기에 코드가 그대로 Servlet 파일(`.java`)에 입력된다.




> #### 좀 더 간편한 **출력을 위한 코드 블록**

* 위에서 배운 대로 jsp 상에서 java로 출력할 것을 작성하려면 매번 `<% out.printf(); %>` 형태의 코드를 작성해야 한다.
  
* 좀 더 편하게 print()를 작성하는 코드 블럭은 `<%= 출력하려는 내용 %>`이다. 
  
* 위 그림에 있는 코드의 경우 아래와 같이 바꿔 쓸 수 있다. 

  ```jsp
  y의 값은 : <%= y %>       
  ```

​     



> #### 멤버 메소드, 멤버 변수를 작성하는 코드 블록

* jspservice 메소드 내부가 아닌 jsp 클래스 내에 코드를 작성하고 싶을 때 사용하는 코드 블록이다. 
  * 일반 코드 블록( `<% %>` )의 경우 jspservice 코드 블록 내에 작성되기 때문에 멤버 변수나 멤버 메소드를 작성하면 안된다.
* 멤버 메소드나 멤버 변수를 작성하기 위해서 사용하는 코드 블록은 바로 `<%! %>`이다. 
  * 느낌표(`!`)를 사용해서 멤버 메소드나 멤버 변수를 작성할 수 있따.

​        

​     



> #### 페이지 지시자 블록 `<%@ %>`

* 해당 jsp 페이지에 대한 메타 데이터를 알려주기 위한 코드 블록은 `<%@ %>` 이다.
  
* 페이지의 메타데이터의 대표적인 항목은 아래와 같다. 

  * `language` : 해당 페이지를 어떤 방식으로 인코딩할 것인지(ex_ "UTF-8")
  * `contentType` : 해당 jsp 페이지의 언어는 무엇인지 (ex_ "java")
  * `pageEncoding` :해당 페이지의 내용은 어떤 형식인지(ex_ "text/html; charset = UTF-8" ) 

* 메타데이터는 페이지 출력을 위한 정보를 브라우저에게 알려주기 위해 작성된다.

  * 페이지 지시자 블록( `<%@ %>` )의 경우, 어떤 출력 코드보다 먼저 실행된다. 
    

* 작성 예시

  * 페이지의 메타데이터는 보통 페이지의 가장 상단에 작성된다. 

    ```jsp
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    
    <html>
        <head>
            <!-- 생략 -->
        </head>
    </html>
    ```

    