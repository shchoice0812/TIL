package class_cooperation;

public class Subway {
	
	int subwayLineNumber;
	int passengerCount;
	int money;
	
	public Subway(int subwayLineNumber) {
		this.subwayLineNumber = subwayLineNumber;
	}
	
	public void takeSubway(int money) { // ����
		this.money += money;
		passengerCount++;
	}

	public void showSubwayInfo() {
		System.out.println(subwayLineNumber + "�� ������ �°���" + passengerCount + "���̰�, ������ " + money + "�Դϴ�.");
	}
}
