package shop;

import java.util.ArrayList;

public class User {
	private String name, id, password;
	private Cart cart;
	
	private ArrayList<Item> items;
	
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
	
	public void userShopping(int index, int amount) {
		Item item = items.get(index);
		
		for(int i = 0; i < amount; i++)
			cart.add(item);
	}
	
	public void runMenu() {
		
	}
}
