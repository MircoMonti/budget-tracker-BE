package exceptions;


public class UserNotFoundExcepion extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundExcepion(String message) {
		super(message);
	}
	
}
