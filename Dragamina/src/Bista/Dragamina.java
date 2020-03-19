package Bista;

// Dragamina

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Eredua.DragaminaKudeatzailea;
import Eredua.Taula;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

public class Dragamina extends JFrame  implements Observer {

	private Taula nireTaula;
	private int zutab;
	private int errenk;
	private JPanel contentPane;
	private JPanel panelGelaxkak;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
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
		JLabel pGelaxka=null;
		for (int i=0;i<this.errenk;i++) {
			for (int j=0;j<this.zutab;j++) {
				pGelaxka=this.gelaxkaSortu();
				this.getPanelGelaxkak().add(pGelaxka);
				this.getPanelGelaxkak().add(pGelaxka, new GridBagConstraints(i,j,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0, 0, 0, 0),0,0));
			}
		}
	}
	
	private JLabel gelaxkaSortu() {
		JLabel label=new JLabel();
		label.setBorder(BorderFactory.createLoweredBevelBorder());
		label.setIcon(new ImageIcon(this.getClass().getResource("tablero.gif")));
		label.setMaximumSize(new Dimension(20, 20));
		label.setMinimumSize(new Dimension(18, 18));
		label.setSize(18, 18);
		//Listenerra gehitzea falta da
		return label;
	}
	
	private JPanel getPanelGelaxkak() {
		if (panelGelaxkak == null) {
			panelGelaxkak = new JPanel();
			GridBagLayout gbl_panelGelaxkak = new GridBagLayout();
			panelGelaxkak.setLayout(gbl_panelGelaxkak);
		}
		return panelGelaxkak;
	}

	@Override
	public void update(Observable o, Object arg) {
		DragaminaKudeatzailea dk = DragaminaKudeatzailea.getDragaminaKudeatzailea();
		
	}
}
