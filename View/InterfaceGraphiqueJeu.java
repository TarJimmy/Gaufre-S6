package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Controller.ControllerFin;
import Model.Gaufre;
import Patterns.Observateur;

public class InterfaceGraphiqueJeu implements Runnable, Observateur {
	public static final int SIZEBLOCK = 75;
	
	private Gaufre gaufre;
	private JFrame frame;
	private VueGaufre vueGaufre;
	CollecteurEvenements controle;
	private JLabel whoPlaying;

	
	private InterfaceGraphiqueJeu(Gaufre gaufre, CollecteurEvenements controle) {
		this.gaufre = gaufre;
		this.controle = controle;
	}
	
	public void run() {
		frame = new JFrame("Gaufre");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screen.width - frame.getSize().width)/2,(screen.height - frame.getSize().height)/2);
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
		panelButton.add(createButtonCommande("Charger Partie", "chargerPartie"));
		panelButton.add(createButtonCommande("Sauvegarder Partie", "sauvegarderPartie"));
		panelButton.add(createButtonCommande("Annuler coup", "annulerCoup"));
		
		whoPlaying = new JLabel("Tour de " + (gaufre.getEstTourDeJoueur1() ? gaufre.getJoueur2() : gaufre.getJoueur1()) + ".", SwingConstants.CENTER);
        whoPlaying.setOpaque(true);
        whoPlaying.setBackground(Color.WHITE);
        frame.add(whoPlaying, BorderLayout.NORTH);
        
		gaufre.ajouteObservateur(this);
		frame.setVisible(true);
	}
	
	public static void demarrer(Gaufre g, CollecteurEvenements control) {
		SwingUtilities.invokeLater(new InterfaceGraphiqueJeu(g, control));
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
		Boolean hasWinner = gaufre.getHasWinner();
		if (hasWinner) {
			frame.dispose();
			new InterfaceGraphiqueFin(new ControllerFin(), hasWinner, gaufre.getJoueur1(), gaufre.getJoueur2());
		} else {
			whoPlaying.setText("Tour de : " + (gaufre.getEstTourDeJoueur1() ? gaufre.getJoueur2() : gaufre.getJoueur1()));
			vueGaufre.misAjour();
		}
	}
	
//	@Override
//	public void fin(Boolean b, String J1, String J2) {
//		frame.dispose();
//		new VueFin(new ControllerFin(), b, J1, J2);
//		
//	}

}
