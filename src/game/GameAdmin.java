package game;

import java.util.ArrayList;
import java.util.Scanner;

import user.Account;

public class GameAdmin {
	private Player currentPlayer, otherPlayer;
	private static int numMoves;
	private static Scanner admin = new Scanner(System.in);
	
	public void getMenu() {
		
	    System.out.println("=====================================");
	    System.out.println("|          MENU SELECTION           |");
	    System.out.println("=====================================");
	    System.out.println("| Options:                          |");
	    System.out.println("|        1. Register                |");
	    System.out.println("|        2. Remove Account          |");
	    System.out.println("|        3. Play Game               |");
	    System.out.println("|        4. Print all accounts      |");
	    System.out.println("|        5. Exit                    |");
	    System.out.println("=====================================");
	    System.out.println("Select option: ");
	    
	}
	
	public Account loginUser(ArrayList<Account> accounts) {
			
		System.out.println("Enter User name: ");
		String userName = admin.next();
		if (userName.equals("e"))
			return null;
		
		for(int i = 0; i < accounts.size(); i++) {
			if (userName.equals(accounts.get(i).getUserName())) {
				
				if (!accounts.get(i).checkPassword()) 
					loginUser(accounts);;
				
				if(accounts.get(i).login())
					return accounts.get(i);
				return loginUser(accounts);
			}
		}
		
		System.out.println("Invalid username or password");
		return loginUser(accounts);
		
	}
	
	public void startGame(ArrayList<Account> accounts) {
		while (true) {
			try { 
			//currentPlayer = new Player(true, loginUser(accounts).getUserName());
			//otherPlayer = new Player(false, loginUser(accounts).getUserName());
			currentPlayer.setDesiredMoves();
			otherPlayer.setDesiredMoves();
			calculateMoves(currentPlayer.getDesiredMoves(), otherPlayer.getDesiredMoves());
			
			System.out.println("first player: " + currentPlayer.getName());
			System.out.println("second player: " + otherPlayer.getName());
			System.out.println("Desired moves: " + numMoves);
			return;
			}
			catch (NullPointerException e){
		     	return;
			}
		}
	}
	
	public static int calculateMoves(int a, int b) {
		return (a+b)/2;
	}
	
	public static int getInput() {
		
		int input;
		
		String tempString = admin.next();
		if (tempString.equals("e"))
			return -1;
		
		try {
		    input = Integer.parseInt(tempString);

		} catch (NumberFormatException ex) {
		    System.out.println("Not a valid number!");
		    return  0;
		}
		return input;
	}
	

}
