/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import daintree.DaintreeStore;
import daintree.Item;
import daintree.User;

import fileio.DaintreeFiles;

/**
 * @author 
 *
 */
public class DaintreeGUI extends JPanel{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param args
     */
    
    public DaintreeGUI(){
        super(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
         
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(400, 200));
        
        JComponent messageLabel1 = createMessageLabel();
        JList users1 = createUsersList();
        JComboBox itemTypes = createItemTypes();
        JRadioButton electronic = new JRadioButton("Electronic");
        JRadioButton physical = new JRadioButton("Physical");
        JButton purchaseButton = new JButton("Purchase");
        JList items = createItemsList();
        JPanel tab1 = new JPanel(new GridBagLayout());
        
        JComponent messageLabel2 = createMessageLabel();
        JList users2 = createUsersList();
        JList cart = createUserCart();
        JLabel newUserLabel = new JLabel("Create New User");
        JLabel idLabel = new JLabel("ID:");
        JTextField idTextField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameTextField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordTextField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        JComboBox userTypes = createUserTypes();
        JButton createUserButton = new JButton("Create User");
        JButton removeButton = new JButton("Remove");
        
        JPanel tab2 = new JPanel(new GridBagLayout());
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 4;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        tab2.add(messageLabel2, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridheight = 3;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 1;
        tab2.add(users2, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 4;
        tab2.add(cart, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 1;
        constraints.gridy = 6;
        tab2.add(removeButton, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 2;
        constraints.gridy = 1;
        tab2.add(newUserLabel, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 2;
        constraints.gridy = 2;
        tab2.add(idLabel, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 3;
        constraints.gridy = 2;
        tab2.add(idTextField, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 2;
        constraints.gridy = 3;
        tab2.add(nameLabel, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 3;
        constraints.gridy = 3;
        tab2.add(nameTextField, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 2;
        constraints.gridy = 4;
        tab2.add(passwordLabel, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 3;
        constraints.gridy = 4;
        tab2.add(passwordTextField, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 2;
        constraints.gridy = 5;
        tab2.add(typeLabel, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 3;
        constraints.gridy = 5;
        tab2.add(userTypes, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 3;
        constraints.gridy = 6;
        tab2.add(createUserButton, constraints);
        
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 4;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        tab1.add(messageLabel1, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        tab1.add(users1, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        tab1.add(itemTypes, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 2;
        tab1.add(electronic, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 2;
        constraints.gridy = 2;
        tab1.add(physical, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.gridx = 3;
        constraints.gridy = 1;
        tab1.add(items, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 3;
        constraints.gridy = 3;
        tab1.add(purchaseButton, constraints);
        
        tabbedPane.add("Buy an Item", tab1);
        tabbedPane.add("Manage Users", tab2);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(tabbedPane, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        JButton saveButton = new JButton("Save");
        add(saveButton, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 1;
        JButton cancelButton = new JButton("Cancel");
        add(cancelButton, constraints);
        
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    protected JComponent createTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(3, 3));
        panel.add(filler, BorderLayout.NORTH);
        return panel;
    }
    
    private JComponent createMessageLabel()
    {
        JLabel label = new JLabel("Message Label");
        label.setForeground(Color.RED);
        label.setBackground(Color.LIGHT_GRAY);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
    
    private JList createUserCart(){
        String[] items = {"Item 1", "Item 2", "Item 3"};
        JList list = new JList(items);
        JLabel label = new JLabel("Shopping Cart");
        label.setLabelFor(list);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        
        return list;
    }
    
    private JList createUsersList(){
        String[] users = {"user1", "user2", "user3"};
        JList list = new JList(users);
        JLabel label = new JLabel("Users");
        label.setLabelFor(list);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        
        return list;
    }
    
    private JList createItemsList(){
        String[] items = {"Book1", "Book2", "Music3"};
        JList list = new JList(items);
        JLabel label = new JLabel("Matching Items");
        label.setLabelFor(list);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.scrollRectToVisible(new Rectangle());
        
        return list;
    }
    
    private JComboBox createItemTypes(){
        String [] types = {"Book", "Music"};
        JComboBox itemTypes = new JComboBox(types);
        return itemTypes;
    }
    
    private JComboBox createUserTypes(){
        String [] types = {"Admin", "Member", "Shopper"};
        JComboBox userTypes = new JComboBox(types);
        return userTypes;
    }
    
    private static void initGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Daintree Stores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add content to the window.
        frame.add(new DaintreeGUI(), BorderLayout.CENTER);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        if(args.length == 2){
            String inputString = args[0];
            DaintreeFiles.loadFile(inputString);
            
            //String outputString = args[1];
            //DaintreeFiles.Save(outputString);
            /*Item.listAllItems(DaintreeStore.items, new User());
            System.out.println("Users number: " + DaintreeStore.users.size());
            Collection<User> users = DaintreeStore.users.values();
            for(User user : users){
                System.out.print(user.getName() + " - ");
                if(user.getCart()!=null){
                    user.getCart().viewCart();
                }
                System.out.println();
            }*/
            //initGUI();
        }else{
            System.out.println("Invalid Arguments");
        }
    }
}
