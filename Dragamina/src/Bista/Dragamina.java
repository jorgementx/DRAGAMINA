package Bista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Eredua.DragaminaKudeatzailea;
import Eredua.Taula;

public class Dragamina extends JFrame implements Observer{

	private static final long serialVersionUID = 3805581801146219635L;
	private static Dragamina nDragamina = null;
	private JPanel contentPane;
	private JPanel pnlMatrizea;
	private JLabel[][] listaGelaxkak;
	private Taula nireTaula;
	private int zailtasuna = 2;
	private int errenk;
	private int zutab;
	private Kontrolatzailea kontrolatzailea = null;

	/**
	 * Create the frame.
	 */
	private Dragamina() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Lehioa izteko "X" klikatzean
		this.initialize();
		DragaminaKudeatzailea.getDragaminaKudeatzailea().addObserver(this);
	}
	
	public static Dragamina getDragamina() {
		if (nDragamina == null) {
			nDragamina = new Dragamina();
		}
		return nDragamina;
	}
	
	private void kudeatuAmaiera() {
		//TODO
	}
	
	private void partidaIrabazi() {
		//TODO
	}
	
	private void partidaGaldu(int i, int j) {
		Dragamina.getDragamina().nireTaula.irekiGuztiak(i, j);
		//TODO
	}
	
	/**
	 * Leihoaren osagaien hasieraketa
	 */
	private void initialize() {
		this.setSize(300, 350); //Leihoaren tamaina
		this.setContentPane(getContentPane()); //Sortu eta finkatu contentPane
		this.setTitle("Dragamina"); //Leihoaren izena finkatu
		this.setLocationRelativeTo(null); //Leihoa monitorearen erdian pantailaratzeko
		this.setVisible(true); //Leihoa bistaratzeko agindua
	}
	
	/**
	 * Panel nagusiaren Singleton-a
	 */
	public JPanel getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout()); //contentPane-aren diseinua (hgap eta vgap gehitu behar izanez gero)
			contentPane.add(getPnlMatrizea(), BorderLayout.SOUTH);
		}
		return contentPane;
	}
	
	/**
	 * Matrizearen panelaren Singleton-a
	 */
	private JPanel getPnlMatrizea() {
		if (pnlMatrizea == null) {
			pnlMatrizea = new JPanel();
			pnlMatrizea.setLayout(new GridBagLayout());
			this.nireTaula = new Taula(zailtasuna);
			this.nireTaula.taulaHasieratu();
			this.matrizeaHasieratu();
		}
		return pnlMatrizea;
	}
	
	public JLabel[][] getListaGelaxkak() {
		if (listaGelaxkak == null) {
			listaGelaxkak = new JLabel[this.errenk][this.zutab];
		}
		return listaGelaxkak;
	}
	
	public Taula getNireTaula() {
		return this.nireTaula;
	}
	
	private void matrizeaHasieratu() {
		JLabel pGelaxka = null;
		if (this.zailtasuna == 1) {
			this.zutab = 7;
			this.errenk = 10;
		}
		else if (this.zailtasuna == 2) {
			this.zutab = 10;
			this.errenk = 15;
		}
		else if (this.zailtasuna == 3) {
			this.zutab = 12;
			this.errenk = 25;
		}
		else {
			this.zutab = 10;
			this.errenk = 15;
		}
		for (int i=0;i<this.errenk;i++) {
			for (int j=0;j<this.zutab;j++) {
				pGelaxka=this.gelaxkaSortu(i, j);
				this.getListaGelaxkak()[i][j] = pGelaxka;
				this.getPnlMatrizea().add(pGelaxka, new GridBagConstraints(j,i,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0, 0, 0, 0),0,0));
			}
		}
	}
	
	private JLabel gelaxkaSortu(int i, int j) {
		JLabel label = new JLabel();
		label.setBorder(BorderFactory.createLoweredBevelBorder());
		label.setIcon(new ImageIcon(this.getClass().getResource("tablero.gif")));
		label.setMaximumSize(new Dimension(20, 20));
		label.setMinimumSize(new Dimension(18, 18));
		label.setSize(18, 18);
		label.addMouseListener(this.getKontrolatzailea());
		return label;
	}
	
	private Kontrolatzailea getKontrolatzailea() {
		if (kontrolatzailea == null) {
			kontrolatzailea = new Kontrolatzailea();
		}
		return kontrolatzailea;
	}
	
	private class Kontrolatzailea implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
				boolean aurk = false;
				int i = 0;
				int j = 0;
				while (!aurk && i<Dragamina.getDragamina().getListaGelaxkak().length) {
					while (!aurk && j<Dragamina.getDragamina().getListaGelaxkak()[0].length) {
						if (Dragamina.getDragamina().getListaGelaxkak()[i][j].equals((JLabel)e.getSource())) {
							aurk = true;
						}
						else {
							j++;
						}
					}
					if (!aurk) {
						j = 0;
						i++;
					}
				}
				if (aurk) {
					Dragamina.getDragamina().nireTaula.irekiGelaxka(i, j);
					if (Dragamina.getDragamina().nireTaula.getGelaxka(j, i).getMota()==9) {
						Dragamina.getDragamina().partidaGaldu(i, j);
					}
					else {
						Dragamina.getDragamina().kudeatuAmaiera();
					}
				}
			}
			else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
				//TODO
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		DragaminaKudeatzailea dk = DragaminaKudeatzailea.getDragaminaKudeatzailea();
		
	}

}
