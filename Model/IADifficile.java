package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class IADifficile extends IA{
	
	HashMap<String,Boolean> memo;
	
	public IADifficile (Gaufre gaufre){
		super(gaufre);
		memo = new HashMap<>();
	}
	
	public HashMap<String,Boolean> getMemo(){
		return this.memo;
	}
	
	public boolean estConfigGagnante(String config) {
		//return true si le joueur a qui c'est le tour est sur de gagner en jouant bien
		//memoisation
		if(memo.containsKey(config)) {
			return memo.get(config);
		}
		//cas de base
		if(contenuCase(0,1,config) == 0 && contenuCase(1,0,config) == 0) {
			memo.put(config, false);
			return false;
		}
		if(contenuCase(0,1,config) + contenuCase(1,0,config) == 1) {
			memo.put(config, true);
			return true;
		}
		
		//recup toutes les config suivantes et si y'en a une perdante on renvoie true
		boolean res = false;
		HashMap <String, Point> coups = getConfigsPossibles(config);
		Iterator<String> it = coups.keySet().iterator();
		while(it.hasNext() && res == false) {
			String newConfig = (String)it.next();
			memo.put(newConfig, estConfigGagnante(newConfig));
			if(memo.get(newConfig) == false) {
				res = true;
			}
		}
		//si on en a pas trouvé return false
		return res;
	}
	
	@Override
	public Point getCoup() {
		//creation d'une configuration representant l'etat actuel de la gaufre
		String config = "";
		config = setString(config,this.gaufre);
		
		//liste des coups jouables
		HashMap <String, Point> coups = getConfigsPossibles(config);
		String newConfig = "";
		Iterator<String> it = coups.keySet().iterator();
		//ArrayList qu'on va remplir avec les coups gagnants
		ArrayList<Point> coupsGagnants = new ArrayList<>();
		//--------------
		//cette partie n'est pas necessaire mais permet de gagner du temps.
		//en effet on va choisir parmis les coups gagnants deja calculés, et 
		//chercher de nouveaux coups gagnants que si on n'en a aucun en reserve
		while(it.hasNext()) {
			newConfig = (String)it.next();
			//si le coup donne une config perdante c'est un coup gagnant
			if(memo.containsKey(newConfig) && memo.get(newConfig) == false) {
				coupsGagnants.add(coups.get(newConfig));
			}
		}
		if (coupsGagnants.size() != 0) {
			Random generator = new Random();
			return coupsGagnants.get(generator.nextInt(coupsGagnants.size()));
		}
		//------------------------
		it = coups.keySet().iterator();
		while(it.hasNext()) {
			newConfig = (String)it.next();
			//si la nouvelle config est perdante c'est un coup gagnant
			if (!estConfigGagnante(newConfig)) {
				coupsGagnants.add(coups.get(newConfig));
			}
		}
		if (coupsGagnants.size() != 0) {
			Random generator = new Random();
			return coupsGagnants.get(generator.nextInt(coupsGagnants.size()));
		}else {
			Object[] points = coups.values().toArray();
			Random generator = new Random();
			Point randomPoint = (Point)points[generator.nextInt(points.length)];
			return randomPoint;
		}
	}
	
	public static void main(String[] args) {
		Point res= new Point(0,0);
		Gaufre gt = new Gaufre("j1","j2",3,3);
		IADifficile monIA = new IADifficile(gt);
//		res = monIA.getCoup();
//		System.out.println(res);
	//	System.out.println(monIA.getMemo());
		//System.out.println(monIA.getConfigsPossibles("211100000"));
	}
}
