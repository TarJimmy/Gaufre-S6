package Controller;

import View.CollecteurEvenements;
import View.VueFin;
import View.VueMenu;

public class ControllerFin implements CollecteurEvenements {
	
	public ControllerFin() {
		
	}
	
	@Override
	public boolean commande(String c) {
		switch (c) {
			case "quitter":
				System.exit(0);
				break;
			case "menu":
				new VueMenu(new ControllerMenu());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + c);
		}
		return false;
	}

	@Override
	public boolean clickSouris(String s, int l, int c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean configuration(String c, VueMenu vueMenu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean configuration(String c, VueFin vueFin) {
		switch (c) {
			case "menu":
				vueFin.getFenetreFin().dispose();
				new VueMenu(new ControllerMenu());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + c);
		}
		return false;
	}

}
