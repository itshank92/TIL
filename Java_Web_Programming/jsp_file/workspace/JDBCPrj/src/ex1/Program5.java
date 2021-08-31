package ex1;

import java.sql.SQLException;

import com.newlecture.app.console.NoticeConsole;

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		NoticeConsole console = new NoticeConsole();
		
		// 사용자가 종료 메뉴를 누르기 전까지는 계속 반복해서 내용을 보여줌
		// 반복문 앞에 라벨을 라벨: 형태로 붙일 수 있다.
		EXIT:while(true) {
			console.printNoticeList();
			
			// 사용자의 입력값을 입력받아 해당하는 업무를 수행(숫자로 입력받음)
			int menu = console.inputNoticeMenu();
			
			switch(menu) {
			case 1: // 상세조회
				
				break;
				
			case 2:  // 이전
				console.movePrevList();
				break;
				
			case 3:  // 다음
				console.moveNextList();
				break;
				
			case 4:  //글쓰기
				break;

			case 5:  //검색
				// 검색어를 입력받음
				console.inputSearchWord();
				break;				
				
			case 6:  //종료
				System.out.println("Bye~~~~~~~");
				// break 다음에 라벨명이 오면 해당 라벨 단계까지 모두 종료한다.
				break EXIT;
				
			default:
				System.out.println("메뉴는 1에서 4까지만 입력할 수 있습니다.");
				break;
			}
		}
	}

}
