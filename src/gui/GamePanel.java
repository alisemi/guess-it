package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.imageio.ImageIO;

import guessIt.DirectionVector;
import guessIt.LightSource;
import guessIt.Line;	
import guessIt.Ray;
import guessIt.Shape;


/**
 * This class is the panel for the game play screen of the game
 * @author  Ali Semi YENIMOL & Hamza Hussain
 * @date 28.04.2016
 * @version 1.0
 */
public class GamePanel extends JPanel {
	final Color ELLIPSE_COLOR = new Color( 73, 93, 204);
	final Color SHAPE_COLOR = new Color (128, 223, 255);
	final Color LIGHT_COLOR = new Color( 255, 255, 0);
    final double centerX = 300;
    final double centerY = 300;
    final double radius = 200;
    
    //Properties
    JButton rotateClockW;
    JButton rotateCounterClockW;
    
    Image torchImage;
    Shape shape;
    LightSource torch;
    MouseMotionListener torchMover;
    ActionListener buttonListener;
    KeyListener torchMover1;
    
    //Constructor
    public GamePanel( Shape shape)
    {
        this.shape = shape;
        //Variables
        
        //Settings and layout
        setPreferredSize( new Dimension(550,550) );
        setSize( 550, 550);
        setMinimumSize( getPreferredSize() );
        
        //Border blackline = BorderFactory.createLineBorder(Color.black);
		//setBorder( blackline);
        
        //Initializing
        torch = new LightSource( new Point2D.Double( centerX - 300 , centerY) , new DirectionVector( new Point2D.Double( 1 , 0) ) );
        torchMover = new MyMouseListener();
        torchMover1 = new MyKeyListener();
        buttonListener = new ButtonListener();
        rotateClockW = new JButton( "Rotate ClockWise");
        rotateClockW.addActionListener( buttonListener);
        rotateCounterClockW = new JButton( "Rotate Counter Clock Wise");
        rotateCounterClockW.addActionListener( buttonListener);  
     
        
        //Torch Image
        torchImage = BaseScreen.loadImage("images/electricTorch.png");
        torchImage = torchImage.getScaledInstance(50, 20, Image.SCALE_DEFAULT);
        
        //Adding the components
        this.add( rotateClockW);
        this.add( rotateCounterClockW);
        this.setBackground( BaseGamePlayPanel.BACKGROUND_COLOR );
        addMouseMotionListener( torchMover);
        addKeyListener( torchMover1);
        this.setFocusable( true);
    }
    
