package guessIt;
import java.awt.geom.*;

/**
 * This class represent the light beams in the game
 * @author Hamza Hussain & Ali Semi YENIMOL
 * @date 27.04.2016
 * @version 1.00
 */
public class Ray
{
    //Properties
    Point2D.Double startPoint;
    DirectionVector direction;
    
    //Constructors
    public Ray ( Point2D.Double startPoint, DirectionVector direction) {
        this.startPoint = startPoint;
        this.direction = direction;
    }
    
    //Methods
    /**
     * Returns the origin point of the ray
     * @return startPoint start point of the ray
     */
    public Point2D.Double getStartPoint() {
        return this.startPoint;
    }
    
    /**
     * Sets the starting point of the ray to the desired point
     * @param point point the setted
     */
    public void setStartPoint( Point2D.Double point) {
        this.startPoint = point;
    }
    
    /**
     * Returns the direction of the ray
     * @return direction direction of the ray
     */
    public DirectionVector getDirection() {
        return this.direction;
    }
    
    //Returns the x coordinate of the start point
    public double getX()
    {
        return startPoint.getX();
    }
    
    //Returns the y coordinate of the start point
    public double getY()
    {
        return startPoint.getY();
    }
    
    //Calculates and returns the slope value of the direction of the ray
    public double getGradient()
    {
        return ( direction.getY()) / ( direction.getX());
    }
    
    //Calculates and returns the y-intercept of the ray
    public double getIntercept()
    {
        return ( getGradient() * ( -1) * ( startPoint.getX())) + startPoint.getY();
    }
    
    //Sets the direction of the ray
    public void setDirection( Point2D.Double direction)
    {
        this.direction = new DirectionVector( direction);
    }
}