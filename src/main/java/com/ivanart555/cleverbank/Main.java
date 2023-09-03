package com.ivanart555.cleverbank;

import com.ivanart555.cleverbank.dao.impl.AccountDAOImpl;
import com.ivanart555.cleverbank.exception.ServiceException;
import com.ivanart555.cleverbank.services.AccountService;
import com.ivanart555.cleverbank.services.impl.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        AccountService accountService = new AccountServiceImpl(new AccountDAOImpl());

        ScheduledExecutorService schedulerScheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            try {
                accountService.applyInterestToAccounts();
            } catch (ServiceException e) {
                LOGGER.warn("Failed to apply interest to accounts balances.");
            }
        };
        schedulerScheduledExecutorService.scheduleAtFixedRate(task, 0, 30, TimeUnit.SECONDS);
        schedulerScheduledExecutorService.shutdown();

    }
}
