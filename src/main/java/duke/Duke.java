package duke;

import duke.command.Command;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeUnknownInputException;

import java.io.IOException;
import java.io.File;

/**
 * <h1>Duke</h1>
 * The Duke program accepts user inputs to add and store different types of tasks in a list.
 * Users can also search for tasks, mark them as completed, delete them, or display the current
 * list of tasks. Whenever changes are made to the task list, the text file used for storing the
 * task list is updated. Upon termination of the Duke program, the task list will be retained
 * in this text file. When the Duke program is run again, the same task list will be loaded from
 * this file.
 *
 * @author Chong Yow Lim
 * @since 2020-08-14
 */
public class Duke {
    // Represents the index position of the most recently added task in the task list.
    // This is used for getting tasks from a file.
    protected static int listIndex = 0;
    private static final String PATH = "data" + File.separator + "duke.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a <code>Duke</code> object given a file path.
     * Reads data from the file used for storage of the task list into a new <code>TaskList</code>.
     * @param filePath the file path of the storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showFileNotFoundError();
            System.exit(-1);
        }
    }

    /**
     * Runs the Duke program and prints various exception messages. User input is
     * read and used to instantiate a <code>Command</code> object. This object performs different
     * operations depending on keywords detected in the user input. The method ends when the
     * <code>Command</code> object is an <code>ExitCommand</code>.
     */
    public void run() {
        ui.printLogo();
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showFileNotFoundError();
                System.exit(-1);
            } catch (NumberFormatException e) {
                ui.showTaskNumberError();
            } catch (StringIndexOutOfBoundsException e) {
                ui.showInputFormatError();
            } catch (IndexOutOfBoundsException e) {
                ui.showMissingTaskNumberError();
            } catch (DukeEmptyDescriptionException e) {
                ui.showBlankDescriptionError();
            } catch (DukeUnknownInputException e) {
                ui.showUnknownInputError();
            }
        }
        ui.printFarewell();
    }

    public static void main(String[] args) {
        new Duke(PATH).run();
    }
}
