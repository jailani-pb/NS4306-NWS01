package chp05;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RunFileOutputStream {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try(FileOutputStream output = new FileOutputStream("test.dat")) {
			for(int i = 1; i <= 10; i++) {
				output.write(i);
			}
		}
	}

}
