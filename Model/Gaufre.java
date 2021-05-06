package Model;

public class Gaufre {
	public static final int JOUEUR_1 = 0;
	public static final int JOUEUR_2 = 1;
	
	public static final int SUPPRIMER = 0;
	public static final int PRESENT = 1;
	public static final int POISON = 2;
	
	private String joueur1;
	private String joueur2;
	private int[][] gaufre;
	
	public Gaufre(String joueur1, String joueur2, int line, int colonne) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.gaufre = new int[line][colonne];
		for (int x = 0; x < line; x++) {
			for (int y = 0; y < colonne; y++) {
				this.gaufre[x][y] = PRESENT;
			}
		}
		this.gaufre[0][0] = POISON;
	}
	
	public int[][] getGaufre() {
		return gaufre;
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
			int[][] copie = copieGaufre();
			for (int x = xPos; x < copie.length; x++) {
				for (int y = yPos; y < copie[0].length; y++) {
					if (copie[x][y] == POISON) {
						return POISON;
					} else {
						copie[x][y] = SUPPRIMER; 
					}
				}
			}
			this.gaufre = copie;
			return PRESENT;
		}
		
	}
	
	private int[][] copieGaufre() {
		int[][] copie = new int[gaufre.length][gaufre[0].length];
		for (int x = 0; x < copie.length; x++) {
			copie[x] = gaufre[x].clone();
		}
		return copie;
	}
	public String toString() {
		String res = "";
		for (int x = 0; x < this.gaufre.length; x++) {
			for (int y = 0; y < this.gaufre[0].length; y++) {
				res += this.gaufre[x][y] + " ";
			}
			res += "\n";
		}
		return res;
	}
	
	public static void main (String[] args) {
	    System.out.println("-----Test Gaufre-----");
	    Gaufre g = new Gaufre("j1", "j2", 8, 10);
	    System.out.println(g);
	    g.mange(6, 6);
	    System.out.println(g);
	    g.mange(9, 3);
	    System.out.println(g);
   }
}
