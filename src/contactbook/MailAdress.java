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
public class MailAdress {
    
    private String adress;
    private String libelle;
    
    public MailAdress() {
        
    }
    
    public MailAdress (String plibelle, String padress) {
    adress = padress;
    libelle = plibelle;
    System.out.println("constructeur MailAdress");
    }

    /**
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
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
