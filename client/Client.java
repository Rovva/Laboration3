package client;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
 
public class Client {

    String fromServer;
    String fromUser;
    Socket socket;
    DataOutputStream out;
    DataInputStream in;
         
		void Connect(String ipPort) throws IOException{ 
			String toParse = ipPort;
			String delims = "[:]";
			String[] parsed = toParse.split(delims);
	        String hostName = parsed[0];
	        int portNumber = Integer.parseInt(parsed[1]);
	 
	        try {
	            socket = new Socket(hostName, portNumber);
	            out = new DataOutputStream(socket.getOutputStream());
	            in = new DataInputStream(socket.getInputStream());
	            
// All den h�r utkommenterade koden kan vi nog ta bort.
//	            in = new BufferedReader(
//	                new InputStreamReader(socket.getInputStream()));
//	            BufferedReader stdIn =
//	                new BufferedReader(new InputStreamReader(System.in));
//	 
//	            while ((fromServer = in.readLine()) != null) {
//	                System.out.println("Server: " + fromServer);
//	                if (fromServer.equals("Bye."))
//	                    break;
//	                 
//	                fromUser = stdIn.readLine();
//	                if (fromUser != null) {
//	                    System.out.println("Client: " + fromUser);
//	                    out.writeUTF(fromUser);
//	                    out.flush();
//	                }
//	            }
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host " + hostName);
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to " +
	                hostName);
	            System.exit(1);
	        }
		}

		// Kan vara v�rt att byta ut variabel namn s�som bla och temp.
		public int[] newPlayer() {
			fromUser = "NEW_CLIENT";
			String bla;
			String[] bla2;
			int[] temp = new int[2];
			try {
				out.writeUTF(fromUser);
				out.flush();
				bla = in.readUTF();
				System.out.println(bla);
				bla2 = bla.split(" ");
				temp[0] = Integer.parseInt(bla2[1]);
				temp[1] = Integer.parseInt(bla2[3]);
				return temp;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			temp[0] = 666;
			return temp;
		}
		
		public String checkCurrentConnection() {
			fromUser = "PING";
			try {
				out.writeUTF(fromUser);
				out.flush();
				return in.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public void sendArmorToServer(int[] armor, int playerID) {
			String temp;
			try {
				temp = "Armor " + playerID + " Head " + armor[0] + " Left_arm " + armor[1] + " Torso " + armor[2] + " Right_arm " + armor[3]
						 + " Left_leg " + armor[4] + " Right_leg " + armor[5];
				out.writeUTF(temp);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void sendReady(int id) {
			String temp;
			temp = "Ready " + id;
			try {
				out.writeUTF(temp);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Denna metod byter ut den gamla "checkReady", byta variabelnamn kan vara bra
		public int checkOpponent(int id) {
			String temp;
			String[] temp2;
			
			temp = "Opponents " + id;
			
			try {
				out.writeUTF(temp);
				out.flush();
				
				temp = in.readUTF();
				
				if(temp.contains("against")) {
					System.out.println("vs: " + temp);
					temp2 = temp.split(" ");
					if(Integer.parseInt(temp2[0]) == id) {
						return Integer.parseInt(temp2[2]);
					} else if(Integer.parseInt(temp2[2]) == id) {
						return Integer.parseInt(temp2[0]);
					} else {
						System.out.println("Something went wrong checking opponent");
						return -1;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Something went really wrong when checking opponent");
				return -1;
			}
			return -1;
		}
		// Vi kan nog mest troligt ta bort denna metod.
		public int checkReady(int id){
			String temp;
			String[] temp2;
			temp = "Recheckagain " + id;
			try {
				out.writeUTF(temp);
				out.flush();
				
				temp = in.readUTF();

				if(temp.contains("Room")){
					temp2 = in.readUTF().split(" ");
					System.out.println(temp2[2]);
					return Integer.parseInt(temp2[3]);
				} else if(temp.contains("Waiting")) {
					return -1;
				} else{
					return -1;
				}
								
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Something went wrong while cheking ready");
				return -1;
			}
		}
		// Denna metod anv�nds inte alls.
		public int listenToCall(int playerid) {
			String temp;
			String[] temp2;
			while(true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				try {
					temp = in.readUTF();
					System.out.println(temp);
					if(temp != null && temp.contains("Battle")) {
						temp2 = temp.split(" ");
						// SERVERN SKRIKER: Battle begun 0 vs 1
						if(Integer.parseInt(temp2[2]) == playerid) {
							return Integer.parseInt(temp2[4]);
						} else if(Integer.parseInt(temp2[4]) == playerid) {
							return Integer.parseInt(temp2[2]);
						}
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		// Vi kan nog s�kert snygga till den h�r metoden.
		boolean sendDamage(String bodypart, int opponentID) {
			try {
				out.writeUTF("DMG Hit " + bodypart + " Player " + opponentID);
				if(in.readUTF().contains("DMG")) {
					System.out.println("Contains DMG");
					return true;
				} else {
					System.out.println("Contains nothing");
					return false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		// Skriva en metod f�r att kolla om det �r spelarens tur att sl�ss.
		int checkTurn() {
			try {
				out.writeUTF("player_turn");

				String temp = in.readUTF();
				System.out.println(temp);
				String temp2[] = temp.split(" ");
				if(in.readUTF().contains("Player id")) {
					System.out.println("Player " + temp2[2] + "s turn");
				}
				return Integer.parseInt(temp2[2]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
			
		}
		
}
