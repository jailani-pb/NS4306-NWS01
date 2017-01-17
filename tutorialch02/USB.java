package tutorialch02;

public class USB {

	//The source device
	private Pendrive fromPendrive;
	//The receiving device
	private Television toTelevision;
	//State to identify if the USB has devices connected.
	private boolean deviceConnected;
	
	public USB(Pendrive fromPendrive, Television toTelevision) {
		deviceConnected = connectPendrive(fromPendrive, toTelevision);
	}
	
	public void disconnect() {
		fromPendrive.disconnectDevice();
		fromPendrive = null;
		toTelevision = null;
	}
	
	/*
	 * Connect Pendrive to Television
	 */
	public boolean connectPendrive(Pendrive fromPendrive, Television toTelevision) {
		//Make sure television does not have other usb device connected and
		//the usb device is already connected to other television
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
