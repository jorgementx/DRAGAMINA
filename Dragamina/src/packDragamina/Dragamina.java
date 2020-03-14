package packDragamina;

public class Dragamina {//singleton
	
	private static Dragamina nDragamina=null;
	
	private Dragamina(){	
		
	}
	
	public static Dragamina getNireDragamina(){
		 if (nDragamina==null){
			 nDragamina= new Dragamina();
		 }
		 return nDragamina;
	}
	
}
