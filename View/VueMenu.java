package View;

import java.awt.Component;

import javax.swing.*;

public class VueMenu {
	
	private CollecteurEvenements collecteur;
	
	private JFrame fenetreMenu;
	private JPanel Button = new JPanel();
	private JPanel joueur = new JPanel();
	private JPanel gaufre = new JPanel();
	private JLabel newParty = new JLabel("Nouvelle partie");
	//nom du joueur 1
	private JLabel joueur1 = new JLabel("Joueur 1");
	private JTextField J1 = new JTextField(10);
	//nom du joueur 2
	private JLabel joueur2 = new JLabel("Joueur 2");
	private JTextField J2 = new JTextField(10);
	//Taille de la gaufre
	private JLabel Taille = new JLabel("Taille de la gaufre :");
	private JLabel Ligne = new JLabel("Ligne");
	private JTextField l = new JTextField(5);
	private JLabel Colonne = new JLabel("Colonne");
	private JTextField c = new JTextField(5);
	private JButton valider = new JButton("Valider");
	private JButton charger = new JButton("Charger la dernière partie");
	
	private Box box;
	
	public VueMenu(CollecteurEvenements collecteur) {
		this.fenetreMenu = new JFrame("Jeu de la Gaufre - Menu");
		this.collecteur = collecteur;
	    joueur1.setLabelFor(J1);
	    joueur2.setLabelFor(J2);
	    Ligne.setLabelFor(l);
	    Colonne.setLabelFor(c);
		valider.addActionListener(new AdaptateurConfiguration(this.collecteur, "valider", this));
		charger.addActionListener(new AdaptateurConfiguration(this.collecteur, "charger", this));
		
		newParty.setAlignmentX(Component.CENTER_ALIGNMENT);
		joueur1.setAlignmentX(Component.LEFT_ALIGNMENT);
		J1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		joueur2.setAlignmentX(Component.LEFT_ALIGNMENT);
		J2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		Taille.setAlignmentX(Component.CENTER_ALIGNMENT);
		Ligne.setAlignmentX(Component.LEFT_ALIGNMENT);
		l.setAlignmentX(Component.RIGHT_ALIGNMENT);
		Colonne.setAlignmentX(Component.LEFT_ALIGNMENT);
		c.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		this.box = Box.createVerticalBox();
		box.add(newParty);
		joueur.add(joueur1);
		joueur.add(J1);
		joueur.add(joueur2);
		joueur.add(J2);
		box.add(joueur);
		box.add(Taille);
		gaufre.add(Ligne);
		gaufre.add(l);
		gaufre.add(Colonne);
		gaufre.add(c);
		box.add(gaufre);
		Button.add(valider);
		Button.add(charger);
		box.add(Button);
		
		fenetreMenu.add(box);
		fenetreMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreMenu.setSize(500,175);
		fenetreMenu.setVisible(true);
	}
	
	public Box getBox() {
		return box;
	}
	
	public String getJoueur1() {
		return J1.getText();
	}
	
	public String getJoueur2() {
		return J2.getText();
	}
	
	public String getLigne() {
		return l.getText();
	}
	public String getColonne() {
		return c.getText();
	}

	public JFrame getFenetreMenu() {
		return fenetreMenu;
	}
}
