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
		cart = new Cart();
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
	
	public void userShopping(Item item, int amount) {
		for(int i = 0; i < amount; i++)
			cart.add(item);
	}
	
	public Item cloneCart(int index) {
		return cart.get(index);
	}
	
	public int getUserCartCount() {
		return this.cart.size();
	}
	
	public void runMenu() {
		
	}
}
