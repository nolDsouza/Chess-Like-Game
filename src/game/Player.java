package game;
import java.util.Scanner;

import ui.Print;
import user.Account;

public class Player {
	private int score, tokens=1, pieces = 6;
	private int desiredMoves;
	private boolean isFirst, crippled;
	private Account account;
	private Piece[] suite = new Piece[6];
	
	public Player(boolean isFirst) {
		this.isFirst = isFirst;
		score = 0;
		suite[0] = new Rook(isFirst);
    	suite[1] = new Bishop(isFirst);
    	suite[2] = new Knight(isFirst);
    	suite[3] = new Knight(isFirst);
    	suite[4] = new Bishop(isFirst);
    	suite[5] = new Rook(isFirst);
	}

	// Init player by asking to login or else register

	public boolean initPlayer(Scanner input) {
		

		int option;

		// Ask player to login
		Print.text("1. Login Account");
		Print.text("2. Register Account");
		Print.text("3. Exit");

		do {
			Print.text("Please choose your option: ", false);

			option = Integer.parseInt(input.nextLine());

			Print.newLine();

			if (option == 1) {
				Account newAccount = Account.loginAccount(input);

				if (newAccount != null) {
					this.account = newAccount;
					return true;
				}
			}
			else if (option == 2) {
				Account newAccount = Account.registerAccount(input);

				if (newAccount != null) {
					this.account = newAccount;
					return true;
				}
			}
			else if (option == 3) {
				System.exit(0);
			}
			else {
				Print.text("Error: Input is invalid please enter a number between 1 - 3.");
			}
		}
		while (option != 3);

		return false;
	}
	
	public void setTokens(int n) {
		this.tokens = n;
	}
	
	public int getTokens() {
		return tokens;
	}
	
	public void addToken() {
		tokens++;
	}
	
	public void removeToken() {
		tokens--;
	}
	
	public void deBuff() {
		this.crippled = true;
		suite[0].deBuff();
		suite[1].deBuff();
		suite[4].deBuff();
		suite[5].deBuff();
	}
	
	public void cleanse() {
		this.crippled = false;
		suite[0].cleanse();
		suite[1].cleanse();
		suite[4].cleanse();
		suite[5].cleanse();
	}
	
	public boolean getSide() {
		return isFirst;
	}
	
	public void setDesiredMoves() {
		System.out.println("How many moves would " + getName() + " like?");
		desiredMoves = GameAdmin.getInput();
	}
	
	public int getDesiredMoves() {
		return desiredMoves;
	}
	
	public String getName() {
		return account.getUserName();
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore() {
		score += 5;
	}
	
	public boolean countPieces() {
		pieces--;
		if (pieces == 0)
			return false;
		return true;
	}
	
	public void revertScore(int score) {
		this.score = score; 
	}
	
	public Piece[] getSuite() {
		return this.suite;
	}
}
