package thisex;

public class Person {
	String name;
	int age;
	
	public Person() {
		this("�̸�����", 1);
		// this ������ age�� �����ų �� �ִ�. �ٸ� this()�� first statement �����Ѵ�, this.age�� this()���� �ۿ������� ���� �߻��Ѵ�.
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
