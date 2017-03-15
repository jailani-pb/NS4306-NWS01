package chp06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			// Create socket for client
			Socket socket = new Socket("localhost", 9101);
			
			// Create data input and output stream
			DataInputStream fromServer = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
			DataOutputStream toServer = new DataOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			
			// For user input
			Scanner scanner = new Scanner(System.in);
			
			while(true) {
				// Ask for user input and parse to double
				double radius = Double.parseDouble(scanner.nextLine());
				// Send radius to server
				toServer.writeDouble(radius);
				toServer.flush();
				// Receive area from server
				double area = fromServer.readDouble();
				
				System.out.println("Radius sent: " + radius);
				System.out.println("Area received: " + area);
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
