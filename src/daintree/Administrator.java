package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         Administrator is a subclass of User and represents the admin user
 * 
 */
public class Administrator extends User {
    
    public final int MIN_MENU_OPTION = 0;
    public final int MAX_MENU_OPTION = 5;
    
    /**
     * 
     * @param id
     * @param name
     * @param email
     * @param password
     *            Administrator's constructor method
     */
    public Administrator(String id, String name, String email, String password) {
        super(id, name, email, password);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * printing the menu options for the administrator
     */
    public int printMenu()
    {
        System.out.println("\n1. List all items \n" +
                "2. Add copies to item \n" +
                "3. Change price of item \n" +
                "4. Add new user \n" +
                "5. Logout (change user) \n" +
                "0. Quit");
        
        System.out.println("Please select an option: \n");
        int input = User.getUserInput(this.MIN_MENU_OPTION, this.MAX_MENU_OPTION);
        return input;
    }
}
