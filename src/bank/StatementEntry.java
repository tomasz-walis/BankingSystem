package bank;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class provides the basic data structure for a single
 * bank account statement entry.
 * @author  Tomasz Walis-Walisiak
 * @version 1.0
 * @since   2018-01-06
 */

public class StatementEntry {


    private Date date = new Date();
    private SimpleDateFormat simpDate = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");

    private final String customerName;
    private final int amount;
    private final int currentBalance;

    /**
     * CurrentBankAccount class constructor
     * @param customerName variable represents the customer name,
     * @param amount variable represents the transaction amount
     * @param currentBalance variable represents the current account balance
     */
    protected StatementEntry(String customerName, int amount, int currentBalance )
    {
        this.customerName = customerName ;
        this.amount     = amount ;
        this.currentBalance = currentBalance ;
    }


    /**
     * Prints statement
     */
    @Override
    public String toString()
    {

        String format = "%-25s %-27s %-20s %-17s ";
        String str = String.format(format, simpDate.format(date),customerName,"£"+amount,currentBalance);

        return str;
    }

}
