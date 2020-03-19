package Eredua;

import javax.swing.ImageIcon;

import Bista.Dragamina;

public class Taula {
	
	private Gelaxka[][] gelaxkaMatrizea;
	private int minaKop;
	
	public Taula(int zailtasuna) {
		if (zailtasuna==1) {
			this.gelaxkaMatrizea=new Gelaxka[7][10];
		}
		else if (zailtasuna==2) {
			this.gelaxkaMatrizea=new Gelaxka[15][10];
		}
		else if (zailtasuna==3) {
			this.gelaxkaMatrizea=new Gelaxka[12][25];
		}
		this.minaKop=this.gelaxkaMatrizea.length*zailtasuna;
	}

	public void taulaHasieratu() {
		for (int i=0;i<this.gelaxkaMatrizea.length;i++) {
			for (int j=0;j<this.gelaxkaMatrizea[0].length;j++) {
				this.gelaxkaMatrizea[i][j]=new Gelaxka();
			}
		}
		int jartzekoFalta=this.minaKop;
		int randomZut=0;
		int randomErrenk=0;
		while (jartzekoFalta>0) {
			randomErrenk=(int)(Math.random()*this.gelaxkaMatrizea.length);
			randomZut=(int)(Math.random()*this.gelaxkaMatrizea[0].length);
			if (this.gelaxkaMatrizea[randomErrenk][randomZut].getMota()!=9) {
				this.gelaxkaMatrizea[randomErrenk][randomZut].setMota(9);;
				jartzekoFalta--;
			}
		}
		this.minaKopJarriGelaxketan();
	}
	
	private void minaKopJarriGelaxketan() {
		for (int errenk=0;errenk<this.gelaxkaMatrizea.length;errenk++) {
			for (int zutab=0;zutab<this.gelaxkaMatrizea[0].length;zutab++) {
				if (errenk>0 && zutab>0) {
					if (this.gelaxkaMatrizea[errenk-1][zutab-1].getMota()==9) {
						this.gelaxkaMatrizea[errenk][zutab].inkMota();
					}
				}
				if (errenk>0) {
					if (this.gelaxkaMatrizea[errenk-1][zutab].getMota()==9) {
						this.gelaxkaMatrizea[errenk][zutab].inkMota();
					}
				}
				if (errenk>0 && zutab<(this.gelaxkaMatrizea[0].length-1)) {
					if (this.gelaxkaMatrizea[errenk-1][zutab+1].getMota()==9) {
						this.gelaxkaMatrizea[errenk][zutab].inkMota();
					}
				}
				if (zutab>0) {
					if (this.gelaxkaMatrizea[errenk][zutab-1].getMota()==9) {
						this.gelaxkaMatrizea[errenk][zutab].inkMota();
					}
				}
				if (zutab<(this.gelaxkaMatrizea[0].length-1)) {
					if (this.gelaxkaMatrizea[errenk][zutab+1].getMota()==9) {
						this.gelaxkaMatrizea[errenk][zutab].inkMota();
					}
				}
				if (errenk<(this.gelaxkaMatrizea.length-1) && zutab>0) {
					if (this.gelaxkaMatrizea[errenk+1][zutab-1].getMota()==9) {
						this.gelaxkaMatrizea[errenk][zutab].inkMota();
					}
				}
				if (errenk<(this.gelaxkaMatrizea.length-1)) {
					if (this.gelaxkaMatrizea[errenk+1][zutab].getMota()==9) {
						this.gelaxkaMatrizea[errenk][zutab].inkMota();
					}
				}
				if (errenk<(this.gelaxkaMatrizea.length-1) && zutab<(this.gelaxkaMatrizea[0].length-1)) {
					if (this.gelaxkaMatrizea[errenk+1][zutab+1].getMota()==9) {
						this.gelaxkaMatrizea[errenk][zutab].inkMota();
					}
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
	
	public void irekiGelaxka(int x, int y) { //gelaxka hutsa bada errekurtsiboki ingurukoak ireki alboan mina bat egon arte
		System.out.println(this.gelaxkaMatrizea[x][y].getMota());
		if (x>=this.getErrenk() || y>=this.getZutab() || x<=0 || y<=0 || this.gelaxkaMatrizea[x][y].getKlikatuta()){
			return; //atera gelaxka ez bada existitzen edo clickatuta badago (return ez da beharrezkoa)
		}
		else {
			this.gelaxkaMatrizea[x][y].klikatu(); //zenbakia edo gelaxka hutsa baldin bada klikatu
			if(this.gelaxkaMatrizea[x][y].getMota()!=9){ //hutsa bada albokoen irekiGelaxka (mina izatera ez gara inoiz helduko lehenik eta behin zenbakia topatuko genukeelako)
				Dragamina.getDragamina().getListaGelaxkak()[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("c"+this.gelaxkaMatrizea[x][y].getMota()+".gif")));
				if (this.gelaxkaMatrizea[x][y].getMota()==0) {
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
			else {
				Dragamina.getDragamina().getListaGelaxkak()[x][y].setIcon(new ImageIcon(this.getClass().getResource("mina-r.gif")));
			}
		}
		
	}

	public void irekiGuztiak(int x, int y) { //ireki gelaxka guztiak klikatu dena izan ezik (mina delako)
		//TODO
	}
}
