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
	
	public void removeUser(int index) {
		users.remove(index);
	}
	
	public User findUserByLog(int log) {
		return users.get(log);
	}
	
	public int getUserLogByIdAndPassword(String id, String pw) {
		int log = -1;
		for (int i = 0; i < getUserCount(); i++) {
			User user = users.get(i);
			if(user.getId().equals(id) && user.getPassword().equals(pw))
				log = i;
		}
		return log;
	}
	
	public User findUserById(String id) {
		for (User user : users) {
			if (user.getId().equals(id))
				return user.clone();
		}
		return new User();
	}
	
	public int getUserCount() {
		return this.users.size();
	}
}
