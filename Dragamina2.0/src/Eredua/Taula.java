package Eredua;

import java.util.Observable;

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
				this.gelaxkaMatrizea[x][y]=new Gelaxka();
			}
		}
	}
	
	public void taulaEzabatu() {
		nTaula=null;
	}
	
	public void irekiGelaxka(int x, int y) { //gelaxka hutsa bada errekurtsiboki ingurukoak ireki alboan mina bat egon arte
		//TODO
		setChanged();
		notifyObservers();
	}
	
	public void eskumakoBotoia(int x, int y) {
		//TODO
		setChanged();
		notifyObservers();
	}
}
