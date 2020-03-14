package packDragamina;

public class Dragamina {
	
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
