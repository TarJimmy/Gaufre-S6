import Model.Gaufre;

public class Application {
	
	private Gaufre gaufre;
	private int tourCourant;
	
	Application() {
		String j1 = "Joueur 1";
	    String j2 = "Joueur 2";
	    int ligne = 8;
	    int colonne = 10;
		gaufre = new Gaufre(j1, j2, ligne, colonne);
		tourCourant = gaufre.JOUEUR_1;
	}
	
	
	
	
	public static void main (String[] args){
	    System.out.println("Hello World");
	    
	    Application app = new Application();
   }
}
