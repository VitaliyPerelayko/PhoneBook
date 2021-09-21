package by.itstep.phonebook.dao.repository.impl;

import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.dao.repository.ContactDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ContactRepository implements ContactDAO {

    EntityManager entityManager;

    public ContactRepository() {
        super();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-h2-phone-book");
        entityManager = factory.createEntityManager();
    }

    @Override
    public Contact save(Contact contact) {
        return entityManager.merge(contact);
    }

    @Override
    public List<Contact> findAll() {
        return entityManager.createQuery("FROM contact c",Contact.class).getResultList();
    }

    public Contact find(Long id){
        return entityManager.find(Contact.class, id);
    }
}
