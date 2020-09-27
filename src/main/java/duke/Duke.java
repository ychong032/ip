package duke;

import duke.command.Command;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeUnknownInputException;

import java.io.IOException;
import java.io.File;

public class Duke {
    protected static int listIndex = 0;
    private static final String PATH = "data" + File.separator + "duke.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

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
