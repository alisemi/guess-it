package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import guessIt.ShapeContainer;
import java.awt.*;
/**
 * This class is the base game panel that holds all other panels related to game playing
 * @author Ali Semi YENIMOL
 * @date 29.04.2016
 * @version 1.00
 *
 */

//TO DO Layout :(
public class BaseGamePlayPanel extends JPanel{
	//Constants
	final static Color BACKGROUND_COLOR = new Color( 73, 55, 61);
	final static Font FONT = new Font("Arial Black", Font.PLAIN, 13);
	
	//Properties
    ChoicesPanel choicesPanel;
    InformationPanel infoPanel;
    GamePanel gamePlay;
    ShapeContainer container;
    int levelNumber;
    
	//Constructor
    public BaseGamePlayPanel(ShapeContainer container , int levelNumber) {
    	this.container = container;
    	this.levelNumber = levelNumber;
    	
    	//Variables
    	GridBagLayout layout;
		GridBagConstraints gbc;
    	
    	//Initializing
    	choicesPanel = new ChoicesPanel(container, levelNumber);
        infoPanel = new InformationPanel( levelNumber);
        if( levelNumber != -1)
        	gamePlay = new GamePanel( container.getShape(levelNumber));
        else
        	gamePlay = new GamePanel( container.getShape(0));
        layout = new GridBagLayout();
		gbc = new GridBagConstraints();
        
        //Settings
        //setLayout( new BorderLayout() );
		setLayout( layout);
        setBackground(BACKGROUND_COLOR );
        setPreferredSize( new Dimension( 800, 800) );
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        
        add(infoPanel, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(choicesPanel, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        add( gamePlay, gbc);
        //this.add(infoPanel, BorderLayout.NORTH);
        //this.add( gamePlay, BorderLayout.CENTER);
        //this.add(choicesPanel, BorderLayout.EAST);
        this.addMouseListener(new ChoiceListener() );
        infoPanel.guessItButton.addActionListener( new ButtonListener() );
        
        //For the free play
        if( infoPanel.getLevelNo() == InformationPanel.FREE_PLAY ){
        	gamePlay.getShape().setVisible(true);      
        }
        else {
        	gamePlay.getShape().setVisible(false);
        }
    }
    
    //Inner Class - MouseListener
	 public class ChoiceListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			//Setting the choice panel active
			int row;
			int column;
			Point point = e.getPoint();
			//System.out.println(point);//DEBUGGING
			point.setLocation(point.getX() - gamePlay.getWidth(), point.getY() - infoPanel.getHeight() );
			//System.out.println(point);//DEBUGGING
			
			row = (int) ( point.getY() / 150 );
			column = (int) ( point.getX() / 110 );
			if( row > 3)
				row = 3;
			if( column > 1)
				column = 1;
			//System.out.println(row); //DEBUGGING
			//System.out.println(column); //DEBUGGING
			if(choicesPanel.choiceLabels[row][column] != null) {
				choicesPanel.choice = choicesPanel.choiceLabels[row][column].shape;
				
				//For the free play
				if( infoPanel.getLevelNo() == InformationPanel.FREE_PLAY ){
					gamePlay.setShape(choicesPanel.getShape() );
					gamePlay.getShape().setVisible(true);      
		        }
				
				//For levels
			    else if(choicesPanel.getShape().equals( gamePlay.getShape() ) )
			    {
			     gamePlay.getShape().setVisible( true);
			     JOptionPane message = new JOptionPane();
			     message.showMessageDialog(new JFrame(), "You Have Guessed The Correct Shape");
			     try
			     {
			      PrintWriter out = new PrintWriter( "level.txt");
			      levelNumber = levelNumber + 1;
			      if( levelNumber < 8)
			      {
			       out.println( levelNumber);
			       out.close();
			      }
			      else
			      {
			       levelNumber = 0;
			       out.println( levelNumber);
			       out.close();
			       message.showMessageDialog(new JFrame(), "Congratulations, You have Finished The Game");
			      }
			      
			      //Checks if the user wants to proceed to the next level
			      
			      if( JOptionPane.showConfirmDialog(null,"Next Level", "Continue?", JOptionPane.YES_NO_OPTION ) == 0)
			      {
			        infoPanel.setLevelText( levelNumber);
			        gamePlay.getShape().setVisible( false);
			        gamePlay.setShape( container.getShape(levelNumber));
			        choicesPanel.levelNo = levelNumber;
			        //choicesPanel.refreshChoices();        
			      }
			  
			     }
			     catch( FileNotFoundException exception)
			     {
			      System.out.println( "level file not found");
			     }
			    }
			    else
			    {
			     JOptionPane message = new JOptionPane();
			     message.showMessageDialog(new JFrame(), "You Have Guessed The Wrong Shape");
			    }
			    repaint();
			    revalidate();
			   }
			
		}
	 }
	 
	 //Inner Class - Action Listener for the See More Shapes button
	 private class ButtonListener implements ActionListener
		{
			public void actionPerformed( ActionEvent e)
			{
				if( infoPanel.levelNo == InformationPanel.FREE_PLAY ){
					choicesPanel.changeChoices();					
				}
			}
		}
}
