package com.ivanart555.cleverbank.dao.impl;

import com.ivanart555.cleverbank.dao.CustomerDAO;
import com.ivanart555.cleverbank.entity.Customer;
import com.ivanart555.cleverbank.exception.DAOException;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    //TODO Add connection to db and implementation
    @Override
    public List<Customer> getAll() throws DAOException {
        return null;
    }

    @Override
    public Customer getById(Long id) throws DAOException {
        return null;
    }

    @Override
    public void create(Customer customer) throws DAOException {

    }

    @Override
    public void update(Customer customer) throws DAOException {

    }

    @Override
    public void delete(Long id) throws DAOException {

    }
}
