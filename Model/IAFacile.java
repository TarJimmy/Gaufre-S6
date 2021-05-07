package Model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class IAFacile extends IA {
	
	IAFacile (Gaufre gaufre) {
		super(gaufre);
	}
	
	public Point getCoup() {
		HashMap <String, Point> config = getConfigsPossibles();
		Object[] points = config.values().toArray();
		Random generator = new Random();
		Point randomPoint = (Point)points[generator.nextInt(points.length)];

		return randomPoint;
	}
	
	public static void main(String[] args) {
		Point res= new Point(0,0);
		Gaufre gt = new Gaufre("j1","j2",3,3);
		IAFacile monIA = new IAFacile(gt);
		res = monIA.getCoup();
		System.out.println(res);
		
	}
}
