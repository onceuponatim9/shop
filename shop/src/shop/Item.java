package shop;

public class Item {
	private String name;
	private int amount;
	
	public Item() {
		
	}
	
	public Item(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public Item clone() {
		return new Item(this.name, this.amount);
	}
}
