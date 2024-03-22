package shop;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> list;
	
	public Cart() {
		list = new ArrayList<Item>();
	}
	
	public void add(Item item) {
		list.add(item);
	}
	
	public void remove(int index) {
		list.remove(index);
	}
	
	public Item get(int index) {
		return list.get(index);
	}
	public int size() {
		return this.list.size();
	}
}
