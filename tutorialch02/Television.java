package tutorialch02;

import java.util.ArrayList;

public class Television {

	public boolean on;
	public String label;
	private USB usb;
	private int fileListIndex;
	private int currentVolume;
	
	public Television(String label) {
		usb = null;
		this.label = label;
		this.on = true;
		currentVolume = 0;
		fileListIndex = 0;
	}
	
	public boolean isUSBConnected() {
		if(usb == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean connectUSBDevice(Pendrive pendrive) {
		if(!isUSBConnected()) {
			fileListIndex = 0;
			usb = new USB(pendrive, this);
			return true;
		} else {
			return false;
		}
	}
	
	public String currentFileInUSB() {
		return getFileInUSB(fileListIndex);
	}
	
	public String nextFileInUSB() {
		if(on) {
			fileListIndex++;
		}
		return getFileInUSB(fileListIndex);
	}
	
	public String previousFileInUSB() {
		if(on) {
			fileListIndex--;
		}
		return getFileInUSB(fileListIndex);
	}
	
	public ArrayList<String> getFileListInUSB() {
		return usb.getPendrive().getFileList();
	}
	
	public String getFileInUSB(int index) {
		if(usb != null && on) {
			ArrayList<String> fileList = usb.getPendrive().getFileList();
			if(index < 0) {
				index = 0;
			}
			if(index >= fileList.size()) {
				index = fileList.size()-1;
			}
			fileListIndex = index;
			return fileList.get(index);
		} else {
			return null;
		}
	}
	
	public void disconnectUSBDevice() {
		usb.getPendrive().disconnectDevice();
		usb = null;
		fileListIndex = 0;
	}
	
	public int increaseVolume() {
		return increaseVolume(1);
	}
	
	public int increaseVolume(int value) {
		return changeVolume(value, true);
	}
	
	public int decreaseVolume() {
		return decreaseVolume(1);
	}
	
	public int decreaseVolume(int value) {
		return changeVolume(value, false);
	}
	
	private int changeVolume(int value, boolean increment) {
		if(on) {
			if(increment) {
				currentVolume += value;
				if(currentVolume > 100) {
					currentVolume = 100;
				}
			} else {
				currentVolume -= value;
				if(currentVolume < 0) {
					currentVolume = 0;
				}
			}
			return currentVolume;
		} else {
			return 0;
		}
	}
	
}
