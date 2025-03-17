package lemonadestand;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import lemonadestand.model.Order;

public class ViewOrder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        File file = new File("./orders");

        String exit;
        do {
            System.out.println("Which order number would you like to view?");

            int orderNumber = scanner.nextInt();
            scanner.nextLine();

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Order order = objectMapper.readValue(new File(file + "/order" + orderNumber + ".json"), Order.class);
                OrderUtils.formatOrder(order);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Ready to exit? type 'Y' for yes.");
            exit = scanner.nextLine();
        } while (!exit.equalsIgnoreCase("Y"));

        scanner.close();
    }

}
