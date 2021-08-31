12    

주제: Annotation을 이용한 URL 매핑

▶ class 파일과 URL을 매핑할 때, annotation을 사용하면 **쉽게 맵핑이 가능**하다.     

![image-20210713082127473]([Servlet&JSP] 기초(12-).assets/image-20210713082127473.png)

​      

> annotation이란

클래스나 메소드에 붙여지는 주석으로 컴파일러에 의해서 사라지지 않는 특성이 있다. 

객체가 실행될 때, 객체에 붙여진 주석 정보를 꺼내서 활용하기에 메타데이터라고 볼 수 있다.     

​     

**annotation으로 맵핑하기 위한 준비 셋팅**

* 서블릿 3.0이상이 되면서 annotation을 사용한 매핑이 가능해졌다.

* 단, 이를 사용하기 위해서는 `web.xml` 파일의 web-app 태그에 있는 `metadata-complete` 속성을 "false"로 바꿔줘야 한다.
  * `metadata-complete`: 맵핑 정보를 설정할 때, `web.xml`만을 사용해서 할 것지를 묻는 속성값

![image-20210713082154901]([Servlet&JSP] 기초(12-).assets/image-20210713082154901.png)

​     

`web.xml`을 수정했으면 기존의 `web.xml`에 존재하던 맵핑정보를 주석처리(`Ctrl` + `Shift `+ `/`) 하고 `Nana.class`에 annotation을 달아준다.

![image-20210713082938987]([Servlet&JSP] 기초(12-).assets/image-20210713082938987.png)

* `"/hello"`는 `루트경로 / hello`를 의미한다.



---

13 

주제: 서블릿은 어떻게 출력을 하고 클라이언트에게는 이것이 어떻게 전달되어 보이는지 

예제 작성 코드(Nana.class 수정)

```java
package com.newlecture.web;

// import는 생략

@WebServlet("/hello")
public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
        
		for(int i=0; i<100; i++) 
			out.println((i+1) + " Hello Servlet!!");
	}
}
```

​      

[크롬] 출력결과

![image-20210713091337839]([Servlet&JSP] 기초(12-).assets/image-20210713091337839.png)

​    



[내장 브라우저] 출력 결과1

![image-20210713085950584]([Servlet&JSP] 기초(12-).assets/image-20210713085950584.png)

* 결과는 이처럼 라인 변경 없이 출력된다. 

* 그 이유는 Servlet에서 클라이언트에게 전달하는 것은 Java 소스 파일이 아니라 그 결과물을 html 형식으로 전달하기 때문이다. (따라서 줄 바꿈 없이 통째로 텍스트 형식으로 전달된다.)

* 만약 내려쓰기를 하고 싶으면 html의 `<br>`태그를 명시적으로 작성해야 적용이 된다.

  ```java
  ...
  out.println((i+1) + " Hello Servlet!!<br>");
  ```

   

[내장 브라우저] 출력결과2

![image-20210713091250943]([Servlet&JSP] 기초(12-).assets/image-20210713091250943.png)



[크롬] 출력결과2

![image-20210713091442580]([Servlet&JSP] 기초(12-).assets/image-20210713091442580.png)

​    

**★ 결론 ★**

* 서버에서 클라이언트에게 전달하는 문서가 어떤 형식(양식)인지 명시적으로 알려줘야 브라우저가 올바른 형식으로 출력할 수 있다. 
* 브라우저에게 컨텐츠 형식을 알려주지 않는 경우 ▶ 자의적인 해석을 하게 된다. 

​     



다음시간 

- 보내는 문서의 형식을 클라이언트에게 알려주는 방법

- 보내는 문서에 한글을 써서 보내도 안깨지게 하는 방법