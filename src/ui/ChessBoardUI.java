package ui;

import game.*;
import ui.Print;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
import javax.swing.border.*;
/* images taken from http://stackoverflow.com/questions/19209650/example-images-for-code-and-mark-up-qas
 * http://icons.mysitemyway.com/legacy-icon/009469-silver-inlay-square-metal-icon-arrows-arrow-undo/
 */
public class ChessBoardUI implements ActionListener, ChessBoard {

	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessRow;
	private Insets buttonMargin = new Insets(0,0,0,0);
	private JButton undo1 = null, undo2 = null, cripple1 = null, cripple2 = null;
	private JLabel firstScore, otherScore, turnString, moves;
	private static final String COLS = "ABCDEF";
	private boolean firstTurn = true;
	private Piece selectedPiece;
	private ChessOperator operator;
	int numMoves = 0, count = 0; 
	
	 	
	public ChessBoardUI(Player currentPlayer, Player otherPlayer, int numMoves) {
		operator = new ChessOperator(currentPlayer, otherPlayer);
		
		this.firstScore = new JLabel(String.format("%s's score: 0", currentPlayer.getName()));
		this.otherScore = new JLabel(String.format("%s's score: 0", otherPlayer.getName()));
		this.turnString = new JLabel(operator.tellTurn(firstTurn));
		this.numMoves = numMoves;
		this.moves = new JLabel(String.format("%d moves left", (numMoves)));
		initGUI(currentPlayer, otherPlayer);
	}

	public final void initGUI(Player currentPlayer, Player otherPlayer) {
		int boardLength = chessBoardSquares.length;

		initFeedbackUI();
		chessRow = new JPanel(new GridLayout(0, 7));
		chessRow.setBorder(new LineBorder(Color.BLACK));
		gui.add(chessRow);

		

		for (int i = 0; i < boardLength; i++) {
			for (int j = 0; j < boardLength; j++) {
				JButton button = new JButton();
				button.setMargin(buttonMargin);
				// Set icon
				if (i == 0) {
					board[i][j] = currentPlayer.getSuite()[j];
					ImageIcon icon = currentPlayer.getSuite()[j].setImg();
					button.setIcon(icon);
				}
				else if (i == 5) {
					board[i][j] = otherPlayer.getSuite()[j];
					ImageIcon icon = otherPlayer.getSuite()[j].setImg();
					button.setIcon(icon);
				}
				
				if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
					button.setBackground(Color.BLACK);
				}
				else {
					button.setBackground(Color.WHITE);
				}
				chessBoardSquares[j][i] = button;
				
				button.addActionListener(this); 
			}
		}

		// fill the black non-pawn piece row
		for (int i = chessBoardSquares.length - 1; i >= 0; i--) {
			for (int j = 0; j < chessBoardSquares.length; j++) {
				if (j == 0) {
					chessRow.add(new JLabel("" + (i + 1), SwingConstants.CENTER));
				}

				chessRow.add(chessBoardSquares[j][i]);
				
			}
		}

