package com.ivanart555.cleverbank.services.impl;

import com.ivanart555.cleverbank.dao.AccountDAO;
import com.ivanart555.cleverbank.dao.TransactionDAO;
import com.ivanart555.cleverbank.dao.impl.BankDAOImpl;
import com.ivanart555.cleverbank.db.DataSource;
import com.ivanart555.cleverbank.entity.Account;
import com.ivanart555.cleverbank.entity.Transaction;
import com.ivanart555.cleverbank.exception.DAOException;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionServiceImpl implements TransactionService {
    private static final String DEPOSIT = "DEPOSIT";
    private static final String WITHDRAW = "WITHDRAW";
    private static final String TRANSFER = "TRANSFER";
    private static final Logger LOGGER = LoggerFactory.getLogger(BankDAOImpl.class);
    private final TransactionDAO transactionDAO;
    private final AccountDAO accountDAO;
    private final Connection connection = DataSource.getConnection();

    public TransactionServiceImpl(TransactionDAO transactionDAO, AccountDAO accountDAO) throws SQLException {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public void depositMoney(Long accountId, BigDecimal amount) throws ServiceException {
        ReentrantLock lock = null;
        try {
            connection.setAutoCommit(false);

            Account account = accountDAO.getById(accountId, connection);

            lock = account.getLock();
            lock.lock();

            Transaction transaction = new Transaction();
            transaction.setDateTime(new Timestamp(System.currentTimeMillis()));
            transaction.setAmount(amount);
            transaction.setSenderAccountNumber(null);
            transaction.setRecipientAccountNumber(account.getNumber());
            transaction.setTransactionType(DEPOSIT);

            BigDecimal currentBalance = account.getBalance();
            BigDecimal newBalance = currentBalance.add(amount);
            account.setBalance(newBalance);
            accountDAO.update(account, connection);
            transactionDAO.create(transaction, connection);

            connection.commit();
        } catch (DAOException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                throw new ServiceException("Failed to perform deposit transaction. Roll back.", rollbackException);
            }
            throw new ServiceException("Failed to deposit money.", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeException) {
                    LOGGER.warn("Failed to close connection!");
                }
            }
            assert lock != null;
            lock.unlock();
        }
    }

    @Override
    public void withdrawMoney(Long accountId, BigDecimal amount) throws ServiceException {
        ReentrantLock lock = null;
        try {
            connection.setAutoCommit(false);
            Account account = accountDAO.getById(accountId, connection);
            lock = account.getLock();
            lock.lock();

            if (account.getBalance().compareTo(amount) >= 0) {
                account.setBalance(account.getBalance().subtract(amount));

                Transaction transaction = new Transaction();
                transaction.setDateTime(new Timestamp(System.currentTimeMillis()));
                transaction.setAmount(amount);
                transaction.setSenderAccountNumber(account.getNumber());
                transaction.setRecipientAccountNumber(null);
                transaction.setTransactionType(WITHDRAW);
                accountDAO.update(account, connection);
                transactionDAO.create(transaction, connection);

                connection.commit();

            } else throw new ServiceException("Insufficient funds!");
        } catch (DAOException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                throw new ServiceException("Failed to perform withdraw transaction. Roll back.", rollbackException);
            }
            throw new ServiceException("Failed to withdraw money.", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeException) {
                    LOGGER.warn("Failed to close connection!");
                }
            }
            assert lock != null;
            lock.unlock();
        }
    }


    @Override
    public void transferMoney(Long senderAccountId, Long recipientAccountId, BigDecimal amount) throws ServiceException {
        ReentrantLock senderAccLock;
        ReentrantLock recipientAccLock;
        try {
            connection.setAutoCommit(false);

            Account senderAccount = accountDAO.getById(senderAccountId, connection);
            senderAccLock = senderAccount.getLock();
            senderAccLock.lock();

            Account recipientAccount = accountDAO.getById(recipientAccountId, connection);
            recipientAccLock = recipientAccount.getLock();
            recipientAccLock.lock();

            if (senderAccount.getBalance().compareTo(amount) >= 0) {
                senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
                recipientAccount.setBalance(recipientAccount.getBalance().add(amount));

                Transaction transaction = new Transaction();
                transaction.setDateTime(new Timestamp(System.currentTimeMillis()));
                transaction.setAmount(amount);
                transaction.setSenderAccountNumber(senderAccount.getNumber());
                transaction.setRecipientAccountNumber(recipientAccount.getNumber());
                transaction.setTransactionType(TRANSFER);

                accountDAO.update(senderAccount, connection);
                accountDAO.update(recipientAccount, connection);
                transactionDAO.create(transaction, connection);

                connection.commit();
            } else {
                throw new ServiceException("Insufficient funds!");
            }

        } catch (SQLException | DAOException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                throw new ServiceException("Failed to perform transfer transaction. Roll back.", rollbackException);
            }
            throw new ServiceException("Failed to transfer money.", e);
        }
    }

    @Override
    public List<Transaction> getAll() throws ServiceException {
        try {
            return transactionDAO.getAll(connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get all transactions.");
        }
    }

    @Override
    public Transaction getById(Long id) throws ServiceException {
        try {
            return transactionDAO.getById(id, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to get transaction by id.");
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            transactionDAO.delete(id, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to delete transaction by id.");
        }
    }

    @Override
    public void update(Transaction transaction) throws ServiceException {
        try {
            transactionDAO.update(transaction, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to update transaction by id.");
        }
    }

    @Override
    public void create(Transaction transaction) throws ServiceException {
        try {
            transactionDAO.create(transaction, connection);
        } catch (DAOException e) {
            throw new ServiceException("Failed to create transaction.");
        }
    }
}
