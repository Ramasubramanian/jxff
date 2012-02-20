package in.jxff.filter.exception;

/**
 * Custom unchecked exception thrown when a Folder criteria is applied a file
 * and vice versa
 * @author raam
 *
 */
public class UnsupportedCriteriaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedCriteriaException() {
		super();
	}

	public UnsupportedCriteriaException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedCriteriaException(String message) {
		super(message);
	}

	public UnsupportedCriteriaException(Throwable cause) {
		super(cause);
	}

	
	
}
