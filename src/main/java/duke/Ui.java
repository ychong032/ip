package duke;

import duke.aesthetics.Emojis;
import duke.task.Task;

import java.util.Scanner;

/**
 * Handles interactions with users. Reads input given by users, prints messages when exceptions arise, and displays
 * changes to the task list when they occur.
 */
public class Ui {
    /**
     * Reads a line of user input and returns it.
     * @return a line of user input as a <code>String</code>.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints a message showing the task that was deleted and the number of remaining tasks in the task list.
     * @param deletedTask the <code>Task</code> object that was deleted from the task list.
     * @param tasks the <code>TaskList</code> object containing the task list.
     * @see Task
     * @see TaskList
     */
    public void showTaskDeleted(Task deletedTask, TaskList tasks) {
        printDivider();
        System.out.println("\tUnderstood, I've deleted this task: ");
        System.out.println("\t\t" + deletedTask);
        System.out.printf("\tYou now have %d task(s) in your list, sir.\n", tasks.getTasks().size());
        printDivider();
    }

    /**
     * Prints an error message when the user-specified task number is outside the range of existing task numbers.
     */
    public void showMissingTaskNumberError() {
        printDivider();
        System.out.println("\tThat task number is not in your list, sir.");
        printDivider();
    }

    /**
     * Prints an error message when storage file for the task list cannot be found.
     */
    public void showFileNotFoundError() {
        printDivider();
        System.out.println("\tduke.txt does not exist in /data folder!");
        System.out.println("\tTerminating Duke...");
        printDivider();
    }

    /**
     * Prints an error message when the user-specified task number is not an integer.
     */
    public void showTaskNumberError() {
        printDivider();
        System.out.println("\t" + Emojis.SAD_EMOJI + " I don't recognise that task number, sir. Please enter a valid" +
                "\n\ttask number after the keyword.");
        printDivider();
    }

    /**
     * Prints the task that was marked as completed.
     * @param taskDone the <code>Task</code> object that was marked as completed.
     * @see Task
     */
    public void showTaskDone(Task taskDone) {
        printDivider();
        System.out.println("\t" + Emojis.CLAP_EMOJI + " Very good, sir. Excellent work accomplishing your task:");
        System.out.println("\t\t" + taskDone);
        printDivider();
    }

    /**
     * Prints an error message when the user fails to specify a description of a task.
     */
    public void showBlankDescriptionError() {
        printDivider();
        System.out.println("\t" + Emojis.SAD_EMOJI + " Apologies, sir. The description of your task cannot be empty.");
        printDivider();
    }

    /**
     * Prints an error message when the user fails to input "/by" for deadlines or "/at" for events.
     */
    public void showInputFormatError() {
        printDivider();
        System.out.println("\t" + Emojis.SAD_EMOJI + " Please ensure that your deadline or event is followed by"
                + "\n\t'/by' or '/at', respectively.");
        printDivider();
    }

    /**
     * Prints the task added to the task list.
     * @param tasks the <code>TaskList</code> object containing the task list.
     * @see TaskList
     */
    public void showAddedTask(TaskList tasks) {
        printDivider();
        System.out.println("\t" + Emojis.OKAY_EMOJI + " Understood, sir. I've added this task: ");
        System.out.println("\t\t" + tasks.getTasks().get(tasks.getTasks().size() - 1));
        System.out.printf("\tYou currently have %d task(s) in your list, sir.%n", tasks.getTasks().size());
        printDivider();
    }

    /**
     * Prints the entire task list. Prints a different message if the task list is empty.
     * @param tasks the <code>TaskList</code> object containing the task list.
     * @see TaskList
     */
    public void showTaskList(TaskList tasks) {
        printDivider();
        if (tasks.getTasks().isEmpty()) {
            System.out.println("\t" + Emojis.HURRAY_EMOJI + " You have no tasks recorded, sir. Huzzah!");
        } else {
            System.out.println("\t" + Emojis.LIST_EMOJI + " Here are your tasks, sir: ");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                System.out.printf("\t%d.", i + 1);
                System.out.println(tasks.getTasks().get(i));
            }
        }
        printDivider();
    }

    /**
     * Prints a goodbye message upon termination of the program.
     */
    public void printFarewell() {
        printDivider();
        System.out.println("\t" + Emojis.SMILE_EMOJI + " Always a pleasure, sir. Do come back soon.");
        printDivider();
    }

    /**
     * Prints the logo of the program.
     */
    public void printLogo() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
    }

    /**
     * Prints the welcome message for when the program begins.
     */
    public void printGreeting() {
        printDivider();
        System.out.println("\t" + Emojis.WAVE_EMOJI + " Greetings, sir. My name is Duke");
        System.out.println("\tHow may I assist you today, sir?");
        printDivider();
    }

    public void printDivider() {
        System.out.println("\t+------------------------------------------------------------------+");
    }

    /**
     * Prints an error message for when there are no valid keywords found in the user input.
     */
    public void showUnknownInputError() {
        printDivider();
        System.out.println("\t" + Emojis.CONFUSED_EMOJI + " I'm afraid I don't understand, sir. Please preface " +
                "your tasks " + "\n\twith 'todo', 'deadline', or 'event' to add them to your list.");
        printDivider();
    }
}