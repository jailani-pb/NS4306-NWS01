package ch06chattingapplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SessionHandler implements Runnable {

	private Socket client1, client2;
	
	public SessionHandler(Socket client1, Socket client2) {
		this.client1 = client1;
		this.client2 = client2;
	}
	
	@Override
	public void run() {
		try {
			// Create data input and output streams for client1
			DataInputStream fromClient1 = new DataInputStream(
					new BufferedInputStream(client1.getInputStream()));
			DataOutputStream toClient1 = new DataOutputStream(
					new BufferedOutputStream(client1.getOutputStream()));
			// Create data input and output streams for client2
			DataInputStream fromClient2 = new DataInputStream(
					new BufferedInputStream(client2.getInputStream()));
			DataOutputStream toClient2 = new DataOutputStream(
					new BufferedOutputStream(client2.getOutputStream()));			
		
			while(true) {
				if(fromClient1.available() > 0) {
					String msg = fromClient1.readUTF();
					toClient2.writeUTF(msg);
					toClient2.flush();
				}
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

}
