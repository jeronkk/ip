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
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.print(LINE);
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

    public static void main(String[] args) {
        printWelcomeMessage();

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                printExitMessage();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printTaskList();
            } else if (input.startsWith("mark ")) {
                handleMarkTask(input);
            } else if (input.startsWith("unmark ")) {
                handleUnmarkTask(input);
            }  else {
                System.out.println(LINE + "\n>Invalid command! Try: todo, deadline, event, list, mark, unmark, bye\n" + LINE);
            }
        }
        scanner.close();
    }
}
