19

한글 입력 문제 해결하기

​    



> 문제 원인 분석하기

입력값의 여행 경로는 다음과 같다.

* [사용자의 입력] → [서버로 전달] → [서버에서 처리] → [다시 사용자에게 전달 후 출력]



위 경로에서 **브라우저에서 출력하는 단계**의 한글 출력 문제는 이전에 해결했기 때문에 해당 영역의 문제는 아니다.

* Servlet 코드에서 response 객체에 인코딩 방식을 명시했기 때문에 제대로 출력된다. 

```java
// Servlet 파일

resp.setCharacterEncoding("UTF-8");
resp.setContentType("text/html; charset=UTF-8");
```

​       

​     

> UTF-8에 대해서

* UTF-8은 2바이트를 사용해서 하나의 문자를 표현한다.

* 톰캣은 기본적으로 1바이트를 하나의 문자로 인식한다.

* 따라서 UTF-8로 입력받은 문자는 톰캣이 읽을 때 깨진 형태로 읽게 된다.

* 이를 해결하기 위해서 입력값을 서버에서 읽을 때, UTF-8로 읽겠다고 설정을 먼저 하고 나서 읽어야 한다.

![image-20210713144635288]([Servlet&JSP] 기초(19-).assets/image-20210713144635288.png)



* 이렇게 Servlet에 인코딩 방식을 설정하는 코드를 매번 작성하는 것이 귀찮은 경우, 톰캣의 환경 설정을 위한 server.xml 파일에 다음과 같이 인코딩 방식을 작성하면 된다.

![image-20210713144622822]([Servlet&JSP] 기초(19-).assets/image-20210713144622822.png)

* 하지만 보통 톰캣을 사용할 때, 여러 개의 서버를 돌리는 경우가 많기 때문에 톰캣 서버의 설정을 일괄적으로 변경하는 위의 방식은 자주 사용되지는 않는다.

​    

> Servlet 파일에 Request의 입력값을 읽는 방식을 설정하기

* Request 객체의 setCharacterEncoding 메소드를 사용해서 인코딩 방식을 명시한다.

```java
//... 

@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet {
    
    // 생략
    
		req.setCharacterEncoding("UTF-8");
		
    // 생략

	}
}
```

* 결과

![image-20210713145210365]([Servlet&JSP] 기초(19-).assets/image-20210713145210365.png)

​      

---

20

서블릿 필터(Servlet Filter)에 대해서



우리가 지금까지 배운 구조

![image-20210713151032435]([Servlet&JSP] 기초(19-).assets/image-20210713151032435.png)

* 우리는 WAS로 톰캣을 사용하고 있다.

* 사용자로부터 요청이 톰캣(WAS)으로 들어오면 적절한 Servlet을 실행시켜서 그 결과값을 돌려준다.

* 해당 소프트웨어를 Servlet이라고 부른다.        



> Filter

우리는 지금까지 Servlet이라고 불리는 객체만 만들었지만 사실 Filter라는 객체도 만들 수 있다.

요청이  Servlet에 도달하기 전에 Filter가 가로채서 작업 + 판단을 하는 역할

* 작업: 요청 내역에 대해서 원하는 작업, 처리를 할 수 있다.
* 판단: 요청 내역을 Servlet으로 보낼지 말지를 결정할 수 있다.

* 마치 수문장 역할을 수행
* 모든 Servlet이 가지고 있는 설정을 Filter에서 한 번만 설정하면 된다.

Filter는 요청이 Servlet으로 들어가기 전에 실행되기도 하지만 Servlet에서 나오는 응답 역시 거쳐가는 곳이다.      



> Servlet 필터 예제 수행

우리는 이전까지 입력값에서 한글을 읽어오고 출력값으로 한글을 표현하기 위해서 Servlet 코드 상에서 다음과 같은 코드를 사용했다. 

```java
resp.setCharacterEncoding("UTF-8");
resp.setContentType("text/html; charset=UTF-8");
req.setCharacterEncoding("UTF-8");
```

하지만 이제는 이렇게 개별 Servlet에 계속 같은 코드를 반복해서 작성하지 않고 Filter를 생성해서 한 번에 모든 Servlet에 적용되는 코드를 작성해볼 것이다.

​    

1) `src > main > java`에 있는 `com.newlecture.web` 패키지에 `filter`라는 패키지를 새로 만든다.

* filter 객체는 독립된 패키지에서 관리하기에 이렇게 새롭게 패키지를 만들어 주는 것이다.
* `com.newlecture.web.filter`     



2) `filter` 패키지 아래 `CharacterEncodingFilter`라는 클래스를 만든다.     

* 이 때, `Interface` 탭에서 `Add`를 눌러서 `Filter`를 추가한다.
* 현재 우리가 사용하려는 `Filter`는 `Servlet` 필터이기 때문에 `javax.servlet`에 들어있는 `Filter` 인터페이스를 불러온다.



