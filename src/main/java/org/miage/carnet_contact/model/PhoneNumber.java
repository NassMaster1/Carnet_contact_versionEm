package org.miage.carnet_contact.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@NoArgsConstructor
@Entity
@Table(name = "PhoneNumber")

public class PhoneNumber {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Long id;


    @Size(min = 2, max = 20)
    @NotBlank
    @Column(name = "phoneKind")
    private String phoneKind;


    @Size(min = 8, max = 20)
    @NotBlank
    @Column(name = "phoneNumber")
    private String phoneNumber;

    //PhoneNum

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="id_contact")
    private Contact contact;

    public PhoneNumber(String phoneKind, String phoneNumber) {
        this.phoneKind = phoneKind;
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber(Long id, String phoneKind, String phoneNumber) {
        this.id = id;
        this.phoneKind = phoneKind;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneKind() {
        return phoneKind;
    }

    public void setPhoneKind(String phoneKind) {
        this.phoneKind = phoneKind;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
