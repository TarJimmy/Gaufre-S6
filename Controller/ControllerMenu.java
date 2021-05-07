package Controller;

import View.CollecteurEvenements;

public class ControllerMenu implements CollecteurEvenements {

	
	public ControllerMenu() {}
	
	@Override
	public boolean commande(String c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean clickSouris(String s, int l, int c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean configuration(String c, int nbLigne, int nbColonne, String joueur1, String joueur2) {
		// TODO Auto-generated method stub
		switch (c) {
		case "valider": 
			// code 
			
			break;
		case "charger":
			//code de charger
		default:
			throw new IllegalArgumentException("Unexpected value: " + c);
		}
		return false;
	}

}
