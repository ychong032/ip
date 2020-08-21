import java.util.Objects;
import java.util.Scanner;
public class Duke {
    static int taskCount = 0;

    public static void main(String[] args) {
        //Opening message
        printDivider();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDivider();

        addToList();

        //Closing message
        printDivider();
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider();
    }

    public static void printDivider() {
        System.out.println("\t-------------------------------------");
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
                int taskCompleted = Integer.parseInt(message.substring(5));
                list[taskCompleted-1].markAsDone();
                printDivider();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.printf("\t[%s] %s%n", list[taskCompleted-1].getStatusIcon(), list[taskCompleted-1].description);
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
        System.out.println("\tHere are the tasks in your list: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("\t%d.[%s] %s", i+1, list[i].getStatusIcon(), list[i].description);
            System.out.print('\n');
        }
        printDivider();
    }
}
