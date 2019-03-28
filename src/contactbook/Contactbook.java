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

    private int id_;
    private String name;
    private String firstname;
    private Phone phone;
    private PostAdress postadress;
    private MailAdress mailadress;
    private Group group;

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
    public Phone getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Phone phone) {
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
    public MailAdress getMailAdress() {
        return mailadress;
    }

    /**
     * @param mailadress the mailadress to set
     */
    public void setMailAdress(MailAdress mailadress) {
        this.mailadress = mailadress;
    }

    /**
     * @return the group
     */
    public Group getGroup() {
        return postadress;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @return the id_
     */
    public int getId_() {
        return id_;
    }

    /**
     * @param id_ the id_ to set
     */
    public void setId_(int id_) {
        this.id_ = id_;
    }

    public Contactbook createNewContact(String name, String firstname, PostAdress postadresse, ArrayList<MailAdress> listmailadress, ArrayList<Phone> listphone) {
        Contactbook personne = new Contactbook();

        return personne;
    }

}
