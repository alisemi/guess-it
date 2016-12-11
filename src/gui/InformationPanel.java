package gui;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 * This class is the upper information panel on the game
 * @author Ali Semi YENIMOL
 * @date 29.04.2016
 * @version 1.00
 */

//TO DO try working on the layout, maybe use Box Layout?
public class InformationPanel extends JPanel{
	//Constants
	public static final int FREE_PLAY = -1;
	
	//Properties
	int levelNo;
	Timer timer;
	long startTime;
	JLabel levelName;
    Icon levelInfoIcon,
    	timerIcon,
    	buttonIcon;
    Image levelInfoImage,
    	timerImage,
    	buttonImage;
    JLabel timerLabel;
    JButton hintButton;
    JButton menuButton;
    JButton guessItButton;
    
	//Constructor
	public InformationPanel(int levelNo) {
		this.levelNo = levelNo;
		
		
		//Variables
		GridBagLayout layout;
		GridBagConstraints gbc;
		
		
		//Border blackline = BorderFactory.createLineBorder(Color.black);
		//setBorder( blackline);
		 
		//Settings of Panel
		layout = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout( layout );
		setPreferredSize( new Dimension( 400, 200 ) );
		setBackground( BaseGamePlayPanel.BACKGROUND_COLOR);
		setMinimumSize( getPreferredSize() );
		//Timer
		startTime = System.currentTimeMillis();
		ActionListener taskPerformer = new TimerListener();
	    timer = new Timer( 1000 , taskPerformer);
	    if( levelNo != FREE_PLAY)
	    {
	    	timer.start();
	    }
		
		//Level Label Image
		levelInfoImage = BaseScreen.loadImage("images/levelLabel.png");
        levelInfoImage = levelInfoImage.getScaledInstance(200, 50, Image.SCALE_DEFAULT);
        levelInfoIcon = new ImageIcon( levelInfoImage);
        
        //Timer Label Image
        timerImage = BaseScreen.loadImage("images/clock.png");
		timerImage = timerImage.getScaledInstance(100, 70, Image.SCALE_DEFAULT);
		timerIcon = new ImageIcon( timerImage);		
		
		if( levelNo == FREE_PLAY) {
			levelName = new JLabel( "Guess It - FreePlay", levelInfoIcon, JLabel.LEFT);
			timerLabel = null;
			hintButton = null;
			guessItButton = new JButton( "See More Shapes", buttonIcon);
			
		}
		
		else {
			levelName = new JLabel( "Guess It - Level " + (levelNo + 1), levelInfoIcon, JLabel.CENTER);
			//Hint Button
			hintButton = new JButton( "Hint", buttonIcon );
			hintButton.setUI(new CustomizedButtonUI() );
			hintButton.setOpaque(false);
	        hintButton.setFocusPainted(false);
	        hintButton.setBorderPainted(false);
	        hintButton.setContentAreaFilled(false);
			
			//Timer Label
			timerLabel = new JLabel(timerIcon, JLabel.CENTER);
			timerLabel.setText("00:00");
			timerLabel.setHorizontalAlignment(JLabel.CENTER);
			timerLabel.setHorizontalTextPosition(JLabel.CENTER);
			timerLabel.setFont(BaseGamePlayPanel.FONT);
			timerLabel.setForeground( Color.WHITE);
			//timerLabel.setBorder(blackline);
			guessItButton = new JButton( "Guess It", buttonIcon);			
		}
		
        levelName.setVerticalTextPosition(JLabel.CENTER);
        levelName.setHorizontalTextPosition(JLabel.CENTER);
        levelName.setFont(BaseGamePlayPanel.FONT);
        levelName.setForeground( Color.WHITE);
        
        //gbc.insets = new Insets(2,2,2,2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        //gbc.gridwidth = GridBagConstraints.RELATIVE;
        this.add( levelName, gbc);
        
        //Menu button
        menuButton = new JButton("Menu");
        menuButton.setUI( new CustomizedButtonUI() );
        menuButton.setPreferredSize(new Dimension(150,100));
        //menuButton.setOpaque(false);
        menuButton.setFocusPainted(false);
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setVerticalTextPosition(JLabel.CENTER);
        menuButton.setHorizontalTextPosition(JLabel.CENTER);
        //menuButton.addActionListener( new MyActionListener());
        gbc.gridwidth = 0;
        gbc.weightx = 0.5;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridy = 0;
        gbc.gridx = 2;
        this.add( menuButton, gbc);
        
        //Guess It Button - NonFuntional if it is not a free play
        gbc.gridy = 1;
        gbc.gridx = 2;
        guessItButton.setVerticalTextPosition(JLabel.CENTER);
        guessItButton.setHorizontalTextPosition(JLabel.CENTER);
        guessItButton.setUI( new CustomizedButtonUI() );
        guessItButton.setOpaque(false);
        guessItButton.setFocusPainted(false);
        guessItButton.setBorderPainted(false);
        guessItButton.setContentAreaFilled(false);
        guessItButton.setVerticalTextPosition(JLabel.CENTER);
        guessItButton.setHorizontalTextPosition(JLabel.CENTER);
        this.add(guessItButton, gbc);
        
        //Hint Button & Timer Label
        if( levelNo != FREE_PLAY ) {
        	gbc.anchor = GridBagConstraints.WEST;
        	gbc.gridy = 1;
        	gbc.gridx = 0;
	        this.add(hintButton, gbc);
	        gbc.anchor = GridBagConstraints.CENTER;
	        gbc.weightx = 1;
	        gbc.gridy = 1;
	        gbc.gridx = 0;
	        this.add(timerLabel, gbc);
        }
        
	}
	//Methods
	//Returns the level no
	public int getLevelNo() {
		return this.levelNo;
	}
	
	public void setLevelText( int levelNumber)
	{
		levelNo = levelNumber;
		levelName.setText("Guess It - Level " + (levelNo + 1) );
	}
	
	//Inner Class - Timer Listener
	public class TimerListener implements ActionListener
	 {
		  public void actionPerformed( ActionEvent e)
		  {
			   long minutes = 0;
			   long seconds =  System.currentTimeMillis() - startTime;
			   seconds = seconds / 1000;
			   if (seconds < 60)
			   { 
			    timerLabel.setText("00:" +seconds);
			   }
			   else 
			   {
			    minutes = seconds / 60;
			    timerLabel.setText("0" + minutes + ":" + seconds % 60);
			   }
		  }
	 }
	
}
