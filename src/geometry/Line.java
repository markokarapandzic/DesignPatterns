package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point tPocetna;
	private Point tKrajnja;

	public Line() {

	}

	public Line(Point tPocetna, Point tKrajnja) {
		this.tPocetna = tPocetna;
		this.tKrajnja = tKrajnja;
	}

	public Line(Point tPocetna, Point tKrajnja, Color boja) {
		this(tPocetna, tKrajnja);
		setColor(boja);
	}

	public void moveBy(int x, int y) {
		tPocetna.setX(tPocetna.getX() + x);
		tPocetna.setY(tPocetna.getY() + y);
		tKrajnja.setX(tKrajnja.getX() + x);
		tKrajnja.setY(tKrajnja.getY() + y);
	}

	public double duzina() {
		return tPocetna.udaljenost(tKrajnja);
	}

	public String toString() {
		String color = Integer.toString(getColor().getRGB());

		return "Linija:" + "(" + tPocetna.getX() + "," + tPocetna.getY() + "),(" + tKrajnja.getX() + ","
				+ tKrajnja.getY() + ")-rgb boje:" + color;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line pomocna = (Line) obj;
			if (tPocetna.equals(pomocna.gettPocetna()) && tKrajnja.equals(pomocna.gettKrajnja()))
				return true;
			else
				return false;

		} else
			return false;
	}

	public Point sredinaLinije() {
		int sredinaX = (tPocetna.getX() + tKrajnja.getX()) / 2;
		int sredinaY = (tPocetna.getY() + tKrajnja.getY()) / 2;
		return new Point(sredinaX, sredinaY);
	}

	public boolean contains(int x, int y) {
		Point mestoKlika = new Point(x, y);
		if (mestoKlika.udaljenost(tPocetna) + mestoKlika.udaljenost(tKrajnja) - this.duzina() < 0.5)
			return true;
		else
			return false;
	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		tPocetna.selected(g);
		tKrajnja.selected(g);
		sredinaLinije().selected(g);
		setSelected(true);
	}

	public void drawShape(Graphics g) {
		g.setColor(getColor());
		g.drawLine(tPocetna.getX(), tPocetna.getY(), tKrajnja.getX(), tKrajnja.getY());
		if (isSelected())
			selected(g);
	}

	public int compareTo(Object o) {
		if (o instanceof Line) {
			Line pomocna = (Line) o;
			return (int) (this.duzina() - pomocna.duzina());
		} else
			return 0;
	}

	public Point gettPocetna() {
		return tPocetna;
	}

	public Point gettKrajnja() {
		return tKrajnja;
	}

	public void settPocetna(Point tPocetna) {
		this.tPocetna = tPocetna;
	}

	public void settKrajnja(Point tKrajnja) {
		this.tKrajnja = tKrajnja;
	}

	public static String findColor(Color color) {
		if (color.equals(Color.BLACK))
			return "crna";
		else if (color.equals(Color.WHITE))
			return "bela";
		else if (color.equals(Color.BLUE))
			return "plava";
		else if (color.equals(Color.RED))
			return "crvena";
		else if (color.equals(Color.GREEN))
			return "zelena";
		else if (color.equals(Color.YELLOW))
			return "�uta";
		else if (color.equals(Color.PINK))
			return "pink";
		else
			return "crna";

	}

}
