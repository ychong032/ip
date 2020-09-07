package duke;
import static duke.aesthetics.Emojis.*;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.task.Event;
import java.util.Scanner;

public class Duke {
    private static final int MAX_TASK_LENGTH = 100;
    private static int taskCount = 0;
    private static int listIndex = 0;

    public static void main(String[] args) {
        //Print logo and opening message
        printLogo();
        printGreeting();

        //Receive user input and execute corresponding functions
        getUserInput();

        //Print closing message
        printFarewell();
    }

    public static void getUserInput() {
        Task[] taskList = new Task[MAX_TASK_LENGTH];
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.trim().toLowerCase().equals("bye")) {
            if (userInput.trim().toLowerCase().equals("list")) {
                displayList(taskList);
            } else if (userInput.trim().toLowerCase().contains("done")) {
                markTaskDone(taskList, userInput);
            } else if (userInput.trim().toLowerCase().contains("todo")) {
                addToList(taskList, userInput, "todo");
            } else if (userInput.trim().toLowerCase().contains("deadline")) {
                addToList(taskList, userInput, "deadline");
            } else if (userInput.trim().toLowerCase().contains("event")) {
                addToList(taskList, userInput, "event");
            } else {
                printErrorMessage();
            }
            userInput = sc.nextLine();
        }
    }

    public static void markTaskDone(Task[] taskList, String userInput) {
        try {
            Task taskDone = getCompletedTask(taskList, userInput);
            taskDone.markAsDone();
            printTaskDone(taskDone);
        } catch (NumberFormatException e) {
            printNFEMessage();
        } catch (NullPointerException e) {
            printNPEMessage();
        }
    }

    public static void printNPEMessage() {
        printDivider();
        System.out.println("\t" + SAD_EMOJI + " That task number is not in your list, sir. Please select an"
                + "\n\texisting task number to mark it as completed.");
        printDivider();
    }

    public static void printNFEMessage() {
        printDivider();
        System.out.println("\t" + SAD_EMOJI + " I don't recognise that task number, sir. Please enter" +
                "\n\ta valid task number after the 'done'.");
        printDivider();
    }

    public static void printTaskDone(Task taskDone) {
        printDivider();
        System.out.println("\t" + CLAP_EMOJI + " Very good, sir. Excellent work accomplishing your task:");
        System.out.print('\t');
        System.out.println(taskDone);
        printDivider();
    }

    public static Task getCompletedTask(Task[] taskList, String userInput) throws NumberFormatException,
            NullPointerException {
        String replacedInput = userInput.trim().toLowerCase().replace("done", "");
        int taskNumber = Integer.parseInt(replacedInput.trim());
        return taskList[taskNumber-1];
    }

    public static void addToList(Task[] taskList, String userInput, String taskType) {
        String replacedInput = userInput.replaceFirst("(?i)" + taskType, "").trim();
        try {
            switch (taskType) {
            case "todo":
                addToDoToList(taskList, replacedInput);
                break;
            case "deadline":
                addDeadlineToList(taskList, replacedInput);
                break;
            case "event":
                addEventToList(taskList, replacedInput);
                break;
            }
        } catch (StringIndexOutOfBoundsException e) {
            printSIOOBEMessage();
        } catch (DukeException e) {
            printDEMessage(taskType);
        }
    }

    public static void printDEMessage(String taskType) {
        printDivider();
        System.out.println("\t" + SAD_EMOJI + " Apologies, sir. The description of your " + taskType
                + " cannot be empty.");
        printDivider();
    }

    public static void printSIOOBEMessage() {
        printDivider();
        System.out.println("\t" + SAD_EMOJI + " Please ensure that your deadline or event is followed by"
                + "\n\t'/by' or '/at', respectively.");
        printDivider();
    }

    public static void printAddedTask(Task[] taskList) {
        printDivider();
        System.out.println("\t" + OKAY_EMOJI + " Understood, sir. I've added this task: ");
        System.out.println("\t\t" + taskList[listIndex]);
        System.out.printf("\tYou currently have %d task(s) in your list, sir.%n", taskCount);
        printDivider();
    }

    public static void addToDoToList(Task[] taskList, String replacedInput) throws DukeException {
        if (replacedInput.isBlank()) {
            throw new DukeException();
        }
        taskList[listIndex] = new ToDo(replacedInput);
        taskCount++;
        printAddedTask(taskList);
        listIndex++;
    }

    public static void addDeadlineToList(Task[] taskList, String replacedInput) throws StringIndexOutOfBoundsException,
            DukeException {
        if (replacedInput.isBlank()) {
            throw new DukeException();
        }
        int byIndex = replacedInput.toLowerCase().indexOf("/by");
        String by = replacedInput.substring(byIndex + 3);
        String description = replacedInput.substring(0, byIndex);
        taskList[listIndex] = new Deadline(description, by);
        taskCount++;
        printAddedTask(taskList);
        listIndex++;
    }

    public static void addEventToList(Task[] taskList, String replacedInput) throws StringIndexOutOfBoundsException,
            DukeException {
        if (replacedInput.isBlank()) {
            throw new DukeException();
        }
        int atIndex = replacedInput.toLowerCase().indexOf("/at");
        String at = replacedInput.substring(atIndex + 3);
        String description = replacedInput.substring(0, atIndex);
        taskList[listIndex] = new Event(description, at);
        taskCount++;
        printAddedTask(taskList);
        listIndex++;
    }


    public static void displayList(Task[] taskList) {
        printDivider();
        if (taskList[0] == null) {
            System.out.println("\t" + HURRAY_EMOJI + " You have no tasks recorded, sir. Huzzah!");
        } else {
            System.out.println("\t" + LIST_EMOJI + " Here are your tasks, sir: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("\t%d.", i + 1);
                System.out.println(taskList[i]);
            }
        }
        printDivider();
    }

    public static void printFarewell() {
        printDivider();
        System.out.println("\t" + SMILE_EMOJI + " Always a pleasure, sir. Do come back soon.");
        printDivider();
    }

    public static void printLogo() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
    }

    public static void printGreeting() {
        printDivider();
        System.out.println("\t" + WAVE_EMOJI + " Greetings, sir. My name is duke.Duke");
        System.out.println("\tHow may I assist you today, sir?");
        printDivider();
    }

    public static void printDivider() {
        System.out.println("\t+------------------------------------------------------------------+");
    }

    public static void printErrorMessage() {
        printDivider();
        System.out.println("\t" + CONFUSED_EMOJI + " I'm afraid I don't understand, sir. Please preface " +
                "your tasks " + "\n\twith 'todo', 'deadline', or 'event' to add them to your list.");
        printDivider();
    }
}
