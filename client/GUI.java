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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JOptionPane;

public class GUI implements Observer, ActionListener {
	
	JLabel welcomeLabel, connectingLabel, connectedLabel, connectedIPLabel, levelAdventureLabel,
		   fightLabel, attackQuestionLabel;
	
	JButton connectButton, disconnectButton, startFightButton;
	
	// Labels and buttons concerning bodyparts
	JLabel headApLabel, leftArmApLabel,	rightArmApLabel, torsoApLabel, leftLegApLabel, rightLegApLabel;
	JLabel placeArmorPointsLabel = new JLabel("Place out armorpoints!");
	JLabel armorPoints = new JLabel("0/0 points");
	JLabel armorLabel = new JLabel("Click below to upgrade armor:");
	JLabel resetArmorLabel = new JLabel("Click the button below to reset armor");
	JButton headButton = new JButton("Head +1");
	JButton leftArmButton = new JButton("Left Arm +1");
	JButton rightArmButton = new JButton("Right Arm +1");
	JButton torsoButton = new JButton("Torso +1"); 
	JButton leftLegButton = new JButton("Left Leg +1");
	JButton rightLegButton = new JButton("Right Leg +1");
	JButton armorResetButton = new JButton("Reset");
	JButton readyButton = new JButton("Ready!");
	
	int x_size = 500, y_size = 500;
	
	JFrame frame;
	SpringLayout layout;
	
	Container contentPane;
	
	String ipAdress;
	
	public GUI() {
		frame = new JFrame("Stickman Tournament");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(x_size, y_size);
		frame.setVisible(true);
		layout = new SpringLayout();
		contentPane = frame.getContentPane();
		contentPane.setLayout(layout);
		contentPane.setBackground(Color.WHITE);
		armorGUI();
	}
	
	private void resetGUI() {	
		contentPane.removeAll();
	}
	
	private void connectGUI() {
		resetGUI();
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
		connectingLabel = new JLabel("Connecting to: " + ipAdress);
		contentPane.add(connectingLabel);

		layout.putConstraint(SpringLayout.NORTH, connectingLabel, 20, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, connectingLabel, 20, SpringLayout.WEST, contentPane);
	}

	private void armorGUI() {
		resetGUI();
	    BufferedImage img;
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
				contentPane.add(leftArmButton);
				contentPane.add(rightArmButton);
				contentPane.add(torsoButton);
				contentPane.add(leftLegButton);
				contentPane.add(rightLegButton);
				contentPane.add(armorResetButton);
				contentPane.add(readyButton);

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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String op = arg0.getActionCommand();
		if(op == "Connect") {
			ipAdress = JOptionPane.showInputDialog("Adress:port");
			armorGUI();
		}
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
