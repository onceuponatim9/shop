package shop;

import java.util.ArrayList;

public class ItemManager {
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public ItemManager() {
		
	}
	
	public Item createItem(String itemName, int price) {
		Item item = new Item(itemName, 1);
		items.add(item);
		return item.clone();
	}
	
	public void removeItem(int index) {
		items.remove(index);
	}
	
	public void updateItemName(int index, String itemName) {
		Item item = items.get(index);
		item.setName(itemName);
	}
	
	public Item findItemByItemName(String itemName) {
		int index = getIndexByItemName(itemName);
		
		if(index == -1)
			return null;
		
		return items.get(index);
	}
	
	public int getIndexByItemName(String itemName) {
		int index = -1;
		for(int i = 0; i < getItemCount(); i++) {
			Item item = items.get(i);
			if(item.getName().equals(itemName))
				index = i;
		}
		return index;
	}
	
//	public int getIndexByItemName(String itemName) {
//		int index = -1;
//		for(int i = 0; i < getItemCount(); i++) {
//			Item item = items.get(i);
//			if(item.getName().equals(itemName))
//				index = i;
//		}
//		return index;
//	}
	
	public Item getItem(int code) {
		//return new Item();
		return items.get(code);
	}
	
	public int getItemCount() {
		return this.items.size();
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

}
