package Eredua;

import Bista.ZailtasunPantaila;

public class Rankinga {
	
	private static Rankinga nRankinga=null;
	
	private Rankinga() {}
	
	public static Rankinga getRankinga() {
		if (nRankinga == null) {
			nRankinga = new Rankinga();
		}
		return nRankinga;
	}
	
	public void RankingaEguneratu() {
		//TODO
	}
}
