package bank;
/**
 * This provides the interface for a CurrentBankAccount class.
 *
 * @author  P. Howells
 * @version 1.0
 * @since   2014-03-31
 *
 * Modified by Tomasz Walis-Walisiak on 06/01/2018
 */
public interface BankAccount {

    int getBalance();
    int getAccNumber();
    String getAccountHolder();
    void deposit(Transaction t);
    void withdraw(Transaction t);
    boolean isOverdrawn(Transaction t);
    void addStatementEntry(Transaction t);

}