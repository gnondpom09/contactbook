/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactbook;

import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author BEN Formation
 */
public class AdressBook {

    public ArrayList<Contactbook> listOfContacts = new ArrayList<Contactbook>();
    public ArrayList<Group> listOfGroups = new ArrayList<Group>();

    /**
     * Constructor
     */
    public AdressBook() {
    }

    /**
     * Get list of contacts
     *
     * @return
     */
    public ArrayList<Contactbook> getListOfContacts() {
        return this.listOfContacts;
    }

    /**
     * Set list of contacts
     *
     * @param contacts
     */
    public void setListOfContacts(ArrayList<Contactbook> contacts) {
        this.listOfContacts = contacts;
    }

    /**
     * Get list of groups
     *
     * @return
     */
    public ArrayList<Group> getListOfGroups() {
        return this.listOfGroups;
    }

    /**
     * set list of groups
     *
     * @param groups
     */
    public void setListOfGroups(ArrayList<Group> groups) {
        this.listOfGroups = groups;
    }

    /**
     * Add new user in the list
     *
     * @param contact
     * @author Benoit Mazzon
     */
    public void addUser(Contactbook contact) {
        listOfContacts.add(contact);
    }

    /**
     * Remove user selected from the list
     *
     * @param contact
     * @author Benoit Mazzon
     */
    public void removeUser(Contactbook contact) {
        listOfContacts.remove(contact);
    }

    /**
     * Find contact in the list by name and display details
     *
     * @param name
     * @param listcontact
     * @return contact
     * @author Benoit Mazzon, Laurent Botella
     */
    public Contactbook findContactByName(String name, ArrayList<Contactbook> listcontact) throws IOException, ParseException {

        // Check name of contact and compare
        Contactbook contactfind = new Contactbook();
        for (Contactbook contactbook : listcontact) {
            if (name.compareTo(contactbook.getName()) == 0) {
                contactfind = contactbook;
            }
        }
        return contactfind;
    }

