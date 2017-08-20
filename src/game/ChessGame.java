package game;
// Code referenced from http://stackoverflow.com/questions/21142686/making-a-robust-resizable-swing-chess-gui


import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import ui.*;


public class ChessGame {
	private Player currentPlayer, otherPlayer;
	private static int numMoves;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		Print.text("Welcome to the Chess like Game!");
	
		// Start chess game object
		ChessGame game = new ChessGame();
		
		// Start coding and testing using cmd
		game.currentPlayer = new Player(true);
		game.otherPlayer = new Player(false);

		Print.text("Start Player One");
		if (game.currentPlayer.initPlayer(input)) {
			Print.text("Start Player Two");
			if (game.otherPlayer.initPlayer(input)) {
				game.currentPlayer.setDesiredMoves();
				game.otherPlayer.setDesiredMoves();
				numMoves = GameAdmin.calculateMoves(game.currentPlayer.getDesiredMoves(), game.otherPlayer.getDesiredMoves());
				Runnable run = new Runnable() {
					@Override
					public void run() {
						game.startGame();
					}
				};

				SwingUtilities.invokeLater(run);
			}
		}
	}

	public void startGame() {

		Print.text("Game has started!");

		ChessBoardUI chessBoard = new ChessBoardUI(currentPlayer, otherPlayer, numMoves);

		JFrame f = new JFrame("Chess Like Game");
		f.add(chessBoard.getGui());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLocationByPlatform(true);

		// ensures the frame is the minimum size it needs to be
		// in order display the components within it
		f.pack();
		// ensures the minimum size is enforced.
		f.setMinimumSize(f.getSize());
		f.setVisible(true);
	}


	public boolean isGameFinished() {
		return true;
	}
}