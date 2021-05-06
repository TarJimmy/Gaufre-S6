package Model;

import java.io.File;
import Configuration.Configuration;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import java.util.Scanner;
//date
//joueur1
//joueur2
//tour de qui
//liste des coups dans l'ordre de l'historique
//ligne colonne
//matrice gaufre

public class SaveConfig {
	private String date;
	private Boolean tour;
	private Gaufre gaufre;
	
	public SaveConfig() {
		tour = null;
		date = null;
		gaufre = null;
	}
	
	public void sauvegarderPartie(Gaufre gaufre, Boolean tour){
		String filePath = Configuration.instance().dirSauv + "/sauv.txt";
		try {
			// Create new file if needed
			File file = new File(filePath);
			file.createNewFile();
			// Write to the file
			FileWriter fWriter = new FileWriter(filePath);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now(); 			
			fWriter.write(dtf.format(now) + "\n");
			
			fWriter.write(gaufre.getJoueur1() + "\n");
			fWriter.write(gaufre.getJoueur2() + "\n");
			fWriter.write(tour.toString() + "\n");			
			Iterator<ArrayList<int[]>> it = gaufre.getHistorique().iterator();
			
			String strHistorique = "";
			while (it.hasNext()) {
				ArrayList<int[]> listeCoupCourant = it.next();
				for(int[] coup : listeCoupCourant) {
					strHistorique += "" + coup[0] + "," + coup[1] + " ";	
				}
				strHistorique += "\n";
			}
			
			strHistorique += "endHistorique\n";
			fWriter.write(strHistorique);
			
			fWriter.write(gaufre.getNbLigne() + " " + gaufre.getNbColonne() + "\n");
			int[][] gaufreArray = gaufre.getGaufre();
			for (int i = 0; i < gaufre.getNbLigne(); i++) {
				for (int j = 0; j < gaufre.getNbColonne(); j++) {
					fWriter.write(gaufreArray[i][j] + " ");
				}
				fWriter.write("\n");
			}
			
			fWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
 
	}
	
	public void chargePartieSauvegarder() {
		try {
			File file = new File(Configuration.instance().dirSauv + "/sauv.txt");
			Scanner fReader = new Scanner(file);
			// Initialization variables banales
			this.date = fReader.nextLine().trim();
			String joueur1 = fReader.nextLine().trim();
			String joueur2 = fReader.nextLine().trim();
			this.tour = Boolean.parseBoolean(fReader.nextLine().trim());
			//Initialisation historique
			Stack<ArrayList<int[]>> historique = new Stack<ArrayList<int[]>>();
			String lineCurrent = fReader.nextLine().trim();
			while (!lineCurrent.equals("endHistorique")) {
				ArrayList<int[]> listCoupCurrente = new ArrayList<>();
				String[] tabListCoup = lineCurrent.split(" ");
				for (String coup : tabListCoup) {
					int[] coupCurrent = new int[2];
					String[] coordonne = coup.split(",");
					System.out.println(coup);
					coupCurrent[0] = Integer.parseInt(coordonne[0]);
					coupCurrent[1] = Integer.parseInt(coordonne[1]);
					listCoupCurrente.add(coupCurrent);
				}
				historique.push(listCoupCurrente);
				lineCurrent = fReader.nextLine().trim();
			}
			
			//Initialisation matrice gaufre
			String[] lc = fReader.nextLine().trim().split(" ");
			int lignes = Integer.parseInt(lc[0]); 
			int colonnes = Integer.parseInt(lc[1]);
			int[][] gaufreArray = new int[lignes][colonnes];
			
			for (int i = 0; i < lignes; i++) {
				String[] ligneInt = fReader.nextLine().trim().split(" ");
				for (int j = 0; j < colonnes; j++) {	
					gaufreArray[i][j] = Integer.parseInt(ligneInt[j]);
				}
			}
			
			fReader.close();

			this.gaufre = new Gaufre(joueur1, joueur2, gaufreArray, historique);
			
		} catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	
	// date tour et gaufre initiliasés après appel de chargePartieSauvegarder
	public String getDatePartie() {
		return date;
	}
	
	public Boolean getTour() {
		return tour;
	}

	public Gaufre getGaufre() {
		return gaufre;
	}

	public static void main(String[] args) {
		Gaufre g = new Gaufre("j1", "j2", 8, 10);
		g.mange(6, 6);
		g.mange(2, 4);
	    System.out.println(g);
	    SaveConfig sc = new SaveConfig();
	    sc.sauvegarderPartie(g, false);
	    sc.chargePartieSauvegarder();
	    Gaufre g2 = sc.getGaufre();
	    System.out.println(sc.getDatePartie());
	    System.out.println(sc.getTour());
	    System.out.println(g2);
	}
}

