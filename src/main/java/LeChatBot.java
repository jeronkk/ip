import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class LeChatBot {
    private static final String LINE = "____________________________________________";
    private static final int MAX_TASKS = 100;
    private static List<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public enum Command {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, INVALID
    }

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public enum ErrorType {
        EMPTY_DESCRIPTION("OOPS!!! The description cannot be empty."),
        TASK_LIST_FULL("OOPS!!! The task list is full."),
        INVALID_COMMAND("OOPS!!! Invalid command! Try: todo, deadline, event, list, mark, unmark, bye"),
        INVALID_TASK_NUMBER("OOPS!!! The task number provided is invalid.");

        private final String message;

        ErrorType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static void printWelcomeMessage() {
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
        System.out.println(LINE + "\nHello! I'm Lebron James.\nWhat can I do for you?\n" + LINE);
    }

    public static void printExitMessage() {
        System.out.println(LINE + "\nBye. Hope to see you again soon!\n" + LINE);
    }

    public static void printTaskList() {
        System.out.print(LINE);
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks added yet.");
        } else {
            System.out.println("\nHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.print(LINE + "\n");
    }

    public static Command getCommand(String input) {
        if (input.equals("list")) return Command.LIST;
        if (input.startsWith("mark ")) return Command.MARK;
        if (input.startsWith("unmark ")) return Command.UNMARK;
        if (input.startsWith("todo ")) return Command.TODO;
        if (input.startsWith("deadline ")) return Command.DEADLINE;
        if (input.startsWith("event ")) return Command.EVENT;
        if (input.startsWith("delete ")) return Command.DELETE;
        if (input.equals("bye")) return Command.BYE;
        return Command.INVALID;
    }

    public static void handleMarkTask(String input) throws LeChatBotException {
        try {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new LeChatBotException(ErrorType.INVALID_TASK_NUMBER);
            }
            tasks.get(taskIndex).markAsDone();
            System.out.println(LINE + "\nNice! I've marked this task as done:\n  " + tasks.get(taskIndex) + "\n" + LINE);
        } catch (NumberFormatException e) {
            throw new LeChatBotException(ErrorType.INVALID_COMMAND);
        }
    }

    public static void handleUnmarkTask(String input) throws LeChatBotException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new LeChatBotException(ErrorType.INVALID_TASK_NUMBER);
            }
            tasks.get(taskIndex).markAsNotDone();
            System.out.println(LINE + "\nOK, I've marked this task as not done yet:\n  " + tasks.get(taskIndex) + "\n" + LINE);
        } catch (NumberFormatException e) {
            throw new LeChatBotException(ErrorType.INVALID_COMMAND);
        }
    }

    public static void printTaskAddedMessage(Task task) {
        System.out.println(LINE + "\nGot it. I've added this task:\n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.\n" + LINE);
    }

    public static void handleTodoTask(String input) throws LeChatBotException {
        if (input.trim().equals("todo")) {
            throw new LeChatBotException(ErrorType.EMPTY_DESCRIPTION);
        }
        if (tasks.size() >= MAX_TASKS) {
            throw new LeChatBotException(ErrorType.TASK_LIST_FULL);
        }
        tasks.add(new Todo(input.substring(5)));
        printTaskAddedMessage(tasks.get(tasks.size() - 1));
    }

    public static void handleDeadlineTask(String input) throws LeChatBotException {
        if (tasks.size() >= MAX_TASKS) {
            throw new LeChatBotException(ErrorType.TASK_LIST_FULL);
        }
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length != 2) {
            throw new LeChatBotException(ErrorType.INVALID_COMMAND);
        }
        tasks.add(new Deadline(parts[0], parts[1]));
        printTaskAddedMessage(tasks.get(tasks.size() - 1));
    }

    public static void handleEventTask(String input) throws LeChatBotException {
        if (tasks.size() >= MAX_TASKS) {
            throw new LeChatBotException(ErrorType.TASK_LIST_FULL);
        }
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length != 3) {
            throw new LeChatBotException(ErrorType.INVALID_COMMAND);
        }
        tasks.add(new Event(parts[0], parts[1], parts[2]));
        printTaskAddedMessage(tasks.get(tasks.size() - 1));
    }

    public static void handleDeleteTask(String input) throws LeChatBotException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new LeChatBotException(ErrorType.INVALID_TASK_NUMBER);
            }
            Task removedTask = tasks.remove(taskIndex);
            System.out.println(LINE + "\nNoted. I've removed this task:\n  " + removedTask +
                    "\nNow you have " + tasks.size() + " tasks in the list.\n" + LINE);
        } catch (NumberFormatException e) {
            throw new LeChatBotException(ErrorType.INVALID_COMMAND);
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        while (true) {
            String input = scanner.nextLine().trim();
            try {
                Command command = getCommand(input);
                switch (command) {
                    case BYE:
                        printExitMessage();
                        return;
                    case LIST:
                        printTaskList();
                        break;
                    case MARK:
                        handleMarkTask(input);
                        break;
                    case UNMARK:
                        handleUnmarkTask(input);
                        break;
                    case TODO:
                        handleTodoTask(input);
                        break;
                    case DEADLINE:
                        handleDeadlineTask(input);
                        break;
                    case EVENT:
                        handleEventTask(input);
                        break;
                    case DELETE:
                        handleDeleteTask(input);
                        break;
                    default:
                        throw new LeChatBotException(ErrorType.INVALID_COMMAND);
                }
            } catch (LeChatBotException e) {
                System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
            }
        }
    }
}
