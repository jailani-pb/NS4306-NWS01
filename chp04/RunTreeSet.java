package chp04;

import java.util.TreeSet;

public class RunTreeSet {

	public static void main(String[] args) {
		TreeSet<String> countryList = new TreeSet<String>();
		//Add elements into TreeSet
		countryList.add("Brunei");
		countryList.add("Malaysia");
		countryList.add("Indonesia");
		countryList.add("Vietnam");
		countryList.add("Cambodia");
		countryList.add("Vietnam");
		System.out.println("(1) " + countryList);
		//Remove element from TreeSet
		countryList.remove("Indonesia");
		System.out.println("(2) " + countryList);
		//Check element if exist in TreeSet
		System.out.println("Exist Brunei? " + countryList.contains("Brunei"));
		System.out.println("Exist brunei? " + countryList.contains("brunei"));
		//Retrieve all elements from TreeSet
		for(String country : countryList) {
			System.out.println("Retrieved: " + country);
		}
	}

}
