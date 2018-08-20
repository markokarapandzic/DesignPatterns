package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Square {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int visina;

	public Rectangle() {

	}

	public Rectangle(Point goreLevo, int sirina, int visina) {
		super(goreLevo, sirina);
		this.visina = visina;
	}

	public Rectangle(Point goreLevo, int sirina, int visina, Color boja, Color bojaU) {
		this(goreLevo, sirina, visina);
		setColor(boja);
		setFillColor(bojaU);
	}

	public int povrsina() {
		return visina * duzinaStranice;
	}

	public int obim() {
		return 2 * visina + 2 * duzinaStranice;
	}

	public String toString() {
		String color = Integer.toString(getColor().getRGB());
		String colorInside = Integer.toString(getFillColor().getRGB());
		return "Pravougaonik:(" + upLeft.getX() + "," + upLeft.getY() + ")," + duzinaStranice + "/" + visina
				+ " rgb boja-" + color + ":" + colorInside;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocni = (Rectangle) obj;
			if (upLeft.equals(pomocni.upLeft) && duzinaStranice == pomocni.duzinaStranice && visina == pomocni.visina)
				return true;
			else
				return false;

		} else
			return false;
	}

	public Line dijagonala() {
		return new Line(upLeft, new Point(upLeft.getX() + duzinaStranice, upLeft.getY() + visina));
	}

	public Point centar() {
		return dijagonala().sredinaLinije();
	}

	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(findColor("plava"));
		new Line(getGoreLevo(), new Point(getGoreLevo().getX() + duzinaStranice, getGoreLevo().getY())).selected(g);
		new Line(getGoreLevo(), new Point(getGoreLevo().getX(), getGoreLevo().getY() + visina)).selected(g);
		new Line(new Point(getGoreLevo().getX() + duzinaStranice, getGoreLevo().getY()), dijagonala().gettKrajnja())
				.selected(g);
		new Line(new Point(getGoreLevo().getX(), getGoreLevo().getY() + visina), dijagonala().gettKrajnja())
				.selected(g);
		setSelected(true);
	}

	public boolean contains(int x, int y) {
		if (this.getGoreLevo().getX() <= x && x <= (this.getGoreLevo().getX() + duzinaStranice)
				&& this.getGoreLevo().getY() <= y && y <= (this.getGoreLevo().getY() + visina))
			return true;
		else
			return false;

	}

	public void drawShape(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upLeft.getX(), upLeft.getY(), duzinaStranice, visina);
		fill(g);
		if (isSelected())
			selected(g);
	}

	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(upLeft.getX() + 1, upLeft.getY() + 1, duzinaStranice - 1, visina - 1);

	}

	public int getVisina() {
		return visina;
	}

	public void setVisina(int visina) {
		this.visina = visina;
	}

}
