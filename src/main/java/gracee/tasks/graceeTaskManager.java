package gracee.tasks;
import gracee.storage.graceeStorage;

import java.util.ArrayList;
import java.util.List;

public class graceeTaskManager {
    private final ArrayList<graceeTaskDetails> tasks;
    private final graceeStorage storage;

    public graceeTaskManager(List<graceeTaskDetails> loaded, graceeStorage storage){
        this.tasks = new ArrayList<>(loaded);
        this.storage = storage;
    }

    public int size(){
        return tasks.size();
    }

    public graceeTaskDetails get(int index){
        return tasks.get(index);
    }

    public void add(graceeTaskDetails task){
        tasks.add(task);
        save();
    }

    public void toggleStatus(int index){
        graceeTaskDetails task = tasks.get(index);
        if("1".equals(task.getStatus())){
            task.markAsPending();
        }else{
            task.markAsDone();
        }

        save();
    }

    public graceeTaskDetails remove(int index){
        graceeTaskDetails removed = tasks.remove(index);
        save();
        return removed;
    }

    private void save(){
        storage.save(tasks);
    }
}
