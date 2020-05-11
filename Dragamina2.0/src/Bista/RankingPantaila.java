package Bista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Eredua.Rankinga;

public class RankingPantaila extends JFrame {
	
	private static final long serialVersionUID = 3805581801146219635L;
	private static RankingPantaila nRankingPantaila = null;
	private JPanel contentPane;
	private JLabel rankingLabel;
	private JPanel zerrendaPanela;
	private JLabel lerroa;
	private JButton amaituBotoia;
	
	private RankingPantaila() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialize();
	}
	
	public static RankingPantaila getRankingPantaila() {
		if (nRankingPantaila==null) {
			nRankingPantaila = new RankingPantaila();
		}
		return nRankingPantaila;
	}
	
	public JPanel getContentPane() {
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		rankingLabel = new JLabel(" IZENA ETA PUNTUAZIOA:");
		contentPane.add(rankingLabel, BorderLayout.NORTH);
		contentPane.add(getZerrendaPanela(), BorderLayout.CENTER);
		amaituBotoia = new JButton("Amaitu");
		amaituBotoia.addMouseListener(new AmaituMouseListener());
		contentPane.add(amaituBotoia, BorderLayout.SOUTH);
		contentPane.add(new JPanel(), BorderLayout.EAST);
		contentPane.add(new JPanel(), BorderLayout.WEST);
		return contentPane;
	}
	
	private JPanel getZerrendaPanela() {
		zerrendaPanela = new JPanel();
		zerrendaPanela.setLayout(new GridLayout(0,1));
		String[] zerrenda = Rankinga.getRankinga().getZerrenda();
		lerroa = null;
		for (int i=0; i<10; i++) {
			lerroa = new JLabel(zerrenda[i]);
			zerrendaPanela.add(lerroa);
		}
		return zerrendaPanela;
	}
	
	private void initialize() {
		this.setSize(400, 400);
		this.setResizable(false);
		this.setContentPane(getContentPane());
		this.setTitle("Ranking");
		this.setLocationRelativeTo(null);	
		this.setContentPane(getContentPane());
		this.setIconImage(new ImageIcon(this.getClass().getResource("icono.png")).getImage());
		this.setVisible(true);
	}
	
	private class AmaituMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			RankingPantaila.getRankingPantaila().setVisible(false);
		}
	}
}