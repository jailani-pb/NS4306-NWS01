package chp06chattingapplicationextended;

import java.io.IOException;
import java.net.BindException;
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
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(9101);
			System.out.println("Server started at " + new Date() + "\n");
			while(true) {
				Socket client1 = serverSocket.accept();
				new Thread(new SessionInitial(client1)).start();
			}
		} catch (IOException e) {
			if(e instanceof BindException) {
				System.out.println("Unable to start server due to port is already in used.");
			} else {
				System.out.println("Unexpected problem. Unable to start server.");
			}
		} finally {
			try {
				if(serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				System.out.println("Unexpected problem. Unable to close server socket.");
			}
		}
	}

}
