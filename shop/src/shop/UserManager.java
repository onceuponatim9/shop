package shop;

import java.util.ArrayList;

public class UserManager {
	private static ArrayList<User> users = new ArrayList<User>();
	//private int userCount;
	
	public UserManager() {
		
	}
	
	public User createUser(String name, String id, String password) {
		User user = new User(name, id, password);
		users.add(user);
		return user.clone();
	}
	
	public static User findUserByLog(int id) {
		for (User user : users) {
			if (user.getId().equals(id))
				return user.clone();
		}
		return new User();
	}
	
//	public void setUserCount(boolean isAdd) {
//		if(isAdd)
//			this.userCount++;
//		else
//			this.userCount--;
//	}
	
	public int getUserCount() {
		return this.users.size();
	}
}
