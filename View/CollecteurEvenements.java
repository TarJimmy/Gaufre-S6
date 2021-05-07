package View;

public interface CollecteurEvenements {
	boolean commande(String c);
	boolean clickSouris(String s, int l, int c);
	boolean configuration(String c, int nbLigne, int nbColonne, String joueur1, String joueur2);
}

