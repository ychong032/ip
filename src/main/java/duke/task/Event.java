package duke.task;

/**
 * Represents an event, which is a task that will occur at a specified date.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs an <code>Event</code> object.
     * @param description the description of the event.
     * @param at the date of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the event in a particular format for printing.
     * @return the event with the specified date as a <code>String</code>.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
