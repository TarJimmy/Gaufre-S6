package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class IADifficile extends IA{
	
	HashMap<String,Boolean> memo;
	
	IADifficile (Gaufre gaufre){
		super(gaufre);
		memo = new HashMap<>();
	}
	
	public boolean estConfigGagnante(String config) {
		return true;
	}
	
	public Point getCoup() {
		HashMap <String, Point> coups = getConfigsPossibles();
		String config = "";
		Iterator it = coups.keySet().iterator();
		ArrayList<Point> coupsGagnants = new ArrayList<>();
		while(it.hasNext()) {
			config = (String)it.next();
			if(memo.containsKey(config) && memo.get(config)) {
				coupsGagnants.add(coups.get(config));
			}
		}
		if (coupsGagnants.size() != 0) {
			Random generator = new Random();
			return coupsGagnants.get(generator.nextInt(coupsGagnants.size()));
		}
		it = coups.keySet().iterator();
		while(it.hasNext()) {
			config = (String)it.next();
			if (estConfigGagnante(config)) {
				coupsGagnants.add(coups.get(config));
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
		IAFacile monIA = new IAFacile(gt);
		res = monIA.getCoup();
		System.out.println(res);
		
	}
}
