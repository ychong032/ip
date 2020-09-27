package duke.task;

/**
 * Represents a deadline, which is a task to be completed by a specified date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a <code>Deadline</code> object.
     * @param description the description of the deadline.
     * @param by the date by which the deadline is to be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline in a particular format for printing.
     * @return the deadline with the specified date as a <code>String</code>.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
