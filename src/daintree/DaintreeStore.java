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
        
        // Initializing the Users
        HashMap<String, User> users = new HashMap<String, User>();
        
        User user1 = new User("lc", "Lawrence Cavedon", "lawrence@daintree.com.au", "lcpasswd");
        users.put(user1.getId(), user1);
        
        Administrator user2 = new Administrator("xla", "Xiang Li", "xiang@daintree.com.au", "xlpw");
        users.put(user2.getId(), user2);
        
        User user3 = new User("xl", "Xiang Li", "xiang@daintree.com.au", "xlpw");
        users.put(user3.getId(), user3);
        
        Member user4 = new Member("as", "Andy Song", "andy@daintree.com.au", "aspw");
        users.put(user4.getId(), user4);
        
        // Initializing the Items
        ArrayList<Item> items = new ArrayList<Item>();
        
        Book book1 = new Book("Absolute Java", "Savitch", 75.0, 15.0, 5);
        Book book2 = new Book("JAVA: How to Program", "Deitel and Deitel", 65.0, 12.0, 0);
        Book book3 = new Book("Computing Concepts with JAVA 3 Essentials", "Horstman", 114.72, 5);
        Book book4 = new Book("Java Software Solutions", "Lewis and Loftus", 80.0, 5);
        Book book5 = new Book("Java Program Design", "Cohoon and Davidson", 51.0, 10.0, 5);
        
        book2.addRecommendation(book1);
        book3.addRecommendation(book1);
        book3.addRecommendation(book2);
        
        Music music1 = new Music("At Folsom Prison", "Johnny Cash", 7.56, 10.99, 10);
        Music music2 = new Music("The Essential Johnny Cash", "Johnny Cash", 12.64, 16.99, 5);
        Music music3 = new Music("American Recordings", "Johnny Cash", 9.99, 9.99, 0);
        Music music4 = new Music("Classic Nursery Rhymes", "Hap Palmer", 13.73, 0);
        Music music5 = new Music("Classic Nursery Rhymes", "Susie Tallman", 11.47, 8.99, 1);
        
        music1.addRecommendation(music2);
        music1.addRecommendation(music3);
        music2.addRecommendation(music1);
        music2.addRecommendation(music3);
        music3.addRecommendation(music2);
        music3.addRecommendation(music1);
        music4.addRecommendation(music5);
        music5.addRecommendation(music4);
        
        items.add(book1);
        items.add(book2);
        items.add(book3);
        items.add(book4);
        items.add(book5);
        items.add(music1);
        items.add(music2);
        items.add(music3);
        items.add(music4);
        items.add(music5);
        
        System.out.println("Welcome to Daintree Store");
        
        User user = new User();
        while (true) {
            printMenu(user, users, items);
        }
    }
}
