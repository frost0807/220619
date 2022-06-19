package controller;

import model.EmployeeDTO;
import java.util.ArrayList;
import java.util.Random;

public class EmployeeController {
	private int nextId;
	private ArrayList<EmployeeDTO> eList;
	
	public EmployeeController(){
		nextId=1;
		eList=new ArrayList<>();
		for(int i=1;i<100;i++) {
			EmployeeDTO temp=new EmployeeDTO();
			Random random=new Random();
			String[] tempName= {"최준석","문병훈","이규화","권혁천","이상돈"};
			String[] tempPosition= {"사원","대리","주임","차장","부장"};
			String[] tempDepartment= {"개발부","홍보부","기획부","인사부","수행부"};
			int[] tempJoinYear= {2022,2021,2020,2019,2018};
			int[] tempSalary= {3000,3500,4000,4500,5000};
			temp.setName(tempName[random.nextInt(5)]);
			temp.setPosition(tempPosition[random.nextInt(5)]);
			temp.setDepartment(tempDepartment[random.nextInt(5)]);
			temp.setJoinYear(tempJoinYear[random.nextInt(5)]);
			temp.setSalary(tempSalary[random.nextInt(5)]);
			insert(temp);
		}
	
	}
	public void insert(EmployeeDTO e) {
		e.setId(nextId++);
		eList.add(e);
	}
	public ArrayList<EmployeeDTO> selectAll() {
		return eList;
	}
	public EmployeeDTO selectOne(int id) {
		return eList.get(eList.indexOf(new EmployeeDTO(id)));
	}
	public void delete(int id) {
		eList.remove(new EmployeeDTO(id));
	}
	public void update(EmployeeDTO e) {
		eList.set(eList.indexOf(e), e);
	}
}
