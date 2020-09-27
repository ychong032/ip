package duke;

import duke.command.*;
import duke.exception.DukeUnknownInputException;

public class Parser {
    public static Command parse(String fullCommand) throws DukeUnknownInputException {
        String trimmedCommand = fullCommand.toLowerCase().trim();
        if (trimmedCommand.contains("todo") | trimmedCommand.contains("deadline") | trimmedCommand.contains("event")) {
            return new AddCommand(fullCommand);
        } else if (trimmedCommand.contains("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (trimmedCommand.contains("done")) {
            return new CompleteCommand(fullCommand);
        } else if (trimmedCommand.contains("find")) {
            return new FindCommand(fullCommand);
        } else if (trimmedCommand.equals("list")) {
            return new ListCommand(fullCommand);
        } else if (trimmedCommand.equals("bye")) {
            return new ExitCommand(fullCommand);
        } else {
            throw new DukeUnknownInputException();
        }
    }
}
