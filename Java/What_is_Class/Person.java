package chap01_what_is_class;

class School{
	
}

class Home{
	
}
/*
 * 2. Person.java ���Ͽ����� class�� ������ ���� �� ������ public�� �� class�� �� �ϳ��� ���� ����
private class myRoom{
	
}
public class Academy{

}
*/
// Ŭ���� �⺻ ��� : �� �ڹ� ���Ͽ��� class�� ������ ���� �� ������ public(private) class�� �� �ϳ��� ���� �����ϴ�.
public class Person {
	
	public int personID;
	public String personName;
	public int personAge;
	private String phoneNum;
	
	public void walk() {}
	public void run() {}
	public void eat() {}

	public void showPersonInfo() {
		System.out.println(personID + " : " + personName + ", " + personAge);
	}
	
	public int getPersonID() {
		return personID;
	}
}
