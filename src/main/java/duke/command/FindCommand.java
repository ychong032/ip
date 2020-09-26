package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeEmptyDescriptionException;

import java.io.IOException;

public class FindCommand extends Command {

    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeEmptyDescriptionException, IOException {
        ui.showSearchResults(tasks.searchTasks(fullCommand), tasks.getTasks());
    }
}
