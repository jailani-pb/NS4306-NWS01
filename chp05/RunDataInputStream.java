package chp05;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RunDataInputStream {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try {
			try(DataInputStream input = new DataInputStream(new FileInputStream("test2.dat"))) {
//				System.out.println(input.readUTF() + " " + input.readDouble());
//				System.out.println(input.readUTF() + " " + input.readDouble());
//				System.out.println(input.readUTF() + " " + input.readDouble());
//				System.out.println(input.readUTF() + " " + input.readDouble());
//				while(true) {
//					System.out.println(input.readUTF() + " " + input.readDouble());
//				}
				while(input.available() != 0) {
					System.out.println(input.readUTF() + " " + input.readDouble());
				}
			}
		} catch (EOFException e) {
			System.out.println("It has reached the end of file.");
		}
	}

}
