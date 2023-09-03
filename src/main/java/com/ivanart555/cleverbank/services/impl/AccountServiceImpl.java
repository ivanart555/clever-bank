package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.dao.AccountDAO;
import com.ivanart555.cleverbank.db.DataSource;
import com.ivanart555.cleverbank.entity.Account;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.AccountService;
import com.ivanart555.cleverbank.utils.config.ConfigLoader;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;
    private final Connection connection = DataSource.getConnection();

    public AccountServiceImpl(AccountDAO accountDAO) throws SQLException {
        this.accountDAO = accountDAO;
    }

    @Override
    public List<Account> getAll() throws ServiceException {
        try {
            return accountDAO.getAll(connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all accounts.");
        }
    }

    @Override
    public Account getById(Long id) throws ServiceException {
        try {
            return accountDAO.getById(id, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get account by id.");
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            accountDAO.delete(id, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to delete account by id.");
        }
    }

    @Override
    public void update(Account account) throws ServiceException {
        try {
            accountDAO.update(account, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to update account by id.");
        }
    }

    @Override
    public void create(Account account) throws ServiceException {
        try {
            accountDAO.create(account, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to create account.");
        }
    }

    @Override
    public void applyInterestToAccounts() throws ServiceException {
        List<Account> accounts;
        try {
            accounts = accountDAO.getAll(connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all accounts.");
        }
        for (Account acc : accounts) {
            if (accrualNeeded(acc)) {
                try {
                    applyInterest(acc);
                } catch (DAOException e) {
                    throw new ServiceException("Failed to apply interest to account balance.");
                }
            }
        }

    }

    private boolean accrualNeeded(Account account) {

        return account.getInterestAppliedDate() == null || isCurrentMonthDifferent(account.getInterestAppliedDate());

    }

    private boolean isCurrentMonthDifferent(Timestamp dateToCompare) {

        Calendar currentCalendar = Calendar.getInstance();
        Calendar compareCalendar = Calendar.getInstance();
        compareCalendar.setTimeInMillis(dateToCompare.getTime());

        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int compareMonth = compareCalendar.get(Calendar.MONTH);

        return currentMonth != compareMonth;
    }

    private void applyInterest(Account account) throws DAOException {
        double interest = Double.parseDouble(ConfigLoader.getValue("accountBalanceInterestRate"));

        BigDecimal balance = account.getBalance();

        BigDecimal interestRateDecimal = BigDecimal.valueOf(interest / 100.0);
        BigDecimal interestValue = balance.multiply(interestRateDecimal);

        BigDecimal newValue = balance.add(interestValue);

        account.setBalance(newValue);
        account.setInterestAppliedDate(Timestamp.valueOf(LocalDateTime.now()));
        accountDAO.update(account, connection);
    }

}
