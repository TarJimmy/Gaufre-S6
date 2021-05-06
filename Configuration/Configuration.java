package Configuration;

import java.io.File;

public class Configuration {
	public final String dirSauv = "../Sauvegarde"; 
	
	private static Configuration instance = null;
	
	private Configuration() {
		File dossier = new File("../Sauvegarde"); 
	    boolean res = dossier.mkdir();
	    
	    if (res) {
	      System.out.println("Le dossier Sauvegarde a été créé.");
	    }
	}
	
	public static Configuration instance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}
}
