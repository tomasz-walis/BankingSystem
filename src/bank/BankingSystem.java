package bank;

/**
 * Class Represents the complete Student Banking System,
 * combines objects & threads in parallel.
 * Class creates the following objects, threads and thread groups
 *    - One Current Account class for a student.
 *    - Two thread groups: one for users and one for Institutes
 *    - prints out the final statement for the student's bank account.
 *
 *
 * @author  Tomasz Walis-Walisiak
 * @version 1.0
 * @since   2018-01-06
 */

public class BankingSystem {

    public static void main(String[] args) {

        System.out.println();
        System.out.println( "STUDENT BANK ACCOUNT:");
        System.out.println();
        System.out.println( "Create: Current Account Object");
        System.out.println();

        /**
         * Create current bank account object
         */
        CurrentBankAccount bankAccount = new CurrentBankAccount( 101010, "Student", 1000) ;


        System.out.println( "Create: Thread Groups Users & Institutes" ) ;
        System.out.println();


        /**
         * Create: Thread Groups Users & Institutes
         */
        ThreadGroup users = new ThreadGroup("Users");
        ThreadGroup institutes = new ThreadGroup("Institutes");

        System.out.println( "Create: Student, Parent, University & LoanCompany Threads" ) ;
        System.out.println();


        /**
         * Create: Student, Parent, University & LoanCompany Threads
         */
        Student student = new Student(bankAccount, users);
        Parent parent = new Parent(bankAccount, users);
        University university = new University(bankAccount, institutes);
        LoanCompany loanCompany = new LoanCompany(bankAccount, institutes);


        System.out.println( "Start: Student, Parent, University & LoanCompany Threads" ) ;
        System.out.println();



        System.out.println("Account Balance: £" + bankAccount.getBalance());
        /**
         * Start: Student, Parent, University & LoanCompany Threads
         */
        student.start();
        parent.start();
        university.start();
        loanCompany.start();



        /**
         * causes the current thread to pause execution until other thread terminates.
         */
        try {
            student.join();
            parent.join();
            university.join();
            loanCompany.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println();
        System.out.println( "STUDENT "+student.getState()+"\n"
                        +"PARENT "+ parent.getState()+"\n"
                        +"UNIVERSITY "+university.getState()+"\n"
                        +"LOAN COMPANY " +loanCompany.getState() ) ;

        /**
         * print final account statement.
         */
        System.out.println(bankAccount);
    }
}
