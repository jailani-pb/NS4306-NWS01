package tutorialch03;

public class Harddrive extends USBDevice {

	private boolean solidstate;
	
	public Harddrive(String deviceName, int usbVersion, boolean solidstate) {
		super(deviceName, usbVersion);
		this.solidstate = solidstate;
	}
	
	public boolean isSolidstate() {
		return solidstate;
	}
}
