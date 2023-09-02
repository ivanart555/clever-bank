package com.ivanart555.cleverbank.dao.impl;

import com.ivanart555.cleverbank.dao.AccountDAO;
import com.ivanart555.cleverbank.entity.Account;
import com.ivanart555.cleverbank.exception.DAOException;

import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    //TODO Add connection to db and implementation
    @Override
    public List<Account> getAll() throws DAOException {
        return null;
    }

    @Override
    public Account getById(Long id) throws DAOException {
        return null;
    }

    @Override
    public void create(Account account) throws DAOException {

    }

    @Override
    public void update(Account account) throws DAOException {

    }

    @Override
    public void delete(Long id) throws DAOException {

    }
}
