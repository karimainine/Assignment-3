package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         Invalid Input is an Exception class for invalid passwords entered by users
 * 
 */

public class InvalidPassword extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1622993916364815556L;
    
    public InvalidPassword() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public InvalidPassword(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }
    
    public InvalidPassword(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    
    public InvalidPassword(Throwable arg0) {
        super(arg0);
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