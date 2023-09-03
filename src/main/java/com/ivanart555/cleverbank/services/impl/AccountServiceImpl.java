package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.dao.AccountDAO;
import com.ivanart555.cleverbank.entity.Account;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public List<Account> getAll() throws ServiceException {
        try {
            return accountDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all accounts.");
        }
    }

    @Override
    public Account getById(Long id) throws ServiceException {
        try {
            return accountDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get account by id.");
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            accountDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Failed to delete account by id.");
        }
    }

    @Override
    public void update(Account account) throws ServiceException {
        try {
            accountDAO.update(account);
        } catch (DAOException e) {
            throw new ServiceException("Failed to update account by id.");
        }
    }

    @Override
    public void create(Account account) throws ServiceException {
        try {
            accountDAO.create(account);
        } catch (DAOException e) {
            throw new ServiceException("Failed to create account.");
        }
    }
}
