package client;

import javax.swing.JFrame;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import org.omg.CORBA.TIMEOUT;

import javax.swing.JOptionPane;

import client.Module;

public class GUI extends JFrame implements Observer, ActionListener {
	
	JLabel welcomeLabel, connectingLabel, connectedLabel, connectedIPLabel, levelAdventureLabel,
		   fightLabel, attackQuestionLabel;
	
	JButton connectButton, disconnectButton, startFightButton;
	
	// Labels and buttons concerning armoring part of the game
	JLabel headApLabel, leftArmApLabel,	rightArmApLabel, torsoApLabel, leftLegApLabel, rightLegApLabel;
	JLabel placeArmorPointsLabel = new JLabel("Place out armorpoints!");
	JLabel armorPoints = new JLabel("0/0 points");
	JLabel armorLabel = new JLabel("Click below to upgrade armor:");
	JLabel resetArmorLabel = new JLabel("Click the button below to reset armor");
	JButton headButton = new JButton("Head +1"), leftArmButton = new JButton("Left Arm +1"),
			rightArmButton = new JButton("Right Arm +1"), torsoButton = new JButton("Torso +1"),
			leftLegButton = new JButton("Left Leg +1"), rightLegButton = new JButton("Right Leg +1"),
			armorResetButton = new JButton("Reset"), readyButton = new JButton("Ready!");
	
	// Fighting buttons and labels
	JLabel attackLabel = new JLabel("Attack:");
	JButton attackHead = new JButton("Head"), attackLeftArm = new JButton("Left Arm"), 
			attackRightArm = new JButton("Right Arm"), attackTorso = new JButton("Torso"), 
			attackLeftLeg = new JButton("Left Leg"), attackRightLeg = new JButton("Right Leg");
	
	int x_size = 700, y_size = 500;
	
	JFrame frame;
	SpringLayout layout;
	Container contentPane;
	
	String ipAdress;
	String guiState;
	Module mod;
	
	public GUI(Module mod) {
		this.mod = mod;
		mod.addObserver(this);
		frame = new JFrame("Stickman Tournament");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(x_size, y_size);
		frame.setVisible(true);
		layout = new SpringLayout();
		contentPane = frame.getContentPane();
		contentPane.setLayout(layout);
		contentPane.setBackground(Color.WHITE);
		this.guiState = "Unconnected";
		connectGUI();
	}
	
	private void resetGUI() {	
		contentPane.removeAll();
	}
	
	private void connectGUI() {
		resetGUI();
		this.guiState = "Unconnected";
		welcomeLabel = new JLabel("Welcome to Stickman Tournament!");
		connectingLabel = new JLabel("Click the Connect button to connect to a server!");
		connectButton = new JButton("Connect");
		contentPane.add(welcomeLabel);
		contentPane.add(connectingLabel);
		contentPane.add(connectButton);

		connectButton.addActionListener(this);
		layout.putConstraint(SpringLayout.NORTH, welcomeLabel, 20, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, welcomeLabel, 20, SpringLayout.WEST, contentPane);
		
		layout.putConstraint(SpringLayout.NORTH, connectingLabel, 20, SpringLayout.NORTH, welcomeLabel);
		layout.putConstraint(SpringLayout.WEST, connectingLabel, 20, SpringLayout.WEST, contentPane);
		
		layout.putConstraint(SpringLayout.NORTH, connectButton, 20, SpringLayout.NORTH, connectingLabel);
		layout.putConstraint(SpringLayout.WEST, connectButton, 20, SpringLayout.WEST, contentPane);
	}
	
	private void connectingGUI() {
		resetGUI();
		frame.repaint();
		this.guiState = "Connecting";
		connectingLabel = new JLabel("Connecting to: " + ipAdress);
		contentPane.add(connectingLabel);

		layout.putConstraint(SpringLayout.NORTH, connectingLabel, 20, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, connectingLabel, 20, SpringLayout.WEST, contentPane);
	}

