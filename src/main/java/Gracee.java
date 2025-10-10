import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Gracee {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        graceeHistorical historical = new graceeHistorical();
        List<graceeTaskList> taskList = new ArrayList<>();

        graceePrinter.printDividerLn();
        graceePrinter.printMainMenu(); // Print main menu
        graceePrinter.printDividerLn();

        boolean chatLive = true;

        while (chatLive) {

            String inputMain = sc.nextLine();

            switch(inputMain){
                case "1": // Main menu: 1. Shop business hour and details.
                    graceePrinter.printDividerLn();
                    graceePrinter.printMainMenuShopDetails();
                    graceePrinter.printDividerLn();

                    historical.add("Asked shop details.");
                    break;

                case "2": // Main menu: 2. Buying/Selling price of gold.
                    graceePrinter.printDividerLn();
                    System.out.println("Thanks for checking gold price with us.\nPlease enter if you want to check price for BUY, SELL or TRADEIN");
                    String goldInput = sc.nextLine();
                    graceeGoldPrice checker = new graceeGoldPrice();
                    checker.showGoldPrice(goldInput);
                    historical.add("Asked gold price.");
                    break;

                case "3": // Main menu: 3. List historical conversation.
                    graceePrinter.printDividerLn();
                    System.out.println("Previous action: ");
                    for(int i = 0; i < historical.size(); i++){
                        System.out.println(i+1 + ": " + historical.get(i));
                    }
                    graceePrinter.printDividerLn();
                    break;

                case "4": // Main menu: Check your task list.
                    graceePrinter.printDividerLn();
                    System.out.println("You are in task list interface.");
                    graceeTask taskMenu = new graceeTask((ArrayList<graceeTaskList>) taskList, sc);
                    taskMenu.printTaskMenu();
                    break;

// ==================================== END mini TASK MENU ===========================================
                case "5": // Main menu: 5. Bye
                    System.out.println("Thank you, Bye");
                    chatLive = false;
                    break;


                default: // Default for main menu
                    System.out.println("Invalid option. Please enter 1-5.");
                    break;
            }
        }

        sc.close();
    }
}


