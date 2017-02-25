package chp05;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class RunBufferedStream {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please type source file:");
		String source = scanner.nextLine();
		File sourceFile = new File(source);
		while(!sourceFile.exists()) {
			System.out.println("File does not exist. Please type source file:");
			source = scanner.nextLine();
			sourceFile = new File(source);
		}
		
		System.out.println("Please type target file:");
		String target = scanner.nextLine();
		File targetFile = new File(target);
		while(targetFile.exists()) {
			System.out.println("File already exist. Please type target file:");
			target = scanner.nextLine();
			targetFile = new File(target);
		}
		
		try(
			BufferedInputStream input = new BufferedInputStream(
					new FileInputStream(sourceFile));
			BufferedOutputStream output = new BufferedOutputStream(
					new FileOutputStream(targetFile));
		) {
			int fileContent = 0;
			int numberOfBytesCopied = 0;
			while((fileContent = input.read()) != -1) {
				output.write((byte)fileContent);
				numberOfBytesCopied++;
			}
			
			System.out.println("File is copied. numberOfBytesCopied: " + numberOfBytesCopied);
		}
	}
	
}




