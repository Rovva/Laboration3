package Server;

import java.util.concurrent.ThreadLocalRandom;

public class Player {
	
	private int id;
	private int HP;
	private int availableAP;
	private boolean Fighting;
	BodyPart Head;
	BodyPart Torso;
	BodyPart LeftArm;
	BodyPart RightArm;
	BodyPart LeftLeg;
	BodyPart RightLeg;
	
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
	
	public void DealDamage_Head(){
		
		if (Head.getHP() <= 0){
			System.out.println("That body part is demolished");
		}
		
		else{
			
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int dmg = ThreadLocalRandom.current().nextInt(1, 6 + 1); // Roll D6
			dmg = Head.damage(dmg);									 // Check armor on body part
			HP = HP - dmg;
			System.out.println(id + " took " + dmg + " points of damage!");
			
			if (Head.getHP() <= 0){
				System.out.println(id + "'s " + Head.getName() + " is destroyed!");
			}
			
		}
		
		
		
	}
	
	public void ApplyArmor_Head(){
		
		if (availableAP > 0){
			availableAP = availableAP - 1;
			Head.addAP();
			System.out.println("CLANG CLANG! Head AC+1! Current AC on Helmet: " + Head.getAP() );
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
	public void getHP(){
		System.out.println("Player "+ getID() + ", has " + HP + " HP Remaining.");
		System.out.println("His head, has " + Head.getHP() + " HP Remaining.");
	}

}
