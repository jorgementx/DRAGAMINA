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

	private void taulaHasieratu() {	//este metodo habria que hacer que metiera minas y todo directamente porque la clase gelaxka es abstracta (como puso el)
		for (int x=0;x<this.gelaxkaMatrizea.length;x++) {
			for (int y=0;y<this.gelaxkaMatrizea[0].length;y++) {
				this.gelaxkaMatrizea[x][y]=new Gelaxka();
			}
		}
	}
	
	public void taulaEzabatu() {
		nTaula=null;
	}
	
	public void irekiGelaxka(int x, int y) { 
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
	
	public void eskumakoBotoia(int x, int y) {
		//TODO
		setChanged();
		notifyObservers();
	}
}
