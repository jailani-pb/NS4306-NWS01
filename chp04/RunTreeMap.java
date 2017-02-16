package chp04;

import java.util.TreeMap;

public class RunTreeMap {

	public static void main(String[] args) {
		TreeMap<String, String> capitalList = new TreeMap<String, String>();
		//Add element into TreeMap
		capitalList.put("Brunei", "Bandar Seri Begawan");
		capitalList.put("Singapore", "Singapore City");
		capitalList.put("Indonesia", "Jakarta");
		capitalList.put("Malaysia", "Kuala Lumpur");
		capitalList.put("Thailand", "Bangkok");
		capitalList.put("Brunei", "Bangar");
		System.out.println("(1) " + capitalList);
		//Remove element from TreeMap
		System.out.println("Removed: " + capitalList.remove("Brunei"));
		System.out.println("(2) " + capitalList);
		//Retrieve element from TreeMap
		System.out.println("Retrieved: " + capitalList.get("Thailand"));
		//Retrieve all keys from TreeMap
		System.out.println("Available Keys: " + capitalList.keySet());
		//Retrieve all values from TreeMap
		System.out.println("Available Values: " + capitalList.values());

		for(String country : capitalList.keySet()) {
			System.out.println("Country: " + country
					+ ", Capital: " + capitalList.get(country));
		}
	}

}
