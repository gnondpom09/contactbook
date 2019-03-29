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
public class Main {

    public static void main(String[] args) {
        // Display window
        MainWindow book = new MainWindow();
        book.setTitle("test");
        book.setVisible(true);
        AdressBook test = new AdressBook();
        //test.getListOfContactsFromFile("contacts.json");
        test.setListOfContacts(test.getListOfContactsFromFile("contacts.json"));
        ArrayList<Contactbook> testList = new ArrayList<Contactbook>();
 
        System.out.println("test collection");
        System.out.println(test.getListOfContacts());

    }
}
