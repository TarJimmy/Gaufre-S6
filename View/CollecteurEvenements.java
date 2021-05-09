 package View;

import Model.Gaufre;
import Model.IA;

public interface CollecteurEvenements {
	boolean commande(String c);
	boolean clickSouris(String s, int l, int c);
	boolean configuration(String c, InterfaceGraphiqueMenu vueMenu, Gaufre maGaufre, IA IAJ1, IA IAJ2);
	boolean configuration(String c, InterfaceGraphiqueFin vueFin);
}

