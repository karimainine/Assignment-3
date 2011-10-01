package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         Book is a sub class of Item and represents the books in the application
 * 
 */

public class Book extends Item {
    private String author;
    
    /**
     * 
     * @param name
     * @param author
     * @param physicalPrice
     * @param electronicPrice
     * @param numberOfCopies
     * 
     *            Constructor for a book that is electronically available
     */
    public Book(String name, String author, double physicalPrice,
            double electronicPrice, int numberOfCopies) {
        super(name, physicalPrice, electronicPrice, numberOfCopies);
        this.author = author;
    }
    
    /**
     * 
     * @param name
     * @param author
     * @param physicalPrice
     * @param numberOfCopies
     * 
     *            Constructor for a book that is electronically unavailable
     */
    public Book(String name, String author, double physicalPrice, int numberOfCopies) {
        super(name, physicalPrice, numberOfCopies);
        this.author = author;
    }
    
    /**
     * 
     * @return author name of the book
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * returns a string of a summary of the book item
     */
    public String display() {
        return super.getName() + " by " + getAuthor();
    }
    
    /**
     * returns a string of the Book Item's description
     */
    public String listItem(User user) {
        String result = "";
        result += "Book: " + super.getName() + " - " + getAuthor() + "\n";
        if (super.getNumberOfCopies() <= 0 && !super.isElectronicallyAvailable()) {
            result += "Currently unavailable.\n";
        } else {
            result += "Available as: \n";
            if (super.getNumberOfCopies() > 0) {
                result += "Hardcopy for " + super.getPhysicalPrice() + " ("
                        + (super.getNumberOfCopies())
                        + (super.getNumberOfCopies() > 1 ? " copies left)" : " copy left)")
                        + "\n";
            }
            if (super.isElectronicallyAvailable()) {
                result += "eBook for " + super.getElectronicPrice() + "\n";
            }
        }
        if (user instanceof Administrator) {
            result += "\nPurchase statistics:\n";
            result += "Hard Copy (" + super.getPurchaseCount()
                    + (super.getPurchaseCount() == 1 ? " purchase" : " purchases") + ")\n";
            if (super.isElectronicallyAvailable()) {
                result += "eBook (" + super.getePurchaseCount()
                        + (super.getPurchaseCount() == 1 ? " purchase" : " purchases") + ")\n";
            }
        }
        return result;
    }
    
}
