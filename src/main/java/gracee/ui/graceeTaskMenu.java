package gracee.ui;
import gracee.tasks.graceeTaskManager;
import gracee.tasks.graceeTaskDetails;
import gracee.tasks.graceeTaskTodo;
import gracee.tasks.graceeTaskEvents;
import gracee.tasks.graceeTaskDeadline;
import gracee.parser.graceeParser;
import java.util.Scanner;

public class graceeTaskMenu {
    private final graceeTaskManager taskList;
    private final Scanner sc;
    private graceeUi ui;

    public graceeTaskMenu(graceeTaskManager taskList, Scanner sc) {
        this.taskList = taskList;
        this.sc = sc;
    }

    public void printTaskMenu(){
        boolean taskChatLive = true;

        while(taskChatLive){
            ui.line();
            ui.printSubMenuTask();
            ui.line();

            String taskInput = sc.nextLine();

            switch (graceeParser.parseSubTask(taskInput)){
                case ADD:
                    addTask();
                    break;

                case UPDATE:
                    updateTask();
                    break;

                case LIST:
                    listTask();
                    break;

                case REMOVE:
                    removeTask();
                    break;

                case BACK:
                    System.out.println("Back to Main Menu.");
                    taskChatLive = false;
                    break;

                case INVALID:
                default:
                    System.out.println("Invalid input. Please enter 1-5.");
            }
        }

    }

    private void addTask(){
        graceeTaskDetails newTask = null;

        System.out.println("Please enter task type: todo / deadline / event");
        String taskType = sc.nextLine().toLowerCase();

        System.out.println("Please enter task description.");
        String taskDescription = sc.nextLine();

        if(taskType.contains("todo")){
            newTask = new graceeTaskTodo(taskDescription);
        }else if(taskType.contains("deadline")){
            System.out.println("Please enter deadline");
            String by = sc.nextLine();
            newTask = new graceeTaskDeadline(taskDescription,by);
        }else if(taskType.contains("event")){
            System.out.println("Please enter event start date");
            String from = sc.nextLine();
            System.out.println("Please enter event end date");
            String to = sc.nextLine();
            newTask = new graceeTaskEvents(taskDescription, from, to);
        }else{
            System.out.println("Invalid task type");
            addTask();
            return;
        }

        taskList.add(newTask);
        System.out.println("Added task: " + newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void updateTask(){
        if(taskList.size() == 0){
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
                System.out.println((task_number)+": " + taskList.get(index) + " has been marked as 0 (Pending).");
            }
            else if(taskList.get(index).getStatus().equals("0")){
                taskList.get(index).markAsDone();
                System.out.println((task_number)+": " + taskList.get(index) + " has been marked as 1 (Done).");
            }
        } catch (NumberFormatException e) {
            System.out.println("You did not enter a number. Please enter number and not other characters.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number is invalid, please enter valid task number between 1 - " + taskList.size());
        }
    }

    private void listTask(){

        if (taskList.size() == 0) {
            System.out.println("No task available.");
            return;
        }

        System.out.println("Task list as below");
        for(int i = 0; i < taskList.size(); i++){
            System.out.println((i+1)+": " + taskList.get(i));
        }
    }

    private void removeTask(){
        if(taskList.size() == 0){
            System.out.println("No task available.");
            return;
        }

        System.out.println("Task list as below");
        for(int i = 0; i < taskList.size(); i++){
            System.out.println((i+1)+": " + taskList.get(i));
        }
        System.out.println("Please enter task number that you would like to remove.");

        try{
            int rmTaskNumber = Integer.parseInt(sc.nextLine());
            int rmIndex = rmTaskNumber - 1;

            System.out.println((rmTaskNumber)+": " + taskList.get(rmIndex) + " is removed.");
            taskList.remove(rmIndex);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println("You did not enter a number. Please enter number and not other characters.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number is invalid, please enter valid task number between 1 - " + taskList.size());
        }
    }

}
