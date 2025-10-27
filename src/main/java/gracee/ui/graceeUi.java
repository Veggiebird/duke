package gracee.ui;
import java.util.Scanner;

/**
 * Handle all UI instance
 */

public class graceeUi {

    /**
     * Create scanner for user input
     */
    private final Scanner sc;
    public graceeUi(Scanner sc){
        this.sc = sc;
    }

    /**
     * Print divider line
     */

    public void line(){
        System.out.println("___________________________");
    }

    /**
     * Print chatbot main menu
     */

    public void printMainMenu(){
        System.out.println("Hello! I am Gracee! This is a Gracee gold shop internal chatbot.");
        System.out.println("What can I do for you?");
        System.out.println("Common FAQ: \n1. Shop business hour and details.");
        System.out.println("2. Buying/Selling price of gold.");
        System.out.println("3. List historical conversation.");
        System.out.println("4. Check your task list.");
        System.out.println("5. Bye.");
        System.out.println("Please enter 1, 2, 3, 4 or bye to proceed further.");
    }

    /**
     * Print shop branches details
     */

    public void printMainMenuShopDetails(){
        System.out.println("Opening time same for all branches: 10:00 AM - 17:00 PM.");

        System.out.println("Branch Rich");
        System.out.println("Address: 123 Blk Rich, S888999");
        System.out.println("Phone:+65 888999888");

        System.out.println("\n");

        System.out.println("Branch Jewel");
        System.out.println("Address: 222 Blk Jewel, S123999");
        System.out.println("Phone:+65 123999123");
    }

    /**
     * Print submenu - Task menu for employee to add todo / deadline / events
     */

    public void printSubMenuTask(){
        System.out.println("Task menu: ");
        System.out.println("1. Add task.");
        System.out.println("2. Update task (Update to Done or Pending)");
        System.out.println("3. List tasks.");
        System.out.println("4. Remove tasks.");
        System.out.println("5. Exit task list interface.");
    }
}
