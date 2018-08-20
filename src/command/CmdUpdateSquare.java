package command;

import geometry.Point;
import geometry.Square;
import mvc.DrawingFrame;

public class CmdUpdateSquare implements Command {

	private Square original;
	private Square newState;
	private Square oldState = new Square();

	public CmdUpdateSquare(Square original, Square newState) {
		this.original = original;
		this.newState = newState;
	}

	@Override
	public void execute() {
		
		oldState.setGoreLevo(new Point(original.getGoreLevo()));
		oldState.setDuzinaStranice(original.getDuzinaStranice());

		oldState.setColor(original.getColor());
		oldState.setFillColor(original.getFillColor());

		original.setGoreLevo(new Point(newState.getGoreLevo()));
		original.setDuzinaStranice(newState.getDuzinaStranice());

		original.setColor(newState.getColor());
		original.setFillColor(newState.getFillColor());
		DrawingFrame.getTxtArea().append("Modified " + newState + "\n");
	}

	@Override
	public void unexecute() {
		
		original.setGoreLevo(new Point(oldState.getGoreLevo()));
		original.setDuzinaStranice(oldState.getDuzinaStranice());

		original.setColor(oldState.getColor());
		original.setFillColor(oldState.getFillColor());
		DrawingFrame.getTxtArea().append("Modified " + oldState + "\n");
		
	}
}
