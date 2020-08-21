import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        printDivider();
        System.out.printf("\tHello! I'm Duke\n");
        System.out.printf("\tWhat can I do for you?\n");
        printDivider();
        loopEcho();
    }

    public static void printDivider() {
        System.out.printf("\t-------------------------------------\n");
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
        printDivider();
        System.out.printf("\tBye. Hope to see you again soon!\n");
        printDivider();
    }
}
