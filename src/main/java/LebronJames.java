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
        System.out.println(LINE + "\n> Hello! I'm Lebron James.\n> What can I do for you?\n" + LINE);
    }

    public static void printExitMessage() {
        System.out.println(LINE + "\n> Bye. Hope to see you again soon!\n" + LINE);
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

    public static void handleMarkTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsDone();
                System.out.println(LINE + "\nNice! I've marked this task as done:\n  " + tasks[taskIndex] + "\n" + LINE);
            } else {
                System.out.println(LINE + "\nInvalid task number!\n" + LINE);
            }
        } catch (NumberFormatException e) {
            System.out.println(LINE + "\nInvalid format! Use: mark X\n" + LINE);
        }
    }

    public static void handleUnmarkTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsNotDone();
                System.out.println(LINE + "\nOK, I've marked this task as not done yet:\n  " + tasks[taskIndex] + "\n" + LINE);
            } else {
                System.out.println(LINE + "\nInvalid task number!\n" + LINE);
            }
        } catch (NumberFormatException e) {
            System.out.println(LINE + "\nInvalid format! Use: unmark X\n" + LINE);
        }
    }

    public static void printTaskAddedMessage(Task task) {
        System.out.println(LINE + "\nGot it. I've added this task:\n" + task +
                "\nNow you have " + taskCount + " tasks in the list.\n" + LINE);
    }

    public static void handleTodoTask(String input) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Todo(input.substring(5));
            taskCount++;
            printTaskAddedMessage(tasks[taskCount - 1]);
        }
    }

    public static void handleDeadlineTask(String input) {
        if (taskCount < MAX_TASKS) {
            String[] parts = input.substring(9).split(" /by ", 2);
            if (parts.length == 2) {
                tasks[taskCount] = new Deadline(parts[0], parts[1]);
                taskCount++;
                printTaskAddedMessage(tasks[taskCount - 1]);
            }
        }
    }

    public static void handleEventTask(String input) {
        if (taskCount < MAX_TASKS) {
            String[] parts = input.substring(6).split(" /from | /to ", 3);
            if (parts.length == 3) {
                tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
                taskCount++;
                printTaskAddedMessage(tasks[taskCount - 1]);
            }
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        while (true) {
            String input = scanner.nextLine().trim();

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
                System.out.println(LINE + "\n>Invalid command! Try: todo, deadline, event, list, mark, unmark, bye\n" + LINE);
            }
        }
        scanner.close();
    }
}
