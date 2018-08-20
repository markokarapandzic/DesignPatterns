package command;

import java.awt.Graphics;
import java.util.ArrayList;

import geometry.HexagonAdapter;
import geometry.Shape;
import geometry.Square;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class CmdUpdateShape implements Command {
	DrawingModel model;
	Shape s;
	Shape shape;
	Graphics graphics;
	ArrayList<Shape> sel;

	public CmdUpdateShape(DrawingModel model, Shape s, Shape shape, Graphics graphics, ArrayList<Shape> sel) {
		this.model = model;
		this.s = s;
		this.shape = shape;
		this.graphics = graphics;
		this.sel = sel;
	}

	@Override
	public void execute() {
		
		for (int i = 0; i < model.getShapes().size(); i++) {
			
			if (model.getShapes().get(i).isSelected()) {
				model.getShapes().set(i, s);
				model.getShapes().get(i).setSelected(true);

				sel.clear();
				sel.add(s);

			}
		}
		
		DrawingFrame.getTxtArea().append("Modified " + s + "\n");
	}

	@Override
	public void unexecute() {
		
		for (int i = 0; i < model.getShapes().size(); i++) {
			
			if (model.getShapes().get(i).isSelected()) {

				model.getShapes().set(i, shape);
				model.getShapes().get(i).setSelected(true);
				sel.clear();
				sel.add(shape);

			}
		}
		
		DrawingFrame.getTxtArea().append("Modified " + shape + "\n");
	}

}
