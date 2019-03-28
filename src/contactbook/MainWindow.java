/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactbook;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author BEN Formation, Laurent Botella
 */
public class MainWindow extends JFrame implements AdressBook {

    public JFrame frame;
    private static final long serialVersionUID = 2026580921442617098L;
    private final JPanel content;
    // blocks
    private final JPanel groupPanel;
    private final JPanel rightPanel;
    private final JPanel leftPanel;
    private final JPanel contactPanel;
    private final JPanel contactFormPanel;
    private final JPanel listPanel;
    public Color COLOR_WEB = Color.decode("#00FFCC");
    public Color COLOR_LIGHT = Color.lightGray;
    private final JTextField nameField;
    private final JTextField firstname;
    private final JTextField street;
    private final JTextField zipCode;
    private final JTextField city;
    private String categoryPhoneSelected;
    private String categoryPhoneSelected2;
    private final JTextField phoneNumber;
    private final JTextField phoneNumber2;
    private String categoryEmailSelected;
    private String categoryEmailSelected2;
    private final JTextField email;
    private final JTextField email2;
    private final ArrayList categoriesList;
    private final ArrayList phoneNumbersList;
    private final ArrayList emailsList;

    /**
     * Constructor
     */
    public MainWindow() {
        this.setTitle("Carnet d'adresses");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new JPanel();
        groupPanel = new JPanel();
        rightPanel = new JPanel();
        leftPanel = new JPanel();
        contactPanel = new JPanel();
        contactFormPanel = new JPanel();
        nameField = new JTextField();
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
        categoriesList = new ArrayList<Category>();
        phoneNumbersList = new ArrayList<Phone>();
        emailsList = new ArrayList<MailAdress>();

        contactPanel.setVisible(true);
        contactFormPanel.setVisible(false);

        this.setContentPane(content);
    }

    /**
     * Display components of application
     *
     * @author Laurent Botella
     */
    public void setContent() {

        // Distribute blocks
        content.setLayout(new GridLayout(0, 2));
        leftPanel.add(groupPanel);
        leftPanel.add(contactPanel);
        rightPanel.add(listPanel);
        content.add(rightPanel);
        content.add((leftPanel));

        //////////////////////////////////////////////////////////////////////
        ////////////////////////////// PART GROUPS ///////////////////////////
        //////////////////////////////////////////////////////////////////////
        JLabel lblGroup = new JLabel("Groupes");
        JList listOfGroups = new JList(); // add instance list of groups
        JButton btnAddGroup = new JButton();
        btnAddGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add new group of contacts
                // code...
            }
        });
        groupPanel.add(lblGroup);
        groupPanel.add(listOfGroups);
        groupPanel.add(btnAddGroup);

        //////////////////////////////////////////////////////////////////////
        ////////////////////////////// PART CONTACT //////////////////////////
        //////////////////////////////////////////////////////////////////////
        JLabel lblName = new JLabel("Nom");
        JLabel lblFirstname = new JLabel("Prénom");
        PostAdress adress = new PostAdress();
        //adress.createAdress(street.getText(), zipCode.getText(), city.getText());
        JComboBox comboBoxCategoriesListPhone = new JComboBox((ComboBoxModel) categoriesList);
        comboBoxCategoriesListPhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // select type of phone
                categoryPhoneSelected = comboBoxCategoriesListPhone.getSelectedItem().toString();
            }
        });
        JComboBox comboBoxCategoriesListPhone2 = new JComboBox((ComboBoxModel) categoriesList);
        comboBoxCategoriesListPhone2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // select type of phone
                categoryPhoneSelected2 = comboBoxCategoriesListPhone2.getSelectedItem().toString();
            }
        });
        JComboBox comboBoxCategoriesListEmail = new JComboBox((ComboBoxModel) categoriesList);
        comboBoxCategoriesListEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // select type of phone
                categoryEmailSelected = comboBoxCategoriesListEmail.getSelectedItem().toString();
            }
        });
        JComboBox comboBoxCategoriesListEmail2 = new JComboBox((ComboBoxModel) categoriesList);
        comboBoxCategoriesListEmail2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // select type of phone
                categoryEmailSelected2 = comboBoxCategoriesListEmail2.getSelectedItem().toString();
            }
        });
        JButton btnAddNewContact = new JButton();
        btnAddNewContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // set form of new contact to visible and hide detail panel
                contactPanel.setVisible(false);
                contactFormPanel.setVisible(true);
            }
        });
        JButton submitNewContact = new JButton();
        submitNewContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add new contact when submit
                Contactbook newContact = new Contactbook();
                // push phone numbers in the list
//                phoneNumbersList.add(new Phone(categoryPhoneSelected, phoneNumber.getText()));
//                phoneNumbersList.add(new Phone(categoryPhoneSelected2, phoneNumber2.getText()));
                // Push emails in the list
//                emailsList.add(new MailAdress(categoryEmailSelected, email.getText()));
//                emailsList.add(new MailAdress(categoryEmailSelected2, email2.getText()));
                // Create new contact with values
                newContact.createNewContact(nameField.getText(),
                        firstname.getText(),
                        adress,
                        emailsList,
                        phoneNumbersList
                );
                // Push new contact in the list and save in JSON file
                // code...
            }
        });
        JButton updateNewContact = new JButton();
        updateNewContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // set update form contact to visible and hide detail panel
                contactPanel.setVisible(false);
                contactFormPanel.setVisible(true);
                // fill form fields with values of contact selected
                // code...
                // update contact and save in JSON file 
                // code...
            }
        });
        contactPanel.add(lblName);
        contactPanel.add(nameField);
        contactPanel.add(lblFirstname);
        contactPanel.add(firstname);
        contactPanel.add(comboBoxCategoriesListPhone);
        contactPanel.add(phoneNumber);
        contactPanel.add(comboBoxCategoriesListPhone2);
        contactPanel.add(phoneNumber2);
        contactPanel.add(comboBoxCategoriesListEmail);
        contactPanel.add(email);
        contactPanel.add(comboBoxCategoriesListEmail2);
        contactPanel.add(email2);

        //////////////////////////////////////////////////////////////////////
        ////////////////////////////// PART LIST /////////////////////////////
        //////////////////////////////////////////////////////////////////////
    }

    public void setFormNewContact() {

    }

    /**
     * Display list of contacts
     *
     * @author Laurent Botella
     */
    @Override
    public void Display() {

    }

}
