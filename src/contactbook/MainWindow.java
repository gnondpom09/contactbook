/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Window of application
 *
 * @author BEN Formation, Laurent Botella
 */
public class MainWindow extends JFrame {

    public JFrame frame;
    private static final long serialVersionUID = 2026580921442617098L;
    private final JPanel globalPanel;
    private final JPanel groupPanel;
    private final JPanel detailPanel;
    private final JPanel listPanel;
    public Color COLOR_WEB = Color.decode("#1BAF55");
    public Color COLOR_DARK = Color.decode("#252529");
    public Color COLOR_GREY = Color.decode("#2E2E33");
    public Color COLOR_LIGTH = Color.decode("#F6F9FD");
    public Color COLOR_LIGHT = Color.lightGray;
    private ArrayList groupList;
    private JTextField search;
    private JTextField name;
    private JTextField firstname;
    private JTextField street;
    private JTextField zipCode;
    private JTextField city;
    private String categoryPhoneSelected;
    private String categoryPhoneSelected2;
    private JTextField phoneNumber;
    private JTextField phoneNumber2;
    private String categoryEmailSelected;
    private String categoryEmailSelected2;
    private JTextField email;
    private JTextField email2;
    private ArrayList categoriesList;
    private ArrayList phoneNumbersList;
    private ArrayList emailsList;
    private ArrayList listOfContacts;
    private ArrayList listOfGroups;
    public static final String FILECONTACT = "contacts.json";
    public static final String FILEGROUP = "groups.json";

    /**
     * Constructor
     *
     * @author Laurent Botella
     */
    public MainWindow() {
        this.setTitle("Carnet d'adresses");
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        globalPanel = new JPanel();
        groupPanel = new JPanel();
        detailPanel = new JPanel();
        groupList = new ArrayList<String>();
        search = new JTextField();
        name = new JTextField();
        firstname = new JTextField();
        street = new JTextField();
        zipCode = new JTextField();
        city = new JTextField();
        listPanel = new JPanel();
        phoneNumber = new JTextField();
        phoneNumber2 = new JTextField();
        categoryPhoneSelected = "";
        categoryPhoneSelected2 = "";
        email = new JTextField();
        email2 = new JTextField();
        categoriesList = new ArrayList<String>();
        categoriesList.add("Domicile");
        categoriesList.add("Travail");
        categoriesList.add("Personnel");
        phoneNumbersList = new ArrayList<Phone>();
        emailsList = new ArrayList<MailAdress>();
        listOfContacts = new ArrayList<Contactbook>();
        listOfGroups = new ArrayList<Group>();

        // Set content of window
        this.setContentPane(globalPanel);
        setGlobalPanel();
    }

