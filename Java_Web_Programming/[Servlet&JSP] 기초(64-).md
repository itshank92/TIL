64

Pager의 이전, 다음 버튼을 JSTL의 if 태그로 구현하기



1) a 태그를 이용해서 다음 페이지로 넘어갈 수 있는 버튼을 만든다.

```jsp
<a class="btn btn-next" href="?p=${startNum+5}&t=&q=">다음</a>
```

* a 태그의 href 주소:
  * 현재 페이지가 속한 페이지 목록 다음에 오는 목록의 첫번째 페이지가 되어야 한다. 
  * 현재 페이지 목록 다음에 오는 목록의 첫번째 페이지 번호 = ${startNum+5}



2) span 태그를 이용해서 클릭하면 다음 페이지가 없다는 안내창이 뜨는 버튼을 만든다.

```jsp
<span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
```



3) a 태그의 다음 버튼과 span 태그의 다음 버튼은 서로 배타적으로 화면에 등장해야 하기 때문에, 조건문을 사용해서 두 버튼의 등장을 제어한다.

* 이를 위해 JSTL의 if 태그를 사용한다.

  

​    

4) if 태그를 사용해서 작동 코드를 감싼다.  

```jsp
<c:if test="${startNum+5<=lastNum}">			
    <a class="btn btn-next" href="?p=${startNum+5}&t=&q=">다음</a>
</c:if>
```

JSTL에는 if 태그만 있고 else 태그는 없다. ▶ 따라서 여러 조건식의 경우 여러 if 태그를 사용해서 작성해야 한다.

if 태그의 조건식은 test라는 속성값에 지정해준다.

* 이 때 조건식에 연산자가 있는 경우 EL문을 사용해서 작성한다. 



5) 조건식에서 사용하기 위한 마지막 페이지 데이터를 변수로 만든다.

* 일단 임시로 lastNum이라는 변수에 상수를 대입해서 만든다.

  ```jsp
  <c:set var="lastNum" value="23"/>
  ```

* 나중에 Controller 부분에서 코드를 작성해서 제대로 된 데이터를 넣어줄 것이다.



6) 다음 버튼의 if 태그에 조건문을 작성한다.

```jsp
<c:if test="${startNum+5<=lastNum}">			
	<a class="btn btn-next" href="?p=${startNum+5}&t=&q=">다음</a>
</c:if>

<c:if test="${startNum+5>lastNum}">			
	<span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
</c:if>
```

* 다음에 오는 페이지 목록의 첫 번호(startNum+5)와 마지막 페이지 번호(lastNum)을 비교한다.





7) 이전 페이지 버튼도 다음 페이지 버튼과 마찬가지로 수행한다.

```jsp
<c:if test="${startNum>1}">		
    <a class="btn btn-prev" href="p=${startNum-1}&t=&q=">이전</a>
</c:if>

<c:if test="${startNum<=1}">		
    <span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
</c:if>
```

  

  

---

65

forTokens 태그를 사용하여 첨부파일 목록을 출력하는 기능을 구현한다.

(detail 페이지에서 해당 글에 첨부된 파일들을 목록 형태로 볼 수 있게 구현한다. ▷ 하나하나가 하이퍼 링크로 나오도록)



글 내용을 출력하는 `detail.jsp`에서 첨부파일을 출력하는 곳에서 forToken 태그를 사용해서 목록을 출력하자

※ 그 전에 detail.jsp에서 JSTL을 사용할 수 있도록 문서 상단에서 코드 블록을 사용하여 JSTL을 선언해줘야 한다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
```



forToken을 사용해서 첨부파일을 출력해보자.

```jsp
<c:forTokens var="fileName" items="${n.files}" delims=",">
    	${fileName}
</c:forTokens>
```

* items에는 출력 대상 변수를 넣어준다. ▷ 여기서는 ${n.files} 를 넣어준다.
* delims는 delimiter의 약어로 구분자를 의미하는데 출력 대상 변수를 delims를 기준으로 구분해서 보여준다. 

* var에는 items에서 하나씩 구분해서 가져오는 대상을 부를 명칭을 정한다.
  * 해당 명칭을 사용해서 각 대상 혹은 대상의 속성을 출력할 수 있다.



위와 같이 출력하는 경우, ${fileName}은 그냥 문자열이기 때문에 그냥 문자열 형태로 화면에 출력된다.

따라서 a태그를 사용해서 파일명을 링크로 만든다.

```jsp
<td colspan="3" style="text-align:left; text-indent:10px;">
    <c:forTokens var="fileName" items="${n.files}" delims=",">
        <a href="${fileName}">${fileName}</a> ,
    </c:forTokens>
</td>
```

* a태그의 href 링크주소는 따로 설정한 것은 없지만 임시로 ${fileName}으로 설정했다.
* a태그의 다음에 ,를 작성하여 화면에 출력되는 파일명들이 ,로 나뉘어 보이게 한다.
* style을 통해서 왼쪽 정렬 & 시작 공백 10px을 설정했다. 



위와 같이 작성하는 경우 아래와 같이 출력된다.

![image-20210813162050654]([Servlet&JSP] 기초(64-).assets/image-20210813162050654.png)

위에서 보면 알겠지만 마지막 파일이름 뒤에도 ,가 붙게 된다.

이를 방지하기 위해서 마지막 파일인 경우 ,를 작성하지 않도록 varStatus 속성값과 if 태그를 사용한다.

```jsp
<c:forTokens var="fileName" items="${n.files}" delims="," varStatus="st">
    <a href="${fileName}">${fileName}</a>
    <c:if test="${not st.last}">
        ,
    </c:if>
</c:forTokens>
```

* forToken 태그에 varStatus를 설정하여 순회하는 각 요소의 상태값을 읽어 올 수 있는 변수명을 만든다. 
* 이렇게 만든 변수명에 상태값 중, 마지막 요소인지 아닌지를 판별하는 상태값인 .last를 사용한다.
  * .last는 마지막 값이면 True를, 아니면 False를 반환하는 속성값이다. 
* JSTL의 if 태그를 사용하여 마지막 값을 판별한다.

