package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.dao.AccountDAO;
import com.ivanart555.cleverbank.db.DataSource;
import com.ivanart555.cleverbank.entity.Account;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.AccountService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;
    private final Connection connection = DataSource.getConnection();

    public AccountServiceImpl(AccountDAO accountDAO) throws SQLException {
        this.accountDAO = accountDAO;
    }

    @Override
    public List<Account> getAll() throws ServiceException {
        try {
            return accountDAO.getAll(connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all accounts.");
        }
    }

    @Override
    public Account getById(Long id) throws ServiceException {
        try {
            return accountDAO.getById(id, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get account by id.");
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            accountDAO.delete(id, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to delete account by id.");
        }
    }

    @Override
    public void update(Account account) throws ServiceException {
        try {
            accountDAO.update(account, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to update account by id.");
        }
    }

    @Override
    public void create(Account account) throws ServiceException {
        try {
            accountDAO.create(account, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to create account.");
        }
    }
}
