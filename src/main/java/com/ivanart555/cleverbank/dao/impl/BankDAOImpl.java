package com.ivanart555.cleverbank.dao.impl;

import com.ivanart555.cleverbank.dao.BankDAO;
import com.ivanart555.cleverbank.db.DataSource;
import com.ivanart555.cleverbank.entity.Bank;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.utils.config.SQLStatements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDAOImpl implements BankDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankDAOImpl.class);
    private final Connection connection;

    public BankDAOImpl() throws SQLException {
        this.connection = DataSource.getConnection();
    }

    @Override
    public List<Bank> getAll() throws DAOException {
        List<Bank> banks = new ArrayList<>();
        String sqlStatement = SQLStatements.getValue("banks", "get.all");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bank bank = mapResultSetToBank(rs);
                banks.add(bank);
            }
            return banks;
        } catch (SQLException e) {
            throw new DAOException("Failed to get all banks!", e);
        }
    }

    @Override
    public Bank getById(Long id) throws DAOException {
        Bank bank = new Bank();
        String sqlStatement = SQLStatements.getValue("banks", "get.byId");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bank = mapResultSetToBank(rs);
            }
            return bank;
        } catch (SQLException e) {
            String msg = String.format("Failed to get bank with ID '%d'", id);
            throw new DAOException(msg, e);
        }
    }

    @Override
    public void create(Bank bank) throws DAOException {
        String sqlStatement = SQLStatements.getValue("banks", "create");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, bank.getName());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                Long generatedId = rs.getLong(1);
                String msg = String.format("Bank with ID '%d' added to database.", generatedId);
                LOGGER.info(msg);
            } else {
                LOGGER.error("Failed to add new Bank to database!");
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute SQL bank create statement!", e);
        }
    }

    @Override
    public void update(Bank bank) throws DAOException {
        String sqlStatement = SQLStatements.getValue("banks", "update");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setString(1, bank.getName());
            ps.setLong(2, bank.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                String msg = String.format("Bank with ID '%d' updated.", bank.getId());
                LOGGER.info(msg);
            } else {
                String msg = String.format("Failed to update Bank with ID '%d'.", bank.getId());
                LOGGER.error(msg);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute SQL bank update statement!", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        String sqlStatement = SQLStatements.getValue("banks", "delete");
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                String msg = String.format("Bank with ID '%d' deleted successfully.", id);
                LOGGER.info(msg);
            } else {
                String msg = String.format("Bank with ID '%d' not found.", id);
                LOGGER.warn(msg);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to execute SQL bank delete statement!", e);
        }
    }

    private Bank mapResultSetToBank(ResultSet resultSet) throws SQLException {
        Bank bank = new Bank();
        bank.setId(resultSet.getLong("id"));
        bank.setName(resultSet.getString("name"));
        return bank;
    }
}
