package by.itstep.phonebook.dao.entity.pojo;

import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.dao.entity.Group;
import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;
import java.util.List;

public class ContactHasGroup {

    @CsvBindByName(column = "id")
    private Long id;
    @CsvBindByName(column = "contact_id")
    private Long contactId;
    @CsvBindByName(column = "group_id")
    private Long groupId;

    public ContactHasGroup(Long id, Long contactId, Long groupId) {
        this.id = id;
        this.contactId = contactId;
        this.groupId = groupId;
    }

    public ContactHasGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public static List<ContactHasGroup> parse(Contact contact, Long id) {
        List<ContactHasGroup> contactHasGroups = new ArrayList<>();
        Long contactId = contact.getId();
        for (Group group: contact.getGroups()){
            contactHasGroups.add(new ContactHasGroup(id, contactId, group.getId()));
            id++;
        }
        return contactHasGroups;
    }
}
