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
public class Phone {
    
    private String number;
    private String libelle;
    
    public Phone() {
        
    }
    
    public Phone (String plibelle, String pnumber) {
    number = pnumber;
    libelle = plibelle;
    System.out.println("constructeur Phone");
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
}
