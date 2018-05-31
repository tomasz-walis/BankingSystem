package bank;
import java.util.Random;

/**
 * Use the Transaction class to create several different "banking transaction", which
 * are then performed on a student's bank account.
 * Contains information for a student, i.e. the thread group its in;
 * Randomizes the transaction amount
 *
 * @author  Tomasz Walis-Walisiak
 * @version 1.0
 * @since   2018-01-06
 */

public class Student extends Thread {

    private Random random = new Random();
    private CurrentBankAccount bankAccount;

    private int numberOfTransactions = 6;
    private int maximumTransaction = 15000;
    private int minimumTransaction = 10000;
    private int range = maximumTransaction - minimumTransaction + 1;


    /**
     * Student class constructor
     * @param bankAccount represents the account
     * @param threadGroup represents the threadGroup
     */
    protected Student(CurrentBankAccount bankAccount, ThreadGroup threadGroup) {
        super(threadGroup, "STUDENT");
        this.bankAccount = bankAccount;
    }


    /**
     * Method to randomize the enum values as transaction type
     */
    private   <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }


    public void run()
    {
        do {
            //randomize the transaction amount
            int amount =  random.nextInt(range) + minimumTransaction;

            //pick random transaction
            TransactionType transType = randomEnum(TransactionType.class);

            if(transType == TransactionType.LOTTERY)
            {
                int winLottery = 5000000;
                Transaction transaction = new Transaction(getName(), winLottery, " Depositing lottery win of: £");
                bankAccount.depositTransactionMonitor(transaction);


            }
            else if (transType == TransactionType.WITHDRAW)
            {
                Transaction transaction = new Transaction(getName(), amount, " Withdrawing amount of: £");
                bankAccount.withdrawTransactionMonitor(transaction);


            }
            else if(transType == TransactionType.DEPOSIT)
            {
                Transaction transaction = new Transaction(getName(), amount, " Depositing amount of: £");
                bankAccount.depositTransactionMonitor(transaction);


            }


            //sleep for random amount of time
            try {
                sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }

            numberOfTransactions--;


        }while (numberOfTransactions !=0);
    }



}