package com.company;

import java.util.UUID;

public class BankAccount {
    private final UUID id;
    private final BankAccountType bankAccountType;
    private double balance;
    private final Currency currency;

    public BankAccount(BankAccountType bankAccountType, Currency currency) {
        this.bankAccountType = bankAccountType;
        this.currency = currency;
        this.balance = 0;
        this.id = UUID.randomUUID();
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BankAccountType getBankAccountType() {
        return bankAccountType;
    }

    public double getBalance() {
        return balance;
    }
}


