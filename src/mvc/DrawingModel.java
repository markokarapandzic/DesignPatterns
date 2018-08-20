package mvc;

import java.io.Serializable;
import java.util.ArrayList;

import geometry.Shape;

public class DrawingModel implements Serializable {

	private static final long serialVersionUID = 1l;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void add(Shape shape) {
		shapes.add(shape);
	}
	
	public Shape getShapeByIndex(int index) {
		return shapes.get(index);
	}
	
	public boolean removeShape(Shape shape) {
		return shapes.remove(shape);
	}
	
}
