package lemonadestand;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import lemonadestand.model.Customer;
import lemonadestand.model.Lemonade;
import lemonadestand.model.Order;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        OrderUtils.printAsteriskLine();
        System.out.println("*                                                    *");
        System.out.println("*           WELCOME TO THE LEMONADE STAND!           *");
        System.out.println("*                                                    *");
        OrderUtils.printAsteriskLine();
        System.out.println("\nEnter your name and number to start your order!\n");
        System.out.println("Let's begin with your name:");

        String name = scanner.nextLine();

        System.out.println("\nHi " + name + ", nice to meet you.\n");
        System.out.println("Next we need your number so we'll be able to call you when your order is ready.");

        String phoneNumber = scanner.nextLine();

        System.out.println("\nAwesome! We captured your phone number as: " + OrderUtils.formatPhoneNumber(phoneNumber) + "\n");
        System.out.println("Is everything correct so far?");

        String validation = "";

        do {
            if (validation.equalsIgnoreCase("N")) {
                System.out.println("\nPlease re-enter your information.\n");
                System.out.println("Enter your Name:");
                name = scanner.nextLine();
                System.out.println("Enter your Number:");
                phoneNumber = scanner.nextLine();
                System.out.println("\nIs the updated information correct?\n");
                System.out.println("Name: " + name);
                System.out.println("Number: " + OrderUtils.formatPhoneNumber(phoneNumber) + "\n");
            }
            System.out.println("Please enter 'Y' for yes or 'N' for no.");
            validation = scanner.nextLine();
        } while (!validation.equalsIgnoreCase("Y"));

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

        File file = new File("./orders");

        File[] files = file.listFiles();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(file + "/order" + (files.length + 1) + ".json"), order);
        } catch (IOException e) {
            e.printStackTrace();
        }

        OrderUtils.formatOrder(order);

        scanner.close();
    }

}
