package Server;

import java.util.ArrayList;

public class startMatch {
	
	
	public static synchronized int fight(){

		if (EventHandler.queue.size() >= 2){

			for (int i = 0; i < EventHandler.battleRoom.length; i++){
				
				if (EventHandler.battleRoom[i][0] == null){
					
					EventHandler.battleRoom[i][0] = EventHandler.queue.get(0);
					EventHandler.queue.remove(0);
					EventHandler.battleRoom[i][1] = EventHandler.queue.get(0);
					EventHandler.queue.remove(0);
					EventHandler.battleRoom[i][0].setFighting();
					EventHandler.battleRoom[i][1].setFighting();
					System.out.println("Battle commenced! ==" + EventHandler.battleRoom[i][0].getID() + " VS " + EventHandler.battleRoom[i][1].getID() + "==");
					return i;
				}
			}	
		}
		
		else{
			System.out.println("Not enough available players.");
		}
		return -1;
	}
}
