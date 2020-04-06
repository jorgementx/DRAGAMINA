package Eredua;

public class ZenbakidunGelaxka extends Gelaxka {
	
	private int kopurua;
	
	public ZenbakidunGelaxka(){
		egoera = new Itxita();
		this.kopurua=0;
	}
	
	public void setKopurua(int pKop) {
		this.kopurua=pKop;
	}
	
	public int getKopurua() {
		return this.kopurua;
	}
}
