package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.entity.Transaction;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public List<Transaction> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Transaction getById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Long id) throws ServiceException {

    }

    @Override
    public void update(Transaction transaction) throws ServiceException {

    }

    @Override
    public void create(Transaction transaction) throws ServiceException {

    }
}
