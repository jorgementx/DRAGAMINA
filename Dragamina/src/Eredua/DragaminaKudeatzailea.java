package Eredua;

import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Bista.Dragamina;

public class DragaminaKudeatzailea extends Observable{
	
	private static DragaminaKudeatzailea nDragaminaKudeatzailea = new DragaminaKudeatzailea();
	
	private DragaminaKudeatzailea() {
		
	}
	
	public static DragaminaKudeatzailea getDragaminaKudeatzailea() {
		return nDragaminaKudeatzailea;
	}
}
