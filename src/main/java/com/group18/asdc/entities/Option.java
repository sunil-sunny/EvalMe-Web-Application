package com.group18.asdc.entities;

public class Option {

	private String displayText;
	private int storedData;

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public int getStoredData() {
		return storedData;
	}

	public void setStoredData(int storedData) {
		this.storedData = storedData;
	}

	@Override
	public String toString() {
		return "Option [displayText=" + displayText + ", storedData=" + storedData + "]";
	}

}
