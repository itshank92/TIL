package sec04.exam03;

public class Car {
	// �޼ҵ�
	int gas;
	
	
	// ������
	void setGas(int gas) {
		this.gas = gas;
	}
	
	boolean isLeftGas() {
		if(gas == 0) {
			System.out.println("������ �����ϴ�");
			return false;
		} else {
			System.out.println("������ �ֽ��ϴ�");
			return true;
		}
	}
	
	void run() {
		while(true) {
			if(gas > 0) {
				System.out.println("�޸��ϴ�.(gas �ܷ�:" + gas + ")");
				gas -= 1;
			} else {
				System.out.println("����ϴ�.(gas �ܷ�:" + gas + ")");
				return;
			}
		}
	}

}
