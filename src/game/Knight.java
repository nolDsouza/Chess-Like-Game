package game;

import javax.swing.ImageIcon;

public class Knight extends Piece {

	public Knight(boolean isFirst) {
    	super(isFirst);
    	if (isFirst)
    		super.icon = new ImageIcon("knight1.png");
    	else
    		super.icon = new ImageIcon("knight2.png");
	}
	
	@Override
	public void getValidMoves() {
		//test(1, 2);  up 2 right 1
		test(1, 2);
		test(2, 1);
		test(-1, 2);
		test(1, -2);
		test(-1, -2);
		test(-2, -1);
		test(-2, 1);
		test(2, -1);
	}

}
