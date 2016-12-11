package guessIt;
import java.awt.geom.*;

/**
 * This class represents the arcs that will (hopefully) be used to create shapes
 * @author Hamza Hussain & Muhammad Bin Sanaullah
 * @date 27.04.2016
 * @version 1.00
 */
public class Line extends Line2D.Double
{
    //Properties
    Point2D.Double startPoint;
    Point2D.Double endPoint;
    DirectionVector normal;
    
    //Constructors
    public Line( Point2D.Double startPoint , Point2D.Double endPoint)
    {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        double x;
        double y;
        
        x = endPoint.getX() - startPoint.getX();
        y = endPoint.getY() - startPoint.getY();
        normal = new DirectionVector( new Point2D.Double (y , -x));
    }
    
    //Methods
    //Returns the startpoint
    public Point2D.Double getStartPoint()
    {
        return startPoint;
    }
    
    //Returns the endPoint
    public Point2D.Double getEndPoint()
    {
        return endPoint;
    }
    
    //Returns the  coordinate of the start point
    public double getStartPointX()
    {
        return startPoint.getX();
    }
    
    //Returns y-coordinate of start point of line
    public double getStartPointY()
    {
        return startPoint.getY();
    }
    
    //Returns x-coordinate of end point of line
    public double getEndPointX()
    {
        return endPoint.getX();
    }
    
    //Returns y-coordinate of end point of line
    public double getEndPointY()
    {
        return endPoint.getY();
    }
    
    //Calculates and returns the slope of the line
    public double getGradient()
    {
        return ( startPoint.getY() - endPoint.getY()) / ( startPoint.getX() - endPoint.getX());
    }
    
    //Calculates the y-intercept of the line and returns it
    public double getIntercept()
    {
        return ( getGradient() * ( -1) * ( startPoint.getX() ) ) + startPoint.getY();
    }
    
    //Returns the normal vector of the line
    public DirectionVector getNormal()
    {
        return normal;
    }
    
    //Returns the highest point on the line according to vertical line
    public Point2D.Double getHighestPoint()
    {
        Point2D.Double highest = new Point2D.Double( 0 , 0);
        if( startPoint.getX() > endPoint.getX())
        {
            highest.setLocation( startPoint.getX() , highest.getY());
        }
        else
        {
            highest.setLocation( endPoint.getX() , highest.getY());
        }
        if( startPoint.getY() > endPoint.getY())
        {
            highest.setLocation( highest.getX() , startPoint.getY());
        }
        else
        {
            highest.setLocation( highest.getX() , endPoint.getY());
        }
        return highest;
    }
    
    //Returns the lowest point on the line according to vertical line
    public Point2D.Double getLowestPoint()
    {
        Point2D.Double lowest = new Point2D.Double( 0 , 0);
        if( startPoint.getX() < endPoint.getX())
        {
            lowest.setLocation( startPoint.getX() , lowest.getY());
        }
        else
        {
            lowest.setLocation( endPoint.getX() , lowest.getY());
        }
        if( startPoint.getY() < endPoint.getY())
        {
            lowest.setLocation( lowest.getX() , startPoint.getY());
        }
        else
        {
            lowest.setLocation( lowest.getX() , endPoint.getY());
        }
        return lowest;
    }
    
    //Returns the string representation of the Line class
    public String toString()
    {
        return "from (" + startPoint.getX() + "," + startPoint.getY() + ") to (" + 
            endPoint.getX() + "," + endPoint.getY() + ")";
    }
}    