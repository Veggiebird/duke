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
import java.util.ArrayList;
import java.util.List;

public class graceeStorage {

    private final Path dataFile;

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
                String by = (parts.length >= 4 ) ? parts[3] : "";
                t = new graceeTaskDeadline(desc, by);
                break;
            }

            case "Event": {
                String when = (parts.length >= 4) ? parts[3] : "";
                String from = when, to = "";
                if(when.contains(" to ")){
                    String[] ft = when.split("\\s+to\\s+", 2);
                    from = ft[0];
                    to = ft.length > 1 ? ft[1] : "";
                }

                t = new graceeTaskEvents(desc,from, to);
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
