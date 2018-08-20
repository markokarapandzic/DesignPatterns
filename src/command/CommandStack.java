package command;

import java.util.ArrayList;

import mvc.DrawingController;

public class CommandStack {

	private ArrayList<Command> list = new ArrayList<Command>();
	private ArrayList<Command> undo = new ArrayList<Command>();
	private int current;

	public void undo() {

		list.get(current).unexecute();
		undo.add(list.get(current));
		current--;

	}

	public void redo() {

		current++;
		list.get(current).execute();

		if (list.get(current) instanceof CmdSelectShape) {
			DrawingController.setEnabled(true);
		} else if (list.get(current) instanceof CmdDeselectShape) {
			DrawingController.setEnabled(false);
		}

		undo.remove(list.get(current));

	}

	public void go() {

		list.get(current).execute();

		if (list.get(current) instanceof CmdSelectShape)
			DrawingController.setEnabled(true);
		else if (list.get(current) instanceof CmdDeselectShape)
			DrawingController.setEnabled(false);

		current++;

	}

	public void newCmd(boolean check) {

		if (check) {
			list.removeAll(undo);
			undo.clear();
		}

		current = list.size() - 1;

	}

	public ArrayList<Command> getList() {
		return list;
	}

	public void setList(ArrayList<Command> list) {
		this.list = list;
	}

	public ArrayList<Command> getUndo() {
		return undo;
	}

	public void setUndo(ArrayList<Command> undo) {
		this.undo = undo;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

}