    /**
     * Display components of application
     *
     * @author Laurent Botella
     */
    public void setGlobalPanel() {

        // get datas from json file and fill collection of contacts
        Contactbook person = new Contactbook();
        PostAdress adress = new PostAdress();
        AdressBook listsManager = new AdressBook();
        listsManager.getListOfContactsFromFile(FILECONTACT);
        listOfContacts = listsManager.getListOfContacts();
        listsManager.getListOfGroupsFromFile(FILEGROUP);
        listOfGroups = listsManager.getListOfGroups();

        // Distribute blocks
        globalPanel.setLayout(new BorderLayout());

        //////////////////////////////////////////////////////////////////////
        ////////////////////////////// PANE GROUPS ///////////////////////////
        //////////////////////////////////////////////////////////////////////
        JPanel headerPanelGroup = new JPanel();
        JLabel lblGroup = new JLabel("Groupes");
        JPanel contentPanelGroup = new JPanel();

        // Get names list of groups from JSON file
        DefaultListModel groupsListModel = new DefaultListModel();
        for (Group group : listsManager.getListOfGroups()) {
            System.out.println("groupe : " + group.getName());
            groupsListModel.addElement(group.getName());
        }

        // Fill list panel with list of groups
        JList componentGroupsList = new JList(groupsListModel);

        // Button to add new group
        JButton btnAddGroup = new JButton("+");

        // Event click to add new group
        btnAddGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add new group of contacts
                // code...
            }
        });

        //////////////////////////////////////////////////////////////////////
        ////////////////////////////// PANE LIST CONTACTS ////////////////////
        //////////////////////////////////////////////////////////////////////
        JPanel headerList = new JPanel();
        JPanel searchPanel = new JPanel();
        JLabel titleList = new JLabel("Contacts");
        JButton btnSearch = new JButton("OK");

        // Fill combobox with list of user names
        DefaultListModel contactsListModel = new DefaultListModel();
        JList componentContactsList = new JList(contactsListModel);

        // Get list of contacts
        listOfContacts = listsManager.getListOfContacts();

        for (Contactbook user : listsManager.getListOfContacts()) {
            String itemName = user.getFirstname() + " " + user.getName();
            System.out.println("item : " + user.getName() + " - " + user.getFirstname());
            contactsListModel.addElement(itemName);

            // set first item selected
            componentContactsList.setSelectedIndex(0);

        }

        // Set first item by default
        componentContactsList.setSelectedIndex(0);
        String firstItemSelected = (String) componentContactsList.getSelectedValue();
        String[] firstArrayNames = firstItemSelected.split(" ");

        // display infos in pane detail
        System.out.println("test first item : " + firstArrayNames[1]);
        name.setText(firstArrayNames[1]);

        // Event click to search user
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Compare value of field with concatenation of firstname and name
                String itemSelected = (String) componentContactsList.getSelectedValue();

                // get informations of user selected
                String[] ArrayNames = itemSelected.split(" ");

                try {
                    // Compare with name of contact
                    listsManager.findContactByName(ArrayNames[1], listOfContacts);

                    // get informations of user find
                    // code ...
                    // update informations of person
                    // code ..
                } catch (Exception err) {
                    System.out.println(err);
                }

            }
        });

        // Event click to select user in the list and display detail
        componentContactsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Get item selected
                String itemSelected = (String) componentContactsList.getSelectedValue();

                // get informations of user selected
                System.out.println("item selectionné: " + itemSelected);
                String[] ArrayNames = itemSelected.split(" ");

                // test 
                System.out.println("Click : " + ArrayNames[1]);

                
                //System.out.println("Click : " + ArrayNames[2]);
                //System.out.println("Click : " + ArrayNames[3]);test

                // display infos in pane detail
                name.setText(ArrayNames[1]);


                try {
                    // Compare with name of contact
                    listsManager.findContactByName(ArrayNames[1], listOfContacts);
                    System.out.println("contact trouvé:");
                    name.setText(listsManager.findContactByName(ArrayNames[1], listOfContacts).getName());
                    //récupération du contact dans la liste et comparaison dans le tableau
                    firstname.setText(listsManager.findContactByName(ArrayNames[1], listOfContacts).getFirstname());
                    street.setText(listsManager.findContactByName(ArrayNames[1], listOfContacts).getPostAdress().getCodepost());
                    // assign contact find in panel detail
                    
                    System.out.println(listsManager.findContactByName(ArrayNames[1], listOfContacts).getName());
                    System.out.println(listsManager.findContactByName(ArrayNames[1], listOfContacts).getFirstname());
//                    System.out.println(listsManager.findContactByName(ArrayNames[1], listOfContacts).getPhone());
//                    System.out.println(listsManager.findContactByName(ArrayNames[1], listOfContacts).getMailAdress());
                    System.out.println(listsManager.findContactByName(ArrayNames[1], listOfContacts).getPostAdress().getStreet());
//                    System.out.println(listsManager.findContactByName(ArrayNames[1], listOfContacts).getGroup());
                    //name = (String) listsManager.findContactByName(ArrayNames[1], listOfContacts).getName();
                    //person.setName(listsManager.findContactByName(ArrayNames[1], listOfContacts).getName());
                    //person.setFirstname(listsManager.findContactByName(ArrayNames[1], listOfContacts).getFirstname());
                    //person.setPostAdress(listsManager.findContactByName(ArrayNames[1], listOfContacts).getPostAdress());
                    
                    System.out.println(listsManager.findContactByName(ArrayNames[1], listOfContacts).getPostAdress().getCodepost());
                    
                    name.setText((person.getName() != null) ? person.getName() : "");
                    firstname.setText((person.getFirstname() != null) ? person.getFirstname() : "");
                    street.setText((person.getPostAdress() != null) ? person.getPostAdress().getStreet() : "");
                    zipCode.setText((person.getPostAdress() != null) ? person.getPostAdress().getCodepost() : "");
                    city.setText((person.getPostAdress() != null) ? person.getPostAdress().getTown() : "");
                    // code ...
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
        });

        //////////////////////////////////////////////////////////////////////
        ////////////////////////////// PANE DETAIL ///////////////////////////
        //////////////////////////////////////////////////////////////////////
        JPanel contentPanelDetail = new JPanel();
        contentPanelDetail.setLayout(new GridLayout(0, 1));
        JPanel headerPanelForm = new JPanel();
        headerPanelForm.setLayout(new GridLayout(0, 2));
        JPanel contentPanelForm = new JPanel();
        contentPanelForm.setLayout(new GridLayout(0, 1));
        JPanel actionPanel = new JPanel();

        //////////////////////////////////////////////////////////////////////
        ////////////////////////////// PANE FORM /////////////////////////////
        //////////////////////////////////////////////////////////////////////
        JLabel lblName = new JLabel("Nom");
        JLabel lblFirstname = new JLabel("Prénom");
        JLabel lblAddress = new JLabel("Adresse postale");
        JLabel lblPhoneNumber = new JLabel("Numéro de téléphone");
        JComboBox comboBoxCategoriesListPhone = new JComboBox();
        JLabel lblPhoneNumber2 = new JLabel("Numéro de téléphone 2");
        JComboBox comboBoxCategoriesListPhone2 = new JComboBox();
        JLabel lblEmail = new JLabel("Email");
        JComboBox comboBoxCategoriesListEmail = new JComboBox();
        JLabel lblEmail2 = new JLabel("Email 2");
        JComboBox comboBoxCategoriesListEmail2 = new JComboBox();

        // Set content of fields and combobox
        name.setText((person.getName() != null) ? person.getName() : "");
        firstname.setText((person.getFirstname() != null) ? person.getFirstname() : "");
        street.setText((person.getPostAdress() != null) ? person.getPostAdress().getStreet() : "");
        zipCode.setText((person.getPostAdress() != null) ? person.getPostAdress().getCodepost() : "");
        city.setText((person.getPostAdress() != null) ? person.getPostAdress().getTown() : "");
        comboBoxCategoriesListPhone.setModel(new DefaultComboBoxModel(categoriesList.toArray()));
        comboBoxCategoriesListPhone2.setModel(new DefaultComboBoxModel(categoriesList.toArray()));
        comboBoxCategoriesListEmail.setModel(new DefaultComboBoxModel(categoriesList.toArray()));
        comboBoxCategoriesListEmail2.setModel(new DefaultComboBoxModel(categoriesList.toArray()));

        // Event to select categorie of first phone
        comboBoxCategoriesListPhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // select type of phone
                categoryPhoneSelected = comboBoxCategoriesListPhone.getSelectedItem().toString();
            }
        });
        if (person.getPhone() != null) {
            // Get list of phones and get first item, set combobox and fields with properties label and number
            for (int i = 0; i < person.getPhone().size(); i++) {
                comboBoxCategoriesListPhone.setSelectedItem(person.getPhone().set(0, person.getPhone().get(i)).getLibelle());
                phoneNumber.setText(person.getPhone().set(0, person.getPhone().get(i)).getNumber());
            }
        }

        // Event to select categorie of second phone
        comboBoxCategoriesListPhone2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // select type of phone
                categoryPhoneSelected2 = comboBoxCategoriesListPhone2.getSelectedItem().toString();
            }
        });
        if (person.getPhone() != null) {
            // Get list of phones and get second item, set combobox and fields with properties label and number
            for (int i = 0; i < person.getPhone().size(); i++) {
                comboBoxCategoriesListPhone2.setSelectedItem(person.getPhone().set(1, person.getPhone().get(i)).getLibelle());
                phoneNumber2.setText(person.getPhone().set(1, person.getPhone().get(i)).getNumber());
            }
        }

        // Event to select categorie of first email
        comboBoxCategoriesListEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // select type of phone
                categoryEmailSelected = comboBoxCategoriesListEmail.getSelectedItem().toString();
            }
        });
        if (person.getMailAdress() != null) {
            // Get list of emails and get second item, set combobox and fields with properties label and email
            for (int i = 0; i < person.getMailAdress().size(); i++) {
                comboBoxCategoriesListEmail.setSelectedItem(person.getMailAdress().set(0, person.getMailAdress().get(i)).getLibelle());
                email.setText(person.getMailAdress().set(0, person.getMailAdress().get(i)).getAdress());
            }
        }

        // Event to select category of second email
        comboBoxCategoriesListEmail2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // select type of phone
                categoryEmailSelected2 = comboBoxCategoriesListEmail2.getSelectedItem().toString();
            }
        });
        if (person.getMailAdress() != null) {
            // Get list of emails and get first item, set combobox and fields with properties label and email
            for (int i = 0; i < person.getMailAdress().size(); i++) {
                comboBoxCategoriesListEmail2.setSelectedItem(person.getMailAdress().set(1, person.getMailAdress().get(i)).getLibelle());
                email2.setText(person.getMailAdress().set(1, person.getMailAdress().get(i)).getAdress());
            }
        }

        // buttons hof header
        JButton btnAddNewContact = new JButton("+");
        JButton submitNewContact = new JButton("Valider");
        JButton updateContact = new JButton("Modifier");
        JButton btnCancel = new JButton("Annuler");
        JButton btnRemove = new JButton("Supprimer");

        // Event click to add new contact
        btnAddNewContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // set form of new contact to visible and hide detail panel
