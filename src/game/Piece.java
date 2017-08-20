package game;

import java.awt.Color;

import javax.swing.ImageIcon;

import game.Player;
import ui.Print;


public abstract class Piece implements ChessBoard {
    // private Coordinates[] validPaths;
    protected PieceType type;
    protected Coordinate position;
    protected boolean isFirst, crippled;
    protected Coordinate[] coord;
    protected Coordinate[] validMoves = new Coordinate[6];
    protected ImageIcon icon;
	
	public void deBuff() {
		this.crippled = true;
	}
	
	public void cleanse() {
		this.crippled = false;
	}
	
    public Piece(Boolean isFirst) {
    	this.isFirst = isFirst;
    }
    
    public Coordinate getPosition() {
    	return position;
    }
    
    public ImageIcon setImg() {
    	if (icon == null)
    		return null;
    	return icon;
    }
    
    public PieceType getType() {
    	return this.type;
    }

    public boolean isValidPath() {
    	return true;
    }
    
    public void setPosition(int width, int height) {
    	this.position = new Coordinate(height, width);
    }
    
    public boolean getSide() {
    	return isFirst;
    }
    
    public boolean test(int y, int x) {
    	final Color lightGreen = new Color(102, 204, 0);

		int height = position.height + y;
		int width = position.width + x;
		
		if (height >= 0 && height < chessBoardSquares.length) {
			if (width >= 0 && width < chessBoardSquares.length) {
				if (board[width][height] == null) {
					chessBoardSquares[height][width].setBackground(lightGreen);
					if (!crippled)
						return true;
					return false;
				}
				if (board[width][height].getSide() != isFirst) {
					chessBoardSquares[height][width].setBackground(Color.orange);
					return false;
				}
			}
		}
		return false;
		
    }
    
    public void move(int height, int width) {
    	chessBoardSquares[position.height][position.width].setIcon(null);
    	board[position.width][position.height] = null;
    	
    	position.height = height;
    	position.width = width;
    	
    	chessBoardSquares[height][width].setIcon(icon);
    	
    }
    
    public void tellPosition() {
    	System.out.printf("Height is %d, width is %d\n", position.height, position.width);
    }

    public abstract void getValidMoves();
}
