09 Servlet으로 클라이언트에게 문자열 출력하기.



일단 Servlet class의 service 함수의 두 인자에 대해서 살펴 보자.

하나는 HttpServletRequest이고 하나는 HttpServletResponse다.

HttpServletRequest는 클라이언트의 입력과 관련된 클래스이고 HttpServletResponse는 클라이언트의 출력과 관련된 클래스다.



이번에는 HttpServletResponse를 살펴본다. 
(HttpServletResponse 클래스를 response라는 변수로 받아서 사용한다.)

HttpServletResponse 클래스의 메소드를 살펴보면 getOutputStream이라는 메소드가 존재한다.

▶getOutputStream: HttpServletResponse 객체의 OutputStream(출력객체)를 반환하는 메소드다.  
(출력객체: 출력을 위한 메소드를 수행할 수 있는 객체)

※ 보통 입력과 출력 작업은 Stream 객체가 수행한다.
→ 정확히 말하자면 Stream 객체의 메소드를 통해 입력값을 받거나 출력값을 출력한다.

HttpServletResponse의 OutputStream 메소드를 사용해서 출력객체를 반환받아 변수에 저장한다.





(1) Servlet 클래스에 들어가서 다음과 같이 수정한다.

* HttpServletResponse 객체의 OutputStream 객체를 변수에 저장한다.
* 위에서 저장한 OutputStream 객체를 사용해서 PrintStream 객체를 만든다.
* PrintStream 객체는 다양한 print메소드(println포함)을 사용할 수 있다.

```java
// response는 HttpServletResponse의 객체
OutputStream os = response.getOutputStream();

// OutputStream 객체는 바이너리 형태의 데이터를 출력하기에 사용자에게 문자열로 출력물을 전달하기 위해 형변환을 해준다. 
PrintStream out = new PrintStream(os, true);
    
// PrintStream 객체는 println을 사용해서 문자열을 클라이언트에게 출력할 수 있다. 
out.println("hello Servlet!!");
```

* PrintStream 객체
  * 기본 형태인 OutputStream 객체를 매개변수로 받아서 생성된다.
  * print 계열의 다양한 메소드들을 사용할 수 있는 객체다.
  * 매개변수의 두번째로는 버퍼 크기와 상관없이 print 메소드 실행시 바로 클라이언트에게 해당 내용을 출력한다는 옵션값이다. 
    * 기본적으로 클라이언트에게 출력물을 전달할 때는 일정 크기의 버퍼가 설정되고 출력하려는 데이터가 해당 버퍼 크기 이상일 때만 전달된다. 
    * 따라서 출력물이 버퍼보다 작은 경우, 출력이 안될 수 있다. 
    * 이를 방지하기 위해, 버퍼 크기와 상관없이 print 메소드를 수행시 내용물을 바로 클라이언트에게 전달하라는 명령어가 바로 두 번째 인자로 오는 true다.



(2) 이렇게 소스코드를 수정하면 다음과 같은 작업을 다시 해야 한다.

* 컴파일
* 배포
* 서버 종료
* 서버 실행

→ 이러한 작업들을 쉽게 수행할 수 있게 도와주는 것이 바로 통합개발환경인 Eclipse다.

이번에는 그냥 다시 수작업으로 모두 수행하고 다음부터 Eclipse를 사용한다.



(3) 결과

![image-20210709142023225]([Servlet&JSP] 기초(09-).assets/image-20210709142023225.png)



(4) 그런데 앞으로는 `PrintStream`이 아닌 `PrintWriter` 객체를 사용할 것이다. 

* 자바에서 입출력 객체는 크게 두 종류가 있다. 
* Stream 객체와 Writer 객체다.
* 다국어를 입력받거나 출력하는 경우에는 Stream 객체가 아닌 Writer 객체를 사용한다.
* 우리는 앞으로 한국어를 입출력 할 것이기에 Writer 객체를 사용할 것이다.
* 위에서 작성한 코드를 Writer 객체로 바꾸면 다음과 같다.

```java
PrintWriter out = response.getWriter();
out.println("Hello Servlet!!")
```

* PrintWriter도 PrintStream과 사용하는 메소드는 똑같다.(단지 안에 인자로 다국어를 줄 수 있다는 점이 차이)



---

10. 웹 개발을 위한 이클립스 IDE 준비하기

 

IDE의 편리함

![image-20210709143130044]([Servlet&JSP] 기초(09-).assets/image-20210709143130044.png)



* 위 과정을 하나의 단축키로 수행할 수 있다.

* 이렇게 통합개발환경은 개발 프로젝트를 수행함에 있어서 많은 일들을 편하게 해준다.

* **하지만 이렇게 편하게 수행하기 위해서는** 일단 통합개발환경에 프로젝트에 대한 정보를 알려줘야 한다.

* 그리고 이렇게 알려주기 위해 작성하는 파일을 **프로젝트 파일**이라고 한다.

  ▶ 모든 통합개발 환경은 **프로젝트 파일**이 존재한다.
  
  ▶ 코드 실행 컴파일, 편집, 배포 등등에 대한 정보를 통합개발환경에게 알려주는 파일이다.
  
  ▶ 이것은 실제로는 따로 프로젝트 파일을 만들어서 IDE에 알려주는 것이 아니라, IDE환경에서 프로젝트를 생성함으로서 알려줄 수 있다.
  
  → 프로젝트를 생성할 때 여러 옵션값들을 설정해줌으로서 알려주는 방식이다.
  
  * Target Runtime 설정
  
    * Servlet을 실행할 환경을 설정하는 항목이다.
    * Servlet을 실행시켜주는 것에는 대표적으로 톰캣이 있지만 다른 것들도 많다.
    * 우리는 톰캣을 사용할 것이기에 톰캣을 설정한다.
    * 톰캣 버전 설정 → 실제 톰캣 설치 디렉토리 입력 
  
    

