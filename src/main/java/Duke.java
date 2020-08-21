import java.util.Scanner;
public class Duke {

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
        String[] list =  new String[100];
        int listIndex = 0;
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while (!message.equals("bye")) {
            if (message.equals("list")) {
                displayList(list);
                message = sc.nextLine();
                continue;
            }
            list[listIndex] = String.format("%d. %s", listIndex + 1, message);
            printDivider();
            System.out.printf("\tadded: %s%n", message);
            printDivider();
            listIndex++;
            message = sc.nextLine();
        }
    }

    public static void displayList(String[] list) {
        printDivider();
        for (String item : list) {
            if (item != null) {
                System.out.println("\t" + item);
            }
        }
        printDivider();
    }
}
