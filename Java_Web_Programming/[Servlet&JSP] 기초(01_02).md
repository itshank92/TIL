01

자바로 웹개발을 할 때 필요한 것들

1) JVM을 위한 ▶ JDK = 자바

2) WAS를 위한 ▶ Servlet, JSP

3) DB를 위한 ▶ JDBC



자바 웹 프로그래밍 순서

1) 자바 프로그램 개발

2) 1번에 Servlet이라는 API를 얹으면 자바 웹 프로그램이 된다.

▶ 이 단계까지만 해도 자바 웹 프로그래밍이 완료될 수 있다. 하지만 Servlet의 경우 문서를 출력하는 부분에 있어서 비효율적인 측면이 존재한다. 

▶ HTML 문서 출력을 간결하게 만들 수 있는 JSP라는 또다른 API를 사용하게 된다. 

3) Servlet을 얹은 자바 프로그램에 JSP를 또 얹는다.

▶ 여기까지 수행을 하고나면 자바 웹 프로그램은 어느정도 완성이 되었는데, 코드가 어지러워 진다는 문제가 발생한다. (스파게티 코드)

▶ 코드를 잘 정리하고자 JSP MVC라는 방식으로 웹프로그래밍을 하게 된다.

4) JSP MVP라는 방식으로 자바 웹 프로그래밍을 수행한다. 





학습 순서

1) Servlet을 일단 먼저 배운다.

2) Servlet을 도와줄 수 있는 페이지 기반의 프로그래밍 방식인 JSP를 배운다.

3) JSP 코드를 잘 분류하고 나눠서 정리하는 방식인 JSP MVC에 대해 배운다.

▶ Servlet, JSP, JSP MVC는 하나의 덩어리라고 보면 된다. 

(이후과정) >> JSP MVC를 포함해서 더 발전시킨 것이 Spring MVC에 대해 배운다.



---

03

웹서버프로그램과 Servlet의 관계



프론트엔드에서 클라이언트가 어떤 데이터에 대한 요청을 하면 이 요청은 백엔드의 Web서버가 받는다. 

Web서버는 이 요청을 수행할 코드(데이터를 찾아서 웹페이지(html)을 만들 코드)를 찾아야 한다.

이 코드를 찾았으면 이 코드를 실행시켜야 하는데 이를 수행하는 서버가 Web Application Server, 즉 WAS다. 

즉, 실제 코드를 작동시켜서 요청 데이터를 가지고 웹페이지를 만드는 역할을 수행하는 것이 WAS다.



사용자가 요구하는 웹페이지가 동적으로 구성되어야 하는 페이지라면(즉, 미리 만들어진 페이지가 아니라 코드를 통해 새로 만들어야 하는 페이지라면) WAS 서버에서 코드를 실행시켜서 해당 페이지를 만든다.

▶ WAS 서버에서 코드를 수행하는 프로그램을 Server Application이라고 한다.



서버 환경은 WEB서버와 WAS서버로 구성된다.



> Servlet 명칭의 유래

![image-20210708231934382]([Servlet&JSP] 기초(01_02).assets/image-20210708231934382.png)

클라이언트는 웹서버에 여러 종류의 요청을 보낼 수 있다.(CRUD)

웹서버는 WAS에 있는 Server Application을 통해 각 요청에 해당하는 코드를 수행한다.

따라서 각 요청을 처리하는 Server Application은 조각 조각이라고 볼 수 있다. 

이렇게 Server Application이 조각난 것을 Servlet이라고 부른다.



Server Application의 조각 = Servlet

Servlet을 만들려면 만들 수 있는 환경을 구축하는 것이 필요하다.



---

04

tomcat9버전 설치하기 

apache 사이트에서 tomcat 9버전을 설치한다. 

회사용 서버로 다운받으려면 installer 버전을 설치하면 된다.

(일반 버전과 기능은 동일하지만 2가지 차이가 존재한다.)

1) 설치파일이 다운 완료되면 자동으로 실행된다.

2) 운영체제에 서비스 목록에 자동으로 등록된다.

▶ 운영체제의 서비스: 시작과 동시에 매번 자동으로 실행되는 프로그램들





톰캣 설치후 내부 폴더 설명

bin(binary)

conf(configuration) : 설정 파일

lib(library): 라이브러리

logs



bin > startup.bat 파일 더블클릭하면 tomcat이 실행될 수 있는지 확인 가능하다.

▶ tomcat이 실행 될 수 있으면 어떤 화면이 계속 떠있게 된다.

▶ tomcat이 실행될 수 없으면 바로 꺼지게 된다.



tomcat이 실행될 수 없는 경우 두 가지를 설정해야 한다.

1) 환경변수 설정 (환경변수에 JAVA_HOME이 설정되어 있어야 한다) 

▶ JAVA라는 JDK를 필요로하는 어플리케이션(대표적으로 톰캣)이 자바 코드를 실행시키기 위해 필요한 경로

(JAVA_HOME은 JDK가 설치된 폴더로 설정해야 한다)

2) 다른 프로그램이 실행중이라서 포트가 사용중일 수 있다.(포트번호 충돌)

▶ 포트번호가 충돌될만한 프로그램이 실행되고 있다면 종료한다.



실행된다면 localhost:8080을 들어가서 톰캣 서버가 켜졌는지 확인한다.



---

05

톰캣은 기본적으로 WAS고 WAS에 웹서버 기능이 포함되어서 크게는 웹서버라고 볼 수 있다.

웹서버: 웹문서를 제공해주는 것



그러면 어떤식으로 웹문서를 제공하는가?



예제실습

