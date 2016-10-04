package Server;

import java.util.ArrayList;

public class startMatch {
	
	EventHandler lol;
	
	
	public startMatch(EventHandler lol){
		this.lol = lol;
		
	}
	
	public void fight(){

		if (lol.queue.size() >= 2){

			for (int i = 0; i < lol.battleRoom.length; i++){
				
				if (lol.battleRoom[i][0] == null){
					
					lol.battleRoom[i][0] = lol.queue.get(0);
					lol.queue.remove(0);
					lol.battleRoom[i][1] = lol.queue.get(0);
					lol.queue.remove(0);
					lol.battleRoom[i][0].setFighting();
					lol.battleRoom[i][1].setFighting();
					System.out.println("Battle commenced! ==" + lol.battleRoom[i][0].getID() + " VS " + lol.battleRoom[i][1].getID() + "==");
					break;
					
				}
				
			}	
			
		}
		
		else{
			System.out.println("Not enough available players.");
		}
	}


}
