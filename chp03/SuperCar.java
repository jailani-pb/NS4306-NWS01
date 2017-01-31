package chp03;

public class SuperCar extends Car implements BringPassenger {

	private int noOfPassenger;
	
	public SuperCar() {
		super();
		this.noOfPassenger = 0;
	}
	
	@Override
	public String turnSteering() {
		return "SuperCar is turning";
	}

	@Override
	public String bringPassenger() {
		if(noOfPassenger == 1) {
			return "This SuperCar cannot fit more than 1 passenger. Current noOfPassenger = " + noOfPassenger;
		} else {
			noOfPassenger = 1;
			return "Welcome to my SuperCar, Passenger. Current noOfPassenger = " + noOfPassenger;
		}
	}

	@Override
	public String leavePassenger() {
		if(noOfPassenger == 0) {
			return "There is no passenger in this SuperCar. Current noOfPassenger = " + noOfPassenger;
		} else {
			noOfPassenger = 0;
			return "Sad to see you go, Passenger. Current noOfPassenger = " + noOfPassenger;
		}
	}

}
