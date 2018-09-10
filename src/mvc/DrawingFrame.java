package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import observer.Observer;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextArea;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class DrawingFrame extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawingView view = new DrawingView();
	private DrawingController controller;

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JToggleButton tglbtnSelect;
	private JToggleButton tglbtnModify;
	private JToggleButton tglbtnDelete;


	public JToggleButton getTglbtnSelektuj() {
		return tglbtnSelect;
	}

	public JToggleButton getTglbtnIzmeniOblik() {
		return tglbtnModify;
	}

	public JToggleButton getTglbtnObrisiOblik() {
		return tglbtnDelete;
	}

	JToggleButton tglbtnTacka;

	public JToggleButton getTglbtnLinija() {
		return tglBtnLine;
	}

	public JToggleButton getTglbtnKvadrat() {
		return tglBtnSquare;
	}

	public JToggleButton getTglbtnPravougaonik() {
		return tglBtnRectangle;
	}

	public JToggleButton getTglbtnKrug() {
		return tglBtnCircle;
	}

	JToggleButton tglBtnLine;
	JToggleButton tglBtnSquare;
	JToggleButton tglBtnRectangle;
	JToggleButton tglBtnCircle;
	private JPanel pnlColor;
	private JButton btnColor;
	private JButton btnFillColor;
	private JButton btnUndo;
	private JButton btnRedo;

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public boolean check() {

		return rootPaneCheckingEnabled;

	}

	private JToggleButton tglbtnHexagon;
	private JButton button;
	private JLabel lblBorderColor;
	private JLabel lblFillColor;
	private JButton button_1;
	private JButton button_2;
	private JButton button_4;
	private JButton button_3;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;

	private JPanel pnlLogging;

	private JButton btnToBack;
	private JButton btnToFront;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmSave;
	private JMenuItem mntmOpen;
	private static JTextArea txtArea;
	private JButton btnGo;

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public JToggleButton getTglbtnTacka() {
		return tglbtnTacka;
	}

	public JButton getBtnGo() {
		return btnGo;
	}

	public void setBtnGo(JButton btnGo) {
		this.btnGo = btnGo;
	}

	public DrawingFrame() {
		setTitle("Marko Karapandzic IT45/2015.");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 800, 600);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser c = new JFileChooser();
				FileNameExtensionFilter f = new FileNameExtensionFilter("Bin", "bin");
				c.setFileFilter(f);

				int userSelection = c.showSaveDialog(null);
				
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					
					File fileToSave = c.getSelectedFile();
					File fileToSaveLog;
					String filePath = fileToSave.getAbsolutePath();
					
					if (!filePath.endsWith(".bin") && !filePath.contains(".")) {
						
						fileToSave = new File(filePath + ".bin");
						fileToSaveLog = new File(filePath + ".txt");

					}

					String filename = fileToSave.getPath();

					System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					System.out.println(filename.substring(filename.lastIndexOf("."), filename.length()));
					
					if (filename.substring(filename.lastIndexOf("."), filename.length()).contentEquals(".bin")) {
						
						try {
							
							filename = fileToSave.getAbsolutePath().substring(0, filename.lastIndexOf(".")) + ".txt";
							System.out.println(filename);
							fileToSaveLog = new File(filename);
							controller.save(fileToSave, fileToSaveLog);
							
						} catch (IOException e) {
							
							e.printStackTrace();
							
						}

					} else {
						JOptionPane.showMessageDialog(null, "You didn't enter a corrent value");

					}
				}

			}
		});
		mnFile.add(mntmSave);

		mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
				FileNameExtensionFilter f = new FileNameExtensionFilter("bin", "bin", "txt");

				c.setFileFilter(f);

				c.setDialogTitle("Open");
				int userSelection = c.showOpenDialog(null);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					
					File fileToLoad = c.getSelectedFile();
					String filename = fileToLoad.getPath();
					
					if (filename.substring(filename.lastIndexOf("."), filename.length()).contentEquals(".bin")) {
						try {
							controller.load(fileToLoad);
						} catch (IOException m) {
							// TODO Auto-generated catch block
							m.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						btnGo.setEnabled(false);

					} else if (filename.substring(filename.lastIndexOf("."), filename.length()).contentEquals(".txt")) {

						try {

							controller.loadOneByOne(fileToLoad);
							
							if (!controller.getBackList().get(0).contains("New")
									|| !controller.getBackList().get(0).contains("-")) {
								btnGo.setEnabled(false);
								controller.getList().getList().clear();
								controller.getList().getUndo().clear();
							} else {
								btnGo.setEnabled(true);
							}

						} catch (IOException m) {
							m.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "You didn't select a corrent File");
					}
				}

			}
		});
		mnFile.add(mntmOpen);

		btnGo = new JButton();
		try {
			btnGo.setText("Next");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnGo.setEnabled(false);
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnGo.isEnabled()) {
					try {
						controller.go();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		menuBar.add(btnGo);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 134, 446, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel pnlMeni = new JPanel();
		GridBagConstraints gbc_pnlMeni = new GridBagConstraints();
		gbc_pnlMeni.anchor = GridBagConstraints.NORTH;
		gbc_pnlMeni.insets = new Insets(0, 0, 5, 5);
		gbc_pnlMeni.gridx = 0;
		gbc_pnlMeni.gridy = 0;
		contentPane.add(pnlMeni, gbc_pnlMeni);
		GridBagLayout gbl_pnlMeni = new GridBagLayout();
		gbl_pnlMeni.columnWidths = new int[] { 160, 50, 160, 30, 160, 50, 160, 0 };
		gbl_pnlMeni.rowHeights = new int[] { 0, 0 };
		gbl_pnlMeni.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlMeni.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlMeni.setLayout(gbl_pnlMeni);

		JPanel pnlOblici = new JPanel();
		pnlOblici.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_pnlOblici = new GridBagConstraints();
		gbc_pnlOblici.insets = new Insets(0, 0, 0, 5);
		gbc_pnlOblici.fill = GridBagConstraints.WEST;
		gbc_pnlOblici.gridx = 0;
		gbc_pnlOblici.gridy = 0;
		pnlMeni.add(pnlOblici, gbc_pnlOblici);
		GridBagLayout gbl_pnlOblici = new GridBagLayout();
		gbl_pnlOblici.columnWidths = new int[] { 0, 0 };
		gbl_pnlOblici.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlOblici.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnlOblici.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlOblici.setLayout(gbl_pnlOblici);

		JLabel lblOblici = new JLabel("");
		lblOblici.setForeground(new Color(255, 245, 238));
		lblOblici.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 17));
		lblOblici.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblOblici = new GridBagConstraints();
		gbc_lblOblici.insets = new Insets(0, 0, 5, 0);
		gbc_lblOblici.gridx = 0;
		gbc_lblOblici.gridy = 0;
		pnlOblici.add(lblOblici, gbc_lblOblici);

		tglbtnTacka = new JToggleButton("Point");

		buttonGroup.add(tglbtnTacka);
		tglbtnTacka.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_tglbtnTacka = new GridBagConstraints();
		gbc_tglbtnTacka.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnTacka.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnTacka.gridx = 0;
		gbc_tglbtnTacka.gridy = 1;
		pnlOblici.add(tglbtnTacka, gbc_tglbtnTacka);

		tglBtnLine = new JToggleButton("Line");
		tglBtnLine.setForeground(new Color(0, 0, 0));
		buttonGroup.add(tglBtnLine);
		GridBagConstraints gbc_tglbtnLinija = new GridBagConstraints();
		gbc_tglbtnLinija.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnLinija.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnLinija.gridx = 0;
		gbc_tglbtnLinija.gridy = 2;
		pnlOblici.add(tglBtnLine, gbc_tglbtnLinija);

		tglBtnCircle = new JToggleButton("Circle");
		tglBtnCircle.setForeground(new Color(0, 0, 0));
		buttonGroup.add(tglBtnCircle);
		GridBagConstraints gbc_tglbtnKrug = new GridBagConstraints();
		gbc_tglbtnKrug.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnKrug.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnKrug.gridx = 0;
		gbc_tglbtnKrug.gridy = 3;
		pnlOblici.add(tglBtnCircle, gbc_tglbtnKrug);

		tglBtnSquare = new JToggleButton("Square");
		tglBtnSquare.setForeground(new Color(0, 0, 0));
		buttonGroup.add(tglBtnSquare);
		GridBagConstraints gbc_tglbtnKvadrat = new GridBagConstraints();
		gbc_tglbtnKvadrat.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnKvadrat.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnKvadrat.gridx = 0;
		gbc_tglbtnKvadrat.gridy = 4;
		pnlOblici.add(tglBtnSquare, gbc_tglbtnKvadrat);

		tglBtnRectangle = new JToggleButton("Rectangle");
		tglBtnRectangle.setForeground(new Color(0, 0, 0));
		buttonGroup.add(tglBtnRectangle);
		GridBagConstraints gbc_tglbtnPravougaonik = new GridBagConstraints();
		gbc_tglbtnPravougaonik.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnPravougaonik.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnPravougaonik.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnPravougaonik.gridx = 0;
		gbc_tglbtnPravougaonik.gridy = 5;
		pnlOblici.add(tglBtnRectangle, gbc_tglbtnPravougaonik);

		tglbtnHexagon = new JToggleButton("Hexagon");
		tglbtnHexagon.setForeground(new Color(0, 0, 0));
		buttonGroup.add(tglbtnHexagon);
		GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
		gbc_tglbtnHexagon.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnHexagon.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnHexagon.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnHexagon.gridx = 0;
		gbc_tglbtnHexagon.gridy = 6;
		pnlOblici.add(tglbtnHexagon, gbc_tglbtnHexagon);

		JPanel pnlKomande = new JPanel();
		GridBagConstraints gbc_pnlKomande = new GridBagConstraints();
		gbc_pnlKomande.fill = GridBagConstraints.WEST;
		gbc_pnlKomande.gridx = 1;
		gbc_pnlKomande.gridy = 0;
		pnlMeni.add(pnlKomande, gbc_pnlKomande);
		GridBagLayout gbl_pnlKomande = new GridBagLayout();
		gbl_pnlKomande.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlKomande.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlKomande.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlKomande.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlKomande.setLayout(gbl_pnlKomande);
		
		// ========================================================COMMANDS========================================================

		JLabel lblKomande = new JLabel("");
		lblKomande.setForeground(new Color(255, 245, 238));
		lblKomande.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 17));
		GridBagConstraints gbc_lblKomande = new GridBagConstraints();
		gbc_lblKomande.gridwidth = 5;
		gbc_lblKomande.insets = new Insets(0, 0, 5, 0);
		gbc_lblKomande.gridx = 0;
		gbc_lblKomande.gridy = 0;
		pnlKomande.add(lblKomande, gbc_lblKomande);

		tglbtnSelect = new JToggleButton("Select");
		buttonGroup.add(tglbtnSelect);
		GridBagConstraints gbc_tglbtnSelektuj = new GridBagConstraints();
		gbc_tglbtnSelektuj.gridwidth = 5;
		gbc_tglbtnSelektuj.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSelektuj.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSelektuj.gridx = 0;
		gbc_tglbtnSelektuj.gridy = 1;
		pnlKomande.add(tglbtnSelect, gbc_tglbtnSelektuj);
		tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.setEnabled(false);

		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.viewButtonModifyClicked(arg0);
				controller.getList()
						.newCmd(controller.getList().getCurrent() != controller.getList().getList().size() - 1);
				btnRedo.setEnabled(false);
				if (btnGo.isEnabled()) {
					controller.backup();
				}
			}
		});

		btnToBack = new JButton("Background");
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if ((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
					controller.bringToBack();
				} else {
					controller.oneToBack();
				}
				controller.getList()
						.newCmd(controller.getList().getCurrent() != controller.getList().getList().size() - 1);
				btnRedo.setEnabled(false);
				if (btnGo.isEnabled()) {
					controller.backup();
				}
			}
		});
		GridBagConstraints gbc_button_9 = new GridBagConstraints();
		gbc_button_9.insets = new Insets(0, 0, 5, 0);
		gbc_button_9.gridx = 3;
		gbc_button_9.gridy = 3;
		pnlKomande.add(btnToBack, gbc_button_9);

		btnToFront = new JButton("Frontground");
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
					controller.bringToFront();
				} else {
					controller.oneToFront();
				}
				controller.getList()
						.newCmd(controller.getList().getCurrent() != controller.getList().getList().size() - 1);
				btnRedo.setEnabled(false);
				if (btnGo.isEnabled()) {
					controller.backup();
				}
			}
		});
		GridBagConstraints gbc_button_11 = new GridBagConstraints();
		gbc_button_11.insets = new Insets(0, 0, 5, 0);
		gbc_button_11.gridx = 3;
		gbc_button_11.gridy = 2;
		pnlKomande.add(btnToFront, gbc_button_11);

		btnUndo = new JButton();
		btnUndo.setText("Undo");
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.anchor = GridBagConstraints.SOUTH;
		gbc_btnUndo.insets = new Insets(5, 5, 5, 5);
		gbc_btnUndo.gridx = 1;
		gbc_btnUndo.gridy = 7;
		pnlKomande.add(btnUndo, gbc_btnUndo);
		btnUndo.setEnabled(false);
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (btnUndo.isEnabled()) {
					controller.undo();
					if (btnGo.isEnabled()) {
						controller.backup();
					}
					btnRedo.setEnabled(true);
				}

				if (controller.getList().getCurrent() == -1) {
					btnUndo.setEnabled(false);

				}

			}
		});

		btnRedo = new JButton();
		btnRedo.setText("Redo");
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.anchor = GridBagConstraints.SOUTH;
		gbc_btnRedo.insets = new Insets(5, 5, 5, 5);
		gbc_btnRedo.gridx = 3;
		gbc_btnRedo.gridy = 7;
		pnlKomande.add(btnRedo, gbc_btnRedo);
		btnRedo.setEnabled(false);
		btnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (btnRedo.isEnabled()) {
					controller.redo();
					btnUndo.setEnabled(true);
					if (btnGo.isEnabled()) {
						controller.backup();
					}
				}

				if (controller.getList().getCurrent() == controller.getList().getList().size() - 1) {
					btnRedo.setEnabled(false);
				}

			}
		});

		buttonGroup.add(tglbtnModify);
		GridBagConstraints gbc_tglbtnIzmeniOblik = new GridBagConstraints();
		gbc_tglbtnIzmeniOblik.gridwidth = 5;
		gbc_tglbtnIzmeniOblik.fill = GridBagConstraints.BOTH;
		gbc_tglbtnIzmeniOblik.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnIzmeniOblik.gridx = 0;
		gbc_tglbtnIzmeniOblik.gridy = 4;
		pnlKomande.add(tglbtnModify, gbc_tglbtnIzmeniOblik);

		tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setEnabled(false);

		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.viewButtonDeleteClicked(arg0);
				controller.getList()
						.newCmd(controller.getList().getCurrent() != controller.getList().getList().size() - 1);
				btnRedo.setEnabled(false);
				if (btnGo.isEnabled()) {
					controller.backup();
				}
			}
		});
		buttonGroup.add(tglbtnDelete);
		GridBagConstraints gbc_tglbtnObrisiOblik = new GridBagConstraints();
		gbc_tglbtnObrisiOblik.gridwidth = 5;
		gbc_tglbtnObrisiOblik.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnObrisiOblik.gridx = 0;
		gbc_tglbtnObrisiOblik.gridy = 5;
		pnlKomande.add(tglbtnDelete, gbc_tglbtnObrisiOblik);

		JTextField visina = new JTextField(5);
		JTextField sirina = new JTextField(5);

		JPanel pnlUnos = new JPanel();
		pnlUnos.add(new JLabel("Enter width:"));
		pnlUnos.add(visina);
		pnlUnos.add(Box.createVerticalBox());
		pnlUnos.add(new JLabel("Enter height:"));
		pnlUnos.add(sirina);

		view.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				view.repaint();
			}

		});

		view.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				controller.viewMouseClicked(e);

				controller.getList()
						.newCmd(controller.getList().getCurrent() != controller.getList().getList().size() - 1);
				btnRedo.setEnabled(false);
				
				if (btnGo.isEnabled()) {
					controller.backup();
				}

			}
		});

		view.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlCrtez = new GridBagConstraints();
		gbc_pnlCrtez.insets = new Insets(0, 0, 5, 5);
		gbc_pnlCrtez.fill = GridBagConstraints.BOTH;
		gbc_pnlCrtez.gridx = 0;
		gbc_pnlCrtez.gridy = 1;
		contentPane.add(view, gbc_pnlCrtez);

		pnlColor = new JPanel();
		pnlColor.setBackground(new Color(169, 169, 169));

		GridBagConstraints gbc_pnlColor = new GridBagConstraints();
		gbc_pnlColor.anchor = GridBagConstraints.NORTHWEST;
		gbc_pnlColor.gridy = 1;
		gbc_pnlColor.gridx = 1;
		gbc_pnlColor.insets = new Insets(0, 5, 5, 5);

		contentPane.add(pnlColor, gbc_pnlColor);

		btnColor = new JButton("");
		btnColor.setPreferredSize(new Dimension(30, 30));
		btnColor.setBackground(Color.BLACK);
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnColor.setBackground(JColorChooser.showDialog(null, "Choose color", Color.BLACK));
			}
		});
		pnlColor.setLayout(new GridLayout(7, 8, 6, 4));

		lblBorderColor = new JLabel("Edge:");
		lblBorderColor.setForeground(new Color(255, 255, 255));
		pnlColor.add(lblBorderColor);
		pnlColor.add(btnColor);

		btnFillColor = new JButton("");
		btnFillColor.setBackground(Color.WHITE);
		btnFillColor.setPreferredSize(new Dimension(30, 30));
		btnFillColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnFillColor.setBackground(JColorChooser.showDialog(null, "Choose color", Color.WHITE));
			}
		});

		lblFillColor = new JLabel("Inner:");
		lblFillColor.setForeground(new Color(255, 255, 255));
		pnlColor.add(lblFillColor);
		pnlColor.add(btnFillColor);

		pnlLogging = new JPanel();
		pnlLogging.setLayout(new BorderLayout());

		GridBagConstraints gbc_pnlLogging = new GridBagConstraints();
		gbc_pnlLogging.fill = GridBagConstraints.BOTH;
		gbc_pnlLogging.insets = new Insets(0, 0, 5, 5);
		gbc_pnlLogging.gridx = 0;
		gbc_pnlLogging.gridy = 2;
		contentPane.add(pnlLogging, gbc_pnlLogging);

		txtArea = new JTextArea();
		txtArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(txtArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		pnlLogging.add(scroll);

		GridBagConstraints gbc_scrP = new GridBagConstraints();
		gbc_scrP.gridheight = 3;
		gbc_scrP.insets = new Insets(0, 0, 5, 0);
		gbc_scrP.fill = GridBagConstraints.BOTH;
		gbc_scrP.gridx = 1;
		gbc_scrP.gridy = 0;
	}

	public static JTextArea getTxtArea() {
		return txtArea;
	}

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	@Override
	public void update(boolean enabled, boolean enabledDelete) {

		this.tglbtnModify.setEnabled(enabled);
		this.tglbtnDelete.setEnabled(enabledDelete);
		this.btnToBack.setEnabled(enabled);
		this.btnToFront.setEnabled(enabled);
		
		if (enabled == false) {
			this.tglbtnModify.setSelected(false);

		} else if (enabledDelete == false) {
			this.tglbtnDelete.setSelected(false);
		}

	}

}