이클립스 워크스페이스 설정하기

`TIL - Java-Web-Programming-jsp_file`에 이클립스 워크스페이스를 위한 `workspace`라는 이름의 디렉토리를 만든다. 



---

11 

주제: 이클립스를 활용해서 Servlet 코드를 만들고 실행해보기



프로젝트에서 홈디렉토리의 위치는 `WebContent`다.

(Maven을 사용해서 프로젝트를 만들었을 경우 홈디렉토리의 위치는 `src>main>webapp`이 된다.)



홈디렉토리에서 index.html 이라는 문서를 만들어보자.

(내용은 그냥 환영합니다!라고 작성한다.)



그리고 나서 저장을 하고 해당 문서를 Run 시키면 다음과 같은 작업이 수행된다.

* 해당 문서가 톰캣(=WAS 서버) 의 디렉토리로 옮겨진다.
* 그리고 나서 톰캣이 실행된다.
* 그리고 나서 브라우저가 띄워진다.
* 그리고 나서 현재 작성한 문서를 요청하는 페이지가 띄워진다.(localhost:8080/index.html)

(실행(Run)의 단축키는 Ctrl + f11이다.)



이렇게 띄워진 창을 확인해보자

![image-20210709200713080]([Servlet&JSP] 기초(09-).assets/image-20210709200713080.png)



여기서 주소를 보면프로젝트이름이 Context명으로 사용된 것을 볼 수 있다.

그런데 기본이 되는 프로젝트인 경우 프로젝트명이 Context명으로 사용되는 것은 좋지 않다.

▶ **기본(루트) 프로젝트는 그냥 아무런 이름 없이 바로 홈디렉토리를 가리키는 것이 좋다.**



[기타]

위에서 실행되는 브라우저는 이클립스 내부에서 설치된 브라우저다.

이를 현재 자신이 사용하고 있는 웹브라우저로 바꾸고 싶다면 `Windows > Web Browser`에서 설정해줄 수 있다.



**루트 프로젝트로 설정하기**

* `프로젝트 오른쪽 버튼 > 속성(Properties) 클릭 > Web Project Settings 클릭` 

* `Context root` 값을  `/` 로 바꿔준다.      

  ​     



**루트 프로젝트 설정 이후 기존 서버에 올라가 있던 Context 사이트 지우기**

* 기존에 Server탭의 Tomcat 아래에 프로젝트 이름으로 되어 있던 것은 Context 사이트를 의미했다.
* 방금 `JSPPrj`라는 Context 명을 지우고 루트프로젝트로 만들었기때문에 아래 보이는 `JSPPrj`는 더 이상 존재하지 않는다.
* `delete`키를 눌러서 지우고 다시 `index.html`로 돌아와서 `Ctrl` + `F11` 키를 통해 다시 실행시킨다.

![image-20210713071238623]([Servlet&JSP] 기초(09-).assets/image-20210713071238623.png)

​    

[결과 페이지]

![image-20210713071654246]([Servlet&JSP] 기초(09-).assets/image-20210713071654246.png)

* 이전과 다르게 프로젝트 이름이 URL 경로에서 사라진 것을 볼 수 있다.     





이클립스에서 서블릿 만들기

0) 자바 파일은 `src > main > java` 안에 작성한다.

1)  com.newlecture.web이라는 패키지 아래 Nana라는 클래스 파일을 만든다.

2) 다음과 같이 servlet 클래스를 작성한다.

```java
package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		out.println(" Hello ~~~ ");
	}
}
```

​    

3) 이렇게 작성한 서블릿 클래스는 `web.xml` 파일을 통해 맵핑을 시킨다.     



4) 루트 디렉토리의 `WEB-INF` 폴더 안에 `web.xml`을 찾는다. 없다면 톰캣 폴더의 `ROOT > WEB-INF`에 있는 `web.xml` 파일을 복사해서 붙여넣어준다.      



5) 기존의 `web.xml` 파일을 보면 아래와 같이 되어 있다. 

```
  <servlet>
    <servlet-name>na</servlet-name>
    <servlet-class>Nana</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>na</servlet-name>
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>
```

현재 우리가 만든 클래스 이름도 `Nana.class`이므로 위 내용과 동일하지만 패키지가 존재한다는 점에서 다르다.

따라서 패키지명까지 명시해야 맵핑이 가능하다. ▶ `com.newlecture.web.Nana`

```
  <servlet>
    <servlet-name>na</servlet-name>
    <servlet-class>com.newlecture.web.Nana</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>na</servlet-name>
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>
```

​    

6) 이렇게 저장하고 다시 `Nana.class`로 돌아와서 `Ctrl` + `F11`을 누르면 바로 다음과 같은 화면이 나온다.

* [코드 수정] + [컴파일] + [배포] + [서버 종료] + [서버 실행] + [해당 페이지 조회 ] 가 한 번에 이뤄진다. 

![image-20210713081532089]([Servlet&JSP] 기초(09-).assets/image-20210713081532089.png)

**다음시간(12)에는 web.xml말고 다른 방식으로 맵핑하는 방법에 대해서 배운다.**