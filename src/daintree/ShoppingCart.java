package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         Shopping cart class representes the shopping cart. Each user has a single shopping cart.
 *         It contains all the purchases at a single session.
 * 
 */
import java.util.ArrayList;

public class ShoppingCart {
    
    private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
    
    /**
     * 
     * @param purchases
     *            ShoppingCart Constructor
     */
    public ShoppingCart(ArrayList<Purchase> purchases) {
        super();
        this.purchases = purchases;
    }
    
    /**
     * Empty Shopping cart Constructor
     */
    public ShoppingCart() {
        super();
        this.purchases = new ArrayList<Purchase>();
    }
    
    /**
     * @return the items
     */
    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }
    
    /**
     * 
     * @param purchase
     * @param user
     *            Adding item to shopping cart
     */
    public void addToCart(Purchase purchase, User user) {
        purchases.add(purchase);
    }
    
    /**
     * printing the cart items
     */
    public void viewCart() {
        if (purchases.size() <= 0) {
            System.out.println("Your cart is empty.");
        } else {
            int index = 1;
            for (Purchase purchase : this.purchases) {
                System.out.println(index
                        + ". "
                        + purchase.display());
                index++;
            }
        }
    }
    
    /**
     * 
     * @return total price of all purchases in the cart
     */
    public double returnTotal() {
        double total = 0;
        for (Purchase purchase : purchases) {
            total += purchase.getFinalPrice();
        }
        return total;
    }
    
    /**
     * removing a purchase from the cart and checking if it was a recommendation or not
     */
    public void removePurchase() {
        viewCart();
        if (purchases.size() > 0) {
            System.out.println("0. Cancel");
            System.out.println("Please select the purchase number you would like to remove:");
            int selection = User.getUserInput(0, purchases.size());
            if (selection > 0) {
                Purchase purchase = purchases.get(selection - 1);
                
                boolean wasRecommendation = false;
                Purchase recommendationPurchase = null;
                for (Purchase otherPurchase : purchases) {
                    if (otherPurchase.getRecommendedBy() != null
                            && otherPurchase.getRecommendedBy().equals(purchase.getItem())) {
                        wasRecommendation = true;
                        recommendationPurchase = otherPurchase;
                    }
                }
                
                if (wasRecommendation) {
                    System.out
                            .println("By removing this item from the cart, you will lose the discount you got on "
                                    + recommendationPurchase.getItem().getName() + ".");
                    System.out.println("Are you sure you want to continue? (y/n)");
                    String input = User.getUserInput(true);
                    if (input.equals("y")) {
                        purchase.getItem().returnItem(purchase.isElectronic());
                        purchases.remove(selection - 1);
                        recommendationPurchase.loseDiscount();
                        System.out.println("You have successfully removed the "
                                + purchase.display()
                                + " from your cart.");
                    } else {
                        System.out.println("You chose not to remove the item from the cart");
                    }
                } else {
                    purchase.getItem().returnItem(purchase.isElectronic());
                    purchases.remove(selection - 1);
                    System.out.println("You have successfully removed the "
                            + purchase.display()
                            + " from your cart.");
                }
            } else {
                System.out.println("You have chosen not to remove anything from the cart.");
            }
        }
    }
}
