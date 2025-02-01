import java.util.Scanner;

public class LebronJames {
    private static final String LINE = "____________________________________________";
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    private static Scanner scanner = new Scanner(System.in);

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
        if (taskCount == 0) {
            System.out.println("\nNo tasks added yet.");
        } else {
            System.out.println("\nHere are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.print(LINE+ "\n");
    }

    public static void handleMarkTask(String input) throws LebronJamesException {
        try {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            if (taskIndex < 0 || taskIndex >= taskCount) {
                throw LebronJamesException.invalidTaskNumber();
            }
            tasks[taskIndex].markAsDone();
            System.out.println(LINE + "\nNice! I've marked this task as done:\n  " + tasks[taskIndex] + "\n" + LINE);
        } catch (NumberFormatException e) {
            throw LebronJamesException.invalidCommand();
        }
    }

    public static void handleUnmarkTask(String input) throws LebronJamesException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            if (taskIndex < 0 || taskIndex >= taskCount) {
                throw LebronJamesException.invalidTaskNumber();
            }
            tasks[taskIndex].markAsNotDone();
            System.out.println(LINE + "\nOK, I've marked this task as not done yet:\n  " + tasks[taskIndex] + "\n" + LINE);
        } catch (NumberFormatException e) {
            throw LebronJamesException.invalidCommand();
        }
    }

    public static void printTaskAddedMessage(Task task) {
        System.out.println(LINE + "\nGot it. I've added this task:\n" + task +
                "\nNow you have " + taskCount + " tasks in the list.\n" + LINE);
    }

    public static void handleTodoTask(String input) throws LebronJamesException {
        if (input.trim().equals("todo")) {
            throw LebronJamesException.emptyTaskDescription();
        }
        if (taskCount >= MAX_TASKS) {
            throw LebronJamesException.taskListFull();
        }
        tasks[taskCount] = new Todo(input.substring(5));
        taskCount++;
        printTaskAddedMessage(tasks[taskCount - 1]);
    }

    public static void handleDeadlineTask(String input) throws LebronJamesException {
        if (taskCount >= MAX_TASKS) {
            throw LebronJamesException.taskListFull();
        }
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length != 2) {
            throw LebronJamesException.invalidCommand();
        }
        tasks[taskCount] = new Deadline(parts[0], parts[1]);
        taskCount++;
        printTaskAddedMessage(tasks[taskCount - 1]);
    }

    public static void handleEventTask(String input) throws LebronJamesException {
        if (taskCount >= MAX_TASKS) {
            throw LebronJamesException.taskListFull();
        }
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length != 3) {
            throw LebronJamesException.invalidCommand();
        }
        tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
        taskCount++;
        printTaskAddedMessage(tasks[taskCount - 1]);
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                if (input.equals("bye")) {
                    printExitMessage();
                    break;
                } else if (input.equals("list")) {
                    printTaskList();
                } else if (input.startsWith("mark ")) {
                    handleMarkTask(input);
                } else if (input.startsWith("unmark ")) {
                    handleUnmarkTask(input);
                } else if (input.startsWith("todo ")) {
                    handleTodoTask(input);
                } else if (input.startsWith("deadline ")) {
                    handleDeadlineTask(input);
                } else if (input.startsWith("event ")) {
                    handleEventTask(input);
                } else {
                    throw LebronJamesException.invalidCommand();
                }
            } catch (LebronJamesException e) {
                System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
            }
        }
        scanner.close();
    }
}
