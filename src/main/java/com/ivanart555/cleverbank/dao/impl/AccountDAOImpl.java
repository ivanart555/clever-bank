package com.ivanart555.cleverbank.dao.impl;

import com.ivanart555.cleverbank.dao.AccountDAO;
import com.ivanart555.cleverbank.db.DataSource;
import com.ivanart555.cleverbank.entity.Account;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.utils.config.SQLStatements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class AccountDAOImpl implements AccountDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDAOImpl.class);
    private final Connection connection;

    public AccountDAOImpl() throws SQLException {
        this.connection = DataSource.getConnection();
    }

    @Override
    public List<Account> getAll() throws DAOException {
        List<Account> accounts = new ArrayList<>();
        String sqlStatement = SQLStatements.getValue("accounts", "get.all");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = mapResultSetToAccount(rs);
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            throw new DAOException("Failed to get all accounts!", e);
        }
    }

    @Override
    public Account getById(Long id) throws DAOException {
        Account account = new Account();
        String sqlStatement = SQLStatements.getValue("accounts", "get.byId");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account = mapResultSetToAccount(rs);
            }
            return account;
        } catch (SQLException e) {
            String msg = format("Failed to get account with ID '%s'", id);
            throw new DAOException(msg, e);
        }
    }

    @Override
    public void create(Account account) throws DAOException {
        String sqlStatement = SQLStatements.getValue("accounts", "create");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, account.getNumber());
            ps.setLong(2, account.getBankId());
            ps.setLong(3, account.getCustomerId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                Long generatedId = rs.getLong(1);
                String msg = format("Account with ID '%s' added to database.", generatedId);
                LOGGER.info(msg);
            } else {
                LOGGER.error("Failed to add new Account to database!");
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute sql account create statement!", e);
        }
    }

    @Override
    public void update(Account account) throws DAOException {
        String sqlStatement = SQLStatements.getValue("accounts", "update");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setString(1, account.getNumber());
            ps.setLong(2, account.getBankId());
            ps.setLong(3, account.getCustomerId());
            ps.setBigDecimal(4, account.getBalance());
            ps.setLong(5, account.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                String msg = format("Account with ID '%s' updated.", account.getId());
                LOGGER.info(msg);
            } else {
                String msg = format("Failed to update Account with ID '%s'.", account.getId());
                LOGGER.error(msg);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute sql account update statement!", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        String sqlStatement = SQLStatements.getValue("accounts", "delete");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                String msg = format("Account with ID '%s' deleted successfully.", id);
                LOGGER.info(msg);
            } else {
                String msg = format("Account with ID '%s' not found.", id);
                LOGGER.warn(msg);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute sql account delete statement!", e);
        }
    }

    private Account mapResultSetToAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();

        account.setId(resultSet.getLong("id"));
        account.setNumber(resultSet.getString("number"));
        account.setBankId(resultSet.getLong("bank_id"));
        account.setCustomerId(resultSet.getLong("customer_id"));

        account.setBalance(resultSet.getBigDecimal("balance"));

        return account;
    }

}
