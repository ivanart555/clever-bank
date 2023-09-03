package com.ivanart555.cleverbank.dao.impl;

import com.ivanart555.cleverbank.dao.TransactionDAO;
import com.ivanart555.cleverbank.db.DataSource;
import com.ivanart555.cleverbank.entity.Transaction;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.utils.config.SQLStatements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionDAOImpl implements TransactionDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDAOImpl.class);


    @Override
    public List<Transaction> getAll(Connection connection) throws DAOException {
        List<Transaction> transactions = new ArrayList<>();
        String sqlStatement = SQLStatements.getValue("transactions", "get.all");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction transaction = mapResultSetToTransaction(rs);
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            throw new DAOException("Failed to get all transactions!", e);
        }
    }

    @Override
    public Transaction getById(Long id, Connection connection) throws DAOException {
        String sqlStatement = SQLStatements.getValue("transactions", "get.byId");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToTransaction(rs);
            }
            return null;
        } catch (SQLException e) {
            String msg = String.format("Failed to get transaction with ID '%d'", id);
            throw new DAOException(msg, e);
        }
    }

    @Override
    public void create(Transaction transaction, Connection connection) throws DAOException {
        String sqlStatement = SQLStatements.getValue("transactions", "create");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, transaction.getDateTime());
            ps.setBigDecimal(2, transaction.getAmount());
            ps.setString(3, transaction.getSenderAccountNumber());
            ps.setString(4, transaction.getRecipientAccountNumber());
            ps.setString(5, transaction.getTransactionType());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                Long generatedId = rs.getLong(1);
                String msg = String.format("Transaction with ID '%d' added to database.", generatedId);
                LOGGER.info(msg);
            } else {
                LOGGER.error("Failed to add new Transaction to database!");
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute SQL transaction create statement!", e);
        }
    }

    @Override
    public void update(Transaction transaction, Connection connection) throws DAOException {
        String sqlStatement = SQLStatements.getValue("transactions", "update");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setTimestamp(1, transaction.getDateTime());
            ps.setBigDecimal(2, transaction.getAmount());
            ps.setString(3, transaction.getSenderAccountNumber());
            ps.setString(4, transaction.getRecipientAccountNumber());
            ps.setString(5, transaction.getTransactionType());
            ps.setLong(6, transaction.getId());


            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                String msg = String.format("Transaction with ID '%d' updated.", transaction.getId());
                LOGGER.info(msg);
            } else {
                String msg = String.format("Failed to update Transaction with ID '%d'.", transaction.getId());
                LOGGER.error(msg);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute SQL transaction update statement!", e);
        }
    }

    @Override
    public void delete(Long id, Connection connection) throws DAOException {
        String sqlStatement = SQLStatements.getValue("transactions", "delete");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                String msg = String.format("Transaction with ID '%d' deleted successfully.", id);
                LOGGER.info(msg);
            } else {
                String msg = String.format("Transaction with ID '%d' not found.", id);
                LOGGER.warn(msg);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute SQL transaction delete statement!", e);
        }
    }

    private Transaction mapResultSetToTransaction(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getLong("id"));
        transaction.setDateTime(resultSet.getTimestamp("date_time"));
        transaction.setAmount(resultSet.getBigDecimal("amount"));
        transaction.setSenderAccountNumber(resultSet.getString("sender_account_number"));
        transaction.setRecipientAccountNumber(resultSet.getString("recipient_account_number"));
        transaction.setTransactionType(resultSet.getString("type"));
        return transaction;
    }
}
