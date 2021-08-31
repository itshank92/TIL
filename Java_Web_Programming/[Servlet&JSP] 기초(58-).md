58

View 페이지(jsp 페이지)를 은닉하기



MVC로 나눈 상태에서 View 페이지에 대한 접근은 Controller에서 관장한다.

이 말은 곧 사용자가 View 페이지에 대한 직접적인 접근(주소를 통한 접근)이 불가하다는 것을 의미한다.

▶ NoticeListController에서 list.jsp에 대한 접근을 포워드로 설정했다면, `localhost:8080/notice/list` 라는 주소로 list.jsp에 접근하려하면 에러가 나타난다.

▶ 즉 MVC 모델에서 V에 대한 접근은 오로지 C를 통해서만 가능한 것이다. 





따라서 View 파일을 아예 사용자가 요청할 수 없는 곳에 두는 방식으로 관리하는 것이 바람직하다.

* jsp 파일이 외부 사용자가 주소를 통해 접근 가능한 디렉토리에 있다면, MVC 패턴에서는 해당 주소로의 접근을 에러로 처리하고, 이는 사용자에게 에러 메세지 창을 보여주는 것을 뜻한다.
* 이를 피하기 위해, 아예 jsp파일을 외부 사용자가 접근할 수 없는 곳에 보관하는 방식을 취한다.  

  

  

비공개 공간인 WEB-INF라는 폴더를 사용하자(WEB-INF는 외부에 공개하지 않는 파일들을 넣는 용도의 폴더)

[ 현재 우리가 만드는 JSPPrj에서 페이지들을 담아두는 폴더들 ]

* admin, member, notice, student
* 위 폴더들을 View 파일들을 담고 있다.
* WEB-INF에  view라는 폴더를 만들고 위 4개의 폴더를 여기에 옮긴다.

  

  

이렇게 view 파일들을 WEB-INF/view안의 폴더에 옮겨 놓았다면, 

Controller에서 View로 연결을 할 때, 바뀐 위치를 적어줘야 한다.

[ NoticeListController를 예로 들면 ]

```java
request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request,response)
```

* `list.jsp`의 새로운 위치인 `/WEB-INF/view/notice/list.jsp` 로 경로를 설정해서 포워딩을 해준다. 





결과

* 이렇게 외부에 비공개인 디렉토리(WEB-INF) 안에 View 파일들을 보관하는 경우, 직접 view파일로 접근하는 요청은 404 에러 메세지가 뜨게 된다.

  

---

59

View(list.jsp)에 남아있는 자바 코드 블록 제거하기 (반복문 제거하기)

▶ jsp에서 자바 코드를 대신할 수 있는 것이 필요하다!

  

[ 현재 list.jsp에 남아있는 코드 상황 ]

글 목록을 보여주기 위한 반복 코드인 for문이 코드 블록으로 남아 있다. 

```jsp
<%
// Object를 받으려면 형변환해서 받는다.
List<Notice> list = (List<Notice>)request.getAttribute("list");

for(Notice n : list){
    pageContext.setAttribute("n",n);
%>

<tr>
    <td>${n.id}</td>
    <td class="title indent text-align-left"><a href="detail?id=${n.id}">${n.title}</a></td>
    <td>${n.writerid}</td>
    <td>${n.regdate}</td>
    <td>${n.hit}</td>
</tr>
<% } %>
```

​    

  

> #### 태그를 사용해서 코드 블록을 대체하기

  

태그란?

일반적으로 html 코드에서 태그는 해당 **태그의 내용**이 무엇인지를 설명하는 의미를 가진다.

즉, a 태그는 링크를, table 태그는 테이블을 의미한다.

하지만 여기서 사용할 태그는 실제 어떤 기능을 수행하고, 해당 **태그의 기능**이 무엇인지 설명하는 의미를 가진다.



jsp에서 태그를 사용하려면 외부 라이브러리인 JSTL을 다운로드 받아야 한다.

https://mvnrepository.com/artifact/javax.servlet/jstl/1.2 

* 위 링크에서 jar 양식의 파일을 다운 받는다.

  

다운 받은 JSTL jar 파일을 WEB-INF에 있는 lib 폴더 안에 넣는다. 

* 이 때, 이클립스를 사용해서 넣는다. 

  

  

jsp 파일에서 태그를 사용하기 위해 작성해야 하는 코드

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
```

* `prefix`로 설정한 값은 태그를 사용할 때, 어떤 태그들이 있는지 목록을 보기 위해 입력하는 값이다. 
  * 예를 들어 위와같이 `prefix` 값이 "c"인 경우, `<c:`을 입력하고 `Ctrl` + `Space` 를 누르면 사용할 수 있는 태그 목록이 나오게 된다.
* `uri`의 경우, 사용할 태그 종류를 가지고 오는 것인데, 직접 저 주소를 작성하는 것은 아니고 자동완성을 이용한다.
  * `uri=""` 를 입력하고 쌍따옴표 안에 커서를 두고 `Ctrl` + `Space` 를 누르면 여러 주소가 나온다.
  * 그 중, `jsp/jstl/core` 가 들어간 링크를 선택해서 입력한다.
  * (주의)  `jsp`가 없이 그냥 `jstl/core` 만 들어간 링크가 있는데, 그것을 선택하면 안된다. 

  

  

반복문을 사용했던 코드 블럭 위치로 가서 반복문 코드 블록을 지우고 태그를 작성한다.

```jsp
<c:forEach var="n" items="${list}">
    <tr>
        <td>${n.id}</td>
        <td class="title indent text-align-left"><a href="detail?id=${n.id}">${n.title}</a></td>
        <td>${n.writerid}</td>
        <td>${n.regdate}</td>
        <td>${n.hit}</td>
    </tr>
</c:forEach>		
```

* `<c:forEach>` 태그를 사용해서 반복문을 수행한다.
  * `items` 에는 반복 대상이 되는 요소를 가리킨다. 
    * `items="${list}"`  == `<% for(Notice n : list) %>`
  * `var` 에는 반복 대상이 되는 요소를 하나씩 꺼낸 값을 담는 것이다. 
    * `var="n"` == `<% pageContext.setAttribute("n", n) %>`