package packDragamina;

import javax.swing.JButton;

public abstract class Gelaxka extends JButton {

	protected boolean klikatuta;
	protected boolean bandera;
	
	public Gelaxka() {
		this.klikatuta=false;
		this.bandera=false;
	}
	
	public void klikatu() {
		this.klikatuta=true;
	}
}