	private void armorGUI() {
		resetGUI();
	    BufferedImage img;
	    this.guiState = "Connected/Armoring";
			try {
				img = ImageIO.read(new File("Graphics/Player/P_Head1.png"));
				ImageIcon icon = new ImageIcon(img);
				JLabel head_imgL = new JLabel(icon);
				
				img = ImageIO.read(new File("Graphics/Player/P_LeftArm1.png"));
				icon = new ImageIcon(img);
				JLabel leftarm_imgL = new JLabel(icon);

				img = ImageIO.read(new File("Graphics/Player/P_torso1.png"));
				icon = new ImageIcon(img);
				JLabel torso_imgL = new JLabel(icon);

				img = ImageIO.read(new File("Graphics/Player/P_RightArm1.png"));
				icon = new ImageIcon(img);
				JLabel rightarm_imgL = new JLabel(icon);

				img = ImageIO.read(new File("Graphics/Player/P_leg1.png"));
				icon = new ImageIcon(img);
				JLabel leftleg_imgL = new JLabel(icon);

				img = ImageIO.read(new File("Graphics/Player/P_leg1.png"));
				icon = new ImageIcon(img);
				JLabel rightleg_imgL = new JLabel(icon);
				
				contentPane.add(head_imgL);
				contentPane.add(leftarm_imgL);
				contentPane.add(torso_imgL);
				contentPane.add(rightarm_imgL);
				contentPane.add(leftleg_imgL);
				contentPane.add(rightleg_imgL);
				
				
				headApLabel = new JLabel("0");
				leftArmApLabel = new JLabel("0");
				rightArmApLabel = new JLabel("0");
				torsoApLabel = new JLabel("0");
				leftLegApLabel = new JLabel("0");
				rightLegApLabel = new JLabel("0");
				
				contentPane.add(headApLabel, 1, 0);
				contentPane.add(leftArmApLabel, 1, 0);
				contentPane.add(rightArmApLabel, 1, 0);
				contentPane.add(torsoApLabel, 1, 0);
				contentPane.add(leftLegApLabel, 1, 0);
				contentPane.add(rightLegApLabel, 1, 0);
				
				contentPane.add(placeArmorPointsLabel);
				contentPane.add(armorPoints);
				
				layout.putConstraint(SpringLayout.NORTH, placeArmorPointsLabel, 5, SpringLayout.NORTH, contentPane);
				layout.putConstraint(SpringLayout.WEST, placeArmorPointsLabel, 5, SpringLayout.WEST, contentPane);
				
				layout.putConstraint(SpringLayout.NORTH, armorPoints, 5, SpringLayout.SOUTH, placeArmorPointsLabel);
				layout.putConstraint(SpringLayout.WEST, armorPoints, 5, SpringLayout.WEST, contentPane);
				
				layout.putConstraint(SpringLayout.NORTH, head_imgL, 75, SpringLayout.NORTH, contentPane);
				layout.putConstraint(SpringLayout.WEST, head_imgL, -13, SpringLayout.EAST, leftarm_imgL);
				
				layout.putConstraint(SpringLayout.SOUTH, headApLabel, -5, SpringLayout.NORTH, head_imgL);
				layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, headApLabel, 0, SpringLayout.HORIZONTAL_CENTER, head_imgL);
				
				layout.putConstraint(SpringLayout.NORTH, leftarm_imgL, 0, SpringLayout.SOUTH, head_imgL);
				layout.putConstraint(SpringLayout.WEST, leftarm_imgL, 30, SpringLayout.WEST, contentPane);
				
				layout.putConstraint(SpringLayout.SOUTH, leftArmApLabel, -5, SpringLayout.NORTH, leftarm_imgL);
				layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, leftArmApLabel, 0, SpringLayout.HORIZONTAL_CENTER, leftarm_imgL);
				
