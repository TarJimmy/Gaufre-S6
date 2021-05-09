package Controller;

import java.awt.Point;

import Model.Gaufre;
import Model.IA;
import Model.SaveConfig;
import View.CollecteurEvenements;
import View.InterfaceGraphiqueJeu;
import View.InterfaceGraphiqueFin;
import View.InterfaceGraphiqueMenu;

public class ControllerMenu implements CollecteurEvenements {
	private Gaufre gaufre;
	private ControllerJeu jeu;
	
	public ControllerMenu() {}
	
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
	public boolean configuration(String c, InterfaceGraphiqueMenu vueMenu, Gaufre gaufre, IA iaJ1, IA iaJ2) {
		switch (c) {
			case "valider":
				// FAUX
//				String joueur1 = vueMenu.getJoueur1();
//				String joueur2 = vueMenu.getJoueur2();
//				int nbLigne = Integer.parseInt(vueMenu.getLigne());
//				int nbColonne = Integer.parseInt(vueMenu.getColonne());
//				vueMenu.getFenetreMenu().dispose();
				Gaufre g = gaufre;
				System.out.println(iaJ1 + " " + iaJ2);
				new Thread(() -> {
					jeu = new ControllerJeu(g, iaJ1, iaJ2);	
				}).start();
				vueMenu.close();
				try {
					Thread.sleep(100);
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				InterfaceGraphiqueJeu.demarrer(g, jeu);
				break;
			case "charger":
				//code de charger
				vueMenu.getFenetreMenu().dispose();
				SaveConfig sc = new SaveConfig();
				sc.chargePartieSauvegarder();
				gaufre = sc.getGaufre();
				jeu = new ControllerJeu(gaufre);
				vueMenu.close();
				InterfaceGraphiqueJeu.demarrer(gaufre, jeu);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + c);
		}
		return false;
	}

	@Override
	public boolean configuration(String c, InterfaceGraphiqueFin vueFin) {
		// TODO Auto-generated method stub
		return false;
	}

}
