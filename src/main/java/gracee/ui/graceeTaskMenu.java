package gracee.ui;
import gracee.tasks.graceeTaskManager;
import gracee.tasks.graceeTaskDetails;
import gracee.tasks.graceeTaskTodo;
import gracee.tasks.graceeTaskEvents;
import gracee.tasks.graceeTaskDeadline;
import gracee.parser.graceeParser;
import gracee.graceeDateTime;
import gracee.tasks.graceeSearch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Handle user interaction for task management.
 *
 * Provides option to add, update, list and remove tasks for task types such as todo / deadline / events.
 */

public class graceeTaskMenu {
    private final graceeTaskManager taskList;
    private final Scanner sc;
    private graceeUi ui;
    private final graceeSearch search;

    /**
     * Create new task menu handler
     * @param taskList
     * @param sc
     * @param ui
     */
    public graceeTaskMenu(graceeTaskManager taskList, Scanner sc, graceeUi ui) {
        this.taskList = taskList;
        this.sc = sc;
        this.ui = ui;
        this.search = new graceeSearch();
    }

    /**
     * Display main task menu
     */

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

                case SEARCH:
                    searchTask();
                    break;

                case KEYWORD:
                    searchKeyword();
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

    /**
     * Ask user to enter date and check if it's valid.
     * If date is invalid and it will ask user to enter again.
     * @param msg
     * @return
     */

    private String checkDate(String msg){
        while(true){
            System.out.println(msg);
            String input = sc.nextLine();
            try{
                graceeDateTime.parseDateFlexible(input);
                return input;
            } catch (IllegalArgumentException e){
                System.out.println("Invalid date format. Please enter valid date such as 111025, 11 oct 2025");
            }
        }
    }

    /**
     * Ask user to enter time and check if it's valid.
     * If date is invalid and it will ask user to enter again.
     * @param msg
     * @return
     */

    private String checkTime(String msg){
        while(true){
            System.out.println(msg);
            String input = sc.nextLine();
            try{
                graceeDateTime.parseTimeFlexible(input);
                return input;
            } catch (IllegalArgumentException e){
                System.out.println("Invalid time format. Please enter valid date such as 1800, 18:00");
            }
        }
    }

    /**
     * Add task based on user input
     */

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
            String byDate = checkDate("Please enter date in ddMMyyy format, example 11102025");
            String byTime = checkTime("Please enter time in HHmm format, example 1800");
            newTask = new graceeTaskDeadline(taskDescription,byDate, byTime);
        }else if(taskType.contains("event")){
            String fromDate = checkDate("Please enter date in ddMMyyy format, example 11102025");
            String fromTime = checkTime("Please enter time in HHmm format, example 1800");
            String toDate = checkDate("Please enter date in ddMMyyy format, example 11102025");
            String toTime = checkTime("Please enter time in HHmm format, example 1800");
            newTask = new graceeTaskEvents(taskDescription, fromDate, fromTime, toDate, toTime);
        }else{
            System.out.println("Invalid task type");
            addTask();
            return;
        }

        taskList.add(newTask);
        System.out.println("Added task: " + newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Update task based on user input, either set the task status to done or pending.
     */

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

    /**
     * List all tasks
     */

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

    /**
     * Remove selected task
     */

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

    private void searchTask(){

        if(taskList.size() == 0){
            System.out.println("No task exist.");
        }

        System.out.println("Select search by \n1. Single Date \n2. Date Range.\nPlease enter 1 or 2.");

        String selectSearchType = sc.nextLine().trim();

        if(selectSearchType.equals("1")){

            System.out.println("Please enter the task date that you would like to search.");

            String dateInput = sc.nextLine();

            LocalDate searchDate = graceeDateTime.parseDateFlexible(dateInput);
            List<graceeTaskDetails> searchDateOutput = search.searchDate(searchDate);

            if(searchDateOutput.isEmpty()){
                System.out.println("No task found for date " + searchDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else{
                System.out.println("Task List matching date as per below: \n");

                for(int i = 0; i < searchDateOutput.size(); i++){
                    System.out.println((i+1)+": " + searchDateOutput.get(i) + "\n");
                }
            }
        } else if(selectSearchType.equals("2")){

            System.out.println("Please enter the task date range that you would like to search.");

            String dateRangeInputFrom = sc.nextLine();
            String dateRangeInputTo = sc.nextLine();

            LocalDate searchDateRangeFrom = graceeDateTime.parseDateFlexible(dateRangeInputFrom);
            LocalDate searchDateRangeTo = graceeDateTime.parseDateFlexible(dateRangeInputTo);
            List<graceeTaskDetails> searchDateRangeOutput = search.searchDateRange(searchDateRangeFrom, searchDateRangeTo);

            if(searchDateRangeTo.isBefore(searchDateRangeFrom)){
                System.out.println("End date cannot be before start date.");
                return;
            }

            if(searchDateRangeOutput.isEmpty()){
                System.out.println("No task found for date between " + searchDateRangeFrom.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " to " + searchDateRangeTo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else{
                System.out.println("Task List matching date range as per below: \n");

                for(int i = 0; i < searchDateRangeOutput.size(); i++){
                    System.out.println((i+1)+": " + searchDateRangeOutput.get(i) + "\n");
                }
            }

        }
    }

    private void searchKeyword(){

        if(taskList.size() == 0){
            System.out.println("No task exist.");
            return;
        }

        System.out.println("Please enter the keyword that you would like to search in task list");

        String searchKey = sc.nextLine();

        if(searchKey.isEmpty()){
            System.out.println("Keyword cannot be empty.");
            return;
        }

        List<graceeTaskDetails> keywordOutput = search.searchKeyword(searchKey);

        if(keywordOutput.isEmpty()){
            System.out.println("No task found for keyword " + searchKey);
        } else{
            System.out.println("Task List matching keyword as per below: \n");

            for(int i = 0; i < keywordOutput.size(); i++){
                System.out.println((i+1)+": " + keywordOutput.get(i) + "\n");
            }
        }

    }

}
