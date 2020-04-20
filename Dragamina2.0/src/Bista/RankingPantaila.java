package Bista;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RankingPantaila extends JFrame {
	
	private static final long serialVersionUID = 3805581801146219635L;
	private static RankingPantaila nRankingPantaila = null;
	
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
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(11,1));
		contentPane.add(new JLabel("RANKING"));		
		
		return contentPane;
	}
	
	private void initialize() {
		this.setSize(300, 180);
		this.setResizable(false);
		this.setContentPane(getContentPane());
		this.setTitle("Dragamina");
		this.setLocationRelativeTo(null);	
		this.setContentPane(getContentPane());
		this.setIconImage(new ImageIcon(this.getClass().getResource("icono.png")).getImage());
		this.setVisible(true);
	}
	
	// Probak egiteko main hau sortu dut
	public static void main(String[] args) {
		RankingPantaila nR = RankingPantaila.getRankingPantaila();
	}
}