package reference;

public class Student {
	
	int studentID;
	String studentName;
	
	Subject korean;
	Subject math;
	
	public Student() {
		korean = new Subject();
		math = new Subject();
	}
	
	public Student(int id, String name) {
		studentID = id;
		studentName = name;
		korean = new Subject();
		math = new Subject();
	}
	
	public void setKoreanSubject(String name, int score) {
		korean.subjectName = name;
		korean.score = score;
	}
	
	public void setMathSubject(String name, int score) {
		math.subjectName = name;
		math.score = score;
	}
	
	public void showStudentInfo() {
		System.out.println(studentName + " �л��� ���� �� ���� ������ " + korean.score + "��, " + math.score + "�� �Դϴ�.");
	}
}
