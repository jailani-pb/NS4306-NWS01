package tutorialch02;

public class USB {

	private Pendrive fromPendrive;
	private Television toTelevision;
	private boolean deviceConnected;
	
	public USB(Pendrive fromPendrive, Television toTelevision) {
		deviceConnected = connectPendrive(fromPendrive, toTelevision);
	}
	
	public void disconnect() {
		fromPendrive.disconnectDevice();
		fromPendrive = null;
		toTelevision = null;
	}
	
	public boolean connectPendrive(Pendrive fromPendrive, Television toTelevision) {
		if(!toTelevision.isUSBConnected() && !fromPendrive.isConnected()) {
			this.fromPendrive = fromPendrive;
			fromPendrive.connectDevice();
			this.toTelevision = toTelevision;
			return true;
		} else {
			this.fromPendrive = null;
			this.toTelevision = null;
			return false;
		}
	}
	
	public boolean isDeviceConnected() {
		return deviceConnected;
	}
	
	public Pendrive getPendrive() {
		return fromPendrive;
	}
	
	public Television getTelevision() {
		return toTelevision;
	}
	
}
