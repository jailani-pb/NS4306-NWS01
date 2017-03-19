package chp06chattingapplicationextended;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class SessionHandler {

	private Socket client1;
	private Socket client2;
	private int sessionid;
	private boolean waitingForClient2;
	private String client1HostName, client1IPAddress;
	private String client2HostName, client2IPAddress;
	private DataInputStream fromClient1, fromClient2;
	private DataOutputStream toClient1, toClient2;
	private boolean messageSentToClient1 = true;
	private boolean messageSentToClient2 = true;
	
	public SessionHandler(int sessionid, Socket client1) {
		this.sessionid = sessionid;
		this.client1 = client1;
		client1HostName = client1.getInetAddress().getHostName();
		client1IPAddress = client1.getInetAddress().getHostAddress();
		waitingForClient2 = true;
	}

	public void connectClient2(Socket client2) {
		this.client2 = client2;
		client2HostName = client2.getInetAddress().getHostName();
		client2IPAddress = client2.getInetAddress().getHostAddress();
		waitingForClient2 = false;
	}

	public void startSession() {
		try {
			fromClient1 = new DataInputStream(
					new BufferedInputStream(client1.getInputStream()));
			toClient1 = new DataOutputStream(
					new BufferedOutputStream(client1.getOutputStream()));

			while(waitingForClient2) {
				try {
					Thread.sleep(500);
					toClient1.writeBoolean(waitingForClient2);
					toClient1.writeUTF("Waiting for other client to connect");
					toClient1.flush();
				} catch (SocketException e) {
					System.out.println();
				} catch (InterruptedException e) {
					System.out.println(client1HostName + " " 
							+ client1IPAddress + " was waiting, but interrupted.");
				}
			}
			
			toClient1.writeUTF(client2IPAddress);
			toClient1.writeUTF(client2HostName);
			toClient1.flush();

			fromClient2 = new DataInputStream(
					new BufferedInputStream(client2.getInputStream()));
			toClient2 = new DataOutputStream(
					new BufferedOutputStream(client2.getOutputStream()));
			
			while(true) {
				if(!messageSentToClient1) {
					notifyOtherClientDisconnected(2);
					break;
				}
				if(!messageSentToClient2) {
					notifyOtherClientDisconnected(1);
					break;
				}
				if(fromClient1.available() > 0) {
					sendMsgToClient2();
				}
				if(fromClient2.available() > 0) {
					sendMsgToClient1();
				}
			}
			System.out.println(getSessionName() + " has stopped.");
		} catch (IOException e) {
			System.out.println("Communication problem in " + getSessionName());
		}
	}
	
	public void notifyOtherClientDisconnected(int notifyClient) throws IOException {
		if(notifyClient == 1) {
			toClient1.writeUTF("Client " + client2HostName 
					+ " " + client2IPAddress + " has disconnected.");
			toClient1.writeBoolean(messageSentToClient2);
			toClient1.flush();
		}
		if(notifyClient == 2) {
			toClient2.writeUTF("Client " + client1HostName
					+ " " + client1IPAddress + " has disconnected.");
			toClient2.writeBoolean(messageSentToClient1);
			toClient2.flush();
		}
	}
	
	public void sendMsgToClient1() throws IOException {
		String msg = fromClient2.readUTF();
		try {
			toClient1.writeUTF(msg);
			toClient1.writeBoolean(messageSentToClient2);
			toClient1.flush();
		} catch (SocketException e) {
			messageSentToClient1 = false;
		}
	}
	
	public void sendMsgToClient2() throws IOException {
		String msg = fromClient1.readUTF();
		try {
			toClient2.writeUTF(msg);
			toClient2.writeBoolean(messageSentToClient1);
			toClient2.flush();
		} catch (SocketException e) {
			messageSentToClient2 = false;
		}
	}

	public String getSessionName() {
		return sessionid + " " + client1HostName + " " + client1IPAddress;
	}

}
