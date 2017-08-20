package game;

import javax.swing.ImageIcon;

public class Bishop extends Piece {

    public Bishop(boolean isFirst) {
    	super(isFirst);
    	if (isFirst)
    		super.icon = new ImageIcon("bishop1.png");
    	else
    		super.icon = new ImageIcon("bishop2.png");
    }
    
	@Override
	public void getValidMoves() {
    	if(test(-1, -1))
    		test(-2, -2);
    	
    	if(test(-1, 1))
    		test(-2, 2);
    	
    	if(test(1, -1))
    		test(2, -2);
    	
    	if(test(1, 1))
    		test(2, 2);
	}

}