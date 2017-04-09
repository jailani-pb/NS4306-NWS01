package ch06chattingapplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ChatServer {

	public static void main(String[] args) {
		try {
			// Create a server socket
			ServerSocket serverSocket = new ServerSocket(9102);
			System.out.println("Server started at " + new Date() + "\n");
			
			while(true) {
				// Listen for client1 connection
				Socket client1 = serverSocket.accept();
				// Listen for client2 connection
				Socket client2 = serverSocket.accept();
				
				new Thread(new SessionHandler(client1, client2)).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
