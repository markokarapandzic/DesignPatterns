package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Square;
import geometry.Point;

import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgKrug extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private int x, y;
	private JTextField txtPoluprecnik;
	/*
	 * private JComboBox cbxBojaIvice; private JComboBox cbxBojaUnutrasnjosti;
	 */
	private JButton btnPotvrdi;
	private JButton btnOdustani;
	private boolean provera = false;
	private JPanel pnlBojaIvice;
	private JPanel pnlBojaUnutrasnjosti;
	private JLabel lblBojaUnutrasnjosti;
	private JLabel lblBojaIvice;

	/*
	 * private String bojaIvice; private String bojaUnutrasnjosti;
	 */
	private int duzinaStranice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgKrug dialog = new DlgKrug();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgKrug() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(178, 34, 34));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X coordinate upper left:");
			lblX.setForeground(new Color(255, 255, 255));
			lblX.setFont(new Font("Tahoma", Font.PLAIN, 13));
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.fill = GridBagConstraints.BOTH;
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 0;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 1;
			gbc_txtX.gridy = 0;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y coordinate upper left:");
			lblY.setForeground(new Color(255, 255, 255));
			lblY.setFont(new Font("Tahoma", Font.PLAIN, 13));
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.fill = GridBagConstraints.BOTH;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 1;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 1;
			gbc_txtY.gridy = 1;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblPoluprecnik = new JLabel("Radius");
			lblPoluprecnik.setForeground(new Color(255, 255, 255));
			lblPoluprecnik.setFont(new Font("Tahoma", Font.PLAIN, 13));
			GridBagConstraints gbc_lblPoluprecnik = new GridBagConstraints();
			gbc_lblPoluprecnik.fill = GridBagConstraints.BOTH;
			gbc_lblPoluprecnik.insets = new Insets(0, 0, 5, 5);
			gbc_lblPoluprecnik.gridx = 0;
			gbc_lblPoluprecnik.gridy = 2;
			contentPanel.add(lblPoluprecnik, gbc_lblPoluprecnik);
		}
		{
			txtPoluprecnik = new JTextField();
			GridBagConstraints gbc_txtPoluprecnik = new GridBagConstraints();
			gbc_txtPoluprecnik.insets = new Insets(0, 0, 5, 0);
			gbc_txtPoluprecnik.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPoluprecnik.gridx = 1;
			gbc_txtPoluprecnik.gridy = 2;
			contentPanel.add(txtPoluprecnik, gbc_txtPoluprecnik);
			txtPoluprecnik.setColumns(10);
		}
		{
			lblBojaIvice = new JLabel("Color:");
			lblBojaIvice.setForeground(new Color(255, 255, 255));
			lblBojaIvice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			GridBagConstraints gbc_lblBojaIvice = new GridBagConstraints();
			gbc_lblBojaIvice.fill = GridBagConstraints.BOTH;
			gbc_lblBojaIvice.insets = new Insets(0, 0, 5, 5);
			gbc_lblBojaIvice.gridx = 0;
			gbc_lblBojaIvice.gridy = 3;
			contentPanel.add(lblBojaIvice, gbc_lblBojaIvice);
		}

		{
			pnlBojaIvice = new JPanel();
			pnlBojaIvice.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					pnlBojaIvice
							.setBackground(JColorChooser.showDialog(null, "Choose color of the square", Color.BLACK));
				}
			});
			GridBagConstraints gbc_pnlBojaIvice = new GridBagConstraints();
			gbc_pnlBojaIvice.insets = new Insets(0, 0, 5, 0);
			gbc_pnlBojaIvice.fill = GridBagConstraints.BOTH;
			gbc_pnlBojaIvice.gridx = 1;
			gbc_pnlBojaIvice.gridy = 3;
			contentPanel.add(pnlBojaIvice, gbc_pnlBojaIvice);
		}
		{
			lblBojaUnutrasnjosti = new JLabel("Inner color:");
			lblBojaUnutrasnjosti.setForeground(new Color(255, 255, 255));
			lblBojaUnutrasnjosti.setFont(new Font("Tahoma", Font.PLAIN, 13));
			GridBagConstraints gbc_lblBojaUnutrasnjosti = new GridBagConstraints();
			gbc_lblBojaUnutrasnjosti.fill = GridBagConstraints.BOTH;
			gbc_lblBojaUnutrasnjosti.insets = new Insets(0, 0, 0, 5);
			gbc_lblBojaUnutrasnjosti.gridx = 0;
			gbc_lblBojaUnutrasnjosti.gridy = 4;
			contentPanel.add(lblBojaUnutrasnjosti, gbc_lblBojaUnutrasnjosti);
		}
		{
			pnlBojaUnutrasnjosti = new JPanel();
			pnlBojaUnutrasnjosti.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					pnlBojaUnutrasnjosti.setBackground(
							JColorChooser.showDialog(null, "Choose an inner color of the square", Color.BLACK));
				}
			});
			GridBagConstraints gbc_pnlBojaUnutrasnjosti = new GridBagConstraints();
			gbc_pnlBojaUnutrasnjosti.fill = GridBagConstraints.BOTH;
			gbc_pnlBojaUnutrasnjosti.gridx = 1;
			gbc_pnlBojaUnutrasnjosti.gridy = 4;
			contentPanel.add(pnlBojaUnutrasnjosti, gbc_pnlBojaUnutrasnjosti);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(178, 34, 34));
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				btnPotvrdi = new JButton("OK");
				btnPotvrdi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						setVisible(false);

						provera = true;

					}
				});
				btnPotvrdi.setActionCommand("OK");
				buttonPane.add(btnPotvrdi);
				getRootPane().setDefaultButton(btnPotvrdi);
			}
			{
				btnOdustani = new JButton("Cancel");
				btnOdustani.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						getTxtX().setText("");
						getTxtY().setText("");
						getTxtPoluprecnik().setText("");
						;

						provera = false;
					}
				});
				btnOdustani.setHorizontalAlignment(SwingConstants.RIGHT);
				btnOdustani.setActionCommand("Cancel");
				buttonPane.add(btnOdustani);
			}
		}
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JButton getBtnPotvrdi() {
		return btnPotvrdi;
	}

	public void setBtnPotvrdi(JButton btnPotvrdi) {
		this.btnPotvrdi = btnPotvrdi;
	}

	public JButton getBtnIzadji() {
		return btnOdustani;
	}

	public void setBtnIzadji(JButton btnIzadji) {
		this.btnOdustani = btnIzadji;
	}

	public boolean isProvera() {
		return provera;
	}

	public void setProvera(boolean provera) {
		this.provera = provera;
	}

	public int getDuzinaStranice() {
		return duzinaStranice;
	}

	public void setDuzinaStranice(int duzinaStranice) {
		this.duzinaStranice = duzinaStranice;
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

	public JTextField getTxtPoluprecnik() {
		return txtPoluprecnik;
	}

	public void setTxtPoluprecnik(JTextField txtPoluprecnik) {
		this.txtPoluprecnik = txtPoluprecnik;
	}

	public JComponent getPnlBojaIvice() {
		// TODO Auto-generated method stub
		return pnlBojaIvice;
	}

	public JComponent getPnlBojaUnutrasnjosti() {
		// TODO Auto-generated method stub
		return pnlBojaUnutrasnjosti;
	}

	public JComponent getLblBojaIvice() {

		// TODO Auto-generated method stub
		return lblBojaIvice;
	}

	public JComponent getLblBojaUnutrasnjosti() {

		// TODO Auto-generated method stub
		return lblBojaUnutrasnjosti;
	}

}
