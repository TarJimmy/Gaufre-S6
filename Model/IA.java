package Model;

import java.awt.Point;
import java.util.HashMap;

public abstract class IA {
	
	protected Gaufre gaufre;
	protected int[][] currentConfig;
	
	public IA (Gaufre gaufre) {
		this.gaufre = gaufre;
	}
	
	public abstract Point getCoup();
	
	public String setString(String config, Gaufre g) {
		//return une string representant la configuration de la gaufre donnée en parametre
		config = "";
		for (int k = 0; k < g.getNbLigne(); k++) {
			for (int l = 0; l < g.getNbColonne(); l++) {
				config = config.concat(Integer.toString(g.getGaufre()[k][l]));
			}
		}
		return config;
	}
	
	public int contenuCase(int i, int j, String config) {
		int a=Integer.parseInt(String.valueOf(config.charAt(gaufre.getNbColonne()*i+j)));
		return a;
	}
	
	public void stringToGaufre(String config, Gaufre g) {
		//config fait la dimension du tableau de la gaufre, cette fonction remplit le tableau de la gaufre conformement a config
		int[][] newGaufre = new int[g.getNbLigne()][g.getNbColonne()];
		for(int i = 0; i < g.getNbLigne(); i++) {
			for(int j = 0; j < g.getNbColonne(); j++) {
				newGaufre[i][j] = contenuCase(i, j, config);
			}
		}
		g.setGaufre(newGaufre);
	}
	
	
	
	public HashMap<String,Point> getConfigsPossibles(String config){
		//renvoie les config atteignables en un coup
		Gaufre g = new Gaufre(gaufre);
		stringToGaufre(config, g);
		HashMap<String, Point> res = new HashMap<>();
		//les coups perdants consistent a manger le poison. L'IA ne return ces coups qu'en dernier recours
		HashMap<String, Point> coupsPerdants = new HashMap<>();
		String newConfig = "";

		int i = 0;
		int j = 0;
		int[][] jeuGaufre = g.getGaufre();
		int h = g.getNbLigne();
		int l = g.getNbColonne();
		while(i < h && jeuGaufre[i][j] != 0) {
			while(j < l && jeuGaufre[i][j] != 0) {
				if(jeuGaufre[i][j] != 2) {
					g.mange(j, i);
					newConfig = setString(newConfig,g);
					res.put(newConfig,new Point(i,j));
					stringToGaufre(config, g);
				}else {
					newConfig = setString(newConfig,g);
					//on fait en sorte que la configuration renvoyée soit gagnante, cela signifie que ce coup est perdant
					newConfig = newConfig.substring(0, gaufre.getNbColonne()*0+1) + '0'+ newConfig.substring(gaufre.getNbColonne()*0+1 + 1);
					newConfig = newConfig.substring(0, gaufre.getNbColonne()*1+0) + '1'+ newConfig.substring(gaufre.getNbColonne()*1+0 + 1);
					coupsPerdants.put(newConfig,new Point(i,j));
				}
				j++;
			}
			j = 0;
			i++;
		}
		if (res.size() != 0) {
			return res;	
		}else {
			return coupsPerdants;
		}
		
	}
}
