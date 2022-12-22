package org.miage.carnet_contact.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Adresse")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adresse_id ", nullable = false)
    private long id;


    @Size(min = 2, max = 100)
    @NotBlank
    @Column(name = "street")
    private String Street;

    @Size(min = 2, max = 30)
    @NotBlank
    @Column(name = "city")
    private String City;

    @Size(min = 2, max = 20)
    @NotBlank
    @Column(name = "zip")
    private String zip;

    @Size(min = 2, max = 20)
    @NotBlank
    @Column(name = "country")
    private String Country;

    @JsonIgnore
    @OneToOne(mappedBy="adresse")
    private Contact contact;


    public Adresse(String street, String city, String zip, String country) {
        Street = street;
        City = city;
        this.zip = zip;
        Country = country;
    }

    public Adresse() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
