package duke.command;

import duke.*;
import duke.exception.DukeEmptyDescriptionException;

import java.io.IOException;

public class AddCommand extends Command {
    private String taskType;

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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeEmptyDescriptionException, IOException {
        tasks.addToList(fullCommand, taskType);
        ui.showAddedTask(tasks);
        storage.writeToFile();
    }
}
