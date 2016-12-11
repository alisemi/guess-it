package guessIt;
import java.awt.geom.*;
/**
 * This class represents the arcs that will (hopefully) be used to create shapes
 * @author Hamza Hussain
 * @date 27.04.2016
 * @version 1.00
 */
public class Arc
{
    //Properties
    Point2D.Double startPoint;
    Point2D.Double endPoint;
    int width; //Represents the width of the rectangle that contains the arc
    int height;//Represents the height of the rectangle that contains the arc
    int startingAngle;//Angle from the x axes from where the arc starts
    int arcAngle;//angle of arc
    
    //Constructors
    public Arc( int width, int height, int startingAngle, int arcAngle,
               Point2D.Double startPoint, Point2D.Double endPoint)
    {
        this.width = width;
        this.height = height;
        this.startingAngle = startingAngle;
        this.arcAngle = arcAngle;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    
    //Returns width of the containing rectangle
    public int getWidth()
    {
        return width;
    }
    
    //Returns the height of the containing rectangle
    public int getHeight()
    {
        return height;
    }
    
    //Returns starting angle
    public int getStartingAngle()
    {
        return startingAngle;
    }
    
    //Returns arc angle
    public int getArcAngle()
    {
        return arcAngle;
    }
    
}