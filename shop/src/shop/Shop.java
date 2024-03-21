package shop;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
	private Scanner scan = new Scanner(System.in);
	
	private ArrayList<User> users;
	
	private String shopName;
	public int log = -1;
	
	public Shop(String shopName) {
		this.shopName = shopName;
		users = new ArrayList<User>();
	}
	
	private int inputNumber(String message) {
		int number = -1;
		try {
			System.out.print(message + " : ");
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch(Exception e) {
			System.out.println("숫자를 입력하세요.");
		}
		
		return number;
	}
	
	private String inputString(String message) {
		System.out.print(message + " : ");
		return scan.next();
	}
	
	private void join() {
		String name = inputString("name");
		String id = inputString("id");
		String pw = inputString("pw");
		
		UserManager um = new UserManager();
		
		int idx = -1;
		for(int i = 0; i < um.getUserCount(); i++) {
			User user = users.get(i);
			if(user.getId().equals(id))
				idx = i;
		}
		
		if(idx != -1) {
			System.out.println("이미 존재하는 아이디입니다.");
			return;
		}
		
		um.createUser(name, id, pw);
		
		System.out.println("회원가입 완료");
	}
	
	private void leave() {
		String pw = inputString("pw");
		if(!users.get(log).getPassword().equals(pw)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		users.remove(log);
		
//		UserManager um = new UserManager();
//		um.setUserCount(false);
		
		log = -1;
		System.out.println("로그아웃 완료");
	}
	
	private void login() {
		String id = inputString("id");
		String pw = inputString("pw");
		
		int log = -1;
		UserManager um = new UserManager();
		
		for(int i = 0; i < um.getUserCount(); i++) {
			User user = users.get(i);
			if(user.getId().equals(id) && user.getPassword().equals(pw))
				log = i;
		}
		
		if(log == -1) {
			System.out.println("로그인 정보가 일치하지 않습니다.");
			return;
		}
		
		System.out.println("로그인 성공");
	}
	
	private void logout() {
		log = -1;
		System.out.println("로그아웃 완료");
	}
	
	private void shopping() {
		
	}
	
	private void myPage() {
		
	}
	
	private void runUserMenu(int select) {
		if(select == 1 && log == -1)
			join();
		else if(select == 1 && log != -1)
			leave();
		else if(select == 2 && log == -1)
			login();
		else if(select == 2 && log != -1)
			logout();
		else if(select == 3)
			shopping();
		else if(select == 4)
			myPage();
		else if(select == 5) {
			FileManager fm = new FileManager();
			fm.run();
		}
	}
	
	private void printUserMenu() {
		System.out.printf("1. %s\n", log == -1 ? "회원가입" : "탈퇴");
		System.out.printf("2. %s\n", log == -1 ? "로그인" : "로그아웃");
		System.out.println("3. 쇼핑하기");
		System.out.println("4. 마이페이지");
		System.out.println("5. 자동저장/자동로드");
		// ㄴ 회원가입/탈퇴/
		// ㄴ 로그인/로그아웃/
		// ㄴ 쇼핑하기/
		// ㄴ 마이페이지(내장바구니, 항복삭제, 수량수정, 결제)/
		// ㄴ 자동저장/자동로드
	}
	
	private void runManagerMenu(int select) {
		if(select == 1) {
			ItemManager im = new ItemManager();
			im.run();
		}
	}
	
	private void printManagerMenu() {
		System.out.println("1. 아이템등록/삭제/수정/");
		System.out.printf("2. 조회(총 매출)");
		// 관리자 -
		// ㄴ 아이템등록/삭제/수정/
		// ㄴ 조회(총 매출)
	}
	
	private void runMenu(int select) {
		if(select == 1) {
			printUserMenu();
			runUserMenu(inputNumber(""));
		}
		else if(select == 2) {
			printManagerMenu();
			runManagerMenu(inputNumber(""));
		}
	}
	
	private void printMenu() {
		System.out.println("[1]User");
		System.out.println("[2]Manager");
	}

	public void run() {
		while(true) {
			UserManager um = new UserManager();
			System.out.println("회원 " + um.getUserCount() + "명");
			System.out.println("log = " + log);
			System.out.printf("[%s Shop]\n", shopName);
			printMenu();
			int select = inputNumber("menu");
			runMenu(select);
		}
	}
}
