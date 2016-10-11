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
	
	boolean sendRecheck() {
		opponentID = client.checkReady(playerID);
		if(opponentID < 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	void resetArmorPoints() {
		this.armorPoints = 10;
		for(int i = 0; i <= 5; i++) {
			bodyparts[i] = 0;
		}
	}
	
	int dealDamage(String bodypart) {
		return 0;
	}
	
}
