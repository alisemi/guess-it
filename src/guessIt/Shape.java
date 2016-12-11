package guessIt;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * This class represents the Shapes in the game. Each shape consist of Lines and Arcs. 
 * @author Hamza Hussain & Mehmetali Semi YENIMOL
 * @date 27.04.2016
 * @version 1.30
 */
public class Shape
{
    //Properties
    int width;//Represents the width of the shape
    int height;//Represents the height of the shape
    boolean visible;//boolean representing weather the shape is visible or not.
    Line[] straightSides;//Array containing all the lines of the shape.
    Arc[] curledSides;//Array containing all the arcs of the shape
    Point2D.Double lowerPoint; 
    Point2D.Double upperPoint;
    
    //Constructors
    public Shape( boolean visible, Line[] straightSides, Arc[] curledSides)
    {
        this.visible = visible;
        this.straightSides = straightSides;
        this.curledSides = curledSides;
        lowerPoint = getLowerPoint();
        upperPoint = getUpperPoint();
        //For creating a shape which has no straightSides and curledSides( a null Shape)
        if( straightSides.length != 0){
        	width = (int)(upperPoint.getX() - lowerPoint.getX());//Calculates the width
            height = (int)(upperPoint.getY() - lowerPoint.getY());//Calculates the height
        }
    }
    
    //Methods
    //Calculates and returns the  highest point on the shape according to vertical line
    public Point2D.Double getUpperPoint()
    {  
    	//For creating a shape which has no straightSides and curledSides( a null Shape)
    	if( straightSides.length == 0) {
    		return null;
    	}
        Point2D.Double upperPoint = straightSides[0].getHighestPoint();
        
        for( int i = 1; i < straightSides.length; i++)
        {
            if( straightSides[i].getHighestPoint().getX() > upperPoint.getX())
            {
                upperPoint.setLocation( straightSides[i].getHighestPoint().getX() , upperPoint.getY());
            }
            if( straightSides[i].getHighestPoint().getY() > upperPoint.getY())
            {
                upperPoint.setLocation( upperPoint.getX() , straightSides[i].getHighestPoint().getY());
            }
        }
        return upperPoint;
    }
    
    //Calculates and returns the  lowest point on the shape according to vertical line
    public Point2D.Double getLowerPoint()
    {
    	//For creating a shape which has no straightSides and curledSides( a null Shape)
    	if( straightSides.length == 0) {
    		return null;
    	}
        Point2D.Double lowerPoint = straightSides[0].getLowestPoint();
        for( int i = 1; i < straightSides.length; i++)
        {
            if( straightSides[i].getLowestPoint().getX() < lowerPoint.getX())
            {
                lowerPoint.setLocation( straightSides[i].getLowestPoint().getX() , lowerPoint.getY());
            }
            if( straightSides[i].getLowestPoint().getY() < lowerPoint.getY())
            {
                lowerPoint.setLocation( lowerPoint.getX() , straightSides[i].getLowestPoint().getY());
            }
        }
        return lowerPoint;
    }
    
    //This method finds the intersectionPoint of ray on the shape, If it can't find any point, it returns null
    //The method first find all points that intersects with the algeabric equations of lines ( and hopefully arcs),
    //then checks if the points are on the shape and returns the nearest point the the ray
    public Point2D.Double getIntersectionPoint( Ray incident)
    {
        double gradientLine = 0;//Represents the gradient of the line currently being checked for intersection with the ray
        double gradientRay = 0;//Represents the gradient of the ray.
        double yInterceptLine = 0;//Represents the y intercept of the line currently being checked for intersection with the ray
        double yInterceptRay = 0;//Represents the y intercept of the ray
        double x = 0;//x coordinate of the point of intersection found.
        double y = 0;//y coordinate of the point of intersection found.
        ArrayList<Point2D.Double> unfiltered = new ArrayList<Point2D.Double>();//ArrayList containing all the unfiltered intersection point.
        Point2D.Double intersection = new Point2D.Double( 0 , 0);//Intersection Point
        Line line = null;//Line to be checked for intersection.
        
        for( int i = 0; i < straightSides.length; i++)
        {
            line = straightSides[i];
            gradientLine = straightSides[i].getGradient();
            gradientRay = incident.getGradient();
            yInterceptLine = straightSides[i].getIntercept();
            yInterceptRay = incident.getIntercept();
            
            //finds x and y coordinate of the intersection point when gradient is infinity.
            if( Double.isInfinite( gradientLine))
            {
                x = line.getStartPointX();
                y = gradientRay * x + yInterceptRay;
            }
            //finds x and y coordinate of the intersection point in other cases.
            else
            {
                x = (( yInterceptLine - yInterceptRay) / ( gradientRay - gradientLine));
                y = (( gradientLine * x) + yInterceptLine);
            }
            intersection = new Point2D.Double( x , y);//Creates a new intersectionPoint.
            
            //Checks if the intersection point lies within the bounds of the line.
            if( intersection.getX() <= upperPoint.getX() && intersection.getX() >= lowerPoint.getX() && intersection.getY() <= upperPoint.getY() && intersection.getY() >= lowerPoint.getY())
            {
                double highestPointX = line.getHighestPoint().getX();
                double highestPointY = line.getHighestPoint().getY();
                double lowestPointX = line.getLowestPoint().getX();
                double lowestPointY = line.getLowestPoint().getY();
                
                //if the intersection point lies within the bounds, adds it to the unfiltered array.
                if( highestPointX >= intersection.getX() && highestPointY >= intersection.getY() && lowestPointX <= intersection.getX() && lowestPointY <= intersection.getY())
                {
                    unfiltered.add( intersection);
                }
            }
        }
        
        //finds the closest intersection point to the light source.
        if( unfiltered.size() != 0)
        {
            intersection = unfiltered.get( 0);
            Point2D.Double intersection1 = unfiltered.get( 0);
            double shortestDistance = Math.pow( ( Math.pow( ( intersection.getX() - incident.getX()) , 2) + Math.pow( (intersection.getY() - incident.getY()) , 2)) , 0.5);
            double distance;
            for( int i = 1; i < unfiltered.size(); i++)
            {
                intersection1 = unfiltered.get( i);
                distance = Math.pow( ( Math.pow(( intersection1.getX() - incident.getX()) , 2) + Math.pow( (intersection1.getY() - incident.getY()) , 2)) , 0.5);
                if( distance < shortestDistance)
                {
                    shortestDistance = distance;
                    intersection = unfiltered.get( i);
                }
            }
        }
        //if there are no intersection points than return null.
        else
        {
            
            intersection = null;
        }
        
        //Last Conrol if the direction of the ray is in the reverse direction
        if( intersection != null) {
            double lengthTorchToIntersection = Math.sqrt( Math.pow( incident.getX() -  intersection.getX(), 2) + 
                                                         Math.pow(incident.getY() - intersection.getY(), 2) );
            double lengthRayToIntersection = Math.sqrt( Math.pow( incident.getX() + incident.getDirection().getX() -  intersection.getX(), 2) + 
                                                       Math.pow(incident.getY() + incident.getDirection().getY() - intersection.getY(), 2) );
            if( lengthTorchToIntersection <  lengthRayToIntersection) {
                intersection = null;
            }
        }
        
        return intersection;
        
    }
    
