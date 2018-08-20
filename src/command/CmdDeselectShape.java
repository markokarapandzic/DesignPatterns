package command;

import java.awt.Graphics;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class CmdDeselectShape implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private Graphics g;
	
	public CmdDeselectShape(DrawingModel model, Shape shape, Graphics g) {
		this.model = model;
		this.shape = shape;
		this.g = g;
	}
	
	@Override
	public void execute() {
		
		try {
			
		model.getShapeByIndex(model.getShapes().indexOf(shape)).setSelected(false);
		
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
		
		DrawingFrame.getTxtArea().append("Deselected " + shape + "\n");
		
	}

	@Override
	public void unexecute() {
		
		try {
			
		model.getShapeByIndex(model.getShapes().indexOf(shape)).selected(g);
		
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
		
		DrawingFrame.getTxtArea().append("Selected " + shape + "\n");
		
	}

}
