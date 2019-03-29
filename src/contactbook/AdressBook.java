/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactbook;

import java.util.ArrayList;

/**
 *
 * @author BEN Formation
 */
public class AdressBook {

    private ArrayList<Contactbook> contacts = new ArrayList<Contactbook>();
    private String[] group;

    public void addUser(Contactbook contact) {
        contacts.add(contact);

    }

    public void removeUser(Contactbook contact) {
        contacts.remove(contact);
    }

    public void deleteContact(Contactbook contact) {

    }

    public Contactbook findContactByName(String name, ArrayList<Contactbook> listcontact) {
        Contactbook contactfind = new Contactbook();
        for (Contactbook contactbook : listcontact) {
            if (contactbook.getName() == name) {
                contactfind = contactbook;
            }
        }
        return contactfind;
    }
}
