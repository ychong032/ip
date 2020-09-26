package duke;

import duke.exception.DukeEmptyDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task markTaskDone(String fullCommand) {
        Task taskDone = getCompletedTask(fullCommand);
        taskDone.markAsDone();
        return taskDone;
    }

    public Task deleteTask(String fullCommand) {
        Task deletedTask = getDeletedTask(fullCommand);
        tasks.remove(deletedTask);
        return deletedTask;
    }

    public Task getDeletedTask(String fullCommand) throws NumberFormatException, IndexOutOfBoundsException {
        String replacedInput = fullCommand.toLowerCase().replace("delete", "");
        int taskDeleted = Integer.parseInt(replacedInput.trim());
        return tasks.get(taskDeleted - 1);
    }

    public Task getCompletedTask(String fullCommand) throws NumberFormatException, IndexOutOfBoundsException {
        String replacedInput = fullCommand.toLowerCase().replace("done", "");
        int taskNumber = Integer.parseInt(replacedInput.trim());
        return tasks.get(taskNumber - 1);
    }

    public void addToList(String fullCommand, String taskType) throws DukeEmptyDescriptionException {
        String replacedInput = fullCommand.replaceFirst("(?i)" + taskType, "").trim();
        switch (taskType) {
        case "todo":
            addToDoToList(replacedInput);
            break;
        case "deadline":
            addDeadlineToList(replacedInput);
            break;
        case "event":
            addEventToList(replacedInput);
            break;
        }
    }

    public void addToDoToList(String replacedInput) throws DukeEmptyDescriptionException {
        if (replacedInput.isBlank()) {
            throw new DukeEmptyDescriptionException();
        }
        tasks.add(new ToDo(replacedInput));
        Duke.listIndex++;
    }

    public void addDeadlineToList(String replacedInput) throws StringIndexOutOfBoundsException,
            DukeEmptyDescriptionException {
        if (replacedInput.isBlank()) {
            throw new DukeEmptyDescriptionException();
        }
        int byIndex = replacedInput.indexOf("/by");
        String by = replacedInput.substring(byIndex + "/by".length()).trim();
        String description = replacedInput.substring(0, byIndex).trim();
        if (description.isBlank()) {
            description = "Unspecified task";
        }
        tasks.add(new Deadline(description, by));
        Duke.listIndex++;
    }

    public void addEventToList(String replacedInput) throws StringIndexOutOfBoundsException,
            DukeEmptyDescriptionException {
        if (replacedInput.isBlank()) {
            throw new DukeEmptyDescriptionException();
        }
        int atIndex = replacedInput.indexOf("/at");
        String at = replacedInput.substring(atIndex + "/at".length()).trim();
        String description = replacedInput.substring(0, atIndex).trim();
        if (description.isBlank()) {
            description = "Unspecified event";
        }
        tasks.add(new Event(description, at));
        Duke.listIndex++;
    }

    public ArrayList<Task> searchTasks(String fullCommand) {
        String replacedInput = fullCommand.replaceFirst("(?i)" + "find", "").trim();
        ArrayList<Task> copyOfTasks = new ArrayList<>();
        if (!replacedInput.isBlank()) {
            for (Task task : tasks) {
                if (task.toString().toLowerCase().contains(replacedInput.toLowerCase())) {
                    copyOfTasks.add(task);
                }
            }
        }
        return copyOfTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}