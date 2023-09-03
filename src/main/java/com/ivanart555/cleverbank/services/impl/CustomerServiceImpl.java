package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.entity.Customer;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<Customer> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Customer getById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Long id) throws ServiceException {

    }

    @Override
    public void update(Customer customer) throws ServiceException {

    }

    @Override
    public void create(Customer customer) throws ServiceException {

    }
}
