package View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdapteurMouse extends MouseAdapter{
	
	private VueGaufre vueGaufre;
	private String commande;
	CollecteurEvenements control;
	public AdapteurMouse(VueGaufre vue, CollecteurEvenements controller, String commande) {
		vueGaufre = vue;
		this.commande = commande;
		this.control = controller; 
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int ligne = (int) x / vueGaufre.getLargeurCase();
		int colonne = (int) y / vueGaufre.getHauteurCase();

		control.clickSouris(commande, ligne, colonne);
	}

}
