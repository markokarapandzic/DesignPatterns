package command;

import geometry.Point;
import geometry.Rectangle;
import mvc.DrawingFrame;

public class CmdUpdateRectangle implements Command {

	private Rectangle original;
	private Rectangle newState;
	private Rectangle oldState = new Rectangle();

	public CmdUpdateRectangle(Rectangle original, Rectangle newState) {
		this.original = original;
		this.newState = newState;
	}

	@Override
	public void execute() {
		
		oldState.setGoreLevo(new Point(original.getGoreLevo()));
		oldState.setDuzinaStranice(original.getDuzinaStranice());
		oldState.setVisina(original.getVisina());
		oldState.setColor(original.getColor());
		oldState.setFillColor(original.getFillColor());

		original.setGoreLevo(new Point(newState.getGoreLevo()));
		original.setDuzinaStranice(newState.getDuzinaStranice());
		original.setVisina(newState.getVisina());
		original.setColor(newState.getColor());
		original.setFillColor(newState.getFillColor());
		DrawingFrame.getTxtArea().append("Modified " + newState + "\n");
		
	}

	@Override
	public void unexecute() {
		
		original.setGoreLevo(new Point(oldState.getGoreLevo()));
		original.setDuzinaStranice(oldState.getDuzinaStranice());
		original.setVisina(oldState.getVisina());
		original.setColor(oldState.getColor());
		original.setFillColor(oldState.getFillColor());
		DrawingFrame.getTxtArea().append("Modified " + oldState + "\n");
		
	}

}
