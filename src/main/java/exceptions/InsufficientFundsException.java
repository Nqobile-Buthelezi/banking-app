package exceptions;

/**
 * The InsufficientFundsException class represents an exception thrown when there are
 * insufficient funds to perform a financial operation.
 */
public class InsufficientFundsException extends Exception {

    /**
     * Constructs an InsufficientFundsException with the specified detail message.
     *
     * @param message The detail message describing the cause of the exception.
     */
    public InsufficientFundsException(String message) {
        super(message);
    }

    // You can add additional constructors or methods based on your needs
}
