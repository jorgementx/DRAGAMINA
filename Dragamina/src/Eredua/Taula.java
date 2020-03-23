package Eredua;

import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

import Bista.Dragamina;

public class Taula {
	
	private Gelaxka[][] gelaxkaMatrizea;
	private boolean minakJarrita;
	private int minaKop;
	private int banderaKop;
	
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
		this.minakJarrita=false;
		this.minaKop=this.gelaxkaMatrizea.length*zailtasuna;
		this.banderaKop=this.gelaxkaMatrizea.length*zailtasuna;
	}

	public void taulaHasieratu() {
		for (int x=0;x<this.gelaxkaMatrizea.length;x++) {
			for (int y=0;y<this.gelaxkaMatrizea[0].length;y++) {
				this.gelaxkaMatrizea[x][y]=new Gelaxka();
			}
		}
	}
	
	public void hasieratuMinak(int x, int y) {
		int jartzekoFalta=this.minaKop;
		int randomZut=0;
		int randomErrenk=0;
		while (jartzekoFalta>0) {
			randomZut=(int)(Math.random()*this.gelaxkaMatrizea.length);
			randomErrenk=(int)(Math.random()*this.gelaxkaMatrizea[0].length);
			if (this.gelaxkaMatrizea[randomZut][randomErrenk].getMota()!=9 && randomZut!=x && randomErrenk!=y) {
				this.gelaxkaMatrizea[randomZut][randomErrenk].setMota(9);
				jartzekoFalta--;
			}
		}
		this.minaKopJarriGelaxketan();
		this.minakJarrita=true;
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
		if (!this.minakJarrita) {
			this.hasieratuMinak(x, y);
		}
		if (x>=this.getZutab() || y>=this.getErrenk() || x<0 || y<0) {}
		else {
			if (!this.gelaxkaMatrizea[x][y].getKlikatuta() && !this.gelaxkaMatrizea[x][y].getBandera()) {
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
	
	public void eskumakoBotoia(int x, int y) {
		if (!this.gelaxkaMatrizea[x][y].getKlikatuta()) {
			if (!this.gelaxkaMatrizea[x][y].getBandera() && !this.gelaxkaMatrizea[x][y].getGalderaMarka()) {
				this.gelaxkaMatrizea[x][y].setBandera();
				this.banderaKop--;
				Dragamina.getDragamina().eguneratuBanderaKont();
				Dragamina.getDragamina().getListaGelaxkak()[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("bandera.gif")));
			}
			else if (this.gelaxkaMatrizea[x][y].getBandera()) {
				this.gelaxkaMatrizea[x][y].setBandera();
				this.banderaKop++;
				this.gelaxkaMatrizea[x][y].setGalderaMarka();
				Dragamina.getDragamina().getListaGelaxkak()[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("marca.gif")));
			}
			else {
				this.gelaxkaMatrizea[x][y].setGalderaMarka();
				Dragamina.getDragamina().getListaGelaxkak()[x][y].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("tablero.gif")));
			}
		}
	}

	public void irekiGuztiak() { //ireki gelaxka guztiak klikatu dena izan ezik (mina delako)

		for (int zutabe=0; zutabe < gelaxkaMatrizea.length; zutabe++){
			for (int errenkada=0; errenkada < gelaxkaMatrizea[0].length; errenkada++){
				if (!gelaxkaMatrizea[zutabe][errenkada].getKlikatuta()){
					gelaxkaMatrizea[zutabe][errenkada].klikatu();
					if(this.gelaxkaMatrizea[zutabe][errenkada].getMota()!=9){
						if (this.gelaxkaMatrizea[zutabe][errenkada].getBandera()){
							Dragamina.getDragamina().getListaGelaxkak()[zutabe][errenkada].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("mina-x.gif")));	
						}
						else{
							Dragamina.getDragamina().getListaGelaxkak()[zutabe][errenkada].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("c"+this.gelaxkaMatrizea[zutabe][errenkada].getMota()+".gif")));
						}
					}
					else{
						Dragamina.getDragamina().getListaGelaxkak()[zutabe][errenkada].setIcon(new ImageIcon(Dragamina.getDragamina().getClass().getResource("mina-n.gif")));
					}
				}
			}
		}
	}
	public boolean irabaziKonprobaketa(){	//creo que tendriamos que meter un atributo de las gelaxkas sin clickar para no tener que contar cada vez las gelaxkas sin clickar para comprobar si has ganado
		//tambien se puede hacer mirandolas todas y si hay alguna sin clickar que no sea mina return false, si no return true
		
		boolean irabazita=false;
		int kont=0;
		for (int zutabe=0; zutabe < gelaxkaMatrizea.length; zutabe++){
			for (int errenkada=0; errenkada < gelaxkaMatrizea[0].length; errenkada++){
				if (!gelaxkaMatrizea[zutabe][errenkada].getKlikatuta()){
					kont++;
				}
			}
		}
		if (kont==minaKop){
			irabazita=true;
		}
		return irabazita;
	}
	
	public void banderaGuztiakJarri(){	//estaria bien tener un array con las posiciones de todas las minas para evitar recorrer toda la matriz
		
		for (int zutabe=0; zutabe < gelaxkaMatrizea.length; zutabe++){
			for (int errenkada=0; errenkada < gelaxkaMatrizea[0].length; errenkada++){
				if (!gelaxkaMatrizea[zutabe][errenkada].getKlikatuta() && !gelaxkaMatrizea[zutabe][errenkada].getBandera()){
					gelaxkaMatrizea[zutabe][errenkada].setBandera();
				}
			}
		}
	}
	
}
