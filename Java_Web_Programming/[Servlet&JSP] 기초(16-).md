16
쿼리로 받을 입력값에 대해서 기본값 설정하기 (사용자가 값을 입력하지 않는 경우를 위해서)



쿼리 스트링으로 입력값을 받는 여러 케이스

![image-20210713121219426]([Servlet&JSP] 기초(16-).assets/image-20210713121219426.png)



위의 모든 경우를 커버하기 위해서 다음과 같이 코드를 수정한다.

1. 변수 cnt의 기본값을 설정한다. 

   ▶ 아무것도 입력하지 않는 경우 기본값으로 설정된만큼만 반복된다. 

2. 임시 변수(temp)를 사용해서 키 cnt로 입력받은 값을 받아온다.

3. 임시변수가 null이 아니고 임시변수가 빈 문자열이 아닌 경우, 임시변수 값을 정수로 형변환해서 cnt에 할당한다. 

```java
// 임시변수
String temp = request.getParameter("cnt");

// 기본값 설정
int cnt = 100;

// null, 빈문자열 검증
if(temp != null && !temp.equals(""));
	cnt = Integer.parseInt(temp);

// 반복 출력
for(int i = 0; i<cnt; i++)
    out.println((i+1) + "안녕하세요!");

```



> 기본 링크와 cnt=3이 입력된 링크로 이동할 수 있는 버튼 만들기

index.html 에 `<a>`태그를 사용해서 기본 링크(hello)와 cnt=3이 입력된 링크(hello?cnt=3)로 이동할 수 있는 버튼을 만든다.

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">    <!-- 인코딩 방식 UTF-8로 지정해주기 -->
<title>Insert title here</title>
</head>
<body>
	환영합니다.
	<a href="hello"> 인사하기 </a><br>
	<a href="hello?cnt=3"> 인사하기 </a><br>

</body>
</html>
```

​        

> 기본 인코딩 방식 변경해주기(html, css, jsp 파일)

* 이클립스 > Window > Preference > Web > html, css, Jsp에서 인코딩 방식 변경하기     





---

17

사용자 입력을 통한 GET 요청

* Form을 통해서 사용자의 입력값 받기

![image-20210713124815421]([Servlet&JSP] 기초(16-).assets/image-20210713124815421.png)

​    

> html 문서에서 form 만들기

* 일단 ROOT 경로에 hello.html 문서를 추가한다 .

```html
<!-- hello.html -->

<body>
    <div>
        <!-- form 태그의 action에는 맵핑할 Servlet을 작성한다.-->
        <!-- submit 버튼을 누르게 되면 hello라는 Servlet에 요청이 간다. -->
        <form action="hello">
            <div>
                <label> "안녕하세요를 몇 번 듣고 싶으세요?"</label>
            </div>
            <div>
                <input type="text" name="cnt"/>
                <input type="submit" value="출력"/>
            </div>
        </form>
    </div>
</body>
```

* 브라우저는 form 태그의 action명을 보고 URL을 작성한다.

  `<form action="hello">` ▶ `http://.../hello`     



* 이 때 브라우저는 입력된 값을 확인해보고(input태그의 값), 입력된 값이 있으면 쿼리 스트링을 만들어준다.
  `<input type="text" name="cnt"/>` ▶  (예시) `http://.../hello?cnt=3`     



* 최종 URL을 서버에 요청한다.       

![image-20210713130349451]([Servlet&JSP] 기초(16-).assets/image-20210713130349451.png)



