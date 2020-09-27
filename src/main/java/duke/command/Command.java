package duke.command;

import duke.exception.DukeEmptyDescriptionException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * An abstract class for executing different commands in the Duke program.
 */
public abstract class Command {
    protected String fullCommand;
    protected boolean isExit;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeEmptyDescriptionException;

    public boolean isExit() {
        return isExit;
    }
}
