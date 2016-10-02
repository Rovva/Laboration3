package client;

import java.util.Observable;

public class Module extends Observable{
	
	String currentState;
	int playerID, currentHP, armorPoints;
	
	public Module() {
		currentState = "Unconnected";
	}
	
	String connectToServer(String ipadress) {
		// Store response from server
		String status = "Connected";
		
		return status;
	}
	
	String getState() {
		return currentState;
	}
	
	void setState(String state) {
		this.currentState = state;
	}
	
	void getArmorPoints() {
		
	}
	
	double setArmorPoint(String bodypart) {
		return 0.0;
	}
	
	int dealDamage(String bodypart) {
		return 0;
	}
	
}
