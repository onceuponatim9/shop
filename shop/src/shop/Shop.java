package shop;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
	private Scanner scan = new Scanner(System.in);
	
	//private ArrayList<User> users;
	private UserManager um = new UserManager();
	private ItemManager im = new ItemManager();
	
	private String shopName;
	public int log = -1;
	
	public Shop(String shopName) {
		this.shopName = shopName;
		//users = new ArrayList<User>();
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
		
		User user = um.findUserById(id);
		
		if(user == null) {
			System.out.println("이미 존재하는 아이디입니다.");
			return;
		}
		
		um.createUser(name, id, pw);
		
		System.out.println("회원가입 완료");
	}
	
	private void leave() {
		User user = um.findUserByLog(log);
		String pw = inputString("pw");
		if(!user.getPassword().equals(pw)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		um.removeUser(log);
		
		log = -1;
		System.out.println("회원탈퇴 완료");
	}
	
	private void login() {
		String id = inputString("id");
		String pw = inputString("pw");
		
		log = um.getUserLogByIdAndPassword(id, pw);
		
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
	
	private int findItemIndexOfUserCart(String itemName) {
		User user = um.findUserByLog(log);
		int index = -1;
		
		for(int i = 0; i < user.getUserCartCount(); i++) {
			if(user.cloneItemInCart(i).getName().equals(itemName))
				index = i;
		}
		
		return index;
	}
	
	private void shopping() {
		showAllItems();
		int index = inputNumber("item number") - 1;
		int amount = inputNumber("amount");
		
		if(index < 0 || index >= im.getItemCount()) {
			System.out.println("존재하지 않는 아이템입니다.");
			return;
		}
		
		if(amount <= 0) {
			System.out.println("수량은 1개부터 선택 가능합니다.");
			return;
		}
		
		// 이미 장바구니에 존재하면 기존 수량 + amount로 바꾸기
		
		if(log != -1) {
			User user = um.findUserByLog(log);
			String itemName = im.getItem(index).getName();
			Item item = new Item(itemName, amount);
			
			int myIndex = findItemIndexOfUserCart(itemName);
			
			if(myIndex == -1) {
				user.userShopping(item);
				return;
			}
			// user의 카트에서 해당 아이템이 몇 번째에 있는지 인덱스 구하기
			System.out.println("카트에 이미 담긴 아이템입니다. 수량을 추가하시겠습니까?");
			int sel = inputNumber("Yes) 1, No) 2");
			if(sel == 1) {
				int previousAmount = user.cloneItemInCart(myIndex).getAmount();
				user.cloneItemInCart(myIndex).setAmount(previousAmount + amount);
			}
		}
	}
	
//	private int myCart() {
//		User user = um.findUserByLog(log);
//		ArrayList<String> list = new ArrayList<>();
//		System.out.println(user.getUserCartCount());
//		
//		for(int i = 0; i < user.getUserCartCount(); i++) {
//			Item item = user.cloneCart(i);
//			boolean isTrue = false;
//			
//			for(int j = 0; j < list.size(); j += 2) {
//				if(list.get(j).equals(item.getName())) {
//					int num = Integer.parseInt(list.get(j + 1)) + 1;
//					list.set(j + 1, String.valueOf(num));
//					isTrue = true;
//				}
//			}
//			
//			if(!isTrue) {
//				list.add(item.getName());
//				list.add(String.valueOf(1));
//			}
//		}
//		
//		//System.out.println("list 길이 : " + list.size());
//		for(int i = 0; i < list.size(); i += 2) {
//			System.out.printf("%d) %s %d개\n", i + 1, list.get(i), Integer.parseInt(list.get(i + 1)));
//		}
//		
//		return list.size();
//	}
	
	private void myCart() {
		User user = um.findUserByLog(log);
		
		for(int i = 0; i < user.getUserCartCount(); i++) {
			Item item = user.cloneItemInCart(i);
			System.out.printf("%d) %s %d개\n", i + 1, item.getName(), item.getAmount());
		}
	}
	
	private void removeItemFromCart() {
		myCart();
		
		int index = inputNumber("item number") - 1;
		
		User user = um.findUserByLog(log);
		int size = user.getUserCartCount();
		
		if(index < 0 || index >= size) {
			System.out.println("존재하지 않는 아이템입니다.");
			return;
		}
		
		user.cloneCart().remove(index);
		System.out.println("장바구니에서 선택한 아이템이 삭제되었습니다.");
	}
	
//	private ArrayList<String> getList() {
//		User user = um.findUserByLog(log);
//		ArrayList<String> list = new ArrayList<>();
//		//System.out.println(user.getUserCartCount());
//		
//		for(int i = 0; i < user.getUserCartCount(); i++) {
//			Item item = user.cloneCart(i);
//			boolean isTrue = false;
//			
//			for(int j = 0; j < list.size(); j += 2) {
//				if(list.get(j).equals(item.getName())) {
//					int num = Integer.parseInt(list.get(j + 1)) + 1;
//					//int num = Integer.parseInt(list.get(j + 1)) + 1;
//					//list.set(j + 1, String.valueOf(num));
//					isTrue = true;
//				}
//			}
//			
//			if(!isTrue) {
//				list.add(item.getName());
//				list.add(String.valueOf(1));
//			}
//		}
//		
//		return list;
//	}
	
	private String findItemName(int index) {
		String itemName = null;
		
		User user = um.findUserByLog(log);
		
		for(int i = 0; i < user.getUserCartCount(); i++) {
			Item item = user.cloneItemInCart(i);
			for(int j = 0; j < im.getItemCount(); j++) {
				if(item.getName().equals(im.getItem(j).getName()))
					itemName = im.getItem(j).getName();
			}
		}
		
//		for(int i = 0; i < im.getItemCount(); i++) {
//			if(list.get(index * 2).equals(im.getItem(i).getName()))
//					itemName = im.getItem(i).getName();
//		}
		
		return itemName;
	}
	
	private int findIndexOfItemName(String name) {
		int index = -1;
		
		for(int i = 0; i < im.getItemCount(); i++) {
			Item item = im.getItem(i);
			if(item.getName().equals(name))
				index = i;
		}
		
		return index;
	}
	
	private int itemCountInCart(String name) {
		int count = 0;
		return count;
	}
	
	private void updateAmount() {
		myCart();
		
		int index = inputNumber("item number") - 1;
		int amount = inputNumber("amount");
		
		User user = um.findUserByLog(log);
		int size = user.getUserCartCount();
		//int size = getList().size();
		
		if(index < 0 || index >= size) {
			System.out.println("존재하지 않는 아이템입니다.");
			return;
		}
		
		if(amount <= 0) {
			System.out.println("수량은 1개부터 선택 가능합니다.");
			return;
		}
		
		// index에 해당하는 아이템 이름과 같은 아이템을 ItemManager에서 찾아서 그 인덱스 리턴받기
		String itemName = findItemName(index);
		
		// 해당 유저의 전체 카트에서 해당 아이템이 존재하지 않으면 return
		if(itemName == null) {
			System.out.println("내 장바구니에 해당 아이템이 존재하지 않습니다.");
			return;
		}
		
		int itemIndex = findIndexOfItemName(itemName);
		
		//Item item = im.getItem(itemIndex);
		
		
		
		// 존재하면 그 개수 바꾸기
//		int itemCnt = itemCountInCart(itemName); // 여기부터 다시 하기
//		System.out.println("itemCnt : " + itemCnt);
//		System.out.println("amount : " + amount);
		
		user.cloneItemInCart(index).setAmount(amount);
		//user.cloneCart(itemIndex).setAmount(amount);
		
//		if(itemCnt >= amount) {
//			// count만큼 해당 아이템 지우기
//			int count = itemCnt - amount;
//			user.removeItem(itemName, count);
//		}
//		else {
//			// count만큼 해당 아이템 추가하기
//			int count = amount - itemCnt;
//			
//			for(int i = 0; i < count; i++)
//				user.userShopping(item, amount);
//		}
	}
	
	private void pay() {
		myCart();
		
		int index = inputNumber("item number") - 1;
		//int price = index * 
	}
	
	private void printMyPage() {
		System.out.println("1. 내 장바구니");
		System.out.println("2. 항목 삭제");
		System.out.println("3. 수량 수정");
		System.out.println("4. 결제");
	}
	
	private void runMyPage(int select) {
		if(select == 1)
			myCart();
		else if(select == 2)
			removeItemFromCart();
		else if(select == 3)
			updateAmount();
		else if(select == 4)
			pay();
	}
	
	private void myPage() {
		printMyPage();
		runMyPage(inputNumber(""));
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
//		else if(select == 5) {
//			FileManager fm = new FileManager();
//			fm.run();
//		}
	}
	
	private void printUserMenu() {
		System.out.printf("1. %s\n", log == -1 ? "회원가입" : "탈퇴");
		System.out.printf("2. %s\n", log == -1 ? "로그인" : "로그아웃");
		System.out.println("3. 쇼핑하기");
		System.out.println("4. 마이페이지");
		//System.out.println("5. 자동저장/자동로드");
		// ㄴ 회원가입/탈퇴/
		// ㄴ 로그인/로그아웃/
		// ㄴ 쇼핑하기/
		// ㄴ 마이페이지(내장바구니, 항복삭제, 수량수정, 결제)/
		// ㄴ 자동저장/자동로드
	}
	
	private void addItem() {
		String itemName = inputString("item name");
		int price = inputNumber("price");
		// 중복 검사
		Item item = im.findItemByItemName(itemName);
		
		if(item != null) {
			System.out.println("이미 존재하는 아이템입니다.");
			return;
		}
		
		if(price <= 0) {
			System.out.println("가격은 1원 이상을 입력하세요.");
			return;
		}
		
		im.createItem(itemName, price);
		System.out.println("아이템 추가 완료");
	}
	
	private void removeItem() {
		showAllItems();
		
		// 아이템 이름 입력받아서 존재하는 아이템이면 해당 아이템 지우기
		String itemName = inputString("item name");
		int index = im.getIndexByItemName(itemName);
		
		if(index == -1) {
			System.out.println("존재하지 않는 아이템입니다.");
			return;
		}
		
		im.removeItem(index);
		
		System.out.println("아이템 삭제 완료");
	}
	
	private void showAllItems() {
		System.out.println("<Item List>");
		
		for(int i = 0; i < im.getItemCount(); i++) {
			Item item = im.getItem(i);
			System.out.printf("%d) %s\n", i + 1, item.getName());
		}
	}
	
	private void updateItem() {
		showAllItems();
		
		int index = inputNumber("item number") - 1;
		String itemName = inputString("new name");
		
		if(index < 0 || index > im.getItemCount() - 1) {
			System.out.println("유효하지 않은 인덱스입니다.");
			return;
		}
		
		// 아이템 이름 변경
		im.updateItemName(index, itemName);
		System.out.println("아이템 수정 완료");
	}
	
	private void showSalesResult() {
		
	}
	
	private void runManagerItemMenu(int select) {
		if(select == 1)
			addItem();
		else if(select == 2)
			removeItem();
		else if(select == 3)
			updateItem();
	}
	
	private void printManagerItemMenu() {
		System.out.println("1. 아이템 등록");
		System.out.println("2. 아이템 삭제");
		System.out.println("3. 아이템 수정");
	}
	
	private void runManagerMenu(int select) {
		if(select == 1) {
			printManagerItemMenu();
			runManagerItemMenu(inputNumber(""));
		}
		else if(select == 2)
			showSalesResult();
	}
	
	private void printManagerMenu() {
		System.out.println("1. 아이템등록/삭제/수정/");
		System.out.println("2. 조회(총 매출)");
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
	
	private void saveAll() {
		FileManager fm = new FileManager();
		fm.run();
	}

	public void run() {
		while(true) {
			System.out.println("회원 " + um.getUserCount() + "명");
			System.out.println("log = " + log);
			System.out.printf("[%s Shop]\n", shopName);
			printMenu();
			int select = inputNumber("menu");
			runMenu(select);
			saveAll();
		}
	}
}