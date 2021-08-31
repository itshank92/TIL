# Thymeleaf를 이용해 레이아웃 만들기



> ## git으로 버전관리하기



**(1) git 설치**

git-scm.com에서 git을 설치해서 소스코드의 버전관리를 해준다.

(이전 코드로 돌아갈 수 있게됨)     



**(2) IntelliJ에서 해당 프로젝트에 git을 사용한다고설정한다.**

* IntelliJ에서 `VCS > Enable Version Control Integration > Git`선택 후 Ok 누른다. 
  ▶ git이 설치되어 있어야 git 선택 가능



* 이제는 VCS 메뉴 아래에 git이 생긴다. `VCS > Git > Commit File `
  ▶ 붉은색으로 표시된 파일: `.gitignore`에 명시되지 않은 파일 중에 버전관리가 되고 있지 않은 파일을 보여준다.
  (버전관리가 되고 있지 않은 파일: git에 올라온 내용과 현재 파일의 내용에 차이가 있는 파일)



* 최초로 commit을 하게되면 commit을 하는 사용자의 이름과 이메일 정보를 입력하는 창이 뜬다.
  ▶ 이렇게 이름과 이메일을 입력하면 다른 사람과 협업을 할 때, 누가 commit을 했는지 파악이 가능하다.     





**(3) 해당 프로젝트를 github와 연동하기**

* github에서 Repository 생성하기 (create repository)

* 만들어진 repository의 주소를 복사

* `IntelliJ > VCS > Git > Remotes > +버튼` 누르고 repository의 주소를 추가
* Commit이후에는 Push를 해줘야 서버에 반영이 된다.
  `VCS > Git > Push`     



**(기타) 설명**

* Commit은 로컬 단계에서 변경 내용을 정리한 것 → 서버로 발송을 위해 포장했다고 생각하면 됨

* Push는 Commit을 통해 포장된 변경 내용을 서버에 반영하는 것이다.

* 따라서 Push버튼을 누르면 해당 Repository의 접근 권한을 확인하기 위한 아이디, 비밀번호를 묻는 창이 뜬다.



| 기능   | IntelliJ 단축키        |
| ------ | ---------------------- |
| Commit | `Ctrl` + `k`           |
| Push   | `Ctrl` + `Shift` + `k` |

​      

​       

​       



> ## css 파일 작성하기

* html 문서에서 사용할 css 파일은 `src > main > resources > static` 디렉토리에 작성한다.

* 이 css 파일은 URL을 통해 접근 가능하다.

  (예시) `localhost:8080/new_css.css` ▶ 접근 가능

  ※ 단, template내의 파일 (html 파일) 의 경우 URL이 아닌 **오직 Cotroller로만 접근**할 수 있다.

  (예시) `localhost:8080/index.html` ▶ 접근 불가

​      

​       



> ## fragments 만들기



#### fragment란?

template을 작성할 때, 다른 template에서 작성한 footer, header, menu 등등을 가져와서 사용하고자 할 때가 있다. 

이를 수행하기 위해서 Thymeleaft는 이러한 부분들을 "fragments"라고 명명하고 원할 때 소환해서 사용한다.

fragments로 명명하기 위해서는 대상 태그의 속성값으로 `th:fragment="fragment이름"`을 정의하면 된다. 

예제를 수행하면서 fragment에 대해 좀 더 이해해보자.      

​      



#### ◆ 예제 ◆

상황: 모든 웹페이지에 동일한 메뉴바(NavBar)를 넣고자 한다.     



**(0) thymeleaf를 사용할 html 페이지는 반드시 html 태그를 다음과 같이 작성해야 한다.**

```html
<html xmlns:th="http://www.thymeleaf.org">
```

| 주의 | IntelliJ의 Community 버전은 Thymeleaf에 대한 코드 작성 assistance를 제공하지 않는다. <br />따라서 thymeleaf를 불러오는 코드의 경우 마치 잘못 작성한 것처럼 코드가 경고 색깔로 바뀌어 보일 수 있다.<br />하지만 사용하는데는 아무런 문제가 없으니 그냥 무시하고 실행시키면 된다. |
| ---- | ------------------------------------------------------------ |

​     

**(1) `resources > templates` 디렉토리 하단에 `fragments`라는 디렉토리를 만든다.**

* `fragments`는 fragment들을 모두 모아서 관리할 디렉토리다.

​       



**(2) 해당 디렉토리에 만들고자 하는 fragment를 작성할 html 파일을 만든다.**

