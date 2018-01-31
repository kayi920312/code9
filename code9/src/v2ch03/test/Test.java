package v2ch03.test;

import java.io.PrintWriter;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
		 PrintWriter pw = new PrintWriter(System.out, true);
		 
		 if(scanner.hasNextLine()){
			 pw.println(scanner.nextLine());
		 }
		
	}

}
