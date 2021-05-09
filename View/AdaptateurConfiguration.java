package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import Model.Gaufre;
import Model.IA;
import Model.IADifficile;
import Model.IAFacile;


public class AdaptateurConfiguration implements ActionListener{
	private CollecteurEvenements control;
	private String commande;
	private InterfaceGraphiqueMenu vueMenu;
	private InterfaceGraphiqueFin vueFin;
	
	AdaptateurConfiguration(CollecteurEvenements c, String com, InterfaceGraphiqueMenu vueMenu) {
		control = c;
		commande = com;
		this.vueMenu = vueMenu;
	}
	
	AdaptateurConfiguration(CollecteurEvenements c, String com, InterfaceGraphiqueFin vueFin) {
		control = c;
		commande = com;
		this.vueFin = vueFin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (vueMenu != null) {
			Gaufre g = null;
			IA iaJ1 = null;
			IA iaJ2 = null;
			if (commande.equals("valider")) {
				String joueur1 = vueMenu.getJoueur1();
				System.out.println(joueur1 + " Joueur 1");
				String joueur2 = vueMenu.getJoueur2();
				int nbLigne = Integer.parseInt(vueMenu.getLigne());
				int nbColonne = Integer.parseInt(vueMenu.getColonne());
				g = new Gaufre(joueur1, joueur2, nbLigne, nbColonne);
				
				for (JRadioButton btnCurrent : vueMenu.getRadioBtnJ1()) {
					if (btnCurrent.isSelected()) {
						if (btnCurrent.getText().equals("IA Facile")) {
							iaJ1 = new IAFacile(g);
						} else if (btnCurrent.getText().equals("IA Difficile")) {
							iaJ1 = new IADifficile(g);
						}
					}
				}
				for (JRadioButton btnCurrent : vueMenu.getRadioBtnJ2()) {
					if (btnCurrent.isSelected()) {
						if (btnCurrent.getText().equals("IA Facile")) {
							iaJ2 = new IAFacile(g);
						} else if (btnCurrent.getText().equals("IA Difficile")) {
							iaJ2 = new IADifficile(g);
						}
					}
				}
			}
			control.configuration(commande, vueMenu, g, iaJ1, iaJ2);
		} else {
			control.configuration(commande, vueFin);
		}
	}
}
