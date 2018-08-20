package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color = Color.BLACK;
	private boolean selected;

	public Shape() {

	}

	public Shape(Color boja) {
		this.color = boja;
	}

	public abstract void drawShape(Graphics g);

	public abstract void selected(Graphics g);

	public abstract boolean contains(int x, int y);

	public static Color findColor(String boja) {
		if (boja.equalsIgnoreCase("crna"))
			return Color.BLACK;
		else if (boja.equalsIgnoreCase("bela"))
			return Color.WHITE;
		else if (boja.equalsIgnoreCase("plava"))
			return Color.BLUE;
		else if (boja.equalsIgnoreCase("crvena"))
			return Color.RED;
		else if (boja.equalsIgnoreCase("zelena"))
			return Color.GREEN;
		else if (boja.equalsIgnoreCase("zuta"))
			return Color.YELLOW;
		else if (boja.equalsIgnoreCase("pink"))
			return Color.PINK;
		else
			return Color.BLACK;

	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color boja) {
		this.color = boja;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
