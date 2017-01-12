package chp02;

public class Car {

	String colour;
	int noOfDoors;
	int gasMileage;
	int currentSpeed;
	int totalMileage;
	static int noOfCarCreated = 0;
	
	public void pressAccelerator(int speed) {
		String msg = "Ku takan jua ni";
		System.out.println(msg);
		currentSpeed += speed;
		gasMileage -= 5;
		totalMileage +=5;
	}
	
	public void pressBrake() {
		currentSpeed = 0;
	}
}
