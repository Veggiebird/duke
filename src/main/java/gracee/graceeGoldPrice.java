package gracee;
import gracee.ui.graceeUi;

public class graceeGoldPrice {

    graceeUi ui;
    // Change gold price daily here

    //Shop buy, customer sell
    private double buy999 = 160.00;
    private double buy916 = 150.00;
    private double buy750 = 130.00;

    //Shop sell, customer buy
    private double sell999 = 175.00;
    private double sell916 = 160.00;

    //Trade in
    private double tradeIn999 = 165.00;
    private double tradeIn916 = 155.00;
    private double tradeIn750 = 135.00;

    public enum goldPrice{
        BUY, SELL, TRADEIN
    }

    public void showGoldPrice(String ask){
        System.out.println("Currency is SGD, price per gram.\n");

        try {
            goldPrice choice = goldPrice.valueOf(ask.toUpperCase());

            switch (choice) {
                case BUY: // Shop sell, customer buy
                    System.out.println("We sell: \n999: " + sell999);
                    System.out.println("916: " + sell916);
                    System.out.println("750: Item sell by piece and not gold rate.");
                    ui.line();
                    break;

                case SELL: //Shop buy, customer sell
                    System.out.println("We buy: \n999: " + buy999);
                    System.out.println("916: " + buy916);
                    System.out.println("750: " + buy750);
                    ui.line();
                    break;

                case TRADEIN: //Customer trade in for other item
                    System.out.println("Trade in price: \n999: " + tradeIn999);
                    System.out.println("916: " + tradeIn916);
                    System.out.println("750: " + tradeIn750);
                    ui.line();
                    break;
            }
        } catch (IllegalArgumentException e){

            System.out.println("Invalid input. Please enter BUY, SELL or TRADEIN.");

        }

    }
}
