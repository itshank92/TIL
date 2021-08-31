07 Servlet 프로그램 만들기



Servlet이란 Server Application을 구성하는 것으로 기능별로 코드나 나뉜 단위를 의미한다.

사용자의 요청에 따라 각각의 Servlet이 실행이 된다.

웹이라는 환경을 통해서 클라이언트의 가지 각색의 요청을 받고 각 요청에 맞는 Servlet이 실행된다.



서버 어플리케이션이라고 해서 모두 한 번에 개발할 필요가 없고 필요에 따라 하나씩(Servlet 형태로) 개발한다.

그 동안 자바 프로그램은 main() 함수로 만들었는데, 이제부터는 service()라는 이름의 함수로 프로그램을 만든다.

service() 함수는 main함수와 거의 비슷한데, 여기에 웹을 통해 입력을 받고 결과를 출력하는 부분만 추가된 형태라고 생각하면 된다.



servlet 클래스 작성 방법

* servlet 클래스는 WAS에 의해서 로드되어 실행된다.  
  * 따라서 servlet 클래스는 WAS가 로드할 수 있고 읽어서 사용할 수 있는 형태를 따라야 한다.
  * 그것이 바로 HttpServlet이라는 클래스다.
* HttpServlet이라는 클래스에는 Service라는 메소드가 정의되어 있고 이를 상속받는 각 Servlet 클래스는 이 메소드를 오버라이딩 해줘야 한다.

```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

// 우리가 만드는 servlet 클래스
// 클래스명은 중요하지 않고 HttpServlet을 상속받는 것이 중요하다.
public class Nana extends HttpServlet {
 public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
 {
  System.out.println("hello Servlet");
 }
}
```

* 이렇게 만든 Servlet은 클래스 이름을 붙여서 Nana Servlet이라고 부른다.



[실습]

이클립스는 Servlet을 만드는 것을 쉽게 도와주는 역할이지 필수적인 것은 아니다.

이번에는 처음이자 마지막으로 메모장, 톰캣, JDK를 사용해서 맨땅에 Servlet을 만들어보는 실습을 진행한다.

이 실습을 하고 난 다음에 이클립스를 통한 Servlet 생성을 하게 되면 원리를 파악한 상태라 더 쉽게 이해할 수 있을 것이다. 



[ 실습1_ Servlet 코드 작성 후 컴파일 ]

(1) 메모장을 열어서 위 Servlet 코드를 작성한다.

(2) 위 코드를 저장한 다음 실행을 위해 컴파일한다. (jsp_file이라는 폴더에 Nana.java로 저장한다. )
     ▶ 콘솔창을 열고 해당 디렉토리로 가서 컴파일 한다. 

      ```
javac Nana.java  

## 위 명령어를 실행하면 에러메시지가 나온다.
## >>>> Nana.java 파일에서 사용된 servlet 라이브러리를 찾을 수 없다는 메세지
      ```

* 이렇게 작성하면 오류가 나타난다. 
* javax.servlet을 찾을 수 없다는 오류 메세지가 출력된다. 
* servlet 라이브러리는 JDK가 포함하지 않는 라이브러리이기에 사용하려면 설정을 해줘야 한다. 
  * `-cp 라이브러리경로`를 사용해서 사용하려는 라이브러리 경로를 명시해줘야한다.
  * cp: classpath라는 의미
* servlet이라는 라이브러리는 어디에 있는가?
  * 톰캣 설치 폴더로 가서 lib 폴더를 열면 servlet-api.jar 파일이 있다.(여기에 servlet 라이브러리가 있다)
  * 해당 파일의 경로를 복사한다.

(3) 다시 돌아와서 컴파일을 수행한다.

```
javac -cp 라이브러리경로(파일명포함) Nana.java
```

▶ 컴파일 완료(Nana.class 파일 생성됨)



---

08

[ 실습2_ Servlet 객체 생성과 실행 ]

(1) Servlet class 파일을 올바른 경로에 저장하기(실행을 위해서는 `ROOT > WEB-INF > classes` 안에 저장해야 한다)

