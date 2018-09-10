package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdDeselectShape;
import command.CmdOneToBack;
import command.CmdOneToFront;
import command.CmdRemoveShape;
import command.CmdSelectShape;
import command.CmdUpdateCircle;
import command.CmdUpdateHexagon;
import command.CmdUpdateLine;
import command.CmdUpdatePoint;
import command.CmdUpdateRectangle;
import command.CmdUpdateShape;
import command.CmdUpdateSquare;
import command.Command;
import command.CommandStack;
import dialogs.DlgKrug;
import dialogs.DlgKvadrat;
import geometry.Point;
import geometry.Shape;
import hexagon.Hexagon;
import observer.Observer;
import observer.Subject;
import strategy.SaveLog;
import strategy.SaveManager;
import strategy.SavePainting;
import dialogs.DlgLinija;
import dialogs.DlgPravougaonik;
import dialogs.DlgTacka;
import geometry.Circle;
import geometry.Square;
import geometry.Rectangle;
import geometry.Line;

import geometry.HexagonAdapter;

public class DrawingController implements Subject {

	private CommandStack list = new CommandStack();
	private ArrayList<String> backList = new ArrayList<String>();
	private boolean pro = false;
	private int pp;
	private int current;
	private ArrayList<Shape> selectedList = new ArrayList<Shape>();
	private ArrayList<Shape> selList = new ArrayList<Shape>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private int check = 0;
	private static boolean enabled;
	private static boolean enabledDelete = false;
	private DrawingModel model;
	private DrawingFrame frame;
	private DlgKvadrat dlgKvadrat = new DlgKvadrat();
	private DlgPravougaonik dlgPravougaonik = new DlgPravougaonik();
	private DlgKrug dlgKrug = new DlgKrug();
	private DlgTacka dlgTacka = new DlgTacka();
	private DlgLinija dlgLinija = new DlgLinija();
	// private SaveStrategy save;

