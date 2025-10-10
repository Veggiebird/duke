public class graceeTaskEvents extends graceeTaskList {
    private String from;
    private String to;

    public graceeTaskEvents(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[Events][" + getStatus() + "]" + description;
    }
}