* fragment 작성을 위한 html 파일이기에 html 태그에 `xmlns:th="http://www.thymeleaf.org"`을 꼭 넣어야 한다. 
* html 파일은 Thymeleaf를 사용할 수 있도록 html 태그에 `xmlns:th="http://www.thymeleaf.org"`가 있어야 한다. 

​        



**(3) 생성된 html 파일에 fragment를 작성한다.**

` th:fragment="fragment이름"` 형태로 작성한다.

```html
<!-- src > main > resources > templates > fragments > common.html -->

<!DOCTYPE html>


<!-- ★thymeleaf를 사용하기 위해서는 반드시 xmlns:th="http://www.thymeleaf.org"를 html 태그 부분에 선언해야 한다. -->
<html xmlns:th="http://www.thymeleaf.org" lang="en">
  <body>
    
    <!-- 아래에 있는 nav바를 menu라는 이름의 fragment로 선언했다. -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" th:fragment="menu">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Spring Boot</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                    	<a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                    	<a class="nav-link" href="#">Link</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
      
  </body>
  
</html>
```





**(4)  template에서 fragment를 사용하는 예제 (th:replace 사용 예제)**

**`th:replace`란?**

해당 html 페이지만 로딩하는 경우 태그 아래에 작성된 내용으로 브라우저에 출력되는 반면, 서버를 켜서 로딩하는 경우 `th:replace`에 명시된 fragment가 대신(replace) 출력된다. 

```html
<!-- src > main > resources > templates > index.html -->

...

	<!-- ■ th:replace를 통해서 fragments 디렉토리에 있는 common 파일내의 menu라는 fragment를 nav 태그로 replace한다.  -->
	<!-- replace할 fragment를 발견 못하는 경우, nav 아래에 쓰여진 html 코드가 대신 출력된다. -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" th:replace="fragments/common :: menu">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Spring Boot</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
```

​     

​    



> ## board/list의 주소를 처리할 BoardController를 만든다

​     

#### list.html의 css 파일 경로는 th:href를 통해 상대 경로로 입력한다. 

`link`태그에서 불러오는 css 파일의 경우 해당 html 페이지가 위치한 경로로부터 탐색을 수행해서 찾는다.

따라서 `board/list.html`의 경우 상위 `static` 폴더에 있는 css 파일을 읽으려면 `index.html`에 입력된 `link`태그의 경로로는 불가능하다.

이는 `th:href`를 통해 상대 경로를 입력해 줌으로서 해결 가능하다.        



**[결과 코드]**

```html
<link href="starter-template.css" th:href="@{/starter-template.css}" rel="stylesheet">
```

* 서버가 구동시, 해당 태그의 `href` 속성의 값으로 `"@{/starter-template.css}"`가 입력된다. 

* th 문법상 `@`는 경로를 나타내는 기호로 `@` 뒤에 오는 중괄호는 경로를 의미한다.

* `@` 뒤에 오는 중괄호안에서 최초의 `/`는 프로젝트의 모든 경로를 의미한다.

* 결론적으로 `"@{/starter-template.css}"`는 프로젝트에서 `starter-template.css` 파일이 위치한 경로를 의미하게 된다.



#### BoardController 코드 작성

위치: `src > main > java > com.godcoder.myhome > controller > BoardController.class`

```java
package com.godcoder.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("경로")를 통해서 해당 컨트롤러가 어떤 공통 경로에 대해서 작동할 지 설정
// 공통 경로 아래의 세부 경로의 경우 컨트롤러 내부의 @GetMapping()을 사용해서 설정
// >>>> 컨트롤러가 하나의 경로에 대해서만 작동하는 경우 @RequestMapping없이 @GetMapping만으로 수행가능
@RequestMapping("/board")
public class BoardController {

    // '.../board/list' 경로에 대한 처리
    @GetMapping("/list")
    public String list() {
        return "board/list";  // >>> templates디렉토리에 있는 board/list.html으로 연결됨
    }
}
```





> ### list.html의 `<head>`와 index.html의 `<head>` 부분이 비슷함 ▶ fragment로 빼주기



**(1) 공통된 부분 코드 복사**    



**(2) `common.html`에 해당 부분 붙여넣고 가장 상위의 태그에 `th:fragment="fragment이름"`으로 명명**   



**(3)css 파일을 불러오는 link 태그도 th:href 사용해서 상대 경로로 지정**

```html
<link href="starter-template.css" th:href="@{/starter-template.css}" rel="stylesheet">
```

