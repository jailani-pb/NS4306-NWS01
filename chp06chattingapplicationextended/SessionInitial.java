package chp06chattingapplicationextended;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SessionInitial implements Runnable {
	
	private Socket client;
	private DataInputStream fromClient;
	private DataOutputStream toClient;
	
	public SessionInitial(Socket client) {
		this.client = client;
		System.out.println("Client connected: " + getClientHostName() + " " 
				+ getClientIPAddress());
	}

	@Override
	public void run() {
		try {
			fromClient = new DataInputStream(
					new BufferedInputStream(client.getInputStream()));
			toClient = new DataOutputStream(
					new BufferedOutputStream(client.getOutputStream()));
			
			sendWelcomeMessage();
			sendMainMenu();
			String option = waitForOptionFromClient();
			
			if(option.equals("1")) {
				startANewSession();
			}
			if(option.equals("2")) {
				listDownAvailableSession();
			}
		} catch (IOException e) {
			System.out.println("Communication problem with " + client.getInetAddress().getHostAddress() + " " 
					+ client.getInetAddress().getHostName() + " .");
		}
	}
	
	public void sendWelcomeMessage() throws IOException {
		toClient.writeUTF("Welcome to PB Chatting");
		toClient.flush();
	}
	
	public void sendMainMenu() throws IOException {
		String menu = "";
		menu = menu + "Please choose an option:\n";
		menu = menu + "(1) Start a new chatting session\n";
		menu = menu + "(2) Connect to available session\n";
		toClient.writeUTF(menu);
		toClient.flush();
	}
	
	public String waitForOptionFromClient() throws IOException {
		while(true) {
			if(fromClient.available() > 0) {
				String option = fromClient.readUTF();
				boolean validOptionSelected;
				if(option.equals("1") || option.equals("2")) {
					validOptionSelected = true;
					toClient.writeBoolean(validOptionSelected);
					toClient.flush();
					return option;
				} else {
					validOptionSelected = false;
					toClient.writeBoolean(validOptionSelected);
					toClient.writeUTF("Wrong option. Please select correct option.");
					toClient.flush();
					sendMainMenu();
				}
			}
		}
	}
	
	public void startANewSession() throws IOException {
		SessionHandler session = new SessionHandler(
				++ChatServer.numberOfSessionCreated, client);
		System.out.println("New session started: " + session.getSessionName());
		toClient.writeUTF("New session started: " + session.getSessionName());
		toClient.flush();
		ChatServer.availableSessions.add(session);
		session.startSession();
	}
	
	public void listDownAvailableSession() throws IOException {
		int printOptionNumber = 0;
		boolean haveAvailableSession = !ChatServer.availableSessions.isEmpty();
		toClient.writeBoolean(haveAvailableSession);
		if(haveAvailableSession) {
			toClient.writeInt(ChatServer.availableSessions.size());
			toClient.writeUTF("Please choose a session:");
			for(SessionHandler session : ChatServer.availableSessions) {
				toClient.writeUTF("(" + printOptionNumber + ") " + session.getSessionName());
				printOptionNumber++;
			}
			toClient.flush();
			waitClientChooseSession();
		} else {
			toClient.writeUTF("No available session to choose.");
			toClient.writeUTF("Do you want to start new session? (y/n)");
			toClient.flush();
			while(true) {
				if(fromClient.available() > 0) {
					String decision = fromClient.readUTF();
					if(decision.equalsIgnoreCase("y")) {
						startANewSession();
						return;
					} else {
						listDownAvailableSession();
						return;
					}
				}
			}
		}
	}
	
	private void waitClientChooseSession() throws IOException {
		while(true) {
			if(fromClient.available() > 0) {
				boolean validSelectedSession;
				int decision = fromClient.readInt();
				if(decision >= 0 && decision < ChatServer.availableSessions.size()) {
					SessionHandler session = ChatServer.availableSessions.remove(decision);
					validSelectedSession = true;
					toClient.writeBoolean(validSelectedSession);
					toClient.writeUTF("Connected to: " + session.getSessionName());
					session.connectClient2(client);
				} else {
					validSelectedSession = false;
					toClient.writeBoolean(validSelectedSession);
					toClient.writeUTF("Selected session does not exists.");
					listDownAvailableSession();
				}
				toClient.flush();
				return;
			}
		}
	}

	public String getClientHostName() {
		return client.getInetAddress().getHostName();
	}
	
	public String getClientIPAddress() {
		return client.getInetAddress().getHostAddress();
	}
	
}
