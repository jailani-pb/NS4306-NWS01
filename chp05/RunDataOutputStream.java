package chp05;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RunDataOutputStream {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try(DataOutputStream output = new DataOutputStream(new FileOutputStream("test2.dat"))) {
			output.writeUTF("Jailani");
			output.writeDouble(85.5);
			output.writeUTF("Abdul");
			output.writeDouble(65.5);
			output.writeUTF("Rahman");
			output.writeDouble(100.0);
		}
	}

}
