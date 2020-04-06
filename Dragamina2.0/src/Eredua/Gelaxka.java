package Eredua;

public abstract class Gelaxka {
	protected Egoera egoera;
	
	public Gelaxka(){}
	
	public void gelaxkaIreki(){
		egoera.ireki(this);	
	}
	
	public void gelaxkanBanderaAldatu(){
		egoera.banderaAldatu(this);
	}
	
	public void egoeraAldatu(Egoera pEgoera){
		this.egoera=pEgoera;
	}
	
	public Egoera getEgoera() {
		return this.egoera;
	}
	
}
