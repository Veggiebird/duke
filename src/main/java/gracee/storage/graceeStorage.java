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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class graceeStorage {

    private final Path dataFile;
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public graceeStorage() {
        this.dataFile = Paths.get("data", "gracee.txt");
    }

    private void checkParentDir() throws IOException {
        Path parent = dataFile.getParent();
        if(parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }

    public List<graceeTaskDetails> load(){
        List<graceeTaskDetails> out = new ArrayList<>();

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

    private graceeTaskDetails parseLine(String line){
        String []  parts = line.split("\\s*\\|\\s*");
        if(parts.length != 3){
            throw new IllegalArgumentException("ERROR! Bad format.");
        }

        String type = parts[0].trim();
        String doneToken = parts[1].trim();
        String desc = parts[2];

        boolean done;

        if("1".equals(doneToken)){
            done = true;
        }else if("0".equals(doneToken)){
            done = false;
        }else{
            throw new IllegalArgumentException("ERROR! Done token must be 1 (Done) or 0 (Pending)");
        }

        graceeTaskDetails t;
        switch(type){
            case "Todo": {
                t = new graceeTaskTodo(desc);
                break;
            }

            case "Deadline":{
                LocalDateTime by = LocalDateTime.parse(parts[3].trim(), timeFormat);
                t = new graceeTaskDeadline(desc, by);
                break;
            }

            case "Event": {

                if (parts.length < 5) {
                    throw new IllegalArgumentException("ERROR! Event line missing start/end datetimes.");
                }
                LocalDateTime from = LocalDateTime.parse(parts[3].trim(), timeFormat);
                LocalDateTime to   = LocalDateTime.parse(parts[4].trim(), timeFormat);
                t = new graceeTaskEvents(desc, from, to); // uses the LocalDateTime overload
                break;
            }

            default:
                throw new IllegalArgumentException("ERROR! Unknown task type: " + type);
        }

        if(done){
            try{
                t.markAsDone();
            } catch (Exception e) {

            }
        }

        return t;
    }
}
