package class_cooperation;

public class Taxi {
	
	int TaxiNumber;
	int passengerCount;
	int money;
	
	public Taxi(int TaxiNumber) {
		this.TaxiNumber = TaxiNumber;		
	}
	
	public void takeTaxi(int money) {
		this.money += money;
		passengerCount++;
	}
	
	public void showTaxiInfo() {
		System.out.println(TaxiNumber + "�� �ý��� �°���" + passengerCount + "���̰�, ������ " + money + "�Դϴ�.");
	}
}
