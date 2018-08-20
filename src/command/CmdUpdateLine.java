package command;

import geometry.Line;
import geometry.Point;
import mvc.DrawingFrame;

public class CmdUpdateLine implements Command {

	private Line original;
	private Line newState;
	private Line oldState = new Line();

	public CmdUpdateLine(Line original, Line newState) {
		this.original = original;
		this.newState = newState;
	}

	@Override
	public void execute() {
		
		oldState.settPocetna(new Point(original.gettPocetna()));
		oldState.settKrajnja(new Point(original.gettKrajnja()));
		oldState.setColor(original.getColor());

		original.settPocetna(new Point(newState.gettPocetna()));
		original.settKrajnja(new Point(newState.gettKrajnja()));
		original.setColor(newState.getColor());
		DrawingFrame.getTxtArea().append("Modified " + newState + "\n");

	}

	@Override
	public void unexecute() {
		
		original.settPocetna(new Point(oldState.gettPocetna()));
		original.settKrajnja(new Point(oldState.gettKrajnja()));
		original.setColor(oldState.getColor());
		DrawingFrame.getTxtArea().append("Modified " + oldState + "\n");
		
	}

}
