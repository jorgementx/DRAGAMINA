package Bista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RankingGalderaPantaila extends JDialog {

	private static RankingGalderaPantaila nRankingGalderaPantaila = null;
	private JPanel contentPane;
	private JPanel panelTestua;
	private JPanel panelBotoiak;
	private JLabel lblPuntuazioa;
	private JLabel lblGaldera;
	private JButton btnBai;
	private JButton btnEz;

	private RankingGalderaPantaila(JFrame parent, boolean modal) {
		super(parent, modal);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		initialize();
	}
	
	public static RankingGalderaPantaila getRankingGalderaPantaila() {
		if (nRankingGalderaPantaila == null) {
			nRankingGalderaPantaila = new RankingGalderaPantaila(Dragamina.getDragamina(), true);
		}
		return nRankingGalderaPantaila;
	}
	
	public void bistaratu() {
		nRankingGalderaPantaila.setVisible(true);
	}
	
	private void initialize() {
		this.setAlwaysOnTop(true);
		this.setSize(300, 180); //Leihoaren tamaina
		this.setResizable(false); //Leihoaren tamaina aldatu ezin izateko
		this.setTitle("Dragamina"); //Leihoaren izena finkatu
		this.setLocationRelativeTo(null); //Leihoa monitorearen erdian pantailaratzeko
		this.setContentPane(getContentPane());
		this.setIconImage(new ImageIcon(this.getClass().getResource("icono.png")).getImage());
	}
	
	public JPanel getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout()); //contentPane-aren diseinua (hgap eta vgap gehitu behar izanez gero)
			contentPane.add(getPanelTestua(), BorderLayout.CENTER);
			contentPane.add(getPanelBotoiak(), BorderLayout.SOUTH);
		}
		return contentPane;
	}
	private JPanel getPanelTestua() {
		if (panelTestua == null) {
			panelTestua = new JPanel();
			panelTestua.setLayout(new BorderLayout());
			panelTestua.add(getLblPuntuazioa(), BorderLayout.NORTH);
			panelTestua.add(getLblGaldera(), BorderLayout.NORTH);
		}
		return panelTestua;
	}
	private JPanel getPanelBotoiak() {
		if (panelBotoiak == null) {
			panelBotoiak = new JPanel();
			panelBotoiak.setLayout(new BorderLayout());
			panelBotoiak.add(getBtnBai());
			panelBotoiak.add(getBtnEz());
		}
		return panelBotoiak;
	}
	private JLabel getLblPuntuazioa() {
		if (lblPuntuazioa == null) {
			lblPuntuazioa = new JLabel("Aukeratu zailtasun-maila:");
			lblPuntuazioa.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPuntuazioa;
	}
	private JLabel getLblGaldera() {
		if (lblGaldera == null) {
			lblGaldera = new JLabel("Aukeratu zailtasun-maila:");
			lblGaldera.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblGaldera;
	}
	private JButton getBtnBai() {
		if (btnBai == null) {
			btnBai = new JButton("Bai");
			btnBai.addMouseListener(new BtnBaiMouseListener());
		}
		return btnBai;
	}
	private JButton getBtnEz() {
		if (btnEz == null) {
			btnEz = new JButton("nommires");
			btnEz.addMouseListener(new BtnEzMouseListener());
		}
		return btnEz;
	}
	private class BtnBaiMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			RankingGalderaPantaila.getRankingGalderaPantaila().setVisible(false); //Lehioa ezkutatzekoa
			RankingPantaila.getRankingPantaila();
		}
	}
	private class BtnEzMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			RankingGalderaPantaila.getRankingGalderaPantaila().setVisible(false); //Lehioa ezkutatzekoa
		}
	}

}
