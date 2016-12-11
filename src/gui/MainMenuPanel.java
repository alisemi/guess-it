package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;

import guessIt.Shape;
import guessIt.ShapeContainer;
import guessIt.ShapeCreator;

public class MainMenuPanel extends JPanel
{
  //variables
  private final int WIDTH = 800, HEIGHT = 700;
  private Image background;
  private ImageIcon backgroundIcon;
  JButton play,
  		freePlay,
  		help,
  		credits,
  		exit;
  
  /*
  //listener added to the buttons
  class listener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      
      if (event.getSource() == play)
      {
    	  ShapeCreator creator =  new ShapeCreator();
          creator.create();
          ShapeContainer container = creator.getContainer();
          Shape shape = container.getShape(0);    
          JFrame frame = new JFrame();
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
          
          BaseGamePlayPanel firstTest = new BaseGamePlayPanel(container , levelNumber);
          frame.add( firstTest);
          frame.setPreferredSize( new Dimension( 800 , 800));
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setResizable( false);
          frame.pack();
          frame.setVisible( true);
       
      }
      
      if( event.getSource() == freePlay)
      {
    	  ShapeCreator creator =  new ShapeCreator();
          creator.create();
          ShapeContainer container = creator.getContainer();
          Shape shape = container.getShape(0);    
          JFrame frame = new JFrame();
          BaseGamePlayPanel firstTest = new BaseGamePlayPanel(container , -1);
          frame.add( firstTest);
          frame.setPreferredSize( new Dimension( 800 , 800));
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setResizable( false);
          frame.pack();
          frame.setVisible( true);
      }
      if (event.getSource() == help)
      {
        message.setText("Move the light source around to see how light behaves through");
        next.setText("space. Reflect it off the hidden object and try to guess its shape.");
        other.setText("Earn points for guessing it minimum time. Have fun!!");
      }
      if (event.getSource() == highScores)
      {
        message.setText("");
        next.setText("");
        other.setText("");
      }
      if (event.getSource() == credits)
      {
        message.setText("This program is a semester project of CS-102 at Bilkent University");
        next.setText("made by Hamza Hussain, Muhammad Bin Sanaullah, Ali Yenimol");
        other.setText("and Talha Rana. Instructors: David Davenport, Aytek Aman");
      }
      if (event.getSource() == exit)
      {
         int output = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","",JOptionPane.YES_NO_OPTION);

         if(output == JOptionPane.YES_OPTION)
         {
             message.setText("");
             other.setText("");
             next.setText("Thanks for playing!");
             System.exit(0);
         }
         else if(output == JOptionPane.NO_OPTION)
         {
             message.setText("");
             next.setText("");
             other.setText("");
             
         }
      }
    }
  }
  */
  
  //constructor
  public MainMenuPanel()
  {
	BasicButtonUI buttonUI;
    //ActionListener listen = new listener();
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    
    /*
    message = new JLabel("");
    next = new JLabel("");
    other = new JLabel("");
    
    message.setFont(new Font("Arial", Font.BOLD, 24));
    message.setForeground(Color.WHITE);
    
    next.setFont(new Font("Arial", Font.BOLD, 24));
    next.setForeground(Color.WHITE);
    
    other.setFont(new Font("Arial", Font.BOLD, 24));
    other.setForeground(Color.WHITE);
    */
    
    buttonUI = new CustomizedButtonUI();
    
    //play button and its features
    play = new JButton("Play");
    //play.addActionListener(listen);
    play.setPreferredSize(new Dimension(200,80) );
    play.setUI(buttonUI );
    play.setOpaque(false);
    play.setFocusPainted(false);
    play.setBorderPainted(false);
    play.setContentAreaFilled(false);
    play.setBackground(Color.BLUE);
    play.setForeground(Color.WHITE);
    
    freePlay = new JButton("Free Play");
    //freePlay.addActionListener( listen);
    freePlay.setPreferredSize(new Dimension(200 , 80));
    freePlay.setUI(buttonUI );
    freePlay.setOpaque(false);
    freePlay.setFocusPainted(false);
    freePlay.setBorderPainted(false);
    freePlay.setContentAreaFilled(false);
    freePlay.setBackground(Color.BLUE);
    freePlay.setForeground(Color.WHITE);
    //help button and its features
    help = new JButton("Help");
    //help.addActionListener(listen);
    help.setPreferredSize(new Dimension(200,80));
    help.setUI(buttonUI );
    help.setOpaque(false);
    help.setFocusPainted(false);
    help.setBorderPainted(false);
    help.setContentAreaFilled(false);
    help.setBackground(Color.BLUE);
    help.setForeground(Color.WHITE);
    
    
    //credits button and its features
    credits = new JButton("Credits");
    //credits.addActionListener(listen);
    credits.setPreferredSize(new Dimension(200,80));
    credits.setUI(buttonUI );
    credits.setOpaque(false);
    credits.setFocusPainted(false);
    credits.setBorderPainted(false);
    credits.setContentAreaFilled(false);
    credits.setBackground(Color.BLUE);
    credits.setForeground(Color.WHITE);
    
    //exit button and its features
    exit = new JButton("Exit");
    exit.setUI(buttonUI );
    exit.setOpaque(false);
    exit.setFocusPainted(false);
    exit.setBorderPainted(false);
    exit.setContentAreaFilled(false);
    exit.setPreferredSize(new Dimension(200,80));   
    exit.setBackground(Color.BLUE);
    exit.setForeground(Color.WHITE);
     
    add(play);
    add(freePlay);
    add(help);
    add(credits);
    add(exit);
    
    
    
    background = BaseScreen.loadImage("images/mainMenuBackground.jpg");
    background = background.getScaledInstance(800, 800, Image.SCALE_DEFAULT);
    backgroundIcon = new ImageIcon(background);
  }
  
  //draws the background image on the panel
  public void paintComponent(Graphics graphics)
  {
    super.paintComponent(graphics);
    backgroundIcon.paintIcon(this, graphics, 0, 0);
  }
  
  
}