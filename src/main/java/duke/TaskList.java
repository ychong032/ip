package duke;

import duke.exception.DukeEmptyDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * Represents a list of <code>Task</code> objects. In this list, different types of tasks can be added, deleted
 * or marked as completed.
 * @see Task
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a specified task as completed and returns it for printing.
     * @param fullCommand full user input.
     * @return the completed task.
     */
    public Task markTaskDone(String fullCommand) {
        Task taskDone = getCompletedTask(fullCommand);
        taskDone.markAsDone();
        return taskDone;
    }

    /**
     * Deletes a task from the list and returns it for printing.
     * @param fullCommand full user input.
     * @return the deleted task.
     */
    public Task deleteTask(String fullCommand) {
        Task deletedTask = getDeletedTask(fullCommand);
        tasks.remove(deletedTask);
        return deletedTask;
    }

    /**
     * Returns the task to be deleted from the task list. The task number specified in the user input is extracted and
     * used to get this task from the task list.
     * @param fullCommand full user input
     * @return the task to be deleted
     * @throws NumberFormatException if the task number in the user input is not an integer.
     * @throws IndexOutOfBoundsException if the task number in the user input is outside the range of existing task
     * numbers.
     */
    public Task getDeletedTask(String fullCommand) throws NumberFormatException, IndexOutOfBoundsException {
        // Remove the keyword so that only the task number is left.
        String replacedInput = fullCommand.toLowerCase().replace("delete", "");
        int taskDeleted = Integer.parseInt(replacedInput.trim());
        return tasks.get(taskDeleted - 1);
    }

    /**
     * Returns the task to be marked as completed. The task number is specified in the user input and is extracted and
     * used to get this task from the task list.
     * @param fullCommand full user input
     * @return the task to be marked as completed
     * @throws NumberFormatException if the task number in the user input is not an integer.
     * @throws IndexOutOfBoundsException if the task number in the user input is outside the range of existing task
     * numbers.
     */
    public Task getCompletedTask(String fullCommand) throws NumberFormatException, IndexOutOfBoundsException {
        String replacedInput = fullCommand.toLowerCase().replace("done", "");
        int taskNumber = Integer.parseInt(replacedInput.trim());
        return tasks.get(taskNumber - 1);
    }

    /**
     * Adds a task to the task list. The type of task depends on the keyword detected.
     * @param fullCommand full user input.
     * @param taskType the type of task.
     * @throws DukeEmptyDescriptionException if the user does not input a description for the task.
     */
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

    /**
     * Adds a <code>ToDo</code> to the task list.
     * @param replacedInput user input without the keyword.
     * @throws DukeEmptyDescriptionException if the user does not input a description for the to-do.
     * @see ToDo
     */
    public void addToDoToList(String replacedInput) throws DukeEmptyDescriptionException {
        if (replacedInput.isBlank()) {
            throw new DukeEmptyDescriptionException();
        }
        tasks.add(new ToDo(replacedInput));
        Duke.listIndex++;
    }

    /**
     * Adds a <code>Deadline</code> to the task list.
     * @param replacedInput user input without the keyword.
     * @throws StringIndexOutOfBoundsException if the user does not input "/by" after the description of the deadline.
     * @throws DukeEmptyDescriptionException if the user does not input a description for the deadline.
     * @see Deadline
     */
    public void addDeadlineToList(String replacedInput) throws StringIndexOutOfBoundsException,
            DukeEmptyDescriptionException {
        if (replacedInput.isBlank()) {
            throw new DukeEmptyDescriptionException();
        }
        int byIndex = replacedInput.indexOf("/by");
        // Extracts the date by which the task is to be completed.
        String by = replacedInput.substring(byIndex + "/by".length()).trim();
        String description = replacedInput.substring(0, byIndex).trim();
        if (description.isBlank()) {
            description = "Unspecified task";
        }
        tasks.add(new Deadline(description, by));
        Duke.listIndex++;
    }

    /**
     * Adds an <code>Event</code> to the task list.
     * @param replacedInput user input without the keyword.
     * @throws StringIndexOutOfBoundsException if the user does not input "/at" after the description of the event
     * @throws DukeEmptyDescriptionException if the user does not input a description for the event.
     * @see Event
     */
    public void addEventToList(String replacedInput) throws StringIndexOutOfBoundsException,
            DukeEmptyDescriptionException {
        if (replacedInput.isBlank()) {
            throw new DukeEmptyDescriptionException();
        }
        int atIndex = replacedInput.indexOf("/at");
        // Extracts the date of the event.
        String at = replacedInput.substring(atIndex + "/at".length()).trim();
        String description = replacedInput.substring(0, atIndex).trim();
        if (description.isBlank()) {
            description = "Unspecified event";
        }
        tasks.add(new Event(description, at));
        Duke.listIndex++;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}