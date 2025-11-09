package gracee;
import gracee.ui.graceeUi;
import gracee.ui.graceeTaskMenu;
import gracee.parser.graceeParser;
import gracee.storage.graceeStorage;
import gracee.tasks.graceeTaskManager;

import java.util.Scanner;

/**
 * Entry for Gracee chatbot application.
 *
 * Gracee is a simple command line assistant for Gracee gold shop employee to manage their task provide shop details.
 * It has main menu to view shop info, check daily gold price, review historical activity and manage task list like todo, deadline and events.
 *
 */
public class Gracee {
    /**
     * Main method that starts the chatbot
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        graceeStorage storage = new graceeStorage();
        graceeTaskManager taskManager = new graceeTaskManager(storage.load(), storage);

        graceeUi ui = new graceeUi(sc);

        graceeHistorical historical = new graceeHistorical();

        ui.line();
        ui.printMainMenu(); // Print main menu
        ui.line();

        boolean isChatLive = true;

        while (isChatLive) {

            String inputMain = sc.nextLine();

            switch (graceeParser.parseMain(inputMain)){
                case SHOP: // Main menu: 1. Shop business hour and details.
                    ui.line();
                    ui.printMainMenuShopDetails();
                    ui.line();

                    historical.add("Check shop details.");
                    break;

                case PRICE: // Main menu: 2. Buying/Selling price of gold.
                    ui.line();
                    System.out.println("Please enter if you want to check price for BUY, SELL or TRADEIN");
                    String goldInput = sc.nextLine();
                    graceeGoldPrice checker = new graceeGoldPrice();
                    checker.showGoldPrice(goldInput);
                    historical.add("Checked gold price.");
                    break;

                case HISTORY: // Main menu: 3. List historical conversation.
                    ui.line();
                    System.out.println("Previous action: ");
                    for(int i = 0; i < historical.size(); i++){
                        System.out.println(i+1 + ": " + historical.get(i));
                    }
                    ui.line();
                    break;

                case TASKS: // Main menu: Check your task list.
                    ui.line();
                    System.out.println("You are in task list interface.");
                    new graceeTaskMenu(taskManager,sc, ui).printTaskMenu();
                    break;

// ==================================== END mini TASK MENU ===========================================
                case EXIT: // Main menu: 5. Bye
                    System.out.println("Thank you, Bye");
                    isChatLive = false;
                    break;

                case INVALID:
                default: // Default for main menu
                    System.out.println("Invalid option. Please enter 1-5.");
                    break;
            }
        }
        sc.close();
    }
}


