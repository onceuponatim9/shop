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
}
