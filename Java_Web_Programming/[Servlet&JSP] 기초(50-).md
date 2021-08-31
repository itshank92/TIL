50

수업용 프로젝트 준비하기



[프로젝트 목표]

공지사항을 작성하면 공지사항 목록과 홈페이지에 공지사항 섹션에 해당 글 목록이 뜨도록 만들어 볼 것이다.





---

51

JSP를 이용한 자바 웹 프로그래밍 시작하기

1) JSP를 이용해서 Servlet 프로그램을 만드는 필요성

2) JSP를 이용하는 방법  

  



> #### 1) JSP를 이용해서 Servlet 프로그램을 만드는 필요성

* JSP를 이용해서 프로그래밍 한다는 것의 의미 = Servlet을 직접 손대지 않겠다는 것  





> #### 2) JSP를 이용하는 방법

* Servlet을 직접 작성하지 않고 코드를 입력하려면 적절한 코드 블록을 이용해야 한다.   

* `<% %>`    : 일반코드 작성 코드 블록
* `<%= %>`  : 출력을 위한 코드 블록
* `<%! %>`   : 멤버 변수나 멤버 메소드 작성을 위한 코드 블록 
* `<%@ %>` : (자바 코드는 아니고) 지시어(페이지 메타데이터 전달)를 위한 코드 블록  

  



> #### 이클립스에서 파일의 한글이 깨져서 보이는 현상 해결

파일 - 속성 - 아래쪽의 Text file encoding에서 방식을 UTF-8로 바꾼다.  





> #### JSP 파일의 한글을 브라우저에서 한글로 보이게 하는 방법

`<%@ %>`를 사용해서 페이지 메타데이터를 작성한다.

이 메타데이터 작성 방법이 기억나지 앟는 경우 JSP 파일을 하나 만들고, 해당 파일에 작성된 것을 복사해서 가져온다.

(참고_메타데이터)

```JSP
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
```

  

  



> #### `<table> ` 안의 `<tr>` 덩어리를 DB에서 가져와서 동적으로 생성할 수 있도록 코드를 작성해보자

* 기존 코드

  * 모든 row에 들어가는 데이터가 정적으로 이미 입력된 형태

  ```jsp
  				<table class="table">
  					<thead>
  						<tr>
  							<th class="w60">번호</th>
  							<th class="expand">제목</th>
  							<th class="w100">작성자</th>
  							<th class="w100">작성일</th>
  							<th class="w60">조회수</th>
  						</tr>
  					</thead>
  					<tbody>
  							
  					<tr>
  						<td>8</td>
  						<td class="title indent text-align-left"><a href="detail.html">스프링 8강까지의 예제 코드</a></td>
  						<td>newlec</td>
  						<td>
  							2019-08-18		
  						</td>
  						<td>146</td>
  					</tr>
  							
  					<tr>
  						<td>7</td>
  						<td class="title indent text-align-left"><a href="detail.html">스프링 DI 예제 코드</a></td>
  						<td>newlec</td>
  						<td>
  							2019-08-15		
  						</td>
  						<td>131</td>
  					</tr>
  							
  					<tr>
  						<td>6</td>
  						<td class="title indent text-align-left"><a href="detail.html">뉴렉쌤 9월 초 국기과정 모집 안내</a></td>
  						<td>newlec</td>
  						<td>
  							2019-06-11		
  						</td>
  						<td>517</td>
  					</tr>
  							
  					<tr>
  						<td>5</td>
  						<td class="title indent text-align-left"><a href="detail.html">뉴렉처 강의 수강 방식 안내</a></td>
  						<td>newlec</td>
  						<td>
  							2019-05-24		
  						</td>
  						<td>448</td>
  					</tr>
  							
  					<tr>
  						<td>4</td>
  						<td class="title indent text-align-left"><a href="detail.html">자바 구조적인 프로그래밍 강의 예제 파일</a></td>
  						<td>newlec</td>
  						<td>
  							2019-04-24		
  						</td>
  						<td>520</td>
  					</tr>
  					
  					
  					</tbody>
  				</table>
  
  ```

  * 위 데이터에서 하나의 `<tr>` 덩어리만 남기고 나머지 `<tr>`은 모두 지운다.  
  * 남은 하나의 `<tr>` 역시 반복문을 사용하여 내용을 동적으로 구성해서 생성할 것이다.  

  

* 변경한 코드

  * 일단 10번 반복해서 `<tr>`을 생성하는 코드부터 작성해 본다. 
  * 번호 칼럼에는 1부터 순차적으로 숫자가 들어가게끔 (`for`문의 `i`를 사용해서) 작성한다.

  ```jsp
  				<table class="table">
  					<thead>
  						<tr>
  							<th class="w60">번호</th>
  							<th class="expand">제목</th>
  							<th class="w100">작성자</th>
  							<th class="w100">작성일</th>
  							<th class="w60">조회수</th>
  						</tr>
  					</thead>
                      
  					<tbody>
  							
  					<% for(int i = 0; i <10; i++){%>
  					<tr>
  						<td> <%= i+1 %></td>
  						<td class="title indent text-align-left"><a href="detail.html">스프링 8강까지의 예제 코드</a></td>
  						<td>newlec</td>
  						<td>
  							2019-08-18		
  						</td>
  						<td>146</td>
  					</tr>
  					<%} %>
                          
  					</tbody>
  				</table>
  ```


  



