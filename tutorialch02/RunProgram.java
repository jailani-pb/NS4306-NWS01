package tutorialch02;

import java.util.ArrayList;
import java.util.Scanner;

public class RunProgram {

	static int menuLevel = 0;
	static String input = "";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//To Store dummy data
		ArrayList<Television> tvList = new ArrayList<Television>();
		ArrayList<Pendrive> pendriveList = new ArrayList<Pendrive>();
		//Generate dummy data
		for(int i = 0; i < 15; i++) {
			Pendrive pd = new Pendrive("Pendrive" + i);
			int noOfFile;
			if(i%3 == 0) {
				noOfFile = 7;
			} else {
				if(i%3 == 1) {
					noOfFile = 5;
				} else {
					noOfFile = 3;
				}
			}
			for(int j = 0; j < noOfFile; j++) {
				pd.addFile(pd.getDeviceName() + "-File" + j);
			}
			pendriveList.add(pd);
		}
		for(int i = 0; i < 10; i++) {
			Television tv = new Television("TV" + i);
			tv.connectUSBDevice(pendriveList.get(i));
			tvList.add(tv);
		}
		int tvIndex = 0;
		Television selectedTelevision = null;
		while(!input.equalsIgnoreCase("Stop")) {
			switch (menuLevel) {
			//Main menu
			case 0:
				System.out.println("\n####Television Simulation####");
				System.out.println("\nPlease choose an option:");
				System.out.println("1. Create a new Television");
				System.out.println("2. Check for existing Television");
				System.out.println("3. Stop the simulation");
				input = scanner.nextLine();
				try {
					int option = Integer.parseInt(input);
					if(option >= 1 && option <= 2) {
						menuLevel = option;
					} else {
						if(option == 3) {
							input = "Stop";
						} else {
							System.out.println("\nError: Please enter a valid option");
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("\nError: Please enter option number only.");
				}
				break;

				//Create new television
			case 1:
				System.out.println("\n####Create a new Television####");
				System.out.println("\nPlease enter the label for the new television. Type cancel to go back");
				input = scanner.nextLine();
				if(!input.equalsIgnoreCase("Cancel")) {
					tvList.add(new Television(input));
					System.out.println("A new television have been added.");
				}
				menuLevel = 0;
				break;

				//Pick existing television. After picking a television it will go to case 7.
			case 2:
				System.out.println("\n####Check for existing Television####");
				if(tvList.isEmpty()) {
					System.out.println("\nNo Television is available in this system.");
				} else {
					System.out.println("\nPlease choose an option:");
					for(int tvListIndex = 0; tvListIndex < tvList.size(); tvListIndex++) {
						System.out.println(tvListIndex + ": " + tvList.get(tvListIndex).label);
					}
					System.out.println(tvList.size() + ": Stop the simulation");
					System.out.println(tvList.size()+1 + ": Back to previous");
					System.out.println(tvList.size()+2 + ": Back to main menu");
				}
				input = scanner.nextLine();
				try {
					int option = Integer.parseInt(input);
					if(option >= 0 && option < tvList.size()) {
						tvIndex = option;
						selectedTelevision = tvList.get(option);
						menuLevel = 7;
					} else {
						if(option == tvList.size()) {
							input = "Stop";
						} else {
							if(option == tvList.size()+1 || option == tvList.size()+2) {
								menuLevel = 0;
							} else {
								System.out.println("\nError: Please enter a valid option");
							}
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("\nError: Please enter option number only.");
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
				input = scanner.nextLine();
				try {
					int option = Integer.parseInt(input);
					switch(option) {
					case 1:
						menuLevel = 4;
						break;
					case 2:
						menuLevel = 4;
						break;
					case 3:
						menuLevel = 5;
						break;
					case 4:
						menuLevel = 6;
						break;
					case 5:
						input = "Stop";
						break;
					case 6:
						menuLevel = 7;
						break;
					case 7:
						menuLevel = 0;
						break;
					default:
						System.out.println("\nError: Please enter a valid option");
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("\nError: Please enter option number only.");
				}
				break;

				//HDMI
			case 4:
				System.out.println("\nWarning: This has yet to be implemented.");
				menuLevel = 3;
				break;

				//USB
			case 5:
				System.out.println("\n####(" + tvIndex + ": " + selectedTelevision.label + ") USB input source####");
				if(selectedTelevision.isUSBConnected()) {
					System.out.println("\nPlease choose an option:");
					System.out.println("1: Remove USB");
					System.out.println("2: Check USB File List");
					System.out.println("3. Stop the simulation");
					System.out.println("4. Back to previous");
					System.out.println("5: Back to main menu");
					input = scanner.nextLine();
					try {
						int option = Integer.parseInt(input);
						switch(option) {
						case 1:
							selectedTelevision.disconnectUSBDevice();
							break;
						case 2:
							menuLevel = 9;
							break;
						case 3:
							input = "Stop";
							break;
						case 4:
							menuLevel = 3;
							break;
						case 5:
							menuLevel = 0;
							break;
						default:
							System.out.println("\nError: Please enter a valid option");
							break;
						}
					} catch (NumberFormatException e) {
						System.out.println("\nError: Please enter option number only.");
					}
				} else {
					System.out.println("\nWarning: No USB is connected. Please select USB to connect");
					ArrayList<Pendrive> tempList = new ArrayList<Pendrive>();
					Pendrive selectedPendrive = null;
					for(int i = 0; i < pendriveList.size(); i++) {
						Pendrive p = pendriveList.get(i);
						if(!p.isConnected()) {
							tempList.add(p);
						}
					}
					if(tempList.isEmpty()) {
						System.out.println("\nNo Pendrive is available.");
					} else {
						System.out.println("\nPlease choose an option:");
						for(int i = 0; i < tempList.size(); i++) {
							System.out.println(i + ": " + tempList.get(i).getDeviceName());
						}
						System.out.println(tempList.size() + ": Stop the simulation");
						System.out.println(tempList.size()+1 + ": Back to previous");
						System.out.println(tempList.size()+2 + ": Back to main menu");
					}
					input = scanner.nextLine();
					try {
						int option = Integer.parseInt(input);
						if(option >= 0 && option < tempList.size()) {
							selectedPendrive = tempList.get(option);
							selectedTelevision.connectUSBDevice(selectedPendrive);
							menuLevel = 5;
						} else {
							if(option == tempList.size()) {
								input = "Stop";
							} else {
								if(option == tempList.size()+1) {
									menuLevel = 3;
								} else {
									if(option == tempList.size()+2) {
										menuLevel = 0;
									} else {
										System.out.println("\nError: Please enter a valid option");
									}
								}
							}
						}
					} catch (NumberFormatException e) {
						System.out.println("\nError: Please enter option number only.");
					}
					menuLevel = 3;
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
				input = scanner.nextLine();
				try {
					int option = Integer.parseInt(input);
					switch(option) {
					case 1:
						menuLevel = 8;
						break;
					case 2:
						menuLevel = 3;
						break;
					case 3:
						input = "Stop";
						break;
					case 4:
						menuLevel = 2;
						break;
					case 5:
						menuLevel = 0;
						break;
					default:
						System.out.println("\nError: Please enter a valid option");
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("\nError: Please enter option number only.");
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
				input = scanner.nextLine();
				try {
					int option = Integer.parseInt(input);
					switch(option) {
					case 1:
						System.out.println("Current Volume" + selectedTelevision.increaseVolume());
						break;
					case 2:
						input = scanner.nextLine();
						int increaseValue = Integer.parseInt(input);
						System.out.println("Current Volume" + selectedTelevision.increaseVolume(increaseValue));
						break;
					case 3:
						System.out.println("Current Volume" + selectedTelevision.decreaseVolume());
						break;
					case 4:
						input = scanner.nextLine();
						int decreaseValue = Integer.parseInt(input);
						System.out.println("Current Volume" + selectedTelevision.decreaseVolume(decreaseValue));
						break;
					case 5:
						input = "Stop";
						break;
					case 6:
						menuLevel = 7;
						break;
					case 7:
						menuLevel = 0;
						break;
					default:
						System.out.println("\nError: Please enter a valid option");
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("\nError: Please enter number only.");
				}
				break;

				//Play file in usb.
			case 9:
				System.out.println("\n####(" + tvIndex + ": " + selectedTelevision.label + ") USB input source navigate file####");
				if(selectedTelevision.getFileListInUSB().isEmpty()) {
					System.out.println("\nNo file in the USB");
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
					input = scanner.nextLine();
					try {
						int option = Integer.parseInt(input);
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
							input = "Stop";
							break;
						case 6:
							menuLevel = 5;
							break;
						case 7:
							menuLevel = 0;
							break;
						default:
							System.out.println("\nError: Please enter a valid option");
							break;
						}
					} catch (NumberFormatException e) {
						System.out.println("\nError: Please enter option number only.");
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
				input = scanner.nextLine();
				try {
					int option = Integer.parseInt(input);
					if(option >= 0 && option < fileList.size()) {
						System.out.println("\nFile Played: " + selectedTelevision.getFileInUSB(option));
						menuLevel = 10;
					} else {
						if(option == fileList.size()) {
							input = "Stop";
						} else {
							if(option == fileList.size()+1) {
								menuLevel = 9;
							} else {
								if(option == fileList.size()+2) {
									menuLevel = 0;
								} else {
									System.out.println("\nError: Please enter a valid option");
								}
							}
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("\nError: Please enter option number only.");
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

}
