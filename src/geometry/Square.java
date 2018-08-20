package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends SurfaceShape implements Moveable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Point upLeft;
	protected int duzinaStranice;

	public Square() {

	}

	public Square(Point goreLevo, int duzinaStranice) {
		this.upLeft = goreLevo;
		this.duzinaStranice = duzinaStranice;
	}

	public Square(Point goreLevo, int duzinaStranice, Color boja, Color bojaUnutrasnjosti) {
		this(goreLevo, duzinaStranice);
		setColor(boja);
		setFillColor(bojaUnutrasnjosti);
	}

	public void moveTo(int x, int y) {
		upLeft.setX(x);
		upLeft.setY(y);
	}

	public void moveBy(int x, int y) {
		upLeft.setX(upLeft.getX() + x);
		upLeft.setY(upLeft.getY() + y);
	}

	public int obim() {
		return 4 * duzinaStranice;
	}

	public int povrsina() {
		return duzinaStranice * duzinaStranice;
	}

	public String toString() {
		String color = Integer.toString(getColor().getRGB());
		String colorInside = Integer.toString(getFillColor().getRGB());
		return "Kvadrat:(" + upLeft.getX() + "," + upLeft.getY() + ")," + duzinaStranice + " rgb boja-" + color + ":"
				+ colorInside;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Square) {
			Square pomocni = (Square) obj;
			if (upLeft.equals(pomocni.upLeft) && duzinaStranice == pomocni.duzinaStranice)
				return true;
			else
				return false;

		} else
			return false;
	}

	public Line dijagonala() {
		return new Line(upLeft, new Point(upLeft.getX() + duzinaStranice, upLeft.getY() + duzinaStranice));
	}

	public Point centar() {
		return dijagonala().sredinaLinije();
	}

	public boolean contains(int x, int y) {
		if (this.getGoreLevo().getX() <= x && x <= (this.getGoreLevo().getX() + duzinaStranice)
				&& this.getGoreLevo().getY() <= y && y <= (this.getGoreLevo().getY() + duzinaStranice))
			return true;
		else
			return false;

	}

	public void selected(Graphics g) {

		g.setColor(findColor("plava"));
		new Line(getGoreLevo(), new Point(getGoreLevo().getX() + duzinaStranice, getGoreLevo().getY())).selected(g);
		new Line(getGoreLevo(), new Point(getGoreLevo().getX(), getGoreLevo().getY() + duzinaStranice)).selected(g);
		new Line(new Point(getGoreLevo().getX() + duzinaStranice, getGoreLevo().getY()), dijagonala().gettKrajnja())
				.selected(g);
		new Line(new Point(getGoreLevo().getX(), getGoreLevo().getY() + duzinaStranice), dijagonala().gettKrajnja())
				.selected(g);
		setSelected(true);

	}

	public void drawShape(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upLeft.getX(), upLeft.getY(), duzinaStranice, duzinaStranice);
		fill(g);
		if (isSelected())
			selected(g);
	}

	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(upLeft.getX() + 1, upLeft.getY() + 1, duzinaStranice - 1, duzinaStranice - 1);

	}

	public int compareTo(Object o) {
		if (o instanceof Square) {
			Square pomocni = (Square) o;
			return (int) (this.povrsina() - pomocni.povrsina());
		} else
			return 0;
	}

	public Point getGoreLevo() {
		return upLeft;
	}

	public int getDuzinaStranica() {
		return duzinaStranice;
	}

	public void setGoreLevo(Point goreLevo) {
		this.upLeft = goreLevo;
	}

	public void setDuzinaStranica(int duzinaStranica) {
		this.duzinaStranice = duzinaStranica;
	}

	public int getDuzinaStranice() {
		return duzinaStranice;
	}

	public void setDuzinaStranice(int duzinaStranice) {
		this.duzinaStranice = duzinaStranice;
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
