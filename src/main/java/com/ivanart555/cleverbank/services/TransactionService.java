package com.ivanart555.cleverbank.services;

import com.ivanart555.cleverbank.entity.Transaction;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.generic.GenericService;

import java.math.BigDecimal;

public interface TransactionService extends GenericService<Transaction, Long> {

    void depositMoney(Long accountId, BigDecimal amount) throws ServiceException;

    void withdrawMoney(Long accountId,BigDecimal amount) throws ServiceException;

    void transferMoney(Long senderAccountId, Long recipientAccountId, BigDecimal amount) throws ServiceException;
}
