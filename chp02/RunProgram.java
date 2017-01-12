package chp02;

public class RunProgram {

	public static void main(String[] args) {
		Car firstCar = new Car();
		firstCar.colour = "Blue";
		firstCar.noOfDoors = 5;
		firstCar.gasMileage = 500;
		firstCar.currentSpeed = 0;
		firstCar.totalMileage = 100;
		
		System.out.println("Initial speed of firstCar");
		System.out.println(firstCar.currentSpeed);
		
		System.out.println("Pressing firstCar accelerator");
		firstCar.pressAccelerator(20);
		System.out.println(firstCar.currentSpeed);
		
		System.out.println("Pressing firstCar accelerator");
		firstCar.pressAccelerator(10);
		System.out.println(firstCar.currentSpeed);
		
		System.out.println("Pressing firstCar brake");
		firstCar.pressBrake();
		System.out.println("Current Speed: " + firstCar.currentSpeed);
		System.out.println("Current Gas Mileage " + firstCar.gasMileage);
		System.out.println("Current Total Mileage " + firstCar.totalMileage);
		
		Car secondCar = new Car();
		secondCar.colour = "White";
		secondCar.noOfDoors = 5;
		secondCar.currentSpeed = 0;
		secondCar.gasMileage = 200;
		secondCar.totalMileage = 10000;
		
		System.out.println("Initial speed of secondCar");
		System.out.println(secondCar.currentSpeed);
		
		System.out.println("Pressing secondCar accelerator");
		secondCar.pressAccelerator(30);
		
		System.out.println("firstCar Speed " + firstCar.currentSpeed);
		System.out.println("secondCar Speed " + secondCar.currentSpeed);
		
		secondCar.noOfCarCreated = 2;
		System.out.println("No of Car Object Created");
		System.out.println("noOfCarCreated variable in firstCar: " 
				+ firstCar.noOfCarCreated);
		System.out.println("noOfCarCreated variable in secondCar: " 
				+ secondCar.noOfCarCreated);
	}
	
}
