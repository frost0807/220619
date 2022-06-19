package model;

public class StudentDTO {
	private final int SUBJECT_NUMBER=3;
	private int id;
	private String name;
	private int korean;
	private int english;
	private int math;
	
	public StudentDTO() {}
	
	public StudentDTO(int id) {
		this.id=id;
	}
	public StudentDTO(StudentDTO s) {
		StudentDTO temp=new StudentDTO();
		this.id=s.id;
		this.name=s.name;
		this.korean=s.korean;
		this.english=s.english;
		this.math=s.math;
	}
	public boolean equals(Object o) {
		if(o instanceof StudentDTO) {
			return this.id==((StudentDTO)o).id;
		}
		return false;
		
	}
	public int getSum() {
		return calculateSum(korean,english,math);
	}
	public double getAverage() {
		return calculateAverage(getSum());
	}
	private int calculateSum(int korean,int english,int math) {
		return korean+english+math;
	}
	private double calculateAverage(int sum) {
		return (double)sum/SUBJECT_NUMBER;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getKorean() {
		return korean;
	}
	public int getEnglish() {
		return english;
	}
	public int getMath() {
		return math;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public void setMath(int math) {
		this.math = math;
	}
}
