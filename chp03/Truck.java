package chp03;

public class Truck extends Car implements BringPassenger, ItemLoading {
	
	String hornSound;
	private int loadCapacity;
	private int maxCapacity;
	private int noOfPassenger;
	private int maxPassenger;
	
	public Truck(String hornSound, int maxCapacity, int maxPassenger) {
		super();
		this.hornSound = hornSound;
		this.maxCapacity = maxCapacity;
		this.maxPassenger = maxPassenger;
		this.noOfPassenger = 0;
		this.loadCapacity = 0;
	}
	
	public Truck(String colour, int noOfDoors, int gasMileage, int currentSpeed, int totalMileage, String hornSound, int maxCapacity, int maxPassenger) {
		super(colour, noOfDoors, gasMileage, currentSpeed, totalMileage);
		this.hornSound = hornSound;
		this.maxCapacity = maxCapacity;
		this.maxPassenger = maxPassenger;
		this.noOfPassenger = 0;
		this.loadCapacity = 0;
	}

	public String pressHorn() {
		return "I have pressed the Horn. " + hornSound;
	}
	
	@Override
	public String pressBrake() {
		int currentSpeed = this.getCurrentSpeed();
		currentSpeed -= 10;
		this.setCurrentSpeed(currentSpeed);
		String msg = "I already pressed the brake. CurrentSpeed = " + this.getCurrentSpeed();
		return msg;
	}
	
	@Override
	public String pressAccelerator(int speed) {
		return super.pressAccelerator(speed) + " <- I am the Subclass";
	}

	@Override
	public String turnSteering() {
		return "Truck is turning";
	}

	@Override
	public String loadItem(int weight) {
		if((loadCapacity+weight) > maxCapacity) {
			return "The Truck cannot fit more than " + maxCapacity + " items weight. Current loadCapacity = " + loadCapacity;
		} else {
			loadCapacity += weight;
			return "The Truck have loaded item with weight = " + weight + ". Current loadCapacity = " + loadCapacity;
		}
	}

	@Override
	public String unloadItem(int weight) {
		if((loadCapacity-weight) < 0) {
			return "The Truck cannot unload " + weight + " items weight. Current loadCapacity = " + loadCapacity;
		} else {
			loadCapacity -= weight;
			return "The Truck have unloaded item with weight = " + weight + ". Current loadCapacity = " + loadCapacity;
		}
	}

	@Override
	public String bringPassenger() {
		if(noOfPassenger >= maxPassenger) {
			return "This Truck cannot fit more than " + maxPassenger + " passenger. Current noOfPassenger = " + noOfPassenger;
		} else {
			noOfPassenger++;
			return "Welcome to my Truck, Passenger. Current noOfPassenger = " + noOfPassenger;
		}
	}

	@Override
	public String leavePassenger() {
		if(noOfPassenger <= 0) {
			return "There is no passenger in this Truck. Current noOfPassenger = " + noOfPassenger;
		} else {
			noOfPassenger--;
			return "Sad to see you go, Passenger. Current noOfPassenger = " + noOfPassenger;
		}
	}

}