    /**
     * Read file and get list of contacts
     *
     * @param file
     * @return
     * @author laurent Botella, Benoit Mazzon
     */
    public ArrayList<Contactbook> getListOfContactsFromFile(String filePath) {

        try {

            // read the json file
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader("src/contactbook/" + filePath));
            JSONArray jsonArray = (JSONArray) object;

            // List all contacts in the file
            for (Object user : jsonArray) {

                // Initialize new contact
                Contactbook contact = new Contactbook();
                JSONObject jsonObjectUser = (JSONObject) user;

                // set properties of contact from json file
                contact.setName((String) jsonObjectUser.get("name"));
                contact.setFirstname((String) jsonObjectUser.get("firstname"));
                JSONObject jsonObjectPostAdress = (JSONObject) jsonObjectUser.get("postadress");
                JSONArray jsonArrayPhones = (JSONArray) jsonObjectUser.get("phone");
                JSONArray jsonArrayEmails = (JSONArray) jsonObjectUser.get("mailadress");

                // set properties od adress instance
                PostAdress adress = new PostAdress();
                adress.setStreet((String) jsonObjectPostAdress.get("street"));
                adress.setCodepost((String) jsonObjectPostAdress.get("codepost"));
                adress.setTown((String) jsonObjectPostAdress.get("town"));

                // Set adress of contact
                contact.setPostAdress((PostAdress) adress);

                // Set phone list of user
                ArrayList<Phone> phones = new ArrayList<Phone>();
                for (Object item : jsonArrayPhones) {

                    // Set proerties of each phone
                    Phone phone = new Phone();
                    JSONObject phoneObject = (JSONObject) item;
                    phone.setLibelle((String) phoneObject.get("libelle"));
                    phone.setNumber((String) phoneObject.get("number"));

                    // add each phone item in collection
                    phones.add(phone);

                }
                // Set phone list of user
                contact.setPhone((ArrayList<Phone>) phones);

                // Set email list of user
                ArrayList<MailAdress> emails = new ArrayList<MailAdress>();
                for (Object itemEmail : jsonArrayEmails) {

                    // Set properties of each email
                    MailAdress email = new MailAdress();
                    JSONObject emailObject = (JSONObject) itemEmail;
                    email.setLibelle((String) emailObject.get("libelle"));
                    email.setAdress((String) emailObject.get("adress"));

                    // Add each email in the collection
                    emails.add(email);
                }
                // Set email list of user
                contact.setMailAdress((ArrayList<MailAdress>) emails);

                // add contact to collection
                this.listOfContacts.add(contact);
            }

        } catch (Exception e) {

            System.out.println(e);

        }
        return listOfContacts;
    }

    /**
     * Read file and get list of groups
     *
     * @param file
     * @return
     * @author Laurent Botella
     */
    public ArrayList<Group> getListOfGroupsFromFile(String filePath) {
        try {
            // read the json file
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader("src/contactbook/" + filePath));
            JSONArray jsonArray = (JSONArray) object;

            for (Object groups : jsonArray) {
                // Initialize new group
                Group groupe = new Group();
                JSONObject jsonObjectGroup = (JSONObject) groups;

                // set properties of group from json file
                groupe.setName((String) jsonObjectGroup.get("name"));

                // add group to collection
                this.listOfGroups.add(groupe);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return listOfGroups;
    }

    /**
     * Add new user in the json file file
     *
     * @param file
     * @param groups
     * @param contacts
     * @author Benoit Mazzon, Laurent Botella
     */
    public void addNewUserInListOfContacts(Contactbook person, String filePath, ArrayList<Group> groups, ArrayList<Contactbook> contacts) {

        JSONArray jsonContactsArray = new JSONArray();

        // Add new contact in the global list
        
        contacts.add(person);

        for (Contactbook contact : contacts) {

            JSONObject jsonObj = new JSONObject();
            // set properties of new contact
            jsonObj.put("name", contact.getName()); // Set the first name/pair 
            jsonObj.put("firstname", contact.getFirstname());

            // set Adress object
            JSONObject addressList = new JSONObject();
            addressList.put("codepost", contact.getPostAdress().getCodepost());
            addressList.put("street", contact.getPostAdress().getStreet());
            addressList.put("town", contact.getPostAdress().getTown());
            jsonObj.put("postadress", addressList);

            // Set phone 1 and phone 2 properties
            JSONObject phone1 = new JSONObject();
            JSONObject phone2 = new JSONObject();

            if (contact.getPhone() != null) {
                for (int i = 0; i < contact.getPhone().size(); i++) {
                    System.out.println(contact.getPhone().get(i).getLibelle());
                    phone1.put("libelle", contact.getPhone().set(0, contact.getPhone().get(i)).getLibelle());
                    phone1.put("number", contact.getPhone().set(0, contact.getPhone().get(i)).getNumber());
                    phone2.put("libelle", contact.getPhone().set(1, contact.getPhone().get(i)).getLibelle());
                    phone2.put("number", contact.getPhone().set(1, contact.getPhone().get(i)).getNumber());
                }
            }

            // Set list of phones
            JSONArray phoneList = new JSONArray();
            phoneList.add(phone1);
            phoneList.add(phone2);
            jsonObj.put("phone", phoneList);

            // Set email 1 and email 2 properties
            JSONObject email1 = new JSONObject();
            JSONObject email2 = new JSONObject();

            if (contact.getMailAdress() != null) {
                for (int i = 0; i < contact.getMailAdress().size(); i++) {
                    email1.put("libelle", contact.getMailAdress().set(0, contact.getMailAdress().get(i)).getLibelle());
                    email1.put("adress", contact.getMailAdress().set(0, contact.getMailAdress().get(i)).getAdress());
                    email2.put("libelle", contact.getMailAdress().set(1, contact.getMailAdress().get(i)).getLibelle());
                    email2.put("adress", contact.getMailAdress().set(1, contact.getMailAdress().get(i)).getAdress());
                }
            }

            // Set list of emails
            JSONArray emailList = new JSONArray();
            emailList.add(email1);
            emailList.add(email2);
            jsonObj.put("mailadress", emailList);

            // add contact to list of objects
            jsonContactsArray.add(jsonObj);
        }

        // Update JSon file
        writeListInJsonFile(jsonContactsArray, filePath);

    }

    /**
     * Update list of contacts with user updated
     *
     * @param name
     * @param listcontact
     * @return contact
     * @author Benoit Mazzon, Laurent Botella
     */
    public ArrayList<Contactbook> removeContact(Contactbook userFind, ArrayList<Contactbook> listContacts) {

        // Check name of contact and compare
        ArrayList newList = new ArrayList<Contactbook>();
        for (Contactbook contactbook : listContacts) {
            System.out.println(contactbook.getName());
            if (contactbook.getName().compareTo(userFind.getName()) != 0) {
                newList.add(contactbook);
            }
        }

        // return new list updated
        return newList;
    }

    /**
     * update json file
     *
     * @param list lsit of contacts or group
     * @param filePath file to update
     */
    private void writeListInJsonFile(JSONArray list, String filePath) {

        // Write new contact in the file
        try (FileWriter file = new FileWriter("src/contactbook/" + filePath)) {
            file.write(list.toJSONString());
            file.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
