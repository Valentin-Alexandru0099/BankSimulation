package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    private final UUID id;
    private final String name;
    private List<BankAccount> accountList;

    public Client(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.accountList = new ArrayList<>();
    }

    public String wireMoney(UUID myAccount, Client destinationClient, UUID destinationAccount, double sum) {
        if (checkAccountsUUID(myAccount, destinationAccount)) return "Can't wire money between your Bank Accounts !";
            BankAccount account = getSpecificBankAccount(myAccount);
        if (!accountExists(account)) return "Can't find this account in your Bank Accounts !";
            BankAccount destinationBankAccount = destinationClient.getSpecificBankAccount(destinationAccount);
        if (!accountExists(destinationBankAccount)) return "Can't find this account in your Bank Accounts !";
        if (!checkCurrency(account.getCurrency(), destinationBankAccount.getCurrency())) return "Currency of the Bank Accounts does not match !";
        if (!enoughBalance(account.getBalance(), sum)) return "You dont own that much money !";
        if (!isCorporate(account.getBankAccountType())){
            double newSum = sum + calculateFee(sum);
            if (!enoughBalance(account.getBalance(), newSum)) return "You dont own that much money for the fee tax !";
                transferMoneyWithFee(account, destinationBankAccount, newSum, sum);
        } else {
            transferMoneyWithoutFee(account, destinationBankAccount, sum);
            account.setBalance(account.getBalance() - sum);
        }
        System.out.println(account.getBalance());
        System.out.println(destinationBankAccount.getBalance());
        return "Transaction Complete !";
    }

    private double calculateFee(double sum) {
        return sum * (1.0 / 100.0);
    }

    protected void addAccount(BankAccount bankAccount) {
        accountList.add(bankAccount);
    }

    public BankAccount getSpecificBankAccount(UUID id) {
        for (BankAccount bankAccount : accountList) {
            if (bankAccount.getId().equals(id)) {
                return bankAccount;
            }
        }
        return null;
    }

    public void insertMoneyInAccount(UUID id, double sum) {
        BankAccount account = getSpecificBankAccount(id);
        if (account != null) {
            account.setBalance(account.getBalance() + sum);
        }
    }

    private boolean checkAccountsUUID(UUID myAccount, UUID destinationAccount) {
        return myAccount.equals(destinationAccount);
    }

    private boolean accountExists(BankAccount bankAccount) {
        return bankAccount != null;
    }

    private boolean checkCurrency(Currency myCurrency, Currency destinationCurrency) {
        return myCurrency.equals(destinationCurrency);
    }

    private boolean enoughBalance(double myBalance, double sum) {
        return myBalance >= sum;
    }

    private boolean isCorporate(BankAccountType type) {
        return type.equals(BankAccountType.CORPORATE);
    }

    private void transferMoneyWithFee(BankAccount myAccount, BankAccount destinationAccount, double feedSum, double initialSum) {
        destinationAccount.setBalance(destinationAccount.getBalance() + initialSum);
        myAccount.setBalance(myAccount.getBalance() - feedSum);
    }

    private void transferMoneyWithoutFee(BankAccount myAccount, BankAccount destinationAccount, double sum) {
        destinationAccount.setBalance(destinationAccount.getBalance() + sum);
        myAccount.setBalance(myAccount.getBalance() - sum);
    }
}
