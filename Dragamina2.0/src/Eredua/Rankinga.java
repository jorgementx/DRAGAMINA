package Eredua;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import Bista.Dragamina;
import Bista.LoginPantalla;
import Bista.ZailtasunPantaila;

public class Rankinga {
	
	private static Rankinga nRankinga=null;
	
	private Rankinga() {}
	
	public static Rankinga getRankinga() {
		if (nRankinga == null) {
			nRankinga = new Rankinga();
		}
		return nRankinga;
	}
	
	public void RankingaEguneratu() throws IOException {
		int kont=0;
		int min=-1;
		String minIzena="";
		File fitxategia=new File("puntuazioak.txt");
		File bitartekaria=new File("bitartekaria.txt");
		BufferedReader irakurle=new BufferedReader(new FileReader(fitxategia));
		BufferedWriter idazle;
		String unekoLerroa;
		while((unekoLerroa=irakurle.readLine()) != null) {
			String[] elementuak=unekoLerroa.split("\t");
			int puntuazioa=Integer.parseInt(elementuak[1]);
			if (min==-1) {
				min=puntuazioa;
				minIzena=elementuak[0];
			}
			else if (puntuazioa<=min) {
				min=puntuazioa;
				minIzena=elementuak[0];
			}
			kont++;
		}
		irakurle.close();
		if (kont==10) {
			if (Taula.getTaula().getPuntuazioa()>min) {
				boolean minKenduta=false;
				irakurle=new BufferedReader(new FileReader(fitxategia));
				idazle=new BufferedWriter(new FileWriter(bitartekaria, true));
				unekoLerroa=irakurle.readLine();
				if (unekoLerroa.contains(minIzena+"\t"+min)) {
					unekoLerroa=irakurle.readLine();
					minKenduta=true;
				}
				idazle.write(unekoLerroa);
				while ((unekoLerroa=irakurle.readLine())!=null) {
					if (!unekoLerroa.contains(minIzena+"\t"+min) || minKenduta) {
						idazle.write("\n"+unekoLerroa);
					}
					if (!minKenduta && unekoLerroa.contains(minIzena+"\t"+min)) {
						minKenduta=true;
					}
				}
				idazle.write("\n"+LoginPantalla.getLoginPantalla().getJokIzena()+"\t"+Taula.getTaula().getPuntuazioa());
				irakurle.close();
				idazle.close();
				irakurle=new BufferedReader(new FileReader(bitartekaria));
				idazle=new BufferedWriter(new FileWriter(fitxategia));
				unekoLerroa=irakurle.readLine();
				idazle.write(unekoLerroa);
				idazle.close();
				idazle=new BufferedWriter(new FileWriter(fitxategia, true));
				while ((unekoLerroa=irakurle.readLine())!=null) {
					idazle.write("\n"+unekoLerroa);
				}
				irakurle.close();
				idazle.close();
			}			
		}
		else {
			irakurle=new BufferedReader(new FileReader(fitxategia));
			idazle=new BufferedWriter(new FileWriter(fitxategia, true));
			if (irakurle.readLine()==null) {
				idazle.write(LoginPantalla.getLoginPantalla().getJokIzena()+"\t"+Taula.getTaula().getPuntuazioa());
			}
			else {
				idazle.write("\n"+LoginPantalla.getLoginPantalla().getJokIzena()+"\t"+Taula.getTaula().getPuntuazioa());
			}
			irakurle.close();
			idazle.close();
		}
		bitartekaria.delete();
	}
	
	private String[] getRankingaFitxategitik() throws IOException {		
		File fitxategia;
		fitxategia = new File("puntuazioak.txt");
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(fitxategia));
		int kont = 0;
		String[] zerrenda = null;
		String lerroa;
		while (reader.readLine() != null) {
			lerroa = reader.readLine();
			zerrenda[kont] = lerroa;
			kont = kont + 1;
		}
		reader.close();
		return zerrenda;
	}
	
	public String[] getZerrenda() {
		String[] zerrenda = null;
		try {
			zerrenda = Rankinga.getRankinga().getRankingaFitxategitik();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zerrenda;
	}
}