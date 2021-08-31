66

format 태그 그룹의 태그를 사용하여 출력되는 날짜의 형식을 변경하기



format 태그 그룹에는 여러 세부 종류의 태그가 존재한다.

formatDate라는 태그를 사용해서 날짜의 출력 형식을 바꿔보자.





0) JSTL은 크게 5 종류의 태그 그룹이 있고 이제까지 우리가 사용하느 태그 그룹은 Core다. 

1) format 태그 그룹을 사용하기 위해서는 core 태그 그룹을 사용했을 때와 마찬가지로 코드 블록을 사용해서 태그를 사용함을 선언해줘야 한다.

```jsp
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
```



2) list.jsp 파일에서 날짜를 출력하는 부분을 찾는다.

```jsp
<td>${n.regdate}</td>
```



3) 해당 부분을 format 태그 그룹의 formatDate 태그를 사용해서 수정한다.

```jsp
<td> <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value=" ${n.regdate}"/> </td>
```

* value에는 출력하고자 하는 값을 입력하면 된다.
* pattern에는 출력하고자 하는 형식을 지정하면 된다. 
  * 년도는 y로 표현(한자리는 y, 두자리는 yy, 네자리는 yyyy로 표현)
  * 월은 M으로 표현 (두자리는 MM)
    * 월은 분과 동일한 알파벳을 사용하기에 대문자로 표현한다.
  * 일은 d로 표현
  * 시, 분, 초는 각각 hh, mm, ss로 표현한다.



**[ 에러 발생시 확인 사항 ]**

1. Detail Controller와 List Controller 클래스에서 java.util.Date가 아닌 java.sql.Date를 import한 실수가 있는 경우를 확인. 
2. rs.getTimestamp 메소드가 아닌 rs.getDate메소드를 사용하여 regdate 를 rs에서 불러온 실수가 있는 경우를 확인.
3. Oracle에서 regdate컬럼의 데이터타입이 timestamp이고 기본값이 systimestamp이며, 입력데이터들의 값이 제대로 연월일시분초밀리초 까지 입력이 되어있는가 확인.



4) detail.jsp에서도 날짜 출력 형식을 바꿔준다.

```jsp
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 생략 -->

<th>작성일</th>
<td class="text-align-left text-indent" colspan="3">
    <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${n.regdate}"/>
</td>
```



