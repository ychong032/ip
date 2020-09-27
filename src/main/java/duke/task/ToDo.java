package duke.task;

/**
 * Represents a to-do, which is a task to be done.
 */
public class ToDo extends Task {
    /**
     * Constructs a <code>ToDo</code> object.
     * @param description the description of the to-do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the to-do in a particular format for printing.
     * @return the to-do as a String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
