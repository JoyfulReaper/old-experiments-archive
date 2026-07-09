package com.kgivler.GuiGuess;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GuessViewer {
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GuessFrame frame = new GuessFrame();
                frame.setTitle("KGD Guess");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}
