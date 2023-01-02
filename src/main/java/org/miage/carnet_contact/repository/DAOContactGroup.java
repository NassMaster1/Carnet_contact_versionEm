package org.miage.carnet_contact.repository;

import org.miage.carnet_contact.exception.ContactGroupConflitException;
import org.miage.carnet_contact.exception.ContactGroupNotFoundException;
import org.miage.carnet_contact.exception.ContactNotFoundExceprion;
import org.miage.carnet_contact.model.Contact;
import org.miage.carnet_contact.model.ContactGroup;
import org.miage.carnet_contact.util.JPAutile;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;



@Repository
public class DAOContactGroup implements IDAOContactGroup{

    public DAOContactGroup() {
    }


    @Override
    public Optional<List<ContactGroup>> findAll() {
        //Lors d'un appel à createNativeQuery(requete, type de l'entité retournée)
        //il faut que le select mentionne toutes les colonnes/attributs de l'entité
        final String nativeQuery="SELECT group_id , name_group FROM contactGroup";

        EntityManager em= JPAutile.getEmf().createEntityManager();
        //Version avec position
        List<ContactGroup> contactGroups= em.createNativeQuery(nativeQuery, ContactGroup.class).getResultList();

        em.close();

        return Optional.ofNullable(contactGroups);

    }

    @Override
    public Optional<ContactGroup> findByName_group(String groupName) {

        EntityManager em=JPAutile.getEmf().createEntityManager();

        TypedQuery<ContactGroup> query = em.createNamedQuery("ContactGroup.findGroupByNameGroup", ContactGroup.class);
        query.setParameter("namegroup", groupName);
        ContactGroup contactGroup=null;
        try {
             contactGroup = query.getSingleResult();
        }catch (NoResultException nre){
        }
        em.close();

        return Optional.ofNullable(contactGroup);
    }

    @Override
    public void saveGroup(ContactGroup conatctGroup) {

        //1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
        EntityManager em=JPAutile.getEmf().createEntityManager();

        // 2 : Ouverture transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.merge(conatctGroup);

        // 3 : Persistance Objet/Relationnel : création d'un enregistrement en base
        //em.persist(conatctGroup);

        // 4 : Fermeture transaction
        tx.commit();
        // 5 : Fermeture de l'EntityManager et de unité de travail JPA
        em.close();
    }

    @Override
    public Optional<ContactGroup> findById(Long id) {
        //1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
        EntityManager em=JPAutile.getEmf().createEntityManager();

        // 2 : Ouverture transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 3 : recuperation de l'objet
        ContactGroup contactGroup = em.find(ContactGroup.class,id);
        // 4 : Fermeture transaction
        tx.commit();
        // 5 : Fermeture de l'EntityManager et de unité de travail JPA
        em.close();

        return Optional.ofNullable(contactGroup);
    }

    @Override
    public void AddContactToGroup(Long id_contact, Long id_group){

        //1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
        EntityManager em=JPAutile.getEmf().createEntityManager();
        Query query = em
                .createQuery("SELECT c,g FROM Contact c,ContactGroup g"
                        + " WHERE c.id_contact=:id_contact AND g.group_id=:id_group");

        query.setParameter("id_contact", id_contact);
        query.setParameter("id_group", id_group);

        List<Object[]> req=query.getResultList();
        Contact contact=null;
        ContactGroup contactGroup=null;


        for (Object[] reportDetail : req) {
            contact = (Contact) reportDetail[0];
            contactGroup = (ContactGroup) reportDetail[1];
        }

        // 3 : recuperation de l'objet
        if(contact ==null) throw new ContactNotFoundExceprion(id_contact);
        if(contactGroup==null) throw new ContactGroupNotFoundException(id_group);

        Boolean isPresente=contact.getContactGroups().stream().
                filter(contactGroup1 -> contactGroup1.getGroup_id().equals(id_group)).toList().isEmpty();
        // 2 : Ouverture transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        if(isPresente){
            contact.getContactGroups().add(contactGroup);
            contactGroup.getContacts().add(contact);
        }else {
            throw new ContactGroupConflitException(contact.getFirstName()+" in this group");
        }

        // 4 : Fermeture transaction
        tx.commit();
        // 5 : Fermeture de l'EntityManager et de unité de travail JPA
        em.close();

    }

    @Override
    public void delete(Long id) {

        //1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
        EntityManager em=JPAutile.getEmf().createEntityManager();

        // 2 : Ouverture transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 3 : recuperation de l'objet
        ContactGroup contactGroup = em.find(ContactGroup.class,id);

        if(contactGroup!=null){
            //suppression des relations
            em.remove(contactGroup);
        }else {
            throw new ContactGroupNotFoundException(id);
        }

        // 4 : Fermeture transaction
        tx.commit();
        // 5 : Fermeture de l'EntityManager et de unité de travail JPA
        em.close();
    }

    @Override
    public void UpdateContactGroup( Long idGroup, ContactGroup groupModify) {


        //1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
        EntityManager em=JPAutile.getEmf().createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        ContactGroup group = em.find(ContactGroup.class,idGroup);

        if(group!=null){

            group.setName_group(groupModify.getName_group());
        }
        else{
            throw new ContactGroupNotFoundException(idGroup);
        }

        // 4 : Fermeture transaction
        tx.commit();
        // 5 : Fermeture de l'EntityManager et de unité de travail JPA
        em.close();

    }

    @Override
    public  Optional<List<Contact>> findListContactFromGroup(Long id_group) {
        //1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
        EntityManager em=JPAutile.getEmf().createEntityManager();
        Query query = em
                .createQuery("SELECT g.contacts FROM ContactGroup g"
                        + " WHERE g.group_id=:id_group");

        query.setParameter("id_group", id_group);

        List<Contact> contacts=query.getResultList();


        // 5 : Fermeture de l'EntityManager et de unité de travail JPA
        em.close();

        return Optional.ofNullable(contacts);
    }



}
