package chp04;

import java.util.LinkedList;
import java.util.ListIterator;

public class RunLinkedList {
	public static void main(String[] args) {
		LinkedList<String> countryList = new LinkedList<String>();
		//Add element into LinkedList
		countryList.addLast("Brunei");
		countryList.addLast("Indonesia");
		countryList.addLast("Malaysia");
		System.out.println("(1) " + countryList);
		//Add ListIterator to LinkedList
		ListIterator<String> countryIterator = countryList.listIterator();
		//Add element into LinkedList using ListIterator
		countryIterator.add("Vietnam");
		System.out.println("(2) " + countryList);
		
		System.out.println("--Moving to the left--");
		//Move ListIterator to the left
		if(countryIterator.hasPrevious()) {
			System.out.println("Traversed Element: " + countryIterator.previous());
		}
		if(countryIterator.hasPrevious()) {
			System.out.println("Traversed Element: " + countryIterator.previous());
		}
		
		System.out.println("--Moving to the right--");
		//Move ListIterator to the right end of LinkedList
		while(countryIterator.hasNext()) {
			System.out.println("Traversed Element: " + countryIterator.next());
		}
		
		//Remove element using ListIterator
		countryIterator.remove();
		System.out.println("(3) " + countryList);
		
		//Modify element using ListIterator
		while(countryIterator.hasPrevious()) {
			if(countryIterator.previous().equals("Brunei")) {
				countryIterator.set("Cambodia");
				break;
			}
		}
		System.out.println("(4) " + countryList);
	}
}