    //Methods
    //Paint component methods which draw everything to the panel
    public void paintComponent( Graphics g)
    {    
        super.paintComponent( g);
        AffineTransform at;//For rotating and locating the movable shapes
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor( ELLIPSE_COLOR );
        Line[] lines = shape.getLines();
        Ray incident = torch.getRay();
        double theta;//For torch image
        
        //Drawing the oval aroud the shape
        float dash1[] = {10.0f};
        Stroke defaultStroke;
        
        defaultStroke = g2.getStroke();
        final BasicStroke dashed =
            new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        g2.setStroke(dashed);
        g2.drawOval( (int)(centerX - radius) , (int)(centerY - radius) , (int)(2 * radius) , (int)(2 * radius) );
        g2.setStroke( defaultStroke);
        g2.setStroke(new BasicStroke( 2f) );
        
        //Drawing the Shape
        g2.setColor( SHAPE_COLOR );
        int [] xPoints;
        int [] yPoints;
        
        if( shape.isVisible())
        {
        	xPoints = new int [lines.length];
        	yPoints = new int [lines.length];
        for( int i = 0; i < lines.length; i++)
        {
            xPoints[i] = (int)(lines[i].getStartPointX() ); 
            yPoints[i] = (int)(lines[i].getStartPointY() );
        }
        g2.fillPolygon( xPoints, yPoints, xPoints.length );
        }
        
        
        //Drawing the torch
        at = new AffineTransform();
        //TO DO problems with the location of the torch image when rotating it
        theta = Math.atan2(torch.getDirection().getY(),torch.getDirection().getX() );
        
        at.translate(torch.getPositionX() - 50 * Math.cos(theta) + 10 * Math.sin(theta) ,
        		torch.getPositionY() - 50 * Math.sin(theta) + 10 * Math.cos(Math.PI - theta) );
        at.rotate( torch.getDirection().getX(),torch.getDirection().getY() );
        g2.drawImage(torchImage, at, null);
        
        //Drawing the light beam
        g2.setColor( LIGHT_COLOR );
        if( shape.getIntersectionPoint( incident) != null)
        {
            int x = (int)shape.getIntersectionPoint( incident).getX();
            int y = (int)shape.getIntersectionPoint( incident).getY();
            Ray reflected = shape.getReflectedRay( incident);
            //Draws the ray up to the intersection point
            g.drawLine( (int)incident.getX() , (int)incident.getY() , x , y);
            //Draws the reflected ray
            g.drawLine( x , y ,(int) ( x + (reflected.getDirection().getX() * 100) ) , (int) ( y + (reflected.getDirection().getY() * 100) ));
//            System.out.println( shape.getIntersectionPoint( incident) );//DEBUGGING
            //Normal Vector at the interseciton point
//            double xNorm = shape.getIntersectionLine( shape.getIntersectionPoint( incident), incident).getNormal().getX();
//            double yNorm = shape.getIntersectionLine( shape.getIntersectionPoint( incident), incident).getNormal().getY();
//            g.drawLine( x, y, (int) ( x + xNorm * 100), (int) ( y + yNorm * 100) ); // Normal at the intersection point
        }
        else {
            g.drawLine( (int)incident.getX() , (int)incident.getY() , (int) (incident.getX() + incident.getDirection().getX() * 500 )  , (int) (incident.getY() + incident.getDirection().getY() *500 ) );
        }
    }
    

    //This method sets the shape of the game panel
    public void setShape( Shape shape) {
    	this.shape = shape;
    }
    
    //This method returns the shape
    public Shape getShape() {
    	return this.shape;
    }
    //Inner Class - mouseListener
    private class MyMouseListener implements MouseMotionListener
    {
        double previousX = torch.getPositionX();
        double previousY = torch.getPositionY();
        
        public void mouseMoved( MouseEvent e)
        {
            if( Math.sqrt( Math.pow( (e.getX() - centerX) , 2) + Math.pow( (e.getY() - centerY) , 2)) < 200)
            {
                torch.move( new Point2D.Double(e.getX() , e.getY() ) );
                previousX = torch.getPositionX();
                previousY = torch.getPositionY();
                repaint();
            }
        }
        public void mouseDragged( MouseEvent e)
        {
            double currentX = e.getX();
            double currentY = e.getY();
            if( currentY > previousY)
            {
                torch.rotate( Math.PI / 8 );
            }
            else
            {
                torch.rotate( -1 * Math.PI / 8 );
            }
            
            repaint();
        }
        public void mousePressed( MouseEvent e)
        {
            previousX = e.getX();
            previousY = e.getY();
        }
        public void mouseReleased( MouseEvent e){}
        public void mouseExited( MouseEvent e){}
        public void mouseEntered( MouseEvent e){}
        
        
        
    }
    
    public class MyKeyListener implements KeyListener
    {
        public void keyPressed( KeyEvent e)
        {
            int keyCode = e.getKeyCode();
            
            switch( keyCode)
            {
                case ( KeyEvent.VK_UP):
                
                    torch.rotate( Math.PI / 8 );
                    break;
                
                    
                case KeyEvent.VK_DOWN:
                
                    torch.rotate( -1 * Math.PI / 8 );
                    break;
            }
               repaint(); 
            }
            
            public void keyReleased( KeyEvent e){}
            public void keyTyped( KeyEvent e){}
        }
    private class ButtonListener implements ActionListener {
        public void actionPerformed( ActionEvent e) {
            if( e.getSource() == rotateClockW) {
                torch.rotate( Math.PI / 2);
            }
            else if( e.getSource() == rotateCounterClockW) {
                torch.rotate( -1 * Math.PI / 2);
            }
            repaint();
        }
    }
}
