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
			this.gelaxkaMatrizea=new Gelaxka[10][15];
		}
		else if (zailtasuna==3) {
			this.gelaxkaMatrizea=new Gelaxka[12][25];
		}
		this.minaKop=this.gelaxkaMatrizea.length*zailtasuna;
	}

	public void taulaHasieratu() {
		for (int x=0;x<this.gelaxkaMatrizea.length;x++) {
			for (int y=0;y<this.gelaxkaMatrizea[0].length;y++) {
				this.gelaxkaMatrizea[x][y]=new Gelaxka();
			}
		}
		int jartzekoFalta=this.minaKop;
		int randomZut=0;
		int randomErrenk=0;
		while (jartzekoFalta>0) {
			randomZut=(int)(Math.random()*this.gelaxkaMatrizea.length);
			randomErrenk=(int)(Math.random()*this.gelaxkaMatrizea[0].length);
			if (this.gelaxkaMatrizea[randomZut][randomErrenk].getMota()!=9) {
				this.gelaxkaMatrizea[randomZut][randomErrenk].setMota(9);;
				jartzekoFalta--;
			}
		}
		this.minaKopJarriGelaxketan();
	}
	
	private void minaKopJarriGelaxketan() {
		for (int x=0;x<this.gelaxkaMatrizea.length;x++) {
			for (int y=0;y<this.gelaxkaMatrizea[0].length;y++) {
				if (this.gelaxkaMatrizea[x][y].getMota()!=9) {
					if (x>0 && y>0) {
						if (this.gelaxkaMatrizea[x-1][y-1].getMota()==9) {
							this.gelaxkaMatrizea[x][y].inkMota();
						}
					}
					if (x>0) {
						if (this.gelaxkaMatrizea[x-1][y].getMota()==9) {
							this.gelaxkaMatrizea[x][y].inkMota();
						}
					}
					if (x>0 && y<(this.gelaxkaMatrizea[0].length-1)) {
						if (this.gelaxkaMatrizea[x-1][y+1].getMota()==9) {
							this.gelaxkaMatrizea[x][y].inkMota();
						}
					}
					if (y>0) {
						if (this.gelaxkaMatrizea[x][y-1].getMota()==9) {
							this.gelaxkaMatrizea[x][y].inkMota();
						}
					}
					if (y<(this.gelaxkaMatrizea[0].length-1)) {
						if (this.gelaxkaMatrizea[x][y+1].getMota()==9) {
							this.gelaxkaMatrizea[x][y].inkMota();
						}
					}
					if (x<(this.gelaxkaMatrizea.length-1) && y>0) {
						if (this.gelaxkaMatrizea[x+1][y-1].getMota()==9) {
							this.gelaxkaMatrizea[x][y].inkMota();
						}
					}
					if (x<(this.gelaxkaMatrizea.length-1)) {
						if (this.gelaxkaMatrizea[x+1][y].getMota()==9) {
							this.gelaxkaMatrizea[x][y].inkMota();
						}
					}
					if (x<(this.gelaxkaMatrizea.length-1) && y<(this.gelaxkaMatrizea[0].length-1)) {
						if (this.gelaxkaMatrizea[x+1][y+1].getMota()==9) {
							this.gelaxkaMatrizea[x][y].inkMota();
						}
					}
				}
			}
		}
	}
	
	public int getZutab() {
		return this.gelaxkaMatrizea.length;
	}
	
	public int getErrenk() {
		return this.gelaxkaMatrizea[0].length;
	}
	
	public Gelaxka getGelaxka(int x, int y) {
		return this.gelaxkaMatrizea[x][y];
	}
	
	public void irekiGelaxka(int x, int y) { //gelaxka hutsa bada errekurtsiboki ingurukoak ireki alboan mina bat egon arte
		if (x>=this.getZutab() || y>=this.getErrenk() || x<0 || y<0) {}
		else {
			if (this.gelaxkaMatrizea[x][y].getKlikatuta()) {}
			else {
				this.gelaxkaMatrizea[x][y].klikatu(); //zenbakia edo gelaxka hutsa baldin bada klikatu
				if(this.gelaxkaMatrizea[x][y].getMota()!=9){ //hutsa bada albokoen irekiGelaxka (mina izatera ez gara inoiz helduko lehenik eta behin zenbakia topatuko genukeelako)
					Dragamina.getDragamina().getListaGelaxkak()[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("c"+this.gelaxkaMatrizea[x][y].getMota()+".gif")));
					if (this.gelaxkaMatrizea[x][y].getMota()==0) {
						irekiGelaxka(x-1,y-1);
						irekiGelaxka(x,y-1);
						irekiGelaxka(x+1,y-1);
						irekiGelaxka(x-1,y);
						irekiGelaxka(x+1,y);
						irekiGelaxka(x-1,y+1);
						irekiGelaxka(x,y+1);
						irekiGelaxka(x+1,y+1);
					}
				}
				else {
					Dragamina.getDragamina().getListaGelaxkak()[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("mina-r.gif")));
				}
			}
		}
		
	}

	public void irekiGuztiak(int x, int y) { //ireki gelaxka guztiak klikatu dena izan ezik (mina delako)
		//TODO
	}
}