​      

**(4) title태그의 경우 html 페이지마다 달라야 하기에 th 문법을 사용해서 작성**

▶ 타이틀 값을 fragment의 parameter로 받아서 해당 parameter를 fragment 내에서 사용해서 나타내는 방식

* head fragment에서 title로 사용할 문자열을 입력값으로 받는다.(title이라는 변수에 담는다.)
  
  ▶ fragment가 받는 입력값의 경우 **`fragment이름(입력값받을 변수)`**로 표현한다. 
  
  ```html
  <!-- common.html 파일 -->
  
  <head th:fragment="head(title)">
  ```
  * head라는 fragment는 매개변수를 받는데 받는 매개변수를 title이라는 이름의 변수에 저장한다. 



* title 태그에서는 head fragment에서 입력값을 받은 변수 title을 사용해서 text(태그의 내용물)을 대체한다.

  ```html
  <!-- common.html 파일 -->
  
  <title th:text="${title}">홈</title>
  ```
  
  * `th:text="${title}"`는 해당 태그의 값을 fragment의 title이라는 변수로 대체하는 것을 의미한다. 

​      

​     



> ### list.html의 head 부분을 fragment로 대체하기



**(1) list.html의 head부분을 common에서 선언한 head라는 fragment를 사용해서 대체한다.**

```html
  <head th:replace="fragments/common :: head('게시판')">
```

* head라는 fragment는 title로 사용할 입력값을 받기 때문에 반드시 입력값을 넣어줘야 한다.



**(2) index.html의 head 부분 역시 common에서 선언한 head라는 fragment를 사용해서 대체한다.**

```html
<head th:replace="fragments/common :: head('홈')">
```

​     

​    





> ### NavBar의 메뉴에 불 들어오게 하기



#### 🎈 classappend에 대해서

* thymeleaf 문법에 classappend라는 속성이 있다. ▶ `th:classappend`

* `th:classappend`는 크게 두 파트로 구성된다. ( <u>조건문</u> 과 <u>결과값</u> )

* 조건문이 True일 때만 뒤에 있는 결과값이 class에 추가되는 것이다. 

```html
<tr th:each="prod : ${prods}" class="row" th:classappend="${prodStat.odd}? 'odd'">
```

* `"${prodStat.odd}?"`라는 조건문이 **True**일 때만  `"row"`라는 클래스에 `'odd'`가 추가된다.	



#### `classappend`를 사용해서 NavBar의 메뉴 코드 수정

일단 각 페이지별로 해당하는 메뉴 항목이 다르기에, 각 페이지별로 어떤 메뉴 항목에 해당하는지를 알려 줄 값이 필요하다.

현재 NavBar는 menu라는 이름의 fragment로 사용되고 있기에 각 페이지마다 "menu"를 호출할 때, 해당 페이지의 정보를 넘겨주는 방식으로 변경한다. 
▶ 즉, menu라는 fragment가 parameter를 받도록 코드를 수정한다.



common.html 파일에서는 

th:fragment="menu(menu)"라고 수정한다.
▶ menu라는 변수명으로 입력값을 받는다. 



index.html 파일에서는 

th:replace = "fragments/common :: menu('home')"으로 수정

list.html 파일에서는

th:replace = "fragments/common :: menu('board')"로 수정



이제 classappend를 사용해서 조건문을 작성하자.(common.html에서 수행됨)

일단 조건문 양식을 가져오자. 

각 메뉴를 표현하는 li 태그에 예제코드인 th:classappend="${prodStat.odd}? 'odd'"를 추가한다. 

이제 조건문에 해당하는 부분을 수정한다.

`${prodStat.odd}?`이 부분을 `${menu} == home ?`으로 수정한다.

▶ `${menu} == home ?`코드의 의미는 `if menu == home:`와 같다. (`${}`는 변수를 표현하기 위한 양식)

결과값에 해당하는 부분도 수정한다. 

`'odd'`이 부분을 `'active'`로 수정한다.

| 결과 코드 | `<li class="nav-item" th:classappend="${menu} == 'home'? 'active'">` |
| --------- | ------------------------------------------------------------ |

게시판 메뉴 항목 역시 위의 방식으로 수정한다. 
(조건문의 검증값만 `'home'`이 아닌 `'board'`로 작성하면 된다.))





추가적으로 bootstrap에서 제공하는 sr_only라는 클래스 역시 th조건문을 통해서 사용해보자.

