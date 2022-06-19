package viewer;

import java.util.Collections;
import java.util.Scanner;
import controller.StudentController;
import model.StudentDTO;
import util.SUtil;

public class StudentViewer {
	private Scanner sc;
	StudentController controller;
	
	public StudentViewer(){
		sc=new Scanner(System.in);
		controller=new StudentController();
	}
	
	public void showMenu() {
		while(true) {
			System.out.println("학생관리 프로그램입니다. 1.성적입력 2.성적출력 3.종료");
			int userChoice=SUtil.nextInt(sc,1,3);
			if(userChoice==1) {
				inputScore();
			} else if(userChoice==2) {
				printList();
			} else if(userChoice==3) {
				System.out.println("사용해주셔서 감사합니다.");
				break;
			}
		}
	}
	private void inputScore() {
		StudentDTO temp=new StudentDTO();
		System.out.println("이름을 입력해주세요");
		temp.setName(SUtil.nextLine(sc));
		System.out.println("국어점수를 입력해주세요");
		temp.setKorean(SUtil.nextInt(sc, 0, 100));
		System.out.println("영어점수를 입력해주세요");
		temp.setEnglish(SUtil.nextInt(sc, 0, 100));
		System.out.println("수학점수를 입력해주세요");
		temp.setMath(SUtil.nextInt(sc, 0, 100));
		controller.insert(temp);
		
	}
	private void printList() {

		if(controller.selectAll().isEmpty()) {
			System.out.println("저장된 정보가 없습니다.");
		} else {
			Collections.reverse(controller.selectAll());
			for(StudentDTO s:controller.selectAll()) {
				System.out.printf("번호:%d 이름:%s\n",s.getId(),s.getName());
			}
			
			System.out.println("학생의 번호를 입력해 성적을 조회하거나 0을눌러 뒤로가기");
			int idChoice=SUtil.nextInt(sc);
			StudentDTO temp=new StudentDTO(idChoice);
			
			if(idChoice!=0&&!controller.selectAll().contains(temp)) {
				System.out.println("잘못된 입력입니다.");
			} else if(idChoice!=0) {
				printOne(idChoice);
			}
		}
	}
	private void printOne(int id) {
		StudentDTO temp=controller.selectOne(id);
		System.out.println("------------------------------------");
		System.out.printf("번호:%d 이름:%s\n",temp.getId(),temp.getName());
		System.out.printf("국어점수:%03d점 영어점수:%03d점 수학점수:%03d점\n",temp.getKorean(),temp.getEnglish(),temp.getMath());
		System.out.printf("총점:%03d점 평균:%06.2f점\n",temp.getSum(),temp.getAverage());
		System.out.println("------------------------------------");
		System.out.println("1.삭제 2.수정 3.뒤로가기");
		int userChoice=SUtil.nextInt(sc, 1,3);
		
		if(userChoice==1) {
			deleteStudent(id);
		} else if(userChoice==2) {
			updateStudent(id);
		}
	}

	private void deleteStudent(int id) {
		System.out.println("정말로 삭제하시겠습니까?");
		String yOrN=SUtil.nextLine(sc, 1);
		if(yOrN.equalsIgnoreCase("y")) {
			controller.delete(id);
		}
	}

	private void updateStudent(int id) {
		StudentDTO temp=new StudentDTO(id);
		System.out.println("새로운 이름을 입력해주세요");
		temp.setName(SUtil.nextLine(sc));
		System.out.println("새로운 국어점수를 입력해주세요");
		temp.setKorean(SUtil.nextInt(sc,0,100));
		System.out.println("새로운 영어점수를 입력해주세요");
		temp.setEnglish(SUtil.nextInt(sc, 0, 100));
		System.out.println("새로운 수학점수를 입력해주세요");
		temp.setMath(SUtil.nextInt(sc, 0, 100));
		
		controller.update(temp);
	}
}
