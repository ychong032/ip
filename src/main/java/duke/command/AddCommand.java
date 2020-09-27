package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeEmptyDescriptionException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a command for adding tasks into the task list.
 */
public class AddCommand extends Command {
    private String taskType;

    /**
     * Constructs an <code>AddCommand</code> object given the full user input.
     * Identifies the keyword in the user input and accordingly determines the task type to be added.
     * @param fullCommand full user input.
     */
    public AddCommand(String fullCommand) {
        super(fullCommand);
        String trimmedCommand = fullCommand.toLowerCase().trim();
        if (trimmedCommand.contains("todo")) {
            taskType = "todo";
        } else if (trimmedCommand.contains("deadline")) {
            taskType = "deadline";
        } else {
            taskType = "event";
        }
    }

    /**
     * Adds a new task to the task list. After this task is added to the list, it is printed onto
     * the screen. Then, the file used for storing tasks is updated with this task.
     * @param tasks the object storing the list of tasks.
     * @param ui the <code>Ui</code> object of the current program.
     * @param storage the object for writing and reading of the storage file.
     * @throws DukeEmptyDescriptionException if user does not give a description of the task.
     * @throws IOException if writing to the file fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeEmptyDescriptionException, IOException {
        try {
            tasks.addToList(fullCommand, taskType);
            ui.showAddedTask(tasks);
            storage.writeToFile();
        } catch (DateTimeParseException e) {
            ui.showDateInputError();
        }
    }
}
