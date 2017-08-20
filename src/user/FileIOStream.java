package user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;



public class FileIOStream {
	
	public String toString(Account a) {
		// These methods will use preset methods to return attributes that can convey relevant information in a string form
		String tempString = (a.getUserName() + ":" + a.getPassword());
		return tempString;
	}
	
	public void addToFile(ArrayList<Account> accounts) {
		String tempString = null;
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter outStream = null;
		
		try {
		// create a new object of the printWriter class and give reference to the text file to be saved on
		fw = new FileWriter("accounts.txt", true);
	    bw = new BufferedWriter(fw);
		outStream = new PrintWriter(bw);
		// append / write data to the file.
		for(int i = 0; i < accounts.size(); i++) {	// Using the for loop to print all indexes to text file.
				tempString = accounts.get(i).toString();	
				outStream.println(tempString);		// Writing each toString() String to the text file on a new line		
		}
		outStream.close();	// close stream
		}
		catch(FileNotFoundException e)
		{
		// display the inbuilt error message belonging to exception
		System.out.println(e);
		}// end try catch
		catch (IOException ioe) {
			 System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	public ArrayList<Account> readFromFile() {		    // This will take all information from the toString() methods, now in the specific text file and convert it back into the Account objects
		ArrayList<Account> accounts = new ArrayList<Account>();	// Return type is an Account array
		Account a = null;					    // A generic account, which will be assigned to create holdings and store into the array
		try {
			File file = new File("accounts.txt");
            Scanner inStream = new Scanner(file);		// Scanner to read the text file 
            int i = 0;
            
            while(inStream.hasNextLine()) {
            	String line = inStream.nextLine();
            	String[] details = line.split(":");
            	String userName = details[0];			
                String password = details[1];
                
                a = new Account(userName, password);
                accounts.add(a);
            }
            inStream.close();
		}
		catch (FileNotFoundException e) { 
	           	System.out.println(e.getMessage());
        }
		return accounts;
	}
	
	public void writeToFile(ArrayList<Account> accounts) {
		String tempString = null;
		
		try {
		
		PrintWriter outStream = new PrintWriter("accounts.txt");
		
		for(int i = 0; i < accounts.size(); i++) {	// Using the for loop to print all indexes to text file.
			tempString = accounts.get(i).toString();	
			outStream.println(tempString);		// Writing each toString() String to the text file on a new line		
		}
		outStream.close();
		}
		catch(FileNotFoundException e)
		{
		System.out.println(e);
		}// end try catch
	}
	
	public ArrayList<Account> getAccounts() {   
		// This will take all information from the toString() methods, now in the specific text file and convert it back into the Account objects
		ArrayList<Account> accounts = new ArrayList<Account>();

		// Return type is an Account array
		Account a = null;

		// A generic account, which will be assigned to create holdings and store into the array
		try {
			File file = new File("accounts.txt");
            Scanner inStream = new Scanner(file);
            // Scanner to read the text file 
            int i = 0;
            
            while (inStream.hasNextLine()) {
            	String line = inStream.nextLine();
            	String[] details = line.split(":");
            	String username = details[0];			
                String password = details[1];

                a = new Account(username, password);
                
                accounts.add(a);
            }
            
            inStream.close();
		}
		catch (FileNotFoundException e) { 
           	System.out.println(e.getMessage());
        }

		return accounts;
	}
	
	public void saveAccounts(ArrayList<Account> accounts) {
		String tempString = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter outStream = null;
		
		try {
			// create a new object of the printWriter class and give reference to the text file to be saved on
			fw = new FileWriter("accounts.txt", true);
		    bw = new BufferedWriter(fw);
			outStream = new PrintWriter(bw);

			FileIOStream.clearAccounts();

			// append / write data to the file.
			for (int i = 0; i < accounts.size(); i++) {
				// Using the for loop to print all indexes to text file.
				tempString = this.toString(accounts.get(i));	
				outStream.println(tempString);
				// Writing each toString() String to the text file on a new line		
			}

			outStream.close();	// close stream
		}
		catch (FileNotFoundException e) {
			// display the inbuilt error message belonging to exception
			System.out.println(e);
		}
		catch (IOException ioe) {
			 System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	public static void clearAccounts() {
		try {
			File file = new File("accounts.txt");
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		}
		catch (FileNotFoundException e) {
			// display the inbuilt error message belonging to exception
			System.out.println(e);
		}
	}
	
	

}
