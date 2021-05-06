import java.util.Iterator;
import java.util.Scanner;

import Model.Gaufre;
import Model.SaveConfig;

public class Application {
	
	private Gaufre gaufre;
	private Boolean estTourDeJoueur1;
	private SaveConfig saveConfig;
	
	Application() {
		saveConfig = new SaveConfig();
		startInitialisation();
	}
	
	private void startInitialisation() {
		//Appeler la fenetre de configuration du jeu, qui génère la gaufre (nouvelle ou par chargement)
		
		//A suupprimer
		String j1 = "Joueur 1";
	    String j2 = "Joueur 2";
	    int ligne = 8;
	    int colonne = 10;
		gaufre = new Gaufre(j1, j2, ligne, colonne);
		estTourDeJoueur1 = true;
		startJeu();
	}
	
	private void startJeu() {
		Scanner scan = new Scanner(System.in);
		int res;
		do {
			System.out.println(gaufre);
			System.out.println("si x = -1 annule le dernier coup");
			System.out.println("si x = -2 sauvegarder Partie");
			System.out.println("si x = -3 charger partie");
			System.out.print("x : ");
			int x = scan.nextInt();
			if (x == -1) {
				annulerCoup();
				res = -1;
				continue;
			} else if (x == -2){
				sauvegarderPartie();
				res = -2;
				continue;
			} else if (x == -3) {
				res = -3;
				chargerPartie();
				continue;
			}
			System.out.print("y : ");
			int y = scan.nextInt();
			res = mange(x, y);
		} while(res != Gaufre.POISON);
		
	}
	
	private int mange(int posX, int posY) {
		int result = gaufre.mange(posX, posY);
		switch (result) {
			case Gaufre.POISON: 
				System.out.println((estTourDeJoueur1 ? gaufre.getJoueur2() : gaufre.getJoueur1()) + " à gagner !");
				break;
			case Gaufre.SUPPRIMER:
				System.out.println("Case déjà mangé");
				break;
			case Gaufre.PRESENT:
				System.out.println("Actualiser l'affichage avec la nouvelle gaufre");
				estTourDeJoueur1 = !estTourDeJoueur1;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + result);
		}
		return result;
	}
	
	private void annulerCoup() {
		if (gaufre.annulerCoup()) {
			System.out.println("Dernier coup annulé");
			estTourDeJoueur1 = !estTourDeJoueur1;
		} else {
			System.out.println("Aucun coup déjà jouer");
		}
	}
	
	private void sauvegarderPartie() {
		saveConfig.sauvegarderPartie(gaufre, estTourDeJoueur1);
	}
	
	private void chargerPartie() {
		saveConfig.chargePartieSauvegarder();
		gaufre = saveConfig.getGaufre();
		estTourDeJoueur1 = saveConfig.getTour();
	}
	
	public Boolean EstTourDeJoueur1() {
		return estTourDeJoueur1;
	}
	
	public static void main (String[] args){
	    System.out.println("Hello World");
	    Application app = new Application();
   }
}
