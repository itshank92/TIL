56

Model 데이터를 구조화 하기



![image-20210808201344453]([Servlet&JSP] 기초(56-).assets/image-20210808201344453.png)

* 여러개의 데이터를 하나의 객체로 만들면 편하게 저장할 수 있을 것이다.

* 이를 위해 Notice라는 객체를 만들고 각 데이터를 속성값으로 설정한다.

  ```java
  public class Notice {
      private int id;
      private String title;
      private String writer;
      private Date regdata;
      private String content;
      private int hit;
      
      // getter와 setter도 작성 (생략)
      // ...
  }
  ```

    

* 이렇게 만든 객체의 속성값을 jsp 문서 내에서는 ${} 형태로 불러와서 사용한다.

  ```jsp
  <a> ${notice.title} </a>
  ```

  

1) 데이터를 모아 둘 클래스 정의

■ 자료형이 되는 클래스를 추가한다.

* `com.newlecture.web` 패키지에 `entity`라는 패키지를 만들고 `Notice`라는 클래스를 추가한다.

* 코드 작성

  ```java
  package com.newlecture.web.entity;
  
  import java.util.Date;
  
  public class Notice {	
      private int id;
      private String title;
      private String writer;
      private Date regdata;
      private String content;
      private int hit;
      
      public Notice() {
  		// TODO Auto-generated constructor stub
  	}
      
  	public Notice(int id, String title, String writer, Date regdata, String content, int hit) {
  		this.id = id;
  		this.title = title;
  		this.writer = writer;
  		this.regdata = regdata;
  		this.content = content;
  		this.hit = hit;
  	}
  
  	public int getId() {
  		return id;
  	}
  
  	public void setId(int id) {
  		this.id = id;
  	}
  
  	public String getTitle() {
  		return title;
  	}
  
  	public void setTitle(String title) {
  		this.title = title;
  	}
  
  	public String getWriter() {
  		return writer;
  	}
  
  	public void setWriter(String writer) {
  		this.writer = writer;
  	}
  
  	public Date getRegdata() {
  		return regdata;
  	}
  
  	public void setRegdata(Date regdata) {
  		this.regdata = regdata;
  	}
  
  	public String getContent() {
  		return content;
  	}
  
  	public void setContent(String content) {
  		this.content = content;
  	}
  
  	public int getHit() {
  		return hit;
  	}
  
  	public void setHit(int hit) {
  		this.hit = hit;
  	}
  
      @Override
      public String toString() {
          return "Notice [id=" + id + ", title=" + title + ", writer=" + writer + ", regdata=" + regdata + ", content="
              + content + ", hit=" + hit + "]";
  	}
      
  }
  ```

  * `toString()`이라는 메소드는 [ 마우스 오른쪽 ] \> [ toString 만들기 ] 를 통해서 만들 수 있다. 
    * 해당 메소드는 `Notice` 객체의 값들을 한 번에 편하게 확인할 수 있는 메소드로 요긴하게 사용할 수 있다. 



2) `Controller.java` 파일에서 데이터들을 가지고 `Notice`객체를 만들고 이를 `request`에 담는다.

```java
// 생략

Notice notice = new Notice(
    id,
    title,
    writerid,
    regdate,
    content,
    hit,
    files
);

// 생성한 Notice 객체를 reqeust에 "n"이라는 이름으로 담는다.
request.setAttribute("n", notice);
```

  

3) `detail.jsp` 파일에서 request를 통해 전달받은 객체를 EL구문을 통해서 편하게 출력한다.

* EL구문: jsp 문서 내에서 `${변수}` 형태로 출력할 수 있는 편리한 구문 

```jsp
<!-- 생략 -->

<a> ${n.title} </a>

<!-- 생략 -->
```

* EL 구문에서 n.title은 n객체의 title 속성값을 바로 읽어오는 것처럼 보이지만, 실제는 getTitle()라는 getter 메소드를 내부에서 자동으로 호출하고 그 반환값을 가져와 읽는 것이다.
* 이러한 이유로 Notice 클래스의 title 속성값은 private인데도 ${n.title}으로 읽어올 수 있는 것이다.
  * 눈에 보이진 않지만 `getter()`를 사용해서 속성 값을 가져오기 때문

