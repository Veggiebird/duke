package gracee.storage;

import gracee.tasks.graceeTaskDetails;
import gracee.tasks.graceeTaskTodo;
import gracee.tasks.graceeTaskEvents;
import gracee.tasks.graceeTaskDeadline;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * Handle create of files to keep and update historical event and tasks.
 * Each line represents a serialized task.
 *
 * Supported formats for each task type:
 *
 * Todo: Todo | status | description
 * Deadline: Deadline | status | description | by date | by time
 * Event: Event | status | description | from date | from time | to date | to time
 */

public class graceeStorage {

    /**
     * Path to storage
     */
    private final Path dataFile;

    /**
     * Date format
     */
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Time format
     */
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Construct storage handler that read and write to data/gracee.txt
     */
    public graceeStorage() {
        this.dataFile = Paths.get("data", "gracee.txt");
    }

    /**
     * Check if parent directory exist and create if absent
     * @throws IOException
     */
    private void checkParentDir() throws IOException {
        Path parent = dataFile.getParent();
        if(parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }

    /**
     * Load task from file
     * If file does not exist, then empty list is returned and create parent directory
     */
    public List<graceeTaskDetails> load(){
        List<graceeTaskDetails> out = new ArrayList<>();

        assert dataFile != null : "Data file path cannot be null";

        try{
            if(!Files.exists(dataFile)){
                checkParentDir();
                return out;
            }

            List<String> lines = Files.readAllLines(dataFile, StandardCharsets.UTF_8);
            for(String line : lines){
                try {
                    graceeTaskDetails task = parseLine(line);
                    if (task != null) {
                        out.add(task);
                    }
                } catch (RuntimeException ex){
                    System.err.println("ERROR! Skipping corrupted line: " + line);
                }
            }
        } catch (IOException e){
            System.err.println("ERROR! Unable to read tasks: " + e.getMessage());
        }

        return out;
        }

    /**
     * Save tasks to file
      * @param tasks Save task to file
     */
    public void save(List<graceeTaskDetails> tasks){
        try{
            checkParentDir();
            List<String> lines = new ArrayList<>();

            for(graceeTaskDetails task : tasks){
                lines.add(task.toString());
            }
            Files.write(
                    dataFile,
                    lines,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE
            );
        } catch (IOException e){
            System.err.println("ERROR! Unable to save tasks: " + e.getMessage());
        }
    }

    /**
     * Parse single serialized task in correct format into file
     * @param line Raw line read from storage
     * @return Parsed task object
     */

    private graceeTaskDetails parseLine(String line){
        String []  parts = line.split("\\s*\\|\\s*");
        if(parts.length < 3){
            throw new IllegalArgumentException("ERROR! Bad format.");
        }

        String type = parts[0].trim();
        String doneToken = parts[1].trim();
        String desc = parts[2];

        boolean isDone;

        if("1".equals(doneToken)){
            isDone = true;
        }else if("0".equals(doneToken)){
            isDone = false;
        }else{
            throw new IllegalArgumentException("ERROR! Done token must be 1 (Done) or 0 (Pending)");
        }

        graceeTaskDetails t;
        switch (type){

            //No time needed for todo event
            case "Todo": {
                t = new graceeTaskTodo(desc);
                break;
            }

            case "Deadline":{

                // Deadline | status | description | by Date | by Time
                if (parts.length < 5) {
                    throw new IllegalArgumentException("ERROR! Deadline line missing start/end datetime.");
                }

                LocalDate byDate = LocalDate.parse(parts[3].trim(), dateFormat);
                LocalTime byTime = LocalTime.parse(parts[4].trim(), timeFormat);
                t = new graceeTaskDeadline(desc, byDate, byTime);
                break;
            }

            case "Event": {

                // Event | status | description | from Date | from Time | to Date | to Time
                if (parts.length < 7) {
                    throw new IllegalArgumentException("ERROR! Event line missing start/end datetime.");
                }
                LocalDate fromDate = LocalDate.parse(parts[3].trim(), dateFormat);
                LocalTime fromTime   = LocalTime.parse(parts[4].trim(), timeFormat);

                LocalDate toDate = LocalDate.parse(parts[5].trim(), dateFormat);
                LocalTime toTime   = LocalTime.parse(parts[6].trim(), timeFormat);
                t = new graceeTaskEvents(desc, fromDate, fromTime, toDate, toTime); // uses the LocalDateTime overload
                break;
            }

            default:
                throw new IllegalArgumentException("ERROR! Unknown task type: " + type);
        }

        if (isDone){
            try{
                t.markAsDone();
            } catch (Exception e) {

            }
        }

        return t;
    }
}
