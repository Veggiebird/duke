package gracee.tasks;

public class graceeTaskTodo extends graceeTaskDetails {

    public graceeTaskTodo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "Todo | " + getStatus() + "|" + description;
    }
}
