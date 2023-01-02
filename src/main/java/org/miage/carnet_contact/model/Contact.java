package org.miage.carnet_contact.model;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Entity
//@NamedQuery(name="Contact.findContactById", query="SELECT c FROM Contact c JOIN FETCH c.phoneNumber  WHERE c.id_contact=:id_contact")
@NamedQuery(name="Contact.findContactByFirstName", query="SELECT c FROM Contact c WHERE c.firstName=:firstname")
@NamedQuery(name="Contact.findContactBylastName", query="SELECT c FROM Contact c WHERE c.lastName=:lastName")
@NamedQuery(name="Contact.findContactBykeyWord", query="SELECT c FROM Contact c WHERE c.lastName LIKE :keyword||'%' OR c.firstName LIKE :keyword||'%'")
@NamedQuery(name="Contact.findContactByFirstNameAndLastName", query="SELECT c FROM Contact c WHERE c.firstName=:firstname AND c.lastName=:lastname")
@Table(name = "contact")
public class Contact {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact ")
    private Long id_contact;



    @Size(min = 2, max = 30)
    @NotBlank
    @Column(name = "firstName")
    private String firstName;

    @Size(min = 2, max = 30)
    @NotBlank
    @Column(name = "lastName")
    private String lastName;

    @Size(min = 5, max = 50)
    @Email
    @NotBlank
    @Column(name = "email")
    private String email;



    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Set<PhoneNumber> phoneNumber = new HashSet<>();


    @OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name="id_address")
    private Adresse adresse;


    @ManyToMany(mappedBy="contacts")
    private Set<ContactGroup> contactGroups=new HashSet<>();

    public Contact( String firstName, String lastName, String email, Adresse adresse) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.adresse = adresse;
    }


    public Contact(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //todo modifier le DTO contact simple
    public Contact(Long id_contact, String firstName, String lastName) {
        this.id_contact = id_contact;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public Long getId_contact() {
        return id_contact;
    }

    public void setId_contact(Long id_contact) {
        this.id_contact = id_contact;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PhoneNumber> getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(Set<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Set<ContactGroup> getContactGroups() {
        return contactGroups;
    }

    public void setContactGroups(Set<ContactGroup> contactGroups) {
        this.contactGroups = contactGroups;
    }

    //todo voir si je dois supprimer cette méthode
    public void addToListPhoneNumber(PhoneNumber phoneNumber){
        Set<PhoneNumber> set=new HashSet<>(this.phoneNumber);
        set.add(phoneNumber);
        this.phoneNumber=set;
    }



}
