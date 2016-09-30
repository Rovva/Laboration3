package client;

import javax.swing.JFrame;

import javax.swing.JLabel;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	placeArmorPointsLabel, armorPointsLabel, fightLabel, attackQuestionLabel, headApLabel, leftArmApLabel,
	rightArmApLabel, torsoApLabel, leftLegApLabel, rightLegApLabel;
	
	JButton connectButton, disconnectButton, resetBUtton, startFightButton;
	
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
				layout.putConstraint(SpringLayout.NORTH, head_imgL, 20, SpringLayout.NORTH, contentPane);
				layout.putConstraint(SpringLayout.WEST, head_imgL, -13, SpringLayout.EAST, leftarm_imgL);
				layout.putConstraint(SpringLayout.NORTH, leftarm_imgL, 0, SpringLayout.SOUTH, head_imgL);
				layout.putConstraint(SpringLayout.WEST, leftarm_imgL, 30, SpringLayout.WEST, contentPane);
				layout.putConstraint(SpringLayout.NORTH, torso_imgL, 0, SpringLayout.SOUTH, head_imgL);
				layout.putConstraint(SpringLayout.WEST, torso_imgL, 0, SpringLayout.EAST, leftarm_imgL);
				layout.putConstraint(SpringLayout.NORTH, rightarm_imgL, 0, SpringLayout.SOUTH, head_imgL);
				layout.putConstraint(SpringLayout.WEST, rightarm_imgL, 0, SpringLayout.EAST, torso_imgL);

				layout.putConstraint(SpringLayout.NORTH, leftleg_imgL, 0, SpringLayout.SOUTH, torso_imgL);
				layout.putConstraint(SpringLayout.WEST, leftleg_imgL, 0, SpringLayout.WEST, torso_imgL);

				layout.putConstraint(SpringLayout.NORTH, rightleg_imgL, 0, SpringLayout.SOUTH, torso_imgL);
				layout.putConstraint(SpringLayout.EAST, rightleg_imgL, 0, SpringLayout.EAST, torso_imgL);
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
