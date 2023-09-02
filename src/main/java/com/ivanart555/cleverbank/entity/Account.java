package com.ivanart555.cleverbank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private String number;
    private Long bankId;
    private Long customerId;
    private BigDecimal balance;
}
