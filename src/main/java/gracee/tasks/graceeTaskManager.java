package gracee.tasks;
import gracee.storage.graceeStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle task content
 */
public class graceeTaskManager {
    private final ArrayList<graceeTaskDetails> tasks;
    private final graceeStorage storage;

    /**
     * Load task from storage file
     * @param loaded
     * @param storage
     */
    public graceeTaskManager(List<graceeTaskDetails> loaded, graceeStorage storage){
        this.tasks = new ArrayList<>(loaded);
        this.storage = storage;
    }

    /**
     * Return task total count
     * @return task size
     */

    public int size(){

        return tasks.size();
    }

    /**
     * Return task with specific index
     * @param index specified index
     * @return Indexed task
     */

    public graceeTaskDetails get(int index){
        return tasks.get(index);
    }

    /**
     * Add Task
     * @param task add task
     */

    public void add(graceeTaskDetails task){
        tasks.add(task);
        save();
    }

    /**
     * Remove task with specific index.
     * @param index specified index
     * @return remove specific task
     */

    public graceeTaskDetails remove(int index){
        graceeTaskDetails removed = tasks.remove(index);
        save();
        return removed;
    }

    /**
     * Save task to storage file.
     */

    private void save(){
        storage.save(tasks);
    }
}
