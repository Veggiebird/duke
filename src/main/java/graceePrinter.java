public class graceePrinter {

    public static void printDividerLn(){
        System.out.println("___________________________");
    }

    public static void printMainMenu(){
        System.out.println("Hello! I am Gracee! This is a gold shop.");
        System.out.println("What can I do for you?");
        System.out.println("Common FAQ: \n1. Shop business hour and details.");
        System.out.println("2. Buying/Selling price of gold.");
        System.out.println("3. List historical conversation.");
        System.out.println("4. Check your task list.");
        System.out.println("5. Bye.");
        System.out.println("Please enter 1, 2, 3, 4 or bye to proceed further.");
    }

    public static void printMainMenuShopDetails(){
        System.out.println("Opening time: 10:00 AM - 17:00 PM.");
        System.out.println("Address: 123 Blk Rich, S888999");
        System.out.println("Phone:+65 888999888");
    }

    public static void printSubMenuTask(){
        System.out.println("Task menu: ");
        System.out.println("1. Add task.");
        System.out.println("2. Update task (Update to Done or Pending)");
        System.out.println("3. List tasks.");
        System.out.println("4. Remove tasks.");
        System.out.println("5. Exit task list interface.");
    }
}