//                contentPanelDetail.setVisible(false);
//                contentPanelForm.setVisible(true);
                btnAddNewContact.setVisible(false);
                updateContact.setVisible(false);
                submitNewContact.setVisible(true);
                btnCancel.setVisible(true);
                btnRemove.setVisible(false);
                name.setEditable(true);
                firstname.setEditable(true);
                street.setEditable(true);
                zipCode.setEditable(true);
                city.setEditable(true);
                phoneNumber.setEditable(true);
                phoneNumber2.setEditable(true);
                email.setEditable(true);
                email2.setEditable(true);

            }
        });

        // Event click to submit new contact
        submitNewContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // set update form contact to visible and hide detail panel
//                contentPanelDetail.setVisible(true);
//                contentPanelForm.setVisible(false);
                btnAddNewContact.setVisible(true);
                updateContact.setVisible(true);
                submitNewContact.setVisible(false);
                btnCancel.setVisible(false);
                btnRemove.setVisible(true);
                name.setEditable(false);
                firstname.setEditable(false);
                street.setEditable(false);
                zipCode.setEditable(false);
                city.setEditable(false);
                phoneNumber.setEditable(false);
                phoneNumber2.setEditable(false);
                email.setEditable(false);
                email2.setEditable(false);

                // push phone numbers in the list
                phoneNumbersList.add(new Phone(categoryPhoneSelected, phoneNumber.getText()));
                phoneNumbersList.add(new Phone(categoryPhoneSelected2, phoneNumber2.getText()));

                // Push emails in the list
                emailsList.add(new MailAdress(categoryEmailSelected, email.getText()));
                emailsList.add(new MailAdress(categoryEmailSelected2, email2.getText()));

                // Add new contact when submit
