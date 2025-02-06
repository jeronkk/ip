package lechatbot.command;

import lechatbot.Storage;
import lechatbot.task.TaskList;
import lechatbot.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}