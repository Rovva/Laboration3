package Server;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class EventHandler {

	public int id = 0;
	
	public ArrayList<Player> queue = new ArrayList<Player>();
	
	public ArrayList<Player> players = new ArrayList<Player>();
	
	public Player[][] battleRoom = new Player[10][2];
	
	
	public EventHandler(){
		
	}
	
	public boolean checkPlayerStatus(Player p){
		return p.getFighting();
	}
	
	public void newPlayer(){
		Player temp = new Player(id);
		players.add(temp);
		System.out.println("Player "+id+" created! ^^");
		readyPlayer(id); //THIS METHOD IS TEMPORARY! WILL BE USED A "READY" CLIENT.
		id++;
	}
	
	public void readyPlayer(int id){
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
