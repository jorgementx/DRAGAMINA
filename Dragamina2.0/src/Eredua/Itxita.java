package Eredua;

public class Itxita implements Egoera {
	
	public Itxita(){}
	
	public void ireki(Gelaxka pGelaxka){
		pGelaxka.egoeraAldatu(new Irekita());
	}
	
	public void banderaAldatu(Gelaxka pGelaxka){
		pGelaxka.egoeraAldatu(new Markatuta());
	}

	public void errekIreki(Gelaxka pGelaxka) {//agian soberan
		// TODO
		
	}

}
