package chp06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) {
		try {
			// Create a socket for the server
			ServerSocket serverSocket = new ServerSocket(9101);
			System.out.println("Server started at " + new Date() + "\n");
			
			// Listen for connection
			Socket socket = serverSocket.accept();
			System.out.println("Client has connected.");
			
			// Create data input and output streams
			DataInputStream fromClient = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
			DataOutputStream toClient = new DataOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			
			while(true) {
				// Receive radius from client
				double radius = fromClient.readDouble();
				// Calculate area
				double area = Math.PI * radius * radius;
				// Send area to client
				toClient.writeDouble(area);
				toClient.flush();
				
				System.out.println("Received radius: " + radius);
				System.out.println("Sent area: " + area);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
