package Eredua;

import java.util.Observable;

import javax.swing.ImageIcon;

import Bista.Dragamina;

public class Taula extends Observable {
	
	private Gelaxka[][] gelaxkaMatrizea;
	private boolean minakJarrita;
	private int minaKop;
	private int banderaKop;
	private boolean galduta;
	private boolean irabazita;
	private static Taula nTaula=null;
	
	private Taula() {
		this.gelaxkaMatrizea=new Gelaxka[10][15];
		this.minakJarrita=false;
		this.galduta=false;
		this.irabazita=false;
		this.minaKop=this.gelaxkaMatrizea.length*2;
		this.banderaKop=this.gelaxkaMatrizea.length*2;
		this.taulaHasieratu();
	}
	
	public static Taula getTaula() {
		if (nTaula == null) {
			nTaula = new Taula();
		}
		return nTaula;
	}
	
	public Gelaxka[][] getGelaxkaMatrizea() {
		return this.gelaxkaMatrizea;
	}
	
	public boolean getGalduta() {
		return this.galduta;
	}
	
	public boolean getIrabazita() {
		return this.irabazita;
	}

	private void taulaHasieratu() {
		for (int x=0;x<this.gelaxkaMatrizea.length;x++) {
			for (int y=0;y<this.gelaxkaMatrizea[0].length;y++) {
				this.gelaxkaMatrizea[x][y]=GelaxkaFactory.getGelaxkaFactory().createGelaxka("Hutsik");
			}
		}
	}
	
	public void taulaEzabatu() {
		nTaula=null;
	}
	
	public void irekiKudeatu(int x, int y) {
		this.irekiGelaxka(x, y);
		setChanged();
		notifyObservers();
	}
	
	private void irekiGelaxka(int x, int y) {
		if (this.galduta || this.irabazita) {}
		else {
			if (!this.minakJarrita) {
				this.hasieratuMinak(x, y);
			}
			if (x>=this.gelaxkaMatrizea.length || y>=this.gelaxkaMatrizea[0].length || x<0 || y<0) {}
			else if (!(this.gelaxkaMatrizea[x][y].getEgoera() instanceof Irekita)) {
				this.gelaxkaMatrizea[x][y].gelaxkaIreki(); //Dei orokorra gelaxka guztientzat, mota kontuan izan gabe
				if (this.gelaxkaMatrizea[x][y] instanceof MinaGelaxka) { //Gelaxka Mina motakoa bada
					this.galduta=true;
				}
				else { //Gelaxka Mina ez den mota batekoa bada
					if (this.gelaxkaMatrizea[x][y] instanceof HutsikGelaxka) { //Mina Hutsik motakoa bada
						irekiGelaxka(x-1,y-1);
						irekiGelaxka(x,y-1);
						irekiGelaxka(x+1,y-1);
						irekiGelaxka(x-1,y);
						irekiGelaxka(x+1,y);
						irekiGelaxka(x-1,y+1);
						irekiGelaxka(x,y+1);
						irekiGelaxka(x+1,y+1);
					}
					this.irabaziKonprobaketa();
					if (this.irabazita) {
						this.ipiniBanderak();
					}
				}
			}
		}
	}
	
	public void hasieratuMinak(int x, int y) {
		int jartzekoFalta=this.minaKop;
		int randomZut=0;
		int randomErrenk=0;
		while (jartzekoFalta>0) {
			randomZut=(int)(Math.random()*this.gelaxkaMatrizea.length);
			randomErrenk=(int)(Math.random()*this.gelaxkaMatrizea[0].length);
			if (!(this.gelaxkaMatrizea[randomZut][randomErrenk] instanceof MinaGelaxka) && randomZut!=x && randomErrenk!=y) {
				this.gelaxkaMatrizea[randomZut][randomErrenk]=GelaxkaFactory.getGelaxkaFactory().createGelaxka("Mina");
				jartzekoFalta--;
			}
		}
		this.minaKopJarriGelaxketan();
		this.minakJarrita=true;
	}
	
