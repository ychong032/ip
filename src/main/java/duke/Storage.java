package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class for performing read/write operations on a file that is used to store a list of tasks.
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        tasks = new ArrayList<>();
    }

    /**
     * Creates a <code>File</code> object using the file path of the storage file and returns it. If the storage file
     * does not exist, a new, empty file is created at the same file path.
     * @return a <code>File</code> object created from the file path of the storage file.
     * @throws IOException if the method fails to create a <code>File</code> object.
     */
    public File createDukeFile() throws IOException {
        File dukeFile = new File(filePath);
        if (!dukeFile.exists()) {
            new File(dukeFile.getParent()).mkdirs();
            dukeFile.createNewFile();
        }
        return dukeFile;
    }

    /**
     * Reads different tasks from the <code>File</code> instance of the storage file and adds them into the task list.
     * The type of a task is identified by its symbol.
     * @param dukeFile the <code>File</code> instance of the storage file.
     * @throws FileNotFoundException if <code>dukeFile</code> cannot be found.
     */
    public void readDukeFile(File dukeFile) throws FileNotFoundException {
        Scanner sc = new Scanner(dukeFile);
        String taskAsString;
        while (sc.hasNext()) {
            // Examines each line as each task occupies exactly one line in the storage file.
            taskAsString = sc.nextLine();
            if (taskAsString.contains("[T]")) {
                readToDo(taskAsString);
            } else if (taskAsString.contains("[D]")) {
                readDeadline(taskAsString);
            } else {
                readEvent(taskAsString);
            }
            // Marks the added task as completed if a tick symbol is found.
            if (taskAsString.contains("\u2713")) {
                tasks.get(Duke.listIndex).markAsDone();
            }
            Duke.listIndex++;
        }
    }

    /**
     * Adds an <code>Event</code> to the task list.
     * @param taskAsString the <code>String</code> representation of the task in the storage file.
     * @see Event
     */
    public void readEvent(String taskAsString) {
        String description;
        // Gets the start index of the date in the event.
        int atStartIndex = taskAsString.indexOf(" (at:") + " (at:".length() + 1;
        // Gets the end index of the date in the event.
        int atEndIndex = taskAsString.lastIndexOf(")");
        String at = taskAsString.substring(atStartIndex, atEndIndex);
        description = taskAsString.substring(taskAsString.indexOf(" ") + 1, taskAsString.indexOf(" (at:"));
        tasks.add(new Event(description, at));
    }

    /**
     * Adds a <code>Deadline</code> to the task list.
     * @param taskAsString the <code>String</code> representation of the task in the storage file.
     * @see Deadline
     */
    public void readDeadline(String taskAsString) {
        String description;
        // Gets the start index of the date by which the deadline is to be completed.
        int deadlineStartIndex = taskAsString.indexOf(" (by:") + " (by:".length() + 1;
        // Gets the end index of the date by which the deadline is to be completed.
        int deadlineEndIndex = taskAsString.lastIndexOf(")");
        String deadline = taskAsString.substring(deadlineStartIndex, deadlineEndIndex);
        description = taskAsString.substring(taskAsString.indexOf(" ") + 1, taskAsString.indexOf(" (by:"));
        tasks.add(new Deadline(description, deadline));
    }

    /**
     * Adds a <code>ToDo</code> to the task list.
     * @param taskAsString the <code>String</code> representation of the task in the storage file.
     */
    public void readToDo(String taskAsString) {
        String description = taskAsString.substring(taskAsString.indexOf(" ") + 1);
        tasks.add(new ToDo(description));
    }

    /**
     * Writes the task list to the storage file.
     * @throws IOException if the <code>FileWriter</code> instance of the storage file cannot be created.
     */
    public void writeToFile() throws IOException {
        FileWriter dukeFile = new FileWriter(filePath);
        for (Task task : tasks) {
            dukeFile.write(task.toString() + "\n");
        }
        dukeFile.close();
    }

    /**
     * Reads the tasks from a <code>File</code> instance of the storage file, adds these tasks into the task list,
     * and returns the task list.
     * @return the task list read from the storage file.
     * @throws IOException if the <code>File</code> or <code>FileWriter</code> instance of the storage file cannot be
     * created.
     */
    public ArrayList<Task> load() throws IOException {
        File dukeFile = createDukeFile();
        readDukeFile(dukeFile);
        return tasks;
    }
}