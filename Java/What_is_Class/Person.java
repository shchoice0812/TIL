package chap01_what_is_class;

class School{
	
}

class Home{
	
}
/*
 * 2. Person.java 파일에서는 class를 여러개 만들 수 있지만 public이 들어간 class는 단 하나만 존재 가능
private class myRoom{
	
}
public class Academy{

}
*/
// 클래스 기본 상식 : 한 자바 파일에서 class가 여러개 있을 수 있지만 public(private) class는 단 하나만 존재 가능하다.
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
