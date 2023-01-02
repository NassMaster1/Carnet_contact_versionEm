package org.miage.carnet_contact.repository;

import org.miage.carnet_contact.exception.ContactNotFoundExceprion;
import org.miage.carnet_contact.exception.PhoneNumberConflitException;
import org.miage.carnet_contact.exception.PhoneNumberNotFoundException;
import org.miage.carnet_contact.model.Contact;
import org.miage.carnet_contact.model.PhoneNumber;
import org.miage.carnet_contact.util.JPAutile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DAOPhoneNumber implements IDAOPhoneNumber{

    /**
     * Ajouter un numéro pour un utilisateur

     * @param id phoneNumber
     * @return
     */

    @Override
    public void addPhoneNumberToContact(Long id, PhoneNumber phoneNumber) {

        //1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
        EntityManager em= JPAutile.getEmf().createEntityManager();

        // 2 : Ouverture transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Contact contact = em.find(Contact.class,id);

        if (contact!=null) {

            Boolean phoneNumberIsExistInContact = contact.getPhoneNumber().stream().filter(
                    phoneNumber1 -> phoneNumber1.getPhoneNumber().equals(phoneNumber.getPhoneNumber())
            ).toList().isEmpty();

            if (phoneNumberIsExistInContact) {
                contact.getPhoneNumber().add(phoneNumber);
                phoneNumber.setContact(contact);
                em.merge(phoneNumber);
            } else {
                throw new PhoneNumberConflitException(phoneNumber.getPhoneNumber() + " in this contact");
            }
        } else {
            throw new ContactNotFoundExceprion(id);
        }

        // 4 : Fermeture transaction
        tx.commit();
        // 5 : Fermeture de l'EntityManager et de unité de travail JPA
        em.close();

    }

    /**
     * Supprimer un numéro pour un utilisateur

     * @param id phoneNumber
     * @return
     */

    @Override
    public void DeletePhoneNumberToContact(Long id, Long id_phoneNumber) {
        final String nativeQuery="DELETE FROM PhoneNumber WHERE id_contact =? AND phone_id=? ";
        //1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
        EntityManager em= JPAutile.getEmf().createEntityManager();

        // 2 : Ouverture transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Contact contact = em.find(Contact.class,id);

        if (contact!=null) {

            List<PhoneNumber> listPhone=contact.getPhoneNumber().stream().filter(
                    phoneNumber1 ->phoneNumber1.getId().equals(id_phoneNumber)).toList();
            if(!listPhone.isEmpty()) {
                Query query = em.createNativeQuery(nativeQuery);
                query.setParameter(1, id);
                query.setParameter(2, id_phoneNumber);
                query.executeUpdate();
            }
            else {
                throw new PhoneNumberNotFoundException(id_phoneNumber+" in this contact");
            }

        } else {
            throw new ContactNotFoundExceprion(id);
        }

        // 4 : Fermeture transaction
        tx.commit();
        // 5 : Fermeture de l'EntityManager et de unité de travail JPA
        em.close();

    }

    @Override
    public void updatehoneNumberToContact(Long idContact, Long idPhoneNumber, PhoneNumber phoneNumber) {
        final String nativeQuery="UPDATE PhoneNumber p SET p.phoneKind=? , p.phoneNumber=? WHERE  id_contact =? AND phone_id=?  ";
        //1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
        EntityManager em= JPAutile.getEmf().createEntityManager();

        // 2 : Ouverture transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Contact contact = em.find(Contact.class,idContact);

        if (contact!=null) {

            List<PhoneNumber> listPhone=contact.getPhoneNumber().stream().filter(
                    phoneNumber1 ->phoneNumber1.getId().equals(idPhoneNumber)).toList();
            if(!listPhone.isEmpty()) {
                Query query = em.createNativeQuery(nativeQuery);
                query.setParameter(1, phoneNumber.getPhoneKind());
                query.setParameter(2, phoneNumber.getPhoneNumber());
                query.setParameter(3, idContact);
                query.setParameter(4, idPhoneNumber);
                query.executeUpdate();
            }
            else {
                throw new PhoneNumberNotFoundException(idPhoneNumber+" in this contact");
            }

        } else {
            throw new ContactNotFoundExceprion(idContact);
        }

        // 4 : Fermeture transaction
        tx.commit();
        // 5 : Fermeture de l'EntityManager et de unité de travail JPA
        em.close();
    }
}
