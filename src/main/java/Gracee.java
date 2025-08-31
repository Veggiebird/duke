import java.util.Scanner;

public class Gracee {
    public static void main(String[] args) {

        Scanner user_input = new Scanner(System.in);

        String[] historical = new String[50];
        int count_historical = 0;

        System.out.println("___________________________");
        System.out.println("Hello! I am Gracee! This is a gold shop.");
        System.out.println("What can I do for you?");
        System.out.println("Common FAQ: \n 1. Shop business hour and details. \n 2. Buying/Selling price of gold. \n 3. List historical conversation \n 4. Bye \n Please enter 1, 2, 3 or bye to proceed further.");
        System.out.println("___________________________");

        boolean chat_live = true;

        while (chat_live) {
            String input = user_input.nextLine();

            if (input.equals("1")) {
                System.out.println("___________________________");
                System.out.println("Opening time: 10:00 AM - 17:00 PM \nAddress: 123 Blk Rich, S123456 \nPhone:+65 12345678");
                System.out.println("___________________________");

                historical[count_historical] = input;
                count_historical++;
                System.out.println("added: " + input );
            }
            else if (input.equals("2")) {
                System.out.println("___________________________");
                System.out.println("Today's buying price for gold is: \n 916: 150 SGD per gram\n 999: 180 SGD per gram");
                System.out.println("Today's selling price for gold is: \n 916: 170 SGD per gram\n 999: 200 SGD per gram");

                historical[count_historical] = input;
                count_historical++;
                System.out.println("added: " + input );
            }
            else if (input.equals("bye")) {
                System.out.println("Thank you, bye! Hope to see you again.");
                chat_live = false;
            }
            else if (input.equals("3")) {

                for (int i = 0; i < count_historical; i++) {
                    System.out.println((i+1) + ":" + historical[i] );
                }
                chat_live = false;
            }
            else{
                System.out.println("I do not understand. Try again.");
            }
        }
        user_input.close();
    }
}


