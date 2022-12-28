package org.miage.carnet_contact.model;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@NamedQuery(name="ContactGroup.findGroupByNameGroup", query="SELECT c FROM ContactGroup c WHERE c.name_group=:namegroup")
@Entity
@Table(name = "contactGroup")
@Slf4j
public class ContactGroup {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long group_id;

    @Size(min = 2, max = 30)
    @NotBlank
    @Column(name = "name_group")
    private String name_group;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinTable(
            name = "join_contact_group",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "id_contact", referencedColumnName = "id_contact ")
    )
    private List<Contact> contacts = new ArrayList<>();



    public ContactGroup(Long group_id, String name_group) {
        this.group_id = group_id;
        this.name_group = name_group;
    }

    public ContactGroup(String name_group) {
        this.name_group = name_group;
    }


    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getName_group() {
        return name_group;
    }

    public void setName_group(String name_group) {
        this.name_group = name_group;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }


    public void addToListContact(Contact contact){
        List<Contact> list=new ArrayList<>(this.contacts);

        list.add(contact);
        this.contacts=list;
    }

}
