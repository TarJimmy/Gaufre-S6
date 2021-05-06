package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Gaufre {
	
	public static final int SUPPRIMER = 0;
	public static final int PRESENT = 1;
	public static final int POISON = 2;
	
	private String joueur1;
	private String joueur2;
	private int[][] gaufre;
	
	private Stack<ArrayList<int[]>> historique; 
	
	public Gaufre(String joueur1, String joueur2, int line, int colonne) {
		historique = new Stack<>();
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.gaufre = new int[line][colonne];
		nouvellePartie(line, colonne);
	}
	
	public Gaufre(String joueur1, String joueur2, int[][] gaufreRestore, Stack<ArrayList<int[]>> historique) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.historique = historique;
		this.gaufre = gaufreRestore;
	}
	
	public int[][] getGaufre() {
		return gaufre;
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
		if (this.gaufre[xPos][yPos] == SUPPRIMER) {
			return SUPPRIMER;
		} else if (this.gaufre[xPos][yPos] == POISON) {
			return POISON;
		} else {
			ArrayList<int[]> caseModifs = new ArrayList<>();
			int[][] copie = copieGaufre();
			for (int x = xPos; x < copie.length; x++) {
				for (int y = yPos; y < copie[0].length; y++) {
					if (copie[x][y] == POISON) {
						return POISON;
					} else if (copie[x][y] == PRESENT) {
						copie[x][y] = SUPPRIMER;
						caseModifs.add(new int[] {x, y});
					}
				}
			}
			this.gaufre = copie;
			historique.push(caseModifs);
			return PRESENT;
		}
		
	}
	
	public Boolean annulerCoup() {
		if (!historique.empty()) {
			ArrayList<int[]> coupAAnnuler = historique.pop();
			for (int[] coup : coupAAnnuler) {
				gaufre[coup[0]][coup[1]] = PRESENT;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void nouvellePartie(int line, int colonne) {
		historique.clear();
		for (int x = 0; x < line; x++) {
			for (int y = 0; y < colonne; y++) {
				this.gaufre[x][y] = PRESENT;
			}
		}
		this.gaufre[0][0] = POISON;
	}
	
	
	private int[][] copieGaufre() {
		int[][] copie = new int[gaufre.length][gaufre[0].length];
		for (int x = 0; x < copie.length; x++) {
			copie[x] = gaufre[x].clone();
		}
		return copie;
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
		for (int y = 0; y < this.gaufre[0].length; y++) {
			res += y + " ";
		}
		res += "\n";
		for (int x = 0; x < this.gaufre.length; x++) {
			res += x + ": ";
			for (int y = 0; y < this.gaufre[0].length; y++) {
				res += this.gaufre[x][y] + " ";
			}
			res += "\n";
		}
		return res;
	}
	
	public static void main (String[] args) {
	    System.out.println("-----Test Gaufre-----");
	    System.out.println("TEST fct manger---");
	    Gaufre g = new Gaufre("j1", "j2", 8, 10);
	    System.out.println(g);
	    g.mange(6, 6);
	    System.out.println();
   }
}
