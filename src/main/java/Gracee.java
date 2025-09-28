import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Gracee {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        gracee_historical historical = new gracee_historical();
        List<gracee_task_list> task_list = new ArrayList<>();

        System.out.println("___________________________");
        System.out.println("Hello! I am Gracee! This is a gold shop.");
        System.out.println("What can I do for you?");
        System.out.println("Common FAQ: \n1. Shop business hour and details.");
        System.out.println("2. Buying/Selling price of gold.");
        System.out.println("3. List historical conversation.");
        System.out.println("4. Check your task list.");
        System.out.println("5. Bye.");
        System.out.println("Please enter 1, 2, 3 or bye to proceed further.");
        System.out.println("___________________________");

        boolean chatLive = true;

        while (chatLive) {

            String inputMain = sc.nextLine();

            switch(inputMain){
                case "1": /* Main menu: 1. Shop business hour and details.*/
                    System.out.println("___________________________");
                    System.out.println("Opening time: 10:00 AM - 17:00 PM.");
                    System.out.println("Address: 123 Blk Rich, S888999");
                    System.out.println("Phone:+65 888999888");
                    System.out.println("___________________________");

                    historical.add("Asked shop details.");
                    break;

                case "2": /* Main menu: 2. Buying/Selling price of gold.*/
                    System.out.println("___________________________");
                    System.out.println("Today's buying price for gold is: \n 916: 150 SGD per gram\n 999: 180 SGD per gram");
                    System.out.println("Today's selling price for gold is: \n 916: 170 SGD per gram\n 999: 200 SGD per gram");
                    System.out.println("___________________________");
                    historical.add("Asked gold price.");
                    break;

                case "3": /* Main menu: 3. List historical conversation.*/
                    System.out.println("___________________________");
                    System.out.println("Previous action: ");
                    for(int i = 0; i < historical.size(); i++){
                        System.out.println(i+1 + ": " + historical.get(i));
                    }
                    System.out.println("___________________________");
                    break;

                case "4": /* Main menu: Check your task list.*/
                    System.out.println("___________________________");
                    System.out.println("You are in task list interface.");

                    boolean taskMode = true;

                    while(taskMode) {System.out.println("___________________________");
                       System.out.println("Task menu: ");
                       System.out.println("1. Add task.");
                       System.out.println("2. Update task (Update to Done or Pending)");
                       System.out.println("3. List tasks.");
                       System.out.println("4. Remove tasks.");
                       System.out.println("5. Exit task list interface.");
                       System.out.println("___________________________");

                       String taskInput = sc.nextLine();

                       switch(taskInput){
                           case "1": /*  Task menu: 1. Add task.  */

                               gracee_task_list newTask = null;

                               System.out.println("Please enter task type: todo / deadline / event");
                               String task_type = sc.nextLine();
                               String lower_task_type = task_type.toLowerCase();

                               System.out.println("Please enter task description.");
                               String task_description = sc.nextLine();

                               if(lower_task_type.contains("todo")){
                                   newTask = new gracee_task_todo(task_description);
                               }else if(lower_task_type.contains("deadline")){
                                   System.out.println("Please enter deadline");
                                   String by = sc.nextLine();
                                   newTask = new gracee_task_deadline(task_description,by);
                               }else if(lower_task_type.contains("event")){
                                   System.out.println("Please enter event start date");
                                   String from = sc.nextLine();
                                   System.out.println("Please enter event end date");
                                   String to = sc.nextLine();
                                   newTask = new gracee_task_events(task_description, from, to);
                               }else{
                                   System.out.println("Invalid task type");
                                   break;
                               }

                               task_list.add(newTask);
                               System.out.println("Added task: " + newTask);
                               System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                               break;

                           case "2": /*  Task menu: 2. Update task (Update to Done or Pending)  */

                               if(task_list.isEmpty()){
                                   System.out.println("No task available.");
                                   break;
                               }

                               System.out.println("Task list as below");
                               for(int i = 0; i < task_list.size(); i++){
                                   System.out.println((i+1)+": " + task_list.get(i));
                               }
                               System.out.println("Please enter task number that you would like to modify.");

                               try {
                                   int task_number = Integer.parseInt(sc.nextLine().trim());
                                   int index = task_number - 1;

                                   System.out.println((task_number)+ ": " + task_list.get(index));
                                   if(task_list.get(index).getStatus().equals("Done")){
                                       task_list.get(index).markAsPending();
                                       System.out.println((task_number)+": " + task_list.get(index) + " has been marked as Pending.");
                                   }
                                   else if(task_list.get(index).getStatus().equals("Pending")){
                                       task_list.get(index).markAsDone();
                                       System.out.println((task_number)+": " + task_list.get(index) + " has been marked as Done.");
                                   }
                               } catch (NumberFormatException e) {
                                   System.out.println("You did not enter a number. Please enter number and not other characters.");
                                   break;
                               } catch (IndexOutOfBoundsException e) {
                                   System.out.println("The task number is invalid, please enter valid task number between 1 - " + task_list.size());
                               }
                               break;

                           case "3": /* Task menu: 3. List tasks.*/
                               System.out.println("Task list as below");
                               for(int i = 0; i < task_list.size(); i++){
                                   System.out.println((i+1)+": " + task_list.get(i));
                               }
                               break;

                           case "4": /* Task menu: 4. Remove task*/
                               if(task_list.isEmpty()){
                                   System.out.println("No task available.");
                                   break;
                               }

                               System.out.println("Task list as below");
                               for(int i = 0; i < task_list.size(); i++){
                                   System.out.println((i+1)+": " + task_list.get(i));
                               }
                               System.out.println("Please enter task number that you would like to remove.");

                               try{
                                    int rm_task_number = Integer.parseInt(sc.nextLine());
                                    int rm_index = rm_task_number - 1;

                                    System.out.println((rm_task_number)+": " + task_list.get(rm_index) + " is removed.");
                                    task_list.remove(rm_index);
                               } catch (NumberFormatException e) {
                                   System.out.println("You did not enter a number. Please enter number and not other characters.");
                                   break;
                               } catch (IndexOutOfBoundsException e) {
                                   System.out.println("The task number is invalid, please enter valid task number between 1 - " + task_list.size());
                                   break;
                               }
                               break;

                           case "5": /* Task menu: 5. Exit task list interface.*/
                               System.out.println("Back to main menu.");
                               taskMode = false;
                               break;
                       }

                        System.out.println("Back to main menu.");

                    }
                break;

                case "5": /* Main menu: 5. Bye*/
                    System.out.println("Thank you, Bye");
                    chatLive = false;
                    break;

                default:
                    System.out.println("Invalid option. Please enter 1-5.");
                    break;
            }
        }
        sc.close();
    }
}


// Level-6 checkpoint
// Level-0 checkpoint
