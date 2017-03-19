package chp06chattingapplicationextended;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

	static String ipaddress = "localhost";
	static int portnumber = 9101;
	static Socket socket;
	static DataInputStream fromServer;
	static DataOutputStream toServer;
	static Scanner scanner;
	static boolean chatActive = true;
	
	public static void main(String[] args) {
		try {
			socket = new Socket(ipaddress, portnumber);
			
			fromServer = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
			toServer = new DataOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			
			scanner = new Scanner(System.in);
			
			readWelcomeMessage();
			readMainMenu();
			selectMainMenuOption();

			new Thread(new Runnable() {
				@Override
				public void run() {
					while(chatActive) {
						try {
							if(fromServer.available() > 0) {
								String receiveMsg = fromServer.readUTF();
								System.out.println(">>> " + receiveMsg);
								if(!fromServer.readBoolean()) {
									chatActive = false;
									scanner.close();
									System.out.println("Problem sending the message to the other client.");
									System.out.println("Thank you for using PB Chat.");
								}				
								
							}
						} catch (IOException e) {
						}
					}
				}
			}).start();
			
			while(chatActive) {
				String sendMsg = scanner.nextLine();
				toServer.writeUTF(sendMsg);
				toServer.flush();
			}
			System.out.println("This client stopped.");
		} catch (ConnectException | UnknownHostException e) {
			System.out.println("Unable to connect to server " + ipaddress + ":" + portnumber);
		} catch (IOException e) {
			System.out.println("Problem communicating with the server.");
		} finally {
			try {
				if(socket != null) {
					socket.close();
				}
				if(fromServer != null) {
					fromServer.close();
				}
				if(toServer != null) {
					toServer.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
	public static void readWelcomeMessage() throws IOException {
		System.out.println(fromServer.readUTF());
	}
	
	public static void readMainMenu() throws IOException {
		System.out.println(fromServer.readUTF());
	}
	
	public static void selectMainMenuOption() throws IOException {
		String option = scanner.nextLine();
		toServer.writeUTF(option);
		toServer.flush();
		boolean validOptionSelected = fromServer.readBoolean();
		if(validOptionSelected) {
			if(option.equals("1")) {
				System.out.println(fromServer.readUTF());
				waitingForOtherClient();
			}
			if(option.equals("2")) {
				listDownAvailableSession();
			}
		} else {
			System.out.println(fromServer.readUTF());
			readMainMenu();
			selectMainMenuOption();
		}
	}
	
	public static void waitingForOtherClient() throws IOException {
		boolean waiting = true;
		while(waiting) {
			try {
				Thread.sleep(500);
				waiting = fromServer.readBoolean();
				System.out.println(fromServer.readUTF());
			} catch (InterruptedException e) {
				System.out.println("Waiting was interrupted.");
			}
		}
		String client2HostName = fromServer.readUTF();
		String client2IPAddress = fromServer.readUTF();
		System.out.println("Client connect, say hi to " + client2HostName + " " + client2IPAddress);
	}
	
	public static void listDownAvailableSession() throws IOException {
		boolean haveAvailableSession = fromServer.readBoolean();
		if(haveAvailableSession) {
			int numberOfAvailableSession = fromServer.readInt();
			System.out.println(fromServer.readUTF());
			for(int i = 0; i < numberOfAvailableSession; i++) {
				System.out.println(fromServer.readUTF());
			}
			sendServerSelectedSession();
		}
	}

	private static void sendServerSelectedSession() throws IOException {
		int sessionSelected = 0;
		try {
			sessionSelected = Integer.parseInt(scanner.nextLine());
			toServer.writeInt(sessionSelected);
			toServer.flush();
			boolean validSelectedSession = fromServer.readBoolean();
			System.out.println(fromServer.readUTF());
			if(!validSelectedSession) {
				listDownAvailableSession();
			}
		} catch (NumberFormatException e) {
			System.out.println("Selected Session is not valid.");
			listDownAvailableSession();
		}
	}

}
