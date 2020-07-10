package class_cooperation;

public class Student {
	
	String studnetName;
	int grade;
	int money;
	
	public Student(String studentName, int money) {
		this.studnetName = studentName;
		this.money = money;
	}
	
	public void takeBus(Bus bus) {
		bus.takeBus(1000);
		this.money -= 1000;
	}
	
	public void takeSubway(Subway subway) {
		subway.takeSubway(1200);
		this.money -= 1200;
	}
	
	public void takeTaxi(Taxi taxi) {
		taxi.takeTaxi(10000);
		this.money -= 10000;
	}
	
	public void showMoneyInfo() {
		System.out.println(studnetName + "´ÔÀÇ ³²Àº µ·Àº " + money + "¿ø ÀÔ´Ï´Ù.");
	}
}
