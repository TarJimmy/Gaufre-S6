package Controller;

import Model.Gaufre;
import Model.SaveConfig;
import View.CollecteurEvenements;
import View.InterfaceGraphique;
import View.VueFin;
import View.VueMenu;

public class ControllerMenu implements CollecteurEvenements {
	private Gaufre gaufre;
	private ControllerJeu jeu;
	
	public ControllerMenu() {
		
	}
	
	@Override
	public boolean commande(String c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean clickSouris(String s, int l, int c) {
		return false;
	}

	@Override
	public boolean configuration(String c, VueMenu vueMenu) {
		switch (c) {
			case "valider":
				// code
				String joueur1 = vueMenu.getJoueur1();
				String joueur2 = vueMenu.getJoueur2();
				int nbLigne = Integer.parseInt(vueMenu.getLigne());
				int nbColonne = Integer.parseInt(vueMenu.getColonne());
				vueMenu.getFenetreMenu().dispose();
				gaufre = new Gaufre(joueur1, joueur2, nbLigne, nbColonne);
				jeu = new ControllerJeu(gaufre);
				InterfaceGraphique.demarrer(gaufre, jeu);
				break;
			case "charger":
				//code de charger
				vueMenu.getFenetreMenu().dispose();
				SaveConfig sc = new SaveConfig();
				sc.chargePartieSauvegarder();
				gaufre = sc.getGaufre();
				jeu = new ControllerJeu(gaufre);
				InterfaceGraphique.demarrer(gaufre, jeu);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + c);
		}
		return false;
	}

	@Override
	public boolean configuration(String c, VueFin vueFin) {
		// TODO Auto-generated method stub
		return false;
	}

}
