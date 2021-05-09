package Controller;

import java.awt.Point;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Model.Gaufre;
import Model.IA;
import Model.IADifficile;
import Model.IAFacile;
import Model.SaveConfig;
import View.CollecteurEvenements;
import View.InterfaceGraphiqueFin;
import View.InterfaceGraphiqueMenu;

public class ControllerJeu implements CollecteurEvenements {

	private Gaufre gaufre;
	private IA iaJoueur1;
	private IA iaJoueur2;
	private SaveConfig saveConfig;

	public ControllerJeu(Gaufre gaufre) {
		saveConfig = new SaveConfig();
		this.gaufre = gaufre;
		this.iaJoueur1 = null;
		this.iaJoueur2 = null;
	}
	
	public ControllerJeu(Gaufre gaufre, IA iaJoueur1, IA iaJoueur2) {
		saveConfig = new SaveConfig();
		this.gaufre = gaufre;
		this.iaJoueur1 = null;
		this.iaJoueur1 = iaJoueur1;
		this.iaJoueur2 = iaJoueur2;
		
		if (iaJoueur1 != null) {
			Point coup = iaJoueur1.getCoup();
			new Thread(() -> {
				try {
					Thread.sleep(1000);
					clickSouris("clickCase", (int)coup.getY(), (int)coup.getX());
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}).start();
		}
	}
	

	private int mange(int posX, int posY) {
		int result = gaufre.mange(posX, posY);
		return result;
	}

	private void annulerCoup() {
		if (gaufre.annulerCoup()) {
			System.out.println("Dernier coup annulé");
		} else {
			System.out.println("Aucun coup déjà jouer");
		}
	}

	private void chargerPartie() {
		saveConfig.chargePartieSauvegarder();
		gaufre.updateGaufre(saveConfig.getGaufre());
		gaufre.metAJour();
	}

	private void sauvegarderPartie() {
		saveConfig.sauvegarderPartie(gaufre);
	}

	private void nouvellePartie() {
		gaufre.nouvellePartie();
	}
	
	private void quitterJeu() {
		System.exit(0);
	}

	@Override
	public boolean commande(String c) {
		switch (c) {
		case "nouvellePartie":
			nouvellePartie();
			break;
		case "chargerPartie":
			chargerPartie();

			break;
		case "sauvegarderPartie":
			sauvegarderPartie();
			break;
		case "annulerCoup":
			gaufre.annulerCoup();
			break;
		case "quitterJeu":
			quitterJeu();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + c);
		}
		return false;
	}

	public String toString() {
		return "Controller Jeu";
	}

	@Override
	public boolean clickSouris(String s, int l, int c) {
		switch (s) {
		case "clickCase":
			mange(l, c);
			if (gaufre.getEstTourDeJoueur1() && iaJoueur1 != null && !gaufre.getHasWinner()) {
				Point coup = iaJoueur1.getCoup();
				new Thread(() -> {
					try {
						Thread.sleep(1000);
						clickSouris("clickCase", (int)coup.getY(), (int)coup.getX());
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}).start();
			} else if (!gaufre.getEstTourDeJoueur1() && iaJoueur2 != null && !gaufre.getHasWinner()) {
				Point coup = iaJoueur2.getCoup();
				new Thread(() -> {
					try {
						Thread.sleep(1000);
						clickSouris("clickCase", (int)coup.getY(), (int)coup.getX());
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}).start();
			}
			
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + s);
		}
		return false;
	}
	
	@Override
	public boolean configuration(String c, InterfaceGraphiqueFin vueFin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean configuration(String c, InterfaceGraphiqueMenu vueMenu, Gaufre maGaufre, IA IAJ1, IA IAJ2) {
		// TODO Auto-generated method stub
		return false;
	}
}
