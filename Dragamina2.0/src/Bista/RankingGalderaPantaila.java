package Bista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

import Eredua.Taula;

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
		this.setSize(400, 120); //Leihoaren tamaina
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
			panelTestua.setLayout(new FlowLayout());
			panelTestua.add(getLblPuntuazioa());
			panelTestua.add(getLblGaldera());
		}
		return panelTestua;
	}
	private JPanel getPanelBotoiak() {
		if (panelBotoiak == null) {
			panelBotoiak = new JPanel();
			panelBotoiak.setLayout(new FlowLayout());
			panelBotoiak.add(getBtnBai());
			panelBotoiak.add(getBtnEz());
		}
		return panelBotoiak;
	}
	private JLabel getLblPuntuazioa() {
		if (lblPuntuazioa == null) {
			lblPuntuazioa = new JLabel("Partida bukatuta. "+Taula.getTaula().getPuntuazioa()+"-ko puntuazioa izan du "+LoginPantalla.getLoginPantalla().getJokIzena()+" jokalariak.");
			lblPuntuazioa.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPuntuazioa;
	}
	private JLabel getLblGaldera() {
		if (lblGaldera == null) {
			lblGaldera = new JLabel("Ranking-a ikusi nahi al duzu?");
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
			btnEz = new JButton("Ez");
			btnEz.addMouseListener(new BtnEzMouseListener());
		}
		return btnEz;
	}
	private class BtnBaiMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			RankingGalderaPantaila.getRankingGalderaPantaila().setVisible(false); //Lehioa ezkutatzekoa
			nRankingGalderaPantaila=null;
			RankingPantaila.getRankingPantaila();
		}
	}
	private class BtnEzMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			RankingGalderaPantaila.getRankingGalderaPantaila().setVisible(false); //Lehioa ezkutatzekoa
			nRankingGalderaPantaila=null;
		}
	}

}
