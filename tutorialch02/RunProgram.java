package tutorialch02;

import java.util.ArrayList;
import java.util.Scanner;

public class RunProgram {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//To Store dummy data
		ArrayList<Television> tvList = new ArrayList<Television>();
		ArrayList<Pendrive> pendriveList = new ArrayList<Pendrive>();
		
		//Generate dummy data
		
		//Create 15 pendrives
		for(int i = 0; i < 15; i++) {
			Pendrive pd = new Pendrive("Pendrive" + i);
			int noOfFile;
			//State the number of files for each pendrive
			if(i%3 == 0) {
				noOfFile = 7;
			} else {
				if(i%3 == 1) {
					noOfFile = 5;
				} else {
					noOfFile = 3;
				}
			}
			//Add the number of files for each pendrive
			for(int j = 0; j < noOfFile; j++) {
				pd.addFile(pd.getDeviceName() + "-File" + j);
			}
			pendriveList.add(pd);
		}
		//Create 10 televisions
		for(int i = 0; i < 10; i++) {
			Television tv = new Television("TV" + i);
			tv.connectUSBDevice(pendriveList.get(i));
			tvList.add(tv);
		}
		//Selected television
		Television selectedTelevision = null;
		//Selected television index
		int tvIndex = 0;
		//Identifier to stop the application
		String cont = "";
		//menuLevel is use for navigating through the menu
		int menuLevel = 0;
		while(!cont.equalsIgnoreCase("Stop")) {
			//To handle user input
			String input = "";
			int option = 0;
			
			//Application Menu
			switch (menuLevel) {
			//Main menu
			case 0:
				System.out.println("\n####Television Simulation####");
				System.out.println("\nPlease choose an option:");
				System.out.println("1. Create a new Television");
				System.out.println("2. Check for existing Television");
				System.out.println("3. Stop the simulation");
				option = chooseOption(scanner.nextLine(), 1, 3, true);
				//Go to case 1 or 2 depending what was inputted.
				if(option >= 1 && option <= 2) {
					menuLevel = option;
				}
				//Stop the application.
				if(option == 3) {
					cont = "Stop";
				}
				break;

			//Create new television
			case 1:
				System.out.println("\n####Create a new Television####");
				System.out.println("\nPlease enter the label for the new television. Type cancel to go back");
				input = scanner.nextLine();
				if(!input.equalsIgnoreCase("Cancel")) {
					//Add new television if the user input is not cancel.
					tvList.add(new Television(input));
					System.out.println("A new television have been added.");
				} else {
					//Cancel adding new television if the user input is cancel.
					System.out.println("Cancel adding new television.");
				}
				//Go back to case 0
				menuLevel = 0;
				break;

			//Pick existing television. After picking a television it will go to case 7.
			case 2:
				System.out.println("\n####Check for existing Television####");
				//Check tvList ArrayList if it is empty
				if(tvList.isEmpty()) {
					System.out.println("\nNo Television is available in this system.");
				} else {
					System.out.println("\nPlease choose an option:");
					//Show all available television inside tvList.
					for(int tvListIndex = 0; tvListIndex < tvList.size(); tvListIndex++) {
						System.out.println(tvListIndex + ": " + tvList.get(tvListIndex).label);
					}
					//Put this 3 options at the end.
					System.out.println(tvList.size() + ": Stop the simulation");
					System.out.println(tvList.size()+1 + ": Back to previous");
					System.out.println(tvList.size()+2 + ": Back to main menu");
				}
				option = chooseOption(scanner.nextLine(), 0, tvList.size()+2, false);
				if(option >= 0 && option < tvList.size()) {
					//Get the selected television index in tvList.
					tvIndex = option;
					//Get the selected television in tvList.
					selectedTelevision = tvList.get(option);
					//Go to case 7
					menuLevel = 7;
				} else {
					if(option == tvList.size()) {
						cont = "Stop";
					} else {
						if(option == tvList.size()+1 || option == tvList.size()+2) {
							menuLevel = 0;
						}
					}
				}
				break;

			//Pick input source
			case 3:
				System.out.println("\n####(" + tvIndex + ": " + selectedTelevision.label + ") Choose Television Input Source####");
				System.out.println("\nPlease choose an option:");
				System.out.println("1. HDMI1");
				System.out.println("2. HDMI2");
				System.out.println("3. USB");
				System.out.println("4. Antenna");
				System.out.println("5. Stop the simulation");
				System.out.println("6. Back to previous");
				System.out.println("7: Back to main menu");
				option = chooseOption(scanner.nextLine(), 1, 7, false);
				switch(option) {
				case 1:
					//Go to case 4 of the upper switch-case statement
					menuLevel = 4;
					break;
				case 2:
					//Go to case 4 of the upper switch-case statement
					menuLevel = 4;
					break;
				case 3:
					//Go to case 5 of the upper switch-case statement
					menuLevel = 5;
					break;
				case 4:
					//Go to case 6 of the upper switch-case statement
					menuLevel = 6;
					break;
				case 5:
					//Stop the application
					input = "Stop";
					break;
				case 6:
					//Go to case 7 of the upper switch-case statement
					menuLevel = 7;
					break;
				case 7:
					//Go to case 0 of the upper switch-case statement
					menuLevel = 0;
					break;
				default:
					break;
				}
				break;

			//HDMI
			case 4:
				System.out.println("\nWarning: This has yet to be implemented.");
				//Go to case 3
				menuLevel = 3;
				break;

			//USB
			case 5:
				System.out.println("\n####(" + tvIndex + ": " + selectedTelevision.label + ") USB input source####");
				//Check if any usb is connected
				if(selectedTelevision.isUSBConnected()) {
					System.out.println("\nPlease choose an option:");
					System.out.println("1: Remove USB");
					System.out.println("2: Check USB File List");
					System.out.println("3. Stop the simulation");
					System.out.println("4. Back to previous");
					System.out.println("5: Back to main menu");
					option = chooseOption(scanner.nextLine(), 1, 5, false);
					switch(option) {
					case 1:
						//Disconnect the usb device
						selectedTelevision.disconnectUSBDevice();
						break;
					case 2:
						//Go to case 9 of the upper switch case statement
						menuLevel = 9;
						break;
					case 3:
						//Stop the application
						cont = "Stop";
						break;
					case 4:
						//Go to case 3 of the upper switch case statement
						menuLevel = 3;
						break;
					case 5:
						//Go to case 0 of the upper switch case statement
						menuLevel = 0;
						break;
					default:
						break;
					}
				} else {
					System.out.println("\nWarning: No USB is connected. Please select USB to connect");
					//Temporary ArrayList to store all available pendrives (not connected).
					ArrayList<Pendrive> tempList = new ArrayList<Pendrive>();
					Pendrive selectedPendrive = null;
					for(int i = 0; i < pendriveList.size(); i++) {
						Pendrive p = pendriveList.get(i);
						if(!p.isConnected()) {
							tempList.add(p);
						}
					}
					//If no available pendrive
					if(tempList.isEmpty()) {
						System.out.println("\nNo Pendrive is available.");
					} else {
						//If there are available pendrives, show the pendrives.
						System.out.println("\nPlease choose an option:");
						for(int i = 0; i < tempList.size(); i++) {
							System.out.println(i + ": " + tempList.get(i).getDeviceName());
						}
						System.out.println(tempList.size() + ": Stop the simulation");
						System.out.println(tempList.size()+1 + ": Back to previous");
						System.out.println(tempList.size()+2 + ": Back to main menu");
					}
					option = chooseOption(scanner.nextLine(), 0, tempList.size()+2, false);
					if(option >= 0 && option < tempList.size()) {
						//Connect the pendrive to the television.
						selectedPendrive = tempList.get(option);
						selectedTelevision.connectUSBDevice(selectedPendrive);
						//Got to case 5.
						menuLevel = 5;
					} else {
						if(option == tempList.size()) {
							cont = "Stop";
						} else {
							if(option == tempList.size()+1) {
								menuLevel = 3;
							} else {
								if(option == tempList.size()+2) {
									menuLevel = 0;
								}
							}
						}
					}
				}
				break;

			//Antenna
			case 6:
				System.out.println("\nWarning: This has yet to be implemented.");
				menuLevel = 3;
				break;

			//Television menu
			case 7:
				System.out.println("\n####(" + tvIndex + ": " + selectedTelevision.label + ") Menu####");
				System.out.println("\nPlease choose an option:");
				System.out.println("1: Change Volume");
				System.out.println("2: Choose Input Source");
				System.out.println("3. Stop the simulation");
				System.out.println("4. Back to previous");
				System.out.println("5: Back to main menu");
				option = chooseOption(scanner.nextLine(), 1, 5, false);
				switch(option) {
				case 1:
					//Go to case 8
					menuLevel = 8;
					break;
				case 2:
					//Go to case 3
					menuLevel = 3;
					break;
				case 3:
					//Stop the application.
					cont = "Stop";
					break;
				case 4:
					//Go to case 2.
					menuLevel = 2;
					break;
				case 5:
					//Go to case 0.
					menuLevel = 0;
					break;
				default:
					break;
				}
				break;

			//Volume control.
			case 8:
				System.out.println("\n####(" + tvIndex + ": " + selectedTelevision.label + ") Volume####");
				System.out.println("1: Increase Volume");
				System.out.println("2: Increase Volume by Value");
				System.out.println("3: Decrease Volume");
				System.out.println("4: Decrease Volume by Value");
				System.out.println("5. Stop the simulation");
				System.out.println("6. Back to previous");
				System.out.println("7: Back to main menu");
				option = chooseOption(scanner.nextLine(), 1, 7, false);
				switch(option) {
				case 1:
					//Increment volume by 1
					System.out.println("Current Volume: " + selectedTelevision.increaseVolume());
					break;
				case 2:
					System.out.println("Enter value 0 to 100 to increase volume.");
					//Increment volume by value input by user
					input = scanner.nextLine();
					try {
						int increaseValue = Integer.parseInt(input);
						System.out.println("Current Volume: " + selectedTelevision.increaseVolume(increaseValue));
					} catch (NumberFormatException e){
						System.out.println("Invalid value");
					}
					break;
				case 3:
					//Decrement volume by 1
					System.out.println("Current Volume: " + selectedTelevision.decreaseVolume());
					break;
				case 4:
					System.out.println("Enter value 0 to 100 to decrease volume.");
					//Decrement volume by value input by user
					input = scanner.nextLine();
					try {
						int decreaseValue = Integer.parseInt(input);
						System.out.println("Current Volume: " + selectedTelevision.decreaseVolume(decreaseValue));
					} catch (NumberFormatException e){
						System.out.println("Invalid value");
					}
					break;
				case 5:
					//Stop the application
					cont = "Stop";
					break;
				case 6:
					//Go to case 7 of upper switch case statement.
					menuLevel = 7;
					break;
				case 7:
					//Go to case 0 of upper switch case statement.
					menuLevel = 0;
					break;
				default:
					break;
				}
				break;

			//Play file in usb.
			case 9:
				System.out.println("\n####(" + tvIndex + ": " + selectedTelevision.label + ") USB input source navigate file####");
				if(selectedTelevision.getFileListInUSB().isEmpty()) {
					System.out.println("\nNo file in the USB");
					//Go to case 5.
					menuLevel = 5;
				} else {
					System.out.println("\nPlease choose an option:");
					System.out.println("1: Next File");
					System.out.println("2: Previous File");
					System.out.println("3: Play Current File");
					System.out.println("4: Choose File");
					System.out.println("5. Stop the simulation");
					System.out.println("6. Back to previous");
					System.out.println("7: Back to main menu");
					option = chooseOption(scanner.nextLine(), 1, 7, false);
					switch(option) {
					case 1:
						System.out.println("\nFile Played: " + selectedTelevision.nextFileInUSB());
						break;
					case 2:
						System.out.println("\nFile Played: " + selectedTelevision.previousFileInUSB());
						break;
					case 3:
						System.out.println("\nFile Played: " + selectedTelevision.currentFileInUSB());
						break;
					case 4:
						menuLevel = 10;
						break;
					case 5:
						cont = "Stop";
						break;
					case 6:
						menuLevel = 5;
						break;
					case 7:
						menuLevel = 0;
						break;
					default:
						break;
					}
				}
				break;

				//Choose specific file in usb.
			case 10:
				System.out.println("\n####(" + tvIndex + ": " + selectedTelevision.label + ") USB input source choose file####");
				ArrayList<String> fileList = selectedTelevision.getFileListInUSB();
				System.out.println("\nPlease choose a file from this USB");
				for(int fileIndex = 0; fileIndex < fileList.size(); fileIndex++) {
					System.out.println(fileIndex + ": " + selectedTelevision.getFileInUSB(fileIndex));
				}
				System.out.println(fileList.size() + ": Stop the simulation");
				System.out.println(fileList.size()+1 + ": Back to previous");
				System.out.println(fileList.size()+2 + ": Back to main menu");						
				option = chooseOption(scanner.nextLine(), 0, fileList.size()+2, false);
				if(option >= 0 && option < fileList.size()) {
					System.out.println("\nFile Played: " + selectedTelevision.getFileInUSB(option));
					menuLevel = 10;
				} else {
					if(option == fileList.size()) {
						cont = "Stop";
					} else {
						if(option == fileList.size()+1) {
							menuLevel = 9;
						} else {
							if(option == fileList.size()+2) {
								menuLevel = 0;
							}
						}
					}
				}
				break;				

			default:
				System.out.println("\nEnter to go back to main menu. Type Stop to exit the program");
				input = scanner.nextLine();
				menuLevel = 0;
				break;
			}
		}
	}
	
	/*
	 * This method to get the user input and make sure the input is in Integer and between
	 * minimum option available to maximum option available. It will display differently
	 * if the menu option is the main menu and if the menu option is not the main menu.
	 */
	public static int chooseOption(String input, int minOption, int maxOption, boolean mainMenu) {
		try {
			//Parse user input to Integer
			int option = Integer.parseInt(input);
			//Number of addition value available in option.
			//0 means 1 additional value.
			//2 means 3 additional value
			int additionalMenu = 0;
			if(mainMenu) {
				additionalMenu = 0;
			} else {
				additionalMenu = 2;
			}
			if(option >= minOption && option < maxOption-additionalMenu) {
				return option;
			} else {
				//Additional Menu
				if(option == maxOption-additionalMenu) {
					return option;
				} else {
					//Will not be shown in Main menu
					if(option == maxOption-(additionalMenu-1) && !mainMenu) {
						return option;
					} else {
						if(option == maxOption-(additionalMenu-2) && !mainMenu) {
							return option;
						} else {
							//If the user input option that is not available.
							System.out.println("\nError: Please enter a valid option");
							return -1;
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			//If the user input other than number.
			System.out.println("\nError: Please enter option number only.");
			return -1;
		}
	}

}
