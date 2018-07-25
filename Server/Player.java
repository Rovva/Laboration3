package server;

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
	
	private boolean currentTurn;
	
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
	
	public void DealDamage(String part){
		BodyPart currentBodyPart;
		if(part.equals("Head")) {
			currentBodyPart = this.Head;
		} else if(part.equals("Left_Arm")) {
			currentBodyPart = this.LeftArm;
		} else if(part.equals("Torso")) {
			currentBodyPart = this.Torso;
		} else if(part.equals("Right_Arm")) {
			currentBodyPart = this.RightArm;
		} else if(part.equals("Left_Leg")) {
			currentBodyPart =  this.LeftLeg;
		} else if(part.equals("Right_Leg")) {
			currentBodyPart = this.RightLeg;
		} else {
			currentBodyPart = null;
		}
		
		if (currentBodyPart.getHP() <= 0){
			System.out.println("That body part is demolished");
		} else {
			
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int dmg = ThreadLocalRandom.current().nextInt(1, 6 + 1); // Roll D6
			dmg = currentBodyPart.damage(dmg);									 // Check armor on body part
			HP = HP - dmg;
			System.out.println(id + " took " + dmg + " points of damage!");
			
			if (currentBodyPart.getHP() <= 0){
				System.out.println(id + "'s " + currentBodyPart.getName() + " is destroyed!");
			}
			
		}
		
	}
	
	public void ApplyArmor(String bodypart, int amount){
		
		if(bodypart.equals("Head")) {
			Head.addAP(amount);
		} else if(bodypart.equals("Left_arm")) {
			LeftArm.addAP(amount);
		} else if(bodypart.equals("Torso")) {
			Torso.addAP(amount);
		} else if(bodypart.equals("Right_arm")) {
			RightArm.addAP(amount);
		} else if(bodypart.equals("Left_leg")) {
			LeftLeg.addAP(amount);
		} else if(bodypart.equals("Right_leg")) {
			RightLeg.addAP(amount);
		}
		
		/*if (availableAP >= amount){
			availableAP = availableAP - amount;
			part.addAP(amount);
			System.out.println("CLANG CLANG! Head AC+ "+ amount + "! Current AC on Helmet: " + part.getAP() );
			System.out.println("Remaining AP points to spend: " + availableAP);
		}
		else{
			System.out.println("You don't have any available Armor Points left!");
		}*/
	}
	
	public int getID(){
		return id;
	}
	
	public int getArmorPoints() {
		return this.availableAP;
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
	
	public boolean getTurn() {
		return currentTurn;
	}
	
	public void setTurn() {
		if(currentTurn == true) {
			currentTurn = false;
		} else {
			currentTurn = true;
		}
	}
}