3) 이렇게 클래스를 생성하면 해당 클래스는 자동으로 `Filter`를 implements 한 채로 생성된다.

* `Filter`가 가지고 있는 추상메소드도 자동으로 Override된 상태로 생성된다.
* 일단 필터가 언제 작동하는지 확인해보기 위해서 `filter`가 작동할 때마다 "hello filter"가 출력되도록 코드를 작성한다.

```java
package com.newlecture.web.filter;

// import 생략

public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request
                         , ServletResponse response
                         , FilterChain chain)
						 throws IOException, ServletException {
            
            System.out.println("hello filter");
	}

}
```

* 위 필터는 콘솔에 "hello filter"만 출력하지 요청을 Servlet으로 보내는 일은 수행하지 않는다. 

  ▶ 필터로 유입된 `Request` 객체를 Servlet으로 보내는 것은 `filter` 객체의 매개변수인 `FilterChain` 객체가 수행한다. → 아래에서 배운다.

​      

​     

> 필터는 언제 작동하는가

톰캣이 가장 처음에 가동되면 필터가 작동한다.

그리고 나서 요청이 발생할 때마다 2번씩(요청받을 때, 응답 보낼때) 작동한다.

​        



> 필터는 어떻게 설정하는가

* 필터는 어떤 URL 요청에 대해서 어떤 Filter가 작동할 지 설정을 통해 맵핑을 해줘야 한다.
  * 이 때, URL은 패턴을 통해 등록할 수 있고, 모든 URL과 맵핑되는 필터는 *(asterisk)를 사용해서 맵핑한다.     

* 필터의 설정 방식은 2가지가 있다. (Servlet 설정 방법과 동일)

  1. **`web.xml`에서 설정하는 방법**

  * `<filter>` 태그:  URL과 연결하려는 filter 클래스를 지정한다.
  * `<filter-mapping>` 태그: filter와 연결하려는 url 패턴을 지정한다.

  ```
  ## web.xml
  
  (생략)
  <filter>
  	<filter-name> characterEncodingFilter </filter-name>
  	<filter-class> com.newlecture.web.filter.CharacterEncodingFilter </filter-class>
  </filter>
  
  
  <filter-mapping>
  	<filter-name> characterEncodingFilter </filter-name>
  	<url-pattern> * </url-pattern>
  </filter-mapping>
  ```

  * `filter-name`: filter와 URL을 연결하기 위해 filter에 붙이는 명칭     

  

  * `filter-class`: URL과 연결하려는 filter 클래스의 이름

     ▶ 해당 클래스의 패키지명까지 모두 작성해야 한다.     

  

  * `url-pattern`: filter 클래스로 연결되는 URL 주소 패턴들

    ▶ 모든 URL을 연결하는 filter의 경우 `<url-pattern>`값을 `*`로 설정한다.        

  

  

  2. **`annotation`을 통해 filter를 설정하는 방법**

  * filter 클래스 파일로 가서 class 이름 위에 annotation을 사용해서 해당 filter와 매칭되는 URL을 명시한다.
  * `@WebFilter( "맵핑되는 주소" )`
  * 모든 URL을 맵핑하려면 → `@WebFilter( "/*" )`
  * `web.xml`을 통해 맵핑하는 것보다 훨씬 더 간단하다.( 어노테이션 사용 권장 ) 

​     

> 필터에서 요청을 Servlet으로 보내는 방법

filter 클래스의 매개변수를 보면 FilterChain 객체를 받는 것을 알 수 있다. 

FilterChain은 filter에 들어온 요청을 다음으로(=Servlet으로) 넘겨줄지 말지를 결정하는 역할을 수행한다.



**[ FilterChain 객체의 doFilter 메소드 ]**    

`<FilterChain 객체 이름>.doFilter( <Request객체이름>, <Response객체이름> );`

* 요청을 Servlet으로 넘기는 메소드
* 해당 코드 이전 코드는 Request에 대한 Filter처리이고, 해당 코드 이후의 코드는 Response에 대한 Filter 처리 코드다.

```java
package com.newlecture.web.filter;

// import 생략

public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
        // 필터체인 객체가 Servlet으로 Request 보내기 전
		System.out.println("before filter");
        
        // Servlet으로 보내기전 요청 내용을 인코딩해서 보낸다.
        request.setCharacterEncoding("UTF-8");
        
        // 필터체인 객체가 Servlet으로 Request 보냄
		chain.doFilter(request, response);
        
        // Response가 filter로 들어옴
		System.out.println("after filter");
	}

}

```

​    

> 필터에 Request만 인코딩하는 코드를 넣는 이유? (Response 인코딩은 안넣는 이유?)

선생님 답변

▶ Response는 응답이라서  doFilter 이후에 넣어야 하는데요. Response 인코딩 코드를 필터에  추가하면 모든 응답에 콘텐츠 타입이 text/html로 고정됩니다.  그것이 css 나 image 일지라도 말입니다.