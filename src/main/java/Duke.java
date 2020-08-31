import java.util.Scanner;
public class Duke {
    private static final int MAX_TASK_LENGTH = 100;
    private static int taskCount = 0;

    public static void main(String[] args) {
        //Print logo and opening message
        printLogo();
        printGreeting();

        //Receive user input and execute corresponding functions
        getUserInput();

        //Print closing message
        printFarewell();
    }

    public static void loopEcho() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            printDivider();
            System.out.printf("\t%s%n", userInput);
            printDivider();
            userInput = sc.nextLine();
        }
    }

    public static void getUserInput() {
        Task[] taskList = new Task[MAX_TASK_LENGTH];
        int listIndex = 0;
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.trim().toLowerCase().equals("bye")) {
            if (userInput.trim().toLowerCase().equals("list")) {
                displayList(taskList);
            } else if (userInput.trim().toLowerCase().contains("done")) {
                markTaskDone(taskList, userInput);
            } else if (userInput.trim().toLowerCase().contains("todo")) {
                listIndex = addToList(taskList, listIndex, userInput, "todo");
            } else if (userInput.trim().toLowerCase().contains("deadline")) {
                listIndex = addToList(taskList, listIndex, userInput, "deadline");
            } else if (userInput.trim().toLowerCase().contains("event")) {
                listIndex = addToList(taskList, listIndex, userInput, "event");
            } else {
                printErrorMessage();
            }
            userInput = sc.nextLine();
        }
    }

    public static void markTaskDone(Task[] taskList, String userInput) {
        Task taskDone = getCompletedTask(taskList, userInput);
        taskDone.markAsDone();
        printDivider();
        System.out.println("\tVery good, sir. Excellent work accomplishing your task:");
        System.out.print('\t');
        System.out.println(taskDone);
        printDivider();
    }

    public static Task getCompletedTask(Task[] taskList, String userInput) {
        String replacedInput = userInput.trim().toLowerCase().replace("done", "");
        int taskNumber = Integer.parseInt(replacedInput.trim().substring(0, 1));
        return taskList[taskNumber-1];
    }

    public static int addToList(Task[] taskList, int listIndex, String userInput, String taskType) {
        String replacedInput;
        String description;
        switch (taskType) {
        case "todo":
            replacedInput = userInput.replaceFirst("(?i)todo", "").trim();
            description = replacedInput;
            taskList[listIndex] = new ToDo(description);
            break;
        case "deadline":
            replacedInput = userInput.replaceFirst("(?i)deadline", "").trim();
            int byIndex = replacedInput.toLowerCase().indexOf("/by");
            String by = replacedInput.substring(byIndex + 3);
            description = replacedInput.substring(0, byIndex);
            taskList[listIndex] = new Deadline(description, by);
            break;
        case "event":
            replacedInput = userInput.replaceFirst("(?i)event", "").trim();
            int atIndex = replacedInput.indexOf("/at");
            String at = replacedInput.substring(atIndex + 3);
            description = replacedInput.substring(0, atIndex);
            taskList[listIndex] = new Event(description, at);
            break;
        }
        printDivider();
        System.out.println("\tUnderstood, sir. I've added this task: ");
        System.out.println("\t\t" + taskList[listIndex]);
        listIndex++;
        taskCount++;
        System.out.printf("\tYou currently have %d task(s) in your list, sir.%n", taskCount);
        printDivider();
        return listIndex;
    }

    public static void displayList(Task[] taskList) {
        printDivider();
        if (taskList[0] == null) {
            System.out.println("\tYou have no tasks recorded, sir. Huzzah!");
        } else {
            System.out.println("\tHere are your tasks, sir: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("\t%d.", i + 1);
                System.out.println(taskList[i]);
            }
        }
        printDivider();
    }

    public static void printFarewell() {
        printDivider();
        System.out.println("\tAlways a pleasure, sir. Do come back soon.");
        printDivider();
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printGreeting() {
        printDivider();
        System.out.println("\tGreetings, sir. My name is Duke");
        System.out.println("\tHow may I assist you today, sir?");
        printDivider();
    }

    public static void printDivider() {
        System.out.println("\t+------------------------------------------------------------------+");
    }

    public static void printErrorMessage() {
        printDivider();
        System.out.println("\tI'm afraid I don't understand, sir. Please preface your tasks " +
                "\n\twith 'todo', 'deadline', or 'event' to add them to your list.");
        printDivider();
    }
}
