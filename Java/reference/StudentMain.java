package reference;

public class StudentMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student student1 = new Student(1, "���̸�");
		student1.setKoreanSubject("����", 100);
		student1.setMathSubject("����", 100);
		
		Student student2 = new Student(2, "����");
		student2.setKoreanSubject("����", 100);
		student2.setMathSubject("����", 100);
		
		student1.showStudentInfo();
		student2.showStudentInfo();
	}

}
