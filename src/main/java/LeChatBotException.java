public class LeChatBotException extends Exception {
    public LeChatBotException(LeChatBot.ErrorType errorType) {
        super(errorType.getMessage());
    }

    public LeChatBotException(String message) {
        super(message);
    }
}