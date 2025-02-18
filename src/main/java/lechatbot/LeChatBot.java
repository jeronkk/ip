package lechatbot;

import lechatbot.command.Command;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

/**
 * The main entry point for the LeChatBot application.
 * This class initializes the application, loads tasks from storage,
 * and processes user input through a command loop.
 */
public class LeChatBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String commandType;

    /**
     * Constructs a new LeChatBot instance.
     * Initializes UI, loads tasks from storage, and handles errors if the file cannot be loaded.
     *
     * @param filePath The file path where task data is stored.
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
     * Enum representing different types of errors that may occur in LeChatBot.
     */
    public enum ErrorType {
        /**
         * Error for empty task descriptions.
         */
        EMPTY_DESCRIPTION("OOPS!!! The description cannot be empty."),
        /**
         * Error when the task list is full.
         */
        TASK_LIST_FULL("OOPS!!! The task list is full."),
        /**
         * Error for an invalid command.
         */
        INVALID_COMMAND("OOPS!!! Invalid command!"),
        /**
         * Error when an invalid task number is provided.
         */
        INVALID_TASK_NUMBER("OOPS!!! The task number provided is invalid."),
        /**
         * Error related to input/output operations.
         */
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
         * Retrieves the error message.
         *
         * @return The error message associated with the error type.
         */
        public String getMessage() {
            return message;
        }
    }

    /**
     * Runs the main loop of LeChatBot.
     * Continuously reads user input, processes commands, and executes them
     * until the user decides to exit.
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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return response;
        } catch (LeChatBotException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }

    /**
     * The main method that starts the LeChatBot application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new LeChatBot("data/LeChatBot.txt").run();
    }
}
