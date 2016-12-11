package gui;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import guessIt.Arc;
import guessIt.Line;
import guessIt.Shape;
import guessIt.ShapeContainer;

//will contain the choices of the shape
	/**
	 * the picture of choices can be shown on a button which will each hold the index of the shape in the array list
	 * if the user clicks the correct button the game will show the shape
	 * @author Ali Semi YENIMOL
	 * @date 29.04.2016
	 * @version 1.00
	 */
public class ChoicesPanel extends JPanel 
{	
	 final static double MINIMISATION_PROPORTION = 0.30;
	 final static Color backgroundColor = new Color( 58, 44, 49);
	 final static Color choiceShapeColor =  new Color(102, 255, 204);
	 final static Color highlightOuter = new Color(0, 0, 102);
	 final static Color highlightInner = new Color( 230, 230, 230);
	 final static Color shadowOuter = new Color( 153, 153, 255);
	 final static Color shadowInner = new Color(204, 204, 255);
	 
	 protected ChoiceLabel [][] choiceLabels;
	 ShapeContainer shapeContainer;
	 protected Shape choice;
	 protected int choicePages;
	 protected int currentPage;
	 protected int levelNo;

	 public ChoicesPanel( ShapeContainer shapeContainer, int levelNo) {
		  super();
		  this.shapeContainer = shapeContainer;
		  this.levelNo = levelNo;
		  
		  choicePages = shapeContainer.size() / 8;
		 	
		 
		  /*
		  if ( shapeContainer.size() % 8 != 0) {
		 		choicePages += 1;
		  }
		  */
		  currentPage = 1;
		  
		  //Initializing
		  choiceLabels = new ChoiceLabel[4][2]; 
		  choice = null;
		  
		  //Variables
		  GridLayout choiceLayout;
		  
		  //Border blackline = BorderFactory.createLineBorder(Color.black);
		  //setBorder( blackline);
		  int row;
		  int column;
		  int randomIndex;
		  
		  //Panel Settings and layout
		  choiceLayout = new GridLayout(4,2);
		  choiceLayout.preferredLayoutSize(this);
		  choiceLayout.layoutContainer(this);
		  setLayout (choiceLayout);
		  setPreferredSize(new Dimension( 200, 400 ) );
		  setSize( 200, 400);
		  setBackground( backgroundColor);
		  setMinimumSize( getPreferredSize() );
		  setMaximumSize( getPreferredSize() );
		  
		  row = 0;
		  column = 0;
		  if( levelNo == InformationPanel.FREE_PLAY) {  
			  for( int i = 0;  i < 8; i ++) { 
				  if( i >= shapeContainer.size() ){
					  choiceLabels[row][column] = new ChoiceLabel( new Shape( false, new Line[0], new Arc[0] ) );
					  
				  }
				  else {
					  choiceLabels[row][column] = new ChoiceLabel( shapeContainer.getShape(i) );		  	  
				  }
				  add(choiceLabels[row][column] );
				  if( i % 2 == 0){
					  column += 1;
				  }
				  else {
					  column -= 1;
				  	  row += 1;
				  }
				  
			  }
		  }
		  
		  else {
			  /*
			  for( int i = 0;  i < 8; i ++) {
				  randomIndex = (int) ( Math.random() * shapeContainer.size() );
				  choiceLabels[row][column] = new ChoiceLabel( shapeContainer.getShape(randomIndex) );		  	  
				  add(choiceLabels[row][column] );
				  if( i % 2 == 0){
					  column += 1;
				  }
				  else {
					  column -= 1;
				  	  row += 1;
				  }
				  
			  }
			  row = (int) (Math.random() * 4);
			  column = (int) (Math.random() * 2);
			  remove( choiceLabels[row][column] );
			  choiceLabels[row][column] = new ChoiceLabel ( shapeContainer.getShape(levelNo) );
			  add( choiceLabels[row][column] );
			  */
			  for( int i = 0;  i < 8; i ++) { 
			      if( i >= shapeContainer.size() ){
			    	  choiceLabels[row][column] = new ChoiceLabel( new Shape( false, new Line[0], new Arc[0] ) );
			      }
			      else {
			    	  choiceLabels[row][column] = new ChoiceLabel( shapeContainer.getShape(i) );       
			      }
			      add(choiceLabels[row][column] );
			      if( i % 2 == 0){
			    	  column += 1;
			      }
			      else {
			    	  column -= 1;
			         row += 1;     
			      }
			  }
		  }
	 }
	 
	 //Methods
	 //This method returns the shape from the panel according to the user preference
	 public Shape getShape() {
		 return choice;
	 }
	 
