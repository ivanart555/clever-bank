package com.ivanart555.cleverbank.dao.impl;

import com.ivanart555.cleverbank.dao.CustomerDAO;
import com.ivanart555.cleverbank.db.DataSource;
import com.ivanart555.cleverbank.entity.Customer;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.utils.config.SQLStatements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDAOImpl.class);

    @Override
    public List<Customer> getAll(Connection connection) throws DAOException {
        List<Customer> customers = new ArrayList<>();
        String sqlStatement = SQLStatements.getValue("customers", "get.all");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = mapResultSetToCustomer(rs);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new DAOException("Failed to get all customers!", e);
        }
    }

    @Override
    public Customer getById(Long id, Connection connection) throws DAOException {
        String sqlStatement = SQLStatements.getValue("customers", "get.byId");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToCustomer(rs);
            }
            return null;
        } catch (SQLException e) {
            String msg = String.format("Failed to get customer with ID '%d'", id);
            throw new DAOException(msg, e);
        }
    }

    @Override
    public void create(Customer customer, Connection connection) throws DAOException {
        String sqlStatement = SQLStatements.getValue("customers", "create");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                Long generatedId = rs.getLong(1);
                String msg = String.format("Customer with ID '%d' added to database.", generatedId);
                LOGGER.info(msg);
            } else {
                LOGGER.error("Failed to add new Customer to database!");
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute SQL customer create statement!", e);
        }
    }

    @Override
    public void update(Customer customer, Connection connection) throws DAOException {
        String sqlStatement = SQLStatements.getValue("customers", "update");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setLong(3, customer.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                String msg = String.format("Customer with ID '%d' updated.", customer.getId());
                LOGGER.info(msg);
            } else {
                String msg = String.format("Failed to update Customer with ID '%d'.", customer.getId());
                LOGGER.error(msg);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute SQL customer update statement!", e);
        }
    }

    @Override
    public void delete(Long id, Connection connection) throws DAOException {
        String sqlStatement = SQLStatements.getValue("customers", "delete");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                String msg = String.format("Customer with ID '%d' deleted successfully.", id);
                LOGGER.info(msg);
            } else {
                String msg = String.format("Customer with ID '%d' not found.", id);
                LOGGER.warn(msg);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute SQL customer delete statement!", e);
        }
    }

    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        return customer;
    }
}
