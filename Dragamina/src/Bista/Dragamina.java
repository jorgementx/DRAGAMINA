package Bista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Eredua.Taula;

public class Dragamina extends JFrame /*implements Observer (hurrengo sprinterako)*/ {

	private static final long serialVersionUID = 3805581801146219635L;
	private static Dragamina nDragamina = null;
	private JPanel contentPane;
	private JPanel pnlMatrizea;
	private JPanel panelMenu;
	private JPanel panelKontagailu;
	//private JButton jokatuBotoia;		//atributu moduan jarri metodo guztiek berdina erabili dezaten aurpegia aldatzeko
	private JLabel jokatuBotoia;
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
		//DragaminaKudeatzailea.getDragaminaKudeatzailea().addObserver(this); hurrengo sprinterako
	}
	
	public static Dragamina getDragamina() {
		if (nDragamina == null) {
			nDragamina = new Dragamina();
		}
		return nDragamina;
	}
	
	private void kudeatuAmaiera() {
		Dragamina.getDragamina().partidaIrabazi();
	}
	
	private void partidaIrabazi() {
		if(Dragamina.getDragamina().nireTaula.irabaziKonprobaketa()){
			Dragamina.getDragamina().nireTaula.banderaGuztiakJarri();
			jokatuBotoia.setIcon(new ImageIcon(this.getClass().getResource("cara3.gif")));
		}
	}
	
	private void partidaGaldu() {
		Dragamina.getDragamina().nireTaula.irekiGuztiak();
		jokatuBotoia.setIcon(new ImageIcon(this.getClass().getResource("cara2.gif")));
	}
	
	/**
	 * Leihoaren osagaien hasieraketa
	 */
	private void initialize() {
		this.setSize(300, 380); //Leihoaren tamaina
		this.setResizable(false);
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
			contentPane.add(getPanelMenu(), BorderLayout.NORTH);
			contentPane.add(getPnlMatrizea(), BorderLayout.SOUTH);
		}
		return contentPane;
	}
	
	/**
	 * Menuaren botoien panela
	 */
	private JPanel getPanelMenu() {
		if (panelMenu==null) {
			panelMenu = new JPanel();
			FlowLayout menuBotoiak = new FlowLayout();
			panelMenu.setLayout(menuBotoiak);
			//jokatuBotoia = new JButton();
			jokatuBotoia = new JLabel();
			jokatuBotoia.setIcon(new ImageIcon(this.getClass().getResource("cara1.gif")));
			panelMenu.add(jokatuBotoia);
			jokatuBotoia.addMouseListener(this.getKontrolatzailea());
		}
		return panelMenu;
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
			listaGelaxkak = new JLabel[this.zutab][this.errenk];
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
		for (int x=0;x<this.zutab;x++) {
			for (int y=0;y<this.errenk;y++) {
				pGelaxka=this.gelaxkaSortu();
				this.getListaGelaxkak()[x][y] = pGelaxka;
				this.getPnlMatrizea().add(pGelaxka, new GridBagConstraints(x,y,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(0, 0, 0, 0),0,0));
			}
		}
	}
	
	private JLabel gelaxkaSortu() {
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
			boolean aurk = false;
			int x = 0;
			int y = 0;
			while (!aurk && x<Dragamina.getDragamina().getListaGelaxkak().length) {
				while (!aurk && y<Dragamina.getDragamina().getListaGelaxkak()[0].length) {
					if (Dragamina.getDragamina().getListaGelaxkak()[x][y].equals((JLabel)e.getSource())) {
						aurk = true;
					}
					else {
						y++;
					}
				}
				if (!aurk) {
					y = 0;
					x++;
				}
			}
			if (aurk) {
				if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) { //Ezkerreko botoia
					Dragamina.getDragamina().getNireTaula().irekiGelaxka(x, y);
					if (Dragamina.getDragamina().getNireTaula().getGelaxka(x, y).getMota()==9 && !Dragamina.getDragamina().getNireTaula().getGelaxka(x, y).getBandera()) {
						Dragamina.getDragamina().partidaGaldu();
					}
					else {
						Dragamina.getDragamina().kudeatuAmaiera();
					}
				}
				else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) { //Eskumako botoia
					Dragamina.getDragamina().getNireTaula().eskumakoBotoia(x, y);
				}
			}
			else if(Dragamina.getDragamina().jokatuBotoia.equals((JLabel)e.getSource())){
				if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
					//setVisible(false); 	//JFrame objektua ikusten utzi
					dispose(); 				//JFrame objektua suntsitu
					nDragamina = new Dragamina();
					//resetDragamina();
					//System.out.println("partida berria");
					//TODO
				}
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

}
