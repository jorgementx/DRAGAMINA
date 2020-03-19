package Eredua;

import java.util.Observable;

import Eredua.DragaminaKudeatzailea;

public class DragaminaKudeatzailea extends Observable {
	
	private static DragaminaKudeatzailea nDragaminaKudeatzailea = new DragaminaKudeatzailea();
	
	private DragaminaKudeatzailea() {
		
	}
	
	public static DragaminaKudeatzailea getDragaminaKudeatzailea() {
		return nDragaminaKudeatzailea;
	}

}
