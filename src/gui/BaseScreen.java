package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import guessIt.Shape;
import guessIt.ShapeContainer;
import guessIt.ShapeCreator;

/**
 * This class is the base screen which hold all panels itself and uses the Card layout to
 * provide the transitions between panels
 * @author Ali Semi YENIMOL
 * @date 01.05.2016
 * @version 1.00
 */
public class BaseScreen implements ActionListener {
	//Constants
	final static String MAIN_MENU = "Main Menu";
	final static String GAMEPLAY = "GamePlay Screen";
	final static String FREE_PLAY = "Free Play";
	final static String MENU_BUTTON = "Menu";
	final static String EXIT_BUTTON = "Exit";
	
	//Properties
	JPanel basePanel;
	MainMenuPanel mainMenuPanel;
	BaseGamePlayPanel gamePlayPanel;
	BaseGamePlayPanel freePlayPanel;
	JButton playButton;
	JButton freePlayButton;
	JButton menuButton1;
	JButton menuButton2;
	JButton exitButton;
	ShapeContainer container;
	Shape shape;
	//Constructor
	public BaseScreen() {
		//Variables

		ShapeCreator creator =  new ShapeCreator();
        creator.create();
        container = creator.getContainer();
        shape = container.getShape(0);
        
        basePanel = new JPanel( new CardLayout() );
		mainMenuPanel = new MainMenuPanel();
		gamePlayPanel = new BaseGamePlayPanel(container, 1);
		freePlayPanel = new BaseGamePlayPanel(container, InformationPanel.FREE_PLAY);
		playButton = mainMenuPanel.play;
		freePlayButton = mainMenuPanel.freePlay;
		menuButton1 = gamePlayPanel.infoPanel.menuButton;
		menuButton2 = freePlayPanel.infoPanel.menuButton;
		exitButton = mainMenuPanel.exit;
		
		basePanel.add(mainMenuPanel, MAIN_MENU);
		basePanel.add(gamePlayPanel, GAMEPLAY);
		basePanel.add(freePlayPanel, FREE_PLAY);
		playButton.addActionListener(this);
		freePlayButton.addActionListener(this);
		menuButton1.addActionListener(this);
		menuButton2.addActionListener(this);
		exitButton.addActionListener(this);
		 
	}
	//Methods
	@Override
	//Transitions Between Screens
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == mainMenuPanel.play ){
			gamePlayPanel.infoPanel.timer.restart();
	        int levelNumber = 0;
	        try
	        {
	         Scanner scan = new Scanner( new File( "level.txt"));
	         levelNumber = scan.nextInt();
	        }
	        catch( FileNotFoundException exception)
	        {
	         System.out.println( "Level File is missing");
	        }
	       
	        gamePlayPanel = new BaseGamePlayPanel(container , levelNumber);
	        menuButton1 = gamePlayPanel.infoPanel.menuButton;
	        menuButton1.addActionListener(this);
	        basePanel.remove(gamePlayPanel);
	        basePanel.add(gamePlayPanel, GAMEPLAY);
	        CardLayout cl = (CardLayout)(basePanel.getLayout() );
	        
	        cl.show(basePanel, GAMEPLAY);
		}
		else if( e.getSource() == mainMenuPanel.freePlay ){
			gamePlayPanel.infoPanel.timer.stop();
			CardLayout cl = (CardLayout)(basePanel.getLayout() );
		    cl.show(basePanel, FREE_PLAY);
		}
		else if( e.getSource() == menuButton1 ){
			gamePlayPanel.infoPanel.timer.restart();
			gamePlayPanel.infoPanel.timer.stop();
			CardLayout cl = (CardLayout)(basePanel.getLayout() );
		    cl.show(basePanel, MAIN_MENU);
		}
		else if( e.getSource() == menuButton2 ){
			CardLayout cl = (CardLayout)(basePanel.getLayout() );
		    cl.show(basePanel, MAIN_MENU);
		}
		else if( e.getSource() == mainMenuPanel.exit ){
			CardLayout cl = (CardLayout)(basePanel.getLayout() );
		    cl.show(basePanel, MAIN_MENU);
			int output = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","",JOptionPane.YES_NO_OPTION);

	         if(output == JOptionPane.YES_OPTION)
	         {
	             System.exit(0);
	         }
	         else if(output == JOptionPane.NO_OPTION)
	         {
	         }
		}
		
	}
	public void addComponentToPane(Container contentPane) {
		contentPane.add(basePanel, BorderLayout.CENTER);
		
	}
	
	//This method loads an image according to the desired file, file name should be passed
    //as a String parameter
    public static BufferedImage loadImage( String fileName) {
    	BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileName) );
        } catch (IOException e) {
        	System.out.println("lalala");
        }
        
        return img;
    }

}

