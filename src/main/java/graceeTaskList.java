public abstract class graceeTaskList {
    protected String description;
    protected boolean isDone;

    public graceeTaskList(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "Done" : "Pending");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsPending(){
        this.isDone = false;
    }

    @Override
    public abstract String toString();
}
