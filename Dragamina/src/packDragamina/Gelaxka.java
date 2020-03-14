package packDragamina;

public abstract class Gelaxka {

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
