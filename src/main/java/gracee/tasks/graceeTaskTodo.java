package gracee.tasks;

/**
 * Handle to do description
 */

public class graceeTaskTodo extends graceeTaskDetails {

    /**
     * Create new todo
     * @param description
     */
    public graceeTaskTodo(String description){
        super(description);
    }

    /**
     * Display string for todo description
     * @return
     */

    @Override
    public String toString(){
        return "Todo | " + getStatus() + " | " + description;
    }
}
