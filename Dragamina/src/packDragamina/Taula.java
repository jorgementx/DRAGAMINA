package packDragamina;

public class Taula {
	
	private Gelaxka[][] gelaxkaMatrizea;
	private int minaKop;
	
	public Taula(int zailtasuna) {
		if (zailtasuna==1) {
			this.gelaxkaMatrizea=new Gelaxka[7][10];
		}
		else if (zailtasuna==2) {
			this.gelaxkaMatrizea=new Gelaxka[10][15];
		}
		else if (zailtasuna==3) {
			this.gelaxkaMatrizea=new Gelaxka[12][25];
		}
		this.minaKop=this.gelaxkaMatrizea.length*zailtasuna;
	}

	public void taulaHasieratu() {
		int jartzekoFalta=this.minaKop;
		int randomZut=0;
		int randomErrenk=0;
		while (jartzekoFalta>0) {
			randomErrenk=(int)(Math.random()*this.gelaxkaMatrizea.length);
			randomZut=(int)(Math.random()*this.gelaxkaMatrizea[0].length);
			if (this.gelaxkaMatrizea[randomErrenk][randomZut]==null) {
				this.gelaxkaMatrizea[randomErrenk][randomZut]=new Mina();
				jartzekoFalta--;
			}
		}
		for (int i=0;i<this.gelaxkaMatrizea.length;i++) {
			for (int j=0;j<this.gelaxkaMatrizea[0].length;j++) {
				if (this.gelaxkaMatrizea[i][j]==null) {
					this.gelaxkaMatrizea[i][j]=new Hutsik();
				}
			}
		}
	}
	
	public int getErrenk() {
		return this.gelaxkaMatrizea.length;
	}
	
	public int getZutab() {
		return this.gelaxkaMatrizea[0].length;
	}
	
	public Gelaxka getGelaxka(int pZutab, int pErrenk) {
		return this.gelaxkaMatrizea[pZutab][pErrenk];
	}
}
