package com.ivanart555.cleverbank.services;

import com.ivanart555.cleverbank.entity.Account;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.generic.GenericService;

public interface AccountService extends GenericService<Account, Long> {

    void applyInterestToAccounts() throws ServiceException;

}
