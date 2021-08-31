## Thymeleaf 기본 익히기



설명: 자바 기반으로 동작하는 Template 엔진



th문법을 사용해서 컨트롤러에 들어있는 데이터를 html 문서 내에서 불러와서 사용할 수 있다. 

th문법 블로그: https://eblo.tistory.com/55



- Simple expressions:

  - Variable Expressions: `${...}`  
    ▶ 컨트롤러로부터 입력받은 변수를 사용할 때는 `$` 기호 사용

  - Selection Variable Expressions: `*{...}`
    ▶ 객체의 속성값을 불러올 때는 `*` 기호 사용

  - Message Expressions: `#{...}`

    ▶ 메세지를 나타낼 때는 `#` 기호 사용 (태그로 쌓인 안의 내용물(텍스트)를 바꿀 때 사용)

  - Link URL Expressions: `@{...}`

    ▶ 링크를 나타낼 때는 `@` 기호 사용

  - Fragment Expressions: `~{...}`





```html
...
	<!-- allProducts라는 배열의 요소들 prod라는 변수로 하나씩 호출 -->
	<!-- tr이 allProducts의 갯수만큼 순회를 하면서 아래 작업을 수행한다 -->
	<tr th:each="prod: ${allProducts}">
      <td th:text="${prod.name}">Oranges</td>
      <td th:text="${#numbers.formatDecimal(prod.price, 1, 2)}">0.99</td>
    </tr>
```



```html
  <head>
    <title>Good Thymes Virtual Grocery</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" media="all" 
          href="../../css/gtvg.css" th:href="@{/css/gtvg.css}" />
  </head>
```

* 해당 html 페이지만 띄울 때는 `href`에 설정된 css 파일을 적용시키고, 서버를 띄울 때는 `th:href`에 명시된 css 파일을 적용한다.

  ▶`@`(골뱅이표시)는 Thymeleaf에서 **<u>링크</u>**를 나타내는 키워드다.





