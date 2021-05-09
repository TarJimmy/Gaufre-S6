package Model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Random;

public class IAFacile extends IA {
	
	public IAFacile (Gaufre gaufre) {
		super(gaufre);
	}
	
	@Override
	public Point getCoup() {
		String config = "";
		config = setString(config, gaufre);
		HashMap <String, Point> coups = getConfigsPossibles(config);
		Object[] points = coups.values().toArray();
		Random generator = new Random();
		Point randomPoint = (Point)points[generator.nextInt(points.length)];

		return randomPoint;
	}
	
	public static void main(String[] args) {
		Gaufre gt = new Gaufre("j1","j2",3,3);
		IAFacile monIA = new IAFacile(gt);
		Point p = new Point(0,0);
		p = monIA.getCoup();
		System.out.println(p);
		
	}
}
