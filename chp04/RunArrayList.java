package chp04;

import java.util.ArrayList;

public class RunArrayList {

	public static void main(String[] args) {
		ArrayList<String> countryList = new ArrayList<String>();
		//Add element into ArrayList
		countryList.add("Brunei");
		System.out.println("(1) " + countryList);
		countryList.add("Indonesia");
		countryList.add("Malaysia");
		System.out.println("(2) " + countryList);
		//Add element into ArrayList in specific location
		countryList.add(0, "Vietnam");
		System.out.println("(3) " + countryList);
		//Removing element from ArrayList
		countryList.remove(0);
		System.out.println("(4) " + countryList);
		//Retrieving element from ArrayList
		System.out.println("Retrieving index 1: " + countryList.get(1));
	}

}
