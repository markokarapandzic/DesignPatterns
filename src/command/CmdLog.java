package command;

import java.util.ArrayList;

import mvc.DrawingController;

public class CmdLog {

	private ArrayList<Command> list = new ArrayList<Command>();
	private ArrayList<Command> undo = new ArrayList<Command>();
	private int current = 0;

	public ArrayList<Command> getList() {
		return list;
	}

	public ArrayList<Command> getUndo() {
		return undo;
	}

	public void setUndo(ArrayList<Command> undo) {
		this.undo = undo;
	}

	public void go() {
		
		list.get(current).execute();
		
		if (list.get(current) instanceof CmdSelectShape) {
			DrawingController.setEnabled(true);
		} else if (list.get(current) instanceof CmdDeselectShape) {
			DrawingController.setEnabled(false);
		}
		
		current++;

	}

	public int getCurrent() {
		return current;
	}

}
