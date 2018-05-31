package bank;
import java.util.Random;

/**
 * Class Uses the Transaction class for the “top-up gifts”.
 * Contains information for the parents, i.e. the thread group it is in
 * Randomizes the transaction amount
 *
 * @author  Tomasz Walis-Walisiak
 * @version 1.0
 * @since   2018-01-06
 */

public class Parent extends Thread {

    private Random random = new Random();
    private CurrentBankAccount bankAccount;

    private int numberOfTransactions = 2;
    private int maximumTransaction = 35000;
    private int minimumTransaction = 10000;
    private int range = maximumTransaction - minimumTransaction + 1;

    /**
     * Parent class constructor
     * @param bankAccount represents the account
     * @param threadGroup represents the threadGroup
     */
    protected Parent(CurrentBankAccount bankAccount, ThreadGroup threadGroup) {
        super(threadGroup, "PARENT");
        this.bankAccount = bankAccount;
    }


    @Override
    public void run()
    {
        do {
            //randomize the transaction amount
            int amount =  random.nextInt(range) + minimumTransaction;
            try {

                Transaction transaction = new Transaction(getName(), amount, " Depositing Gift of: £");
                bankAccount.depositTransactionMonitor(transaction);
            }catch (Exception e){
                e.printStackTrace();
            }

            //sleep for random amount of time
            try {
                sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            numberOfTransactions--;

        }while (numberOfTransactions != 0);

    }
}

