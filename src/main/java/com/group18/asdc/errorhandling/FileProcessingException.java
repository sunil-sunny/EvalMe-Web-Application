package com.group18.asdc.errorhandling;

public class FileProcessingException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileProcessingException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
