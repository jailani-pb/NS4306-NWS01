package chp04;

import java.util.HashSet;

public class RunHashSet {

	public static void main(String[] args) {
		HashSet<String> countryList = new HashSet<String>();
		//Add elements into HashSet
		countryList.add("Brunei");
		countryList.add("Malaysia");
		countryList.add("Indonesia");
		countryList.add("Vietnam");
		countryList.add("Cambodia");
		countryList.add("Vietnam");
		System.out.println("(1) " + countryList);
		//Remove element from HashSet
		countryList.remove("Indonesia");
		System.out.println("(2) " + countryList);
		//Check element if exist in HashSet
		System.out.println("Exist Brunei? " + countryList.contains("Brunei"));
		System.out.println("Exist brunei? " + countryList.contains("brunei"));
		//Retrieve all elements from HashSet
		for(String country : countryList) {
			System.out.println("Retrieved: " + country);
		}
	}

}
