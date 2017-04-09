package chp06chattingapplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

	static Socket socket;
	static DataInputStream fromServer;
	static DataOutputStream toServer;
	static Scanner scanner;
	
	public static void main(String[] args) {
		try {
			// Create socket for client
			socket = new Socket("localhost", 9101);
			
			fromServer = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
			toServer = new DataOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			
			scanner = new Scanner(System.in);
			
			// Read welcoming message
			readWelcomeMessage();
			// Read main menu
			readMainMenu();
			// Select option (main menu) and send to server
			selectMainMenuOption();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						try {
							if(fromServer.available() > 0) {
								String receiveMsg = fromServer.readUTF();
								System.out.println(">>> " + receiveMsg);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
			
			while(true) {
				String sendMsg = scanner.nextLine();
				toServer.writeUTF(sendMsg);
				toServer.flush();
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readWelcomeMessage() throws IOException {
		// (1)
		System.out.println(fromServer.readUTF());
	}
	
	public static void readMainMenu() throws IOException {
		// (2)
		System.out.println(fromServer.readUTF());
	}
	
	public static void selectMainMenuOption() throws IOException {
		String option = scanner.nextLine();
		// (A)
		toServer.writeUTF(option);
		toServer.flush();
		// (3)
		boolean validOptionSelected = fromServer.readBoolean();
		if(validOptionSelected) {
			if(option.equals("1")) {
				System.out.println(fromServer.readUTF());
				waitingForOtherClient();
			}
		} else {
			// (3-1)
			System.out.println(fromServer.readUTF());
			// (3-2)
			readMainMenu();
			selectMainMenuOption();
		}
	}
	
	public static void waitingForOtherClient() throws IOException {
		boolean waiting = true;
		while(waiting) {
			try {
				Thread.sleep(1000);
				if(fromServer.available() > 0) {
					// (4)
					waiting = fromServer.readBoolean();
					// (4-1)
					System.out.println(fromServer.readUTF());
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
