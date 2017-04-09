package chp06chattingapplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ChatServer {

	static List<SessionHandler> availableSessions;
	static int numberOfSessionCreated = 0;
	
	public static void main(String[] args) {
		availableSessions = Collections.synchronizedList(new ArrayList<SessionHandler>());
		try {
			// Create a server socket
			ServerSocket serverSocket = new ServerSocket(9101);
			System.out.println("Server started at " + new Date() + "\n");
			
			while(true) {
				// Listen for client 1 connection
				Socket client1 = serverSocket.accept();
				
				new Thread(new SessionInitial(client1)).start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
