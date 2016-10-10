package Server;

import java.net.*;
import java.io.*;
import Server.EventHandler;
import Server.Player;
import Server.BodyPart;
import Server.startMatch;
 
public class Protocol {
	
	EventHandler ble = new EventHandler();
    private static final int WAITING = 0;
    private static final int SENTID = 1;
    private static final int APPLIEDARMOR = 2;
    private static final int READY = 3;
    private static final int FIGHTING_YOUR_TURN = 4;
    private static final int FIGHTING_OTHER_TURN = 4;
    private static final int ANOTHER = 5;
 
    private static final int NUMJOKES = 7;
 
    private int state = WAITING;
    private int currentJoke = 0;
 
    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
                                 "I didn't know you could yodel!",
                                 "Bless you!",
                                 "Is there an owl in here?",
                                 "Is there an echo in here?" };
 
    public String processInput(String theInput) {
    	System.out.println(theInput);
        String theOutput = "OK";
        if (state == WAITING && theInput.equals("NEW_CLIENT")) {
        	ble.newPlayer();
            state = SENTID;
            int tempID = EventHandler.id -1;
            theOutput = "ID: " + (EventHandler.id - 1) + " AP: " + ble.players.get(tempID).getArmorPoints();
        } else if (state == SENTID && theInput.contains("Armor")) {
            System.out.println("Sätter in armor");
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
            
        	String[] temp = theInput.split(" ");
        	ble.readyPlayer(Integer.parseInt(temp[1]));
        	int temp2 = startMatch.fight();
        	if (startMatch.fight() > -1){
        		if(EventHandler.battleRoom[temp2][0] == EventHandler.players.get(Integer.parseInt(temp[1])))
        			state = FIGHTING_YOUR_TURN;
        		else{
        			state = FIGHTING_OTHER_TURN;
        		}
        	}
        	else{
        		state = READY;
        	}
        	
        	

        } else if (state == ANOTHER && theInput == "bajs") {
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTID;
            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        } else if (theInput.equals("PING")) {
        	System.out.println("PING OK");
        	theOutput = "PING OK";
        }
        return theOutput;
    }
}

