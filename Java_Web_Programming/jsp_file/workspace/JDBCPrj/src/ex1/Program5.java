package ex1;

import java.sql.SQLException;

import com.newlecture.app.console.NoticeConsole;

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		NoticeConsole console = new NoticeConsole();
		
		// ����ڰ� ���� �޴��� ������ �������� ��� �ݺ��ؼ� ������ ������
		// �ݺ��� �տ� ���� ��: ���·� ���� �� �ִ�.
		EXIT:while(true) {
			console.printNoticeList();
			
			// ������� �Է°��� �Է¹޾� �ش��ϴ� ������ ����(���ڷ� �Է¹���)
			int menu = console.inputNoticeMenu();
			
			switch(menu) {
			case 1: // ����ȸ
				
				break;
				
			case 2:  // ����
				console.movePrevList();
				break;
				
			case 3:  // ����
				console.moveNextList();
				break;
				
			case 4:  //�۾���
				break;

			case 5:  //�˻�
				// �˻�� �Է¹���
				console.inputSearchWord();
				break;				
				
			case 6:  //����
				System.out.println("Bye~~~~~~~");
				// break ������ �󺧸��� ���� �ش� �� �ܰ���� ��� �����Ѵ�.
				break EXIT;
				
			default:
				System.out.println("�޴��� 1���� 4������ �Է��� �� �ֽ��ϴ�.");
				break;
			}
		}
	}

}
