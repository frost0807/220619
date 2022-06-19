package viewer;

import java.util.Scanner;
import controller.UserController;
import model.UserDTO;
import util.SUtil;

public class UserViewer {
	private UserController controller;
	private Scanner sc;
	
	public UserViewer(){
		controller=new UserController();
		sc=new Scanner(System.in);
	}
	
	public void showMenu() {
		while(true) {
			System.out.println("안녕하세요 1.로그인 2.회원가입 3.종료");
			int userChoice=SUtil.nextInt(sc, 1, 3);
			if(userChoice==1) {
				if(controller.selectAll().isEmpty()) {
					System.out.println("가입된 회원이 없습니다.");
				} else {
					login();
				}
			} else if(userChoice==2) {
				signUp();
			} else if(userChoice==3) {
				System.out.println("사용해 주셔서 감사합니다.");
				break;
			}
		}
	}
	private void login() {
		System.out.println("아이디를 입력해주세요");
		String tempId=SUtil.nextLine(sc);
		System.out.println("비밀번호를 입력해주세요");
		String tempPassword=SUtil.nextLine(sc);
		checkIdAndPassword(tempId, tempPassword);
		
	}
	private void signUp() {
		UserDTO temp=new UserDTO();
		System.out.println("이름을 입력해주세요");
		temp.setName(SUtil.nextLine(sc));
		System.out.println("아이디를 입력해주세요");
		temp.setId(SUtil.nextLine(sc));
		System.out.println("비밀번호를 입력해주세요");
		temp.setPassword(SUtil.nextLine(sc));
		System.out.println("휴대폰번호를 입력해주세요 (- 제외)");
		temp.setPhone(SUtil.nextLine(sc));
		System.out.println("이메일주소를 입력해주세요");
		temp.setEmail(SUtil.nextLine(sc));
		
		controller.insert(temp);
		System.out.println("회원가입이 완료되었습니다.");
	}
	private void checkIdAndPassword(String id,String password) {
		UserDTO temp=new UserDTO(id);
		if(!controller.selectAll().contains(temp)) {
			System.out.println("존재하지 않는 아이디입니다.");
		} else {
			String existPassword=controller.selectOne(id).getPassword();
			if(password.equals(existPassword)) {
				System.out.println("로그인 되었습니다.");
				loggedIn(id);
			} else {
				System.out.println("잘못된 비밀번호 입니다.");
			}
		}
	}
	private void loggedIn(String id) {
		System.out.println("1.회원탈퇴 2.회원정보수정 3.로그아웃");
		int userChoice=SUtil.nextInt(sc, 1, 3);
		if(userChoice==1) {
			withdraw(id);
		} else if(userChoice==2) {
			printUser(id);
			updateUser(id);
		} else if(userChoice==3) {
			
		}
	}
	private void withdraw(String id) {
		System.out.println("정말로 탈퇴하시겠습니까? Y/N");
		String yOrN=SUtil.nextLine(sc,1);
		if(yOrN.equalsIgnoreCase("y")) {
			controller.delete(id);
			System.out.println("삭제 되었습니다.");
		} else {
			loggedIn(id);
		}
	}
	private void printUser(String id) {
		UserDTO temp=controller.selectOne(id);
		System.out.println("이름:"+temp.getName());
		System.out.println("아이디:"+temp.getId());
		System.out.println("비밀번호:"+temp.getPassword());
		System.out.println("휴대폰번호:"+temp.getPhone());
		System.out.println("이메일주소:"+temp.getEmail());
	}
	private void updateUser(String id) {
		UserDTO temp=controller.selectOne(id);
		System.out.println("회원정보를 변경합니다. 그대로 사용하시려는 항목에는 -1을 입력해주세요");
		System.out.println("새로운 비밀번호를 입력해주세요");
		String tempPassword=SUtil.nextLine(sc);
		if(!tempPassword.equals("-1")) {
			temp.setPassword(tempPassword);
		}
		System.out.println("새로운 이메일을 입력해주세요");
		String tempEmail=SUtil.nextLine(sc);
		if(!tempEmail.equals("-1")) {
			temp.setEmail(tempEmail);
		}
		System.out.println("새로운 핸드폰번호를 입력해주세요");
		String tempPhone=SUtil.nextLine(sc);
		if(!tempPhone.equals("-1")) {
			temp.setPhone(tempPhone);
		}
		controller.update(temp);
		System.out.println("회원정보 변경이 완료되었습니다.");
	}
}