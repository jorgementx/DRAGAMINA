package Eredua;

public class Gelaxka {

	private boolean klikatuta;
	private boolean bandera;
	private int mota;
	
	public Gelaxka() {
		this.klikatuta=false;
		this.bandera=false;
		this.mota=0;
	}
	
	public void klikatu() {
		this.klikatuta=true;
	}
	
	public boolean getKlikatuta(){
		return this.klikatuta;
	}
	
	public void setMota(int pMota) {
		this.mota=pMota;
	}
	
	public int getMota() {
		return this.mota;
	}
	
	public void inkMota() {
		this.mota++;
	}
}
