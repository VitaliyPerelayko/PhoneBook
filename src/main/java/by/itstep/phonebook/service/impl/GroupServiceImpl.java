package by.itstep.phonebook.service.impl;

import by.itstep.phonebook.dao.entity.Group;
import by.itstep.phonebook.dao.repository.GroupRepository;
import by.itstep.phonebook.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }
}
