package lemonadestand;

import java.text.DecimalFormat;
import java.util.Scanner;

import lemonadestand.model.Customer;
import lemonadestand.model.Lemonade;
import lemonadestand.model.Order;

public class Application {

    private static final int LINE_LENGTH = 54;
    private static final String ASTERISK_LINE = "*".repeat(LINE_LENGTH);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printAsteriskLine();
        System.out.println("*                                                    *");
        System.out.println("*           WELCOME TO THE LEMONADE STAND!           *");
        System.out.println("*                                                    *");
        printAsteriskLine();
        System.out.println("\nEnter your name and number to start your order!\n");
        System.out.println("Let's begin with your name:");

        String name = scanner.nextLine();

        System.out.println("\nHi " + name + ", nice to meet you.\n");
        System.out.println("Next we need your number so we'll be able to call you when your order is ready.");

        String phoneNumber = scanner.nextLine();

        System.out.println("\nAwesome! We captured your phone number as: " + formatPhoneNumber(phoneNumber) + "\n");
        System.out.println("Is everything correct so far?");

        String validation = "";

        do {
            if (validation.equals("N")) {
                System.out.println("\nPlease re-enter your information.\n");
                System.out.println("Enter your Name:");
                name = scanner.nextLine();
                System.out.println("Enter your Number:");
                phoneNumber = scanner.nextLine();
                System.out.println("\nIs the updated information correct?\n");
                System.out.println("Name: " + name);
                System.out.println("Number: " + formatPhoneNumber(phoneNumber) + "\n");
            }
            System.out.println("Please enter 'Y' for yes or 'N' for no.");
            validation = scanner.nextLine();
        } while (!validation.equals("Y"));

        System.out.println("\nGreat! Let's get to your order then...\n");

        Customer customer = new Customer(name, phoneNumber);
        Order order = new Order(customer);

        System.out.println("How many lemonades would you like to order?");

        for (int numberOfLemonades = scanner.nextInt(), currentLemonade = 1; numberOfLemonades > 0; numberOfLemonades--, currentLemonade++) {
            System.out.println("\nLemonade number " + currentLemonade + ":");
            System.out.println("How much lemon Juice do you want? (in cups)");
            double lemonJuice = scanner.nextDouble();
            System.out.println("How much water do you want? (in cups)");
            double water = scanner.nextDouble();
            System.out.println("How much sugar do you want? (in cups)");
            double sugar = scanner.nextDouble();
            System.out.println("How many ice cubes do you want?");
            int iceCubes = scanner.nextInt();
            order.addLemonade(new Lemonade(lemonJuice, water, sugar, iceCubes));
        }

        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("\nThank you for your order !\n");
        printAsteriskLine();
        printLineWithAsterisks("* Total for " + customer.getName() + "'s order: $" + df.format(order.getTotal()));
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

        scanner.close();
    }

    private static void printAsteriskLine() {
        System.out.println(ASTERISK_LINE);
    }

    private static void printLineWithAsterisks(String content) {
        System.out.println(content + " ".repeat(LINE_LENGTH - content.length() - 1) + "*");
    }

    private static String formatPhoneNumber(String phoneNumber) {
        return phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
    }
}
