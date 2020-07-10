package class_cooperation;

public class Subway {
	
	int subwayLineNumber;
	int passengerCount;
	int money;
	
	public Subway(int subwayLineNumber) {
		this.subwayLineNumber = subwayLineNumber;
	}
	
	public void takeSubway(int money) { // 승차
		this.money += money;
		passengerCount++;
	}

	public void showSubwayInfo() {
		System.out.println(subwayLineNumber + "번 버스의 승객은" + passengerCount + "명이고, 수입은 " + money + "입니다.");
	}
}
