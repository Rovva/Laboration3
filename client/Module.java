package client;

import java.io.IOException;
import java.util.Observable;
import client.Client;

public class Module extends Observable {
	
	String currentState;
	int playerID, opponentID, currentHP, armorPoints, maxArmorPoints;
	int[] bodyparts = new int[6];
	Client client;
	
	public Module() {
		currentState = "Unconnected";
		client = new Client();
		setChanged();
		notifyObservers();
	}
	
	void connectToServer(String ipadress) throws IOException {
		// Store response from server
		//String status = "Connected";
		client.Connect(ipadress);
		int[] temp = client.newPlayer();
		playerID = temp[0];
		maxArmorPoints = temp[1];
		armorPoints = maxArmorPoints;
		opponentID = -1;
		setState("Connecting");
		//return status;
	}
	
	boolean checkConnection() {
		if (client.checkCurrentConnection().equals("PING OK")) {
			return true;
		} else {
			return false;
		}
	}
	
	String getState() {
		return currentState;
	}
	
	void setState(String state) {
		this.currentState = state;
		setChanged();
		notifyObservers();
	}
	
	int getArmorPoints() {
		return armorPoints;
	}
	
	int getMaxArmorPoints() {
		return maxArmorPoints;
	}
	
	void setArmorPoint(String bodypart) {
		this.armorPoints = this.armorPoints - 1;
		if(bodypart.equals("Head")) {
			bodyparts[0] += 1;
		} else if(bodypart.equals("Left Arm")) {
			bodyparts[1] += 1;
		} else if(bodypart.equals("Torso")) {
			bodyparts[2] += 1;
		} else if(bodypart.equals("Right Arm")) {
			bodyparts[3] += 1;
		} else if(bodypart.equals("Left Leg")) {
			bodyparts[4] += 1;
		} else if(bodypart.equals("Right Leg")) {
			bodyparts[5] += 1;
		}
		setChanged();
		notifyObservers();
	}
	
	int getBodyPartArmor(String bodypart) {
		if(bodypart.equals("Head")) {
			return bodyparts[0];
		} else if(bodypart.equals("Left Arm")) {
			return bodyparts[1];
		} else if(bodypart.equals("Torso")) {
			return bodyparts[2];
		} else if(bodypart.equals("Right Arm")) {
			return bodyparts[3];
		} else if(bodypart.equals("Left Leg")) {
			return bodyparts[4];
		} else if(bodypart.equals("Right Leg")) {
			return bodyparts[5];
		} else {
			return 0;
		}
	}
	
	void sendArmorPoints() {
		client.sendArmorToServer(bodyparts, playerID);
	}
	
	void setReady() {
		client.sendReady(playerID);
		setChanged();
		notifyObservers();
	}
	
	boolean startListenToCall() {
		this.opponentID = client.listenToCall(this.playerID);
		return true;
	}
	
	// Kanske byta namn på metoden.
	boolean sendRecheck() {
//		opponentID = client.checkReady(playerID);
//		System.out.println(opponentID);
//		if(opponentID < 0) {
//			return false;
//		} else {
//			return true;
//		}
		
		if(client.checkOpponent(playerID) != -1) {
			this.opponentID = client.checkOpponent(playerID);
			return true;
		} else {
			return false;
		}
	}
	
	void resetArmorPoints() {
		this.armorPoints = maxArmorPoints;
		for(int i = 0; i <= 5; i++) {
			bodyparts[i] = 0;
		}
	}
	// Vi borde skriva om kod så att det aldrig skickas mellanrum på kroppsdelar.
	void dealDamage(String bodypart) {
		// client.sendDamage(bodypart, opponentID);
		if(bodypart.equals("Head")) {
			client.sendDamage("Head", opponentID);
		} else if(bodypart.equals("Left Arm")) {
			client.sendDamage("Left_Arm", opponentID);
		} else if(bodypart.equals("Torso")) {
			client.sendDamage("Torso", opponentID);
		} else if(bodypart.equals("Right Arm")) {
			client.sendDamage("Right_Arm", opponentID);
		} else if(bodypart.equals("Left Leg")) {
			client.sendDamage("Left_Leg", opponentID);
		} else if(bodypart.equals("Right Leg")) {
			client.sendDamage("Right_Leg", opponentID);
		} else {
			System.out.println("Something weird happend with dealDamage");
		}
	}
	
	boolean checkPlayerTurn() {
		if(client.checkTurn() == this.playerID) {
			return true;
		} else {
			return false;
		}
	}
}
