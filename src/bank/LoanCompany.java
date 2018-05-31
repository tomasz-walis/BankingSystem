package bank;
import java.util.Random;

/**
 * Class Uses the Transaction class for the loans
 * Contains information for the loan company, the thread group it is in
 * Randomizes the transaction amount
 *
 * @author  Tomasz Walis-Walisiak
 * @version 1.0
 * @since   2018-01-06
 */

public class LoanCompany extends Thread {

    private Random random = new Random();
    private CurrentBankAccount bankAccount;

    private int numberOfTransactions = 3;
    private int maximumTransaction = 45000;
    private int minimumTransaction = 20000;
    private int range = maximumTransaction - minimumTransaction + 1;

    /**
     * LoanCompany class constructor
     * @param bankAccount represents the account
     * @param threadGroup represents the threadGroup
     */
    protected LoanCompany(CurrentBankAccount bankAccount, ThreadGroup threadGroup ){
        super(threadGroup , "LOAN COMPANY");
        this.bankAccount = bankAccount;
    }


    @Override
    public void run()
    {
        do{
            //randomize the transaction amount
            int amount =  random.nextInt(range) + minimumTransaction;

            try {
                Transaction transaction = new Transaction(getName(), amount, " Depositing Loan of: £");
                bankAccount.depositTransactionMonitor(transaction);
            }catch (Exception e){
                e.printStackTrace();
            }

            //sleep for random amount of time
            try {
                sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            numberOfTransactions--;

        } while (numberOfTransactions != 0);

    }
}


