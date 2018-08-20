package command;

import geometry.HexagonAdapter;
import hexagon.Hexagon;
import mvc.DrawingFrame;

public class CmdUpdateHexagon implements Command {
	private HexagonAdapter original;
	private HexagonAdapter newState;
	private HexagonAdapter oldState = new HexagonAdapter();

	public CmdUpdateHexagon(HexagonAdapter original, HexagonAdapter newState) {
		this.original = original;
		this.newState = newState;
	}

	@Override
	public void execute() {

		Hexagon h = new Hexagon(original.getHexagon().getX(), original.getHexagon().getY(),
				original.getHexagon().getR());
		h.setAreaColor(original.getHexagon().getAreaColor());
		h.setBorderColor(original.getHexagon().getBorderColor());
		h.setSelected(true);
		oldState.setHexagon(h);
		Hexagon h1 = new Hexagon(newState.getHexagon().getX(), newState.getHexagon().getY(),
				newState.getHexagon().getR());
		h1.setAreaColor(newState.getHexagon().getAreaColor());
		h1.setBorderColor(newState.getHexagon().getBorderColor());
		h1.setSelected(true);

		original.setHexagon(h1);
		DrawingFrame.getTxtArea().append("Modified " + newState + "\n");
	}

	@Override
	public void unexecute() {
		
		Hexagon h2 = new Hexagon(oldState.getHexagon().getX(), oldState.getHexagon().getY(),
				oldState.getHexagon().getR());
		h2.setAreaColor(oldState.getHexagon().getAreaColor());
		h2.setBorderColor(oldState.getHexagon().getBorderColor());
		h2.setSelected(true);
		original.setHexagon(h2);
		DrawingFrame.getTxtArea().append("Modified " + oldState + "\n");

	}
}
