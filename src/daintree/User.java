package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         User class is used for both the regular user (not member and not Admin) and also as a
 *         parent class for the Members and Admins.
 * 
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class User {
    private String                  id;
    private String                  name;
    private String                  email;
    private String                  password;
    private ShoppingCart            cart                        = new ShoppingCart();
    private ArrayList<ShoppingCart> purchaseHistory             = new ArrayList<ShoppingCart>();
    
    public final int                MIN_MENU_OPTION             = 0;
    public final int                MAX_MENU_OPTION             = 7;
    public final int                MAX_PURCHASES_TOTAL         = 100;
    public final int                MEMBERSHIP_TOTAL_PERCENTAGE = 10;
    public final int                DISCOUNT                    = 15;
    
    /**
     * Empty User constructor
     */
    public User() {
    }
    
    /**
     * 
     * @param id
     * @param name
     * @param email
     * @param password
     * @param purchaseHistory
     *            User constructor used to change a regular user to a member
     */
    public User(String id, String name, String email, String password,
            ArrayList<ShoppingCart> purchaseHistory) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.purchaseHistory = purchaseHistory;
    }
    
    /**
     * 
     * @param id
     * @param name
     * @param email
     * @param password
     *            User constructor
     */
    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    /**
     * @return the purchaseHistory
     */
    public ArrayList<ShoppingCart> getPurchaseHistory() {
        return purchaseHistory;
    }
    
    /**
     * @return the cart
     */
    public ShoppingCart getCart() {
        return cart;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * 
     * @param min
     *            - minimum value to be entered by the user
     * @param max
     *            - maximum value to be entered by the user
     * @return integer value entered by the user
     * 
     *         getUserInput - gets the integer values entered by the user and
     *         checks that the input doesn't throw an exception and is within a
     *         certain range. Otherwise, it allows the user to try again.
     */
    public static int getUserInput(int min, int max) {
        // Initializing the keyboard instance
        Scanner keyboard = new Scanner(System.in);
        // result will store the integer returned by the user
        int result;
        
        try {
            // Getting the user's input
            result = keyboard.nextInt();
            // Checking if the input is within the range specified for the menus
            if (result > max || result < min) {
                // Informing the user to try again
                System.out.println("Invalid input. Please Try Again:");
                // Setting result to the user's input
                result = getUserInput(min, max);
            }
        } catch (Exception e) {
            // Informing the user to try again
            System.out.println("Invalid input. Please Try Again:");
            // Setting result to the user's input
            result = getUserInput(min, max);
        }
        return result;
    }
    
    /**
     * 
     * @param isYesNo
     * @return
     *         getUserInput - if isYesNo is true the method checks if the input is either 'y' or
     *         'n'. Otherwise, it returns the String inputted by the user
     */
    public static String getUserInput(boolean isYesNo) {
        // Initializing the keyboard instance
        Scanner keyboard = new Scanner(System.in);
        // result will store the String returned by the user
        String result;
        
        try {
            // Getting the user's input
            result = keyboard.nextLine();
            // Checking if the input should be a (y/n) option or not and whether it is valid
            if (isYesNo && (!result.toLowerCase().equals("y") && !result.toLowerCase().equals("n"))) {
                // Informing the user to try again
                System.out.println("Invalid input. Please Try Again:");
                // Setting result to the user's input
                result = getUserInput(isYesNo);
            }
        } catch (Exception e) {
            // Informing the user to try again
            System.out.println("Invalid input. Please Try Again:");
            // Setting result to the user's input
            result = getUserInput(isYesNo);
        }
        return result.toLowerCase();
    }
    
    /**
     * 
     * @return the role inputed by the admin user when creating a new user
     */
    public static String getRole() {
        // Initializing the keyboard instance
        Scanner keyboard = new Scanner(System.in);
        // result will store the String returned by the user
        String result;
        
        try {
            // Getting the user's input
            result = keyboard.next();
            if ((!result.toLowerCase().equals("admin") && !result.toLowerCase().equals("buyer"))) {
                // Informing the user to try again
                System.out.println("Invalid input. Please Try Again:");
                // Setting result to the user's input
                result = getRole();
            }
        } catch (Exception e) {
            // Informing the user to try again
            System.out.println("Invalid input. Please Try Again:");
            // Setting result to the user's input
            result = getRole();
        }
        return result.toLowerCase();
    }
    
    /**
     * 
     * @return double inputed by the admin when changing an item's price
     */
    public static double getUserInput() {
        // Initializing the keyboard instance
        Scanner keyboard = new Scanner(System.in);
        // result will store the String returned by the user
        double result;
        
        try {
            // Getting the user's input
            result = keyboard.nextDouble();
        } catch (Exception e) {
            // Informing the user to try again
            System.out.println("Invalid input. Please Try Again:");
            // Setting result to the user's input
            result = getUserInput();
        }
        return result;
    }
    
    /**
     * 
     * @param users
     * @return
     * @throws InvalidPassword
     * @throws UserDoesNotExist
     * 
     *             Login function for all users
     */
    public static User Login(HashMap<String, User> users) throws InvalidPassword, UserDoesNotExist
    {
        User user = new User();
        do {
            System.out.println("Please Enter Your User ID:\n");
            String username = getUserInput(false);
            System.out.println("Please Enter Your Password:\n");
            String password = getUserInput(false);
            
            if (users.containsKey(username))
            {
                user = users.get(username);
                if (user.getPassword().equals(password))
                {
                    System.out.println("Welcome " + user.getName() + ": \n");
                    return user;
                }
                else {
                    throw new InvalidPassword();
                }
            }
            else
            {
                throw new UserDoesNotExist();
            }
        } while (user.getId() == null);
    }
    
    /**
     * printing the regular user's (including members) menu
     * 
     * @return
     */
    public int printMenu()
    {
        System.out.println("\n1. Add item to shopping cart \n" +
                "2. View shopping cart \n" +
                "3. Remove item from shopping cart \n" +
                "4. Checkout \n" +
                "5. List all items \n" +
                "6. Print previous purchases \n" +
                "7. Logout (change user) \n" +
                "0. Quit");
        
        System.out.println("Please select an option: \n");
        int input = User.getUserInput(this.MIN_MENU_OPTION, this.MAX_MENU_OPTION);
        return input;
    }
    
    /**
     * 
     * @return remaining amount a regular user can still shop with
     */
    public double remainingPurchaseAmount() {
        ArrayList<Purchase> purchases = getCart().getPurchases();
        double totalPrice = 0;
        for (Purchase purchase : purchases) {
            totalPrice += purchase.getFinalPrice();
        }
        return MAX_PURCHASES_TOTAL - totalPrice;
    }
    
    /**
     * 
     * @return user (mainly because a user could become a member after checking out)
     *         Checkout clears the shopping cart and checks if the user is eligible to become a
     *         member
     */
    public User checkOut(HashMap<String, User> users) {
        User user = this;
        ShoppingCart currentCart = new ShoppingCart();
        if (cart.getPurchases().size() > 0) {
            for (Purchase purchase : cart.getPurchases()) {
                currentCart.addToCart(purchase, user);
            }
            purchaseHistory.add(currentCart);
            System.out.println("You have purchased the following items successfully:\n");
            cart.viewCart();
            System.out
                    .print("----------------------------------------------------------------------------------------\n");
            DecimalFormat formatter = new DecimalFormat("#.##");
            System.out.println("Total: " + formatter.format(currentCart.returnTotal()));
            cart.getPurchases().clear();
            
            double totalPurchases = 0;
            for (ShoppingCart histroyCart : purchaseHistory) {
                totalPurchases += histroyCart.returnTotal();
            }
            
            if ((!(this instanceof Member))
                    && (totalPurchases >= (MAX_PURCHASES_TOTAL * (100 + MEMBERSHIP_TOTAL_PERCENTAGE)) / 100)) {
                System.out
                        .println("You qualify to become a member of Daintree Store. Would you like become a member?(y/n)");
                String userInput = getUserInput(true);
                if (userInput.equals("y")) {
                    // Convert to Member
                    users.remove(user.getId());
                    user = new Member(user.getId(), user.getName(), user.getEmail(),
                            user.getPassword(), user.getPurchaseHistory());
                    users.put(user.getId(), user);
                    System.out.println("You have successfully become a member.");
                } else {
                    System.out.println("You have chosen not to become a member.");
                }
            }
        } else {
            System.out.println("Your cart is empty.");
        }
        
        return user;
    }
    
    /**
     * printing a user purchase history
     */
    public void printHistroy() {
        if (purchaseHistory.size() <= 0) {
            System.out.println("You do not have any purchase history.");
        } else {
            int index = 1;
            for (ShoppingCart transaction : purchaseHistory) {
                System.out.println("Transaction " + index
                        + ".\n");
                transaction.viewCart();
                System.out
                        .print("----------------------------------------------------------------------------------------\n");
                index++;
            }
        }
    }
    
    /**
     * 
     * @param users
     * @return
     * 
     *         creating a new user (admin only)
     */
    public static User createUser(HashMap<String, User> users) {
        User user = new User();
        System.out.println("Please fill in the following information:");
        System.out.println("Role (Admin/Buyer):");
        String role = getRole();
        System.out.println("Full Name:");
        String name = getUserInput(false);
        System.out.println("E-mail:");
        String email = getUserInput(false);
        System.out.println("Username:");
        boolean isUnique = true;
        String username = "";
        do {
            if (!isUnique) {
                System.out.println("Username already exists. Please enter another one:");
            }
            username = getUserInput(false);
            isUnique = (!users.containsKey(username));
        } while (!isUnique);
        System.out.println("Password:");
        String password = getUserInput(false);
        
        if (role.equals("admin")) {
            user = new Administrator(username, name, email, password);
        } else {
            user = new User(username, name, email, password);
        }
        return user;
    }
}
