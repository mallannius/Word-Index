package ie.gmit.dip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class runner {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter a valide e-book file : ");
		String file = s.nextLine();
		System.out.println(file);
		File f = new File(file);
		if(f.exists() && !f.isDirectory()) { 
			System.out.println("\n\nGenerating index for this book ...");
			BookIndex bk = new BookIndex (file);		
		}else{
			System.out.println("You did not enter a valid file name!");
		}

	}

}
