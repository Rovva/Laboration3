package Server;

import java.net.*;
import java.io.*;

 
public class ServerThread extends Thread {
    private Socket socket = null;
 
    public ServerThread(Socket socket) {
        super("ServerThread");
        this.socket = socket;
    }
     
    public void run() {
 
        try (
        	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String inputLine, outputLine;
            Protocol proto = new Protocol();
            outputLine = proto.processInput(null);
            out.writeUTF(outputLine);
            out.flush();
 
            while ((inputLine = in.readLine()) != null) {
                outputLine = proto.processInput(inputLine);
                out.writeUTF(outputLine);
                out.flush();
                if (outputLine.equals("Bye"))
                    break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}