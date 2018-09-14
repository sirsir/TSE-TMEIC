package jp.co.tmeic.mespd.exception;

public class MesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMessageKey;

	public MesException() {

	}

	public MesException(String message) {
		super(message);
	}

	public MesException(String message, String errorMessageKey) {
		super(message);
		this.errorMessageKey = errorMessageKey;
	}

	public String getErrorMessageKey() {
		return errorMessageKey;
	}
}
