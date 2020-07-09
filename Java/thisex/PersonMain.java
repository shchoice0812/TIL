package thisex;

public class PersonMain {

	public static void main(String[] args) {
		Person personNoName = new Person();
		personNoName.showInfo();
		
		Person personLee = new Person("Lee", 3);
		personLee.showInfo();
		System.out.println(personLee);
		
		Person p = personLee.getSelf();
		System.out.println(p);
		
	}

}
