package duke;

import duke.aesthetics.Emojis;
import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


    public void showTaskDeleted(Task deletedTask, TaskList tasks) {
        printDivider();
        System.out.println("\tUnderstood, I've deleted this task: ");
        System.out.println("\t\t" + deletedTask);
        System.out.printf("\tYou now have %d task(s) in your list, sir.\n", tasks.getTasks().size());
        printDivider();
    }

    public void showMissingTaskNumberError() {
        printDivider();
        System.out.println("\tThat task number is not in your list, sir.");
        printDivider();
    }

    public void showFileNotFoundError() {
        printDivider();
        System.out.println("\tduke.txt does not exist in /data folder!");
        System.out.println("\tTerminating Duke...");
        printDivider();
    }

    public void showTaskNumberError() {
        printDivider();
        System.out.println("\t" + Emojis.SAD_EMOJI + " I don't recognise that task number, sir. Please enter a valid" +
                "\n\ttask number after the keyword.");
        printDivider();
    }

    public void showTaskDone(Task taskDone) {
        printDivider();
        System.out.println("\t" + Emojis.CLAP_EMOJI + " Very good, sir. Excellent work accomplishing your task:");
        System.out.println("\t\t" + taskDone);
        printDivider();
    }

    public void showBlankDescriptionError() {
        printDivider();
        System.out.println("\t" + Emojis.SAD_EMOJI + " Apologies, sir. The description of your task cannot be empty.");
        printDivider();
    }

    public void showInputFormatError() {
        printDivider();
        System.out.println("\t" + Emojis.SAD_EMOJI + " Please ensure that your deadline or event is followed by"
                + "\n\t'/by' or '/at', respectively.");
        printDivider();
    }

    public void showAddedTask(TaskList tasks) {
        printDivider();
        System.out.println("\t" + Emojis.OKAY_EMOJI + " Understood, sir. I've added this task: ");
        System.out.println("\t\t" + tasks.getTasks().get(tasks.getTasks().size() - 1));
        System.out.printf("\tYou currently have %d task(s) in your list, sir.%n", tasks.getTasks().size());
        printDivider();
    }

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

    public void printFarewell() {
        printDivider();
        System.out.println("\t" + Emojis.SMILE_EMOJI + " Always a pleasure, sir. Do come back soon.");
        printDivider();
    }

    public void printLogo() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
    }

    public void printGreeting() {
        printDivider();
        System.out.println("\t" + Emojis.WAVE_EMOJI + " Greetings, sir. My name is Duke");
        System.out.println("\tHow may I assist you today, sir?");
        printDivider();
    }

    public void printDivider() {
        System.out.println("\t+------------------------------------------------------------------+");
    }

    public void showUnknownInputError() {
        printDivider();
        System.out.println("\t" + Emojis.CONFUSED_EMOJI + " I'm afraid I don't understand, sir. Please preface " +
                "your tasks " + "\n\twith 'todo', 'deadline', or 'event' to add them to your list.");
        printDivider();
    }

    public void showSearchResults(ArrayList<Task> copyOfTasks, ArrayList<Task> tasks) {
        printDivider();
        if (copyOfTasks.isEmpty()) {
            System.out.println("\tI could not find any matching tasks, sir. Please give me an existing" +
                    "\n\tphrase to search for in your list.");
        } else {
            System.out.println("\tHere are the matching tasks in your list, sir: ");
            for (Task task : copyOfTasks) {
                if (tasks.contains(task)) {
                    System.out.printf("\t%d.", tasks.indexOf(task) + 1);
                    System.out.println(task);
                }
            }
        }
        printDivider();
    }
}