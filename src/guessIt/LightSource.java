package guessIt;
import java.awt.geom.*;

/**
 * This class is the main game object of the program. It can be moved by the user and
 * controlled. It produce an inital ray that will interact with objects. It also has a
 * DircetionVector in order to determine the direction of the light source. 
 * @author Ali Semi YENIMOL & Hamza Hussain
 * @version 1.20
 * @date 27.04.2016
 */

public class LightSource
{
    //Properties
    Point2D.Double position;
    DirectionVector direction;
    Ray initialRay;
    
    //Constructors
    public LightSource( Point2D.Double position, DirectionVector direction) {
        this.position = position;
        this.direction = direction;
        initialRay = new Ray( position, direction);
    }
    //Methods
    /**
     * This method moves the light source to the desired point
     * @param p point to move
     */
    public void move( Point2D.Double position) {
        double positionX = 0;
        final double radius = 200;
        final double center = 300;
        if( position.getX() > center)
        {
            positionX = Math.pow( ( Math.pow( radius , 2) - Math.pow( position.getY() - center , 2)) , 0.5) + center;
        }
        else
        {
            positionX = (- 1 * Math.pow( ( Math.pow( radius , 2) - Math.pow( position.getY() - center , 2)) , 0.5)) + center;
        }
        this.setPosition( new Point2D.Double( positionX , position.getY()));
//        this.direction.setDirection( new Point2D.Double( 400 - positionX , 400 - position.getY()));
//        initialRay.setDirection( new Point2D.Double( 400 - positionX , 400 - position.getY()));
    }
    
    /**
     * This method changes the direction of the lightsource
     * @param angle rotating angle in degree
     */
    public void rotate( double angle) {
        double newX;
        double newY;
        Point2D.Double point;
        
        newX = (Math.cos( Math.toRadians( angle) ) * direction.getX() -  Math.sin( Math.toRadians( angle) ) * direction.getY());
        newY = (Math.sin( Math.toRadians( angle) ) * direction.getX() + Math.cos( Math.toRadians( angle) ) * direction.getY());
        point = new Point2D.Double( newX, newY);
        
        direction.setDirection( point);
        initialRay.setDirection( point);
    }
    
    /**
     * Returns the x coordinate of the position of the lightsource
     * @return position.getX() x coordinate
     */
    public double getPositionX() {
        return position.getX();
    }
    
    /**
     * Returns the x coordinate of the position of the lightsource
     * @return position.getX() x coordinate
     */
    public double getPositionY() {
        return position.getY();
    }
    
    /**
     * Returns the direction of the lightsource
     * @return direction direction vector
     */
    public  DirectionVector getDirection() {
        return this.direction;
    }
    
    /**
     * Returns the initial ray that leaves firstly from the light source
     * @return initialRay inital ray of the ligh source
     */
    public Ray getRay() {
        return this.initialRay;
    }
    
    //Sets the porsition of the lightsource
    public void setPosition( Point2D.Double position)
    {
        this.position = position;
        initialRay.setStartPoint( position);
    }
}