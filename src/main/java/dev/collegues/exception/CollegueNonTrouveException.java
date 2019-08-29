package dev.collegues.exception;

/**
 * Exception lancé lorsqu’on ne trouve pas un collegue
 */
public class CollegueNonTrouveException extends RuntimeException {

	public CollegueNonTrouveException() {
		super();
	}

	public CollegueNonTrouveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CollegueNonTrouveException(String message, Throwable cause) {
		super(message, cause);
	}

	public CollegueNonTrouveException(String message) {
		super(message);
	}

	public CollegueNonTrouveException(Throwable cause) {
		super(cause);
	}

}
