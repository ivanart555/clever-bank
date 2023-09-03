package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.entity.Account;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    @Override
    public List<Account> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Account getById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Long id) throws ServiceException {

    }

    @Override
    public void update(Account account) throws ServiceException {

    }

    @Override
    public void create(Account account) throws ServiceException {

    }
}
