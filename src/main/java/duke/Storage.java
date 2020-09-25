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

public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        tasks = new ArrayList<>();
    }

    public File createDukeFile() throws IOException {
        File dukeFile = new File(filePath);
        if (!dukeFile.exists()) {
            new File(dukeFile.getParent()).mkdirs();
            dukeFile.createNewFile();
        }
        return dukeFile;
    }

    public void readDukeFile(File dukeFile) throws FileNotFoundException {
        Scanner sc = new Scanner(dukeFile);
        String taskAsString;
        while (sc.hasNext()) {
            taskAsString = sc.nextLine();
            if (taskAsString.contains("[T]")) {
                readToDo(taskAsString);
            } else if (taskAsString.contains("[D]")) {
                readDeadline(taskAsString);
            } else {
                readEvent(taskAsString);
            }
            if (taskAsString.contains("\u2713")) {
                tasks.get(Duke.listIndex).markAsDone();
            }
            Duke.listIndex++;
        }
    }

    public void readEvent(String taskAsString) {
        String description;
        int atStartIndex = taskAsString.indexOf(" (at:") + " (at:".length() + 1;
        int atEndIndex = taskAsString.lastIndexOf(")");
        String at = taskAsString.substring(atStartIndex, atEndIndex);
        description = taskAsString.substring(taskAsString.indexOf(" ") + 1, taskAsString.indexOf(" (at:"));
        tasks.add(new Event(description, at));
    }

    public void readDeadline(String taskAsString) {
        String description;
        int deadlineStartIndex = taskAsString.indexOf(" (by:") + " (by:".length() + 1;
        int deadlineEndIndex = taskAsString.lastIndexOf(")");
        String deadline = taskAsString.substring(deadlineStartIndex, deadlineEndIndex);
        description = taskAsString.substring(taskAsString.indexOf(" ") + 1, taskAsString.indexOf(" (by:"));
        tasks.add(new Deadline(description, deadline));
    }

    public void readToDo(String taskAsString) {
        String description = taskAsString.substring(taskAsString.indexOf(" ") + 1);
        tasks.add(new ToDo(description));
    }

    public void writeToFile() throws IOException {
        FileWriter dukeFile = new FileWriter(filePath);
        for (Task task : tasks) {
            dukeFile.write(task.toString() + "\n");
        }
        dukeFile.close();
    }

    public ArrayList<Task> load() throws IOException {
        File dukeFile = createDukeFile();
        readDukeFile(dukeFile);
        return tasks;
    }
}