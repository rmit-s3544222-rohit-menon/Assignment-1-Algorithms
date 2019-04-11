import java.io.*;
import java.util.*;

public class Dgen {

	private static Formatter x;
	public static void open() {
		try {
			x = new Formatter("dg1.in");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addRobots() {
		
		x.format("%s%n","A robot");
		
	}
	
	public static void addTables() {
		x.format("%s%n","A table");
	}
	
	public static void addComputer() {
		x.format("%s%n","A computer");
	}
	
	public static void pq() {
		x.format("%s%n","P");
		x.format("%s%n","Q");
	}
	public static void closeFile() {
		x.close();
	}
	
	public static void main(String[] args) {
		
		open();
		for (int i = 0; i < 100000;i++) {
			addRobots();
		}
		for (int i = 0; i < 40000;i++) {
			addTables();
		}
		for (int i = 0; i < 30000;i++) {
			addComputer();
		}
		pq();
		closeFile();
			
	}
}
