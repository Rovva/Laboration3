package Server;

import Server.EventHandler;
import Server.Player;
import Server.BodyPart;
import Server.startMatch;

public class Main {

	public static void main(String[] args) {
		EventHandler ble = new EventHandler();
		startMatch bla = new startMatch(ble);
		ble.newPlayer();
		ble.newPlayer();
		bla.fight();
	}
}
