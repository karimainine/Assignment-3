package daintree;
/**
 * 
 * @author Karim Abulainine student id: 3314713
 * 
 *         Java for Programmers - Assignment 2
 * 
 *         Music is a sub class of Item and represents the music items in the application
 * 
 */
public class Music extends Item {
    
    private String artist;
    
    /**
     * 
     * @param name
     * @param artist
     * @param physicalPrice
     * @param electronicPrice
     * @param numberOfCopies
     *            Constructor for a music item that is electronically unavailable
     */
    public Music(String name, String artist, double physicalPrice,
            double electronicPrice, int numberOfCopies) {
        super(name, physicalPrice, electronicPrice, numberOfCopies);
        this.artist = artist;
    }
    
    /**
     * 
     * @param name
     * @param artist
     * @param physicalPrice
     * @param numberOfCopies
     *            Constructor for a music item that is electronically unavailable
     */
    public Music(String name, String artist, double physicalPrice, int numberOfCopies) {
        super(name, physicalPrice, numberOfCopies);
        this.artist = artist;
    }
    
    /**
     * 
     * @return Artist name of music
     * 
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * returns a string of a summary of the music item
     */
    public String display() {
        return super.getName() + " by " + getArtist();
    }
    
    /**
     * returns a string of the Music Item's description
     */
    public String listItem(User user) {
        String result = "";
        result += "Music: " + super.getName() + " - " + getArtist() + "\n";
        if (super.getNumberOfCopies() <= 0 && !super.isElectronicallyAvailable()) {
            result += "Currently unavailable.\n";
        } else {
            result += "Available as: \n";
            if (super.getNumberOfCopies() > 0) {
                result += "CD for " + super.getPhysicalPrice() + " ("
                        + (super.getNumberOfCopies())
                        + (super.getNumberOfCopies() > 1 ? " copies left)" : " copy left)")
                        + "\n";
            }
            if (super.isElectronicallyAvailable()) {
                result += "eMusic for " + super.getElectronicPrice() + "\n";
            }
        }
        
        if (user instanceof Administrator) {
            result += "\nPurchase statistics:\n";
            result += "CD (" + super.getPurchaseCount()
                    + (super.getPurchaseCount() == 1 ? " purchase" : " purchases") + ")\n";
            if (super.isElectronicallyAvailable()) {
                result += "eMusic (" + super.getePurchaseCount()
                        + (super.getPurchaseCount() == 1 ? " purchase" : " purchases") + ")\n";
            }
        }
        return result;
    }
}
