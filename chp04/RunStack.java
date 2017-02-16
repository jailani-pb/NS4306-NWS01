package chp04;

import java.util.Stack;

public class RunStack {

	public static void main(String[] args) {
		Stack<String> countryList = new Stack<String>();
		//Add element into Stack
		countryList.push("Brunei");
		countryList.push("Malaysia");
		countryList.push("Indonesia");
		countryList.push("Thailand");
		System.out.println("(1) " + countryList);
		
		//Retrieve and Remove element from Stack
		System.out.println("Removed: " + countryList.pop());
		System.out.println("(2) " + countryList);
		
		//Retrieve element from Stack
		System.out.println("Retrieved: " + countryList.peek());
		System.out.println("(3) " + countryList);
		
		//Retrieve and Remove all elements from Stack
		while(!countryList.isEmpty()) {
			System.out.println("Removed: " + countryList.pop());
		}
		System.out.println("(4) " + countryList);
	}

}
