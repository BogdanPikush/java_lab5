package org.example;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public BankAccount createAccount(String accountName, double initialDeposit) throws NegativeAmountException {
        int accountNumber = generateAccountNumber();

        try {
            BankAccount newAccount = new BankAccount(accountNumber, accountName, initialDeposit);
            accounts.add(newAccount);
            return newAccount;
        } catch (NegativeAmountException e) {
            System.err.println("Error creating account: " + e.getMessage());
            throw e;
        }
    }

    public BankAccount findAccount(int accountNumber) throws AccountNotFoundException {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        throw new AccountNotFoundException("Account with number " + accountNumber + " not found");
    }

    public void transferMoney(int fromAccountNumber, int toAccountNumber, double amount)
            throws NegativeAmountException, InsufficientFundsException, AccountNotFoundException {

        try {
            BankAccount fromAccount = findAccount(fromAccountNumber);
            BankAccount toAccount = findAccount(toAccountNumber);

            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        } catch (NegativeAmountException | InsufficientFundsException | AccountNotFoundException e) {
            System.err.println("Error transferring money: " + e.getMessage());
            throw e;
        }
    }

    private int generateAccountNumber() {
        return accounts.size() + 1;
    }
}
