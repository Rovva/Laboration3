package Server;

import java.util.ArrayList;

public class EventHandler {
	
	public int id;
	
	ArrayList<Player> matchup = new ArrayList<Player>();
	
	public EventHandler(){
		
	}
	
	public boolean checkPlayerStatus(Player p){
		return p.fighting();
	}
	
	public void linkPlayers(Player x, Player y){
		matchup.add(x, y);
	}

}
