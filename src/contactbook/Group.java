/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactbook;

/**
 *
 * @author gnondpom
 */
public class Group {
    
    private String name;
    private int nbContacts;
    
    /**
     * Constructor
     */
    public Group() {
        
        System.out.println("constructeur group");
        
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getNbContacts() {
        return this.nbContacts;
    }
    
    public void setNbContacts(int nbContacts) {
        this.nbContacts = nbContacts;
    }
}
