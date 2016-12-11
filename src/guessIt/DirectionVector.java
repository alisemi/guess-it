package guessIt;
import java.awt.geom.*;

/**
 * This class represents a direction vector, every object that has a direction is supposed to use this class for
 * mathematical calculations
 * @author Ali Semi YENIMOL
 * @date 27.04.2016
 * @version 1.00
 */
public class DirectionVector
{
    //Properties
    Point2D.Double direction;
    
    //Constructors
    public DirectionVector ( Point2D.Double direction) {
        this.direction = direction;
        double norm;
        norm = Math.sqrt( Math.pow( direction.getX() , 2) + Math.pow( direction.getY() , 2) );
        this.direction.setLocation(direction.getX() / norm , direction.getY() / norm ); 
    }
    
    //Methods
    /**
     * This method returns the x component of the vector
     * @return direction.getX() x coordinate of the point
     */
    public double getX() {
        return direction.getX();
    }
    
    /**
     * This method returns the y component of the vector
     * @return direction.getY() y coordinate of the point
     */
    public double getY() {
        return direction.getY();
    }
    
    /**
     * This method set the direction vector's parameters
     * @param direction direction point to sset
     */
    public void setDirection( Point2D.Double direction) {
        this.direction = direction;
    }
    
    /**
     * This method returns the norm of the vector
     * @return norm norm of the vector
     */
    public double getNorm() {
        double norm;
        norm = Math.sqrt( Math.pow( direction.getX() , 2) + Math.pow( direction.getY() , 2) );
        return norm;
    }
    
    /**
     * This method returns the slope of the vector
     * @return getY() / getX() y component divided by x component
     */
    public double getGradient() {
    	return getY() / getX();
    }
    
    //Returns the string representation of the vector
    public String toString() {
        return "x = " + direction.getX() + " y = " + direction.getY();
    }
}