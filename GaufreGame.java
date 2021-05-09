import Controller.ControllerJeu;
import Controller.ControllerMenu;
import View.InterfaceGraphiqueMenu;

public class GaufreGame {
	
	public static void main(String[] args ) {
		new InterfaceGraphiqueMenu(new ControllerMenu());
	}
}
