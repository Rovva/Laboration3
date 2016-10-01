package Server;

import java.util.LinkedList;
import java.util.PriorityQueue;

import carFactory.Car;
import carWashSim.FIFO;

public class EventHandler {
	
	public int id = 0;
	
	//public static PriorityQueue<Player> queue = new PriorityQueue<Player>();
	
	
	public static Player[][] battleRoom = new Player[10][2];
	FIFO<Player> Queue = new FIFO<Player>();
	
	
	public EventHandler(){
		
	}
	
	public boolean checkPlayerStatus(Player p){
		return p.fighting();
	}
	
	public Player newPlayer(){
		
		Player newbie = new Player(id);
		id++;
		return newbie;
	}
	
	public static void startMatch(){
		
		if (queue.size() >= 2){
			loop:
			for (int i = 0; i < battleRoom.length; i++){
				
				if (battleRoom[i] != null){
					
					battleRoom[i][0] = queue.poll();
					battleRoom[i][1] = queue.poll();
					System.out.println("Battle commenced! ==" + battleRoom[i][0].getID() + " VS " + battleRoom[i][1].getID() + "==");
					battleRoom[i][0].Fighting = true;
					battleRoom[i][1].Fighting = true;
					break loop;
					
				}
				
			}	
			
		}
		
		else{
			System.out.println("Not enough available players!");
		}
	}

	public static void main(String[] args) {
		newPlayer();
		newPlayer();
		System.out.println(queue.peek());
		System.out.println(queue.size());
		startMatch();

	}


}
