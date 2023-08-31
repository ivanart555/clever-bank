package com.ivanart555.cleverbank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private Timestamp dateTime;
    private BigDecimal amount;
    private Long senderAccountId;
    private Long recipientAccountId;
}
