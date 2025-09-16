import java.util.List;
import java.util.ArrayList;

public class gracee_task_list{
    protected String description;
    protected boolean isDone;

    public gracee_task_list(String description) {
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
    public String toString(){
        return "[" + getStatus() + "] " + description;
    }
}