| ※ 주의 ※ | 부트스트랩 4버전에서 제공되던 `sr-only` 클래스는 5버전에서 `visually-hidden`으로 바뀌었다. |
| -------- | ------------------------------------------------------------ |

sr_only(screen reader)를 이해하기 위해서는 일단 screen reader를 이해해야 한다. 

from: https://rgy0409.tistory.com/3079

| screen reader | 검색엔진에서 검색어에 해당하는 웹페이지를 탐색할 때, 웹페이지의 내용을 읽는 프로그램을 지칭한다. screen reader는 웹페이지의 내용을 읽으면서 검색어와의 일치성을 검증하는 역할을 수행한다. |
| ------------- | ------------------------------------------------------------ |

screen reader를 이해했으면 이제 웹 접근성이라는 개념에 대해서 읽어보자.

| 웹 접근성 | 웹 접근성이란, 어떤 웹페이지가 잠재적 이용자들에게 얼마나 접근하기 편한지를 의미한다. 예를 들면 서울의 맛집을 소개한 웹페이지가 있을 때, '서울 맛집'이라는 검색어로 해당 웹페이지를 금방 찾을 수 있다면 접근성이 좋은 것이고 그렇지 않다면 접근성이 안좋은 것이다. |
| --------- | ------------------------------------------------------------ |

웹페이지를 만드는 많은 사람들은 웹 접근성에 대해 많은 고민을 한다. 따라서 웹 접근성을 높이기 위해 제목과 내용이 전달하고자 하는 키워드를 포함시키거나 이것도 부족해서 태그를 따로 추가하기도 한다. 



하지만 해당 웹페이지와 관련되어 있으나, 제목과 내용에 포함시키기 어렵거나 포함시키기는 싫은 키워드인 경우  어떡할까? (그냥 안넣을 수는 없는게 사용자는 보통 웹접근성을 높이고 싶어하기 때문이다)

예전에는 해당 내용의 색을 투명하게 하는 방식으로 처리했다. 이 방식을 통해 본문에는 포함되어 있어서 screen reader는 읽을 수 있지만 방문자에게는 보이지 않는 것이 가능했다. 

(cf_ `display:None`의 경우 screen reader에게도 보이지 않는다.)

하지만 디스플레이가 고도로 발달하여 이러한 방식이 먹히지 않는 상황이 발생했다. 



이러한 상황에서 등장한 것이 바로 sr_only 클래스다. 

이 클래스가 추가된 태그는 방문자에게는 보이지 않고 오직 screen reader에게만 보이게 된다.

해당 태그는 아예 없는 것처럼 취급되어 페이지 상 공간조차 차지하지 않게 된다.

 

sr_only 예시

```html
<body>
  <h1>Hi, HTML and CSS!</hi>
  <h1 class="sr-only">Nice to meet you.</hi>
</body>
```



출력 결과

# Hi, HTML and CSS!

* sr_only가 적용된  ` <h1 class="sr-only">Nice to meet you.</hi>`의 경우 숨김 처리되었다. 





각 메뉴에 (current)라는 내용을 sr_only라는 조건을 달아서 작성한다.

th:if를 사용해서 오직 해당 메뉴가 활성화(active) 상태일 때만 (current)가 screen reader에게만 보이도록 작성한다.

```html
<a class="nav-link" aria-current="page" href="#"> 홈 <span class="sr_only" th:if="${menu} == 'home'">(current)</span> </a>
```

* 위 코드에서 <span> 태그 부분이 새롭게 작성한 부분이다.

* th:if ="조건문"은 해당 조건문이 True일 때만 해당 태그를 유효하게 하는 기능이 있다. 

  ▶ 따라서 ${menu}가 'home'일 때만 span 태그가 작동한다 == (current)라는 내용이 screen reader에게 보이게 된다.



#### 최종 코드

(부트스트랩 5버전에서는 sr-only 대신 visually-hidden이 사용되기에 이 부분만 고치면 된다)

```html
<a class="nav-link" aria-current="page" href="#"> 홈 <span class="visually-hidden" th:if="${menu} == 'home'">(current)</span> </a>
```







> ### NavBar 메뉴에 template 페이지 연결하기



메뉴의 a태그에 있는 href를 th:href를 사용해서 해당하는 url과 연결해준다.

홈 메뉴인 경우 기본 주소와 동일한 url이기에 그냥 @{/}으로 한다.

게시판 메뉴인 경우 @{/board/list} 로 설정한다.