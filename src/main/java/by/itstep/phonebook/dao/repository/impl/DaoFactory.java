package by.itstep.phonebook.dao.repository.impl;

import by.itstep.phonebook.dao.repository.ContactDAO;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance(){
        if (daoFactory == null){
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public ContactDAO getContactDAO(){
        return new ContactDaoJdbsImpl();
    }
}
