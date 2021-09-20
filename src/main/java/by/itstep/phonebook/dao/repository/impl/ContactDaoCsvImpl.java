package by.itstep.phonebook.dao.repository.impl;

import by.itstep.phonebook.conection.ConnectionCsvImpl;
import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.dao.entity.Group;
import by.itstep.phonebook.dao.entity.pojo.ContactHasGroup;
import by.itstep.phonebook.dao.repository.ContactDAO;

import java.util.*;

import static by.itstep.phonebook.Properties.CONTACT_FILE_PATH;
import static by.itstep.phonebook.Properties.CONTACT_HAS_GROUP_FILE_PATH;

public class ContactDaoCsvImpl implements ContactDAO {

    private ConnectionCsvImpl connection = ConnectionCsvImpl.getInstance();

    @Override
    public Contact save(Contact contact) {
        Long id = connection.getId(CONTACT_FILE_PATH);
        contact.setId(id);
        connection.writeCsvFromBean(CONTACT_FILE_PATH, Collections.singletonList(contact));
        Set<Group> groups = contact.getGroups();
        if (groups != null && !groups.isEmpty()) {
            Long contactHasGroupId = connection.getId(CONTACT_HAS_GROUP_FILE_PATH);
            List<ContactHasGroup> chgRecords =
                    ContactHasGroup.parse(contact, contactHasGroupId);
            connection.writeCsvFromBean(CONTACT_HAS_GROUP_FILE_PATH, chgRecords);
        }
        return contact;
    }

    @Override
    public List<Contact> findAll() {
        return null;
    }
}
