public class gracee_task_deadline extends gracee_task_list{

    private String by;

    public gracee_task_deadline(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[Deadline] [" + getStatus() + "]" + description + " by " + by + "}";
    }
}