	 @Override 
	 public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 
		 /* Unnecessary
		 int row;
		 int column;
		 row = 0;
		 column = 0;
		 for( int i = 0;  i < 8; i ++) { 
			  if( i % 2 == 0){
				  choiceLabels[row][column].repaint();
			  	  column += 1;
			  }
			  else {
				  choiceLabels[row][column].repaint();
			  	  column -= 1;
			  	  row += 1;
			  }	  		
		 }
		 */
	 }
	 
	 public void changeChoices() {
		  int row;
		  int column;

		  repaint();
		  //System.out.println("Label1");//DEBUGGING
		  

		  row = 0;
		  column = 0;
		  for( int i = 0;  i < 8; i ++) { 
			  remove(choiceLabels[row][column] );
			  if( i % 2 == 0){
				  column += 1;
			  }
			  else {
				  column -= 1;
			  	  row += 1;
			  }  
		  }

		  /*
		  row = 0;
		  column = 0;
		  for( int i = currentPage * 8;  i < currentPage * 8 + 8; i ++) { 
			  if( i >= shapeContainer.size() ){
				  System.out.println("Label2");//DEBUGGING
				  choiceLabels[row][column] = new ChoiceLabel( new Shape( false, new Line[0], new Arc[0] ) );
				  add(choiceLabels[row][column] );
			  }
			  else if( i % 2 == 0){
				  System.out.println("Label3");//DEBUGGING
				  choiceLabels[row][column] = new ChoiceLabel( shapeContainer.getShape(i) );
				  add( choiceLabels[row][column] );
				  choiceLabels[row][column].repaint();
			  	  column += 1;
			  }
			  else {
				  System.out.println("Label4");//DEBUGGING
				  choiceLabels[row][column] = new ChoiceLabel( shapeContainer.getShape(i) );
				  add(choiceLabels[row][column]);
				  choiceLabels[row][column].repaint();
			  	  column -= 1;
			  	  row += 1;
			  }	  
		  }
		  */
		  row = 0;
		  column = 0;  
		  for( int i = currentPage * 8 ;  i < currentPage * 8 + 8; i ++) { 
			  if( i >= shapeContainer.size() ){
				  choiceLabels[row][column] = new ChoiceLabel( new Shape( false, new Line[0], new Arc[0] ) );
				  
			  }
			  else {
				  choiceLabels[row][column] = new ChoiceLabel( shapeContainer.getShape(i) );		  	  
			  }
			  add(choiceLabels[row][column] );
			  if( i % 2 == 0){
				  column += 1;
			  }
			  else {
				  column -= 1;
			  	  row += 1;
			  }
			  
		  }
		  if( currentPage == choicePages){
				currentPage = 0;
				//System.out.println("Page changing label");//DEBUGGING
		  }
			
		  else {
				//System.out.println("Page changing label2");//DEBUGGING
				currentPage += 1;
		  }
		  revalidate();
		  repaint();
	 }
	 
	 public void refreshChoices() {
		  int row;
		  int column;
		  int randomIndex;

		  repaint();

		  row = 0;
		  column = 0;
		  for( int i = 0;  i < 8; i ++) { 
			  remove(choiceLabels[row][column] );
			  if( i % 2 == 0){
				  column += 1;
			  }
			  else {
				  column -= 1;
			  	  row += 1;
			  }  
		  }
		  
		  row = 0;
		  column = 0;
		  for( int i = 0;  i < 8; i ++) {
			  randomIndex = (int) ( Math.random() * shapeContainer.size() );
			  choiceLabels[row][column] = new ChoiceLabel( shapeContainer.getShape(randomIndex) );		  	  
			  add(choiceLabels[row][column] );
			  if( i % 2 == 0){
				  column += 1;
			  }
			  else {
				  column -= 1;
			  	  row += 1;
			  }
			  
		  }
		  row = (int) (Math.random() * 4);
		  column = (int) (Math.random() * 2);
		  remove( choiceLabels[row][column] );
		  choiceLabels[row][column] = new ChoiceLabel ( shapeContainer.getShape(levelNo) );
		  add( choiceLabels[row][column] );
	 }
	 
	 //Inner class for the choice labels
	 protected class ChoiceLabel extends JLabel {
		  //Properties
		  Shape shape;

	  
		  public ChoiceLabel( Shape shape) {
			  super();
			  this.shape = shape;
			  Border labelBorder;
			  labelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, highlightOuter, highlightInner, shadowOuter, shadowInner);
			  setBackground( new Color(255, 255, 255) );
			  setPreferredSize( new Dimension(100,100) );
			  setMinimumSize( getPreferredSize() );
			  setMaximumSize( getPreferredSize() );
			  setSize(100,100);
			  setBorder( labelBorder);
			  repaint();
		  }
	
		  @Override
		  public void paintComponent( Graphics g) {
			   super.paintComponent(g);
			   Graphics2D g2;
			   Line[] lines;
			   int [] xPoints;
		       int [] yPoints;
			   
			   g2 = (Graphics2D) g;
			   lines = shape.getLines(); 
			   xPoints = new int [lines.length];
		       yPoints = new int [lines.length];
		       g2.setColor( choiceShapeColor);
		       for( int i = 0; i < lines.length; i++)
		       {
		           xPoints[i] = (int)( (lines[i].getStartPointX() - 150) *  MINIMISATION_PROPORTION ); 
		           yPoints[i] = (int)( (lines[i].getStartPointY() - 100) *  MINIMISATION_PROPORTION );
		       }
		       g2.fillPolygon( xPoints, yPoints, xPoints.length );
	   
		  }
		  
		  
		  @Override
	      public Dimension getPreferredSize() {
	          return new Dimension(100, 100);
	      }

	 }
	 
	 
	
}
