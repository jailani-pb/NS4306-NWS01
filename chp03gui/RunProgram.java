package chp03gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RunProgram {

	public static void main(String[] args) {
		JFrame f = new JFrame();
//		JPanel p = new MainPage();
//		f.add(p);
		f.add(new MainPage());
		f.setSize(600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
}
