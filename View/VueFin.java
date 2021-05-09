package View;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueFin {
	private CollecteurEvenements collecteurFin;
	
	private JFrame fenetreFin;
	private JPanel panelButton;
	private JLabel Gagnant;
	private JButton Menu = new JButton("Menu");
	private JButton Quitter = new JButton("Quitter");
	
	private Box box;
	
	public VueFin(CollecteurEvenements collecteurFin, Boolean B, String J1, String J2) {
		this.fenetreFin = new JFrame("Jeu de la Gaufre - Fin");
		this.collecteurFin = collecteurFin;
		this.Gagnant = new JLabel((B ? J2 : J1) + " à gagner !");
		this.Gagnant.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.panelButton = new JPanel();
		
		this.Menu.addActionListener(new AdaptateurConfiguration(this.collecteurFin, "menu",this));
		this.Quitter.addActionListener(new AdaptateurCommande(this.collecteurFin, "quitter"));
		this.Menu.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.Quitter.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.box = Box.createVerticalBox();
		box.add(Gagnant);
		panelButton.add(Menu);
		panelButton.add(Quitter);
		box.add(panelButton);
		
		fenetreFin.add(box);
		fenetreFin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreFin.setSize(300,100);
		fenetreFin.setVisible(true);
	}
	
	public JFrame getFenetreFin() {
		return fenetreFin;
	}
}
