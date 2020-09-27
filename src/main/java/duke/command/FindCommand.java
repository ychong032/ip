package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeEmptyDescriptionException;

import java.io.IOException;

/**
 * Represents a command that searches the task list for tasks that contain the search phrase.
 */
public class FindCommand extends Command {

    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Prints the matching tasks from the task list that contain the user-specified search phrase.
     * @param tasks the object storing the list of tasks.
     * @param ui the <code>Ui</code> object of the current program.
     * @param storage the object for writing and reading of the storage file.
     * @throws DukeEmptyDescriptionException if the user fails to input a description of the task.
     * @throws IOException if there is a problem related to the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeEmptyDescriptionException, IOException {
        ui.showSearchResults(tasks.searchTasks(fullCommand), tasks.getTasks());
    }
}
