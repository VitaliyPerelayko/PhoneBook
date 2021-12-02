package by.itstep.phonebook.service;

import by.itstep.phonebook.dao.entity.Group;

import java.util.List;
import java.util.Set;

public interface GroupService {

    List<Group> getAll();

    Group getByName(String name);

    Group save(Group group);
}
