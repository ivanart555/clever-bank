package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.dao.AccountDAO;
import com.ivanart555.cleverbank.dao.TransactionDAO;
import com.ivanart555.cleverbank.entity.Account;
import com.ivanart555.cleverbank.entity.Transaction;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.TransactionService;

import java.math.BigDecimal;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionDAO transactionDAO;
    private final AccountDAO accountDAO;

    public TransactionServiceImpl(TransactionDAO transactionDAO, AccountDAO accountDAO) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public void depositMoney(Long accountId, BigDecimal amount) throws ServiceException {
        try {
            Account account = accountDAO.getById(accountId);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get account by id.");
        }

    //TODO


    }

    @Override
    public List<Transaction> getAll() throws ServiceException {
        try {
            return transactionDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all transactions.");
        }
    }

    @Override
    public Transaction getById(Long id) throws ServiceException {
        try {
            return transactionDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get transaction by id.");
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            transactionDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Failed to delete transaction by id.");
        }
    }

    @Override
    public void update(Transaction transaction) throws ServiceException {
        try {
            transactionDAO.update(transaction);
        } catch (DAOException e) {
            throw new ServiceException("Failed to update transaction by id.");
        }
    }

    @Override
    public void create(Transaction transaction) throws ServiceException {
        try {
            transactionDAO.create(transaction);
        } catch (DAOException e) {
            throw new ServiceException("Failed to create transaction.");
        }
    }
}
