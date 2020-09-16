package duke;
import static duke.aesthetics.Emojis.*;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.task.Event;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;

public class Duke {
    private static int listIndex = 0;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String PATH = "data" + File.separator + "duke.txt";


    public static void main(String[] args) {
        //Print logo and opening message
        try {
            File dukeFile = createDukeFile();
            readDukeFile(dukeFile);
        } catch (FileNotFoundException e) {
            System.out.println("\tduke.txt not found in /data folder!");
            System.exit(-1);
        } catch (IOException e) {
            printIOEMessage();
            System.exit(-1);
        }

        printLogo();
        printGreeting();

        //Receive user input and execute corresponding functions

        getUserInput();

        //Print closing message
        printFarewell();
    }

    public static File createDukeFile() throws IOException {
        File dukeFile = new File(PATH);
        if (!dukeFile.exists()) {
            new File(dukeFile.getParent()).mkdirs();
            dukeFile.createNewFile();
        }
        return dukeFile;
    }

    public static void readDukeFile(File dukeFile) throws FileNotFoundException {
        Scanner sc = new Scanner(dukeFile);
        String taskAsString;
        while (sc.hasNext()) {
            taskAsString = sc.nextLine();
            if (taskAsString.contains("[T]")) {
                readToDo(taskAsString);
            } else if (taskAsString.contains("[D]")) {
                readDeadline(taskAsString);
            } else {
                readEvent(taskAsString);
            }
            if (taskAsString.contains("\u2713")) {
                tasks.get(listIndex).markAsDone();
            }
            listIndex++;
        }
    }

    public static void readEvent(String taskAsString) {
        String description;
        int atStartIndex = taskAsString.indexOf(" (at:") + " (at:".length();
        int atEndIndex = taskAsString.lastIndexOf(")");
        String at = taskAsString.substring(atStartIndex, atEndIndex);
        description = taskAsString.substring(taskAsString.indexOf(" "), taskAsString.indexOf(" (at:"));
        tasks.add(new Event(description, at));
    }

    public static void readDeadline(String taskAsString) {
        String description;
        int deadlineStartIndex = (taskAsString.indexOf(" (by:") + " (by:".length());
        int deadlineEndIndex = taskAsString.lastIndexOf(")");
        String deadline = taskAsString.substring(deadlineStartIndex, deadlineEndIndex);
        description = taskAsString.substring(taskAsString.indexOf(" "), taskAsString.indexOf(" (by:"));
        tasks.add(new Deadline(description, deadline));
    }

    public static void readToDo(String taskAsString) {
        String description = taskAsString.substring(taskAsString.indexOf(" "));
        tasks.add(new ToDo(description));
    }

    public static void writeToFile() throws IOException {
        FileWriter dukeFile = new FileWriter("data/duke.txt");
        for (Task task : tasks) {
            dukeFile.write(task.toString() + "\n");
        }
        dukeFile.close();
    }

