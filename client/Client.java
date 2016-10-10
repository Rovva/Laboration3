package client;

import java.io.*;
import java.net.*;
 
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
}
