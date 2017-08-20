package game;

import java.awt.Color;

import javax.swing.ImageIcon;

import ui.Print;

public class Rook extends Piece {

    public Rook(boolean isFirst) {
    	super(isFirst);
    	if (isFirst)
    		super.icon = new ImageIcon("rook1.png");
    	else
    		super.icon = new ImageIcon("rook2.png");
    }
    
    public void getValidMoves()	{
    	if(test(0, -1))
    		test(0, -2);
    	
    	if(test(-1, 0))
    		test(-2, 0);
    	
    	if(test(0, 1))
    		test(0, 2);
    	
    	if(test(1, 0))
    		test(2, 0);
    		
    }
    
    public void setPosition() {
    	return;
    }
    
}