	private int x, y;
	private int currentUndo = 0;
	private int counter = 0;
	private Object o;
	public int pr;
	private File fileToLoad;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	// Modify START
	public void viewButtonModifyClicked(ActionEvent arg0) {

		if (frame.getTglbtnIzmeniOblik().isSelected()) {
			for (int i = model.getShapes().size() - 1; i >= 0; i--) {

				if (model.getShapes().get(i) instanceof Point) {

					Point temp = (Point) model.getShapes().get(i);

					if (temp.isSelected()) {

						dlgTacka.setTitle("Point Modification: ");
						dlgTacka.getTxtX().setText(Integer.toString(temp.getX()));
						dlgTacka.getTxtY().setText(Integer.toString(temp.getY()));
						dlgTacka.getPnlBojaIvice().setBackground(temp.getColor());
						dlgTacka.setVisible(true);

						try {

							Color color = dlgTacka.getPnlBojaIvice().getBackground();

							Point temp1 = new Point(Integer.parseInt(dlgTacka.getTxtX().getText()),
									Integer.parseInt(dlgTacka.getTxtY().getText()), color);

							if (dlgTacka.isProvera()) {

								CmdUpdatePoint cmd = new CmdUpdatePoint(temp, temp1);
								cmd.execute();
								list.getList().add(cmd);

							}

							dlgTacka.getTxtX().setText("");
							dlgTacka.getTxtY().setText("");
							dlgTacka.getPnlBojaIvice().setBackground(Color.WHITE);
							frame.getView().repaint();
							frame.getBtnUndo().setEnabled(true);

						} catch (Exception exc) {

							JOptionPane.showMessageDialog(null, "You didn't enter a corrent value");
							dlgTacka.getTxtX().setText("");
							dlgTacka.getTxtY().setText("");
							dlgTacka.getPnlBojaIvice().setBackground(Color.WHITE);

						}

					}

				} else if (model.getShapes().get(i) instanceof HexagonAdapter) {

					HexagonAdapter helper = (HexagonAdapter) model.getShapes().get(i);

					if (helper.isSelected()) {

						dlgKrug.setTitle("Hexagon Modification: ");
						dlgKrug.getTxtX().setText(Integer.toString(helper.getHexagon().getX()));
						dlgKrug.getTxtY().setText(Integer.toString(helper.getHexagon().getY()));
						dlgKrug.getTxtPoluprecnik().setText(Integer.toString(helper.getHexagon().getR()));
						dlgKrug.getPnlBojaUnutrasnjosti().setBackground(helper.getHexagon().getAreaColor());
						dlgKrug.getPnlBojaIvice().setBackground(helper.getHexagon().getBorderColor());
						dlgKrug.getTxtX().setEditable(true);
						dlgKrug.getTxtY().setEditable(true);
						dlgKrug.setVisible(true);

						try {

							int x = Integer.parseInt(dlgKrug.getTxtX().getText());
							int y = Integer.parseInt(dlgKrug.getTxtY().getText());
							Color colorInner = dlgKrug.getPnlBojaUnutrasnjosti().getBackground();
							Color color = dlgKrug.getPnlBojaIvice().getBackground();
							int r = Integer.parseInt(dlgKrug.getTxtPoluprecnik().getText());

							Hexagon k = new Hexagon(x, y, r);
							k.setAreaColor(colorInner);
							k.setBorderColor(color);
							HexagonAdapter ha = new HexagonAdapter(k);

							if (dlgKrug.isProvera()) {

								CmdUpdateHexagon cmd = new CmdUpdateHexagon(helper, ha);
								cmd.execute();
								list.getList().add(cmd);

							}

							dlgKrug.getTxtX().setText("");
							dlgKrug.getTxtY().setText("");
							dlgKrug.getTxtPoluprecnik().setText("");

							dlgKrug.getPnlBojaIvice().setBackground(Color.WHITE);
							dlgKrug.getPnlBojaUnutrasnjosti().setBackground(Color.WHITE);
							frame.getView().repaint();
							frame.getBtnUndo().setEnabled(true);

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, "You didn't enter a correct value");
							dlgKrug.getTxtX().setText("");
							dlgKrug.getTxtY().setText("");
							dlgKrug.getTxtPoluprecnik().setText("");
							dlgKrug.getPnlBojaIvice().setBackground(Color.WHITE);
							dlgKrug.getPnlBojaUnutrasnjosti().setBackground(Color.WHITE);

						}

					}
				} else if (model.getShapes().get(i) instanceof Line) {

					Line helper = (Line) model.getShapes().get(i);

					if (helper.isSelected()) {

						dlgLinija.setTitle("Line Modification:");
						dlgLinija.getTxtXPocetne().setText(Integer.toString(helper.gettPocetna().getX()));
						dlgLinija.getTxtYPocetne().setText(Integer.toString(helper.gettPocetna().getY()));
						dlgLinija.getTxtXKrajnje().setText(Integer.toString(helper.gettKrajnja().getX()));
						dlgLinija.getTxtYKrajnje().setText(Integer.toString(helper.gettKrajnja().getY()));
						dlgLinija.getPnlBojaIvice().setBackground(helper.getColor());
						dlgLinija.setVisible(true);

						try {

							Color color = dlgLinija.getPnlBojaIvice().getBackground();
							int startX = Integer.parseInt(dlgLinija.getTxtXPocetne().getText());
							int startY = Integer.parseInt(dlgLinija.getTxtYPocetne().getText());
							int endX = Integer.parseInt(dlgLinija.getTxtXKrajnje().getText());
							int endY = Integer.parseInt(dlgLinija.getTxtYKrajnje().getText());
							Line l1 = new Line(new Point(startX, startY), new Point(endX, endY), color);

							if (dlgLinija.isProvera()) {

								CmdUpdateLine cmd = new CmdUpdateLine(helper, l1);
								cmd.execute();
								list.getList().add(cmd);

							}

							dlgLinija.getTxtXPocetne().setText("");
							dlgLinija.getTxtYPocetne().setText("");
							dlgLinija.getTxtXKrajnje().setText("");
							dlgLinija.getTxtYKrajnje().setText("");
							dlgLinija.getPnlBojaIvice().setBackground(Color.WHITE);

							frame.getView().repaint();
							frame.getBtnUndo().setEnabled(true);

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, "You didn't enter a correct value");
							dlgLinija.getTxtXPocetne().setText("");
							dlgLinija.getTxtYPocetne().setText("");
							dlgLinija.getTxtXKrajnje().setText("");
							dlgLinija.getTxtYKrajnje().setText("");
							dlgLinija.getPnlBojaIvice().setBackground(Color.WHITE);

						}

					}
				} else if (model.getShapes().get(i) instanceof Rectangle) {

					Rectangle helper = (Rectangle) model.getShapes().get(i);

					if (helper.isSelected()) {

						Exception exp = null;
						dlgPravougaonik.setTitle("Rectangle Modification:");
						dlgPravougaonik.getTxtX().setText(Integer.toString(helper.getGoreLevo().getX()));
						dlgPravougaonik.getTxtY().setText(Integer.toString(helper.getGoreLevo().getY()));
						dlgPravougaonik.getTxtDuzinaStranice().setText(Integer.toString(helper.getDuzinaStranica()));
						dlgPravougaonik.getTxtSirinaStranice().setText(Integer.toString(helper.getVisina()));
						dlgPravougaonik.getPnlBojaIvice().setBackground(helper.getColor());
						dlgPravougaonik.getPnlBojaUnutrasnjosti().setBackground(helper.getFillColor());
						dlgPravougaonik.getTxtX().setEditable(true);
						dlgPravougaonik.getTxtY().setEditable(true);
						dlgPravougaonik.setVisible(true);

						try {

							Color color = dlgPravougaonik.getPnlBojaIvice().getBackground();
							Color colorInner = dlgPravougaonik.getPnlBojaUnutrasnjosti().getBackground();
							int x = Integer.parseInt(dlgPravougaonik.getTxtX().getText());
							int y = Integer.parseInt(dlgPravougaonik.getTxtY().getText());
							int duzina = Integer.parseInt(dlgPravougaonik.getTxtDuzinaStranice().getText());
							int sirina = Integer.parseInt(dlgPravougaonik.getTxtSirinaStranice().getText());

							if (duzina <= 0 || sirina <= 0) {
								throw exp;
							}

							Rectangle p = new Rectangle(new Point(x, y), duzina, sirina, color, colorInner);

							if (dlgPravougaonik.isProvera()) {

								CmdUpdateRectangle cmd = new CmdUpdateRectangle(helper, p);
								cmd.execute();
								list.getList().add(cmd);

							}

							dlgPravougaonik.getTxtX().setText("");
							dlgPravougaonik.getTxtY().setText("");
							dlgPravougaonik.getTxtDuzinaStranice().setText("");
							dlgPravougaonik.getTxtSirinaStranice().setText("");
							dlgPravougaonik.getPnlBojaIvice().setBackground(Color.WHITE);
							dlgPravougaonik.getPnlBojaUnutrasnjosti().setBackground(Color.WHITE);
							frame.getView().repaint();
							frame.getBtnUndo().setEnabled(true);

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, "You didn't enter a correct value");
							dlgPravougaonik.getTxtX().setText("");
							dlgPravougaonik.getTxtY().setText("");
							dlgPravougaonik.getTxtDuzinaStranice().setText("");
							dlgPravougaonik.getTxtSirinaStranice().setText("");
							dlgPravougaonik.getPnlBojaIvice().setBackground(Color.WHITE);
							dlgPravougaonik.getPnlBojaUnutrasnjosti().setBackground(Color.WHITE);

						}

					}
				} else if (model.getShapes().get(i) instanceof Square) {

					Square helper = (Square) model.getShapes().get(i);

					if (helper.isSelected()) {

						Exception exp = null;
						dlgKvadrat.setTitle("Square Modification: ");
						dlgKvadrat.getTxtX().setText(Integer.toString(helper.getGoreLevo().getX()));
						dlgKvadrat.getTxtY().setText(Integer.toString(helper.getGoreLevo().getY()));
						dlgKvadrat.getTxtDuzinaStranice().setText(Integer.toString(helper.getDuzinaStranica()));
						dlgKvadrat.getPnlBojaUnutrasnjosti().setBackground(helper.getFillColor());
						dlgKvadrat.getPnlBojaIvice().setBackground(helper.getColor());
						dlgKvadrat.getTxtX().setEditable(true);
						dlgKvadrat.getTxtY().setEditable(true);
						dlgKvadrat.setVisible(true);

						try {

							Color color = dlgKvadrat.getPnlBojaIvice().getBackground();
							Color colorInner = dlgKvadrat.getPnlBojaUnutrasnjosti().getBackground();
							int x = Integer.parseInt(dlgKvadrat.getTxtX().getText());
							int y = Integer.parseInt(dlgKvadrat.getTxtY().getText());
							int duzina = Integer.parseInt(dlgKvadrat.getTxtDuzinaStranice().getText());

							if (duzina <= 0) {
								throw exp;
							}

							Square k = new Square(new Point(x, y), duzina, color, colorInner);

							if (dlgKvadrat.isProvera()) {

								CmdUpdateSquare cmd = new CmdUpdateSquare(helper, k);
								cmd.execute();
								list.getList().add(cmd);

							}

							dlgKvadrat.getTxtX().setText("");
							dlgKvadrat.getTxtY().setText("");
							dlgKvadrat.getTxtDuzinaStranice().setText("");

							dlgKvadrat.getPnlBojaIvice().setBackground(Color.WHITE);
							dlgKvadrat.getPnlBojaUnutrasnjosti().setBackground(Color.WHITE);

							frame.getView().repaint();
							frame.getBtnUndo().setEnabled(true);

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, "You didn't enter a correct value");
							dlgKvadrat.getTxtX().setText("");
							dlgKvadrat.getTxtY().setText("");
							dlgKvadrat.getTxtDuzinaStranice().setText("");
							dlgKvadrat.getPnlBojaIvice().setBackground(Color.WHITE);
							dlgKvadrat.getPnlBojaUnutrasnjosti().setBackground(Color.WHITE);

						}

					}
				} else if (model.getShapes().get(i) instanceof Circle) {

					Circle helper = (Circle) model.getShapes().get(i);

					if (helper.isSelected()) {

						Exception exp = null;
						dlgKrug.setTitle("Circle Modification: ");
						dlgKrug.getTxtX().setText(Integer.toString(helper.getCentar().getX()));
						dlgKrug.getTxtY().setText(Integer.toString(helper.getCentar().getY()));
						dlgKrug.getTxtPoluprecnik().setText(Integer.toString(helper.getR()));
						dlgKrug.getPnlBojaUnutrasnjosti().setBackground(helper.getFillColor());
						dlgKrug.getPnlBojaIvice().setBackground(helper.getColor());
						dlgKrug.getTxtX().setEditable(true);
						dlgKrug.getTxtY().setEditable(true);
						dlgKrug.setVisible(true);

						try {

							int x = Integer.parseInt(dlgKrug.getTxtX().getText());
							int y = Integer.parseInt(dlgKrug.getTxtY().getText());
							Color bojaU = dlgKrug.getPnlBojaUnutrasnjosti().getBackground();
							Color boja = dlgKrug.getPnlBojaIvice().getBackground();
							int r = Integer.parseInt(dlgKrug.getTxtPoluprecnik().getText());

							if (r <= 0) {
								throw exp;
							}

							Circle k = new Circle(new Point(x, y), r, boja, bojaU);

							if (dlgKrug.isProvera()) {

								CmdUpdateCircle cmd = new CmdUpdateCircle(helper, k);
								cmd.execute();
								list.getList().add(cmd);

							}

							dlgKrug.getTxtX().setText("");
							dlgKrug.getTxtY().setText("");
							dlgKrug.getTxtPoluprecnik().setText("");

							dlgKrug.getPnlBojaIvice().setBackground(Color.WHITE);
							dlgKrug.getPnlBojaUnutrasnjosti().setBackground(Color.WHITE);
							frame.getView().repaint();
							frame.getBtnUndo().setEnabled(true);

						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, "You didn't enter a correct value");
							dlgKrug.getTxtX().setText("");
							dlgKrug.getTxtY().setText("");
							dlgKrug.getTxtPoluprecnik().setText("");
							dlgKrug.getPnlBojaIvice().setBackground(Color.WHITE);
							dlgKrug.getPnlBojaUnutrasnjosti().setBackground(Color.WHITE);

						}

					}
				}

			}
		}

	}
	// Modify END

	// Drawing START
	@SuppressWarnings("null")
	public void viewMouseClicked(MouseEvent e) {

		if (frame.getTglbtnTacka().isSelected()) {

			x = e.getX();
			y = e.getY();
			Color color = frame.getBtnColor().getBackground();
			Point t1 = new Point(x, y, color);
			CmdAddShape cmd = new CmdAddShape(model, t1);
			cmd.execute();
			list.getList().add(cmd);
			frame.getBtnUndo().setEnabled(true);

		} else if (frame.getTglbtnHexagon().isSelected()) {

			x = e.getX();
			y = e.getY();

			dlgKrug.setTitle("Hexagon properties: ");
			dlgKrug.getTxtX().setText(Integer.toString(x));
			dlgKrug.getTxtY().setText(Integer.toString(y));
			dlgKrug.getTxtX().setEditable(false);
			dlgKrug.getTxtY().setEditable(false);
			dlgKrug.getPnlBojaIvice().setVisible(false);
			dlgKrug.getPnlBojaUnutrasnjosti().setVisible(false);
			dlgKrug.getLblBojaIvice().setVisible(false);
			dlgKrug.getLblBojaUnutrasnjosti().setVisible(false);

			dlgKrug.setVisible(true);
			Exception exp = null;

			try {
				if (dlgKrug.isProvera()) {

					int r = Integer.parseInt(dlgKrug.getTxtPoluprecnik().getText());
					Color boja = frame.getBtnColor().getBackground();
					Color bojaU = frame.getBtnFillColor().getBackground();

					if (r < 0) {
						throw exp;
					}

					Hexagon hex = new Hexagon(x, y, r);
					hex.setBorderColor(boja);
					hex.setAreaColor(bojaU);
					HexagonAdapter mHexagonAdapter = new HexagonAdapter(hex);
					CmdAddShape cmd = new CmdAddShape(model, mHexagonAdapter);
					cmd.execute();
					frame.getBtnUndo().setEnabled(true);
					list.getList().add(cmd);

					dlgKrug.getTxtX().setText("");
					dlgKrug.getTxtY().setText("");
					dlgKrug.getTxtPoluprecnik().setText("");
					dlgKrug.getPnlBojaIvice().setVisible(true);
					dlgKrug.getPnlBojaUnutrasnjosti().setVisible(true);
					dlgKrug.getLblBojaIvice().setVisible(true);
					dlgKrug.getLblBojaUnutrasnjosti().setVisible(true);

				}
			} catch (Exception ex) {

				JOptionPane.showMessageDialog(null, "You didn't enter a correct value");
				dlgKrug.getTxtX().setText("");
				dlgKrug.getTxtY().setText("");
				dlgKrug.getTxtPoluprecnik().setText("");
				dlgKrug.getPnlBojaIvice().setVisible(true);
				dlgKrug.getPnlBojaUnutrasnjosti().setVisible(true);
				dlgKrug.getLblBojaIvice().setVisible(true);
				dlgKrug.getLblBojaUnutrasnjosti().setVisible(true);

			}

		} else if (frame.getTglbtnLinija().isSelected()) {

			if (counter == 0) {

				o = new Line(new Point(e.getX(), e.getY()), new Point(0, 0));
				counter++;
				return;

			} else {

				Line l1 = (Line) o;

				l1.settKrajnja(new Point(e.getX(), e.getY()));
				Color color = frame.getBtnColor().getBackground();
				l1.setColor(color);

				CmdAddShape cmd = new CmdAddShape(model, l1);
				cmd.execute();
				frame.getBtnUndo().setEnabled(true);
				list.getList().add(cmd);

				counter = 0;

			}

		} else if (frame.getTglbtnKvadrat().isSelected()) {

			Exception exp = null;
			try {

				x = e.getX();
				y = e.getY();

				dlgKvadrat.setTitle("Square properties:");
				dlgKvadrat.getTxtX().setText(Integer.toString(x));
				dlgKvadrat.getTxtY().setText(Integer.toString(y));
				dlgKvadrat.getTxtX().setEditable(false);
				dlgKvadrat.getTxtY().setEditable(false);
				dlgKvadrat.getPnlBojaIvice().setVisible(false);
				dlgKvadrat.getPnlBojaUnutrasnjosti().setVisible(false);
				dlgKvadrat.getLblBojaIvice().setVisible(false);
				dlgKvadrat.getLblBojaUnutrasnjosti().setVisible(false);
				dlgKvadrat.setVisible(true);

				if (dlgKvadrat.isProvera()) {

					int sideLength = Integer.parseInt(dlgKvadrat.getTxtDuzinaStranice().getText());
					Color color = frame.getBtnColor().getBackground();
					Color colorInner = frame.getBtnFillColor().getBackground();

					if (sideLength <= 0) {
						throw exp;
					}

					Square k = new Square(new Point(x, y), sideLength, color, colorInner);

					CmdAddShape cmd = new CmdAddShape(model, k);

					cmd.execute();
					frame.getBtnUndo().setEnabled(true);
					list.getList().add(cmd);
					dlgKvadrat.getTxtX().setText("");
					dlgKvadrat.getTxtY().setText("");
					dlgKvadrat.getTxtDuzinaStranice().setText("");
					dlgKvadrat.getPnlBojaIvice().setVisible(true);
					dlgKvadrat.getPnlBojaUnutrasnjosti().setVisible(true);
					dlgKvadrat.getLblBojaIvice().setVisible(true);
					dlgKvadrat.getLblBojaUnutrasnjosti().setVisible(true);

				}
			} catch (Exception ex) {

				JOptionPane.showMessageDialog(null, "You didn't enter a correct value");
				dlgKvadrat.getTxtX().setText("");
				dlgKvadrat.getTxtY().setText("");
				dlgKvadrat.getTxtDuzinaStranice().setText("");

				dlgKvadrat.getPnlBojaIvice().setVisible(true);
				dlgKvadrat.getPnlBojaUnutrasnjosti().setVisible(true);
				dlgKvadrat.getLblBojaIvice().setVisible(true);
				dlgKvadrat.getLblBojaUnutrasnjosti().setVisible(true);

			}

		} else if (frame.getTglbtnPravougaonik().isSelected()) {
			Exception exp = null;
			x = e.getX();
			y = e.getY();
			dlgPravougaonik.setTitle("Rectangle properties: ");
			dlgPravougaonik.getTxtX().setText(Integer.toString(x));
			dlgPravougaonik.getTxtY().setText(Integer.toString(y));
			dlgPravougaonik.getTxtX().setEditable(false);
			dlgPravougaonik.getTxtY().setEditable(false);
			dlgPravougaonik.getPnlBojaIvice().setVisible(false);
			dlgPravougaonik.getPnlBojaUnutrasnjosti().setVisible(false);
			dlgPravougaonik.getLblBojaIvice().setVisible(false);
			dlgPravougaonik.getLblBojaUnutrasnjosti().setVisible(false);

			dlgPravougaonik.setVisible(true);

			try {

				if (dlgPravougaonik.isProvera()) {

					int sideLenght = Integer.parseInt(dlgPravougaonik.getTxtDuzinaStranice().getText());
					int sideWidth = Integer.parseInt(dlgPravougaonik.getTxtSirinaStranice().getText());
					Color boja = frame.getBtnColor().getBackground();
					Color bojaU = frame.getBtnFillColor().getBackground();

					if (sideLenght <= 0 || sideWidth <= 0) {
						throw exp;
					}

					Rectangle p = new Rectangle(new Point(x, y), sideLenght, sideWidth, boja, bojaU);

					CmdAddShape cmd = new CmdAddShape(model, p);
					cmd.execute();
					frame.getBtnUndo().setEnabled(true);
					list.getList().add(cmd);

					dlgPravougaonik.getTxtX().setText("");
					dlgPravougaonik.getTxtY().setText("");
					dlgPravougaonik.getTxtDuzinaStranice().setText("");
					dlgPravougaonik.getTxtSirinaStranice().setText("");

					dlgPravougaonik.getPnlBojaIvice().setVisible(true);
					dlgPravougaonik.getPnlBojaUnutrasnjosti().setVisible(true);
					dlgPravougaonik.getLblBojaIvice().setVisible(true);
					dlgPravougaonik.getLblBojaUnutrasnjosti().setVisible(true);

				}

			} catch (Exception x) {

				JOptionPane.showMessageDialog(null, "You didn't enter a correct value");
				dlgPravougaonik.getTxtX().setText("");
				dlgPravougaonik.getTxtY().setText("");
				dlgPravougaonik.getTxtDuzinaStranice().setText("");
				dlgPravougaonik.getTxtSirinaStranice().setText("");

			}

		} else if (frame.getTglbtnKrug().isSelected()) {

			x = e.getX();
			y = e.getY();

			dlgKrug.setTitle("Circle properties: ");
			dlgKrug.getTxtX().setText(Integer.toString(x));
			dlgKrug.getTxtY().setText(Integer.toString(y));
			dlgKrug.getTxtX().setEditable(false);
			dlgKrug.getTxtY().setEditable(false);
			dlgKrug.getPnlBojaIvice().setVisible(false);
			dlgKrug.getPnlBojaUnutrasnjosti().setVisible(false);
			dlgKrug.getLblBojaIvice().setVisible(false);
			dlgKrug.getLblBojaUnutrasnjosti().setVisible(false);

			dlgKrug.setVisible(true);
			Exception exp = null;

			try {
				if (dlgKrug.isProvera()) {

					int radius = Integer.parseInt(dlgKrug.getTxtPoluprecnik().getText());
					Color color = frame.getBtnColor().getBackground();
					Color colorInner = frame.getBtnFillColor().getBackground();

					if (radius < 0) {
						throw exp;
					}

					Circle kr = new Circle(new Point(x, y), radius, color, colorInner);
					CmdAddShape cmd = new CmdAddShape(model, kr);
					cmd.execute();
					frame.getBtnUndo().setEnabled(true);
					list.getList().add(cmd);

					dlgKrug.getTxtX().setText("");
					dlgKrug.getTxtY().setText("");
					dlgKrug.getTxtPoluprecnik().setText("");
					dlgKrug.getPnlBojaIvice().setVisible(true);
					dlgKrug.getPnlBojaUnutrasnjosti().setVisible(true);
					dlgKrug.getLblBojaIvice().setVisible(true);
					dlgKrug.getLblBojaUnutrasnjosti().setVisible(true);

				}
			} catch (Exception ex) {

				JOptionPane.showMessageDialog(null, "You didn't enter a correct value");
				dlgKrug.getTxtX().setText("");
				dlgKrug.getTxtY().setText("");
				dlgKrug.getTxtPoluprecnik().setText("");
				dlgKrug.getPnlBojaIvice().setVisible(true);
				dlgKrug.getPnlBojaUnutrasnjosti().setVisible(true);
				dlgKrug.getLblBojaIvice().setVisible(true);
				dlgKrug.getLblBojaUnutrasnjosti().setVisible(true);

			}

		}
		// Selection START

		if (frame.getTglbtnSelektuj().isSelected()) {

			x = e.getX();
			y = e.getY();
			check = 0;

			for (int i = model.getShapes().size() - 1; i >= 0; i--) {

				if (model.getShapes().get(i) instanceof Point) {

					Point helper = (Point) model.getShapes().get(i);

					if (helper.contains(x, y) && model.getShapes().get(i).isSelected()) {

						selectedList.remove(helper);
						CmdDeselectShape cmd = new CmdDeselectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);

						notifyAllObservers();
						return;

					} else if (helper.contains(x, y)) {

						selectedList.add(helper);
						CmdSelectShape cmd = new CmdSelectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);

						notifyAllObservers();
						return;

					} else {
						check++;
					}
				} else if (model.getShapes().get(i) instanceof HexagonAdapter) {

					HexagonAdapter helper = (HexagonAdapter) model.getShapes().get(i);

					if (helper.contains(x, y) && model.getShapes().get(i).isSelected()) {

						selectedList.remove(helper);
						CmdDeselectShape cmd = new CmdDeselectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);

						notifyAllObservers();
						return;

					} else if (helper.contains(x, y)) {

						selectedList.add(helper);
						CmdSelectShape cmd = new CmdSelectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);
						notifyAllObservers();
						return;

					} else {
						check++;
					}
				} else if (model.getShapes().get(i) instanceof Rectangle) {

					Rectangle helper = (Rectangle) model.getShapes().get(i);

					if (helper.contains(x, y) && model.getShapes().get(i).isSelected()) {

						selectedList.remove(helper);
						CmdDeselectShape cmd = new CmdDeselectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);
						notifyAllObservers();
						return;

					} else if (helper.contains(x, y)) {

						selectedList.add(helper);
						CmdSelectShape cmd = new CmdSelectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);
						notifyAllObservers();
						return;

					} else {
						check++;
					}
				} else if (model.getShapes().get(i) instanceof Square) {

					Square helper = (Square) model.getShapes().get(i);

					if (helper.contains(x, y) && model.getShapes().get(i).isSelected()) {

						selectedList.remove(helper);
						CmdDeselectShape cmd = new CmdDeselectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);
						notifyAllObservers();
						return;

					} else if (helper.contains(x, y)) {

						selectedList.add(helper);
						CmdSelectShape cmd = new CmdSelectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);
						notifyAllObservers();
						return;

					} else {
						check++;
					}
				} else if (model.getShapes().get(i) instanceof Circle) {

					Circle helper = (Circle) model.getShapes().get(i);

					if (helper.contains(x, y) && model.getShapes().get(i).isSelected()) {

						selectedList.remove(helper);
						CmdDeselectShape cmd = new CmdDeselectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);
						notifyAllObservers();
						return;

					} else if (helper.contains(x, y)) {

						selectedList.add(helper);
						CmdSelectShape cmd = new CmdSelectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);
						notifyAllObservers();
						return;

					} else {
						check++;
					}
				} else if (model.getShapes().get(i) instanceof Line) {

					Line helper = (Line) model.getShapes().get(i);

					if (helper.contains(x, y) && model.getShapes().get(i).isSelected()) {

						selectedList.remove(helper);
						CmdDeselectShape cmd = new CmdDeselectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);
						notifyAllObservers();
						return;

					} else if (helper.contains(x, y)) {

						selectedList.add(helper);
						CmdSelectShape cmd = new CmdSelectShape(model, helper, frame.getView().getGraphics());
						cmd.execute();
						list.getList().add(cmd);
						notifyAllObservers();
						return;

					} else {
						check++;
					}
				}

			}
			if (check == model.getShapes().size()) {
				for (int j = selectedList.size() - 1; j >= 0; j--) {

					selectedList.get(j).setSelected(false);

					CmdDeselectShape cmd = new CmdDeselectShape(model, selectedList.get(j),
							frame.getView().getGraphics());

					cmd.execute();
					list.getList().add(cmd);

				}
				selectedList.clear();
				check = 0;
			}

		}
		// Selection END

		notifyAllObservers();

	}
	// Drawing END

	// Delete START
	public void viewButtonDeleteClicked(ActionEvent arg0) {

		int n = JOptionPane.showConfirmDialog(null, "Are you sure, that you want to continue?");

		if (frame.getTglbtnObrisiOblik().isSelected()) {
			for (int j = selectedList.size() - 1; j >= 0; j--) {

				if (n == 0) {

					CmdRemoveShape cmd = new CmdRemoveShape(model, selectedList.get(j));
					cmd.execute();
					list.getList().add(cmd);
					frame.getView().repaint();
					frame.getBtnUndo().setEnabled(true);
					selectedList.remove(j);
					notifyAllObservers();
				}

			}

		}

	}
	// Delete END

	// Undo & Redo START
	public void undo() {

		list.undo();

		notifyAllObservers();

	}

	public void redo() {

		list.redo();

		notifyAllObservers();

	}
	// Undo & Redo END

	public void bringToBack() {

		CmdBringToBack cmd = new CmdBringToBack(model);
		cmd.execute();
		list.getList().add(cmd);

	}

	public void oneToBack() {

		CmdOneToBack cmd = new CmdOneToBack(model);
		cmd.execute();
		list.getList().add(cmd);

	}

	public void oneToFront() {

		CmdOneToFront cmd = new CmdOneToFront(model);
		cmd.execute();
		list.getList().add(cmd);

	}

	public void bringToFront() {

		CmdBringToFront cmd = new CmdBringToFront(model);
		cmd.execute();
		list.getList().add(cmd);

	}

	// Save Object & Log START
	public void save(File fileToSave, File fileToSaveLog) throws IOException {

		SaveManager savePainting = new SaveManager(new SavePainting());
		SaveManager saveLog = new SaveManager(new SaveLog());

		savePainting.save(model, fileToSave);
		saveLog.save(frame, fileToSaveLog);

	}
	// Save Object & Log START

	@SuppressWarnings("unchecked")
	public void load(File fileToLoad) throws ClassNotFoundException, IOException {

		DrawingFrame.getTxtArea().setText("");
		File file = new File(fileToLoad.getAbsolutePath().replaceAll("bin", "txt"));
		BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
		String line;

		while ((line = mBufferedReader.readLine()) != null) {
			DrawingFrame.getTxtArea().append(line + '\n');
		}

		mBufferedReader.close();

		ObjectInputStream mObjectInputStream = new ObjectInputStream(new FileInputStream(fileToLoad));

		try {

			model.getShapes().clear();
			list.getList().clear();
			selectedList.clear();
			list.getUndo().clear();
			frame.getBtnUndo().setEnabled(false);
			frame.getBtnRedo().setEnabled(false);
			frame.getView().repaint();

			model.getShapes().addAll((ArrayList<Shape>) mObjectInputStream.readObject());

			frame.getView().repaint();

		} catch (SocketTimeoutException exc) {
			// you got the timeout
			exc.printStackTrace();
		} catch (InvalidClassException exc) {
			exc.printStackTrace();
		} catch (EOFException exc) {
			mObjectInputStream.close();
			exc.printStackTrace();
			// end of stream
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {

				selectedList.add(model.getShapes().get(i));

			}
		}

		notifyAllObservers();
		mObjectInputStream.close();
	}

	public void loadOneByOne(File fileToLoad) throws IOException {

		list.getList().clear();
		list.getUndo().clear();
		frame.getBtnUndo().setEnabled(false);
		frame.getBtnRedo().setEnabled(false);
		selectedList.clear();
		selList.clear();

		model.getShapes().clear();
		notifyAllObservers();
		backList.clear();
		DrawingFrame.getTxtArea().setText("");
		current = -1;
		currentUndo = -1;
		list.setCurrent(0);
		pro = true;
		pp = 0;

		BufferedReader br = new BufferedReader(new FileReader(fileToLoad));
		String line;
		Shape s = null;

		while ((line = br.readLine()) != null) {
			backList.add(line);
		}

		for (int i = 0; i < backList.size(); i++) {

			line = backList.get(i);

			if (line.contains("Tacka")) {

				int x = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(",")));
				int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(")")));
				String color = line.substring(line.lastIndexOf(":") + 1);
				Color c = Color.BLACK;

				c = new Color(Integer.parseInt(color));
				s = new Point(x, y, c);

			}

			else if (line.contains("Linija")) {

				int x = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(",")));
				int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(")")));
				int x1 = Integer.parseInt(line.substring(line.lastIndexOf("(") + 1, line.lastIndexOf(",")));
				int y1 = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1, line.lastIndexOf(")")));
				String color = line.substring(line.lastIndexOf(":") + 1);
				Color c = Color.BLACK;

				c = new Color(Integer.parseInt(color));
				s = new Line(new Point(x, y), new Point(x1, y1), c);

			} else if (line.contains("Kvadrat")) {

				int x = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(",")));
				int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(")")));
				int length = Integer.parseInt(line.substring(line.lastIndexOf(")") + 2, line.lastIndexOf("r") - 1));

				String color = line.substring(line.indexOf("-") + 1, line.lastIndexOf("-") - 1);
				String color1 = line.substring(line.lastIndexOf("-"));
				Color c = Color.BLACK;
				Color c1 = Color.WHITE;

				c = new Color(Integer.parseInt(color));
				c1 = new Color(Integer.parseInt(color1));

				s = new Square(new Point(x, y), length, c, c1);

			} else if (line.contains("Pravougaonik")) {

				int x = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(",")));
				int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(")")));
				int length = Integer.parseInt(line.substring(line.lastIndexOf(")") + 2, line.indexOf("/")));
				int height = Integer.parseInt(line.substring(line.indexOf("/") + 1, line.lastIndexOf("r") - 1));

				String color = line.substring(line.indexOf("-") + 1, line.lastIndexOf("-") - 1);

				String color1 = line.substring(line.lastIndexOf("-"));
				Color c = Color.BLACK;
				Color c1 = Color.WHITE;

				c = new Color(Integer.parseInt(color));
				c1 = new Color(Integer.parseInt(color1));

				s = new Rectangle(new Point(x, y), length, height, c, c1);
			} else if (line.contains("Heksagon")) {
				int x = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(",")));
				int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(")")));

				int r = Integer.parseInt(line.substring(line.lastIndexOf(")") + 2, line.indexOf(" rgb boja-")));
				String color = line.substring(line.indexOf("-") + 1, line.lastIndexOf(":"));

				String color1 = line.substring(line.lastIndexOf("-"));
				Color c = Color.BLACK;
				Color c1 = Color.WHITE;

				c = new Color(Integer.parseInt(color));
				c1 = new Color(Integer.parseInt(color1));

				HexagonAdapter hex = new HexagonAdapter(new Hexagon(x, y, r));

				hex.getHexagon().setBorderColor(c);
				hex.getHexagon().setAreaColor(c1);
				s = hex;

			} else if (line.contains("Krug")) {

				int x = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(",")));
				int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(")")));
				int r = Integer.parseInt(line.substring(line.lastIndexOf(")") + 2, line.indexOf(" rgb boja-")));
				String color = line.substring(line.indexOf("-") + 1, line.lastIndexOf(":"));
				String color1 = line.substring(line.lastIndexOf("-"));
				Color c = Color.BLACK;
				Color c1 = Color.WHITE;
				c = new Color(Integer.parseInt(color));
				c1 = new Color(Integer.parseInt(color1));

				Circle cr = new Circle(new Point(x, y), r);
				cr.setColor(c);
				cr.setFillColor(c1);
				s = cr;

			}

			if (line.contains("New")) {
				
				CmdAddShape cmd = new CmdAddShape(model, s);

				list.getList().add(cmd);

				current++;

			} else if (line.contains("Selected")) {

				selList.add(s);

				CmdSelectShape cmd = new CmdSelectShape(model, s, frame.getView().getGraphics());

				list.getList().add(cmd);

				current++;

			} else if (line.contains("Deselected")) {

				CmdDeselectShape cmd = new CmdDeselectShape(model, s, frame.getView().getGraphics());
				selList.remove(s);
				list.getList().add(cmd);

				current++;

			} else if (line.contains("Modified")) {

				CmdUpdateShape cmd = new CmdUpdateShape(model, s, selList.get(0), frame.getGraphics(), selList);

				list.getList().add(cmd);

				current++;

			} else if (line.contains("Deleted")) {
				selList.clear();

				CmdRemoveShape cmd = new CmdRemoveShape(model, s);

				list.getList().add(cmd);
				s.setSelected(true);

				current++;

			} else if (line.contains("Moved behind the selected object")) {

				CmdOneToFront cmd = new CmdOneToFront(model);
				list.getList().add(cmd);

				current++;

			} else if (line.contains("Moved in front of the selected object")) {

				CmdOneToBack cmd = new CmdOneToBack(model);
				list.getList().add(cmd);

				current++;

			} else if (line.contains("Moved to back")) {

				CmdBringToBack cmd = new CmdBringToBack(model);
				list.getList().add(cmd);

				current++;

			} else if (line.contains("Moved to front")) {

				CmdBringToFront cmd = new CmdBringToFront(model);
				list.getList().add(cmd);

				current++;

			}

		}
		
		Iterator<Command> it = list.getList().iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}

		br.close();
	}

	public void go() {

		list.go();

		if (backList.get(list.getCurrent() - 1).contains("Deleted")) {
			selList.clear();
		}

		if (list.getList().size() == list.getCurrent()) {

			frame.getBtnGo().setEnabled(false);
			selectedList.clear();

			for (int i = 0; i < model.getShapes().size(); i++) {

				if (model.getShapes().get(i).isSelected()) {
					selectedList.add(model.getShapes().get(i));
				}
			}

			frame.getView().repaint();

			notifyAllObservers();

			list.getList().clear();
			frame.getBtnUndo().setEnabled(false);
			frame.getBtnRedo().setEnabled(false);

		}

	}

	public void backup() {

		frame.getBtnGo().setEnabled(false);
		selectedList.clear();

		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {

				selectedList.add(model.getShapes().get(i));

			}
		}

		frame.getView().repaint();
		notifyAllObservers();
		list.getList().clear();

	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyAllObservers() {

		int counter = 0;

		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {

				counter++;

			}
		}

		if (counter == 1) {

			enabled = true;
			enabledDelete = true;

		} else if (counter > 1) {

			enabled = false;
			enabledDelete = true;

		} else {

			enabled = false;
			enabledDelete = false;

		}
		for (Observer mObserver : observers) {
			mObserver.update(enabled, enabledDelete);
		}

	}

	public static boolean isEnabled() {
		return enabled;
	}

	public static void setEnabled(boolean enable) {
		DrawingController.enabled = enable;
	}

	public CommandStack getList() {
		return list;
	}

	public void setList(CommandStack list) {
		this.list = list;
	}

	public ArrayList<String> getBackList() {
		return backList;
	}

	public void setBackList(ArrayList<String> backList) {
		this.backList = backList;
	}

	public boolean isPro() {
		return pro;
	}

	public void setPro(boolean pro) {
		this.pro = pro;
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public ArrayList<Shape> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(ArrayList<Shape> selectedList) {
		this.selectedList = selectedList;
	}

	public ArrayList<Shape> getSelList() {
		return selList;
	}

	public void setSelList(ArrayList<Shape> selList) {
		this.selList = selList;
	}

	public ArrayList<Observer> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<Observer> observers) {
		this.observers = observers;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public static boolean isEnabledDelete() {
		return enabledDelete;
	}

	public static void setEnabledDelete(boolean enabledDelete) {
		DrawingController.enabledDelete = enabledDelete;
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}

	public DlgKvadrat getDlgKvadrat() {
		return dlgKvadrat;
	}

	public void setDlgKvadrat(DlgKvadrat dlgKvadrat) {
		this.dlgKvadrat = dlgKvadrat;
	}

	public DlgPravougaonik getDlgPravougaonik() {
		return dlgPravougaonik;
	}

	public void setDlgPravougaonik(DlgPravougaonik dlgPravougaonik) {
		this.dlgPravougaonik = dlgPravougaonik;
	}

	public DlgKrug getDlgKrug() {
		return dlgKrug;
	}

	public void setDlgKrug(DlgKrug dlgKrug) {
		this.dlgKrug = dlgKrug;
	}

	public DlgTacka getDlgTacka() {
		return dlgTacka;
	}

	public void setDlgTacka(DlgTacka dlgTacka) {
		this.dlgTacka = dlgTacka;
	}

	public DlgLinija getDlgLinija() {
		return dlgLinija;
	}

	public void setDlgLinija(DlgLinija dlgLinija) {
		this.dlgLinija = dlgLinija;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCurrentUndo() {
		return currentUndo;
	}

	public void setCurrentUndo(int currentUndo) {
		this.currentUndo = currentUndo;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	public int getPr() {
		return pr;
	}

	public void setPr(int pr) {
		this.pr = pr;
	}

	public File getFileToLoad() {
		return fileToLoad;
	}

	public void setFileToLoad(File fileToLoad) {
		this.fileToLoad = fileToLoad;
	}

}
