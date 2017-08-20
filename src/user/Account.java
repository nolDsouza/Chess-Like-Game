package user;

import java.util.ArrayList;
import java.util.Scanner;
import ui.Print;

public class Account {
	
	private String userName;
	private String password;
	private boolean logged;
	private static Scanner sc = new Scanner(System.in);
	
	public Account(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.logged = false;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	/*register user is a setter which gathers two
	 * inputs and assign's the userName and password
	 * the Account that was changed can be handled elsewhere
	 */ 
	@SuppressWarnings("resource")
	
	public String toString() {
		return (userName + ":" + password);
	}
	
	public boolean registerUser() {
		this.userName = null;
		this.password = null;
		
		System.out.println("Enter User name: ");		
													 
		userName = sc.next();						 
		if (userName.equals("e"))					 
			return false;
		
		System.out.println("Enter password: ");
		password = sc.next();
		if (password.equals("e"))
			return false;
		
		return true;
		
	}
	
	public boolean checkPassword() {
		System.out.println("Enter " + getUserName() 
			+ "'s password... hint: it's " + getPassword());
		String password = sc.next();
		if (password.equals("e"))
			return false;
		if (password.equals(this.password)) {
			return true;
		}
		System.out.println("Incorect password");
		if (checkPassword())
			return true;
		return false;
	}
	
	public static void removeAccount(ArrayList<Account> accounts) {
		
		if (accounts.size() == 0) {
			System.out.println("There are no accounts to remove");
			return;
		}
			
		for(int i = 0; i < accounts.size(); i++) {
            System.out.println(i+1 + ". " + accounts.get(i).getUserName());
        }
		
		String tempString = sc.next();
		
		if (tempString.equals("e"))
			return;
		
		for(int i = 1; i < accounts.size()+1; i++) {
			if (tempString.equals(String.valueOf(i))) {
				System.out.println("Removing " + accounts.get(i-1).getUserName());
				accounts.remove(i-1);
				return;
			}
		}
		
		System.out.println("That is not a valid option");
		removeAccount(accounts);
		
	}
	
	public boolean login() {
		if (!logged) {
			logged = true;
			return logged;
		}
		System.out.println("Account already logged in");
		return false;
	}
	
	public boolean isLogged() {
		return logged;
	}
	
	public static Account loginAccount(Scanner input) {
		boolean valid = false, exists = false;
		FileIOStream stream = new FileIOStream();
		ArrayList<Account> accounts = new ArrayList<>();
		String accUsername, accPassword;
		
		// Get accounts from accounts.txt
		accounts = stream.getAccounts();

		Print.text("Start Account Login");
		
		do {
			Print.text("Enter username: ", false);

			accUsername = input.nextLine();

			if (accUsername.equals("e")) {
				return null;
			}

			Print.text("Enter password: ", false);

			accPassword = input.nextLine();
			
			if (accUsername.equals("e")) {
				return null;
			}
			
			for (int i = 0; i < accounts.size(); i++) {
				if (accounts.get(i).getUserName().equals((accUsername)) && accounts.get(i).getPassword().equals((accPassword))) {
					Print.text("Notice: " + accUsername + " is now logged in.");
					exists = true;

					break;
				}
			}

			if (exists) {
				valid = true;
			}
			else {
				Print.text("Error: Invalid username or password.");
			}
		}
		while(!valid);

		Account newAccount = new Account(accUsername, accPassword);

		return newAccount;
	}
	
	public static Account registerAccount(Scanner input) {
		boolean valid = false, exists = false;
		FileIOStream stream = new FileIOStream();
		ArrayList<Account> accounts = new ArrayList<>();
		String accUsername, accPassword;
		
		// Get accounts from accounts.txt
		accounts = stream.getAccounts();

		Print.text("Start Accounts Registration");
		
		do {
			Print.text("Enter username: ", false);

			accUsername = input.nextLine();

			if (accUsername.equals("e")) {
				return null;
			}
			
			Print.text("Enter password: ", false);

			accPassword = input.nextLine();
			
			if (accUsername.equals("e")) {
				return null;
			}

			if (accUsername.equals("") || accPassword.equals("")) {
				Print.text("Error: Input is empty please try again.");
			}
			else {
				Print.newLine();

				for (int i = 0; i < accounts.size(); i++) {
					if (accounts.get(i).getUserName().equals((accUsername))) {
						exists = true;

						break;
					}
					else {
						exists = false;
					}
				}

				if (exists) {
					Print.text("Error: Username already exists, please try different username.");
				}
				else {
					valid = true;
				}
			}
		}
		while (!valid);
		
		// Create object and save into ArrayList
		Account newAccount = new Account(accUsername, accPassword);
		accounts.add(newAccount);

		// Save all accounts back to txt file
		stream.saveAccounts(accounts);

		Print.text("Success: Account has been registered.");
		Print.text("Notice: " + accUsername + " is now logged in.");
		Print.newLine();
		
		return newAccount;
	}
}
