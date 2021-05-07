package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Model.Gaufre;
import Patterns.Observateur;

public class InterfaceGraphique implements Runnable, Observateur {
	public static final int SIZEBLOCK = 75;
	
	private Gaufre gaufre;
	private JFrame frame;
	private VueGaufre vueGaufre;
	CollecteurEvenements controle;
	
	private InterfaceGraphique(Gaufre gaufre, CollecteurEvenements controle) {
		this.gaufre = gaufre;
		this.controle = controle;
	}
	
	public void run() {
		frame = new JFrame("Gaufre");
		int width = SIZEBLOCK * gaufre.getNbColonne();
		int height = SIZEBLOCK * gaufre.getNbLigne() + 75; // 150 c'est pour le panel
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.vueGaufre = new VueGaufre(gaufre);
		frame.add(vueGaufre);
		vueGaufre.misAjour(); 
		vueGaufre.addMouseListener(new AdapteurMouse(vueGaufre, controle, "clickCase"));
		JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.WHITE);
		frame.add(panelButton, BorderLayout.SOUTH);
		panelButton.add(createButtonCommande("Nouvelle Partie", "nouvellePartie"));
		panelButton.add(createButtonCommande("Charger Partie", "chargerPartie"));
		panelButton.add(createButtonCommande("Sauvegarder Partie", "sauvegarderPartie"));
		panelButton.add(createButtonCommande("Annuler coup", "annulerCoup"));
		
		gaufre.ajouteObservateur(this);
		frame.setVisible(true);
	}
	
	public static void demarrer(Gaufre g, CollecteurEvenements control) {
		SwingUtilities.invokeLater(new InterfaceGraphique(g, control));
	}

	private JButton createButtonCommande(String s, String c) {
		JButton but = new JButton(s);
		but.addActionListener(new AdaptateurCommande(controle, c));
		but.setAlignmentX(Component.CENTER_ALIGNMENT);
		but.setSize(150, 50);
		but.setFocusable(false);
		return but;
	}

	@Override
	public void miseAJour() {
		// TODO Auto-generated method stub
		vueGaufre.misAjour();
	}
	
	public static void main(String[] args) {
		Gaufre g = new Gaufre(null, null, 8, 5);
		InterfaceGraphique.demarrer(g, new CollecteurEvenements() {
			
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
		});
	}

}
