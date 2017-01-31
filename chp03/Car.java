package chp03;

abstract public class Car {

	String colour;
	int noOfDoors;
	int gasMileage;
	private int currentSpeed;
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
		String msg = "I already pressed the accelerator. CurrentSpeed = " + currentSpeed;
		return msg;
	}
	
	public String pressBrake() {
		currentSpeed = 0;
		String msg = "I already pressed the brake. CurrentSpeed = " + currentSpeed;
		return msg;
	}
	
	public abstract String turnSteering();

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	//Current Speed will be between 0 to 100.
	public void setCurrentSpeed(int currentSpeed) {
		if(currentSpeed > 100) {
			this.currentSpeed = 100;
		} else {
			if(currentSpeed < 0) {
				this.currentSpeed = 0;
			} else {
				this.currentSpeed = currentSpeed;
			}
		}
	}
	
	
	
}
