import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    public void testCreateAccount() {
        Bank bank = new Bank();

        try {
            BankAccount account = bank.createAccount("Reynold Russell", 100.0);
            assertNotNull(account);
            assertEquals("Reynold Russell", account.getAccountName());
            assertEquals(100.0, account.getBalance(), 0.01);
        } catch (NegativeAmountException e) {
            fail("Unexpected NegativeAmountException");
        }
    }

    @Test
    public void testCreateAccountWithNegativeBalance() {
        Bank bank = new Bank();

        assertThrows(NegativeAmountException.class, () -> {
            bank.createAccount("Negative Balance", -50.0);
        });
    }

    @Test
    public void testFindAccount() {
        Bank bank = new Bank();

        try {
            BankAccount account = bank.createAccount("Maximillian Hodges", 100.0);

            try {
                BankAccount foundAccount = bank.findAccount(account.getAccountNumber());
                assertNotNull(foundAccount);
                assertEquals(account, foundAccount);
            } catch (AccountNotFoundException e) {
                fail("Unexpected AccountNotFoundException");
            }

            assertThrows(AccountNotFoundException.class, () -> {
                bank.findAccount(-1);
            });
        } catch (NegativeAmountException e) {
            fail("Unexpected NegativeAmountException");
        }
    }

    @Test
    public void testTransferMoney() {
        Bank bank = new Bank();

        try {
            BankAccount fromAccount = bank.createAccount("Maximillian Hodges", 100.0);
            BankAccount toAccount = bank.createAccount("Maximillian Hodges", 50.0);

            try {
                bank.transferMoney(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), 30.0);
                assertEquals(70.0, fromAccount.getBalance(), 0.01);
                assertEquals(80.0, toAccount.getBalance(), 0.01);
            } catch (NegativeAmountException | InsufficientFundsException | AccountNotFoundException e) {
                fail("Unexpected exception");
            }
        } catch (NegativeAmountException e) {
            fail("Unexpected NegativeAmountException");
        }
    }

    @Test
    public void testTransferMoneyWithInsufficientFunds() {
        Bank bank = new Bank();

        try {
            BankAccount fromAccount = bank.createAccount("Alice Johnson", 100.0);
            BankAccount toAccount = bank.createAccount("Alice Johnson", 50.0);

            assertThrows(InsufficientFundsException.class, () -> {
                bank.transferMoney(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), 1000.0);
            });
        } catch (NegativeAmountException e) {
            fail("Unexpected NegativeAmountException");
        }
    }

    @Test
    public void testTransferMoneyWithNegativeAmount() {
        Bank bank = new Bank();

        try {
            BankAccount fromAccount = bank.createAccount("Bob Smith", 100.0);
            BankAccount toAccount = bank.createAccount("Bob Smith", 50.0);

            assertThrows(NegativeAmountException.class, () -> {
                bank.transferMoney(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), -30.0);
            });
        } catch (NegativeAmountException e) {
            fail("Unexpected NegativeAmountException");
        }
    }

    @Test
    public void testTransferMoneyWithInvalidAccountNumber() {
        Bank bank = new Bank();

        try {
            BankAccount fromAccount = bank.createAccount("Bob Smith", 100.0);
            BankAccount toAccount = bank.createAccount("Bob Smith", 50.0);

            assertThrows(AccountNotFoundException.class, () -> {
                bank.transferMoney(fromAccount.getAccountNumber(), -1, 30.0);
            });
        } catch (NegativeAmountException e) {
            fail("Unexpected NegativeAmountException");
        }
    }
}
