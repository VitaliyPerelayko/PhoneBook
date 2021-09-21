package by.itstep.phonebook.dao.entity;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "group")
@Table(name = "circle")
public class Group {

    @CsvBindByName(column = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CsvBindByName(column = "name")
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "groups")
    private Set<Contact> contacts;

    public Group(Long id, String name, Set<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
    }

    public Group(String name) {
        this.name = name;
    }

    public Group() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return name;
    }
}
