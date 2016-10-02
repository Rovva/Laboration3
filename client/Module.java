package client;

import java.util.Observable;

public class Module extends Observable{
	
	String currentState;
	int playerID, currentHP, armorPoints, maxArmorPoints;
	
	public Module() {
		currentState = "Unconnected";
		armorPoints = 10;
		maxArmorPoints = 10;
		setChanged();
		notifyObservers();
	}
	
	void connectToServer(String ipadress) {
		// Store response from server
		//String status = "Connected";
		setState("Connecting");
		//return status;
	}
	
	boolean checkConnection() {
		return true;
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
		setChanged();
		notifyObservers();
	}
	
	int dealDamage(String bodypart) {
		return 0;
	}
	
}
