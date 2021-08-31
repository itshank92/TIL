## Thymeleaf를 이용한 화면 작성





> ### thymeleaf를 사용할 html 파일 만들기

(1) `src > main > resources > templates` 디렉토리 안에 html 파일을 생성 가능     

​     



> ### Controller 만들기



`main` 메소드가 위치한 파일과 같은 층에 `controller`라는 패키지를 새로 생성

`controller` 패키지 내에 `컨트롤러.class` 생성

기본적으로 컨트롤러는 `@controller`로 어노테이션 생성



#### Controller 문법

(1) `@GetMapping("/greeting")`

`기본경로/greeting` 이라는 URL에 GET 요청보내는 것



(2) `@RequestParam`

URL뒤에 parameter를 붙임



(3) `return "greeting"`

컨트롤러가 문자열을 return 하면 해당 문자열과 동일한 이름의 html 파일 (`src > main > resources > templates` 안에 위치) 이 호출된다. 
▶ 위 경우 `greeting.html`이라는 파일이 호출된다.        

​      

Controller 코드

```java
package com.example.mythymeleaf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controller 어노테이션 필수
@Controller
public class GreetingController {

    // 기본경로/greeting이라는 URL에 GET요청을 보낸다.
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        
        // model에 "name"이라는 key와 그에 해당하는 value인 변수 name에 설정된 값을 넣어준다.
        // 이렇게 넣은 데이터는 아래 return으로 연결되는 html 문서에서 사용할 수 있다.
        model.addAttribute("name", name);

        // greeting.html이라는 문서로 연결된다.
        return "greeting";
    }
}
```

​     



> ### html 코드 작성 (Visual Studio Code)



* html 코드 작성은 VS Code를 사용하자

* IntelliJ는 아직 html 코드 자동완성을 제대로 지원하지 않는다.

  ▶ 따라서 html 코드의 경우 VS Code를 사용해서 작성하는 게 좋다.

* VS Code에서 `src > main > resources` 디렉토리를 열어서 해당 부분 아래는  VS Code로 작성한다. 

* 실시간으로 html 문서 변경 사항을 볼 수 있는 VS Code의 Extension 설치 ▶ **LiveServer**      



```html
<!DOCTYPE HTML>
<!-- html 태그 안에 thymeleaf를 사용하기 위해서 명시를 해준다. -->
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Getting Started: Serving Web Content</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <!-- p태그 안에 ${name}을 사용해서 Controller로부터 전달받은 데이터(key:value형태로 전달받음)를 사용했다. -->
    <!-- 더 자세한 thymeleaf 문법의 경우 검색해서 알아본다  -->
    <p th:text="'Hello, ' + ${name} + '!'" />
</body>
</html>
```









> Run server

URL뒤에 `?name=유현욱`을 입력하면 화면에 thymeleaf의 ${name}자리에 유현욱이 들어간 형태로 출력된다.

![image-20210707094127025](C:\Users\212565\AppData\Roaming\Typora\typora-user-images\image-20210707094127025.png)

