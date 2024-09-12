package com.yc.springboot.test;

public interface Transactions {
    void add(Account account, TransactionType transactionType, int amount);
}
