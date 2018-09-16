package server;

import java.util.ArrayList;

public class EventHandler {

	public static int id = 0;
	
	public static ArrayList<Player> queue = new ArrayList<Player>();
	
	public static ArrayList<Player> players = new ArrayList<Player>();
	
	public static Player[][] battleRoom = new Player[10][2];
	
	
	public EventHandler(){
		
	}
	
	public synchronized boolean checkPlayerStatus(Player p){
		return p.getFighting();
	}
	
	public synchronized void newPlayer(){
		Player temp = new Player(id);
		players.add(temp);
		System.out.println("Player " + id + " created! ^^");
		id++;
		
	}
	
	public synchronized void readyPlayer(int id){
		queue.add(players.get(id));
	}
	
	public ArrayList<Player> getQueue(){
		return queue;
	}
	
	public Player[][] getbattleRoom(){
		return battleRoom;
	}
	
	public int getID(){
		return id;
	}
	
	
	
	


}
