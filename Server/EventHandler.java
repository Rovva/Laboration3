package Server;

import java.util.PriorityQueue;

public class EventHandler {

	public static int id = 0;
	
	public static PriorityQueue<Player> queue = new PriorityQueue<Player>();

	
	public Player[][] battleRoom = new Player[10][2];
	
	
	public EventHandler(){
		
	}
	
	public boolean checkPlayerStatus(Player p){
		return p.getFighting();
	}
	
	public static void newPlayer(){
		
		Player newName = new Player(id);
		queue.add(newName);
		System.out.println("Player "+id+" created! ^^");
		id++;
	}
	
	public void startMatch(){
		
		if (queue.size() >= 2){
			loop:
			for (int i = 0; i < battleRoom.length; i++){
				
				if (battleRoom[i] != null){
					
					battleRoom[i][0] = queue.poll();
					battleRoom[i][1] = queue.poll();
					battleRoom[i][0].setFighting();
					battleRoom[i][1].setFighting();
					System.out.println("Battle commenced! ==" + battleRoom[i][0].getID() + " VS " + battleRoom[i][1].getID() + "==");
					break loop;
					
				}
				
			}	
			
		}
		
		else{
			System.out.println("Not enough available players.");
		}
	}
	
	public static void main(String[] args) {
		newPlayer();
		queue.peek().DealDamage_Head();
		queue.peek().getHP();
		queue.peek().ApplyArmor_Head();
		queue.peek().ApplyArmor_Head();
		queue.peek().ApplyArmor_Head();
		queue.peek().DealDamage_Head();
		queue.peek().DealDamage_Head();
		queue.peek().DealDamage_Head();
		queue.peek().DealDamage_Head();
		queue.peek().getHP();
		queue.peek().ApplyArmor_Head();
		queue.peek().ApplyArmor_Head();
		queue.peek().ApplyArmor_Head();
		queue.peek().ApplyArmor_Head();
		queue.peek().ApplyArmor_Head();
		queue.peek().ApplyArmor_Head();
		queue.peek().ApplyArmor_Head();
		queue.peek().ApplyArmor_Head();
		queue.peek().DealDamage_Head();
		queue.peek().DealDamage_Head();
		queue.peek().DealDamage_Head();
		queue.peek().getHP();
	}


}
