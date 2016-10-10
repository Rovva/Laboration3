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
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
 
    private static final int NUMJOKES = 5;
 
    private int state = WAITING;
    private int currentJoke = 0;
 
    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
                                 "I didn't know you could yodel!",
                                 "Bless you!",
                                 "Is there an owl in here?",
                                 "Is there an echo in here?" };
 
    public String processInput(String theInput) {
        String theOutput = "bajs";
        if (state == WAITING && theInput.equals("NEW_CLIENT")) {
        	ble.newPlayer();
            state = SENTID;
            theOutput = (String.valueOf((EventHandler.id - 1)));
        } else if (state == SENTID && theInput == "bajs") {
            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                state = SENTCLUE;
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! " +
                "Try again. Knock! Knock!";
            }
        } else if (state == SENTCLUE && theInput == "bajs") {
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                state = ANOTHER;
            } else {
                theOutput = "You're supposed to say \"" + 
                clues[currentJoke] + 
                " who?\"" + 
                "! Try again. Knock! Knock!";
                state = SENTID;
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
        }
        return theOutput;
    }
}

