import java.util.ArrayList;
import java.util.Scanner;

public class graceeTask {
    private final ArrayList<graceeTaskList> taskList;
    private final Scanner sc;
    private final graceeStorage storage;

    public graceeTask(ArrayList<graceeTaskList> taskList, Scanner sc, graceeStorage storage) {
        this.taskList = taskList;
        this.sc = sc;
        this.storage = storage;
    }

    public void printTaskMenu(){
        boolean taskChatLive = true;

        while(taskChatLive){
            graceePrinter.printDividerLn();
            graceePrinter.printSubMenuTask();
            graceePrinter.printDividerLn();

            String taskInput = sc.nextLine();

            switch (taskInput){
                case "1":
                    addTask();
                    break;

                case "2":
                    updateTask();
                    break;

                case "3":
                    listTask();
                    break;

                case "4":
                    removeTask();
                    break;

                case "5":
                    System.out.println("Back to Main Menu.");
                    taskChatLive = false;
                    break;

                default:
                    System.out.println("Invalid input. Please enter 1-5.");
            }
        }

    }

    private void addTask(){
        graceeTaskList newTask = null;

        System.out.println("Please enter task type: todo / deadline / event");
        String taskType = sc.nextLine().toLowerCase();

        System.out.println("Please enter task description.");
        String task_description = sc.nextLine();

        if(taskType.contains("todo")){
            newTask = new graceeTaskTodo(task_description);
        }else if(taskType.contains("deadline")){
            System.out.println("Please enter deadline");
            String by = sc.nextLine();
            newTask = new graceeTaskDeadline(task_description,by);
        }else if(taskType.contains("event")){
            System.out.println("Please enter event start date");
            String from = sc.nextLine();
            System.out.println("Please enter event end date");
            String to = sc.nextLine();
            newTask = new graceeTaskEvents(task_description, from, to);
        }else{
            System.out.println("Invalid task type");
            addTask();
            return;
        }

        taskList.add(newTask);
        System.out.println("Added task: " + newTask);
        storage.save(taskList);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void updateTask(){
        if(taskList.isEmpty()){
            System.out.println("No task available.");
        }

        System.out.println("Task list as below");
        for(int i = 0; i < taskList.size(); i++){
            System.out.println((i+1)+": " + taskList.get(i));
        }
        System.out.println("Please enter task number that you would like to modify.");

        try {
            int task_number = Integer.parseInt(sc.nextLine().trim());
            int index = task_number - 1;

            System.out.println((task_number)+ ": " + taskList.get(index));
            if(taskList.get(index).getStatus().equals("1")){
                taskList.get(index).markAsPending();
                System.out.println((task_number)+": " + taskList.get(index) + " has been marked as Pending.");
            }
            else if(taskList.get(index).getStatus().equals("0")){
                taskList.get(index).markAsDone();
                System.out.println((task_number)+": " + taskList.get(index) + " has been marked as Done.");
            }
            storage.save(taskList);
        } catch (NumberFormatException e) {
            System.out.println("You did not enter a number. Please enter number and not other characters.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number is invalid, please enter valid task number between 1 - " + taskList.size());
        }
    }

    private void listTask(){

        if (taskList.isEmpty()) {
            System.out.println("No task available.");
            return;
        }

        System.out.println("Task list as below");
        for(int i = 0; i < taskList.size(); i++){
            System.out.println((i+1)+": " + taskList.get(i));
        }
    }

    private void removeTask(){
        if(taskList.isEmpty()){
            System.out.println("No task available.");
            return;
        }

        System.out.println("Task list as below");
        for(int i = 0; i < taskList.size(); i++){
            System.out.println((i+1)+": " + taskList.get(i));
        }
        System.out.println("Please enter task number that you would like to remove.");

        try{
            int rm_task_number = Integer.parseInt(sc.nextLine());
            int rm_index = rm_task_number - 1;

            System.out.println((rm_task_number)+": " + taskList.get(rm_index) + " is removed.");
            taskList.remove(rm_index);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");

            storage.save(taskList);

        } catch (NumberFormatException e) {
            System.out.println("You did not enter a number. Please enter number and not other characters.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number is invalid, please enter valid task number between 1 - " + taskList.size());
        }
    }

}
