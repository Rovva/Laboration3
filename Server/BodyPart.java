package server;

public class BodyPart {
	
	private String name;
	private int HP;
	private double AP;
	
	public BodyPart(String name){
		this.name = name;
		HP = 100;
		AP = 0.0;
	}
	
	public String getName(){
		return name;
	}
	
	public int damage(int dmg){
		dmg = (int) (dmg * (1 - AP));
		HP = HP - dmg;
		return dmg;
	}
	
	public int getHP(){
		return HP;
	}
	
	public void addAP(int i){
		this.AP = AP + (i / 10);
	}
	
	public int getAP(){
		return (int)Math.round(AP * 10);
	}

}
