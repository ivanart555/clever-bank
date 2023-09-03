package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.dao.BankDAO;
import com.ivanart555.cleverbank.entity.Bank;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {
    private final BankDAO bankDAO;

    public BankServiceImpl(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    @Override
    public List<Bank> getAll() throws ServiceException {
        try {
            return bankDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all banks.");
        }
    }

    @Override
    public Bank getById(Long id) throws ServiceException {
        try {
            return bankDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get bank by id.");
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            bankDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Failed to delete bank by id.");
        }
    }

    @Override
    public void update(Bank bank) throws ServiceException {
        try {
            bankDAO.update(bank);
        } catch (DAOException e) {
            throw new ServiceException("Failed to update bank by id.");
        }
    }

    @Override
    public void create(Bank bank) throws ServiceException {
        try {
            bankDAO.create(bank);
        } catch (DAOException e) {
            throw new ServiceException("Failed to create bank.");
        }
    }
}
