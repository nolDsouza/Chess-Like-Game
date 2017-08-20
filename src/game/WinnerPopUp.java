package game;

import javax.swing.JOptionPane;

public class WinnerPopUp {
	
	public static void infoBox(String infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

}
