package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements Moveable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, Color boja) {
		this(x, y);
		setColor(boja);

	}

	public Point(Point t) {
		this.setX(t.getX());
		this.setY(t.getY());
		setColor(t.getColor());
	}

	public void moveTo(int novoX, int novoY) {
		x = novoX;
		setY(novoY);
	}

	public void moveBy(int novoX, int novoY) {
		x = x + novoX;
		setY(getY() + novoY);
	}

	public double udaljenost(Point t2) {
		double dx = x - t2.getX();
		double dy = y - t2.getY();
		double rezultat = Math.sqrt(dx * dx + dy * dy);

		return rezultat;
	}

	// (x,y)
	public String toString() {
		String color = Integer.toString(getColor().getRGB());

		return "Tacka:(" + x + "," + y + ") " + "rgb boje:" + color;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;
			if (x == pomocna.x && y == pomocna.y)
				return true;
			else
				return false;

		} else
			return false;
	}

	public boolean contains(int x, int y) {
		Point mestoKlika = new Point(x, y);
		if (mestoKlika.udaljenost(this) <= 2)
			return true;
		else
			return false;
	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x - 3, y - 3, 6, 6);
		setSelected(true);
	}

	@Override
	public void drawShape(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x - 1, this.y - 1, this.x + 1, this.y + 1);
		g.drawLine(this.x - 1, this.y + 1, this.x + 1, this.y - 1);
	}

	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point pomocna = (Point) o;
			return (int) this.udaljenost(new Point(0, 0)) - (int) pomocna.udaljenost(new Point(0, 0));

		} else
			return 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int novoX) {
		x = novoX;
	}

	public void setY(int novoY) {
		y = novoY;
	}

	public static String pronadjiBoju(Color boja) {
		if (boja.equals(Color.BLACK))
			return "crna";
		else if (boja.equals(Color.WHITE))
			return "bela";
		else if (boja.equals(Color.BLUE))
			return "plava";
		else if (boja.equals(Color.RED))
			return "crvena";
		else if (boja.equals(Color.GREEN))
			return "zelena";
		else if (boja.equals(Color.YELLOW))
			return "�uta";
		else if (boja.equals(Color.PINK))
			return "pink";
		else
			return "crna";

	}

}
