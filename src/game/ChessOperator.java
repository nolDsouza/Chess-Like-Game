package game;

import javax.swing.*;

import ui.ChessBoardUI;

public class ChessOperator implements ChessBoard {
	private Player first, other;
	private Reversion firstReversion, otherReversion;
	private int firstTokens, otherTokens; 
	
	public ChessOperator(Player currentPlayer, Player otherPlayer) {
		this.first = currentPlayer;
		this.other = otherPlayer;
		
		firstReversion = new Reversion();
		otherReversion = new Reversion();
	}
	
	public void capture(Piece piece, int height, int width) {
		makeMove(piece, height, width);
		
		if (piece.getSide() == first.getSide()) {
			first.addScore();
			first.addToken();
			if (!other.countPieces())
				getWinner();
		}
		else if (piece.getSide() == other.getSide()) {
			other.addScore();
			if (!first.countPieces())
				getWinner();
		}
	}
	
	 public void makeMove(Piece piece, int height, int width) {
		 	if (piece.getSide()) {
		 		first.cleanse();
		 		encapsulate(true);
		 	}
		 	else {
		 		other.cleanse();
		 		encapsulate(false);
		 	}
		 
	    	chessBoardSquares[piece.getPosition().height][piece.getPosition().width].setIcon(null);
	    	board[piece.getPosition().width][piece.getPosition().height] = null;
	    	
	    	piece.getPosition().height = height;
	    	piece.getPosition().width = width;
	    	
	    	chessBoardSquares[height][width].setIcon(piece.setImg());
	    	board[width][height] = piece;
	    	
    }
	 
	public String getFirstScore() {
		return String.format("%s's score: %d", first.getName(), first.getScore());
	}
	
	public String getOtherScore() {
		return String.format("%s's score: %d", other.getName(), other.getScore());
	}
	 
	public String tellTurn(boolean turn) {
		String tempString;
		if (turn == first.getSide())
			tempString = first.getName();
		else 
			tempString = other.getName();
		
		return String.format("It's %s's turn", tempString);
		
	}
	
	public void encapsulate(boolean isfirst) {
		firstTokens = first.getTokens();
		otherTokens = other.getTokens();
		
		if (isfirst) {
			firstReversion.encapsulate();
		}
		else {
			otherReversion.encapsulate();
		}
	}
	
	public void revert(boolean isfirst, JButton c1, JButton c2) {
		first.setTokens(firstTokens);
		other.setTokens(otherTokens);
		
		System.out.printf("there are %d tokens", firstTokens);
		if (firstTokens > 0)
			c1.setVisible(true);
		else
			c1.setVisible(false);
		
		if (otherTokens > 0)
			c2.setVisible(true);
		else
			c2.setVisible(false);
		
		
		
		if (isfirst) {
			firstReversion.revert();
			this.first.revertScore(first.getScore());
		}
		else {
			otherReversion.revert();
			this.other.revertScore(other.getScore());
		}
		
		
		
	}
	
	public boolean applyDebuffs(boolean isfirst) {
		if (isfirst) {
			encapsulate(false);
			first.deBuff();
			other.removeToken();
			if (other.getTokens() == 0)
				return false;
		}
		else {
			encapsulate(true);
			other.deBuff();
			first.removeToken();
			if (first.getTokens() == 0)
				return false;
		}
		return true;
		
	}
	
	public void getWinner() {
		Player temp = null;
		if (first.getScore() > other.getScore())
			temp = first;
		else if (other.getScore() > first.getScore())
			temp = other;
		else 
			WinnerPopUp.infoBox("it's a draw!");
		
		WinnerPopUp.infoBox(String.format("%s is the winner!", temp.getName()));
	}

}
