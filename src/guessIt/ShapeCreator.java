package guessIt;

import java.awt.geom.*;
/**
 * This class will be used to create shapes and put them in ShapeContainer
 * @author Hamza Hussain
 * @date 28.04.2016
 * @version 1.00
 */
public class ShapeCreator
{
    ShapeContainer container;
    
    //Constructor
    public ShapeCreator()
    {
        container = new ShapeContainer();
    }
    
    
  //Creates new Shapes and put them in the container
    public void create()
    {
        Line[] lines = new Line[4];
        Arc[] arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 200 , 200) , new Point2D.Double( 200 , 400));
        lines[1] = new Line( new Point2D.Double( 200 , 400) , new Point2D.Double( 400 , 350));
        lines[2] = new Line( new Point2D.Double( 400 , 350) , new Point2D.Double( 400 , 300));
        lines[3] = new Line( new Point2D.Double( 400 , 300) , new Point2D.Double( 200 , 200));
        Shape shape = new Shape( false , lines , arcs);
        container.add(shape);
        
        lines = new Line[3];
        arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 200 , 200) , new Point2D.Double( 400 , 300));
        lines[1] = new Line( new Point2D.Double( 400 , 300) , new Point2D.Double( 250 , 400));
        lines[2] = new Line( new Point2D.Double( 250 , 400) , new Point2D.Double( 200 , 200));
        shape = new Shape( false , lines , arcs);
        container.add(shape);
        
        lines = new Line[5];
        arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 250 , 200) , new Point2D.Double( 450 , 250));
        lines[1] = new Line( new Point2D.Double( 450 , 250) , new Point2D.Double( 350 , 400));
        lines[2] = new Line( new Point2D.Double( 350 , 400) , new Point2D.Double( 250 , 450));
        lines[3] = new Line( new Point2D.Double( 250 , 450) , new Point2D.Double( 200 , 250));
        lines[4] = new Line( new Point2D.Double( 200 , 250) , new Point2D.Double( 250 , 200));
        shape = new Shape( false , lines , arcs);
        container.add(shape);
        
        lines = new Line[4];
        arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 250 , 250) , new Point2D.Double( 250 , 350));
        lines[1] = new Line( new Point2D.Double( 250 , 350) , new Point2D.Double( 350 , 350));
        lines[2] = new Line( new Point2D.Double( 350 , 350) , new Point2D.Double( 350 , 250));
        lines[3] = new Line( new Point2D.Double( 350 , 250) , new Point2D.Double( 250 , 250));
        shape = new Shape( false , lines , arcs);
        container.add(shape);
        
        lines = new Line[4];
        arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 350 , 250) , new Point2D.Double( 400 , 300));
        lines[1] = new Line( new Point2D.Double( 400 , 300) , new Point2D.Double( 350 , 350));
        lines[2] = new Line( new Point2D.Double( 350 , 350) , new Point2D.Double( 300 , 300));
        lines[3] = new Line( new Point2D.Double( 300 , 300) , new Point2D.Double( 350 , 250));
        shape = new Shape( false , lines , arcs);
        container.add(shape);
        
        lines = new Line[4];
        arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 300 , 250) , new Point2D.Double( 350 , 300));
        lines[1] = new Line( new Point2D.Double( 350 , 300) , new Point2D.Double( 300 , 450));
        lines[2] = new Line( new Point2D.Double( 300 , 450) , new Point2D.Double( 250 , 300));
        lines[3] = new Line( new Point2D.Double( 250 , 300) , new Point2D.Double( 300 , 250));
        shape = new Shape( false , lines , arcs);
        container.add(shape);
        
        lines = new Line[4];
        arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 250 , 250) , new Point2D.Double( 450 , 250));
        lines[1] = new Line( new Point2D.Double( 450 , 250) , new Point2D.Double( 400 , 350));
        lines[2] = new Line( new Point2D.Double( 400 , 350) , new Point2D.Double( 200 , 350));
        lines[3] = new Line( new Point2D.Double( 200 , 350) , new Point2D.Double( 250 , 250));
        shape = new Shape( false , lines , arcs);
        container.add(shape);

	lines = new Line[8];
	arcs = new Arc[0];
	lines[0] = new Line( new Point2D.Double( 250 , 250) , new Point2D.Double( 300 , 250));
	lines[1] = new Line( new Point2D.Double( 300 , 250) , new Point2D.Double( 350 , 300));
	lines[2] = new Line( new Point2D.Double( 350 , 300) , new Point2D.Double( 350 , 350));
	lines[3] = new Line( new Point2D.Double( 350 , 350) , new Point2D.Double( 300 , 400));
	lines[4] = new Line( new Point2D.Double( 300 , 400) , new Point2D.Double( 250 , 400));
	lines[5] = new Line( new Point2D.Double( 250 , 400) , new Point2D.Double( 200 , 350));
	lines[6] = new Line( new Point2D.Double( 200 , 350) , new Point2D.Double( 200 , 300));
	lines[7] = new Line( new Point2D.Double( 200 , 300) , new Point2D.Double( 250 , 250));
	shape = new Shape( false , lines , arcs);
	container.add(shape);
        
        lines = new Line[6];
        arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 250 , 250) , new Point2D.Double( 350 , 250));
        lines[1] = new Line( new Point2D.Double( 350 , 250) , new Point2D.Double( 400 , 300));
        lines[2] = new Line( new Point2D.Double( 400 , 300) , new Point2D.Double( 350 , 350));
        lines[3] = new Line( new Point2D.Double( 350 , 350) , new Point2D.Double( 250 , 350));
        lines[4] = new Line( new Point2D.Double( 250 , 350) , new Point2D.Double( 200 , 300));
        lines[5] = new Line( new Point2D.Double( 200 , 300) , new Point2D.Double( 250 , 250));
        shape = new Shape( false , lines , arcs);
        container.add(shape); 

	lines = new Line[5];
        arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 350 , 200) , new Point2D.Double( 450 , 350));
        lines[1] = new Line( new Point2D.Double( 450 , 350) , new Point2D.Double( 400 , 400));
        lines[2] = new Line( new Point2D.Double( 400 , 400) , new Point2D.Double( 300 , 400));
        lines[3] = new Line( new Point2D.Double( 300 , 400) , new Point2D.Double( 250 , 300));
        lines[4] = new Line( new Point2D.Double( 250 , 300) , new Point2D.Double( 350 , 250));
        shape = new Shape( false , lines , arcs);
        container.add(shape);
        
        lines = new Line[6];
        arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 250 , 250) , new Point2D.Double( 350 , 250));
        lines[1] = new Line( new Point2D.Double( 350 , 250) , new Point2D.Double( 400 , 300));
        lines[2] = new Line( new Point2D.Double( 400 , 300) , new Point2D.Double( 400 , 400));
        lines[3] = new Line( new Point2D.Double( 400 , 400) , new Point2D.Double( 200 , 400));
        lines[4] = new Line( new Point2D.Double( 200 , 400) , new Point2D.Double( 200 , 300));
        lines[5] = new Line( new Point2D.Double( 200 , 300) , new Point2D.Double( 250 , 250));
        shape = new Shape (false , lines , arcs);
        container.add(shape);
        
        lines = new Line[12];
        arcs = new Arc[0];
        lines[0] = new Line( new Point2D.Double( 250 , 250) , new Point2D.Double( 300 , 200));
        lines[1] = new Line( new Point2D.Double( 300 , 200) , new Point2D.Double( 350 , 250));
        lines[2] = new Line( new Point2D.Double( 350 , 250) , new Point2D.Double( 400 , 250));
        lines[3] = new Line( new Point2D.Double( 400 , 250) , new Point2D.Double( 350 , 300));
        lines[4] = new Line( new Point2D.Double( 350 , 300) , new Point2D.Double( 400 , 350));
        lines[5] = new Line( new Point2D.Double( 400 , 350) , new Point2D.Double( 350 , 350));
        lines[6] = new Line( new Point2D.Double( 350 , 350) , new Point2D.Double( 300 , 400));
        lines[7] = new Line( new Point2D.Double( 300 , 400) , new Point2D.Double( 250 , 350));
        lines[8] = new Line( new Point2D.Double( 250 , 350) , new Point2D.Double( 200 , 350));
        lines[9] = new Line( new Point2D.Double( 200 , 350) , new Point2D.Double( 250 , 300));
        lines[10] = new Line( new Point2D.Double( 250 , 300) , new Point2D.Double( 200 , 250));
        lines[11] = new Line( new Point2D.Double( 200 , 250) , new Point2D.Double( 250 , 250));
        shape = new Shape( false , lines , arcs);
        container.add(shape);
        
        
    }
    
    //returns container
    public ShapeContainer getContainer()
    {
        return container;
    }
}