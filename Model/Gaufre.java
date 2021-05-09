package Model;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.Stack;

import Patterns.Observable;

public class Gaufre extends Observable {
	
	public static final int SUPPRIMER = 0;
	public static final int PRESENT = 1;
	public static final int POISON = 2;
	
	private String joueur1;
	private String joueur2;
	private int[][] gaufre;
	
	private Boolean estTourDeJoueur1;
	
	private Stack<ArrayList<int[]>> historique; 
	private Boolean hasWinner;
	
	public Gaufre(String joueur1, String joueur2, int line, int colonne) {
		historique = new Stack<>(); 
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.gaufre = new int[line][colonne];
		this.estTourDeJoueur1 = true;
		this.hasWinner = false;
		nouvellePartie(line, colonne);
	}
	
	public Gaufre(String joueur1, String joueur2, Boolean estTourDeJoueur1, int[][] gaufreRestore, Stack<ArrayList<int[]>> historique) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.estTourDeJoueur1 = estTourDeJoueur1;
		this.historique = historique;
		this.gaufre = gaufreRestore;
		this.hasWinner = false;
	}
	
	public Gaufre(Gaufre gaufre) {
		this.joueur1 = gaufre.getJoueur1();
		this.joueur2 = gaufre.getJoueur2();
		this.gaufre = gaufre.copieGaufre();
		this.historique = gaufre.copieHistorique();
		this.estTourDeJoueur1 = gaufre.getEstTourDeJoueur1();
		this.hasWinner = false;
	}
	
	public int[][] getGaufre() {
		return gaufre;
	}
	
	public void setGaufre(int[][] gaufre) {
		this.gaufre = gaufre;
	}
	
	public Stack<ArrayList<int[]>> getHistorique() {
		return historique;
	}
	
	public int getNbLigne() {
		return gaufre.length;
	}
	
	public int getNbColonne() {
		return gaufre[0].length;
	}
	
	
	public String getJoueur1() {
		return joueur1;
	}


	public String getJoueur2() {
		return joueur2;
	}

	public Boolean getEstTourDeJoueur1() {
		return estTourDeJoueur1;
	}
	
	public void updateGaufre(Gaufre g) {
		this.joueur1 = g.joueur1;
		this.joueur2 = g.joueur2;
		this.gaufre = g.gaufre;
		this.historique = g.historique;
		this.hasWinner = false;
	}
	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @return
	 * SUPPRIMER si on souhaite manger une case supprimé
	 * POISON si on clique ou mange une case empoisonné
	 * PRESENT si tout s'est bien passé
	 */
	public int mange(int xPos, int yPos) {
		if (this.gaufre[yPos][xPos] == SUPPRIMER) {
			return SUPPRIMER;
		} else if (this.gaufre[yPos][xPos] == POISON) {
			//finit(...)
			this.hasWinner = true;
			metAJour();
			return POISON;
		} else {
			ArrayList<int[]> caseModifs = new ArrayList<>();
			int[][] copie = copieGaufre();
			for (int y = yPos; y < copie.length; y++) {
				for (int x = xPos; x < copie[0].length; x++) {
					if (copie[y][x] == POISON) {
						return POISON;
					} else if (copie[y][x] == PRESENT) {
						copie[y][x] = SUPPRIMER;
						caseModifs.add(new int[] {y, x});
					}
				}
			}
			this.gaufre = copie;
			estTourDeJoueur1 = !estTourDeJoueur1;
			historique.push(caseModifs);
			metAJour();
			return PRESENT;
		}
		
	}
	
	public Boolean annulerCoup() {
		if (!historique.empty()) {
			ArrayList<int[]> coupAAnnuler = historique.pop();
			for (int[] coup : coupAAnnuler) {
				gaufre[coup[0]][coup[1]] = PRESENT;
			}
			estTourDeJoueur1 = !estTourDeJoueur1;
			metAJour();
			return true;
		} else {
			return false;
		}
	}
	
	public void nouvellePartie(int line, int colonne) {
		gaufre = new int[line][colonne];
		nouvellePartie();
	}
	
	public void nouvellePartie() {
		historique.clear();
		for (int y = 0; y < gaufre.length; y++) {
			for (int x = 0; x < gaufre[0].length; x++) {
				this.gaufre[y][x] = PRESENT;
			}
		}
		this.gaufre[0][0] = POISON;
		metAJour();
	}
	
	public int[][] copieGaufre() {
		int[][] copie = new int[gaufre.length][gaufre[0].length];
		for (int y = 0; y < copie.length; y++) {
			copie[y] = gaufre[y].clone();
		}
		return copie;
	}
	
	@SuppressWarnings("unchecked")
	public Stack<ArrayList<int[]>> copieHistorique() {
		Stack<ArrayList<int[]>> stack = new Stack<>();
		Iterator<ArrayList<int[]>> it = historique.iterator();
		while (it.hasNext()) {
			ArrayList<int[]> listeCoupCourant = it.next();
			stack.push((ArrayList<int[]>)listeCoupCourant.clone());
		}
		return stack;
	}
	
	public String toString() {
		String res = "Historique : \n";
		Iterator<ArrayList<int[]>> it = historique.iterator();
		while (it.hasNext()) {
			ArrayList<int[]> listeCoupCourant = it.next();
			for(int[] coup : listeCoupCourant) {
				res += "(" + coup[0] + ", " + coup[1] + ") ";	
			}
			res += "\n";
		}
		res += "\nGaufre : \n";
		res += "   ";
		for (int x = 0; x < this.gaufre[0].length; x++) {
			res += x + " ";
		}
		res += "\n";
		for (int y = 0; y < this.gaufre.length; y++) {
			res += y + ": ";
			for (int x = 0; x < this.gaufre[0].length; x++) {
				res += this.gaufre[y][x] + " ";
			}
			res += "\n";
		}
		return res;
	}
	
	public Boolean getHasWinner() {
		return this.hasWinner;
	}
	
	public static void main (String[] args) {
	    System.out.println("-----Test Gaufre-----");
	    System.out.println("TEST fct manger---");
	    Gaufre g = new Gaufre("j1", "j2", 8, 10);
	    System.out.println(g);
	    g.mange(6, 6);
	    System.out.println(g);
   }
}
