package Server;

import java.util.PriorityQueue;

public class EventHandler {
	
	public int id = 0;
	
	public PriorityQueue<Player> queue = new PriorityQueue<Player>();
	
	public Player[][] battleRoom = new Player[10][2];
	
	
	public EventHandler(){
		
	}
	
	public boolean checkPlayerStatus(Player p){
		return p.fighting();
	}
	
	public void newPlayer(){
		
		Player newName = new Player(id);
		id++;
		queue.add(newName);
	}
	
	public void startMatch(){
		
		if (queue.size() >= 2){
			loop:
			for (int i = 0; i < battleRoom.length; i++){
				
				if (battleRoom[i] != null){
					
					battleRoom[i][0] = queue.poll();
					battleRoom[i][1] = queue.poll();
					System.out.println("Battle commenced! ==" + battleRoom[i][0].getID() + " VS " + battleRoom[i][1].getID() + "==");
					break loop;
					
				}
				
			}	
			
		}
		
		else{
			System.out.println("Not enough available players.");
		}
	}


}
