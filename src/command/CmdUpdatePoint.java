package command;

import geometry.Point;
import mvc.DrawingFrame;

public class CmdUpdatePoint implements Command {

	private Point original;
	private Point newState;
	private Point oldState = new Point();

	public CmdUpdatePoint(Point original, Point newState) {
		this.original = original;
		this.newState = newState;
	}

	@Override
	public void execute() {

		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setColor(original.getColor());

		original.setX(newState.getX());
		original.setY(newState.getY());
		original.setColor(newState.getColor());
		DrawingFrame.getTxtArea().append("Modifikovan " + newState + "\n");

	}

	@Override
	public void unexecute() {

		original.setX(oldState.getX());
		original.setY(oldState.getY());
		original.setColor(oldState.getColor());
		DrawingFrame.getTxtArea().append("Modified " + oldState + "\n");

	}

}
