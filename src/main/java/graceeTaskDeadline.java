public class graceeTaskDeadline extends graceeTaskList {

    private String by;

    public graceeTaskDeadline(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[Deadline] [" + getStatus() + "]" + description + " by " + by + "}";
    }
}
