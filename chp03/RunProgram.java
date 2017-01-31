package chp03;

public class RunProgram {

	public static void main(String[] args) {
		System.out.println("##Truck##");
		Car car1 = new Truck("Beep!", 4, 100);
		System.out.println(car1.colour);
		System.out.println(((Truck) car1).pressHorn());
		System.out.println(car1.pressAccelerator(30));
		System.out.println(car1.pressBrake());
		System.out.println(car1.pressBrake());
		System.out.println(car1.pressBrake());
		System.out.println(car1.pressBrake());
		System.out.println(car1.pressBrake());
		
		System.out.println("##SuperCar##");
		car1 = new SuperCar();
		System.out.println(car1.pressAccelerator(100));
		System.out.println(car1.pressBrake());
	}
	
}
