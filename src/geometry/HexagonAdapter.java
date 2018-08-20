package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends Shape {

	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;

	public HexagonAdapter() {
	}

	public HexagonAdapter(Hexagon h) {
		this.hexagon = h;
	}

	public int compareTo(Object o) {

		if (o instanceof Hexagon) {
			Hexagon temp = (Hexagon) o;
			return (int) hexagon.getR() - temp.getR();
		} else {
			return 0;
		}

	}

	public String toString() {
		String color = Integer.toString(hexagon.getBorderColor().getRGB());
		String colorInside = Integer.toString(hexagon.getAreaColor().getRGB());
		return "Heksagon:(" + hexagon.getX() + "," + hexagon.getY() + ")," + hexagon.getR() + " rgb boja-" + color + ":"
				+ colorInside;
	}

	@Override
	public void drawShape(Graphics g) {
		hexagon.paint(g);
	}

	@Override
	public void selected(Graphics g) {

		hexagon.setSelected(true);
		setSelected(true);

	}

	@Override
	public void setSelected(boolean b) {

		super.setSelected(b);
		hexagon.setSelected(b);

	}

	public boolean equals(Object obj) {

		if (obj instanceof HexagonAdapter) {

			HexagonAdapter temp = (HexagonAdapter) obj;
			Point t = new Point(hexagon.getX(), hexagon.getY());
			Point t1 = new Point(temp.hexagon.getX(), temp.hexagon.getY());

			if (t.equals(t1) && hexagon.getR() == temp.getHexagon().getR())
				return true;
			else
				return false;

		} else
			return false;

	}

	public static String findColor(Color boja) {
		
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
			return "žuta";
		else if (boja.equals(Color.PINK))
			return "pink";
		else
			return "crna";

	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

}
