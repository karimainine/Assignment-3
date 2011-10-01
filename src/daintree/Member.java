package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         Member is a subclass of User. It is a special type of user with extra previlages.
 * 
 */
import java.util.ArrayList;

public class Member extends User {
    
    public final int DISCOUNT = 25;
    
    /**
     * 
     * @param id
     * @param name
     * @param email
     * @param password
     * 
     *            Member constructor used in application to create new member
     */
    public Member(String id, String name, String email, String password) {
        super(id, name, email, password);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 
     * @param id
     * @param name
     * @param email
     * @param password
     * @param purchaseHistory
     * 
     *            Member constructor used in the changing a regular user to a member (stores
     *            purchase history)
     */
    public Member(String id, String name, String email, String password,
            ArrayList<ShoppingCart> purchaseHistory) {
        super(id, name, email, password, purchaseHistory);
    }
    
}
