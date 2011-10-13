package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JList;
import java.util.ArrayList;
import java.util.Collection;
import daintree.*;
import fileio.DaintreeFiles;
import javax.swing.ListSelectionModel;

public class DaintreeAppWindow {
    private static String    outputFilePath;
    private static JList     itemsList;
    private static JComboBox eItemComboBox;
    private static JComboBox itemTypeComboBox;
    private static JTextPane messageTextPane;
    private static JLabel    eAvailableLabel;
    private JFrame           frame;
    private static JList     usersList;
    private JTextField       userIdTextField;
    private JTextField       userNameTextField;
    private JTextField       userPasswordTextField;
    private JComboBox        userTypeComboBox;
    private JList            shoppingCartList;
    private static JList     manageUsersUsersList;
    
    private Panel initManageUsersPanel() {
        
        Panel manageUsersPanel = new Panel();
        manageUsersPanel.setLayout(null);
        
        JLabel createUserLabel = new JLabel("Create New User");
        createUserLabel.setBounds(0, 0, 150, 30);
        manageUsersPanel.add(createUserLabel);
        
        //Initializing user id textField
        JLabel userIdLabel = new JLabel("Id");
        userIdLabel.setBounds(0, 35, 15, 30);
        manageUsersPanel.add(userIdLabel);
        
        userIdTextField = new JTextField();
        userIdTextField.setBounds(15, 35, 150, 30);
        manageUsersPanel.add(userIdTextField);
        
        //Initializing user name textField
        JLabel userNameLabel = new JLabel("Name");
        userNameLabel.setBounds(165, 35, 40, 30);
        manageUsersPanel.add(userNameLabel);
        
        userNameTextField = new JTextField();
        userNameTextField.setBounds(205, 35, 150, 30);
        manageUsersPanel.add(userNameTextField);
        
        //Initializing user password textField
        JLabel userPasswordLabel = new JLabel("Password");
        userPasswordLabel.setBounds(355, 35, 60, 30);
        manageUsersPanel.add(userPasswordLabel);
        
        userPasswordTextField = new JTextField();
        userPasswordTextField.setBounds(415, 35, 150, 30);
        manageUsersPanel.add(userPasswordTextField);
        
        //Initializing user type comboBox
        JLabel userTypeLabel = new JLabel("Type");
        userTypeLabel.setBounds(565, 35, 40, 30);
        manageUsersPanel.add(userTypeLabel);
        
        String [] userTypes = {"Admin", "Shopper", "Member"};
        userTypeComboBox = new JComboBox(userTypes);
        userTypeComboBox.setBounds(605, 35, 150, 30);
        manageUsersPanel.add(userTypeComboBox);
        
        //Initializing create User Button
        JButton createUserButton = new JButton("Create User");
        createUserButton.setBounds(605, 70, 150, 30);
        manageUsersPanel.add(createUserButton);
        
        //Initializing Users List
        JLabel usersListLabel = new JLabel("Users");
        usersListLabel.setBounds(100, 95, 50, 30);
        manageUsersPanel.add(usersListLabel);
        
        manageUsersUsersList = new JList(getUsers());
        manageUsersUsersList.setBounds(100, 125, 210, 250);
        manageUsersPanel.add(manageUsersUsersList);
        
        //Initializing Users shopping cart
        JLabel shoppingCartLabel = new JLabel("Shopping Cart");
        shoppingCartLabel.setBounds(325, 95, 150, 30);
        manageUsersPanel.add(shoppingCartLabel);
        
        shoppingCartList = new JList();
        shoppingCartList.setBounds(325, 125, 270, 250);
        manageUsersPanel.add(shoppingCartList);
        
        //Initializing Remove Button
        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(445, 380, 150, 30);
        manageUsersPanel.add(removeButton);
        
        return manageUsersPanel;
    }
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            String inputString = args[0];
            DaintreeFiles.loadFile(inputString);
            outputFilePath = args[1];
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        DaintreeAppWindow window = new DaintreeAppWindow();
                        window.frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            System.out.println("Invalid Arguments");
        }
    }
    
    /**
     * Create the application.
     * 
     * @wbp.parser.entryPoint
     */
    public DaintreeAppWindow() {
        initialize();
    }
    
    private String[] getUsers(){
        Collection<User> users = DaintreeStore.users.values();
        String[] usersArray = new String[users.size()];
        int i = 0;
        for (User user : users) {
            if (!(user instanceof Administrator)) {
                usersArray[i] = user.getId() + ", " + user.getName();
            }
            i++;
        }
        return usersArray;
    }
    private JList initUsersList() {
        
        JList usersList = new JList(getUsers());
        usersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usersList.setBounds(6, 28, 210, 359);
        return usersList;
    }
    
    private JComboBox initItemType() {
        String[] types = { "Book", "Music" };
        itemTypeComboBox = new JComboBox(types);
        itemTypeComboBox.setBounds(250, 28, 210, 27);
        ActionListener typeActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JComboBox cb = (JComboBox) e.getSource();
                if (cb.getSelectedItem().toString().equals("Music")) {
                    eAvailableLabel.setText("E-Music");
                    itemsList.setListData(getItemsArray(false, eItemComboBox.getSelectedItem()
                            .toString().equals("Yes")));
                } else {
                    eAvailableLabel.setText("E-Book");
                    itemsList.setListData(getItemsArray(true, eItemComboBox.getSelectedItem()
                            .toString().equals("Yes")));
                }
            }
        };
        itemTypeComboBox.addActionListener(typeActionListener);
        return itemTypeComboBox;
    }
    
    private JComboBox initEItem() {
        String[] values = { "Yes", "No" };
        eItemComboBox = new JComboBox(values);
        eItemComboBox.setBounds(493, 28, 237, 27);
        ActionListener eItemActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JComboBox cb = (JComboBox) e.getSource();
                if (cb.getSelectedItem().toString().equals("Yes")) {
                    itemsList.setListData(getItemsArray(itemTypeComboBox.getSelectedItem()
                            .toString().equals("Book"), true));
                } else {
                    itemsList.setListData(getItemsArray(itemTypeComboBox.getSelectedItem()
                            .toString().equals("Book"), false));
                }
            }
        };
        eItemComboBox.addActionListener(eItemActionListener);
        return eItemComboBox;
    }
    
    private String[] getItemsArray(boolean isBook, boolean isElectronic) {
        ArrayList<Item> items = DaintreeStore.items;
        String[] itemsArray = new String[items.size()];
        int i = 0;
        for (Item item : items) {
            if (isBook) {
                if (item instanceof Book) {
                    Book book = (Book) item;
                    if (isElectronic) {
                        if (item.isElectronicallyAvailable()) {
                            itemsArray[i] = book.getName() + " - " + book.getAuthor();
                        }
                    } else {
                        if (item.getNumberOfCopies() > 0) {
                            itemsArray[i] = book.getName() + " - " + book.getAuthor();
                        }
                    }
                }
            } else {
                if (item instanceof Music) {
                    Music music = (Music) item;
                    if (isElectronic) {
                        if (item.isElectronicallyAvailable()) {
                            itemsArray[i] = music.getName() + " - " + music.getArtist();
                        }
                    } else {
                        if (item.getNumberOfCopies() > 0) {
                            itemsArray[i] = music.getName() + " - " + music.getArtist();
                        }
                    }
                }
            }
            i++;
        }
        return itemsArray;
    }
    
    private JList initItemsList() {
        
        itemsList = new JList(getItemsArray(true, true));
        itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemsList.setBounds(258, 107, 490, 280);
        return itemsList;
    }
    
    private Panel initBuyItemPanel() {
        Panel buyItemPanel = new Panel();
        buyItemPanel.setLayout(null);
        
        // Initializing eSelection combo box
        eAvailableLabel = new JLabel("EBook");
        eAvailableLabel.setBounds(507, 6, 76, 16);
        buyItemPanel.add(eAvailableLabel);
        
        eItemComboBox = initEItem();
        buyItemPanel.add(eItemComboBox);
        
        // Initializing Items List
        JLabel itemsLabel = new JLabel("Matching Items");
        itemsLabel.setBounds(258, 79, 108, 16);
        buyItemPanel.add(itemsLabel);
        
        itemsList = initItemsList();
        buyItemPanel.add(itemsList);
        
        // Initializing item type combo box
        JLabel itemTypeLabel = new JLabel("Item Type");
        itemTypeLabel.setBounds(258, 6, 70, 16);
        buyItemPanel.add(itemTypeLabel);
        
        itemTypeComboBox = initItemType();
        buyItemPanel.add(itemTypeComboBox);
        
        // Initializing users list
        JLabel usersLabel = new JLabel("Users");
        usersLabel.setBounds(6, 6, 41, 16);
        buyItemPanel.add(usersLabel);
        
        usersList = initUsersList();
        buyItemPanel.add(usersList);
        
        JButton purchaseButton = initPurchaseButton();
        buyItemPanel.add(purchaseButton);
        
        return buyItemPanel;
    }
    
    private void purchase(String userString, String itemString) {
        String userId = userString.split(", ")[0];
        User user = DaintreeStore.users.get(userId);
        String itemName = itemString.split(" - ")[0];
        String itemAuthor = itemString.split(" - ")[1];
        Item item = Item.searchItem(itemName, itemAuthor, DaintreeStore.items);
        double price = eItemComboBox.getSelectedItem().toString().equals("Yes") ? item
                .getElectronicPrice() : item.getPhysicalPrice();
        
        if (!(user instanceof Member) && price > user.remainingPurchaseAmount()) {
            messageTextPane.setText("Error: You have exceeded your purchase limit.");
        } else {
            Purchase purchase = new Purchase(item, eItemComboBox.getSelectedItem().toString()
                    .equals("Yes"), price);
            item.buy(eItemComboBox.getSelectedItem().toString().equals("Yes"));
            user.getCart().addToCart(purchase, user);
            messageTextPane.setText(itemName + "by " + itemAuthor + "has been added to "
                    + user.getName() + "'s cart successfully.");
        }
    }
    
    private JButton initPurchaseButton() {
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.setBounds(631, 390, 117, 29);
        ActionListener purchaseActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (usersList.getSelectedValue() == null) {
                    messageTextPane.setText("Error: Please select a user");
                } else if (itemsList.getSelectedValue() == null) {
                    messageTextPane.setText("Error: Please select an item");
                } else {
                    purchase(usersList.getSelectedValue().toString(), itemsList.getSelectedValue()
                            .toString());
                }
            }
        };
        purchaseButton.addActionListener(purchaseActionListener);
        return purchaseButton;
    }
    
    private JButton initQuitButton() {
        JButton cancelButton = new JButton("Quit");
        cancelButton.setBounds(547, 521, 117, 29);
        frame.getContentPane().add(cancelButton);
        ActionListener quitActionListner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        };
        cancelButton.addActionListener(quitActionListner);
        return cancelButton;
    }
    
    private JButton initSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(664, 521, 117, 29);
        ActionListener saveActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                fileio.DaintreeFiles.Save(DaintreeAppWindow.outputFilePath);
                messageTextPane.setText("Data has been saved successfully!");
            }
        };
        saveButton.addActionListener(saveActionListener);
        return saveButton;
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 787, 578);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        messageTextPane = new JTextPane();
        messageTextPane.setEditable(false);
        messageTextPane.setBackground(UIManager.getColor("Button.background"));
        messageTextPane.setBounds(6, 6, 775, 29);
        frame.getContentPane().add(messageTextPane);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(6, 38, 775, 471);
        frame.getContentPane().add(tabbedPane);
        
        // Initializing buy item panel
        Panel buyItemPanel = initBuyItemPanel();
        tabbedPane.addTab("Buy an Item", null, buyItemPanel, null);
        
        Panel manageUsersPanel = initManageUsersPanel();
        tabbedPane.addTab("Manage Users", null, manageUsersPanel, null);
        
        frame.getContentPane().setFocusTraversalPolicy(
                new FocusTraversalOnArray(new Component[] { tabbedPane, buyItemPanel }));
        
        frame.getContentPane().add(initQuitButton());
        frame.getContentPane().add(initSaveButton());
    }
}
