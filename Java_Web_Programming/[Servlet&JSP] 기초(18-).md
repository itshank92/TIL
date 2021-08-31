18

입력할 내용이 많은 경우 POST 요청을 수행한다.



> 실습    

[상황]

* 입력받은 내용의 공지사항을 화면에 출력해주는 기능을 만든다.

* 공지사항은 제목과 내용을 입력받아서 작성된다.      

​     

[ 공지 사항을 등록하는 html 페이지 작성 ]

1) hello.html을 복사해서 reg.html을 생성한다. 

2) reg.html 의 form 태그의 action을 "notice_reg"로 한다.

​    ★ form 태그는 연결할 Servlet을 action 속성 값에 명시한다. 

3) 제목을 받는 input 태그를 만든다.

* 한 줄만 작성할 수 있는 input 태그

4) 내용을 받는 textarea 태그를 만든다.

* 여러 줄을 작성할 수 있는 textarea 태그

5) 각 입력 폼(input, textarea)은 name이라는 속성 값을 사용해서 이름을 설정할 수 있다.      

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div>
       <!-- form 태그의 action에는 맵핑할 Servlet을 작성한다.-->
       <form action="notice-reg">
           <div>
               <label>제목:</label><input name="title" type="text">
           </div>
           <div>
               <label>내용:</label><textarea name="content"></textarea>
           </div>
           <div>
               <input type="submit" value="등록"/>
           </div>
       </form>
   </div>
</body>
</html>
```

5) 결과

![image-20210713140557098]([Servlet&JSP] 기초(18-).assets/image-20210713140557098.png)

​     



[ 공지사항 등록을 수행하는 Servlet 만들기 ]

1) `src > main > java > com.newlecture.web`에 NoticeReg.class라는 클래스 파일을 만든다.

▶ 기존의 Nana.class 파일을 복사해서 만든다.



2) (코드) 어노테이션을 통해 URL과 매핑한다. ▶  `@WebServlet("/notice-reg")`



3) (코드) 변수를 생성해서 입력값 두 개(title, content)를 받고 화면에 출력해준다.

```java
// NoticeReg.class

String title = req.getParameter("title");
String content = req.getParameter("content");

out.println(title);
out.println(content);
```



4) 결과

![image-20210713140846875]([Servlet&JSP] 기초(18-).assets/image-20210713140846875.png)

​     



**※ 문제 발생 ※**

입력한 내용인 URL의 Parameter로 들어가 있다. 

* 문제 1 ▶ URL은 길이 제한이 있기 때문에 입력값이 길어지는 경우 문제가 생길 수 있다. 

* 문제 2 ▶ 입력 내용에 URL에서 사용 불가능한 문자가 포함된 경우 문제가 생길 수 있다. 

* 문제 3 ▶ 쿼리 스트링은 받으려는 **문서의 옵션 값을 명시하는데 사용되는 것**이지 내용을 전달하기 위한게 아님



> form 태그의 default 설정

* `<form>` 태그는 아무런 설정을 하지 않는다면, 기본적으로 입력값을 쿼리값으로 해서 요청을 보내게 된다. 

  ▶ 이는 `<form>` 태그가 기본적으로(by default) GET 메소드로 설정되어 있기 때문이다.

  ▶ `<form>` == `<form method ="get">`     



```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div>
       <!-- method = "post"로 변경한다. -->
       <form action="notice-reg" method="post">
           <div>
               <label>제목:</label><input name="title" type="text">
           </div>
           <div>
               <label>내용:</label><textarea name="content"></textarea>
           </div>
           <div>
               <input type="submit" value="등록"/>
           </div>
       </form>
   </div>
</body>
</html>
```

​     

* 이렇게 쿼리로 입력값을 전달하는 방식을 변경하고 싶으면 form의 전달 방식을 GET에서 POST로 바꾸면 된다.

  ▶ `<form method ="post">`

  ▶ 이렇게 바꾸면 입력값을 URL에 붙여서 전달하지 않고 Response 객체의 body 부분에 포함되서 전달된다. 

  ▶ Response 객체 안에`title=입력값&&content=입력값` 형태로 포함되어 전달된다. 

​     

---

![image-20210713142503128]([Servlet&JSP] 기초(18-).assets/image-20210713142503128.png)

​                                                                                   ▼

![image-20210713142513792]([Servlet&JSP] 기초(18-).assets/image-20210713142513792.png)

또다른 문제가 발생했다.

입력 폼에 한글을 입력해서 전달하는 경우 인코딩이 안되서 문자가 깨진 형태로 화면에 출력되는 것이다.

이를 해결하기 위한 내용을 다음 시간에 학습한다. 