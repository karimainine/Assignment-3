package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         DaintreeStore is main driver class that runs the application
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;

public class DaintreeStore {
    
    public static HashMap<String, User> users = new HashMap<String, User>();
    public static ArrayList<Item> items = new ArrayList<Item>();
    /**
     * 
     * @param user
     *            - current logged in user
     * @param users
     *            - list of users
     * @param items
     *            - list of items
     * 
     *            printMenu prints the menu and goes to the action methods based on the logged in
     *            user's privileges.
     */
    public static void printMenu(User user, HashMap<String, User> users, ArrayList<Item> items)
    {
        try {
            if (user.getId() != null) {
                int input = user.printMenu();
                if (user instanceof Administrator) {
                    user = adminAction(input, user, users, items);
                } else {
                    user = userAction(input, user, users, items);
                }
            } else {
                do {
                    user = User.Login(users);
                } while (user == null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            printMenu(user, users, items);
        }
    }
    
    /**
     * 
     * @param input
     *            - user's input
     * @param user
     *            - current logged in user
     * @param users
     *            - list of users
     * @param items
     *            - list of items
     * @return user only in the case of the user's details changing
     * @throws InvalidMenuOption
     * @throws UserDoesNotExist
     * @throws InvalidPassword
     * 
     *             userAction handles the actions selected by the regular users (shoppers and
     *             members)
     * 
     */
    public static User userAction(int input, User user, HashMap<String, User> users,
            ArrayList<Item> items)
            throws InvalidMenuOption, UserDoesNotExist, InvalidPassword {
        switch (input) {
        case 0: {
            System.out.println("Good Bye!");
            System.exit(0);
            break;
        }
        case 1: {
            System.out.println("Please enter item title to search for (0. To Cancel):");
            String title = User.getUserInput(false);
            if (!title.equals("0")) {
                ArrayList<Item> searchList = Item.search(title, items);
                if (searchList.size() <= 0) {
                    System.out.println("No items starting with this name found. Please try again.");
                } else {
                    System.out.println("0. Cancel");
                    System.out.println("Enter item number to add to cart:");
                    int itemNumber = User.getUserInput(0, searchList.size());
                    if (itemNumber > 0) {
                        Item item = searchList.get(itemNumber - 1);
                        Purchase.createPurchase(item, user, null);
                    }
                }
            }
            break;
        }
        case 2: {
            user.getCart().viewCart();
            break;
        }
        case 3: {
            user.getCart().removePurchase();
            break;
        }
        case 4: {
            user = user.checkOut(users);
            break;
        }
        case 5: {
            Item.listAllItems(items, user);
            break;
        }
        case 6: {
            user.printHistroy();
            break;
        }
        case 7: {
            return new User();
        }
        default: {
            throw new InvalidMenuOption();
        }
        }
        return user;
    }
    
    /**
     * 
     * @param input
     *            - user's input
     * @param user
     *            - current logged in user
     * @param users
     *            - list of users
     * @param items
     *            - list of items
     * @return user only in the case of the user's details changing
     * @throws InvalidMenuOption
     * @throws UserDoesNotExist
     * @throws InvalidPassword
     * 
     *             userAction handles the actions selected by the administrators
     * 
     */
    public static User adminAction(int input, User user, HashMap<String, User> users,
            ArrayList<Item> items)
            throws InvalidMenuOption, UserDoesNotExist, InvalidPassword {
        switch (input) {
        case 0: {
            System.out.println("Good Bye!");
            System.exit(0);
            break;
        }
        case 1: {
            Item.listAllItems(items, user);
            break;
        }
        case 2: {
            Item.listAllItems(items, user);
            System.out.println("Please select an item:");
            int selection = User.getUserInput(0, items.size());
            System.out.println("Please enter the number of copies to add (0 -"
                    + Item.MAX_NUMBER_OF_COPIES + "):");
            int copiesNumber = User.getUserInput(0, Item.MAX_NUMBER_OF_COPIES);
            if (selection > 0) {
                Item item = items.get(selection - 1);
                item.addCopies(copiesNumber);
                System.out.println("You have added " + copiesNumber
                        + (copiesNumber == 1 ? " copy" : " copies") + " to " + item.display());
            }
            break;
        }
        case 3: {
            Item.listAllItems(items, user);
            System.out.println("Please select an item:");
            int selection = User.getUserInput(0, items.size());
            if (selection > 0) {
                Item item = items.get(selection - 1);
                System.out
                        .println("Please enter the new price of the physical version (0. To Cancel):");
                double physicalPrice = User.getUserInput();
                if (item.isElectronicallyAvailable() && physicalPrice > 0) {
                    System.out
                            .println("Please enter the new price of the electronic version (0. To Cancel):");
                    double ePrice = User.getUserInput();
                    if (ePrice > 0) {
                        item.changePrice(physicalPrice, ePrice);
                        System.out.println("You have successfully changed the price of "
                                + item.display());
                    }
                } else {
                    if (physicalPrice > 0) {
                        item.changePrice(physicalPrice);
                        System.out.println("You have successfully changed the price of "
                                + item.display());
                    }
                }
            }
            break;
        }
        case 4: {
            User newUser = User.createUser(users);
            if (newUser.getId() != null) {
                users.put(newUser.getId(), newUser);
                System.out.println(newUser.getName() + " (" + newUser.getId()
                        + ") has been added successfully.");
            }
            break;
        }
        case 5: {
            return new User();
        }
        default: {
            throw new InvalidMenuOption();
        }
        }
        return user;
    }
    
    /**
     * 
     * @param args
     *            main function initializes the lists and starts the application.
     */
    public static void main(String[] args) {
        
        System.out.println("Welcome to Daintree Store");
        
        User user = new User();
        while (true) {
            printMenu(user, users, items);
        }
    }
}