//                PostAdress adress = new PostAdress(street.getText(), zipCode.getText(), city.getText());
                Contactbook newContact = new Contactbook(name.getText(),
                        firstname.getText(),
                        adress,
                        emailsList,
                        phoneNumbersList,
                        listOfGroups
                );

                // Push new contact in the list and save in JSON file
                // code ...
            }
        });

        // Event click to update contact
        updateContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // set update form contact to visible and hide detail panel
//                contentPanelDetail.setVisible(false);
//                contentPanelForm.setVisible(true);
                submitNewContact.setVisible(true);
                btnAddNewContact.setVisible(false);
                updateContact.setVisible(false);
                btnCancel.setVisible(true);
                btnRemove.setVisible(false);
                name.setEditable(true);
                firstname.setEditable(true);
                street.setEditable(true);
                zipCode.setEditable(true);
                city.setEditable(true);
                phoneNumber.setEditable(true);
                phoneNumber2.setEditable(true);
                email.setEditable(true);
                email2.setEditable(true);

                // fill form fields with values of contact selected
                // code...
                // update contact, update list of contacts and save in JSON file 
                // code...
            }
        });

        // Event to delete contact
        btnRemove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // remove contact from json file
                // code...
            }
        });

        // Event click cancel action
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // set update form contact to visible and hide detail panel
//                contentPanelDetail.setVisible(true);
//                contentPanelForm.setVisible(false);
                submitNewContact.setVisible(false);
                btnAddNewContact.setVisible(true);
                updateContact.setVisible(true);
                btnCancel.setVisible(false);
                btnRemove.setVisible(true);
                name.setEditable(false);
                firstname.setEditable(false);
                street.setEditable(false);
                zipCode.setEditable(false);
                city.setEditable(false);
                phoneNumber.setEditable(false);
                phoneNumber2.setEditable(false);
                email.setEditable(false);
                email2.setEditable(false);

                // reset fields
                // code...
            }
        });

        // define boxes
        BoxLayout boxLayoutFirstCol = new BoxLayout(groupPanel, BoxLayout.Y_AXIS);
        groupPanel.setLayout(boxLayoutFirstCol);
        BoxLayout boxLayoutGroup = new BoxLayout(headerPanelGroup, BoxLayout.X_AXIS);
        headerPanelGroup.setLayout(boxLayoutGroup);
        BoxLayout boxLayoutListGroup = new BoxLayout(contentPanelGroup, BoxLayout.Y_AXIS);
        contentPanelGroup.setLayout(boxLayoutListGroup);
