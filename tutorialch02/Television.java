package tutorialch02;

import java.util.ArrayList;

public class Television {

	//State to identify if the Television is switched on or off
	public boolean on;
	//The label given to the Television.
	public String label;
	//The USB slot for the Television.
	private USB usb;
	//To store the state on which file the Television is currently reading in USB.
	private int fileListIndex;
	//To store the state on what current volume is the Television.
	private int currentVolume;
	
	/*
	 * The television object will have its own label with no devices currently connected
	 * to the television's usb, television switched on, currentVolume and fileListIndex is zero.
	 */
	public Television(String label) {
		usb = null;
		this.label = label;
		this.on = true;
		currentVolume = 0;
		fileListIndex = 0;
	}
	
	/*
	 * Check if there is a device that is connected to the usb.
	 */
	public boolean isUSBConnected() {
		if(usb == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * Connect Pendrive using USB object.
	 */
	public boolean connectUSBDevice(Pendrive pendrive) {
		if(!isUSBConnected()) {
			fileListIndex = 0;
			usb = new USB(pendrive, this);
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Get the current file being read.
	 */
	public String currentFileInUSB() {
		return getFileInUSB(fileListIndex);
	}
	
	/*
	 * Get the next file in usb.
	 */
	public String nextFileInUSB() {
		if(on) {
			fileListIndex++;
		}
		return getFileInUSB(fileListIndex);
	}
	
	/*
	 * Get the previous file in usb.
	 */
	public String previousFileInUSB() {
		if(on) {
			fileListIndex--;
		}
		return getFileInUSB(fileListIndex);
	}
	
	/*
	 * Get all the files in the usb.
	 */
	public ArrayList<String> getFileListInUSB() {
		return usb.getPendrive().getFileList();
	}
	
	/*
	 * Allow user to get a file directly.
	 */
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
	
	/*
	 * Disconnect the usb from the television.
	 */
	public void disconnectUSBDevice() {
		usb.getPendrive().disconnectDevice();
		usb = null;
		fileListIndex = 0;
	}
	
	/*
	 * Increment volume by 1
	 */
	public int increaseVolume() {
		return increaseVolume(1);
	}
	
	/*
	 * Increment volume by value
	 */
	public int increaseVolume(int value) {
		return changeVolume(value, true);
	}
	
	/*
	 * decrement volume by 1
	 */
	public int decreaseVolume() {
		return decreaseVolume(1);
	}
	
	/*
	 * decrement volume by value.
	 */
	public int decreaseVolume(int value) {
		return changeVolume(value, false);
	}
	
	/*
	 * This is used for increaseVolume and decreaseVolume methods.
	 */
	private int changeVolume(int value, boolean increment) {
		if(on) {
			if(value >= 0 && value <= 100) {
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
			}
			return currentVolume;
		} else {
			return 0;
		}
	}
	
}
