package by.itstep.phonebook.dao.entity;

import by.itstep.phonebook.dao.entity.converter.ListStringConverter;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "contact")
@Table(name = "contact")
public class Contact {

    @CsvBindByName(column = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CsvBindByName(column = "firsName")
    @Column(name = "first_name", length = 50)
    private String firsName;
    @Column(name = "last_name", length = 50, nullable = false)
    @CsvBindByName(column = "lastName")
    private String lastName;
    @CsvBindByName(column = "phone_set")
    @Column(name = "phones", nullable = false)
    @Convert(converter = ListStringConverter.class)
    private Set<String> phones = new HashSet<>();
    @Column(name = "email", length = 50)
    @CsvBindByName(column = "email")
    private String email;
    @ManyToMany
    @JoinTable(
            name = "contact_has_group",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "group_Id")
    )
    private Set<Group> groups = new HashSet<>();

    public Contact(String firsName, String lastName, Set<String> phones, String email, Set<Group> groups) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.phones = phones;
        this.email = email;
        this.groups = groups;
    }

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group){
        this.groups.add(group);
    }

    @Override
    public String toString(){
        return String.format("Last Name: %s  Email: %s Phones: %s Groups: %s", lastName, email, phones, groups);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) &&
                Objects.equals(firsName, contact.firsName) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firsName, lastName, email);
    }
}