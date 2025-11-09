package gracee.tasks;

/**
 * Handling abstraction for all task types
 * Each task has details of description, status (Pending (0) / Done (1)).
 */
public abstract class graceeTaskDetails {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description Description of task
     */
    public graceeTaskDetails(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return Task status.
     * @return status of task (1 is done, 0 is pending)
     */

    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Mark task as done (1)
     */

    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Mark task as pending (0)
     */

    public void markAsPending(){
        this.isDone = false;
    }

    @Override
    public abstract String toString();
}
