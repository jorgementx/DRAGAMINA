package Eredua;

public class Gelaxka {

	private boolean klikatuta;
	private boolean bandera;
	private boolean galderaMarka;
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
	
	public boolean getBandera() {
		return this.bandera;
	}
	
	public void setBandera() {
		this.bandera=!this.bandera;
	}
	
	public boolean getGalderaMarka() {
		return this.galderaMarka;
	}
	
	public void setGalderaMarka() {
		this.galderaMarka=!this.galderaMarka;
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