    public static void getUserInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.trim().toLowerCase().equals("bye")) {
            if (userInput.trim().toLowerCase().equals("list")) {
                displayList();
            } else if (userInput.trim().toLowerCase().contains("done")) {
                markTaskDone(userInput);
            } else if (userInput.trim().toLowerCase().contains("todo")) {
                addToList(userInput, "todo");
            } else if (userInput.trim().toLowerCase().contains("deadline")) {
                addToList(userInput, "deadline");
            } else if (userInput.trim().toLowerCase().contains("event")) {
                addToList(userInput, "event");
            } else if (userInput.trim().toLowerCase().contains("delete")) {
                deleteTask(userInput);
            } else {
                printErrorMessage();
            }
            userInput = sc.nextLine();
        }
    }

    public static void markTaskDone(String userInput) {
        try {
            Task taskDone = getCompletedTask(userInput);
            taskDone.markAsDone();
            printTaskDone(taskDone);
            writeToFile();
        } catch (NumberFormatException e) {
            printNFEMessage();
        } catch (IndexOutOfBoundsException e) {
            printIOOEMessage();
        } catch (IOException e) {
            printIOEMessage();
            System.exit(-1);
        }
    }

    public static void deleteTask(String userInput) {
        try {
            Task deletedTask = getDeletedTask(userInput);
            printTaskDeleted(deletedTask);
            tasks.remove(deletedTask);
            writeToFile();
        } catch (NumberFormatException e) {
            printNFEMessage();
        } catch (IndexOutOfBoundsException e) {
            printIOOEMessage();
        } catch (IOException e) {
            printIOEMessage();
            System.exit(-1);
        }
    }

    public static void printTaskDeleted(Task deletedTask) {
        printDivider();
        System.out.println("\tUnderstood, I've deleted this task: ");
        System.out.println("\t\t" + deletedTask);
        System.out.printf("\tYou now have %d task(s) in your list, sir.\n", tasks.size() - 1);
        printDivider();
    }

    public static Task getDeletedTask(String userInput) throws NumberFormatException, IndexOutOfBoundsException {
        String replacedInput = userInput.trim().toLowerCase().replace("delete", "");
        int taskDeleted = Integer.parseInt(replacedInput.trim());
        return tasks.get(taskDeleted - 1);
    }

    public static void printIOOEMessage() {
        printDivider();
        System.out.println("\tThat task number is not in your list, sir.");
        printDivider();
    }

    public static void printIOEMessage() {
        printDivider();
        System.out.println("\tduke.txt does not exist in /data folder!");
        System.out.println("\tTerminating Duke...");
        printDivider();
    }

    public static void printNFEMessage() {
        printDivider();
        System.out.println("\t" + SAD_EMOJI + " I don't recognise that task number, sir. Please enter a valid" +
                "\n\ttask number after the keyword.");
        printDivider();
    }

    public static void printTaskDone(Task taskDone) {
        printDivider();
        System.out.println("\t" + CLAP_EMOJI + " Very good, sir. Excellent work accomplishing your task:");
        System.out.println("\t\t" + taskDone);
        printDivider();
    }

    public static Task getCompletedTask(String userInput) throws NumberFormatException, IndexOutOfBoundsException {
        String replacedInput = userInput.trim().toLowerCase().replace("done", "");
        int taskNumber = Integer.parseInt(replacedInput.trim());
        return tasks.get(taskNumber-1);
    }

    public static void addToList(String userInput, String taskType) {
        String replacedInput = userInput.replaceFirst("(?i)" + taskType, "").trim();
        try {
            switch (taskType) {
            case "todo":
                addToDoToList(replacedInput);
                break;
            case "deadline":
                addDeadlineToList(replacedInput);
                break;
            case "event":
                addEventToList(replacedInput);
                break;
            }
            writeToFile();
        } catch (StringIndexOutOfBoundsException e) {
            printSIOOBEMessage();
        } catch (DukeException e) {
            printDEMessage(taskType);
        } catch (IOException e) {
            printIOEMessage();
            System.exit(1);
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

    public static void printAddedTask() {
        printDivider();
        System.out.println("\t" + OKAY_EMOJI + " Understood, sir. I've added this task: ");
        System.out.println("\t\t" + tasks.get(listIndex));
        System.out.printf("\tYou currently have %d task(s) in your list, sir.%n", tasks.size());
        printDivider();
    }

    public static void addToDoToList(String replacedInput) throws DukeException {
        if (replacedInput.isBlank()) {
            throw new DukeException();
        }
        tasks.add(new ToDo(replacedInput));
        printAddedTask();
        listIndex++;
    }

    public static void addDeadlineToList(String replacedInput) throws StringIndexOutOfBoundsException, DukeException {
        if (replacedInput.isBlank()) {
            throw new DukeException();
        }
        int byIndex = replacedInput.toLowerCase().indexOf("/by");
        String by = replacedInput.substring(byIndex + "/by".length());
        String description = replacedInput.substring(0, byIndex);
        if (description.isBlank()) {
            description = "Unspecified task";
        }
        tasks.add(new Deadline(description, by));
        printAddedTask();
        listIndex++;
    }

    public static void addEventToList(String replacedInput) throws StringIndexOutOfBoundsException, DukeException {
        if (replacedInput.isBlank()) {
            throw new DukeException();
        }
        int atIndex = replacedInput.toLowerCase().indexOf("/at");
        String at = replacedInput.substring(atIndex + "/at".length());
        String description = replacedInput.substring(0, atIndex);
        if (description.isBlank()) {
            description = "Unspecified event";
        }
        tasks.add(new Event(description, at));
        printAddedTask();
        listIndex++;
    }

    public static void displayList() {
        printDivider();
        if (tasks.isEmpty()) {
            System.out.println("\t" + HURRAY_EMOJI + " You have no tasks recorded, sir. Huzzah!");
        } else {
            System.out.println("\t" + LIST_EMOJI + " Here are your tasks, sir: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("\t%d.", i + 1);
                System.out.println(tasks.get(i));
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
        System.out.println("\t" + WAVE_EMOJI + " Greetings, sir. My name is Duke");
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
