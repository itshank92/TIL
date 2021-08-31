61

중간 정리 요약

![image-20210811163545665]([Servlet&JSP] 기초(61-).assets/image-20210811163545665.png)

* 웹 프로그램: 웹으로 입력과 출력을 하는 것
  (콘솔 프로그램의 경우 콘솔로 입력과 출력을 하는 것)

  

* 출력하는 부분에서 불편함을 없애주는 JSP를 사용한다. 

  

* JSP를 오랫동안 사용하다가 스파게티 코드의 문제점을 해결하기 위해 MVC Model이 등장하였다. 

  

* View 영역에서 코드를 사용하기 위해 쓰는 대표적인 2가지 라이브러리
  * EL: 출력용
  * JSTL: 제어용  

  

  

---

62

forEach의 속성 사용하기

  



`forEach`의 속성 중 이전 시간에 배운 속성을 제외하고 새로운 속성에 대해서 설명

(이전 시간에 배운 속성: `var` , `items` )

1) `begin`

반복문의 시작 위치를 설정 (시작 인덱스는 0)

2) `end`

반복문의 끝 위치를 설정

3) `varStatus`

반복되는 요소의 속성값을 사용하기 위해 반복요소를 부를 명칭을 설정하는 것

`varStatus = "st"` 의 경우 반복되는 요소를 st라고 부를 수 있고 해당 요소에 대한 속성을 사용할 수 있다. 

  

[ 속성들 ]

`.index` = 반복되는 요소의 등장 순서(0부터 시작)

`.count` =  반복되는 횟수를 나타냄(1부터 시작)

`.first` = 현재 반복되는 요소가 첫번째 아이템인 경우 True, 아니면 False

`.last` = 현재 반복되는 요소가 마지막 아이템인 경우 True, 아니면 False

`.step` = 현재 반복되는 요소가 1회 반복시마다 증가하는 정도

  

[ 실제 사용 코드 ]

```jsp
<c:forEach var = "n" items = "${lsit}" varStatus="st">

    <span> ${st.index} </span>
    
</c:forEach>
```

  

---

63

forEach문으로 Pager 만들기



Pager란?

게시글 목록 아래에 보통 위치하여 페이지 이동 기능을 수행하는 요소



**[ 원래 상태 ]**

```jsp
<!--  list.jsp  -->

<ul class="-list- center">
    <li><a class="-text- orange bold" href="?p=1&t=&q=" >1</a></li>
</ul>
```



**[ forEach 태그 삽입 ]**

```jsp
<ul class="-list- center">
    
    <c:forEach var="i" begin="0" end="4">	
        
        <li><a class="-text- orange bold" href="?p=1&t=&q=" >${1+i}</a></li>
        
    </c:forEach>
    
</ul>
```

  

`begin`과 `end`를 사용해서 원하는 횟수만큼 반복을 한다.

* 여기서는 5번 반복을 위해서 5만큼의 간격으로 `begin`과 `end`를 설정한다.

  

반복문이 수행되면서 증가하는 값을 사용해서 `<li>` 태그의 값(페이지 번호를 나타냄)을 나타내기 위해서 반복문에 변수(`var`)를 선언한다.

* `forEach` 태그의 `var` 속성값은 반복문이 수행될 때 각각 반복 회차의 요소를 가리키게 된다.
* 리스트를 반복하는 경우, `var`은 리스트의 각 요소를 가리키게된다.
* 특정 구간의 횟수를 반복하는 경우, `var`은 매 회차의 번호를 가리키게 된다.
  * 예를 들면, `begin=10` ,`end=13` 인 경우 `var`은 각 회차마다 `10`, `11`, `12`, `13`이 된다.  

  



**[ a태그의  href 변경 ]**

```jsp
<li><a class="-text- orange bold" href="?p=${1+i}&t=&q=" >${1+i}</a></li>
```

각 페이지 번호는 a태그 href의 파라미터 p의 값으로 들어가게 된다.

* 따라서 href의 파라미터 p의 값을 페이지 번호와 연동되도록 `${i+1}`로 바꿔준다.

  

**[ 페이지 번호 동적 생성 ]**

```jsp

```

페이지 번호는 1부터 5까지만이 아니라, 그 이상의 번호도 나올 수 있어야 한다.

* 파라미터 `p`의 값을 통해 요구하는 페이지의 번호를 기준으로 출력되는 페이지 번호 목록을 정한다.
* `?p=17` 인 경우 `16 17 18 19 20` 이 나와야 한다.
* `?p=19` 인 경우도 `16 17 18 19 20` 이 나와야 한다. 

  

나머지 연산자를 사용해서 페이지 번호 목록을 만든다.

* 페이지 번호 목록은 목록이 시작하는 페이지 번호(`startNum`)을 얻어서 그 것부터 5페이지로 설정한다.
* 현재 페이지(`page`)에 대해서 `page - (page-1) % 5`를 통해서 시작 페이지 번호(`startNum`)을 얻을 수 있다.
  * 현재 페이지가 3인 경우, 시작 페이지는 `3-(3-1)%5` 의 값인 1이 된다. 
  * 현재 페이지가 17인 경우, 시작 페이지는 `17-(17-1)%5`의 값인 16이 된다.

  

  

**[ JSTL의 set 태그를 사용해서 (startNum으로 사용할) 변수 생성하기 ]**

```jsp
<c:set var="startNum" value="${page-(page-1)%5}"/>
```

* EL구문 안에서는 연산이 가능하다. ▶ `${page-(page-1)%5}`

* `page` 라는 변수는 현재 list 화면의 페이지 번호를 의미하는데 이 역시 변수가 없기에 만들어준다.

  ```jsp
  <c set var="page" value="${param.p}"/>
  ```

  * 마찬가지로 set 태그를 사용해서 page라는 변수를 만들어줬다.

  * 이 때, 값은 url 파라미터를 의미하는 param의 p태그의 값인 param.p로 설정했다.

  * 근데 첫 화면의 경우(page가 1인 경우) url을 보면 p태그가 안붙어 있다. 

    * 주소:`localhost:8080/notice/list`

  * 따라서 이렇게 설정한 값이 비어 있는 경우도 Pager가 작동하도록 삼항연산자를 써서 경우의 수를 처리한다.

    ```jsp
    <c:set var="page" value="${(param.p == null)?1:param.p}"/>
    ```

    * param.p가 null이면 1을, 아니면 param.p를 page값으로 설정한다.

  

**[ 만든 startNum을 사용해서 Pager의 출력부분 바꿔주기 ]**

```jsp
<li><a class="-text- orange bold" href="?p=${startNum+i}&t=&q=" >${startNum+i}</a></li>
```

  

  

**[ 전체 코드 ]**

```jsp
<!-- list.jsp -->

<c:set var="page" value="${(param.p == null)?1:param.p}"/>
<c:set var="startNum" value="${page-(page-1)%5}"/>

<ul class="-list- center">
    <c:forEach var="i" begin="0" end="4">		
        <li><a class="-text- orange bold" href="?p=${startNum+i}&t=&q=" >${startNum+i}</a></li>
    </c:forEach>

</ul>
```



