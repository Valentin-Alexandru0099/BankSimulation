package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private List<BankAccount> accounts;
    private List<Client> clients;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public UUID openBankAccount(Client client, BankAccountType bankAccountType, Currency currency){
        clients.add(client);
        BankAccount bankAccount = new BankAccount(bankAccountType,currency);
        accounts.add(bankAccount);
        client.addAccount(bankAccount);
        return bankAccount.getId();
    }
}
