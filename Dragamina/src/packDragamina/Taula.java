package packDragamina;

public class Taula {
	
	private Gelaxka[][] gelaxkaMatrizea;
	
	public Taula() {
		
	}

	public void taulaHasieratu(int pZutab, int pErrenk) {
		this.gelaxkaMatrizea=new Gelaxka[pZutab][pErrenk];
	}
}
