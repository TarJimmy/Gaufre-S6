package Controller;

import Model.Gaufre;
import Model.IA;
import View.CollecteurEvenements;
import View.InterfaceGraphiqueFin;
import View.InterfaceGraphiqueMenu;

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
				new InterfaceGraphiqueMenu(new ControllerMenu());
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
	public boolean configuration(String c, InterfaceGraphiqueFin vueFin) {
		switch (c) {
			case "menu":
				vueFin.getFenetreFin().dispose();
				new InterfaceGraphiqueMenu(new ControllerMenu());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + c);
		}
		return false;
	}

	@Override
	public boolean configuration(String c, InterfaceGraphiqueMenu vueMenu, Gaufre maGaufre, IA IAJ1, IA IAJ2) {
		// TODO Auto-generated method stub
		return false;
	}

}
