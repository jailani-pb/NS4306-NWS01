package tutorialch02;

import java.util.ArrayList;

public class Pendrive {
	
	//Name given to the Pendrive
	private String deviceName;
	//List of files inside the Pendrive.
	private ArrayList<String> fileList;
	//State to identify the Pendrive is connected or not.
	private boolean connected;
	
	public Pendrive(String deviceName) {
		this.deviceName = deviceName;
		this.connected = false;
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
