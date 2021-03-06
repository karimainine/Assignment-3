package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         Invalid Input is an Exception class for invalid user names entered by users
 * 
 */
public class UserDoesNotExist extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8158079038524088971L;
    
    public UserDoesNotExist() {
        // TODO Auto-generated constructor stub
    }
    
    public UserDoesNotExist(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }
    
    public UserDoesNotExist(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
    
    public UserDoesNotExist(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * message generated by the exception
     */
    public String getMessage()
    {
        return "Invalid username and/or password.";
    }
    
}
