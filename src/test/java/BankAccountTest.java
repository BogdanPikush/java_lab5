import org.example.BankAccount;
import org.example.InsufficientFundsException;
import org.example.NegativeAmountException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    @Test
    public void testDepositPositiveAmount() throws NegativeAmountException {
        BankAccount account = new BankAccount(1, "Reynold Russell", 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.01);
    }

    @Test
    public void testDepositNegativeAmount() {
        try {
            BankAccount account = new BankAccount(1, "Reynold Russell", 100.0);
            account.deposit(-50.0);
            fail("Expected NegativeAmountException, but no exception was thrown");
        } catch (NegativeAmountException exception) {
            // Ви можете також вивести інформацію про виняток для подальшого аналізу, якщо потрібно
            System.out.println("Exception Message: " + exception.getMessage());
            System.out.println("Exception Class: " + exception.getClass() + "\n");
        }
    }

    @Test
    public void testWithdrawSufficientFunds() throws NegativeAmountException, InsufficientFundsException {
        BankAccount account = new BankAccount(2, "Maximillian Hodges", 200.0);
        account.withdraw(50.0);
        assertEquals(150.0, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        assertThrows(InsufficientFundsException.class, () -> {
            try {
            BankAccount account = new BankAccount(2, "Maximillian Hodges", 50.0);
            account.withdraw(100.0);
            } catch (NegativeAmountException | InsufficientFundsException exception) {
                System.out.println("Exception Message: " + exception.getMessage());
                System.out.println("Exception Class: " + exception.getClass() + "\n");
                throw exception;
            }
        });
    }

    @Test
    public void testGetAccountSummary() throws NegativeAmountException {
        BankAccount account = new BankAccount(3, "Alice Johnson", 300.0);
        String expectedSummary = "Account Number: 3\nAccount Name: Alice Johnson\nBalance: $300.0";
        assertEquals(expectedSummary, account.getAccountSummary());
    }

    @Test
    public void testGetBalance() throws NegativeAmountException {
        BankAccount account = new BankAccount(4, "Bob Smith", 400.0);
        assertEquals(400.0, account.getBalance(), 0.01);
    }
}
