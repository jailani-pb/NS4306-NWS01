package chp04;

import java.util.LinkedList;
import java.util.Queue;

public class RunQueue {

	public static void main(String[] args) {
		Queue<String> countryList = new LinkedList<String>();
		//Add element into Queue
		countryList.offer("Brunei");
		countryList.offer("Malaysia");
		countryList.offer("Indonesia");
		countryList.offer("Thailand");
		System.out.println("(1) " + countryList);
		
		//Retrieve and remove element from Queue
		System.out.println("Removed: " + countryList.poll());
		System.out.println("(2) " + countryList);
		
		//Retrieve element from Queue
		System.out.println("Retrieved: " + countryList.peek());
		System.out.println("(3) " + countryList);		
		
		//Retrieve and remove all elements from Queue
		while(!countryList.isEmpty()) {
			System.out.println("Removed: " + countryList.poll());
		}
		System.out.println("(4) " + countryList);
	}

}
