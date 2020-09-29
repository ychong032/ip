package duke.task;

/**
 * Represents a task, which has a description and a status of completion. The task is
 * either completed or not, so the status of completion is either <code>true</code> or <code>false</code>.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a <code>Task</code> object.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task's status icon, which represents the status of completion. The number 1
     * is for completed tasks, while a zero is for uncompleted tasks.
     * @return the status icon of the task as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Sets the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the task in a particular format for printing.
     * @return the task with the specified description as a String.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}

