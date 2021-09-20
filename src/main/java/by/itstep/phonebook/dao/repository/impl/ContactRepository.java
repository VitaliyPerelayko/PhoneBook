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
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-mysql-phone-book");
        entityManager = factory.createEntityManager();
    }

    @Override
    public Contact save(Contact contact) {
        return null;
    }

    @Override
    public List<Contact> findAll() {
        return entityManager.createQuery("FROM contact c",Contact.class).getResultList();
    }
}
