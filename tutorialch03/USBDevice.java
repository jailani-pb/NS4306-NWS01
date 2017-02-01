package tutorialch03;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public abstract class USBDevice extends Device {

	private final static int maxUsbVersion = 3;
	//List of files inside the USBDevice.
	private ArrayList<String> fileList;
	//The usb version of the device.
	private int usbVersion;
	
	public USBDevice(String deviceName, int usbVersion) {
		super(deviceName);
		if(usbVersion >= 1 && usbVersion <= maxUsbVersion) {
			this.usbVersion = usbVersion;
		} else {
			throw new InvalidParameterException("usbVersion must be between 1 to " + maxUsbVersion + ". Value inputted is " + usbVersion);
		}
		fileList = new ArrayList<String>();
	}
	
	public ArrayList<String> getFileList() {
		return fileList;
	}
	
	public boolean addFile(String fileName) {
		if(!fileList.contains(fileName)) {
			fileList.add(fileName);
			return true;
		} else {
			return false;
		}
	}
	
	public int getUsbVersion() {
		return usbVersion;
	}

}