메모장을 켜서 아무말이나 적고 저장한다.

저장한 파일을 톰캣이 웹문서들을 보관하고 있는 디렉토리인 Root 폴더에 넣는다.(webapps > ROOT)

(톰캣의 Root 디렉토리: 웹문서들을 보관하고 있는 홈이라고 생각하면 된다.)

원격에서 해당 문서를 톰캣에게 달라도 하려면 `http://localhost:8080/문서이름.txt` 를 하면 된다.

로컬이 아닌 외부 컴퓨터에서 접근하려면 `http://서버컴퓨터IP주소/문서이름.txt`를 하면 된다.



webapps > ROOT 디렉토리안에 파일을 보면 index.jsp 파일이 있다.

이 파일이 바로 서버의 첫 화면(localhost:8080에서 보이는 것)에 출력되는 문서다.

그런데 왜 localhost:8080/index.jsp가 아니라 그냥 localhost:8080인가?

일단 그 전에 localhost:8080과 index.jsp 사이에 붙는 /에 대해서 이해를 하자.

localhost:8080 바로 뒤에 오는 슬래시인 /는 바로 홈디렉토리 즉, ROOT 폴더를 의미한다.

다시 내용으로 돌아가서 왜 index.jsp 는 생략가능했는가.

localhost:8080/index.jsp를 입력해도 같은 내용이 나온다.

그럼에도 생략이 가능한 이유는 index.jsp를 기본문서로 설정했기 때문이다. 



---

06

Context 사이트 추가하기 



context란?

사이트를 만들다 보면 웹페이지(파일)수가 많아지고 이들을 분류하는 디렉토리의 숫자도 많아질 것이다.

보통 각각의 디렉토리를 서로 다른 팀 or 개발자가 맡아서 개발을 진행한다.

따라서 하나의 홈 폴더 아래 수 많은 개발자가 각자 자신의 디렉토리를 가지고 개발을 하는 것보다,

각자 자신이 개발하는 영역을 별도의 홈 폴더로 만드는 것이 좋다.

그리고 각자의 홈 폴더는 나중에 상위 홈 폴더 아래 하나의 디렉토리로 들어가는 방식으로 개발하게 된다.



물리적으로는 2개의 사이트이지만, 마치 하나의 사이트처럼 하나의 사이트가 다른 하나의 사이트 하위에 종속되어 돌아가게 한다.

상위 사이트 = Root 사이트

하위 사이트 = Context 사이트



| 요약                                                         |
| ------------------------------------------------------------ |
| 규모가 큰 경우 여러 context 사이트(디렉토리)를 만들어서 여러 팀이 개발을 수행한다. |

![image-20210709074054888]([Servlet&JSP] 기초(01_02).assets/image-20210709074054888.png)



ROOT 디렉토리 안에 IT 디렉토리가 있고 그 안에 news.txt가 있다.

이를 호출하려면 `localhost:8080/ROOT/IT/news.txt`로 접근해야 한다.

그런데 academy 디렉토리를 다른 부서에게 개발을 맡겨서 수행하고자 academy 폴더를 잘라내기해서 ROOT와 같은 레벨에 위치시킨다. 

(academy 디렉토리는 ROOT와 같은 레벨에 위치할 필요는 없다. 실제로는 완전 다른 경로에 둬도 상관없다.)

그런데 주소 체계에서는 현재 `localhost:8080/ROOT/academy/news.txt`  이렇게 나오게 하고 싶다.

▶ 톰캣 디렉토리에 `conf > server.xml` 에서 두 경로를 연결해서 사용할 수 있게 설정할 수 있다.

(우리가 설정하다가 잘못할수도 있으니까 복사본을 만들어서 원본을 저장하고나서 수정한다 )



server.xml 문서 내에 `<Host>` 태그 아래 `<context>`를 넣어준다.

![image-20210709080816351]([Servlet&JSP] 기초(01_02).assets/image-20210709080816351.png)

* path = context 사이트를 표현할 가상 디렉토리 이름(실제 디렉토리와 이름 같지 않아도 된다)

  ▶ 바로 이 path에 설정된 이름이 홈경로에서 호출되면 docBase에 있는 디렉토리를 가리키게 된다.

  ▶ `localhost:8080/academy/`를 하면 바로 docBase 경로가 열리게 된다. 

* docBase:  context 사이트의 웹페이지가 저장된 디렉토리의 전체 경로



```
< server.xml >

...
      <Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
		<context path="academy" docBase="C:\Users\ok\Downloads\apache-tomcat-9.0.50-windows-x64\apache-tomcat-9.0.50\webapps\academy" privileged="true"/>
        <!-- SingleSignOn valve, share authentication between web applications
             Documentation at: /docs/config/valve.html -->
        <!--
        <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
        -->

        <!-- Access log processes all example.
             Documentation at: /docs/config/valve.html
             Note: The pattern used is equivalent to using pattern="common" -->
        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log" suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />

      </Host>
```



이렇게 서버 설정을 바꾼 경우 서버를 재시작해야 한다. (톰캣 종료 후 재시작)

→ 오류가 나오면 수정하면서 스펠링이 틀렸거나 등의 문제가 있다는 것



★ 주의 ★

톰캣 버전이 올라가면서 이렇게 server.xml에서 직접 context를 추가하는 등의 수정작업을 지양해달라는 권고가 나왔다.

왜냐하면 이렇게 수정할 때마다 서버를 껐다 켜야 하기 때문에 회사입장에서는 위험하기 때문이다.

이를 대체하기 위해서 각 어플리케이션 마다 Metainfo라는 곳에다가 context를 마련할 수 있다. 

