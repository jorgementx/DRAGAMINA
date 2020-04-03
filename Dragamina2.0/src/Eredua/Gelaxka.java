package Eredua;

public abstract class Gelaxka {
	protected Egoera egoera;
	protected int x;
	protected int y;
	
	public Gelaxka(){}
	
	public void GelaxkaIreki(){
		egoera.ireki(this.x,this.y);
		
	}
	
	public void egoeraAldatu(Egoera pEgoera){
		this.egoera=pEgoera;
	}
	
}
