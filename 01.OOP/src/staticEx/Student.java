package staticEx;

public class Student {
	
	public static int serialNum = 1000;
	private int studentID;
	public String studentName;  
	public String address;
	
	public Student(String name) {
		studentName = name;
		serialNum++;
		studentID = serialNum;
	}
	
	public Student(int id, String name) {
		studentID = id;
		studentName = name;
		address = "주소없음";
		serialNum++;
	} 
	
	public void showStudentInfo() {
		System.out.println(studentName + "," + address);
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public int getStudentID() {
		serialNum++;
		return studentID;
	}
}