		//fill the chess board
		chessRow.add(new JLabel(""));
		// fill the top row
		for (int i = 0; i < 6; i++) {
			chessRow.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
		}
	}

	public final JComponent getChessBoard() {
		return chessRow;
	}

	public final JComponent getGui() {
		return gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
	  for (int i = 0; i < chessBoardSquares.length; i++) 
	  {
	    for (int j = 0; j < chessBoardSquares.length; j++) 
	     {
	       if(ae.getSource()==chessBoardSquares[i][j]) //chessBoardSquares[i][j] was clicked
	       {
	         
	             
	             if (board[j][i] == null) {
	            	 if(selectedPiece == null)
	            		 return;
	            	 else {
	            		 if (chessBoardSquares[i][j].getBackground() != Color.BLACK
	            				 && chessBoardSquares[i][j].getBackground() != Color.WHITE) {
	            			 
	            			 operator.makeMove(selectedPiece, i, j);
	            			 nextTurn();
	            		 }
	            	 }
            		 clear();
	             }
	             else if (board[j][i].getSide() == firstTurn) {
	            	 clear();
	            	 selectedPiece = board[j][i];
	            	 selectedPiece.setPosition(i, j);
	            	 selectedPiece.getValidMoves();
	            	 selectedPiece.tellPosition();
	             }
	             else if (chessBoardSquares[i][j].getBackground() == Color.orange) {
	            	 operator.capture(selectedPiece, i, j);
	            	 if (selectedPiece.getSide())
	            		 cripple1.setVisible(true);
	            	 else
	            		 cripple2.setVisible(true);
	            	 nextTurn();
            		 clear();
	             }
	             return;
	       }
	       
	     }
	  }
	  if (ae.getSource() == cripple1) {
		  if (!operator.applyDebuffs(false))
			  cripple1.setVisible(false);
		  nextTurn();
	 	  clear();
	  }
	  else if (ae.getSource() == cripple2) {
		  if (!operator.applyDebuffs(true))
			  cripple2.setVisible(false);
		  nextTurn();
 		  clear();
	  }
	  else revert(ae);
	}
	
	public void initFeedbackUI() {
		JPanel feedbackUI = new JPanel(new GridLayout(8, 10));
		
		gui.add(feedbackUI, BorderLayout.PAGE_START);
		feedbackUI.add(Box.createHorizontalStrut(10));
		feedbackUI.add(firstScore);
		
		feedbackUI.add(Box.createHorizontalGlue());
		feedbackUI.add(otherScore);
		
		feedbackUI.add(Box.createHorizontalGlue());
		feedbackUI.add(turnString);
		
		feedbackUI.add(Box.createHorizontalGlue());
		feedbackUI.add(moves);
		
		initUndoUi();
		
	}
	
	public void initUndoUi() {
		
	
		undo1 = new JButton();
		undo1.setPreferredSize(new Dimension(70, 0));
		undo1.setIcon(new ImageIcon("undo1.png"));
		undo1.addActionListener(this);
		
		undo2 = new JButton();
		undo2.setPreferredSize(new Dimension(70, 70));
		undo2.setIcon(new ImageIcon("undo2.png"));
		undo2.addActionListener(this);
		
		cripple1 = new JButton();
		cripple1.setPreferredSize(new Dimension(70, 70));
		cripple1.setIcon(new ImageIcon("cripple1.png"));
		cripple1.addActionListener(this);
		
		cripple2 = new JButton();
		cripple2.setPreferredSize(new Dimension(70, 70));
		cripple2.setIcon(new ImageIcon("cripple2.png"));
		cripple2.addActionListener(this);
		cripple2.setEnabled(false);
		
		JPanel undoUI = new JPanel(new GridLayout(7, 0));
		undoUI.add(undo1);
		undoUI.add(cripple2);
		undoUI.add(Box.createHorizontalGlue());
		undoUI.add(Box.createHorizontalGlue());
		undoUI.add(cripple1);
		undoUI.add(undo2);
		gui.add(undoUI, BorderLayout.EAST);			
		
	}
	
	public void revert(ActionEvent ae) {
		
		if (ae.getSource() == undo1 && ((count%2)!=0)) {
			operator.revert(false, cripple1, cripple2);
			count -= 2;
			undo1.setEnabled(false);
			moves.setText(String.format("%d moves left", (numMoves-count)));
		}
		else if (ae.getSource() == undo2 && ((count%2)==0)) {
			operator.revert(true, cripple1, cripple2);
			count -= 2;
			undo2.setEnabled(false);
			moves.setText(String.format("%d moves left", (numMoves-count)));
		}
	}
	
	public void nextTurn() {
		count++;
		this.firstTurn = !firstTurn;
		firstScore.setText(operator.getFirstScore());
		otherScore.setText(operator.getOtherScore());
		turnString.setText(operator.tellTurn(firstTurn));
		moves.setText(String.format("%d moves left", (numMoves-count)));
		
		cripple1.setEnabled(!cripple1.isEnabled());
		cripple2.setEnabled(!cripple2.isEnabled());
		
		if (count == numMoves) {
			operator.getWinner();
			
		}
	}
	
	
	public void clear() {

		for (int i = 0; i < chessBoardSquares.length; i++) {
			for (int j = 0; j < chessBoardSquares.length; j++) {
				if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
					chessBoardSquares[j][i].setBackground(Color.BLACK);
				}
				else {
					chessBoardSquares[j][i].setBackground(Color.WHITE);
				}
			}
		}
		selectedPiece = null;
	}
}