public abstract class graceeTaskDetails {
    protected String description;
    protected boolean isDone;

    public graceeTaskDetails(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "1" : "0");
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
