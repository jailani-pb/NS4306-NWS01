package chp05;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

public class RunObjectInputStream {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			try(ObjectInputStream input = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream("object.dat")))) {
				while(true) {
					String name = input.readUTF();
					double mark = input.readDouble();
					Date date = (Date) input.readObject();
					System.out.println(name + " " + mark + " " + date);
				}
			}
		} catch (EOFException e) {
			System.out.println("You have reached the end of file");
		}
	}

}
