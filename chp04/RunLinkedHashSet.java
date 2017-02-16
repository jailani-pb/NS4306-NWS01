package chp04;

import java.util.LinkedHashSet;

public class RunLinkedHashSet {

	public static void main(String[] args) {
		LinkedHashSet<String> countryList = new LinkedHashSet<String>();
		//Add elements into LinkedHashSet
		countryList.add("Brunei");
		countryList.add("Malaysia");
		countryList.add("Indonesia");
		countryList.add("Vietnam");
		countryList.add("Cambodia");
		countryList.add("Vietnam");
		System.out.println("(1) " + countryList);
		//Remove element from LinkedHashSet
		countryList.remove("Indonesia");
		System.out.println("(2) " + countryList);
		//Check element if exist in LinkedHashSet
		System.out.println("Exist Brunei? " + countryList.contains("Brunei"));
		System.out.println("Exist brunei? " + countryList.contains("brunei"));
		//Retrieve all elements from LinkedHashSet
		for(String country : countryList) {
			System.out.println("Retrieved: " + country);
		}
	}

}
