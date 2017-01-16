package chp02;

public class Car {

	String colour;
	int noOfDoors;
	int gasMileage;
	public int currentSpeed;
	int totalMileage;
	static int noOfCarCreated = 0;

	public Car() {
		this("Blue", 5, 0, 0, 0);
	}
	
	public Car(int gasMileage, int currentSpeed, int totalMileage) {
		this("Blue", 5, gasMileage, currentSpeed, totalMileage);
	}

	public Car(String colour, int noOfDoors, int gasMileage, int currentSpeed, int totalMileage) {
		this.colour = colour;
		this.noOfDoors = noOfDoors;
		this.gasMileage = gasMileage;
		this.currentSpeed = currentSpeed;
		this.totalMileage = totalMileage;
	}
	
	public String pressAccelerator() {
		return this.pressAccelerator(10);
	}

	public String pressAccelerator(int speed) {
		currentSpeed += speed;
		gasMileage -= 5;
		totalMileage +=5;
		String msg = "I already pressed the accelerator";
		return msg;
	}
	
	public String pressBrake() {
		currentSpeed = 0;
		String msg = "I already pressed the brake";
		return msg;
	}
}
