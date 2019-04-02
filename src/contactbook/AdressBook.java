/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactbook;

import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
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
        Contactbook contactfind = new Contactbook();
        // Check name of contact and compare
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
     * Get list of contacts and groups updated and write collections in Json
     * file
     *
     * @param file
     * @param groups
     * @param contacts
     */
    public void updateJsonFile(Contactbook person, String filePath, ArrayList<Group> groups, ArrayList<Contactbook> contacts) {

        // Here we convert Java Object to JSON 
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", person.getName()); // Set the first name/pair 
        jsonObj.put("firstname", person.getFirstname());
        //faire boucle pour l'adresse
        jsonObj.put("codepost", person.getPostAdress().getCodepost());
        jsonObj.put("street", person.getPostAdress().getStreet());
        jsonObj.put("town", person.getPostAdress().getTown());

//            for (Object postAdres : person.getPostAdress()) {
//                postAdres.put("address", person.getMailAdress().get(0));
//                jsonAdd.put("city", person.getAddress().getCity());
//                jsonAdd.put("state", person.getAddress().getState());
//            }
        // We add the object to the main object
        //jsonObj.put("address", jsonAdd);
        // and finally we add the phone number
        // In this case we need a json array to hold the java list
//            JSONArray jsonArr = new JSONArray();
//
//            for (PhoneNumber pn : person.getPhoneList()) {
//                JSONObject pnObj = new JSONObject();
//                pnObj.put("num", pn.getNumber());
//                pnObj.put("type", pn.getType());
//                jsonArr.put(pnObj);
//            }
//
//            jsonObj.put("phoneNumber", jsonArr);
    }

}
