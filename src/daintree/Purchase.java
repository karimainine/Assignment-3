package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         Purchase class represents purchases that are added to the shopping cart. A purchase
 *         contains the item, whether it is electronic or not, its recommendation parent (if any)
 *         and the final price (could be discounted)
 * 
 */

import java.text.DecimalFormat;

public class Purchase {
    private Item    item;
    private boolean isElectronic;
    private Item    recommendedBy;
    private double  finalPrice;
    
    /**
     * 
     * @param item
     * @param isElectronic
     * @param finalPrice
     *            Purchase constructor for regular purchase
     */
    public Purchase(Item item, boolean isElectronic, double finalPrice) {
        this.item = item;
        this.isElectronic = isElectronic;
        this.finalPrice = finalPrice;
    }
    
    /**
     * 
     * @param item
     * @param isElectronic
     * @param finalPrice
     * @param recommendedBy
     *            Purchase constructor for a recommended purchase
     */
    public Purchase(Item item, boolean isElectronic, double finalPrice,
            Item recommendedBy) {
        this(item, isElectronic, finalPrice);
        this.recommendedBy = recommendedBy;
    }
    
    /**
     * 
     * @return the Item
     */
    public Item getItem() {
        return item;
    }
    
    /**
     * @return the isElectronic
     */
    public boolean isElectronic() {
        return isElectronic;
    }
    
    /**
     * @return the recommendedBy
     */
    public Item getRecommendedBy() {
        return recommendedBy;
    }
    
    /**
     * @return the finalPrice rounded to two decimal places
     */
    public double getFinalPrice() {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.valueOf(formatter.format(finalPrice));
    }
    
    /**
     * 
     * @param user
     * @return whether the user can buy this item or not (total purchase amount check)
     */
    public boolean validate(User user) {
        if (!(user instanceof Member) && this.getFinalPrice() <= user.remainingPurchaseAmount()) {
            return true;
        } else if (user instanceof Member) {
            return true;
        } else {
            System.out
                    .println("You have exceeded your purchase limit. Please check out and try again.");
            return false;
        }
    }
    
    /**
     * 
     * @param item
     * @param user
     * @param recommendation
     *            Creating a new purchase after the user selects an item to add to shopping cart.
     *            When successful, the purchase is added to the user's shopping cart
     */
    public static void createPurchase(Item item, User user, Item recommendation) {
        Purchase purchase = null;
        if (!item.isElectronicallyAvailable() && item.getNumberOfCopies() <= 0) {
            System.out.println("Sorry, this item is currently unavailable.");
            
        } else if (!item.isElectronicallyAvailable() && item.getNumberOfCopies() > 0) {
            System.out.println("This item is only available as a hard copy for "
                    + item.getPhysicalPrice());
            System.out.println("Would you like to purchase it? (y/n)");
            String userInput = User.getUserInput(true);
            if (userInput.equals("y")) {
                double price = item.getPhysicalPrice();
                if (recommendation != null) {
                    price = (price * (100 - user.DISCOUNT)) / 100;
                    purchase = new Purchase(item, false, price, recommendation);
                } else {
                    purchase = new Purchase(item, false, price);
                }
                item.buy(false);
            }
            
        } else if (item.isElectronicallyAvailable() && item.getNumberOfCopies() <= 0) {
            System.out.println("This item is only available electronically for "
                    + item.getElectronicPrice());
            System.out.println("Would you like to purchase it? (y/n)");
            String userInput = User.getUserInput(true);
            if (userInput.equals("y")) {
                double price = item.getElectronicPrice();
                if (recommendation != null) {
                    price = (price * (100 - user.DISCOUNT)) / 100;
                    purchase = new Purchase(item, true, price, recommendation);
                } else {
                    purchase = new Purchase(item, true, price);
                }
                
                item.buy(true);
            }
            
        } else {
            // code to choose between electronic and hard copy
            if (item instanceof Music) {
                System.out.println("This item is available as a(n): " +
                        "\n1.eMusic for " + item.getElectronicPrice() +
                        "\n2.CD for " + item.getPhysicalPrice() +
                        "\n0.Cancel");
            } else {
                System.out.println("This item is available as a(n): " +
                        "\n1.eBook for " + item.getElectronicPrice() +
                        "\n2.Hard Copy for " + item.getPhysicalPrice() +
                        "\n0.Cancel");
            }
            
            System.out.println("Please select an option:");
            int selection = User.getUserInput(0, 2);
            if (selection == 1) {
                double price = item.getElectronicPrice();
                if (recommendation != null) {
                    price = (price * (100 - user.DISCOUNT)) / 100;
                    purchase = new Purchase(item, true, price, recommendation);
                } else {
                    purchase = new Purchase(item, true, price);
                }
                
                item.buy(true);
            } else if (selection == 2) {
                double price = item.getPhysicalPrice();
                if (recommendation != null) {
                    price = (price * (100 - user.DISCOUNT)) / 100;
                    purchase = new Purchase(item, false, price, recommendation);
                } else {
                    purchase = new Purchase(item, false, price);
                }
                item.buy(false);
            }
        }
        
        if (purchase == null && (item.isElectronicallyAvailable() || item.getNumberOfCopies() > 0)) {
            System.out.println("You have selected not to purchase this item.");
        } else if (purchase != null && purchase.validate(user)) {
            user.getCart().addToCart(purchase, user);
            System.out.println("You have successfully added "
                    + purchase.display() + " to your cart");
            if (purchase.getItem().getRecommendations().size() > 0) {
                purchase.checkRecommendations(user);
            }
        }
    }
    
    /**
     * 
     * @param user
     *            getting the Item's recommendations
     */
    public void checkRecommendations(User user) {
        System.out.println("\nIf you like this, you might like the following "
                + (item.getRecommendations().size() == 1 ? "item" : "items")
                + ":\n");
        Item.listAllItems(item.getRecommendations(), user);
        System.out.println("0. Cancel");
        System.out.println("\nSelect a recommended item now to add it to your cart and get a "
                + (user instanceof Member ? "25%" : "15%") + " discount on its price:");
        int input = User.getUserInput(0, item.getRecommendations().size());
        if (input > 0) {
            Item selectedItem = item.getRecommendations().get(input - 1);
            createPurchase(selectedItem, user, item);
        }
    }
    
    /**
     * removing the discount offered on a recommended Item if the parent recommending item is
     * removed
     */
    public void loseDiscount() {
        if (isElectronic()) {
            finalPrice = item.getElectronicPrice();
        } else {
            finalPrice = item.getPhysicalPrice();
        }
        recommendedBy = null;
    }
    
    public String display() {
        String result = "";
        if (item instanceof Music) {
            Music musicItem = (Music) item;
            result = (isElectronic ? "eMusic: " : "Music CD: ") + musicItem.getName() + " by "
                    + musicItem.getArtist() + " for " + getFinalPrice();
        } else {
            Book bookItem = (Book) item;
            result = (isElectronic ? "eBook: " : "Hard Copy: ") + bookItem.getName() + " by "
                    + bookItem.getAuthor() + " for " + getFinalPrice();
        }
        return result;
    }
}
