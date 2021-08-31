47

**JSP를 MVC로 만들 때 아주 유용하게 쓸 수 있는 EL이라는 표기 언어**

▶ **<u>Expression Langauge</u>**



MVC는 기본적으로 Controller에서 데이터를 만드록 `request`에 담는다.

View에 `request`를 보내는데, 이 때 `request`에 담긴 데이터를 읽기 위해서 View에서 java코드를 사용해야 한다.
(코드 블록 & 자바 코드 필요)

이렇게 HTML문서인 View에서 java코드를 사용하지 않고 간단하게 값을 꺼내서 출력하는 언어가 EL이다.



> #### EL 사용 예시_1

* `request`에 담긴 "cnt"라는 값을 View 파일에서 출력하는 기존의 방법

  ```jsp
  <%= request.getAttribute("cnt") %>
  ```

* 간단히 View 파일에서 "cnt"를 출력하는 방법

  * `$`기호를 쓰고 중괄호`{}` 로 변수를 묶어주면 된다. 

  ```jsp
  ${cnt}
  ```

​     

  

> #### EL 사용 예시_2

* `request`에 담긴 리스트 변수인 "list"의 첫번째 값을 View 파일에서 출력하는 기존의 방법

  * `getAttribute()`는 무조건 `Object`형으로 반환하기에 강제 형변환을 해야 한다.
  * 그리고 나서 `get(0)`을 사용해서 첫번째 값을 뽑을 수 있다.

  ```jsp
  <%= ((List)request.getAttribute("list")).get(0) %>
  ```

    

* 위의 과정을 EL을 사용하는 경우 아래와 같다.

  * 리스트 객체인데 마치 배열을 사용하는 것처럼 `[ <index> ]`를 사용해서 뽑을 수 있다.

  ```jsp
  ${list[0]}
  ```

   

    

> #### EL 사용 예시_3

* 해쉬 맵을 만들어서 jsp에서 출력하는 기존의 java 코드

  ```jsp
  ((MAP)request.getAttribute("n")).get("title")
  ```

  

* EL을 사용해서 출력하는 코드 

  ```JSP
  ${n.title}
  ```

    

* (cf) java에서 해쉬맵 만드는 코드

  ```java
  MAP<String, Object> notice = new HashMap<String, Object>;
  
  notice.put("id", 1);
  notice.put("title", "EL은 좋아요");
  ```

  

---



48 

EL의 데이터 저장소 (EL은 어디에 데이터를 저장해서 꺼내오는 걸까?)  

  

> #### 데이터를 저장하는 공간(4)

* Servlet에서 값을 저장하는 공간은 4가지가 존재한다. 

  * page, request, session, application (순서대로 지역 >>> 전역)

* 그리고 Servlet에서 특정한 데이터를 꺼내올 때는 다음과 같은 우선순위에 따라서 순차적으로 데이터 공간을 탐색한다.

  ![image-20210723080457991]([Servlet&JSP] 기초(47-).assets/image-20210723080457991.png)

  * 확인하다가 해당 데이터를 발견하면 바로 그 데이터를 불러오고 탐색을 종료한다. 

  

> #### 데이터 탐색 우선순위 무시하기

* `request`에 있는 "cnt"라는 데이터를 사용하고 싶은데 `page`에도 "cnt"가 있을 때, `${cnt}`를 하면 `page`에 있는 "cnt"가 출력된다.  



* 이처럼 데이터 탐색 우선순위는 때때로 원하는 데이터를 얻는데 방해가 되는데, 이러한 문제를 해결하기 위해 EL에서는 데이터 탐색 범위를 직접 지정해 줄 수 있다.   



* `원하는 저장 공간 + Scope`라는 명칭을 붙여서 데이터를 호출 하면 해당 저장공간에 있는 데이터를 가져오게 된다.

  ```jsp
  ${sessionScope.cnt}
  ```

  



> #### 데이터 저장 공간 간단 설명

**[ page ]**

* jsp내에서 jsp가 Servlet으로 자동적으로 변환할 때 만들어지는 내장 객체 중 하나

* page는 크게 두 가지 기능이 있고 두 번째 기능이 저장공간으로서의 page 기능이다.

* page의 첫 번째 기능

  * jsp에서 사용되는 객체들( `request`, `response`, `session`, `application` 등등)을 모아놓은 객체

    ```jsp
    <!-- .jsp페이지 -->
    
    <!-- 생략 -->
    
    <%
    pageContext.getRequest();
    %>
    ```

    * `getRequest()` 메소드를 통해서 `request` 객체를 불러올 수 있다.



