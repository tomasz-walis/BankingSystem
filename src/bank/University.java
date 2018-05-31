package bank;

import java.util.Random;

/**
 * Use the Transaction class for withdrawing course fees.
 * Contains information for the University, i.e. the thread group it is in;
 * Randomizes the transaction amount
 *
 * @author  Tomasz Walis-Walisiak
 * @version 1.0
 * @since   2018-01-06
 */

public class University extends Thread {

    private Random random = new Random();
    private CurrentBankAccount bankAccount;

    private int numberOfTransactions = 3;
    private int maximumTransaction = 15000;
    private int minimumTransaction = 10000;
    private int range = maximumTransaction - minimumTransaction + 1;



    /**
     * University class constructor
     * @param bankAccount represents the account
     * @param threadGroup represents the threadGroup
     */
    protected University(CurrentBankAccount bankAccount, ThreadGroup threadGroup ){
        super(threadGroup , "UNIVERSITY");
        this.bankAccount = bankAccount;
    }

    @Override
    public void run()
    {
        do {
            //randomize the transaction amount
            int amount =  random.nextInt(range) + minimumTransaction;

            try {
                Transaction transaction = new Transaction(getName(), amount, " Withdrawing Fee of: £");
                bankAccount.withdrawTransactionMonitor(transaction);
            }catch (Exception e){
                e.printStackTrace();
            }

            //sleep for random amount of time
            try {
                sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           numberOfTransactions--;

        } while (numberOfTransactions != 0);

    }


}


