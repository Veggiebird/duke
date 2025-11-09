package gracee;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Capture, display user historical activities for chatbot
 * Contains maximum history of 20 lines
 *
 * Each entry add with timestamp and will be auto save
 */
public class graceeHistorical {
    private static final int MAX_LINES = 20; //only store up to 20 historical action
    private static final Path FILE = Paths.get("data", "history.txt");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private final Deque<String> historyCap = new ArrayDeque<>(MAX_LINES);

    private final ArrayList<String> items = new ArrayList<>(0);

    public graceeHistorical() {
        load();
    }

    /**
     * Add new historical entry
     * @param entry
     */

    public void add(String entry){

        if(entry == null || entry.isEmpty()){
            return;
        }

        String timestamp = timeFormat.format(LocalDateTime.now()) + " | " + entry;

        if(historyCap.size() >= MAX_LINES){
            historyCap.removeFirst();
        }

        historyCap.addLast(timestamp);
        save();
    }

    /**
     * Return number of historical entries count
     * @return
     */

    public int size(){
        return historyCap.size();
    }

    /**
     * Get history with specific index
     * @param index
     * @return
     */

    public String get(int index){
        return new ArrayList<>(historyCap).get(index);
    }

    public List<String> list(){
        return Collections.unmodifiableList(new ArrayList<>(historyCap));
    }

    /**
     * Load history from storage file
     */

    private void load(){
        try{
            if (Files.exists(FILE)){
                List<String> lines = Files.readAllLines(FILE);
                int start = Math.max(0, lines.size() - MAX_LINES);
                for(String line : lines.subList(start, lines.size())){
                    historyCap.addLast(line);
                }
            } else {
                Files.createDirectories(FILE.getParent());
                Files.createFile(FILE);
            }
        } catch (IOException e){
            System.err.println("ERROR! Could not load history: " + e.getMessage());
        }
    }

    /**
     * Save history to storage file
     */

    private void save(){
        try {
            Files.write(
                    FILE,
                    historyCap,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE
            );
        }  catch (IOException e){
                System.err.println("ERROR! Could not save history: " + e.getMessage());
            }
        }
}