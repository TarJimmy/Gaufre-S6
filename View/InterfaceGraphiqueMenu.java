package View;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.*;

public class InterfaceGraphiqueMenu {
	
	private CollecteurEvenements collecteur;
	
	private JFrame fenetreMenu;
	private JPanel panelButtons;
	private JPanel joueur;
	private JPanel panelGaufre;
	//nom du joueur 1
	private JLabel labelJ1;
	private JTextField textJ1 = new JTextField(10);
	//nom du joueur 2
	private JLabel labelJ2;
	private JTextField textJ2 = new JTextField(10);
	//labelTaille de la panelGaufre
	private JLabel labelTaille;
	private JLabel Ligne;
	private JTextField l;
	private JLabel Colonne;
	private JTextField c;
	private JButton valider;
	private JButton charger;
	
	private ButtonGroup groupJoueur1;
	private ButtonGroup groupJoueur2;
	
	private JRadioButton[] btnRadioJ1;
	private JRadioButton[] btnRadioJ2;
	
	private Box box;
	
	public void close() {
		fenetreMenu.setVisible(false); //you can't see me!
		fenetreMenu.dispose(); //Destroy the JFrame object
	}
	
	public InterfaceGraphiqueMenu(CollecteurEvenements collecteur) {
		this.fenetreMenu = new JFrame("Jeu de la panelGaufre - Menu");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.fenetreMenu.setLocation((screen.width - this.fenetreMenu.getSize().width)/2,(screen.height - this.fenetreMenu.getSize().height)/2);
		this.collecteur = collecteur;
		
		//////
		JPanel panelButtons = new JPanel();
		joueur = new JPanel();	
		panelGaufre = new JPanel();
		labelJ1 = new JLabel("Joueur 1");
		labelJ2 = new JLabel("Joueur 2");
		labelTaille = new JLabel("Taille de la Gaufre :");
		Ligne = new JLabel("Ligne");
		l = new JTextField(5);
		Colonne = new JLabel("Colonne");
		c = new JTextField(5);
		valider = new JButton("Valider");
		charger = new JButton("Charger la dernière partie");
		
		labelJ1.setLabelFor(textJ1);
		labelJ2.setLabelFor(textJ2);
	    Ligne.setLabelFor(l);
	    Colonne.setLabelFor(c);
		valider.addActionListener(new AdaptateurConfiguration(this.collecteur, "valider", this));
		charger.addActionListener(new AdaptateurConfiguration(this.collecteur, "charger", this));
		
		labelJ1.setAlignmentX(Component.LEFT_ALIGNMENT);
		textJ1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		labelJ2.setAlignmentX(Component.LEFT_ALIGNMENT);
		textJ2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		labelTaille.setAlignmentX(Component.CENTER_ALIGNMENT);
		Ligne.setAlignmentX(Component.LEFT_ALIGNMENT);
		l.setAlignmentX(Component.RIGHT_ALIGNMENT);
		Colonne.setAlignmentX(Component.LEFT_ALIGNMENT);
		c.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		groupJoueur1 = new ButtonGroup();
		btnRadioJ1 = new JRadioButton[3];
		btnRadioJ1[0] = new JRadioButton("Humain", true);
		btnRadioJ1[1] = new JRadioButton("IA Facile", false);
		btnRadioJ1[2] = new JRadioButton("IA Difficile", false);
		
		groupJoueur2 = new ButtonGroup();
		btnRadioJ2 = new JRadioButton[3];
		btnRadioJ2[0] = new JRadioButton("Humain", true);
		btnRadioJ2[1] = new JRadioButton("IA Facile", false);
		btnRadioJ2[2] = new JRadioButton("IA Difficile", false);
		JPanel panelJ1 = new JPanel(new GridLayout(1, 5));
		JPanel panelJ2 = new JPanel(new GridLayout(1, 5));
		
		this.box = Box.createVerticalBox();
		box.add(panelJ1);
		box.add(panelJ2);
		box.add(labelTaille);
		box.add(panelGaufre);
		box.add(panelButtons);
		
		panelJ1.add(labelJ1);
		panelJ1.add(textJ1);
		panelJ1.add(btnRadioJ1[0]);
		panelJ1.add(btnRadioJ1[1]);
		panelJ1.add(btnRadioJ1[2]);
		
		panelJ2.add(labelJ2);
		panelJ2.add(textJ2);
		panelJ2.add(btnRadioJ2[0]);
		panelJ2.add(btnRadioJ2[1]);
		panelJ2.add(btnRadioJ2[2]);
		
		panelGaufre.add(Ligne);
		panelGaufre.add(l);
		panelGaufre.add(Colonne);
		panelGaufre.add(c);
		
		panelButtons.add(valider);
		panelButtons.add(charger);
		

		
		groupJoueur1.add(btnRadioJ1[0]);
		groupJoueur1.add(btnRadioJ1[1]);
		groupJoueur1.add(btnRadioJ1[2]);

		groupJoueur2.add(btnRadioJ2[0]);
		groupJoueur2.add(btnRadioJ2[1]);
		groupJoueur2.add(btnRadioJ2[2]);
		
		fenetreMenu.add(box);
		fenetreMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreMenu.setSize(750, 250);
		fenetreMenu.setVisible(true);
		System.out.println(getJoueur1());
	}
	
	public Box getBox() {
		return box;
	}
	
	public String getJoueur1() {
		return textJ1.getText();
	}
	
	public String getJoueur2() {
		return textJ2.getText();
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
	
	public JRadioButton[] getRadioBtnJ1() {
		return this.btnRadioJ1;
	}
	
	public JRadioButton[] getRadioBtnJ2() {
		return this.btnRadioJ2;
	}
}
