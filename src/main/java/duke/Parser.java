package duke;

import duke.command.*;
import duke.exception.DukeUnknownInputException;

/**
 * A class used for receiving user input and making sense of it.
 */
public class Parser {
    /**
     * Returns a new <code>Command</code> object for the <code>Duke</code> program to execute.
     * @param fullCommand Full user input
     * @return a command depending on the keyword detected in the user input
     * @throws DukeUnknownInputException If no valid keyword is detected.
     * @see DukeUnknownInputException
     */
    public static Command parse(String fullCommand) throws DukeUnknownInputException {
        // Ignore case and remove trailing whitespaces when examining user input
        String trimmedCommand = fullCommand.toLowerCase().trim();
        if (trimmedCommand.contains("todo") | trimmedCommand.contains("deadline") | trimmedCommand.contains("event")) {
            return new AddCommand(fullCommand);
        } else if (trimmedCommand.contains("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (trimmedCommand.contains("done")) {
            return new CompleteCommand(fullCommand);
        } else if (trimmedCommand.equals("list")) {
            return new ListCommand(fullCommand);
        } else if (trimmedCommand.equals("bye")) {
            return new ExitCommand(fullCommand);
        } else {
            throw new DukeUnknownInputException();
        }
    }
}
