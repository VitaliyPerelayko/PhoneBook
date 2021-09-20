package by.itstep.phonebook;

import by.itstep.phonebook.dao.repository.ContactDAO;
import by.itstep.phonebook.dao.repository.impl.ContactDaoJdbsImpl;
import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.dao.repository.impl.ContactRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Run {

    public static void main(String[] args) {
        ContactRepository contactRepository = new ContactRepository();

        contactRepository.findAll().forEach(System.out::println);

        //getAllContacts().forEach(System.out::println);
    }



    private static List<Contact> getAllContacts(){
        ContactDAO instance = new ContactDaoJdbsImpl();
        return instance.findAll();
    }

    private static void pasteContact(){

        Contact contact = new Contact("Ilia", "Madison",
                new HashSet<>(Collections.singletonList("+375-29-1233")), "mad@fakeemail.com", null);
        ContactDAO contactDAO = new ContactDaoJdbsImpl();
        contactDAO.save(contact);
        System.out.println(contact.getId());
    }
}
