package command;

import java.awt.Graphics;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class CmdSelectShape implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private Graphics g;
	
	public CmdSelectShape(DrawingModel model, Shape shape, Graphics g) {
		
		this.model = model;
		this.shape = shape;
		this.g = g;
		
	}

	@Override
	public void execute() {
		
		for (Shape tempShape : model.getShapes()) {
			
			if (shape.equals(tempShape)) {
				tempShape.selected(g);
			}
			
		}
		
		DrawingFrame.getTxtArea().append("Selected " + shape+"\n");
		
	}

	@Override
	public void unexecute() {
		
		model.getShapeByIndex(model.getShapes().indexOf(shape)).setSelected(false);
		
		DrawingFrame.getTxtArea().append("Deselected " + shape+"\n");
		
	}
	
	

}
