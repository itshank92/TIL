15

사용자 인터페이스를 구축해서 입력값을 쿼리문에 사용할 수 있게 만들기



#### 1) 사용자의 인터페이스 역할을 하는 자바 파일 만들기

패키지: `com.newlecture.app.console `

* 콘솔창에 UI를 구축할 것이기 때문에 패키지 명에 console이 포함된다.

자바 파일: `NoticeConsole.java`



#### 2) 콘솔을 실행시킬 자바 파일 만들기

자바 파일: `Program5.java`



**※** 실제 코드 구현은 콘솔을 실행시킬 파일( `Program5.java` )를 작성하고, 작성된 내용에서 사용된 함수들을 `NoticeConsole.java` 에다가 세부적으로 작성하는 형식으로 만든다. ▶ **TOP-DOWN 방식**

  



> #### UI콘솔을 실행시키는 Program5.java 작성

```JAVA
package ex1;

import java.sql.SQLException;
import com.newlecture.app.console.NoticeConsole;



public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        // 콘솔 생성
		NoticeConsole console = new NoticeConsole();
		
        // 콘솔 내용 프린트
		console.printNoticeList();
		
		// 사용자의 입력값을 입력받아 해당하는 업무를 수행(숫자로 입력받음)
		int menu = console.inputNoticeMenu();
		
        // 입력받은 menu에 대한 작업 내용을 작성할 공간
		switch(menu) {
		case 1: // 상세조회
			
			break;
			
		case 2:  // 이전
			break;
			
		case 3:  // 다음
			break;
			
		case 4:  //글쓰기
			break;
		default:
			break;
		}
	}
}
```

​    

  



> #### UI 콘솔 기능을 수행하는 NoticeConsole.java 작성

```java
package com.newlecture.app.console;

import java.sql.SQLException;
import java.util.List;
import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;


public class NoticeConsole {

	// 데이터 처리(CRUD)를 위한 NoticeService 객체 담을 변수(service) 선언
	private NoticeService service;
	
    // NoticeConsole 생성자(NoticeService객체 생성해서 service 변수에 담음)
	public NoticeConsole() {
		service = new NoticeService();
	}
    
    // 콘솔 내용을 출력할 함수
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		
        // 생성자에서 만든 NoticeSerive 객체를 사용해서 데이터를 가져온다.
        // 데이터는 List<Notice> 형태로 구성되어 있음
		List<Notice> list = service.getList();
		
        // 제목 구간 (일회성)
		System.out.println("──────────────────────────────────────────────────");
		System.out.printf("<공지사항> 총%d 게시글\n", 12);
		System.out.println("──────────────────────────────────────────────────");

        // 글 목록 구간 (반복) 
		for(Notice n: list) {
			int id = n.getId();
			String title = n.getTitle();
			String writeid = n.getWriterid();
			String regdate = String.valueOf(n.getRegDate());
			
			System.out.printf("%d. %s / %s / %s \n", id, title,writeid,regdate);
		}
        
        // 메뉴 구간(일회성)
		System.out.println("──────────────────────────────────────────────────");
		System.out.printf("         %d/%d page\n", 1,2);
	}

    // 사용자의 입력을 받아 해당하는 기능을 수행하기 위한 메소드(아직 미작성) 
	public int inputNoticeMenu() {
		// TODO Auto-generated method stub
		return 0;
	}
}
```





