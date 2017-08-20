package game;

import javax.swing.ImageIcon;

public class Reversion implements ChessBoard {
	private Piece[][] backBoard;
	private ImageIcon[][] backBoardIcons;
	
	public Reversion() {
		backBoard = new Piece[6][6];
		backBoardIcons = new ImageIcon[6][6];
	}
	
	public void encapsulate() {
		for (int i = 0; i < chessBoardSquares.length; i++) {
			for (int j = 0; j < chessBoardSquares.length; j++) {
				backBoard[j][i] = board[j][i];
				if (board[j][i] != null)
					backBoardIcons[j][i] = board[j][i].setImg(); 
			}
		}
	}
	
	public void revert() {
		
		for (int i = 0; i < chessBoardSquares.length; i++) {
			for (int j = 0; j < chessBoardSquares.length; j++) {
				board[j][i] = backBoard[j][i];
				if (board[j][i] != null)
					chessBoardSquares[i][j].setIcon(backBoardIcons[j][i]);
				else
					chessBoardSquares[i][j].setIcon(null);
			}
				
		}
	}
	
	
	
	
}
