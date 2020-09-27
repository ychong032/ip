package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Represents a command for deleting tasks from the task list.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Deletes an existing task specified by the user. The deleted task is printed
     * on the screen. Then, the file storing the task list is updated.
     * @param tasks the object for storing the list of tasks.
     * @param ui the <code>Ui</code> object of the current program.
     * @param storage the object for writing and reading of the storage file.
     * @throws IOException if writing to the file fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showTaskDeleted(tasks.deleteTask(fullCommand), tasks);
        storage.writeToFile();
    }
}
