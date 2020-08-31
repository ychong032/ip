import java.util.Scanner;
public class Duke {
    private static final int MAX_TASK_LENGTH = 100;
    private static int taskCount = 0;

    public static void main(String[] args) {
        //Print logo and opening message
        printLogo();
        printDivider();
        printGreeting();
        printDivider();

        addToList();

        //Print closing message
        printDivider();
        printFarewell();
        printDivider();
    }

    public static void printFarewell() {
        System.out.println("\tAlways a pleasure, sir. Do come back soon.");
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
        System.out.println("\tGreetings, sir. My name is Duke");
        System.out.println("\tHow may I assist you today, sir?");
    }

    public static void printDivider() {
        System.out.println("\t+--------------------------------------------------------+");
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

    public static void addToList() {
        Task[] taskList =  new Task[MAX_TASK_LENGTH];
        int listIndex = 0;
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.trim().toLowerCase().equals("bye")) {
            if (userInput.trim().toLowerCase().equals("list")) {
                displayList(taskList);
                userInput = sc.nextLine();
                continue;
            }
            if (userInput.trim().toLowerCase().contains("done")) {
                int taskNumber = Integer.parseInt(userInput.substring(5)); //Magic number!
                Task taskDone = taskList[taskNumber-1];
                taskDone.markAsDone();
                printDivider();
                System.out.println("\tVery good, sir. Excellent work accomplishing your task:");
                System.out.printf("\t[%s] %s%n", taskDone.getStatusIcon(), taskDone.description);
                printDivider();
                userInput = sc.nextLine();
                continue;
            }
            taskList[listIndex] = new Task(userInput);
            printDivider();
            System.out.printf("\tadded: %s%n", taskList[listIndex].description);
            printDivider();
            listIndex++;
            taskCount++;
            userInput = sc.nextLine();
        }
    }

    public static void displayList(Task[] taskList) {
        printDivider();
        if (taskList[0] == null) {
            System.out.println("\tYou have no tasks recorded this evening, sir.");
        } else {
            System.out.println("\tHere are your tasks for this evening, sir: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("\t%d.[%s] %s", i + 1, taskList[i].getStatusIcon(), taskList[i].description);
                System.out.print('\n');
            }
        }
        printDivider();
    }
}
