package server;

import server.BodyPart;
import server.EventHandler;
import server.Player;
import server.Server;
import server.startMatch;

public class Main {

	public static void main(String[] args) {
		EventHandler ble = new EventHandler();
		startMatch bla = new startMatch(ble);
		ble.newPlayer();
		ble.newPlayer();
		ble.newPlayer();
		ble.newPlayer();
		System.out.println(ble.battleRoom[0][0]);
		bla.fight();
		bla.fight();
		ble.battleRoom[0][0].DealDamage(ble.battleRoom[0][0].getHead());
		ble.battleRoom[0][0].DealDamage(ble.battleRoom[0][0].getTorso());
		ble.battleRoom[0][0].getHP();
		ble.battleRoom[0][1].getHP();
		ble.battleRoom[0][0].getHP(ble.battleRoom[0][0].getHead());
		ble.battleRoom[0][0].getHP(ble.battleRoom[0][0].getTorso());
		ble.battleRoom[0][1].getHP(ble.battleRoom[0][1].getHead());
		ble.battleRoom[0][1].getHP(ble.battleRoom[0][1].getTorso());
		ble.battleRoom[0][0].ApplyArmor(ble.battleRoom[0][0].getHead(), 10);
		ble.battleRoom[0][1].ApplyArmor(ble.battleRoom[0][0].getHead(), 10);
		ble.battleRoom[0][0].ApplyArmor(ble.battleRoom[0][0].getHead(), 1);
	}
}
