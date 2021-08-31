package com.newlecture.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;





public class NoticeConsole {

	// ������ ó��(CRUD)�� ����
	private NoticeService service;
	
	private int page;
	
	// �Խñ� �� ����
	private int count;

	private String searchWord;

	private String searchField;
	
	public NoticeConsole() {
		service = new NoticeService();
		page = 1;
		count = 0;
		searchField ="TITLE";//�����ڿ� �߰�(�ʱⰪ ���ڿ�)
		searchWord =""; //�����ڿ� �߰�(�ʱⰪ ���ڿ�)
	}
	
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = service.getList(page,searchField,searchWord);
		
		// count ������ ����
		int count = service.getCount();
		int lastPage = count/10;
		lastPage = count%10>0?lastPage+1:lastPage;
		
		System.out.println("����������������������������������������������������������������������������������������������������");
		// count �߰�
		System.out.printf("<��������> ��%d �Խñ�\n", count);
		System.out.println("����������������������������������������������������������������������������������������������������");
		// �ݺ�����
		for(Notice n: list) {
			int id = n.getId();
			String title = n.getTitle();
			String writeid = n.getWriterid();
			String regdate = String.valueOf(n.getRegDate());
			
			System.out.printf("%d. %s / %s / %s \n", id, title,writeid,regdate);
		}
		System.out.println("����������������������������������������������������������������������������������������������������");
		System.out.printf("         %d/%d page\n", page,lastPage);
	}

	public int inputNoticeMenu() {
		Scanner scan = new Scanner(System.in); 
		
		System.out.printf("1.����ȸ/ 2.����/ 3.����/ 4.�۾���/ 5.�˻� /6.���� >");
		
		String menu_ = scan.nextLine();
		
		int menu = Integer.parseInt(menu_);
		
		
		return menu;
	}

	public void movePrevList() {
		if(page==1) {
			System.out.println("==================================");
			System.out.println("���� �������� �����ϴ�.");
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
			System.out.println("���� �������� �����ϴ�.");
			System.out.println("==================================");
			
		}
		else {
			page++;
		}
	}

	public void inputSearchWord() {
		// ��ĳ�� ����
		Scanner scan = new Scanner(System.in);
		
		System.out.println("�˻� ����(title/content/writerId) �� �ϳ��� �Է��ϼ���");
		System.out.print(" >");
		// � �ʵ�(����, �ۼ���, ���� ���)�� �˻��� ������ �Է¹޴´�.
		searchField = scan.nextLine();
		
		System.out.print("�˻��� >");
		// �˻�� �Է¹޴´�.
		searchWord = scan.nextLine();
		
	}

}