	private void minaKopJarriGelaxketan() {
		for (int x=0;x<this.gelaxkaMatrizea.length;x++) {
			for (int y=0;y<this.gelaxkaMatrizea[0].length;y++) {
				if (!(this.gelaxkaMatrizea[x][y] instanceof MinaGelaxka)) {
					int kopurua=this.ingurukoMinak(x, y);
					if (kopurua>0) {
						this.gelaxkaMatrizea[x][y]=GelaxkaFactory.getGelaxkaFactory().createGelaxka("Zenbakidun");
						((ZenbakidunGelaxka)this.gelaxkaMatrizea[x][y]).setKopurua(kopurua);
					}
				}
			}
		}
	}
	
	private int ingurukoMinak (int x, int y) { //inguruko gelaxkak minak al diren begiratzen du
		int kopurua=0;
		if (x>0 && y>0) {
			if (this.gelaxkaMatrizea[x-1][y-1] instanceof MinaGelaxka) {
				kopurua++;
			}
		}
		if (x>0) {
			if (this.gelaxkaMatrizea[x-1][y] instanceof MinaGelaxka) {
				kopurua++;
			}
		}
		if (x>0 && y<(this.gelaxkaMatrizea[0].length-1)) {
			if (this.gelaxkaMatrizea[x-1][y+1] instanceof MinaGelaxka) {
				kopurua++;
			}
		}
		if (y>0) {
			if (this.gelaxkaMatrizea[x][y-1] instanceof MinaGelaxka) {
				kopurua++;
			}
		}
		if (y<(this.gelaxkaMatrizea[0].length-1)) {
			if (this.gelaxkaMatrizea[x][y+1] instanceof MinaGelaxka) {
				kopurua++;
			}
		}
		if (x<(this.gelaxkaMatrizea.length-1) && y>0) {
			if (this.gelaxkaMatrizea[x+1][y-1] instanceof MinaGelaxka) {
				kopurua++;
			}
		}
		if (x<(this.gelaxkaMatrizea.length-1)) {
			if (this.gelaxkaMatrizea[x+1][y] instanceof MinaGelaxka) {
				kopurua++;
			}
		}
		if (x<(this.gelaxkaMatrizea.length-1) && y<(this.gelaxkaMatrizea[0].length-1)) {
			if (this.gelaxkaMatrizea[x+1][y+1] instanceof MinaGelaxka) {
				kopurua++;
			}
		}
		return kopurua;
	}
	
	public void irabaziKonprobaketa() {
		int kont=0;
		for (int zutabe=0; zutabe < gelaxkaMatrizea.length; zutabe++){
			for (int errenkada=0; errenkada < gelaxkaMatrizea[0].length; errenkada++){
				if (!(gelaxkaMatrizea[zutabe][errenkada].getEgoera() instanceof Irekita)){
					kont++;
				}
			}
		}
		if (kont==minaKop){
			irabazita=true;
		}
	}
	
	private void ipiniBanderak() { //Banderak ipintzeko Mina motako gelaxketan partida irabaztean
		for (int x=0;x<this.gelaxkaMatrizea.length;x++) {
			for (int y=0;y<this.gelaxkaMatrizea[0].length;y++) {
				if (this.gelaxkaMatrizea[x][y].getEgoera() instanceof Itxita) {
					this.gelaxkaMatrizea[x][y].gelaxkanBanderaAldatu();
					this.banderaKop--;
				}
			}
		}
	}
	
	public void eskumakoBotoia(int x, int y) {
		if (this.galduta || this.irabazita) {}
		else { //Partida ez bada bukatu
			this.gelaxkaMatrizea[x][y].gelaxkanBanderaAldatu();
			if (this.gelaxkaMatrizea[x][y].getEgoera() instanceof Markatuta) {
				this.banderaKop--;
			}
			else {
				this.banderaKop++;
			}
		}
		setChanged();
		notifyObservers();
	}
}
