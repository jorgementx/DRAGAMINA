package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Eredua.Gelaxka;
import Eredua.HutsikGelaxka;
import Eredua.Irekita;
import Eredua.Itxita;
import Eredua.Markatuta;
import Eredua.MinaGelaxka;
import Eredua.Rankinga;
import Eredua.Taula;
import Eredua.ZenbakidunGelaxka;

public class Dragamina extends JFrame implements Observer {

	private static final long serialVersionUID = 3805581801146219635L;
	private static Dragamina nDragamina = null;
	private Kontrolatzailea kontrolatzailea=null;
	private JPanel contentPane;
	private JPanel pnlMatrizea;
	private JPanel panelMenu;
	private JLabel jokatuBotoia;
	private JLabel bKontEhuneko;
	private JLabel bKontHamarreko;
	private JLabel bKontBateko;
	private JLabel[][] listaGelaxkak;
	private int zailtasuna;
	private int errenk;
	private int zutab;

	private Dragamina() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Lehioa izteko "X" klikatzean
		this.zailtasuna=ZailtasunPantaila.getZailtasunPantaila().getZailtasuna();
		this.initialize();
		Taula.getTaula().addObserver(this);
	}
	
	public static Dragamina getDragamina() {
		if (nDragamina == null) {
			nDragamina = new Dragamina();
		}
		return nDragamina;
	}
	
	public int getZutab() {
		return this.zutab;
	}
	
	public int getErrenk() {
		return this.errenk;
	}
	
	private void initialize() {
		this.setResizable(false); //Leihoaren tamaina aldatu ezin izateko
		this.setContentPane(getContentPane()); //Sortu eta finkatu contentPane
		this.setTitle("Dragamina"); //Leihoaren izena finkatux
		this.leihoarenTamaina();
		this.setLocationRelativeTo(null); //Leihoa monitorearen erdian pantailaratzeko
		this.setIconImage(new ImageIcon(this.getClass().getResource("icono.png")).getImage());
		this.setVisible(true); //Leihoa bistaratzeko agindua
	}
	
	private void leihoarenTamaina() {
		if (this.zailtasuna==1) {
			this.setSize(166, 283);
		}
		else if (this.zailtasuna==2) {
			this.setSize(224, 383);
		}
		else if (this.zailtasuna==3) {
			this.setSize(264, 583);
		}
	}
	
	public JPanel getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout()); //contentPane-aren diseinua (hgap eta vgap gehitu behar izanez gero)
			contentPane.add(getPanelMenu(), BorderLayout.NORTH);
			contentPane.add(getPnlMatrizea(), BorderLayout.CENTER);
		}
		return contentPane;
	}
	
	private JPanel getPanelMenu() {
		if (panelMenu==null) {
			panelMenu = new JPanel();
			FlowLayout menuBotoiak = new FlowLayout();
			panelMenu.setLayout(menuBotoiak);
			jokatuBotoia = new JLabel();
			jokatuBotoia.setIcon(new ImageIcon(this.getClass().getResource("cara1.gif")));
			panelMenu.add(jokatuBotoia);
			jokatuBotoia.addMouseListener(this.getKontrolatzailea());
			
			bKontEhuneko = new JLabel();
			bKontHamarreko = new JLabel();
			bKontBateko = new JLabel();
			bKontEhuneko.setIcon(new ImageIcon(this.getClass().getResource("n0.gif")));
			bKontHamarreko.setIcon(new ImageIcon(this.getClass().getResource("n"+((Taula.getTaula().getBanderaKop())/10+".gif"))));
			bKontBateko.setIcon(new ImageIcon(this.getClass().getResource("n"+(Taula.getTaula().getBanderaKop())%10+".gif")));
			panelMenu.add(bKontEhuneko);
			panelMenu.add(bKontHamarreko);
			panelMenu.add(bKontBateko);
		}
		return panelMenu;
	}

	
	private JPanel getPnlMatrizea() {
		if (pnlMatrizea == null) {
			pnlMatrizea = new JPanel();
			pnlMatrizea.setLayout(new GridBagLayout());
			this.matrizeaHasieratu();
			Taula.getTaula();
		}
		return pnlMatrizea;
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
	
	public JLabel[][] getListaGelaxkak() {
		if (listaGelaxkak == null) {
			listaGelaxkak = new JLabel[this.zutab][this.errenk];
		}
		return listaGelaxkak;
	}
	
	private void matrizeaEguneratu() {
		for (int x=0;x<this.listaGelaxkak.length;x++) {
			for (int y=0;y<this.listaGelaxkak[0].length;y++) {
				Gelaxka pGelaxka=Taula.getTaula().getGelaxkaMatrizea()[x][y];
				if (pGelaxka.getEgoera() instanceof Irekita) { //Gelaxkaren egoera Irekita da
					if (pGelaxka instanceof HutsikGelaxka) {
						this.listaGelaxkak[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("c0.gif")));
					}
					else if (pGelaxka instanceof ZenbakidunGelaxka) {
						this.listaGelaxkak[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("c"+((ZenbakidunGelaxka)pGelaxka).getKopurua()+".gif")));
					}
					else {
						this.listaGelaxkak[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("mina-r.gif")));
					}
				}
				else if (pGelaxka.getEgoera() instanceof Itxita) { //Gelaxkaren egoera itxita da
					if ((pGelaxka instanceof MinaGelaxka) && (Taula.getTaula().getGalduta())) {
						this.listaGelaxkak[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("mina-n.gif")));
					}
					else {
						this.listaGelaxkak[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("tablero.gif")));
					}
				}
				else { //Gelaxkaren egoera markatuta da
					if (((pGelaxka instanceof HutsikGelaxka) || (pGelaxka instanceof ZenbakidunGelaxka)) && (Taula.getTaula().getGalduta())) {
						this.listaGelaxkak[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("mina-x.gif")));
					}
					else {
						this.listaGelaxkak[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("bandera.gif")));
					}
				}
			}
		}
	}
	
	private void aurpegiaEguneratu() {
		if (Taula.getTaula().getGalduta()) {
			jokatuBotoia.setIcon(new ImageIcon(this.getClass().getResource("cara2.gif")));
		}
		else if (Taula.getTaula().getIrabazita()) {
			jokatuBotoia.setIcon(new ImageIcon(this.getClass().getResource("cara3.gif")));
		}
	}
	
	private void banderaKontEguneratu(){
		if (Taula.getTaula().getBanderaKop()>=0){
			bKontEhuneko.setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("n0.gif")));
			bKontHamarreko.setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("n"+((Taula.getTaula().getBanderaKop())/10+".gif"))));
			bKontBateko.setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("n"+(Taula.getTaula().getBanderaKop())%10+".gif")));
		}
		else{
			bKontEhuneko.setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("n-.gif")));
			bKontHamarreko.setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("n"+(-((Taula.getTaula().getBanderaKop())%100)/10+".gif"))));
			bKontBateko.setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("n"+(-(Taula.getTaula().getBanderaKop())%10)+".gif")));
		}		
	}
	
	private void bukatutaKudeatu() throws IOException{
		if (Taula.getTaula().getIrabazita() || Taula.getTaula().getGalduta()) {
			Rankinga.getRankinga().RankingaEguneratu();
			RankingGalderaPantaila.getRankingGalderaPantaila().bistaratu();
		}	
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
			if (Dragamina.getDragamina().jokatuBotoia.equals((JLabel)e.getSource())) {
				if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
					//setVisible(false); 	//JFrame objektua ikusten utzi
					dispose(); 				//JFrame objektua suntsitu
					Taula.getTaula().taulaEzabatu(); //Taula klaseko objektua ezabatzeko
					nDragamina = new Dragamina();
					//resetDragamina();
					//System.out.println("partida berria");
					//TODO
				}
			}
			else if (Taula.getTaula().getIrabazita() || Taula.getTaula().getGalduta()) {} //Partida bukatu bada ez ditugu gelaxketan egindako klikak kontuan izan nahi
			else {
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
						Taula.getTaula().irekiKudeatu(x, y);
					}
					else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) { //Eskumako botoia
						Taula.getTaula().eskumakoBotoia(x, y);
					}
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

	@Override
	public void update(Observable o, Object arg) {
		this.matrizeaEguneratu();
		this.aurpegiaEguneratu();
		this.banderaKontEguneratu();
		try {
			this.bukatutaKudeatu();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//TODO //agian hemen ez da ezer gehiago egin behar
	}

}
