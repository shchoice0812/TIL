package thisex;

public class Person {
	String name;
	int age;
	
	public Person() {
		this("이름없음", 1);
		// this 다음에 age를 변경시킬 수 있다. 다만 this()가 first statement 여야한다, this.age가 this()보다 앟에있으면 에러 발생한다.
		this.age = 100;
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public void showInfo() {
		System.out.println(name + ", " + age);
	}
	
	public Person getSelf() {
		return this;
	}
}
