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
//import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author BEN Formation
 */
public class AdressBook {

    private ArrayList<Contactbook> listOfContacts = new ArrayList<Contactbook>();
    private ArrayList<String> listOfGroups = new ArrayList<String>();

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
    public ArrayList<String> getListOfGroups() {
        return this.listOfGroups;
    }

    /**
     * set list of groups
     *
     * @param groups
     */
    public void setListOfGroups(ArrayList<String> groups) {
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
     */
    public ArrayList<Contactbook> getListOfContactsFromFile(String filePath) {

        return listOfContacts;
    }

    /**
     * Read file and get list of groups
     *
     * @param file
     * @return
     */
    public ArrayList<String> getListOfGroupsFromFile(String filePath) {
        System.out.println("test");
        try {
            // read the json file
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader("src/contactbook/" + filePath));
            JSONObject jsonArray = (JSONObject) object;
            
            System.out.println(jsonArray);


            File directory = new File("src/contactbook");
            System.out.println(directory.getAbsolutePath());

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
    public void updateJsonFile(String filePath, ArrayList<String> groups, ArrayList<Contactbook> contacts) {

    }

}
