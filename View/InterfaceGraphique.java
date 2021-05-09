package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controller.ControllerFin;
import Model.Gaufre;
import Patterns.Observateur;

public class InterfaceGraphique implements Runnable, Observateur {
	public static final int SIZEBLOCK = 75;
	
	private Gaufre gaufre;
	private JFrame frame;
	private VueGaufre vueGaufre;
	private JLabel joueur;
	CollecteurEvenements controle;
	
	private InterfaceGraphique(Gaufre gaufre, CollecteurEvenements controle) {
		this.gaufre = gaufre;
		this.controle = controle;
	}
	
	public void run() {
		frame = new JFrame("Gaufre - Jeu");
		JPanel panelJoueur = new JPanel();
		joueur = createLabel("");
		tourJoueur();
		panelJoueur.add(joueur);
		frame.add(panelJoueur, BorderLayout.NORTH);
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
		panelButton.add(createButtonCommande("Quitter Jeu", "quitterJeu"));
		panelButton.add(createButtonCommande("Recommencer", "nouvellePartie"));
		panelButton.add(createButtonCommande("Sauvegarder Partie", "sauvegarderPartie"));
		panelButton.add(createButtonCommande("Annuler Coup", "annulerCoup"));
		
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
	
	private JLabel createLabel(String s) {
		JLabel lab = new JLabel(s);
		lab.setAlignmentX(Component.CENTER_ALIGNMENT);
		return lab;
	}
	
	private void tourJoueur() {
		if (gaufre.getEstTourDeJoueur1()) {
			joueur.setText("A "+gaufre.getJoueur1()+" de jouer !");
		} else {
			joueur.setText("A "+gaufre.getJoueur2()+" de jouer !");
		}
	}

	@Override
	public void miseAJour() {
		// TODO Auto-generated method stub
		tourJoueur();
		vueGaufre.misAjour();
	}

	@Override
	public void fin(Boolean b, String J1, String J2) {
		frame.dispose();
		new VueFin(new ControllerFin(), b, J1, J2);
		
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

			@Override
			public boolean configuration(String c, VueMenu vueMenu) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean configuration(String c, VueFin vueFin) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

}
