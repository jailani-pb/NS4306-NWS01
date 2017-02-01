package tutorialch03;

public abstract class Device {

	//Name given to the Device
	private String deviceName;
	//State to identify the Device is connected or not.
	private boolean connected;
	
	public Device(String deviceName) {
		this.deviceName = deviceName;
		this.connected = false;
	}
	
	public String getDeviceName() {
		return deviceName;
	}

	public boolean changeDeviceName(String deviceName) {
		if(connected) {
			return false;
		} else {
			this.deviceName = deviceName;
			return true;
		}
	}
	
	public boolean isConnected() {
		return connected;
	}

	public void connectDevice() {
		this.connected = true;
	}
	
	public void disconnectDevice() {
		this.connected = false;
	}
	
}
