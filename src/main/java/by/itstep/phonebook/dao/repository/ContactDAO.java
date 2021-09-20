package by.itstep.phonebook.dao.repository;

import by.itstep.phonebook.dao.entity.Contact;

import java.util.List;

public interface ContactDAO {

    Contact save(Contact contact);

    List<Contact> findAll();
}
