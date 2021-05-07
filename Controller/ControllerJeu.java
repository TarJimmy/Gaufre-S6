package Controller;

import java.util.Scanner;

import Model.Gaufre;
import Model.SaveConfig;
import View.CollecteurEvenements;

public class ControllerJeu implements CollecteurEvenements {
	
	private Gaufre gaufre;
	private SaveConfig saveConfig;
	
	public ControllerJeu(Gaufre gaufre) {
		saveConfig = new SaveConfig();
		this.gaufre = gaufre;
	}
	
	@SuppressWarnings("unused")
	private void startJeuText() {
		
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
		scan.close();
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

	@Override
	public boolean commande(String c) {	
		switch(c) {
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
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + s);
		}
		return false;
	}
}
