package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape {

	private static final long serialVersionUID = 1L;
	private Color insideColor = Color.WHITE;

	public abstract void fill(Graphics g);

	public Color getFillColor() {
		return insideColor;
	}

	public void setFillColor(Color fillColor) {
		this.insideColor = fillColor;
	}

}
