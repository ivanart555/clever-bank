package com.ivanart555.cleverbank.dao.impl;

import com.ivanart555.cleverbank.dao.BankDAO;
import com.ivanart555.cleverbank.entity.Bank;
import com.ivanart555.cleverbank.exception.DAOException;

import java.util.List;

public class BankDAOImpl implements BankDAO {
    //TODO Add connection to db and implementation
    @Override
    public List<Bank> getAll() throws DAOException {
        return null;
    }

    @Override
    public Bank getById(Long id) throws DAOException {
        return null;
    }

    @Override
    public void create(Bank bank) throws DAOException {

    }

    @Override
    public void update(Bank bank) throws DAOException {

    }

    @Override
    public void delete(Long id) throws DAOException {

    }
}
