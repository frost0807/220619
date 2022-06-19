package controller;

import java.util.ArrayList;
import model.StudentDTO;

public class StudentController {
	private ArrayList<StudentDTO> sList;
	private int nextId;
	
	public StudentController(){
		sList=new ArrayList<>();
		nextId=1;
	}
	
	public ArrayList<StudentDTO> selectAll(){
		return sList;
	}
	public StudentDTO selectOne(int id) {
		StudentDTO temp=new StudentDTO(id);
		if(sList.contains(temp)) {
			return sList.get(sList.indexOf(temp));
		} else {
			return null;
		}
	}
	public void insert(StudentDTO s) {
		s.setId(nextId++);
		sList.add(s);
	}
	public void delete(int id) {
		sList.remove(new StudentDTO(id));
	}
	public void update(StudentDTO s) {
		sList.set(sList.indexOf(s),s);
	}
}
