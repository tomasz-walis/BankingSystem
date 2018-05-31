package bank;

/**
 * Provides the basic data structure for a bank transaction.
 * That is customer id & the amount to either deposit or
 *
 * @author  P. Howells
 * @version 1.0
 * @since   2014-03-31
 *
 * Modified by Tomasz Walis-Walisiak on 06/01/2018
 */

public class Transaction {

    private final String transactionBy;
    private final int amount;
    private String type;

    public Transaction(String transactionBy, int amount, String type)
    {
        this.transactionBy = transactionBy;
        this.amount = amount;
        this.type = type;
    }


    public String getTransactionBy()
    {
        return transactionBy;
    }

    public int getAmount()
    {
        return amount;
    }

    public String transactionType()
    {
        return type;
    }
}