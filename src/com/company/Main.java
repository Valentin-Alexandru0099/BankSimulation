package com.company;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Client client1 = new Client("Alex");
        Client client2 = new Client("Cosmin");
        UUID uuidClient1 = bank.openBankAccount(client1, BankAccountType.CONSUMER, Currency.RON);
        UUID uuidClient2 = bank.openBankAccount(client2, BankAccountType.CONSUMER, Currency.RON);
        client1.insertMoneyInAccount(uuidClient1, 150);
        client2.insertMoneyInAccount(uuidClient2, 100);
        System.out.println(client1.wireMoney(uuidClient1,client2,uuidClient2, 100));
    }
}
