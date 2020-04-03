package Eredua;

import java.util.Observable;

import Bista.Dragamina;

public class Taula extends Observable {
	
	private static Taula nTaula=null;
	
	private Taula() {
		
	}
	
	public static Taula getTaula() {
		if (nTaula == null) {
			nTaula = new Taula();
		}
		return nTaula;
	}
	
	public void taulaEzabatu() {
		nTaula=null;
	}
	
	public void irekiGelaxka(int x, int y) {
		//TODO
	}
	
	public void eskumakoBotoia(int x, int y) {
		//TODO
	}
}
