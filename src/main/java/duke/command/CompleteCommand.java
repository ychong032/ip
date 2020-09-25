package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class CompleteCommand extends Command {

    public CompleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showTaskDone(tasks.markTaskDone(fullCommand));
        storage.writeToFile();
    }
}