* page의 두 번째 기능

  * 해당 페이지 내에서 사용할 데이터를 담아놓는 공간

    ```jsp
    <!-- .jsp페이지 -->
    
    <!-- 생략 -->
    
    <head>
    <%
    pageContext.setAttribute("aa", "hello");
    %>
    </head>
    ```

  * 이렇게 담아놓은 데이터는 해당 페이지에서 바로 사용 가능하다.

    ```jsp
    <!-- .jsp페이지 -->
    
    <!-- 생략 -->
    <head>
    <%
    pageContext.setAttribute("aa", "hello");
    %>
    </head>
    
    <!-- 새로 작성한 부분 -->
    <body>
        <%
        ${aa}
        %>
    </body>
    ```

    * 위 페이지를 출력해보면 hello가 나온다.(aa에 해당하는 데이터)

  

**[ request ]**

* 두 개의 Servlet이 (forwarding을 통해서) 어떤 데이터를 공유하고 싶을 때 사용하는 저장소 

  

  

> #### 기타 데이터 저장소와 사용방법

![image-20210723084950384]([Servlet&JSP] 기초(47-).assets/image-20210723084950384.png)

* 4대 저장소를 제외해도 다양한 데이터 저장 공간이 존재한다.  



* 위 방식을 사용해서 각 데이터 공간의 데이터를 읽을 수 있다.  



* parameter의 경우 URL에서 바로 얻을 수 도 있지만, 위의 방식처럼 `param.key값` 방식으로도 가지고 올 수 있다.     



* pageContext의 경우 특이하게 메소드를 사용해서 원하는 객체와 메소드를 사용할 수 있는데, EL문 안에서는 메소드를 사용할 수 없기 때문에 다음과 같이 표현한다.

  ![image-20210723085558050]([Servlet&JSP] 기초(47-).assets/image-20210723085558050.png)



---

49

EL 연산자



EL이라는 구문 안에서 다음의 연산자들을 사용할 수 있다 .

![image-20210723093051301]([Servlet&JSP] 기초(47-).assets/image-20210723093051301.png)

* `lt`, `gt`, `le`, `ge`와 `<`, `>`, `<=`, `>=` 는 동일한 기능을 하는 비교 연산자다.

  * 굳이 2개의 표현 방식을 만드는 이유는 HTML의 특성 때문(밑에 정리)  

    

* 모든 연산자는 EL 구문 `${}` 안에서만 사용 가능하다.    



* **[☆많이 사용되는 연산자☆]  `empty` 연산자**

  * 어떤 데이터의 값이 전달되지 않은 상태로 jsp에 오는 경우 해당 값은 `""` or `null`로 오게 된다.  
    

  * 이 두 가지 경우(`""` or `null`)에 해당하는 경우 `true`를 반환하는 연산자가 바로 `empty`다.  
    

  * 즉, 우리는 `empty`를 사용함으로서 두 번의 확인 과정을 단축할 수 있게 되는 것이다.

    ```jsp
    <!-- 두 번의 비교 연산 -->
    ${param.n == null || param.n == ''}
    
    <!-- empty 한 번만 사용 -->
    ${empty param.n}
    ```

    

  * 빈 문자열(`""`) 도 아니고 `null`도 아닌 경우는 `empty` 앞에 `not`을 붙여줌으로서 `true`로 출력 가능하다.

    ```jsp
    ${not empty param.n}
    ```

      

  * empty의 결과를 조건으로 사용하는 코드

    ```jsp
    ${empty param.n? '값이 비어 있습니다.':param.n}
    ```

    * `param.n`이 `null` 혹은 `""`인 경우 `true`가 나와서 *'값이 비어있습니다'* 가 출력된다.
    * `empty param.n`이 `false`인 경우 `param.n` 값이 출력된다.



> #### 두 가지의 비교 연산자 표현 방식이 존재하는 이유(ex_ `>`와 `lt` )

* HTLM의 태그를 작성할 때 꺾은 표시(`<` or `>`) 가 사용된다.  
  

* 따라서 HTML안에서 표현되는 EL 구문 역시, 그 안에서 꺾은 표시를 비교 연산자로 사용하는 것은 바람직한 표현 방식이라고 볼 수 없다.   
  

* 따라서 엄격한 HTML 구문의 경우 꺾은 표시의 비교연산자를 허용하지 않는다.  

  

  



> #### EL 구문 사용 예

![image-20210723093235327]([Servlet&JSP] 기초(47-).assets/image-20210723093235327.png)