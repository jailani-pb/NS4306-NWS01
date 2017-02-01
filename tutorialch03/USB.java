package tutorialch03;

public class USB {

	//The source device
	private USBDevice fromUSBDevice;
	//The receiving device
	private Television toTelevision;
	//State to identify if the USB has devices connected.
	private boolean deviceConnected;
	
	public USB(USBDevice fromUSBDevice, Television toTelevision) {
		deviceConnected = connectUsbDevice(fromUSBDevice, toTelevision);
	}
	
	public void disconnect() {
		fromUSBDevice.disconnectDevice();
		fromUSBDevice = null;
		toTelevision = null;
	}
	
	/*
	 * Connect UsbDevice to Television
	 */
	public boolean connectUsbDevice(USBDevice fromUSBDevice, Television toTelevision) {
		//Make sure television does not have other usb device connected and
		//the usb device is already connected to other television
		if(!toTelevision.isUSBConnected() && !fromUSBDevice.isConnected()) {
			this.fromUSBDevice = fromUSBDevice;
			fromUSBDevice.connectDevice();
			this.toTelevision = toTelevision;
			return true;
		} else {
			this.fromUSBDevice = null;
			this.toTelevision = null;
			return false;
		}
	}
	
	public boolean isDeviceConnected() {
		return deviceConnected;
	}
	
	public USBDevice getUSBDevice() {
		return fromUSBDevice;
	}
	
	public Television getTelevision() {
		return toTelevision;
	}
	
}
