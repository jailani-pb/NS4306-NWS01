package chp06chattingapplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SessionHandler implements Runnable {

	private Socket client1;
	private Socket client2;
	private int sessionid;
	private boolean waitingForClient2;
	
	public SessionHandler(int sessionid, Socket client1) {
		this.sessionid = sessionid;
		this.client1 = client1;
		waitingForClient2 = true;
	}
	
	@Override
	public void run() {
		try {
			// Create data input and output streams for client1
			DataInputStream fromClient1 = new DataInputStream(
					new BufferedInputStream(client1.getInputStream()));
			DataOutputStream toClient1 = new DataOutputStream(
					new BufferedOutputStream(client1.getOutputStream()));
			
			while(waitingForClient2) {
				try {
					Thread.sleep(1000);
					// (4)
					toClient1.writeBoolean(waitingForClient2);
					// (4-1)
					toClient1.writeUTF("Waiting for other client to connect");
					toClient1.flush();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 			
			// Create data input and output streams for client2
			DataInputStream fromClient2 = new DataInputStream(
					new BufferedInputStream(client2.getInputStream()));
			DataOutputStream toClient2 = new DataOutputStream(
					new BufferedOutputStream(client2.getOutputStream()));
			
			while(true) {
				// If any msg from client1
				if(fromClient1.available() > 0) {
					String msg = fromClient1.readUTF();
					toClient2.writeUTF(msg);
					toClient2.flush();
				}
				// If any msg from client2
				if(fromClient2.available() > 0) {
					String msg = fromClient2.readUTF();
					toClient1.writeUTF(msg);
					toClient1.flush();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSessionName() {
		return sessionid + " " + client1.getInetAddress().getHostName() 
				+ " " + client1.getInetAddress().getHostAddress();
	}

}
