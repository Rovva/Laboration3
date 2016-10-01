package Server;

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
	
	public void addAP(){
		AP = AP + 0.1;
	}
	
	public double getAP(){
		return AP;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
