import java.util.Scanner;
public class Duke {
    static int taskCount = 0;

    public static void main(String[] args) {
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
        String message = sc.nextLine();
        while (!message.equals("bye")) {
            printDivider();
            System.out.printf("\t%s%n", message);
            printDivider();
            message = sc.nextLine();
        }
    }

    public static void addToList() {
        Task[] list =  new Task[100];
        int listIndex = 0;
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while (!message.equals("bye")) {
            if (message.equals("list")) {
                displayList(list);
                message = sc.nextLine();
                continue;
            }
            if (message.contains("done")) {
                int taskNumber = Integer.parseInt(message.substring(5));
                Task taskDone = list[taskNumber-1];
                taskDone.markAsDone();
                printDivider();
                System.out.println("\tVery good, sir. Excellent work accomplishing your task:");
                System.out.printf("\t[%s] %s%n", taskDone.getStatusIcon(), taskDone.description);
                printDivider();
                message = sc.nextLine();
                continue;
            }
            list[listIndex] = new Task(message);
            printDivider();
            System.out.printf("\tadded: %s%n", list[listIndex].description);
            printDivider();
            listIndex++;
            taskCount++;
            message = sc.nextLine();
        }
    }

    public static void displayList(Task[] list) {
        printDivider();
        System.out.println("\tHere are your tasks for this evening, sir: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("\t%d.[%s] %s", i+1, list[i].getStatusIcon(), list[i].description);
            System.out.print('\n');
        }
        printDivider();
    }
}
