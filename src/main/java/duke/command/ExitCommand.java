package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command for exiting the Duke program.
 */
public class ExitCommand extends Command {

    public ExitCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Sets the <code>isExit</code> attribute to <code>true</code>. When control is returned
     * to the <code>run</code> method in the <code>Duke</code> class, the <code>run</code>
     * method ends.
     * @param tasks the object for storing the list of tasks.
     * @param ui the <code>Ui</code> object of the current program.
     * @param storage the object for writing and reading of the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
    }
}
