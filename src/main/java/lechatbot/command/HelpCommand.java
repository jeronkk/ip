package lechatbot.command;

import lechatbot.LeChatBotException;
import lechatbot.Storage;
import lechatbot.task.TaskList;
import lechatbot.ui.Ui;

/**
 * Command that displays a list of available commands.
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super(null);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LeChatBotException {
        return ui.showHelpMessage();
    }
}
