package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.entity.Bank;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {
    @Override
    public List<Bank> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Bank getById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Long id) throws ServiceException {

    }

    @Override
    public void update(Bank bank) throws ServiceException {

    }

    @Override
    public void create(Bank bank) throws ServiceException {

    }
}
