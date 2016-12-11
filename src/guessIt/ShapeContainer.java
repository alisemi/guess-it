package guessIt;

import java.util.ArrayList;
/**
 * This class will be used to hold shapes
 * @author Hamza Hussain
 * @date 28.04.2016
 * @version 1.00
 */
public class ShapeContainer
{
    ArrayList<Shape> container;
    
    //Constructors
    public ShapeContainer()
    {
        container = new ArrayList<Shape>();
    }
    
    public ShapeContainer( ArrayList<Shape> container) {
    	this.container = container;
    }
    
    //Adds a new shape to the container
    public void add( Shape newShape)
    {
        container.add( newShape);
    }
    
    //Removes the shape from the container
    public void remove( int index)
    {
        container.remove( index);
    }
    
    //Returns a particular shape based on the index provided
    public Shape getShape( int index)
    {
        return container.get( index);
    }
    
    //Returns the size of the container
    public int size() {
    	return container.size();
    }
    
    //Returns the sublist of the container
    public ArrayList<Shape> subList(int from, int to) {
    	if( from >= to || from < 0 ) {
    		return null;
    	}
    	if(  to > container.size() ){
    		to = container.size();
    	}
	
		ArrayList<Shape> newList;
		newList = new ArrayList<Shape>();
		for( int i = from; i < to; i++) {
    		newList.add(container.get(i) );
    	}
    	return newList;
    	
    	
    }
}