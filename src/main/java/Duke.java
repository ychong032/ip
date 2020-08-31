import java.util.Scanner;
public class Duke {
    static int taskCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //Opening message
        printDivider();
        System.out.println("\tGreetings, sir. My name is Duke");
        System.out.println("\tHow may I assist you today, sir?");
        printDivider();

        addToList();

        //Closing message
        printDivider();
        System.out.println("\tAlways a pleasure, sir. Do come back soon.");
        printDivider();
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
        Task[] taskList =  new Task[100];
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
                int taskNumber = Integer.parseInt(userInput.substring(5));
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
        System.out.println("\tHere are your tasks for this evening, sir: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("\t%d.[%s] %s", i+1, taskList[i].getStatusIcon(), taskList[i].description);
            System.out.print('\n');
        }
        printDivider();
    }
}