				layout.putConstraint(SpringLayout.NORTH, torso_imgL, 0, SpringLayout.SOUTH, head_imgL);
				layout.putConstraint(SpringLayout.WEST, torso_imgL, 0, SpringLayout.EAST, leftarm_imgL);
				
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, torsoApLabel, -20, SpringLayout.VERTICAL_CENTER, torso_imgL);
				layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, torsoApLabel, 0, SpringLayout.HORIZONTAL_CENTER, torso_imgL);
				
				layout.putConstraint(SpringLayout.NORTH, rightarm_imgL, 0, SpringLayout.SOUTH, head_imgL);
				layout.putConstraint(SpringLayout.WEST, rightarm_imgL, 0, SpringLayout.EAST, torso_imgL);

				layout.putConstraint(SpringLayout.SOUTH, rightArmApLabel, -5, SpringLayout.NORTH, rightarm_imgL);
				layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, rightArmApLabel, 0, SpringLayout.HORIZONTAL_CENTER, rightarm_imgL);
				
				layout.putConstraint(SpringLayout.NORTH, leftleg_imgL, 0, SpringLayout.SOUTH, torso_imgL);
				layout.putConstraint(SpringLayout.WEST, leftleg_imgL, 0, SpringLayout.WEST, torso_imgL);

				layout.putConstraint(SpringLayout.EAST, leftLegApLabel, -5, SpringLayout.WEST, leftleg_imgL);
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, leftLegApLabel, 0, SpringLayout.VERTICAL_CENTER, leftleg_imgL);
				
				layout.putConstraint(SpringLayout.NORTH, rightleg_imgL, 0, SpringLayout.SOUTH, torso_imgL);
				layout.putConstraint(SpringLayout.EAST, rightleg_imgL, 0, SpringLayout.EAST, torso_imgL);

				layout.putConstraint(SpringLayout.WEST, rightLegApLabel, 5, SpringLayout.EAST, rightleg_imgL);
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, rightLegApLabel, 0, SpringLayout.VERTICAL_CENTER, rightleg_imgL);
				
				contentPane.add(armorLabel);
				
				headButton.setPreferredSize(new Dimension(120, 30));
				leftArmButton.setPreferredSize(new Dimension(120, 30));
				rightArmButton.setPreferredSize(new Dimension(120, 30));
				torsoButton.setPreferredSize(new Dimension(120, 30));
				leftLegButton.setPreferredSize(new Dimension(120, 30));
				rightLegButton.setPreferredSize(new Dimension(120, 30));
				armorResetButton.setPreferredSize(new Dimension(120, 30));
				readyButton.setPreferredSize(new Dimension(120, 30));
				// Place all the buttons to upgrade armor
				contentPane.add(headButton);
				headButton.addActionListener(this);
				contentPane.add(leftArmButton);
				leftArmButton.addActionListener(this);
				contentPane.add(rightArmButton);
				rightArmButton.addActionListener(this);
				contentPane.add(torsoButton);
				torsoButton.addActionListener(this);
				contentPane.add(leftLegButton);
				leftLegButton.addActionListener(this);
				contentPane.add(rightLegButton);
				rightLegButton.addActionListener(this);
				contentPane.add(armorResetButton);
				armorResetButton.addActionListener(this);
				contentPane.add(readyButton);
				readyButton.addActionListener(this);

				layout.putConstraint(SpringLayout.NORTH, armorLabel, 20, SpringLayout.NORTH, contentPane);
				layout.putConstraint(SpringLayout.EAST, armorLabel, -5, SpringLayout.EAST, contentPane);
				
				layout.putConstraint(SpringLayout.EAST, headButton, -5, SpringLayout.EAST, armorLabel);
				layout.putConstraint(SpringLayout.NORTH, headButton, 5, SpringLayout.SOUTH, armorLabel);

				layout.putConstraint(SpringLayout.EAST, leftArmButton, 0, SpringLayout.EAST, headButton);
				layout.putConstraint(SpringLayout.NORTH, leftArmButton, 5, SpringLayout.SOUTH, headButton);
				
				layout.putConstraint(SpringLayout.EAST, rightArmButton, 0, SpringLayout.EAST, leftArmButton);
				layout.putConstraint(SpringLayout.NORTH, rightArmButton, 5, SpringLayout.SOUTH, leftArmButton);

				layout.putConstraint(SpringLayout.EAST, torsoButton, 0, SpringLayout.EAST, rightArmButton);
				layout.putConstraint(SpringLayout.NORTH, torsoButton, 5, SpringLayout.SOUTH, rightArmButton);

				layout.putConstraint(SpringLayout.EAST, leftLegButton, 0, SpringLayout.EAST, torsoButton);
				layout.putConstraint(SpringLayout.NORTH, leftLegButton, 5, SpringLayout.SOUTH, torsoButton);

				layout.putConstraint(SpringLayout.EAST, rightLegButton, 0, SpringLayout.EAST, leftLegButton);
				layout.putConstraint(SpringLayout.NORTH, rightLegButton, 5, SpringLayout.SOUTH, leftLegButton);
				
				layout.putConstraint(SpringLayout.EAST, armorResetButton, 0, SpringLayout.EAST, rightLegButton);
				layout.putConstraint(SpringLayout.NORTH, armorResetButton, 5, SpringLayout.SOUTH, rightLegButton);
				
				layout.putConstraint(SpringLayout.EAST, readyButton, 0, SpringLayout.EAST, armorResetButton);
				layout.putConstraint(SpringLayout.NORTH, readyButton, 30, SpringLayout.SOUTH, armorResetButton);
							
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	private void fightingGUI() {
		resetGUI();
		BufferedImage img;
		this.guiState = "Connected/Fighting";
		try {
			// Get images for the player
			img = ImageIO.read(new File("Graphics/Player/P_Head1.png"));
			ImageIcon icon = new ImageIcon(img);
			JLabel playerHead_imgL = new JLabel(icon);
			
			img = ImageIO.read(new File("Graphics/Player/P_LeftArm1.png"));
			icon = new ImageIcon(img);
			JLabel playerLeftarm_imgL = new JLabel(icon);

			img = ImageIO.read(new File("Graphics/Player/P_torso1.png"));
			icon = new ImageIcon(img);
			JLabel playerTorso_imgL = new JLabel(icon);

			img = ImageIO.read(new File("Graphics/Player/P_RightArm1.png"));
			icon = new ImageIcon(img);
			JLabel playerRightarm_imgL = new JLabel(icon);

			img = ImageIO.read(new File("Graphics/Player/P_leg1.png"));
			icon = new ImageIcon(img);
			JLabel playerLeftleg_imgL = new JLabel(icon);

			img = ImageIO.read(new File("Graphics/Player/P_leg1.png"));
			icon = new ImageIcon(img);
			JLabel playerRightleg_imgL = new JLabel(icon);
			
			// Add images to GUI
			contentPane.add(playerHead_imgL);
			contentPane.add(playerLeftarm_imgL);
			contentPane.add(playerTorso_imgL);
			contentPane.add(playerRightarm_imgL);
			contentPane.add(playerLeftleg_imgL);
			contentPane.add(playerRightleg_imgL);
			
			// Place player-images in layout

			layout.putConstraint(SpringLayout.NORTH, playerHead_imgL, 75, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, playerHead_imgL, -13, SpringLayout.EAST, playerLeftarm_imgL);
			
			layout.putConstraint(SpringLayout.NORTH, playerLeftarm_imgL, 0, SpringLayout.SOUTH, playerHead_imgL);
			layout.putConstraint(SpringLayout.WEST, playerLeftarm_imgL, 30, SpringLayout.WEST, contentPane);
			
			layout.putConstraint(SpringLayout.NORTH, playerTorso_imgL, 0, SpringLayout.SOUTH, playerHead_imgL);
			layout.putConstraint(SpringLayout.WEST, playerTorso_imgL, 0, SpringLayout.EAST, playerLeftarm_imgL);

			layout.putConstraint(SpringLayout.NORTH, playerRightarm_imgL, 0, SpringLayout.SOUTH, playerHead_imgL);
			layout.putConstraint(SpringLayout.WEST, playerRightarm_imgL, 0, SpringLayout.EAST, playerTorso_imgL);
			
			layout.putConstraint(SpringLayout.NORTH, playerLeftleg_imgL, 0, SpringLayout.SOUTH, playerTorso_imgL);
			layout.putConstraint(SpringLayout.WEST, playerLeftleg_imgL, 0, SpringLayout.WEST, playerTorso_imgL);
			
			layout.putConstraint(SpringLayout.NORTH, playerRightleg_imgL, 0, SpringLayout.SOUTH, playerTorso_imgL);
			layout.putConstraint(SpringLayout.EAST, playerRightleg_imgL, 0, SpringLayout.EAST, playerTorso_imgL);

			// Get images for enemy
			img = ImageIO.read(new File("Graphics/Enemy/E_Head1.png"));
			icon = new ImageIcon(img);
			JLabel enemyHead_imgL = new JLabel(icon);
			
			img = ImageIO.read(new File("Graphics/Enemy/E_RightArm1.png"));
			icon = new ImageIcon(img);
			JLabel enemyLeftarm_imgL = new JLabel(icon);

			img = ImageIO.read(new File("Graphics/Enemy/E_torso1.png"));
			icon = new ImageIcon(img);
			JLabel enemyTorso_imgL = new JLabel(icon);

			img = ImageIO.read(new File("Graphics/Enemy/E_LeftArm1.png"));
			icon = new ImageIcon(img);
			JLabel enemyRightarm_imgL = new JLabel(icon);

			img = ImageIO.read(new File("Graphics/Enemy/E_leg1.png"));
			icon = new ImageIcon(img);
			JLabel enemyLeftleg_imgL = new JLabel(icon);

			img = ImageIO.read(new File("Graphics/Enemy/E_leg1.png"));
			icon = new ImageIcon(img);
			JLabel enemyRightleg_imgL = new JLabel(icon);

			// Add images to GUI
			contentPane.add(enemyHead_imgL);
			contentPane.add(enemyLeftarm_imgL);
			contentPane.add(enemyTorso_imgL);
			contentPane.add(enemyRightarm_imgL);
			contentPane.add(enemyLeftleg_imgL);
			contentPane.add(enemyRightleg_imgL);
						
			
			// Place enemy images in layout
			layout.putConstraint(SpringLayout.NORTH, enemyHead_imgL, 75, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.EAST, enemyHead_imgL, 13, SpringLayout.WEST, enemyRightarm_imgL);
			
			layout.putConstraint(SpringLayout.NORTH, enemyRightarm_imgL, 0, SpringLayout.SOUTH, enemyHead_imgL);
			layout.putConstraint(SpringLayout.EAST, enemyRightarm_imgL, -30, SpringLayout.EAST, contentPane);
			
			layout.putConstraint(SpringLayout.NORTH, enemyTorso_imgL, 0, SpringLayout.SOUTH, enemyHead_imgL);
			layout.putConstraint(SpringLayout.EAST, enemyTorso_imgL, 0, SpringLayout.WEST, enemyRightarm_imgL);

			layout.putConstraint(SpringLayout.NORTH, enemyLeftarm_imgL, 0, SpringLayout.SOUTH, enemyHead_imgL);
			layout.putConstraint(SpringLayout.EAST, enemyLeftarm_imgL, 0, SpringLayout.WEST, enemyTorso_imgL);
			
			layout.putConstraint(SpringLayout.NORTH, enemyLeftleg_imgL, 0, SpringLayout.SOUTH, enemyTorso_imgL);
			layout.putConstraint(SpringLayout.WEST, enemyLeftleg_imgL, 0, SpringLayout.WEST, enemyTorso_imgL);
			
			layout.putConstraint(SpringLayout.NORTH, enemyRightleg_imgL, 0, SpringLayout.SOUTH, enemyTorso_imgL);
			layout.putConstraint(SpringLayout.EAST, enemyRightleg_imgL, 0, SpringLayout.EAST, enemyTorso_imgL);
			
			
			// Add label and buttons
			contentPane.add(attackLabel);
			contentPane.add(attackHead);
			attackHead.addActionListener(this);
			contentPane.add(attackLeftArm);
			attackLeftArm.addActionListener(this);
			contentPane.add(attackTorso);
			attackTorso.addActionListener(this);
			contentPane.add(attackRightArm);
			attackRightArm.addActionListener(this);
			contentPane.add(attackLeftLeg);
			attackLeftLeg.addActionListener(this);
			contentPane.add(attackRightLeg);
			attackRightLeg.addActionListener(this);
			
			// Place label and buttons in gui
			layout.putConstraint(SpringLayout.NORTH, attackLabel, 5, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, attackLabel, 0, SpringLayout.WEST, contentPane);
			
			layout.putConstraint(SpringLayout.NORTH, attackHead, 5, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, attackHead, 5, SpringLayout.EAST, attackLabel);
			
			layout.putConstraint(SpringLayout.NORTH, attackLeftArm, 5, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, attackLeftArm, 5, SpringLayout.EAST, attackHead);
			
			layout.putConstraint(SpringLayout.NORTH, attackTorso, 5, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, attackTorso, 5, SpringLayout.EAST, attackLeftArm);
			
			layout.putConstraint(SpringLayout.NORTH, attackRightArm, 5, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, attackRightArm, 5, SpringLayout.EAST, attackTorso);
			
			layout.putConstraint(SpringLayout.NORTH, attackLeftLeg, 5, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, attackLeftLeg, 5, SpringLayout.EAST, attackRightArm);
			
			layout.putConstraint(SpringLayout.NORTH, attackRightLeg, 5, SpringLayout.NORTH, contentPane);
			layout.putConstraint(SpringLayout.WEST, attackRightLeg, 5, SpringLayout.EAST, attackLeftLeg);

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void waitGUI() {
		resetGUI();
		this.guiState = "Connected/FightWait";
		JLabel waiting = new JLabel("Waiting for another player...");
		contentPane.add(waiting);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, waiting, 0, SpringLayout.VERTICAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, waiting, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String op = arg0.getActionCommand();
		if(op == "Connect") {
			ipAdress = JOptionPane.showInputDialog("Adress:port");
			try {
				mod.connectToServer(ipAdress);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(op == "Reset") {
			mod.resetArmorPoints();
		} else if(op == "Ready!") {
			mod.setState("Connected/FightWait");
		}
		checkBodyParts(op);
		attackBodyPart(op);
		this.armorPoints.setText(mod.getArmorPoints() + " / " + mod.getMaxArmorPoints() + " points.");
	}
	
	private void attackBodyPart(String op) {
		if(op == "Head") {
			mod.dealDamage("Head");
		} else if (op == "Left Arm") {
			mod.dealDamage("Left Arm");
		} else if (op == "Torso") {
			mod.dealDamage("Torso");
		} else if (op == "Right Arm") {
			mod.dealDamage("Right Arm");
		} else if (op == "Left Leg") {
			mod.dealDamage("Left Leg");
		} else if (op == "Right Leg") {
			mod.dealDamage("Right Leg");
		}
	}
	
	private void checkBodyParts(String op) {
		if(mod.getArmorPoints() > 0) {
			if(op == "Head +1") {
				mod.setArmorPoint("Head");
			} else if(op == "Left Arm +1") {
				mod.setArmorPoint("Left Arm");
			} else if(op == "Torso +1") {
				mod.setArmorPoint("Torso");
			} else if(op == "Right Arm +1") {
				mod.setArmorPoint("Right Arm");
			} else if(op == "Left Leg +1") {
				mod.setArmorPoint("Left Leg");
			} else if(op == "Right Leg +1") {
				mod.setArmorPoint("Right Leg");
			}
		}
	}
	
	/* void attackBodyParts(String op) {
		
	} */
	
	@Override
	public void update(Observable arg0, Object arg1) {
		String moduleGameState = mod.getState();
		System.out.println(moduleGameState);
		if(moduleGameState == ("Unconnected")) {
			connectGUI();
		} else if(moduleGameState == ("Connecting")) {
			connectingGUI();
			if(mod.checkConnection()) {
				mod.setState("Connected/Armoring");
			}
		} else if(moduleGameState == ("Connected/Armoring") && this.guiState != "Connected/Armoring") {
			armorGUI();
		} else if(moduleGameState == ("Connected/Armoring") && this.guiState == "Connected/Armoring") {
			getArmorLabels();
		} else if(moduleGameState == ("Connected/FightWait") && this.guiState != "Connected/FightWait") {
			waitGUI();
			mod.sendArmorPoints();
			System.out.println("wait");
			mod.setReady();
		} else if(moduleGameState == ("Connected/FightWait") && this.guiState == "Connected/FightWait") {
			System.out.println("moduleGameState : " + moduleGameState + " this.guiState: " + this.guiState);
			waitGUI();
			System.out.println("Waiting...");
			loop:
			while(true) {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
					if(mod.sendRecheck()) {
						System.out.println("LOL");
						mod.setState("Connected/Fighting");
						break loop;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break loop;
				}
			}
		} else if(moduleGameState == ("Connected/Fighting") && this.guiState != "Connected/Fighting") {
			System.out.println("moduleGameState : " + moduleGameState + " this.guiState: " + this.guiState);
			fightingGUI();
		} else if(moduleGameState == ("Connected/Fighting") && this.guiState == "Connected/Fighting") {
			System.out.println("moduleGameState : " + moduleGameState + " this.guiState: " + this.guiState);
			fightingGUI();
		} else if(moduleGameState == "Disconnect" && this.guiState != "Disconnect") {
		//	disconnectGUI();
		}
		this.frame.validate();
	}
	
	private void getArmorLabels() {
		headApLabel.setText((String.valueOf(mod.getBodyPartArmor("Head"))));
		leftArmApLabel.setText((String.valueOf(mod.getBodyPartArmor("Left Arm"))));
		torsoApLabel.setText((String.valueOf(mod.getBodyPartArmor("Torso"))));
		rightArmApLabel.setText((String.valueOf(mod.getBodyPartArmor("Right Arm"))));
		leftLegApLabel.setText((String.valueOf(mod.getBodyPartArmor("Left Leg"))));
		rightLegApLabel.setText((String.valueOf(mod.getBodyPartArmor("Right Leg"))));
	}
}
