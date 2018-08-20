package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape implements Moveable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point center;
	private int r;

	public Circle() {

	}

	public Circle(Point centar, int r) {
		this.center = centar;
		this.r = r;
	}

	public Circle(Point centar, int r, Color boja, Color bojaUnutrasnjosti) {
		this(centar, r);
		setColor(boja);
		setFillColor(bojaUnutrasnjosti);
	}

	public void moveTo(int x, int y) {
		center.moveTo(x, y);
	}

	public void moveBy(int x, int y) {
		center.moveBy(x, y);
	}

	public double povrsina() {
		return r * r * Math.PI;
	}

	public double obim() {
		return 2 * r * Math.PI;
	}

	public String toString() {
		String color = Integer.toString(getColor().getRGB());
		String colorInside = Integer.toString(getFillColor().getRGB());
		return "Krug:(" + center.getX() + "," + center.getY() + ")," + r + " rgb boja-" + color + ":" + colorInside;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle help = (Circle) obj;
			if (center.equals(help.center) && r == help.r)
				return true;
			else
				return false;

		} else
			return false;
	}

	public boolean contains(int x, int y) {
		Point pointClick = new Point(x, y);
		if (pointClick.udaljenost(center) <= r)
			return true;
		else
			return false;

	}

	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		new Line(new Point(center.getX(), center.getY() - r), new Point(center.getX(), center.getY() + r)).selected(g);
		new Line(new Point(center.getX() - r, center.getY()), new Point(center.getX() + r, center.getY())).selected(g);
		setSelected(true);
	}

	public void drawShape(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX() - r, center.getY() - r, 2 * r, 2 * r);
		fill(g);
		if (isSelected())
			selected(g);
	}

	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillOval(center.getX() - r + 1, center.getY() - r + 1, 2 * r - 2, r + r - 2);

	}

	public int compareTo(Object o) {
		if (o instanceof Circle) {
			Circle pomocni = (Circle) o;
			return (int) (this.r - pomocni.r);
		} else
			return 0;
	}

	public Point getCentar() {
		return center;
	}

	public int getR() {
		return r;
	}

	public void setCentar(Point centar) {
		this.center = centar;
	}

	public void setR(int r) {
		this.r = r;
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
