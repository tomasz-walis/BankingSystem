package bank;
import java.util.LinkedList;

/**
 * Provides the data structure for a bank account statement.
 * @author  Tomasz Walis-Walisiak
 * @version 1.0
 * @since   2018-01-06
 */

public class Statement {

    private LinkedList<StatementEntry> entries;

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_CYAN = "\u001B[36m";
    private final String ANSI_WHITE = "\u001B[37m";

    private final String accountHolder ;
    private final int accountNumber ;

    /**
     * CurrentBankAccount class constructor
     * @param accountHolder variable represents the account holder name,
     * @param accountNumber variable represents the account number
     */
    protected Statement(String accountHolder, int accountNumber)
    {
        entries = new LinkedList<>();
        this.accountHolder = accountHolder ;
        this.accountNumber = accountNumber ;
    }


    /**
     * Add transaction to the statement
     * @param customerName variable represents the customer name,
     * @param amount variable represents the transaction amount
     * @param balance variable represents the account balance
     */
    public void addTransaction(String customerName, int amount, int balance)
    {
        entries.add(new StatementEntry(customerName, amount, balance));
    }


    /**
     * Print statement Headers
     */
    @Override
    public String toString()
    {
        String format = "%-34s " + "%-30s" + "%19s" + "%26s\n";

        String str = String.format(format,ANSI_GREEN+"DateTime:"+ ANSI_RESET,
                ANSI_YELLOW+"Transaction By:"+ ANSI_RESET,
                ANSI_WHITE+"Transaction Amount:"+ ANSI_RESET,
                ANSI_CYAN+ "Balance: \n"+ ANSI_RESET);
        System.out.println();
        System.out.println("===================================================================================");
        for (StatementEntry statementEntry : entries) {
            str+= statementEntry+"\n";
        }
        return str;
    }
}
