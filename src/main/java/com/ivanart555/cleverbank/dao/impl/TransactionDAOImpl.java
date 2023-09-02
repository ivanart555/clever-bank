package com.ivanart555.cleverbank.dao.impl;

import com.ivanart555.cleverbank.dao.TransactionDAO;
import com.ivanart555.cleverbank.entity.Transaction;
import com.ivanart555.cleverbank.exception.DAOException;

import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    //TODO Add connection to db and implementation
    @Override
    public List<Transaction> getAll() throws DAOException {
        return null;
    }

    @Override
    public Transaction getById(Long id) throws DAOException {
        return null;
    }

    @Override
    public void create(Transaction transaction) throws DAOException {

    }

    @Override
    public void update(Transaction transaction) throws DAOException {

    }

    @Override
    public void delete(Long id) throws DAOException {

    }
}
