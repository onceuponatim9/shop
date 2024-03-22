package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private UserManager um = new UserManager();
	private ItemManager im = new ItemManager();
	
	private String fileName = "jang.txt";
	private String userFileName = "user.txt";
	private String ItemName = "item.txt";
	
	File userFile = new File(userFileName);
	File jangFile = new File(fileName);
	File itemFile = new File(ItemName);
	
	FileWriter fw = null;
	FileReader fr = null;
	BufferedReader br = null;
	
	private int count = 1;
	private int itemCount = 0;
	private int jangCount = 0;

	
	public FileManager() {
		
	}
	
	private void saveJangFile() {
		
	}
	
	private void saveUserFile() {
		
	}
	
	private void saveItemFile() {
		
	}
	
	private void loadJangFile() {
		
	}
	
	private void loadUserFile() {
		
	}
	
	private void loadItemFile() {
		
	}
	
	private void save() {
		saveJangFile();
		saveUserFile();
		saveItemFile();
	}
	
	private void load() {
		loadJangFile();
		loadUserFile();
		loadItemFile();
	}
	
	public void run() {
		save();
		load();
	}

}