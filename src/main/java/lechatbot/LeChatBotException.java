package lechatbot;

/**
 * Represents an exception specific to the LeChatBot application.
 */
public class LeChatBotException extends Exception {

    /**
     * Constructs a LeChatBotException with the specified error message.
     *
     * @param message The detail message.
     */
    public LeChatBotException(String message) {
        super(message);
    }

    /**
     * Constructs a LeChatBotException with a predefined error type.
     *
     * @param errorType The error type from LeChatBot.
     */
    public LeChatBotException(LeChatBot.ErrorType errorType) {
        super(errorType.getMessage());
    }
}
