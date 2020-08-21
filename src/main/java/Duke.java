import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        //Opening message
        printDivider();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDivider();

        loopEcho();

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
}
