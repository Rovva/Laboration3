package Server;

import java.util.concurrent.ThreadLocalRandom;

public class Player {
	
	private int id;
	private int HP;
	private int availableAP;
	private boolean Fighting;
	private BodyPart Head;
	private BodyPart Torso;
	private BodyPart LeftArm;
	private BodyPart RightArm;
	private BodyPart LeftLeg;
	private BodyPart RightLeg;
	
	public Player(int id){
		this.id = id;
		HP = 300;
		availableAP = 10;
		Fighting = false;
		Head = new BodyPart("Head");
		Torso = new BodyPart("Torso");
		LeftArm = new BodyPart("LeftArm");
		RightArm = new BodyPart("RightArm");
		LeftLeg = new BodyPart("LeftLeg");
		RightLeg = new BodyPart("RightLeg");
	}
	
	public void DealDamage(BodyPart part){
		
		if (part.getHP() <= 0){
			System.out.println("That body part is demolished");
		}
		
		else{
			
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int dmg = ThreadLocalRandom.current().nextInt(1, 6 + 1); // Roll D6
			dmg = part.damage(dmg);									 // Check armor on body part
			HP = HP - dmg;
			System.out.println(id + " took " + dmg + " points of damage!");
			
			if (part.getHP() <= 0){
				System.out.println(id + "'s " + part.getName() + " is destroyed!");
			}
			
		}
		
		
		
	}
	
	public void ApplyArmor(BodyPart part, int amount){
		
		if (availableAP >= amount){
			availableAP = availableAP - amount;
			part.addAP(amount);
			System.out.println("CLANG CLANG! Head AC+ "+ amount + "! Current AC on Helmet: " + part.getAP() );
			System.out.println("Remaining AP points to spend: " + availableAP);
		}
		else{
			System.out.println("You don't have any available Armor Points left!");
		}
	}
	
	public int getID(){
		return id;
	}
	
	public boolean getFighting(){
		return Fighting;
	}
	
	public void setFighting(){
		if (Fighting == true){
			Fighting = false;
		}
		else{
			Fighting = true;
		}
	}
	public String getHP(){
		return ("Player "+ getID() + ", has " + HP + " HP Remaining.");
	}
	
	public void getHP(BodyPart part){
		System.out.println("Player " + id + "'s " + part.getName() + ", has " + part.getHP() + " HP Remaining.");
	}
	
	public BodyPart getHead(){
		return Head;
	}
	
	public BodyPart getTorso(){
		return Torso;
	}
	
	public BodyPart getLeftArm(){
		return LeftArm;
	}
	
	public BodyPart getRightArm(){
		return RightArm;
	}
	
	public BodyPart getLeftLeg(){
		return LeftLeg;
	}
	
	public BodyPart getRightLeg(){
		return RightLeg;
	}

}
