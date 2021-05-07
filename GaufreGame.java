import Controller.ControllerJeu;
import Model.Gaufre;
import View.InterfaceGraphique;

public class GaufreGame {
	
	public static void main(String[] args ) {
		String j1 = "Joueur 1";
	    String j2 = "Joueur 2";
	    int ligne = 5;
	    int colonne = 10;
		Gaufre g = new Gaufre(j1, j2, ligne, colonne);
		ControllerJeu c = new ControllerJeu(g);
		
		InterfaceGraphique.demarrer(g, c);
	}
}
