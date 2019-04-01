/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
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
    public Color COLOR_WEB = Color.decode("#00FFCC");
    public Color COLOR_LIGHT = Color.lightGray;
    private ArrayList groupList;
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
    public static final String FILEGROUP = "src/contactbook/groups.json";

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

        // get datas from file and fill collection of contacts
        Contactbook person = new Contactbook();
        AdressBook listsManager = new AdressBook();
        listsManager.getListOfContactsFromFile(FILECONTACT);
        listsManager.getListOfGroupsFromFile(FILEGROUP);

        // Distribute blocks
        globalPanel.setLayout(new BorderLayout());

        //////////////////////////////////////////////////////////////////////
        ////////////////////////////// PANE GROUPS ///////////////////////////
        //////////////////////////////////////////////////////////////////////
        JPanel headerPanelGroup = new JPanel();
        headerPanelGroup.setLayout(new GridLayout(0, 2));
        JLabel lblGroup = new JLabel("Groupes");
        JPanel contentPanelGroup = new JPanel();
        contentPanelGroup.setLayout(new GridLayout(0, 2));

        // Get names list of groups from JSON file
        DefaultListModel groupsListModel = new DefaultListModel();
//        for (Object group : listsManager.getListOfGroups()) {
//            System.out.println("groupe : " + group.getName());
//        }
//        groupsListModel.addElement(listOfGroups);

        // Fill list panel with list of groups
        JList componentGroupsList = new JList(groupsListModel);

        // Button to add new group
        JButton btnAddGroup = new JButton("+");

        // Get list of groups
        // code ...
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
        JLabel titleList = new JLabel("liste des contacts");

        // Get names list of groups from JSON file
        DefaultListModel contactsListModel = new DefaultListModel();
        // test display contacts
        for (Contactbook user : listsManager.getListOfContacts()) {
            System.out.println("item : " + user.getName() + " - " + user.getFirstname());
            contactsListModel.addElement(listOfContacts);
        }

        // Fill list panel with list of groups
        JList componentContactsList = new JList(contactsListModel);

        // Get list of contacts
        listOfContacts = listsManager.getListOfContacts();

        //////////////////////////////////////////////////////////////////////
        ////////////////////////////// PANE DETAIL ///////////////////////////
        //////////////////////////////////////////////////////////////////////
        JPanel contentPanelDetail = new JPanel();
        contentPanelDetail.setLayout(new GridLayout(0, 1));
        JPanel headerPanelForm = new JPanel();
        headerPanelForm.setLayout(new GridLayout(0, 2));
        JPanel contentPanelForm = new JPanel();
        contentPanelForm.setLayout(new GridLayout(0, 1));

        //////////////////////////////////////////////////////////////////////
        ////////////////////////////// PANE FORM /////////////////////////////
        //////////////////////////////////////////////////////////////////////
        //contentPanelForm.setLayout(new GridLayout(1, 2));
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
        name.setText((person.getName() != null) ? person.getName() : "Saisissez votre nom");
        firstname.setText((person.getFirstname() != null) ? person.getFirstname() : "Saisissez votre prénom");
        street.setText((person.getPostAdress() != null) ? person.getPostAdress().getStreet() : "Rue...");
        zipCode.setText((person.getPostAdress() != null) ? person.getPostAdress().getCodepost() : "Code postal...");
        city.setText((person.getPostAdress() != null) ? person.getPostAdress().getTown() : "Ville...");
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
        JButton btnAddNewContact = new JButton("Ajouter");
        JButton submitNewContact = new JButton("Valider");
        JButton updateContact = new JButton("Modifier");
        JButton btnCancel = new JButton("Annuler");

        // Event click to add new contact
        btnAddNewContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // set form of new contact to visible and hide detail panel
                contentPanelDetail.setVisible(false);
                contentPanelForm.setVisible(true);
                btnAddNewContact.setVisible(false);
                updateContact.setVisible(false);
                submitNewContact.setVisible(true);
                btnCancel.setVisible(true);
            }
        });

        // Event click to submit new contact
        submitNewContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // set update form contact to visible and hide detail panel
                contentPanelDetail.setVisible(true);
                contentPanelForm.setVisible(false);
                btnAddNewContact.setVisible(true);
                updateContact.setVisible(true);
                submitNewContact.setVisible(false);
                btnCancel.setVisible(false);

                // push phone numbers in the list
                phoneNumbersList.add(new Phone(categoryPhoneSelected, phoneNumber.getText()));
                phoneNumbersList.add(new Phone(categoryPhoneSelected2, phoneNumber2.getText()));

                // Push emails in the list
                emailsList.add(new MailAdress(categoryEmailSelected, email.getText()));
                emailsList.add(new MailAdress(categoryEmailSelected2, email2.getText()));

                // Add new contact when submit
                PostAdress adress = new PostAdress(street.getText(), zipCode.getText(), city.getText());
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
                contentPanelDetail.setVisible(false);
                contentPanelForm.setVisible(true);
                submitNewContact.setVisible(true);
                btnAddNewContact.setVisible(false);
                updateContact.setVisible(false);
                btnCancel.setVisible(true);

                // fill form fields with values of contact selected
                // code...
                // update contact, update list of contacts and save in JSON file 
                // code...
            }
        });

        // Event to validate update contact
        // code...
        // Event click cancel action
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // set update form contact to visible and hide detail panel
                contentPanelDetail.setVisible(true);
                contentPanelForm.setVisible(false);
                submitNewContact.setVisible(false);
                btnAddNewContact.setVisible(true);
                updateContact.setVisible(true);
                btnCancel.setVisible(false);

                // reset fields
                // code...
            }
        });

        // Set content of panel group
        headerPanelGroup.add(lblGroup);
        headerPanelGroup.add(btnAddGroup);
        contentPanelGroup.add(componentGroupsList);
        groupPanel.add(headerPanelGroup);
        groupPanel.add(contentPanelGroup);

        // Set content of panel contacts list
        listPanel.add(titleList);
        listPanel.add(componentContactsList);
        listPanel.setBackground(COLOR_WEB);

        // set content of header in the panel detail
        headerPanelForm.add(btnAddNewContact);
        headerPanelForm.add(updateContact);
        headerPanelForm.add(submitNewContact);
        headerPanelForm.add(btnCancel);

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

        // Distribute blocks to detail panel
        detailPanel.add(headerPanelForm);
        detailPanel.add(contentPanelDetail);
        detailPanel.add(contentPanelForm);
        detailPanel.setBackground(Color.white);

        // Init visibility of blocks
        contentPanelDetail.setVisible(true);
        contentPanelForm.setVisible(false);
        btnAddNewContact.setVisible(true);
        updateContact.setVisible(true);
        submitNewContact.setVisible(false);
        btnCancel.setVisible(false);

        // Distribute containers in the window
        globalPanel.add(groupPanel, BorderLayout.WEST);
        globalPanel.add(listPanel, BorderLayout.CENTER);
        globalPanel.add(detailPanel, BorderLayout.EAST);
    }

    public void setFormNewContact() {

    }

}
