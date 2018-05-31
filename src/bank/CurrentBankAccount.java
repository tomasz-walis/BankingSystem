package bank;

/**
 * Class implements BankAccount interface,
 * Uses the Transaction class to represent a ban transaction
 * Uses the Statement & StatementEntry classes
 *
 *    -Statement class is a list of StatementEntry objects.
 *    Together they are used to represent a "bank statement"
 *
 * @author  Tomasz Walis-Walisiak
 * @version 1.0
 * @since   2018-01-06
 */

public class CurrentBankAccount implements BankAccount {

    private int balance;
    private String accName;
    private int accNumber;
    private Statement statement;

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_GREEN = "\u001B[32m";
    private  final String ANSI_RED = "\u001B[31m";


    /**
     * CurrentBankAccount class constructor
     * @param accNumber variable represents the account number
     * @param accName variable represents the account holder name
     * @param balance variable represents account balance
     */
    protected CurrentBankAccount(int accNumber, String accName, int balance) {
        this.accNumber = accNumber;
        this.accName = accName;
        this.balance = balance;
        this.statement = new Statement(accName, accNumber);
    }

    @Override
    public int getBalance()
    {
        return balance;
    }

    @Override
    public int getAccNumber()
    {
        return accNumber;
    }

    @Override
    public String getAccountHolder()
    {
        return accName;
    }

    /**
     * withdraw monitor  prevents more than one thread from accessing the monitored (synchronized) section at the same time.
     * One thread will start, and monitor will prevent the other from accessing the region before the first one finishes.
     * @param transaction  represents the account transaction
     */
    public synchronized void withdrawTransactionMonitor(Transaction transaction) {
        System.out.println();
        System.out.println(transaction.getTransactionBy() + transaction.transactionType() + transaction.getAmount());

        /**
         * Wait while the account has no Sufficient Funds available
         */
       while (isOverdrawn(transaction))
        {
            try {
                System.out.println(ANSI_RED+ transaction.getTransactionBy() +  " Waiting For Sufficient Funds" + ANSI_RESET);
                wait();
            }catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
        withdraw(transaction);
        notifyAll();

    }


    /**
     * deposit monitor  prevents more than one thread from accessing the monitored (synchronized) section at the same time.
     * One thread will start, and monitor will prevent the other from accessing the region before the first one finishes.
     * @param transaction  represents the account transaction
     */
    public synchronized void depositTransactionMonitor(Transaction transaction) {

        System.out.println();
        System.out.println(transaction.getTransactionBy() + transaction.transactionType()  + transaction.getAmount());
        deposit(transaction);
        notifyAll();
    }




    @Override
    public void deposit(Transaction t)
    {
        balance += t.getAmount();
        addStatementEntry(t);
        System.out.println("Account Balance: £" + getBalance());
    }

    @Override
    public void withdraw(Transaction t)
    {
        balance -= t.getAmount();
        addStatementEntry(t);
        System.out.println("Account Balance: £" + getBalance());
    }

    @Override
    public void addStatementEntry(Transaction t) {
        statement.addTransaction(t.getTransactionBy(), t.getAmount(), balance);
    }

    /**
     * boolean isOverdraw method to check if the account has been overdrawn
     * @param transaction  represents the account transaction
     */
    @Override
    public boolean isOverdrawn(Transaction transaction)
    {
        return transaction.getAmount() > balance;
    }


    /**
     * toString method to print statement
     */
    @Override
    public String toString()
    {
        String str = ANSI_GREEN + "\n   Account Statement:"
                + "\n   Account NUMBER = " + getAccNumber()
                + "\n   Account Holder = " + getAccountHolder()
                + "\n   Account Balance =  £" + getBalance() + "\n\n"+ ANSI_RESET;
        str += statement;
        return str;
    }


}
