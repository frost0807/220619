package viewer;

import java.util.Scanner;
import controller.EmployeeController;
import model.EmployeeDTO;
import util.SUtil;

public class EmployeeViewer {
	private Scanner sc;
	private EmployeeController controller;
	
	public EmployeeViewer() {
		sc=new Scanner(System.in);
		controller=new EmployeeController();
	}
	
	public void showMenu() {
		while(true) {
			System.out.println("사원관리 프로그램입니다. 1.사원등록 2.사원검색 및 편집 3.종료");
			int userChoice=SUtil.nextInt(sc,1,3);
			if(userChoice==1) {
				register();
			} else if(userChoice==2) {
				if(controller.selectAll().isEmpty()) {
					System.out.println("저장된 사원의 정보가 없습니다.");
				} else {
					search();
				}
			} else if(userChoice==3) {
				System.out.println("사용해 주셔서 감사합니다.");
				break;
			}
		}
	}
	private void register() {
		EmployeeDTO temp=new EmployeeDTO();
		System.out.println("이름을 입력해주세요");
		temp.setName(SUtil.nextLine(sc));
		System.out.println("입사년도를 입력해주세요");
		temp.setJoinYear(SUtil.nextInt(sc));
		System.out.println("부서명을 입력해주세요");
		temp.setDepartment(SUtil.nextLine(sc));
		System.out.println("직급을 입력해주세요");
		temp.setPosition(SUtil.nextLine(sc));
		System.out.println("연봉을 입력해주세요");
		temp.setSalary(SUtil.nextInt(sc));
		
		controller.insert(temp);
		System.out.println("사원등록이 완료되었습니다.");
	}
	private void search() {
		System.out.println("사원검색 및 편집 페이지입니다.\n"
				+ "1.이름으로검색 2.직급으로검색 3.부서명으로검색 4.입사년도로검색 5.뒤로가기");
		int userChoice=SUtil.nextInt(sc, 1, 5);
		if(userChoice==1) {
			searchByName();
		} else if(userChoice==2) {
			searchByPosition();
		} else if(userChoice==3) {
			searchByDepartment();
		} else if(userChoice==4) {
			searchByJoinYear();
		}
	}
	private void searchByName() {
		System.out.println("이름으로 검색합니다. 이름을 입력해주세요");
		String tempName=SUtil.nextLine(sc);		
		boolean checkValid=false;
		
		for(EmployeeDTO e:controller.selectAll()) {
			if(e.getName().equals(tempName)) {
				printOne(e);
				checkValid=true;
			}
		}
		if(checkValid) {
			editEmployeeStatus();
		} else {
			System.out.println("해당하는 사원정보가 없습니다.");
		}
	}
	private void searchByPosition() {
		System.out.println("직급으로 검색합니다. 직급을 입력해주세요");
		String tempPosition=SUtil.nextLine(sc);		
		boolean checkValid=false;
		
		for(EmployeeDTO e:controller.selectAll()) {
			if(e.getPosition().equals(tempPosition)) {
				printOne(e);
				checkValid=true;
			}
		}
		if(checkValid) {
			editEmployeeStatus();
		} else {
			System.out.println("해당하는 사원정보가 없습니다.");
		}
	}
	private void searchByDepartment() {
		System.out.println("부서로 검색합니다. 부서를 입력해주세요");
		String tempDepartment=SUtil.nextLine(sc);	
		boolean checkValid=false;
		
		for(EmployeeDTO e:controller.selectAll()) {
			if(e.getDepartment().equals(tempDepartment)) {
				printOne(e);
				checkValid=true;
			}
		}
		if(checkValid) {
			editEmployeeStatus();
		} else {
			System.out.println("해당하는 사원정보가 없습니다.");
		}
	}
	private void searchByJoinYear() {
		System.out.println("입사년도로 검색합니다. 입사년도를 입력해주세요");
		int tempJoinYear=SUtil.nextInt(sc);	
		boolean checkValid=false;
		
		for(EmployeeDTO e:controller.selectAll()) {
			if(e.getJoinYear()==tempJoinYear) {
				printOne(e);
				checkValid=true;
			}
		}
		if(checkValid) {
			editEmployeeStatus();
		} else {
			System.out.println("해당하는 사원정보가 없습니다.");
		}
	}
	private void editEmployeeStatus() {
		System.out.println("사원정보를 편집을 위해 사원번호를 입력해주시거나 0을눌러 뒤로가기");
		int idChoice=SUtil.nextInt(sc);
		if(idChoice!=0&&!controller.selectAll().contains(new EmployeeDTO(idChoice))) {
			System.out.println("잘못된 입력입니다.");
		} else if(idChoice!=0) {
			System.out.println("-----------------------------------------------------------");
			printOne(controller.selectOne(idChoice));
			System.out.println("-----------------------------------------------------------");
			System.out.println("1.사원정보삭제 2.사원정보수정 3.뒤로가기");
			int userChoice=SUtil.nextInt(sc,1,3);
			if(userChoice==1) {
				deleteProcess(idChoice);
			} else if(userChoice==2) {
				updateProcess(idChoice);
			} else if(userChoice==3) {
				search();
			}
		}
	}
	private void deleteProcess(int id) {
		System.out.println("정말로 삭제하시겠습니까? Y/N");
		String yOrN=SUtil.nextLine(sc,1);
		if(yOrN.equalsIgnoreCase("y")) {
			controller.delete(id);
			System.out.println("사원정보가 삭제되었습니다.");
		}
	}
	private void updateProcess(int id) {
		EmployeeDTO temp=controller.selectOne(id);
		System.out.println("새로운 직급을 입력하시거나 변경사항이 없다면 -1을 입력해주세요");
		String tempPosition=SUtil.nextLine(sc);
		if(!tempPosition.equals("-1")) {
			temp.setPosition(tempPosition);
		}
		System.out.println("새로운 부서를 입력하시거나 변경사항이 없다면 -1을 입력해주세요");
		String tempDepartment=SUtil.nextLine(sc);
		if(!tempDepartment.equals("-1")) {
			temp.setDepartment(tempDepartment);
		}
		System.out.println("새로운 연봉을 입력하시거나 변경사항이 없다면 -1을 입력해주세요");
		int tempSalary=SUtil.nextInt(sc);
		if(tempSalary!=-1) {
			temp.setSalary(tempSalary);
		}
		controller.update(temp);
		System.out.println("사원정보 갱신이 완료되었습니다.");
	}
	private void printOne(EmployeeDTO e) {
		System.out.printf("사원번호:%03d 이름:%3s 직급:%s 부서:%s 입사년도:%d 연봉:%d\n",
				e.getId(),e.getName(),e.getPosition(),e.getDepartment(),e.getJoinYear(),e.getSalary());
	}
}