* 컴파일된 Servlet class 파일은 `ROOT`아래 `WEB-INF` (Web Information의 뜻) 안에 `classes`라는 폴더를 만들고 그 폴더 안에 위치시킨다. (`.java`파일은 필요없음 →  `.class`파일만 위치)

* 만약 저장하려는 Servlet 클래스가 패키지가 있었다면, 그 패키지의 경로가 `classes` 안에 위치해야 한다.

  ▶ 예를 들어 Servlet 클래스가 com.godcoder라는 패키지에 속했다면, `WEB-INF > classes > com > godcoder` 의 경로에 Servlet class 파일을 저장해야 한다. 

​     

※ 이렇게 위치시키면 해당 경로로 URL 요청을 보내면 저장한 Servlet 파일을 실행시킬 수 있을까?

▶ 실행시킬 수 없다. 이렇게 아무나(불특정 다수) URL을 통해서 Servlet을 접근, 실행시킬 수 있다면 그것은 큰 문제다. 

▶ WEB-INF 안쪽에 있는 내용들은 오직 서버쪽에서만 접근할 수 있다.(클라이언트는 불가능)



※ 그렇다면 사용자가 Servlet을 어떻게 요청하는가?

WEB-INF에 있는 내용들은 서버만 알고 있으면 된다. 

사용자는 단순히 요청만 하고, 웹 서버는 요청의 내용과 (WEB-INF에 있는) Servlet을 Mapping해서 연결하면 된다.

즉, 사용자의 요청과 Servlet을 Mapping하는 작업이 필요하다.

▶ URL과 맵핑된 Servlet 코드를 찾아서 실행한다.

(이때 Servlet과 맵핑시키는 URL 명칭은 Servlet의 이름과 동일할 필요가 없다)



(2) URL과 Servlet class를 맵핑한다.

`ROOT > WEB-INF > web.xml` 파일에 다음과 같은 내용을 추가한다.

[ 예시코드 _ `localhost:8080/hello` (URL)에 `Nana.class` (Servlet)를 맵핑하기 ]

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

​    

* `/hello`라는 URL을 통해 요청을 하면 `Nana`라는 Servlet class가 실행되도록 맵핑을 한 것이다.
  * 맵핑의 기준은 `<servlet-name>`을 통해서 연결된다.

![image-20210709130838013]([Servlet&JSP] 기초(07-).assets/image-20210709130838013.png)

​     

* 연결하려는 Servlet class에 패키지가 있다면 `<servlet-class>` 을 작성할 때, 패키지이름까지 포함해서 작성해야 한다. 
  ▶ (예시) `<servlet-class> Package1.Package2.Nana </servlet-class>`





(3) 작동원리

위처럼 작성을 해놓은 상태에서 클라이언트가 localhost:8080/hello로 요청을 보냈을 때 어떤 과정이 수행될까?

* 일단 WAS에서는 ROOT 안에 hello가 있는지 확인한다.
* hello가 없으면 WAS는 WEB-INF의 web.xml에서 `/hello`와 맵핑되어 있는 Servlet class가 있는지 확인한다.
* 맵핑된 Servlet class가 있다면 해당 Servlet을 실행한다.



(4) 잘 실행되었다면 하얀 화면이 나올 것이다.

우리가 작성한 Nana.java 파일에는 단순히 Console에다가 "hello Servlet"을 출력하는 것이다.

콘솔은 서버쪽에 있는 것이기에 클라이언트쪽에는 아무런 출력물도 나타나지 않을 것이다. 

따라서 흰 화면이 나온다면 정상이다. 



그렇다면 서버의 콘솔이 아닌 클라이언트에게 출력하려면 어떻게 하는가?

클라이언트에게 출력하기 위해 사용하는 도구는 바로 service 함수가 매개변수로 받는`HttpServletResponse` 클래스다. 



다음시간에는 해당 클래스를 사용해서 클라이언트에게 문자열을 출력하는 Servlet을 만들어 보자.

