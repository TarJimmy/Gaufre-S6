package View;

import java.awt.Component;

import javax.swing.*;

import Controller.ControllerMenu;

public class VueMenu {
	
	private CollecteurEvenements collecteur;
	
	JFrame fenetreMenu;
	JLabel newParty = new JLabel("Nouvelle partie");
	//nom du joueur 1
	JLabel joueur1 = new JLabel("Joueur 1");
	JTextField J1 = new JTextField(20);
	//nom du joueur 2
	JLabel joueur2 = new JLabel("Joueur 2");
	JTextField J2 = new JTextField(20);
	//Taille de la gaufre
	JLabel Taille = new JLabel("Taille de la gauffre :");
	JLabel Ligne = new JLabel("Ligne");
	JTextField l = new JTextField(5);
	JLabel Colonne = new JLabel("Colonne");
	JTextField c = new JTextField(5);
	JButton valider = new JButton("Valider");
	private JButton charger = new JButton("Charger la dernière partie");
	
	private Box box;
	
	public VueMenu(CollecteurEvenements collecteur) {
		this.fenetreMenu = new JFrame("Jeu de la Gaufre - Menu");
		this.collecteur = collecteur;
	    joueur1.setLabelFor(J1);
	    joueur2.setLabelFor(J2);
	    Ligne.setLabelFor(J1);
	    Colonne.setLabelFor(J1);
		valider.addActionListener(new AdaptateurConfiguration(collecteur, "valider", this));
		charger.addActionListener(new AdaptateurConfiguration(collecteur, "charger", this));
		this.box = Box.createVerticalBox();
		box.add(newParty);
		box.add(joueur1);
		box.add(J1);
		box.add(joueur2);
		box.add(J2);
		box.add(Taille);
		box.add(Ligne);
		box.add(l);
		box.add(Colonne);
		box.add(c);
		box.add(valider);
		box.add(charger);
		
		fenetreMenu.add(box);
		fenetreMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreMenu.setSize(500,300);
		fenetreMenu.setVisible(true);
	}
	
	public Box getBox() {
		return box;
	}
	
	public static void main (String[] args){
		VueMenu f1 = new VueMenu(new ControllerMenu());
	    
	}
}
