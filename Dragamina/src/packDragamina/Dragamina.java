package packDragamina;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class Dragamina extends JFrame {

	private Taula nireTaula;
	private int zutab;
	private int errenk;
	private JPanel contentPane;
	private JPanel panelGelaxkak;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dragamina frame = new Dragamina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Dragamina() {
		this.nireTaula=new Taula(2);
		this.zutab=this.nireTaula.getZutab();
		this.errenk=this.nireTaula.getErrenk();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelGelaxkak(), BorderLayout.NORTH);
		this.gelaxkenMatrizeaHasieratu();
	}
	
	private void gelaxkenMatrizeaHasieratu() {
		this.nireTaula.taulaHasieratu();
		Gelaxka pGelaxka=null;
		for (int i=0;i<this.errenk;i++) {
			for (int j=0;j<this.zutab;j++) {
				if (this.nireTaula.getGelaxka(i, j) instanceof Mina) {
					pGelaxka=new Mina();
				}
				else if (this.nireTaula.getGelaxka(i, j) instanceof Hutsik) {
					pGelaxka=new Hutsik();
				}
				this.getPanelGelaxkak().add(pGelaxka);
				this.getPanelGelaxkak().add(pGelaxka, new GridBagConstraints(i,j,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0, 0, 0, 0),0,0));
			}
		}
	}
	private JPanel getPanelGelaxkak() {
		if (panelGelaxkak == null) {
			panelGelaxkak = new JPanel();
			panelGelaxkak.setLayout(new GridBagLayout());
		}
		return panelGelaxkak;
	}
}
