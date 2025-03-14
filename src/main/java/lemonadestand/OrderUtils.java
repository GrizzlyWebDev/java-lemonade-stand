package lemonadestand;

import java.text.DecimalFormat;

import lemonadestand.model.Lemonade;
import lemonadestand.model.Order;

public class OrderUtils {

    private static final int LINE_LENGTH = 54;
    private static final String ASTERISK_LINE = "*".repeat(LINE_LENGTH);

    public static void printAsteriskLine() {
        System.out.println(ASTERISK_LINE);
    }

    public static void printLineWithAsterisks(String content) {
        System.out.println(content + " ".repeat(LINE_LENGTH - content.length() - 1) + "*");
    }

    public static String formatPhoneNumber(String phoneNumber) {
        return phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
    }

    public static void formatOrder(Order order) {
        DecimalFormat df = new DecimalFormat("#.00");

        printAsteriskLine();
        printLineWithAsterisks("* Total for " + order.getCustomer().getName() + "'s order: $" + df.format(order.getTotal()));
        printLineWithAsterisks("* Phone Number: " + formatPhoneNumber(order.getCustomer().getPhoneNumber()));
        System.out.println("*                                                    *");
        int lemonadeCount = 1;
        for (Lemonade lemonade : order.getLemonades()) {
            printLineWithAsterisks("* Lemonade #" + lemonadeCount);
            System.out.println("*                                                    *");

            printLineWithAsterisks("* LemonJuice: " + df.format(lemonade.getLemonJuice()));
            printLineWithAsterisks("* Water: " + df.format(lemonade.getWater()));
            printLineWithAsterisks("* Sugar: " + df.format(lemonade.getSugar()));
            printLineWithAsterisks("* Ice Cubes: " + lemonade.getIceCubes());
            printLineWithAsterisks("* Price: $" + df.format(lemonade.getPrice()));

            System.out.println("*                                                    *");
            printAsteriskLine();
            lemonadeCount++;
        }
    }
}
