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
        return response.toString();
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
