package ru.mipt.matrix.exceptions;

public class InvalidSizeException extends Exception {
	private static final long serialVersionUID = -457583162647357003L;
		//Exception extends Throwable implements Serializable 
	public InvalidSizeException(String message) {
        super(message);
    }
}
