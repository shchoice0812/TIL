/*
 * �ش� �ڵ带 ���� �����Ϸ��� �ϴ°�
 * 1. �����ϰ����ϴ� ����� ���� Ŭ������ main �Լ� ������ �и��� �� �ִ�.
 * 2. Person.java ���Ͽ����� class�� ������ ���� �� ������ public�� �� class�� �� �ϳ��� ���� ����
 * 3. private�� public ����
 * 		- private�� �ٸ� ���Ͽ����� ���� �� ����.
 * 		- public�� ��𿡼��� ���� �� �ִ�.(��Ű�� �Ӹ� �ƴ϶� ���� �� ��ü)
 * 4. ��������(���۷�������)�� ���� ���� �� �޸𸮿� ������ �� �� �ִ�.
 */

package chap01_what_is_class;

public class PersonMain {

	public static void main(String[] args) {
		// Person Ÿ���� ��ü�� ����ų person1 ����(���۷���) ���� ����
		Person person1 = new Person();
		person1.personID = 1;
		person1.personName = "���̸�";
		person1.personAge = 30;
		// 3. private�� public ����
		// �Ʒ��� phoneNum�� private�̱� ������ ������ �ʴ´�.
		// person1.phoneNum = "010-0000-0000";		
		person1.showPersonInfo();
		
		// Person ��ü�� ���� person2 ����(���۷���) ���� ����
		Person person2 = new Person();
		person2.personID = 2;
		person2.personName = "����";
		person2.personAge = 27;
		person2.showPersonInfo();
		
		Person person3 = new Person();
		person3.personID = 3;
		person3.personName = "�¿�";
		person3.personAge = 32;
		person3.showPersonInfo();
		
		//4. ���������� ���� ���� �� �޸𸮿� ������ �� �� �ִ�.
		//�޸� �ּ�Ȯ�� 
		System.out.println(person1); // class fullname(��Ű���� ����)@�� �޸��� ���� ����Ű�� �ּҰ�
		System.out.println(person2);
		System.out.println(person3);
	}
}
