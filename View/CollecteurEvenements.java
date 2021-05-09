package View;

public interface CollecteurEvenements {
	boolean commande(String c);
	boolean clickSouris(String s, int l, int c);
	boolean configuration(String c, VueMenu vueMenu);
	boolean configuration(String c, VueFin vueFin);
}

