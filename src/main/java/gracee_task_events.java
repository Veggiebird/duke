public class gracee_task_events extends gracee_task_list {
    private String from;
    private String to;

    public gracee_task_events(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[Events][" + getStatus() + "]" + description;
    }
}
