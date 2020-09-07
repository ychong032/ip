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
        Task taskDone = getCompletedTask(taskList, userInput);
        taskDone.markAsDone();
        printDivider();
        String clapEmoji = new String(Character.toChars(0x1f44f));
        System.out.println("\t" + clapEmoji + " Very good, sir. Excellent work accomplishing your task:");
        System.out.print('\t');
        System.out.println(taskDone);
        printDivider();
    }

    public static Task getCompletedTask(Task[] taskList, String userInput) {
        String replacedInput = userInput.trim().toLowerCase().replace("done", "");
        int taskNumber = Integer.parseInt(replacedInput.trim().substring(0, 1));
        return taskList[taskNumber-1];
    }

    public static void addToList(Task[] taskList, String userInput, String taskType) {
        String replacedInput = userInput.replaceFirst("(?i)" + taskType, "").trim();
        if (isValidInput(replacedInput, taskType)) {
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
        }
    }

    public static void printAddedTask(Task[] taskList) {
        printDivider();
        String okayEmoji = new String(Character.toChars(0x1f44c));
        System.out.println("\t" + okayEmoji + " Understood, sir. I've added this task: ");
        System.out.println("\t\t" + taskList[listIndex]);
        System.out.printf("\tYou currently have %d task(s) in your list, sir.%n", taskCount);
        printDivider();
    }

    public static void addToDoToList(Task[] taskList, String replacedInput) {
        taskList[listIndex] = new ToDo(replacedInput);
        taskCount++;
        printAddedTask(taskList);
        listIndex++;
    }

    public static void addDeadlineToList(Task[] taskList, String replacedInput) {
        int byIndex = replacedInput.toLowerCase().indexOf("/by");
        String by = replacedInput.substring(byIndex + 3);
        String description = replacedInput.substring(0, byIndex);
        taskList[listIndex] = new Deadline(description, by);
        taskCount++;
        printAddedTask(taskList);
        listIndex++;
    }

    public static void addEventToList(Task[] taskList, String replacedInput) {
        int atIndex = replacedInput.toLowerCase().indexOf("/at");
        String at = replacedInput.substring(atIndex + 3);
        String description = replacedInput.substring(0, atIndex);
        taskList[listIndex] = new Event(description, at);
        taskCount++;
        printAddedTask(taskList);
        listIndex++;
    }

    public static boolean isValidInput(String replacedInput, String taskType) {
        if (replacedInput.isBlank()) {
            printDivider();
            String sadEmoji = new String(Character.toChars(0x1f61e));
            System.out.println("\t" + sadEmoji + " Apologies, sir. The description of your " + taskType
                    + " cannot be empty.");
            printDivider();
            return false;
        }
        return true;
    }

    public static void displayList(Task[] taskList) {
        printDivider();
        if (taskList[0] == null) {
            String hurrayEmoji = new String(Character.toChars(0x1f64c));
            System.out.println("\t" + hurrayEmoji + " You have no tasks recorded, sir. Huzzah!");
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
        String smileEmoji = new String(Character.toChars(0x1f600));
        System.out.println("\t" + smileEmoji + " Always a pleasure, sir. Do come back soon.");
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
        String waveEmoji = new String(Character.toChars(0x1f64b));
        System.out.println("\t" + waveEmoji + " Greetings, sir. My name is Duke");
        System.out.println("\tHow may I assist you today, sir?");
        printDivider();
    }

    public static void printDivider() {
        System.out.println("\t+------------------------------------------------------------------+");
    }

    public static void printErrorMessage() {
        printDivider();
        String confusedEmoji = new String(Character.toChars(0x1f615));
        System.out.println("\t" + confusedEmoji + " I'm afraid I don't understand, sir. Please preface " +
                "your tasks " + "\n\twith 'todo', 'deadline', or 'event' to add them to your list.");
        printDivider();
    }
}
