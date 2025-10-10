public class graceeTaskTodo extends graceeTaskList {

    public graceeTaskTodo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[Todo][" + getStatus() + "]" + description;
    }
}
