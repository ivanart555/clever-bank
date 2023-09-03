package com.ivanart555.cleverbank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private final ReentrantLock lock = new ReentrantLock();

    private Long id;
    private String number;
    private Long bankId;
    private Long customerId;
    private BigDecimal balance;

    public ReentrantLock getLock() {
        return lock;
    }
}
