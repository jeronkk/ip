package lechatbot.ui;

import java.util.Scanner;

import lechatbot.task.TaskList;

/**
 * Handles user interaction and console output.
 * This class provides methods to display messages, read user input, and show task lists.
 */
public class Ui {
    private static final String LINE = "____________________________________________";
    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} instance.
     * Initializes the scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message with ASCII art and instructions.
     */
    public void showWelcome() {
        String asciiArt = """
                                                            @@@@@@@@@@@@                                           \s
                                                          @@@@%#*#*##%@@@@@                                        \s
                                                        @@#**++++++****####@@                                       \s
                                                       @%#*+=-=======+++*###@@                                      \s
                                                      @@**---:-======+==-=###@@                                     \s
                                                      @%++*+=+**++==+*##*****%@                                     \s
                                                      %#+##+=--::-::::---+#%**%@                                    \s
                                                      #*##*+#%@+=....=#@@%+*%*#@                                    \s
                                                    %####=*##%==----==**%****##@%%                                 \s
                                                   -#=#@+=*==+###+=++%%%*=*#+%#=*#                                 \s
                                                   *.-%*-:::---=+==-=++=-:::=*%+:-                                 \s
                                                   *%+%+=..:-*=-.   ..-++-..=*#+%#                                 \s
                                                    +#@*+..::.:-=*##+=-:.::.:+#%+*                                 \s
                                                    *@@**--=*#%@%%##%*@@%*=-=#%%*                                  \s
                                                     @@%+-.-*@+-      -#@*-:=%@@                                   \s
                                                     @@@@@@=-==:  .   =**-*@@@@@                                   \s
                                                     #@@@@@@@#::*%%#**--@@@@@@@@                                   \s
                                                      @@@@@@@@@-.-=+:.=@@@@@@@@                                    \s
                                                      @@@@@@@@@@@#%%*#@@@@@@@@@                                    \s
                                                       @@@@@@@@@@@@@@@@@@@@@@@                                     \s
                                                        *#@@@@@@@@@@@@@@@@@%*                                      \s
                                                       @#=--+%@@@@@@@@@@%+==*%%@*:                                 \s
                                                +.#@####*=-::-==+-:-+==+---=+#**:==...                             \s
                                             .. .:.+#==++--...:--=+*=---:--==++@@@...... #                         \s
                                           ..... =:.=:-==--:.....:---::.::::==+..%@@=:.. @.-%                      \s
                                       %# .......  -.+-:--:::::::......::::::+:.% ....-@@@=+@###                   \s
                                     ##..# ........ +..+:::.........:......:=..* ....... %:=+*#*#*%%               \s
                                 @%%%+%*.*: ........  =..===---........---=..+  ........ *..==+++*#*#@%            \s
                             @@%*#=++=--..% ........... ::...::::-:::..:...+  .......... =..--==+++++*%%@          \s
                           @@%*+*=====--..+ .............   *.....::....*  ..........:..  -.-:-=====+**++%         \s
                          %%*===*==-=--::..  .................    ..    ......::=::...... +...:::---=#+=*%%        \s
                         %#==--=-:---::....  .......................................-.... ...:.:::::==-=**#@       \s
                        %#+---:::::....::..  ......-.............................:........ -.....::.-==++**%@      \s
                       %#*==---:.:.:..:::.: ...:*:........................................ :..:.....:--+=+*%%      \s
                       %*+-=::....:-::-...+ ............................................... =..:....:..---+#%%     \s
                """;
        String asciiText = """
                ██╗     ███████╗██████╗ ██████╗  ██████╗ ███╗   ██╗         ██╗ █████╗ ███╗   ███╗███████╗███████╗
                ██║     ██╔════╝██╔══██╗██╔══██╗██╔═══██╗████╗  ██║         ██║██╔══██╗████╗ ████║██╔════╝██╔════╝
                ██║     █████╗  ██████╔╝██████╔╝██║   ██║██╔██╗ ██║         ██║███████║██╔████╔██║█████╗  ███████╗
                ██║     ██╔══╝  ██╔══██╗██╔══██╗██║   ██║██║╚██╗██║    ██   ██║██╔══██║██║╚██╔╝██║██╔══╝  ╚════██║
                ███████╗███████╗██████╔╝██║  ██║╚██████╔╝██║ ╚████║    ╚█████╔╝██║  ██║██║ ╚═╝ ██║███████╗███████║
                ╚══════╝╚══════╝╚═════╝ ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝     ╚════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝╚══════╝
                """;
        System.out.println(asciiArt + asciiText);
        showLine();
        System.out.println("Hello! I'm LeChatBot.");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the exit message when the bot is terminated.
     *
     * @return A formatted exit message.
     */
    public String showExitMessage() {
        String response = "Bye. Hope to see you again soon!";
        showLine();
        System.out.println(response);
        showLine();
        return response;
    }

    /**
     * Displays an error message when loading saved tasks fails.
     */
    public void showLoadingError() {
        System.out.println(LINE + "\nError loading saved tasks.\n" + LINE);
    }

    /**
     * Displays a formatted error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(LINE + "\n" + message + "\n" + LINE);
    }

    /**
     * Generates a formatted string representation of all tasks in the task list.
     *
     * @param tasks The task list whose tasks will be displayed.
     * @return A formatted string representation of the task list.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder response = new StringBuilder();
        response.append(LINE).append("\n");

        if (tasks.isEmpty()) {
            response.append("No tasks added yet.");
        } else {
            response.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                response.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }

        response.append(LINE);
        System.out.println(response);
        int startIndex = LINE.length() + 1;
        int endIndex = response.length() - LINE.length();
        return response.substring(startIndex, endIndex).trim();
    }

    /**
     * Displays the help message with a list of available commands in LeChatBot.
     *
     * <p>
     * The method prints the help message wrapped between horizontal lines for
     * a structured console output. However, it returns only the contents of
     * the help message without the enclosing lines.
     * </p>
     *
     * @return A formatted string containing the help message without the horizontal lines.
     */
    public String showHelpMessage() {
        String helpMessage =
                "Here are the available commands in LeChatBot:\n"
                        + "1. todo <task> - Adds a new todo.\n"
                        + "2. deadline <task> /by <date> - Adds a deadline task.\n"
                        + "   Example: deadline trade Anthony Davis /by 06/02/2025\n"
                        + "3. event <task> /from <start date/time> /to <end date/time> - Adds an event task.\n"
                        + "   Example: event team meeting /from 03/02/2025 1400 /to 03/02/2025 1600\n"
                        + "4. list - Displays all tasks.\n"
                        + "5. mark <task number> - Marks task as done.\n"
                        + "6. unmark <task number> - Marks task as not done.\n"
                        + "7. find <keyword> - Searches for tasks containing the keyword.\n"
                        + "8. delete <task number> - Deletes a task.\n"
                        + "9. exit - Exits the application.\n";
        System.out.println(LINE + "\n" + helpMessage + LINE);
        return helpMessage.trim();
    }

    /**
     * Reads and returns the next user command input.
     *
     * @return The user input command as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Generates a formatted string displaying tasks that match a given keyword.
     *
     * @param matchingTasks The list of tasks that match the search criteria.
     * @return A formatted string listing the matching tasks, or a message if none are found.
     */
    public String showMatchingTasks(TaskList matchingTasks) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:\n")
                .append(LINE).append("\n");

        for (int i = 0; i < matchingTasks.getSize(); i++) {
            response.append((i + 1)).append(". ").append(matchingTasks.getTask(i)).append("\n");
        }

        response.append(LINE);
        return response.toString();
    }

}
