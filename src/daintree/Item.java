package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         Item is parent abstract class for Books and Music. It contains all the implementations
 *         related to Items
 * 
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Item {
    private String          name;
    private boolean         isElectronicallyAvailable;
    private double          physicalPrice;
    private double          electronicPrice;
    private int             numberOfCopies;
    private ArrayList<Item> recommendations      = new ArrayList<Item>();
    private int             ePurchaseCount       = 0;
    private int             purchaseCount        = 0;
    
    public static final int MAX_NUMBER_OF_COPIES = 10;
    
    /**
     * 
     * @param name
     * @param physicalPrice
     * @param electronicPrice
     * @param numberOfCopies
     * 
     *            Constructor for an electronically available item
     */
    public Item(String name, double physicalPrice, double electronicPrice, int numberOfCopies) {
        this.name = name;
        this.isElectronicallyAvailable = true;
        this.physicalPrice = physicalPrice;
        this.electronicPrice = electronicPrice;
        this.numberOfCopies = numberOfCopies;
    }
    
    /**
     * 
     * @param name
     * @param physicalPrice
     * @param numberOfCopies
     *            Constructor for an electronically unavailable item
     */
    public Item(String name, double physicalPrice, int numberOfCopies) {
        this.name = name;
        this.isElectronicallyAvailable = false;
        this.physicalPrice = physicalPrice;
        this.numberOfCopies = numberOfCopies;
    }
    
    /**
     * @return the purchaseCount
     */
    public int getPurchaseCount() {
        return purchaseCount;
    }
    
    /**
     * @return the ePurchaseCount
     */
    public int getePurchaseCount() {
        return ePurchaseCount;
    }
    
    /**
     * @return the recommendations
     */
    public ArrayList<Item> getRecommendations() {
        return recommendations;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the isElectronicallyAvailable
     */
    public boolean isElectronicallyAvailable() {
        return isElectronicallyAvailable;
    }
    
    /**
     * @return the physicalPrice rounded to two decimal places
     */
    public double getPhysicalPrice() {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.valueOf(formatter.format(physicalPrice));
    }
    
    /**
     * @return the electronicPrice rounded to two decimal places
     */
    public double getElectronicPrice() {
        
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.valueOf(formatter.format(electronicPrice));
    }
    
    /**
     * @return the numberOfCopies
     */
    public int getNumberOfCopies() {
        return numberOfCopies;
    }
    
    /**
     * 
     * @param isElectronic
     * 
     *            buying an Item. Checks if it is electronic or not to update the purchase counts
     *            and number of copies
     */
    public void buy(boolean isElectronic) {
        if (!isElectronic) {
            numberOfCopies--;
            purchaseCount++;
        } else {
            ePurchaseCount++;
        }
    }
    
    /**
     * 
     * @param isElectronic
     * 
     *            returning an Item. Checks if it is electronic or not to update the purchase counts
     *            and number of copies
     */
    public void returnItem(boolean isElectronic) {
        if (!isElectronic) {
            numberOfCopies++;
            purchaseCount--;
        } else {
            ePurchaseCount--;
        }
    }
    
    /**
     * 
     * @param item
     *            adding recommendations to Items.
     */
    public void addRecommendation(Item item)
    {
        this.recommendations.add(item);
    }
    
    /**
     * 
     * @param items
     * @param user
     *            Listing all items and their prices and availability
     */
    public static void listAllItems(ArrayList<Item> items, User user)
    {
        int index = 1;
        for (Item item : items) {
            System.out.print(index + ". " + item.listItem(user));
            System.out
                    .print("-------------------------------------------------------------------------------\n");
            index++;
        }
    }
    
    /**
     * 
     * @param input
     * @param items
     * @return arraylist of items returned by the search result
     */
    public static ArrayList<Item> search(String input, ArrayList<Item> items) {
        ArrayList<Item> result = new ArrayList<Item>();
        
        for (Item item : items) {
            if (item.getName().toLowerCase().startsWith(input.toLowerCase())) {
                result.add(item);
            }
        }
        listAllItems(result, new User());
        return result;
    }
    
    /**
     * 
     * @param copiesNumber
     *            add number of copies by admin
     */
    public void addCopies(int copiesNumber) {
        numberOfCopies = numberOfCopies + copiesNumber;
    }
    
    /**
     * 
     * @param physicalPrice
     * @param ePrice
     *            changing price by admin
     */
    public void changePrice(double physicalPrice, double ePrice) {
        this.electronicPrice = ePrice;
        this.physicalPrice = physicalPrice;
    }
    
    public void changePrice(double physicalPrice) {
        this.physicalPrice = physicalPrice;
    }
    
    /**
     * returns a string of a summary of the item
     */
    public abstract String display();
    
    /**
     * returns a string of the full description of the item
     */
    public abstract String listItem(User user);
}
