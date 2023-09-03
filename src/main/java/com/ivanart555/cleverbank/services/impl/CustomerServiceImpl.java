package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.dao.CustomerDAO;
import com.ivanart555.cleverbank.entity.Customer;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customer> getAll() throws ServiceException {
        try {
            return customerDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all customers.");
        }
    }

    @Override
    public Customer getById(Long id) throws ServiceException {
        try {
            return customerDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get customer by id.");
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            customerDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Failed to delete customer by id.");
        }
    }

    @Override
    public void update(Customer customer) throws ServiceException {
        try {
            customerDAO.update(customer);
        } catch (DAOException e) {
            throw new ServiceException("Failed to update customer by id.");
        }
    }

    @Override
    public void create(Customer customer) throws ServiceException {
        try {
            customerDAO.create(customer);
        } catch (DAOException e) {
            throw new ServiceException("Failed to create customer.");
        }
    }
}
