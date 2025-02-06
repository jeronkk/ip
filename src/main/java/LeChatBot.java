public class LeChatBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public enum ErrorType {
        EMPTY_DESCRIPTION("OOPS!!! The description cannot be empty."),
        TASK_LIST_FULL("OOPS!!! The task list is full."),
        INVALID_COMMAND("OOPS!!! Invalid command!"),
        INVALID_TASK_NUMBER("OOPS!!! The task number provided is invalid."),
        IO_ERROR("OOPS!!! An error occurred while saving or loading tasks.");

        private final String message;

        ErrorType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

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

    public static void main(String[] args) {
        new LeChatBot("data/LeChatBot.txt").run();
    }
}