//        BoxLayout boxLayoutPanelGroup = new BoxLayout(groupPanel, BoxLayout.Y_AXIS);
//        groupPanel.setLayout(boxLayoutPanelGroup);
        BoxLayout boxLayoutHeaderSearch = new BoxLayout(searchPanel, BoxLayout.X_AXIS);
        searchPanel.setLayout(boxLayoutHeaderSearch);
        BoxLayout boxLayoutLastCol = new BoxLayout(listPanel, BoxLayout.Y_AXIS);
        listPanel.setLayout(boxLayoutLastCol);
        BoxLayout boxlayoutList = new BoxLayout(listPanel, BoxLayout.Y_AXIS);
        listPanel.setLayout(boxlayoutList);
        BoxLayout boxlayoutForm = new BoxLayout(detailPanel, BoxLayout.Y_AXIS);
        detailPanel.setLayout(boxlayoutForm);
        BoxLayout boxLayoutAction = new BoxLayout(actionPanel, BoxLayout.X_AXIS);
        actionPanel.setLayout(boxLayoutAction);

        // Set content of panel group
        headerPanelGroup.add(lblGroup);
        headerPanelGroup.add(btnAddGroup);
        contentPanelGroup.add(componentGroupsList);
        groupPanel.add(headerPanelGroup);
        groupPanel.add(contentPanelGroup);
        groupPanel.setPreferredSize(new Dimension(150, 800));

        // Custon Panel group
        headerPanelGroup.setBackground(COLOR_GREY);
        lblGroup.setForeground(Color.white);
        groupPanel.setBackground(COLOR_DARK);
        componentGroupsList.setBackground(Color.darkGray);
        componentGroupsList.setForeground(Color.white);
        btnAddGroup.setPreferredSize(new Dimension(25, 25));
        btnAddGroup.setBackground(COLOR_WEB);
        btnAddGroup.setBorder(BorderFactory.createLineBorder(COLOR_WEB));
        btnAddGroup.setOpaque(true);
        btnAddGroup.setForeground(Color.white);

        // Set content of panel contacts list
        headerList.add(titleList);
        headerList.setMaximumSize(new Dimension(500, 50));
        searchPanel.add(search);
        searchPanel.add(btnSearch);
        searchPanel.setMaximumSize(new Dimension(500, 25));
        listPanel.add(headerList);
        listPanel.add(searchPanel);
        listPanel.add(componentContactsList);

        // Customn Panel list
        headerList.setBackground(Color.white);
        searchPanel.setBackground(Color.white);
        listPanel.setBackground(Color.white);
        listPanel.setBorder(BorderFactory.createLineBorder(COLOR_LIGHT));
        btnAddNewContact.setBorder(BorderFactory.createLineBorder(COLOR_WEB));
        btnSearch.setBackground(COLOR_WEB);
        btnSearch.setOpaque(true);
        btnSearch.setBorder(BorderFactory.createLineBorder(COLOR_WEB));
        btnSearch.setForeground(Color.white);
        btnSearch.setPreferredSize(new Dimension(25, 25));
        btnAddNewContact.setOpaque(true);
        btnAddNewContact.setBackground(COLOR_WEB);
        btnAddNewContact.setForeground(Color.white);
        updateContact.setOpaque(true);
        updateContact.setBackground(Color.white);
        updateContact.setForeground(COLOR_WEB);
        updateContact.setBorder(BorderFactory.createLineBorder(COLOR_WEB));
        submitNewContact.setOpaque(true);
        submitNewContact.setBackground(COLOR_WEB);
        submitNewContact.setForeground(Color.white);
        submitNewContact.setBorder(BorderFactory.createLineBorder(COLOR_WEB));
        btnCancel.setOpaque(true);
        btnCancel.setBackground(Color.white);
        btnCancel.setForeground(COLOR_WEB);
        btnCancel.setBorder(BorderFactory.createLineBorder(COLOR_WEB));

        // set content of header in the panel detail
        headerPanelForm.add(btnAddNewContact);
        headerPanelForm.setMaximumSize(new Dimension(500, 40));

        // Set content of detail and form of contact
        contentPanelForm.add(lblName);
        contentPanelForm.add(name);
        contentPanelForm.add(lblFirstname);
        contentPanelForm.add(firstname);
        contentPanelForm.add(lblAddress);
        contentPanelForm.add(street);
        contentPanelForm.add(zipCode);
        contentPanelForm.add(city);
        contentPanelForm.add(lblPhoneNumber);
        contentPanelForm.add(comboBoxCategoriesListPhone);
        contentPanelForm.add(phoneNumber);
        contentPanelForm.add(lblPhoneNumber2);
        contentPanelForm.add(comboBoxCategoriesListPhone2);
        contentPanelForm.add(phoneNumber2);
        contentPanelForm.add(lblEmail);
        contentPanelForm.add(comboBoxCategoriesListEmail);
        contentPanelForm.add(email);
        contentPanelForm.add(lblEmail2);
        contentPanelForm.add(email2);
        contentPanelForm.add(comboBoxCategoriesListEmail2);
        contentPanelForm.add(email2);
        contentPanelForm.setPreferredSize(new Dimension(500, 800));
        actionPanel.add(updateContact);
        actionPanel.add(submitNewContact);
        actionPanel.add(btnCancel);
        actionPanel.add(btnRemove);

        // Distribute blocks to detail panel
        detailPanel.add(headerPanelForm);
        detailPanel.add(contentPanelDetail);
        detailPanel.add(contentPanelForm);
        detailPanel.add(actionPanel);
        detailPanel.setBackground(Color.white);

        // Init visibility of blocks
//        contentPanelDetail.setVisible(true);
//        contentPanelForm.setVisible(false);
        name.setEditable(false);
        firstname.setEditable(false);
        street.setEditable(false);
        zipCode.setEditable(false);
        city.setEditable(false);
        phoneNumber.setEditable(false);
        phoneNumber2.setEditable(false);
        email.setEditable(false);
        email2.setEditable(false);
        btnAddNewContact.setVisible(true);
        updateContact.setVisible(true);
        submitNewContact.setVisible(false);
        btnCancel.setVisible(false);

        // Distribute containers in the window
        globalPanel.add(groupPanel, BorderLayout.WEST);
        globalPanel.add(listPanel, BorderLayout.CENTER);
        globalPanel.add(detailPanel, BorderLayout.EAST);
    }

}
