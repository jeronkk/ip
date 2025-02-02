public class LeChatBotException extends Exception {
    public LeChatBotException(LeChatBot.ErrorType errorType) {
        super(errorType.getMessage());
    }
}