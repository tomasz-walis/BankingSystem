package bank;

/**
 * Provides the transaction type enums
 * those enums are randomized in the Student Class
 *
 * @author  Tomasz Walis-Walisiak
 * @version 1.0
 * @since   2018-01-06
 */

enum TransactionType {


    LOTTERY {
        public String toString() {
            return "lottery";
        }
    },

    WITHDRAW {
        public String toString() {
            return "withdrawal";
        }
    },

    DEPOSIT {
        public String toString() {
            return "deposit";
        }
    },
}
