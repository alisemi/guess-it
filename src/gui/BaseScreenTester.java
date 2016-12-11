package gui;

import javax.swing.JFrame;

/**
 * This is a testers class for BaseScreen
 * @author Ali Semi YENIMOL
 * @date 01.05.2016
 * @version 1.00
 */
public class BaseScreenTester {
	public static void main( String [] args) {
		//Create and set up the window.
        JFrame frame = new JFrame("Guess It");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        BaseScreen demo = new BaseScreen();
        demo.addComponentToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
}
