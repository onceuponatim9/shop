package shop;

public class User {
	private String name, id, password;
	private Cart cart;
	
	public User() {
		
	}
	
	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public User clone() {
		return new User(this.name, this.id, this.password);
	}
	
	public void runMenu() {
		
	}
}
