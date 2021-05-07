package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import Model.Gaufre;


public class VueGaufre extends JComponent {

	Graphics2D drawable;
	Gaufre g;
	private int largeurCase, hauteurCase;
	VueGaufre(Gaufre g) {
		this.g = g;
	}
	
	
	protected void tracer(Color c,int x, int y, int l, int h) {
		if(!c.equals(Color.WHITE)) {
			drawable.setColor(c);
			drawable.fillRect(x, y, l, h);
			drawable.setColor(new Color(168,93,50));
			drawable.drawRect(x, y, l, h);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		drawable = (Graphics2D) g;
		drawable.setStroke(new BasicStroke(6));
		
		int largeur  = getWidth();
		int hauteur = getHeight();
		// On efface tout
		drawable.clearRect(0, 0, largeur, hauteur);
		tracerNiveau();
	}
	
	public void misAjour() { 
		repaint();
	}
	
	public Gaufre getGaufre() {
		return g;
	}
	
	void tracerNiveau() {
		
		int[][] gaufreArray = g.getGaufre();
		
		largeurCase = getWidth() / g.getNbColonne();
		hauteurCase = getHeight() / g.getNbLigne();
		// On prend le minimum
		largeurCase = Math.min(largeurCase, hauteurCase);		
		
		for (int ligne = 0; ligne < g.getNbLigne(); ligne++) {
			for (int colonne = 0; colonne < g.getNbColonne(); colonne++) {
				int x = colonne * largeurCase;
				int y = ligne * largeurCase;
								
				if (gaufreArray[ligne][colonne] == Gaufre.POISON) {
					tracer(Color.GREEN,x,y,largeurCase,largeurCase);
				} else if (gaufreArray[ligne][colonne] == Gaufre.PRESENT) {
					tracer(Color.ORANGE,x,y,largeurCase,largeurCase);
				} else {
					tracer(Color.WHITE,x,y,largeurCase,largeurCase);
				}
			}
		}

	}

	public int getLargeurCase() {
		return largeurCase;
	}

	public int getHauteurCase() {
		return hauteurCase;
	}
}
