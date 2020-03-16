package packDragamina;

// clase Taula

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
	
	public Gelaxka getGelaxka(int pErrenk, int pZutab) {
		return this.gelaxkaMatrizea[pErrenk][pZutab];
	}
	
	public void irekiGelaxka(int x, int y){ //gelaxka hutsa bada errekurtsiboki ingurukoak ireki alboan mina bat hegon arte
		
		//minaren kasua gehitu daiteke
		
		if (y>=this.getErrenk() || x>=this.getZutab() || x<=0 || y<=0 || this.gelaxkaMatrizea[x][y].getKlikatuta()){
			return; //atera gelaxka ez bada existitzen edo clickatuta badago (return ez da beharrezkoa)
		}
		else {
			
			((Hutsik)this.gelaxkaMatrizea[x][y]).klikatu(); //zenbakia edo gelaxka hutsa baldin bada klikatu
			
			if(((Hutsik)this.gelaxkaMatrizea[x][y]).getMinak()==0){ //hutsa bada albokoen irekiGelaxka (mina izatera ez gara inoiz helduko lehenik eta behin zenbakia topatuko genukeelako)
				irekiGelaxka(x++,y);		//eskuina
				irekiGelaxka(x--,y);		//ezkerra
				irekiGelaxka(x,y++);		//behekoa
				irekiGelaxka(x,y--);		//goikoa
				irekiGelaxka(x++,y++);		//diagonalki behe-eskuin
				irekiGelaxka(x--,y--);		//diagonalki goi-ezker
				irekiGelaxka(x--,y++);		//diagonalki behe-ezker
				irekiGelaxka(x++,y--);		//diagonalki goi-eskuin
			}
		}
		
	}
	
}
