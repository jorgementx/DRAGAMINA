package Eredua;

import java.util.Observable;

import javax.swing.ImageIcon;

import Bista.Dragamina;

public class Taula extends Observable {
	
	private Gelaxka[][] gelaxkaMatrizea;
	private boolean minakJarrita;
	private int minaKop;
	private int banderaKop;
	
	private static Taula nTaula=null;
	
	private Taula() {
		this.gelaxkaMatrizea=new Gelaxka[Dragamina.getDragamina().getZutab()][Dragamina.getDragamina().getErrenk()];
		this.minakJarrita=false;
		this.minaKop=this.gelaxkaMatrizea.length*Dragamina.getDragamina().getZailtasuna();
		this.banderaKop=this.gelaxkaMatrizea.length*Dragamina.getDragamina().getZailtasuna();
		this.taulaHasieratu();
	}
	
	public static Taula getTaula() {
		if (nTaula == null) {
			nTaula = new Taula();
		}
		return nTaula;
	}

	private void taulaHasieratu() {
		for (int x=0;x<this.gelaxkaMatrizea.length;x++) {
			for (int y=0;y<this.gelaxkaMatrizea[0].length;y++) {
				this.gelaxkaMatrizea[x][y]=GelaxkaFactory.getGelaxkaFactory().gelaxkaSortu("Hutsik");
			}
		}
	}
	
	public void taulaEzabatu() {
		nTaula=null;
	}
	
	public void irekiGelaxka(int x, int y) {
		if (!this.minakJarrita) {
			this.hasieratuMinak(x, y);
		}
		if (x>=this.gelaxkaMatrizea.length || y>=this.gelaxkaMatrizea[0].length || x<0 || y<0) {}
		else {
			if (this.gelaxkaMatrizea[x][y] instanceof Mina) { //agian zihurtatu aldez aurretik mina den edo ez casting-a egiteko
				((Mina)this.gelaxkaMatrizea[x][y]).gelaxkaIreki();
			}
			else {
				this.gelaxkaMatrizea[x][y].gelaxkaIreki();
				if (this.gelaxkaMatrizea[x][y] instanceof Hutsik) {
					irekiGelaxka(x-1,y-1);
					irekiGelaxka(x,y-1);
					irekiGelaxka(x+1,y-1);
					irekiGelaxka(x-1,y);
					irekiGelaxka(x+1,y);
					irekiGelaxka(x-1,y+1);
					irekiGelaxka(x,y+1);
					irekiGelaxka(x+1,y+1);
				}
			}
		}
		
		setChanged();
		notifyObservers();
	}
	
	public void hasieratuMinak(int x, int y) {
		int jartzekoFalta=this.minaKop;
		int randomZut=0;
		int randomErrenk=0;
		while (jartzekoFalta>0) {
			randomZut=(int)(Math.random()*this.gelaxkaMatrizea.length);
			randomErrenk=(int)(Math.random()*this.gelaxkaMatrizea[0].length);
			if (!(this.gelaxkaMatrizea[randomZut][randomErrenk] instanceof Mina) && randomZut!=x && randomErrenk!=y) {
				this.gelaxkaMatrizea[randomZut][randomErrenk]=GelaxkaFactory.getGelaxkaFactory().gelaxkaSortu("Mina");
				jartzekoFalta--;
			}
		}
		this.minaKopJarriGelaxketan();
		this.minakJarrita=true;
	}
	
	private void minaKopJarriGelaxketan() {
		for (int x=0;x<this.gelaxkaMatrizea.length;x++) {
			for (int y=0;y<this.gelaxkaMatrizea[0].length;y++) {
				if (!(this.gelaxkaMatrizea[x][y] instanceof Mina)) {
					int kopurua=this.ingurukoMinak(x, y);
					if (kopurua>0) {
						this.gelaxkaMatrizea[x][y]=GelaxkaFactory.getGelaxkaFactory().gelaxkaSortu("Zenbakidun");
						((Zenbakidun)this.gelaxkaMatrizea[x][y]).setKopurua(kopurua);
					}
				}
			}
		}
	}
	
	private int ingurukoMinak (int x, int y) { //inguruko gelaxkak minak al diren begiratzen du
		int kopurua=0;
		if (x>0 && y>0) {
			if (this.gelaxkaMatrizea[x-1][y-1] instanceof Mina) {
				kopurua++;
			}
		}
		if (x>0) {
			if (this.gelaxkaMatrizea[x-1][y] instanceof Mina) {
				kopurua++;
			}
		}
		if (x>0 && y<(this.gelaxkaMatrizea[0].length-1)) {
			if (this.gelaxkaMatrizea[x-1][y+1] instanceof Mina) {
				kopurua++;
			}
		}
		if (y>0) {
			if (this.gelaxkaMatrizea[x][y-1] instanceof Mina) {
				kopurua++;
			}
		}
		if (y<(this.gelaxkaMatrizea[0].length-1)) {
			if (this.gelaxkaMatrizea[x][y+1] instanceof Mina) {
				kopurua++;
			}
		}
		if (x<(this.gelaxkaMatrizea.length-1) && y>0) {
			if (this.gelaxkaMatrizea[x+1][y-1] instanceof Mina) {
				kopurua++;
			}
		}
		if (x<(this.gelaxkaMatrizea.length-1)) {
			if (this.gelaxkaMatrizea[x+1][y] instanceof Mina) {
				kopurua++;
			}
		}
		if (x<(this.gelaxkaMatrizea.length-1) && y<(this.gelaxkaMatrizea[0].length-1)) {
			if (this.gelaxkaMatrizea[x+1][y+1] instanceof Mina) {
				kopurua++;
			}
		}
		return kopurua;
	}
	
	public void eskumakoBotoia(int x, int y) {
		//TODO
		setChanged();
		notifyObservers();
	}
}
