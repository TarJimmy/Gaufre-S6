package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdaptateurConfiguration implements ActionListener{
	private CollecteurEvenements control;
	private String commande;
	private VueMenu vueMenu;
	private VueFin vueFin;
	
	AdaptateurConfiguration(CollecteurEvenements c, String com, VueMenu vueMenu) {
		control = c;
		commande = com;
		this.vueMenu = vueMenu;
	}
	
	AdaptateurConfiguration(CollecteurEvenements c, String com, VueFin vueFin) {
		control = c;
		commande = com;
		this.vueFin = vueFin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (vueMenu!=null) {
			control.configuration(commande,vueMenu);
		} else {
			control.configuration(commande,vueFin);
		}
	}
}
