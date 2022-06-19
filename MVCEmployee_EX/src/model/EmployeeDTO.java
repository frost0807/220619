package model;

public class EmployeeDTO {
	private String name;
	private String position;
	private String department;
	private int id;
	private int salary;
	private int joinYear;
	
	public EmployeeDTO() {}
	public EmployeeDTO(int id) {
		this.id=id;
	}
	public EmployeeDTO(EmployeeDTO e) {
		this.id=e.id;
		this.name=e.name;
		this.position=e.position;
		this.department=e.department;
		this.salary=e.salary;
		this.joinYear=e.joinYear;
	}
	public boolean equals(Object o) {
		if(o instanceof EmployeeDTO) {
			return this.id==((EmployeeDTO)o).id;
		} else {
			return false;
		}
	}
	public String getName() {
		return name;
	}
	public String getPosition() {
		return position;
	}
	public String getDepartment() {
		return department;
	}
	public int getId() {
		return id;
	}
	public int getSalary() {
		return salary;
	}
	public int getJoinYear() {
		return joinYear;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setJoinYear(int joinYear) {
		this.joinYear = joinYear;
	}
	
}
