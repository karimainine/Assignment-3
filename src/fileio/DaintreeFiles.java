package fileio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;
import daintree.*;

public class DaintreeFiles {
    
    public static final int USER         = 1;
    public static final int BOOK         = 2;
    public static final int MUSIC        = 3;
    public static final int ITEM_TOKENS  = 6;
    public static final int CART_TOKENS  = 3;
    public static final int USER_TOKENS  = 6;
    public static final int ADMIN_TOKENS = 5;
    
    /**
     * @param args
     */
    
    public static void loadFile(String path) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(path));
            String next = "";
            int dataType = -1;
            while ((next = input.readLine()) != null) {
                if (next.equals("#books")) {
                    dataType = BOOK;
                } else if (next.equals("#music")) {
                    dataType = MUSIC;
                } else if (next.equals("#users")) {
                    dataType = USER;
                } else if (!next.startsWith("#") && (!next.equals(""))) {
                    StringTokenizer token = new StringTokenizer(next, "|", false);
                    switch (dataType) {
                    case BOOK:
                    case MUSIC: {
                        tokenItem(token, dataType);
                        break;
                    }
                    case USER: {
                        tokenUser(token);
                        break;
                    }
                    default: {
                        // Do Nothing
                    }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Invalid file format");
            System.exit(0);
        }
    }
    
    public static void tokenItem(StringTokenizer token, int itemType) {
        ArrayList<String> tokenItems = new ArrayList<String>();
        while (token.hasMoreTokens()) {
            tokenItems.add(token.nextToken());
        }
        
        if (tokenItems.size() != ITEM_TOKENS) {
            System.out.println("Invalid file format.");
            System.exit(0);
        }
        Item item = null;
        String name = tokenItems.get(0);
        String author = tokenItems.get(1);
        int numberOfCopies = Integer.valueOf(tokenItems.get(2));
        boolean isEbook = tokenItems.get(3).toString().equalsIgnoreCase("yes") ? true : false;
        double physicalPrice = Double.valueOf(tokenItems.get(4));
        double electronicPrice = Double.valueOf(tokenItems.get(5));
        if (itemType == BOOK) {
            if (isEbook) {
                item = new Book(name, author, physicalPrice, electronicPrice, numberOfCopies);
            } else {
                item = new Book(name, author, physicalPrice, numberOfCopies);
            }
        } else {
            if (isEbook) {
                item = new Music(name, author, physicalPrice, electronicPrice, numberOfCopies);
            } else {
                item = new Music(name, author, physicalPrice, numberOfCopies);
            }
        }
        DaintreeStore.items.add(item);
    }
    
    public static void tokenUser(StringTokenizer token) {
        
        ArrayList<String> tokenItems = new ArrayList<String>();
        while (token.hasMoreTokens()) {
            tokenItems.add(token.nextToken());
        }
        
        User user = null;
        String id = tokenItems.get(0);
        String password = tokenItems.get(1);
        String name = tokenItems.get(2);
        String email = tokenItems.get(3);
        boolean isShopper = tokenItems.get(4).equals("shopper") ? true : false;
        boolean isMember = false;
        if (isShopper) {
            isMember = tokenItems.get(5).equals("member") ? true : false;
            if (isMember) {
                user = new Member(id, name, email, password);
            } else {
                user = new User(id, name, email, password);
            }
            if (tokenItems.size() > USER_TOKENS) {
                for (int i = USER_TOKENS; i <= tokenItems.size() - CART_TOKENS; i = i + CART_TOKENS) {
                    String cartToken = "";
                    for (int j = 0; j < CART_TOKENS; j++) {
                        if (j == CART_TOKENS) {
                            cartToken += tokenItems.get(i + j);
                        } else {
                            cartToken += tokenItems.get(i + j) + "|";
                        }
                    }
                    StringTokenizer tempToken = new StringTokenizer(cartToken, "|", false);
                    tokenUserCart(tempToken, user);
                }
            }
        } else {
            if (tokenItems.size() > ADMIN_TOKENS) {
                System.out.println("Invalid file format.");
                System.exit(0);
            } else {
                user = new Administrator(id, name, email, password);
            }
        }
        DaintreeStore.users.put(user.getId(), user);
    }
    
    public static void tokenUserCart(StringTokenizer token, User user) {
        ArrayList<String> tokenItems = new ArrayList<String>();
        while (token.hasMoreTokens()) {
            tokenItems.add(token.nextToken());
        }
        if (tokenItems.size() != CART_TOKENS) {
            System.out.println("Invalid file format.");
            System.exit(0);
        }
        String name = tokenItems.get(0);
        String author = tokenItems.get(1);
        Item item = Item.searchItem(name, author, DaintreeStore.items);
        if (item == null) {
            System.out.println("Invalid file format");
            System.exit(0);
        }
        boolean isEbook = tokenItems.get(2).toString().equalsIgnoreCase("yes") ? true : false;
        double price = isEbook ? item.getElectronicPrice() : item.getPhysicalPrice();
        Purchase purchase = new Purchase(item, isEbook, price);
        user.getCart().addToCart(purchase, user);
    }
    
    public static void Save(String path) {
        FileWriter writer = null;
        
        try {
            writer = new FileWriter(path);
            
            writer.write("#books\n");
            writer.write("#title|author|no. of copies|ebook?(yes,no)|physical-price|ebook-price\n");
            // loop for the books and write them
            for (Item item : DaintreeStore.items) {
                if (item instanceof Book) {
                    Book book = (Book) item;
                    writer.write(book.getName() + "|"
                            + book.getAuthor() + "|"
                            + book.getNumberOfCopies() + "|"
                            + (book.isElectronicallyAvailable() ? "yes" : "no") + "|"
                            + book.getPhysicalPrice() + "|"
                            + book.getElectronicPrice() + "\n");
                }
            }
            writer.write("#music\n");
            writer.write("#title|artist|no. of copies|eMusic?(yes,no)|physical-price|e-price\n");
            // loop for the music and write them
            for (Item item : DaintreeStore.items) {
                if (item instanceof Music) {
                    Music music = (Music) item;
                    writer.write(music.getName() + "|"
                            + music.getArtist() + "|"
                            + music.getNumberOfCopies() + "|"
                            + (music.isElectronicallyAvailable() ? "yes" : "no") + "|"
                            + music.getPhysicalPrice() + "|"
                            + music.getElectronicPrice() + "\n");
                }
            }
            writer.write("#users\n#id|password|name|email|shopper/admin|member/non-member[|cart-title1|cart-author1|ebook?]*for any items in cart \n");
            // loop for the users and write them
            Collection<User> users = DaintreeStore.users.values();
            for (User user : users) {
                writer.write(user.getId()+"|"
                        + user.getPassword()+"|"
                        + user.getName()+ "|"
                        + user.getEmail()+"|"
                        + (user instanceof Administrator ? "admin" : "shopper")
                        + (user instanceof Member ? "|member" : (user instanceof Administrator ? ""
                                : "|non-member")));
                if(user.getCart().getPurchases().size()>0){
                    for(Purchase purchase:user.getCart().getPurchases()){
                        Item item = purchase.getItem();
                        if(item instanceof Book){
                            Book book = (Book)item;
                            writer.write("|"+book.getName()+"|"+book.getAuthor()+"|"+(book.isElectronicallyAvailable()?"yes":"no"));
                        }else{
                            Music music = (Music)item;
                            writer.write("|"+music.getName()+"|"+music.getArtist()+"|"+(music.isElectronicallyAvailable()?"yes":"no"));
                        }
                    }
                }
                writer.write("\n");
            }
            writer.close();
            
        } catch (Exception e) {
            System.err.println("File cannot be created, or cannot be opened");
            System.exit(0);
        }
    }
}
