package gracee.tasks;

/**
 * Handle to do description
 */

public class graceeTaskTodo extends graceeTaskDetails {

    /**
     * Create new todo
     * @param description todo description
     */
    public graceeTaskTodo(String description){
        super(description);
    }

    /**
     * Display formatted string for todo description:
     * Todo | status | description
     * @return Todo string
     */

    @Override
    public String toString(){
        return "Todo | " + getStatus() + " | " + description;
    }
}
