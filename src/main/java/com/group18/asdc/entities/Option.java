package com.group18.asdc.entities;

public class Option implements IOption{

	private String displayText;
	private int storedData;

	@Override
	public String getDisplayText() {
		return displayText;
	}

	@Override
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	@Override
	public int getStoredData() {
		return storedData;
	}

	@Override
	public void setStoredData(int storedData) {
		this.storedData = storedData;
	}

	@Override
	public String toString() {
		return "Option [displayText=" + displayText + ", storedData=" + storedData + "]";
	}
}
