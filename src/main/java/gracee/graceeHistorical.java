package gracee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class graceeHistorical {
    private final ArrayList<String> items = new ArrayList<>(0);

    public void add(String entry){
        if(entry != null && !entry.isEmpty()){
            items.add(entry);
        }
    }

    public int size(){
        return items.size();
    }

    public String get(int index){
        return items.get(index);
    }

    public List<String> list(){
        return Collections.unmodifiableList(items);
    }
}