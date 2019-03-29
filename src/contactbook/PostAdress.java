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
public class PostAdress {
    
    private String street;
    private String codepost;
    private String town;
    
    public PostAdress () {}
    
    public PostAdress (String pstreet, String pcodepost, String ptown) {
    street = pstreet;
    codepost = pcodepost;
    town = ptown;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the codepost
     */
    public String getCodepost() {
        return codepost;
    }

    /**
     * @param codepost the codepost to set
     */
    public void setCodepost(String codepost) {
        this.codepost = codepost;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }
    
    
}
