/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactbook;

import java.util.ArrayList;



/**
 *
 * @author gnondpom
 */
public class Contactbook {

    private String name;
    private String firstname;
    private ArrayList<Phone> phone;
    private PostAdress postadress;
    private ArrayList<MailAdress> mailadress;
    private ArrayList<String> group;

    public Contactbook() {
    }

    public Contactbook(String pname, 
            String pfirstname, 
            PostAdress ppostadress, 
            ArrayList<Phone> pphoneList, 
            ArrayList<MailAdress> pmailadressList, 
            ArrayList<String> pgroup) {
        name = pname;
        firstname = pfirstname;
        phone = pphoneList;
        postadress = ppostadress;
        mailadress = pmailadressList;
        group = pgroup;

    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phone
     */
    public ArrayList<Phone> getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(ArrayList<Phone> phone) {
        this.phone = phone;
    }

    /**
     * @return the postadress
     */
    public PostAdress getPostAdress() {
        return postadress;
    }

    /**
     * @param postadress the postadress to set
     */
    public void setPostAdress(PostAdress postadress) {
        this.postadress = postadress;
    }

    /**
     * @return the mailadress
     */
    public ArrayList<MailAdress> getMailAdress() {
        return mailadress;
    }

    /**
     * @param mailadress the mailadress to set
     */
    public void setMailAdress(ArrayList<MailAdress> mailadress) {
        this.mailadress = mailadress;
    }

    /**
     * @return the group
     */
    public ArrayList<String> getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(ArrayList<String> group) {
        this.group = group;
    }

    
    public void setInformation (String pname, 
            String pfirstname, 
            PostAdress ppostadress, 
            ArrayList<Phone> pphoneList, 
            ArrayList<MailAdress> pmailadressList, 
            ArrayList<String> pgroup){
    
    }
} //fin de classe
