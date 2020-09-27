package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Represents a command for marking existing tasks as completed.
 */
public class CompleteCommand extends Command {

    public CompleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Marks an existing task in the task list as completed. The task is then printed
     * on the screen. Next, the file used for storing tasks is updated.
     * @param tasks the object for storing the list of tasks.
     * @param ui the <code>Ui</code> object of the current program.
     * @param storage the object for writing and reading of the storage file.
     * @throws IOException if writing to the file fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showTaskDone(tasks.markTaskDone(fullCommand));
        storage.writeToFile();
    }
}
