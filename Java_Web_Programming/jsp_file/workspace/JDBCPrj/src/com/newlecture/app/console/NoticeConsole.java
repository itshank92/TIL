package com.newlecture.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;





public class NoticeConsole {

	// 데이터 처리(CRUD)를 위함
	private NoticeService service;
	
	private int page;
	
	// 게시글 수 변수
	private int count;

	private String searchWord;

	private String searchField;
	
	public NoticeConsole() {
		service = new NoticeService();
		page = 1;
		count = 0;
		searchField ="TITLE";//생성자에 추가(초기값 빈문자열)
		searchWord =""; //생성자에 추가(초기값 빈문자열)
	}
	
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = service.getList(page,searchField,searchWord);
		
		// count 가지고 오기
		int count = service.getCount();
		int lastPage = count/10;
		lastPage = count%10>0?lastPage+1:lastPage;
		
		System.out.println("──────────────────────────────────────────────────");
		// count 추가
		System.out.printf("<공지사항> 총%d 게시글\n", count);
		System.out.println("──────────────────────────────────────────────────");
		// 반복구간
		for(Notice n: list) {
			int id = n.getId();
			String title = n.getTitle();
			String writeid = n.getWriterid();
			String regdate = String.valueOf(n.getRegDate());
			
			System.out.printf("%d. %s / %s / %s \n", id, title,writeid,regdate);
		}
		System.out.println("──────────────────────────────────────────────────");
		System.out.printf("         %d/%d page\n", page,lastPage);
	}

	public int inputNoticeMenu() {
		Scanner scan = new Scanner(System.in); 
		
		System.out.printf("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색 /6.종료 >");
		
		String menu_ = scan.nextLine();
		
		int menu = Integer.parseInt(menu_);
		
		
		return menu;
	}

	public void movePrevList() {
		if(page==1) {
			System.out.println("==================================");
			System.out.println("이전 페이지가 없습니다.");
			System.out.println("==================================");
		} 
		else {
			page--;
		}
		
		
	}

	public void moveNextList() throws ClassNotFoundException, SQLException {
		int count = service.getCount();
		int lastPage = count/10;
		lastPage = count%10>1?lastPage+1:lastPage;
		if(page==lastPage){
			System.out.println("==================================");
			System.out.println("다음 페이지가 없습니다.");
			System.out.println("==================================");
			
		}
		else {
			page++;
		}
	}

	public void inputSearchWord() {
		// 스캐너 생성
		Scanner scan = new Scanner(System.in);
		
		System.out.println("검색 범주(title/content/writerId) 중 하나를 입력하세요");
		System.out.print(" >");
		// 어떤 필드(제목, 작성자, 내용 등등)로 검색할 것인지 입력받는다.
		searchField = scan.nextLine();
		
		System.out.print("검색어 >");
		// 검색어를 입력받는다.
		searchWord = scan.nextLine();
		
	}

}
