package Eredua;

public class GelaxkaFactory {
	
	private static GelaxkaFactory nGelaxkaFactory;
	
	private GelaxkaFactory() {}
	
	public static GelaxkaFactory getGelaxkaFactory() {
		if (nGelaxkaFactory==null) {
			nGelaxkaFactory = new GelaxkaFactory();
		}
		return nGelaxkaFactory;
	}
	
	public Gelaxka createGelaxka(String mota) {
		Gelaxka nireGelaxka = null;
		if (mota=="Hutsik") {
			nireGelaxka = new HutsikGelaxka();
		}
		else if (mota=="Mina") {
			nireGelaxka = new MinaGelaxka();
		}
		else if (mota=="Zenbakidun") {
			nireGelaxka = new ZenbakidunGelaxka();
		}
		return nireGelaxka;
	}
}