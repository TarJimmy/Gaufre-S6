package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class IA {
	
	protected Gaufre gaufre;
	protected int[][] currentConfig;
	
	IA (Gaufre gaufre) {
		this.gaufre = gaufre;
	}
	
	public String setString(String config, Gaufre g) {
		config = "";
		for (int k = 0; k < g.getNbLigne(); k++) {
			for (int l = 0; l < g.getNbColonne(); l++) {
				config = config.concat(Integer.toString(g.getGaufre()[k][l]));
			}
		}
		return config;
	}
	
	public HashMap<String,Point> getConfigsPossibles(){
		Gaufre g = new Gaufre(gaufre);
		HashMap<String,Point> res = new HashMap();
		String config = "";

		int i = 0;
		int j = 0;
		int[][] jeuGaufre = g.getGaufre();
		int h = g.getNbLigne();
		int l = g.getNbColonne();
		while(i < h && jeuGaufre[i][j] != 0) {
			while(j < l && jeuGaufre[i][j] != 0) {
				g.mange(i, j);
				config = setString(config,g);
				res.put(config,new Point(i,j));
				g.setGaufre(gaufre.copieGaufre());
				j++;
			}
			j = 0;
			i++;
		}
		return res;	
	}
}
