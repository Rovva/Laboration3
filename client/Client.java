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
		
		boolean checkConnection() {
			return false;
		}


		public int newPlayer() {
			fromUser = "NEW_CLIENT";
			String bla;
			String[] bla2;
			try {
				out.writeUTF(fromUser);
				out.flush();
				bla = in.readUTF();
				return Integer.parseInt(bla);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 666;
		}
}
