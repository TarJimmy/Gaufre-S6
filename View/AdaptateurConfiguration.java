package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdaptateurConfiguration implements ActionListener{
	private CollecteurEvenements control;
	private String commande;
	private VueMenu vueMenu;
	AdaptateurConfiguration(CollecteurEvenements c, String com, VueMenu vueMenu) {
		control = c;
		commande = com;
		this.vueMenu = vueMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int l_2 = Integer.parseInt(vueMenu.l.getText());
		int c_2 = Integer.parseInt(vueMenu.c.getText());
		vueMenu.getBox().dispose();
		
		//String c, int nbLigne, int nbColonne, String joueur1, String joueur2
		control.configuration(com);
	}
}
