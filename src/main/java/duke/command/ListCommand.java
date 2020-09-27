package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to display the current list of tasks.
 */
public class ListCommand extends Command {

    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Prints the current list of tasks, including the type, description, and status of completion.
     * @param tasks the object for storing the list of tasks.
     * @param ui the <code>Ui</code> object of the current program.
     * @param storage the object for writing and reading of the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
