package ch06chattingapplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 9102);
			
			DataInputStream fromServer = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
			DataOutputStream toServer = new DataOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			
			Scanner scanner = new Scanner(System.in);
			
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
