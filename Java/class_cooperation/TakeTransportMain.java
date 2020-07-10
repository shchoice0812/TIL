package class_cooperation;

public class TakeTransportMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student student1 = new Student("아이유", 10000);
		Student student2 = new Student("아이린", 30000);
		Student student3 = new Student("수지", 15000);
		
		Bus bus3313 = new Bus(3313);
		Bus bus3315 = new Bus(3315);
		
		Subway line3 = new Subway(3);
		Subway line2 = new Subway(2);
		
		Taxi taxi1 = new Taxi(1);
		
		student1.takeBus(bus3313);
		student2.takeSubway(line2);
		student3.takeTaxi(taxi1);
		
		student1.showMoneyInfo();
		student2.showMoneyInfo();
		student3.showMoneyInfo();
		
		bus3313.showBusInfo();
		bus3315.showBusInfo();
		
		line2.showSubwayInfo();
		
		taxi1.showTaxiInfo();
		
		
	}

}
