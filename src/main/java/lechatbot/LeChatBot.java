package lechatbot;

import lechatbot.command.Command;
import lechatbot.task.TaskList;

/**
 * LeChatBot is a chatbot that helps users manage their tasks.
 * It supports commands to add, delete, mark, and list tasks.
 */
public class LeChatBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a LeChatBot instance with the specified file path for data storage.
     *
     * @param filePath The file path to load and save task data.
     */
    public LeChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Defines error types for handling different exceptions in LeChatBot.
     */
    public enum ErrorType {
        EMPTY_DESCRIPTION("OOPS!!! The description cannot be empty."),
        TASK_LIST_FULL("OOPS!!! The task list is full."),
        INVALID_COMMAND("OOPS!!! Invalid command!"),
        INVALID_TASK_NUMBER("OOPS!!! The task number provided is invalid."),
        IO_ERROR("OOPS!!! An error occurred while saving or loading tasks.");

        private final String message;

        /**
         * Constructs an ErrorType with the specified error message.
         *
         * @param message The error message associated with this error type.
         */
        ErrorType(String message) {
            this.message = message;
        }

        /**
         * Retrieves the error message associated with this error type.
         *
         * @return The error message as a String.
         */
        public String getMessage() {
            return message;
        }
    }

    /**
     * Runs the chatbot, processing user input until the exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (LeChatBotException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point for running LeChatBot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new LeChatBot("data/LeChatBot.txt").run();
    }
}
