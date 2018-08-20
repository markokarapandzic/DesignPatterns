package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class CmdRemoveShape implements Command {

	private DrawingModel model;
	private Shape shape;

	public CmdRemoveShape(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.removeShape(shape);
		DrawingFrame.getTxtArea().append("Deleted " + shape + "\n");
	}

	@Override
	public void unexecute() {
		model.add(shape);
		DrawingFrame.getTxtArea().append("New " + shape + "\n");
	}

}
