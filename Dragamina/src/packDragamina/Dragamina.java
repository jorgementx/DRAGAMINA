package packDragamina;//jdv

public class Dragamina {
	
	private static Dragamina nDragamina=null;
	private Taula taula;
	
	private Dragamina(){	
		this.taula=new Taula();
	}
	
	public static Dragamina getNireDragamina(){
		 if (nDragamina==null){
			 nDragamina= new Dragamina();
		 }
		 return nDragamina;
	}
	
	public static void main(String [ ] args) {
		Dragamina.getNireDragamina().jokoaHasieratu();
	}
	
	private void jokoaHasieratu() {
		this.taula.taulaHasieratu();
	}
	
}
