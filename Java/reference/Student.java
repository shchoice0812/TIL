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
		System.out.println(studentName + " 학생의 국어 및 수학 점수는 " + korean.score + "점, " + math.score + "점 입니다.");
	}
}
