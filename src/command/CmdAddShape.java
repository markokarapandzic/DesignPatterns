package command;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class CmdAddShape implements Command {

	private DrawingModel model;
	private Shape shape;

	public CmdAddShape(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		model.add(shape);
		DrawingFrame.getTxtArea().append("New " + shape + "\n");
	}

	@Override
	public void unexecute() {
		model.removeShape(shape);
		DrawingFrame.getTxtArea().append("New " + shape + "\n");
	}

}
