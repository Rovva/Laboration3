package server;

import java.net.*;

import server.BodyPart;
import server.EventHandler;
import server.Player;
import server.StartMatch;

import java.io.*;

// M�ste fixa hantering av flera spelare och udda antal.
// t.ex. att tv� spelare ansluter och de matchas upp, medan en ny spelare
// ansluter men m�ste v�nta p� en fj�rde spelare f�r att kunna spela. Samt
// Ifall en spelare av de f�rsta spelare l�mnar, s� matchas spelare 1 och 2.

public class Protocol {
	
	EventHandler ble = new EventHandler();
    private static final int WAITING = 0;
    private static final int SENTID = 1;
    private static final int APPLIEDARMOR = 2;
    private static final int READY = 3;
    private static final int FIGHTING_YOUR_TURN = 4;
    private static final int FIGHTING_OTHER_TURN = 5;
    private static final int ANOTHER = 6;
 
    private int state = WAITING;

    public String processInput(String theInput) {
    	System.out.println(theInput);
        String theOutput = "OK";
        if (state == WAITING && theInput.equals("NEW_CLIENT")) {
        	ble.newPlayer();
            state = SENTID;
            int tempID = EventHandler.id -1;
            theOutput = "ID: " + (EventHandler.id - 1) + " AP: " + ble.players.get(tempID).getArmorPoints();
        } else if (state == SENTID && theInput.contains("Armor ")) {
            System.out.println("S�tter in armor");
        	String[] temp = theInput.split(" ");
        	ble.players.get(Integer.parseInt(temp[1])).ApplyArmor(temp[2], Integer.parseInt(temp[3])); // Head
        	System.out.println("Storing Head: " + temp[2] + temp[3]);
        	ble.players.get(Integer.parseInt(temp[1])).ApplyArmor(temp[4], Integer.parseInt(temp[5])); // Left arm
        	ble.players.get(Integer.parseInt(temp[1])).ApplyArmor(temp[6], Integer.parseInt(temp[7])); // Torso
        	ble.players.get(Integer.parseInt(temp[1])).ApplyArmor(temp[8], Integer.parseInt(temp[9])); // Right arm
        	ble.players.get(Integer.parseInt(temp[1])).ApplyArmor(temp[10], Integer.parseInt(temp[11])); // Left leg
        	ble.players.get(Integer.parseInt(temp[1])).ApplyArmor(temp[12], Integer.parseInt(temp[13])); // Right leg
        	state = APPLIEDARMOR;
        } else if (state == APPLIEDARMOR && theInput.contains("Ready")) {
            System.out.println("Player ready");
            System.out.println(theInput);
        	String[] temp = theInput.split(" ");
        	ble.readyPlayer(Integer.parseInt(temp[1]));
        	int temp2 = StartMatch.fight();
        	if (temp2 > -1) {
        		if(EventHandler.battleRoom[temp2][0] == EventHandler.players.get(Integer.parseInt(temp[1]))) {
        			state = FIGHTING_YOUR_TURN;
        			System.out.println("Your turn.");
        			String temp3 = "Battle begun " + Integer.parseInt(temp[1]) + " vs " + EventHandler.battleRoom[temp2][1].getID();
        			theOutput = temp3;
        		} else {
        			state = FIGHTING_OTHER_TURN;
        			System.out.println("His turn.");
        			String temp3 = "Battle begun " + Integer.parseInt(temp[1]) + " vs " + EventHandler.battleRoom[temp2][0].getID();
        			theOutput = temp3;
        		}
        	} else {
        		state = READY;
        	}
        // Denna if sats �r ersatt l�ngre ned med Opponents. Kanske plocka bort denna?
        } else if (state == READY && theInput.contains("Recheck")) {
        	System.out.println("Checking for other players");
        	String[] temp = theInput.split(" ");
        	System.out.println("temp1: " + temp[1]);
        	System.out.println("temp0: " + temp[0]);
        	for(int i = 0; i < 10; i++) {
        		if(EventHandler.battleRoom[i][0] != null) {
        			if(EventHandler.battleRoom[i][0].getID() == (Integer.parseInt(temp[1]))) {
        				if(EventHandler.battleRoom[i][1] != null) {
                			String temp2 = "Room " + i + " Opponent " + EventHandler.battleRoom[i][1].getID();
                			System.out.println(temp2);
                			theOutput = temp2;
            			} else {
            				theOutput = "Keep waiting";
            			}
        			}
        		} else if(EventHandler.battleRoom[i][1] != null) {
        			if(EventHandler.battleRoom[i][1].getID() == (Integer.parseInt(temp[1]))) {
            			if(EventHandler.battleRoom[i][0] != null) {
                			String temp2 = "Room " + i + " Opponent " + EventHandler.battleRoom[i][0].getID();
                			System.out.println(temp2);
                			theOutput = temp2;
            			} else {
            				theOutput = "Keep waiting";
            			}
        			}
        		}
        	}
        // Denna funktion �r inte helt klar, vi m�ste ha en loop h�r s� det st�djer fler �n 2 spelare.
        } else if (state == READY && theInput.contains("DMG")) {
        	String temp[] = theInput.split(" ");
        	//for(int i = 0; i < EventHandler.battleRoom.length; i++) {
        	//	 System.out.println("4: " + Integer.parseInt(temp[4]));
        	//	 System.out.println("4: " + temp[4]);
        		if(EventHandler.battleRoom[0][0].getID() == Integer.parseInt(temp[4])) {
        			EventHandler.battleRoom[0][0].DealDamage(temp[2]);
        			EventHandler.battleRoom[0][0].setTurn();
        		} else if(EventHandler.battleRoom[0][1].getID() == Integer.parseInt(temp[4])) {
        			EventHandler.battleRoom[0][1].DealDamage(temp[2]);
        			EventHandler.battleRoom[0][1].setTurn();
        		}
        	//}
        } else if (theInput.equals("PING")) {
        	System.out.println("PING OK");
        	theOutput = "PING OK";
        // Denna �r inte heller helt klar, beh�vs en loop s� det finns st�d f�r fler �n 2 spelare.
        } else if(theInput.contains("Opponents")) {
        	System.out.println(theInput);
        	String[] temp = theInput.split(" ");
        	System.out.println("Checking opponents");
        	System.out.println("temp1: " + temp[1]);
        	System.out.println("temp0: " + temp[0]);
        	if(EventHandler.battleRoom[0][0] != null && EventHandler.battleRoom[0][1] != null) {
        		if(EventHandler.battleRoom[0][0].getID() == Integer.parseInt(temp[1])) {
	        		theOutput = EventHandler.battleRoom[0][0].getID() + " against " + EventHandler.battleRoom[0][1].getID();
	        	} else if(EventHandler.battleRoom[0][1].getID() == Integer.parseInt(temp[1])) {
	        		theOutput = EventHandler.battleRoom[0][0].getID() + " against " + EventHandler.battleRoom[0][1].getID();
	        	}
        	} else {
        		System.out.println("Need to wait for another player");
        		theOutput = "wait";
        	}
        } else if(theInput.contains("player_turn")) {
        	System.out.println("Checking whos turn it is.");
        }
        return theOutput;
    }
}

