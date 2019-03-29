/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactbook;

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
        test.getListOfGroupsFromFile("contacts.json");

    }
}
