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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgLinija extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtXPocetne;
	private JTextField txtYPocetne;

	/*
	 * private JComboBox cbxBojaIvice; private JComboBox cbxBojaUnutrasnjosti;
	 */
	private JButton btnPotvrdi;
	private JButton btnOdustani;
	private boolean provera = false;
	private JPanel pnlBojaIvice;

	/*
	 * private String bojaIvice; private String bojaUnutrasnjosti;
	 */

	private JLabel lblXKoordinataKrajnje;
	private JLabel lblYKoordinataKrajnje;
	private JTextField txtXKrajnje;
	private JTextField txtYKrajnje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLinija dialog = new DlgLinija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLinija() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(178, 34, 34));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X coordinates of starting point:");
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
			txtXPocetne = new JTextField();
			GridBagConstraints gbc_txtXPocetne = new GridBagConstraints();
			gbc_txtXPocetne.insets = new Insets(0, 0, 5, 0);
			gbc_txtXPocetne.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXPocetne.gridx = 1;
			gbc_txtXPocetne.gridy = 0;
			contentPanel.add(txtXPocetne, gbc_txtXPocetne);
			txtXPocetne.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y coordinates of starting point:");
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
			txtYPocetne = new JTextField();
			GridBagConstraints gbc_txtYPocetne = new GridBagConstraints();
			gbc_txtYPocetne.insets = new Insets(0, 0, 5, 0);
			gbc_txtYPocetne.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYPocetne.gridx = 1;
			gbc_txtYPocetne.gridy = 1;
			contentPanel.add(txtYPocetne, gbc_txtYPocetne);
			txtYPocetne.setColumns(10);
		}
		{
			lblXKoordinataKrajnje = new JLabel("X coordinates of ending point::");
			lblXKoordinataKrajnje.setForeground(new Color(255, 255, 255));
			lblXKoordinataKrajnje.setFont(new Font("Tahoma", Font.PLAIN, 13));
			GridBagConstraints gbc_lblXKoordinataKrajnje = new GridBagConstraints();
			gbc_lblXKoordinataKrajnje.anchor = GridBagConstraints.WEST;
			gbc_lblXKoordinataKrajnje.insets = new Insets(0, 0, 5, 5);
			gbc_lblXKoordinataKrajnje.gridx = 0;
			gbc_lblXKoordinataKrajnje.gridy = 2;
			contentPanel.add(lblXKoordinataKrajnje, gbc_lblXKoordinataKrajnje);
		}
		{
			txtXKrajnje = new JTextField();
			GridBagConstraints gbc_txtXKrajnje = new GridBagConstraints();
			gbc_txtXKrajnje.insets = new Insets(0, 0, 5, 0);
			gbc_txtXKrajnje.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXKrajnje.gridx = 1;
			gbc_txtXKrajnje.gridy = 2;
			contentPanel.add(txtXKrajnje, gbc_txtXKrajnje);
			txtXKrajnje.setColumns(10);
		}
		{
			lblYKoordinataKrajnje = new JLabel("Y coordinates of ending point:");
			lblYKoordinataKrajnje.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblYKoordinataKrajnje.setForeground(new Color(255, 255, 255));
			GridBagConstraints gbc_lblYKoordinataKrajnje = new GridBagConstraints();
			gbc_lblYKoordinataKrajnje.anchor = GridBagConstraints.WEST;
			gbc_lblYKoordinataKrajnje.insets = new Insets(0, 0, 5, 5);
			gbc_lblYKoordinataKrajnje.gridx = 0;
			gbc_lblYKoordinataKrajnje.gridy = 3;
			contentPanel.add(lblYKoordinataKrajnje, gbc_lblYKoordinataKrajnje);
		}
		{
			txtYKrajnje = new JTextField();
			GridBagConstraints gbc_txtYKrajnje = new GridBagConstraints();
			gbc_txtYKrajnje.insets = new Insets(0, 0, 5, 0);
			gbc_txtYKrajnje.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYKrajnje.gridx = 1;
			gbc_txtYKrajnje.gridy = 3;
			contentPanel.add(txtYKrajnje, gbc_txtYKrajnje);
			txtYKrajnje.setColumns(10);
		}
		{
			JLabel lblBojaIvice = new JLabel("Color :");
			lblBojaIvice.setForeground(new Color(255, 255, 255));
			lblBojaIvice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			GridBagConstraints gbc_lblBojaIvice = new GridBagConstraints();
			gbc_lblBojaIvice.fill = GridBagConstraints.BOTH;
			gbc_lblBojaIvice.insets = new Insets(0, 0, 5, 5);
			gbc_lblBojaIvice.gridx = 0;
			gbc_lblBojaIvice.gridy = 5;
			contentPanel.add(lblBojaIvice, gbc_lblBojaIvice);
		}
		
		{
			pnlBojaIvice = new JPanel();
			pnlBojaIvice.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					pnlBojaIvice.setBackground(
							JColorChooser.showDialog(null, "Choose color", Color.BLACK));
				}
			});
			GridBagConstraints gbc_pnlBojaIvice = new GridBagConstraints();
			gbc_pnlBojaIvice.insets = new Insets(0, 0, 5, 0);
			gbc_pnlBojaIvice.fill = GridBagConstraints.BOTH;
			gbc_pnlBojaIvice.gridx = 1;
			gbc_pnlBojaIvice.gridy = 5;
			contentPanel.add(pnlBojaIvice, gbc_pnlBojaIvice);
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
						Exception ex = null;
						try {

							setVisible(false);

							provera = true;

						} catch (Exception r) {
							JOptionPane.showMessageDialog(null, "You didn't enter a correct value!");

						}
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
						getTxtXPocetne().setText("");
						getTxtYPocetne().setText("");
						getTxtXKrajnje().setText("");
						getTxtYKrajnje().setText("");
						getPnlBojaIvice().setBackground(Color.WHITE);

						getPnlBojaIvice().setBackground(Color.WHITE);

						provera = false;
					}
				});
				btnOdustani.setHorizontalAlignment(SwingConstants.RIGHT);
				btnOdustani.setActionCommand("Cancel");
				buttonPane.add(btnOdustani);
			}
		}
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

	public JPanel getPnlBojaIvice() {
		return pnlBojaIvice;
	}

	public void setPnlBojaIvice(JPanel pnlBojaIvice) {
		this.pnlBojaIvice = pnlBojaIvice;
	}

	public JTextField getTxtXPocetne() {
		return txtXPocetne;
	}

	public void setTxtXPocetne(JTextField txtXPocetne) {
		this.txtXPocetne = txtXPocetne;
	}

	public JTextField getTxtYPocetne() {
		return txtYPocetne;
	}

	public void setTxtYPocetne(JTextField txtYPocetne) {
		this.txtYPocetne = txtYPocetne;
	}

	public JTextField getTxtXKrajnje() {
		return txtXKrajnje;
	}

	public void setTxtXKrajnje(JTextField txtXKrajnje) {
		this.txtXKrajnje = txtXKrajnje;
	}

	public JTextField getTxtYKrajnje() {
		return txtYKrajnje;
	}

	public void setTxtYKrajnje(JTextField txtYKrajnje) {
		this.txtYKrajnje = txtYKrajnje;
	}

}
