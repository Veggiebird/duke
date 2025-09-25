public class gracee_task_todo extends gracee_task_list{

    public gracee_task_todo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[Todo][" + getStatus() + "]" + description;
    }
}
