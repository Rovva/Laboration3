package Server;

import java.util.concurrent.ThreadLocalRandom;

public class Player {
	
	private int id;
	private int HP;
	private int availableAP;
	private boolean Fighting;
	
	public Player(int id){
		this.id = id;
		HP = 300;
		availableAP = 10;
		Fighting = false;
		BodyPart Head = new BodyPart("Head");
		BodyPart Torso = new BodyPart("Torso");
		BodyPart LeftArm = new BodyPart("LeftArm");
		BodyPart RightArm = new BodyPart("RightArm");
		BodyPart LeftLeg = new BodyPart("LeftLeg");
		BodyPart RightLeg = new BodyPart("RightLeg");
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
			System.out.println(id + "took " + dmg + " points of damage!");
			
			if (part.getHP() <= 0){
				System.out.println(id + "'s " + part.getName() + " is destroyed!");
			}
			
		}
		
		
		
	}
	
	public void ApplyArmor(BodyPart part){
		
		if (availableAP > 0){
			availableAP = availableAP - 1;
			part.addAP();
		}
		else{
			System.out.println("You don't have any available Armor Points left!");
		}
	}

}