    //This method returns the line that has the intersectionPoint, if no Line founded, it returns null
    //Kinda solved the bug but still there might be another problem in further for different cases
    public Line getIntersectionLine( Point2D.Double intersection , Ray incident)
    {
        Line line;//Line currently being checked.
        Line intersectionLine = null;//Line on which the intersection point lies.
        
        for( int i = 0; i < straightSides.length; i++)
        {
            line = straightSides[i];
            if( Double.isInfinite( line.getGradient()))
            {
                if( line.getStartPointX() == intersection.getX())
                {
                    intersectionLine = line;
                }
            }
            else
            {
                
                if( Math.abs((line.getGradient() * intersection.getX() + line.getIntercept()) - intersection.getY()) < 0.000001)
                {
                    intersectionLine = line;
                }
            }
        }
        return intersectionLine;
    }
    
    //This method calculates and returns the reflected ray
    // TO DO try the recursive method for complicated (concave) shapes
    public Ray getReflectedRay( Ray incident)
    {
        Point2D.Double intersection = getIntersectionPoint( incident );
        Line intersectionLine = getIntersectionLine( intersection , incident);
        DirectionVector normalOfLine = intersectionLine.getNormal();
        //System.out.println( normalOfLine); //DEBUGGING
        double dotProduct;
        double xComponent;
        double yComponent;
        
        dotProduct = incident.getDirection().getX() * normalOfLine.getX() + 
            incident.getDirection().getY() * normalOfLine.getY();
        //Converting the normal vector to a unit vector
        dotProduct = dotProduct / (Math.pow( normalOfLine.getNorm(), 2)  );
        
        //Calculating the x component of the direction vector of reflectedRay
        xComponent = incident.getDirection().getX() - 2 * dotProduct * normalOfLine.getX();
        //Calculating the y component of the direction vector of reflectedRay
        yComponent = incident.getDirection().getY() - 2 * dotProduct * normalOfLine.getY();
        
        return new Ray( intersection, new DirectionVector( new Point2D.Double( xComponent, yComponent) ) );
        
//         double gradient = 0;
//         double angle = Math.PI + (-1 * Math.atan( incident.getGradient())) + ( 2 * Math.atan( intersectionLine.getGradient()));
//         System.out.println( angle);
//         gradient = -1 * Math.tan( +(-1 * Math.atan( incident.getGradient())) + ( 2 * Math.atan( intersectionLine.getGradient())));
//         System.out.println( gradient);
//         return new Ray( intersection , new DirectionVector( new Point2D.Double( 1 , gradient)));
//         //Trying Recursion Here
//         if( intersectionLine != null)
//         {
//             gradient = Math.tan( Math.atan( incident.getGradient()) - Math.atan( intersectionLine.getGradient()));
//             return getReflectedRay( new Ray( intersection , new DirectionVector( new Point2D.Double( 1 , gradient))) , center) ;
//         }
//         else
//             return new Ray( intersection , new DirectionVector( new Point2D.Double( 1 , gradient)));
//     }
    }
    
    //Checks if the shape is visible or not
    public boolean isVisible()
    {
    	return visible;
    }
    
    public void setVisible( boolean visibility)
    {
    	this.visible = visibility;
    }
    
    //Returns the lines in the shape
    public Line[] getLines()
    {
        return straightSides;
    }
}