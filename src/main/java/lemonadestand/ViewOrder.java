package lemonadestand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

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

            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file + "/order" + orderNumber + ".txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                Order order = (Order) objectInputStream.readObject();
                OrderUtils.formatOrder(order);
            } catch (FileNotFoundException e) {
                System.out.println("Order with number " + orderNumber + " doesn't exist.");
            } catch (IOException e) {
                System.out.println("Internal IOException.");
            } catch (ClassNotFoundException e) {
                System.out.println("Tried to read in an order that isn't formatted correctly.");
            } catch (ClassCastException e) {
                System.out.println("The file read doesn't contain an order.");
            }

            System.out.println("Ready to exit? type 'Y' for yes.");
            exit = scanner.nextLine();
        } while (!exit.equalsIgnoreCase("Y"));
    }

}
