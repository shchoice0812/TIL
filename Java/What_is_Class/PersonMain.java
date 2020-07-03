/*
 * 해당 코드를 통해 공부하려고 하는것
 * 1. 구현하고자하는 기능을 가진 클래스와 main 함수 파일은 분리할 수 있다.
 * 2. Person.java 파일에서는 class를 여러개 만들 수 있지만 public이 들어간 class는 단 하나만 존재 가능
 * 3. private과 public 차이
 * 		- private은 다른 파일에서는 읽을 수 없다.
 * 		- public은 어디에서든 읽을 수 있다.(패키지 뿐만 아니라 폴더 내 전체)
 * 4. 참조변수(레퍼런스변수)의 실제 값은 힙 메모리에 있음을 알 수 있다.
 */

package chap01_what_is_class;

public class PersonMain {

	public static void main(String[] args) {
		// Person 타입의 객체를 가리킬 person1 참조(레퍼런스) 변수 선언
		Person person1 = new Person();
		person1.personID = 1;
		person1.personName = "아이린";
		person1.personAge = 30;
		// 3. private과 public 차이
		// 아래의 phoneNum은 private이기 때문에 보이지 않는다.
		// person1.phoneNum = "010-0000-0000";		
		person1.showPersonInfo();
		
		// Person 객체에 대한 person2 참조(레퍼런스) 변수 선언
		Person person2 = new Person();
		person2.personID = 2;
		person2.personName = "수지";
		person2.personAge = 27;
		person2.showPersonInfo();
		
		Person person3 = new Person();
		person3.personID = 3;
		person3.personName = "태연";
		person3.personAge = 32;
		person3.showPersonInfo();
		
		//4. 참조변수의 실제 값은 힙 메모리에 있음을 알 수 있다.
		//메모리 주소확인 
		System.out.println(person1); // class fullname(패키지명 포함)@힙 메모리의 값을 가리키는 주소값
		System.out.println(person2);
		System.out.println(person3);
	}
}
