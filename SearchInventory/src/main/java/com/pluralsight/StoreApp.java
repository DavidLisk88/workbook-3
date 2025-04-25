package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreApp {

    // I initialize static variables to call things in multiple methods.
    static Scanner userInput = new Scanner(System.in);
    static ArrayList<Product> inventory = new ArrayList<>();
    // set the path of the file we want to manipulate.
    static String searchFile = "src/main/resources/inventory.csv";
    // I make a new variable for the file located in the searchfile stream.
    static File logFile = new File(searchFile);



    // THE MAIN METHOD WILL ASK IF THE USER WANTS TO START THE PROGRAM.
    public static void main(String[] args) {

        // Before we even start, we need to read the file.
        readTheFileFirst();


        System.out.println("----------WELCOME----------\n");

        System.out.println("Hello there, friend...");
        waiting();

            // Use a while loop for the users choice to move forward.
            while (true) {

                System.out.println("Would you like to start the program or exit? ");
                System.out.println("Y or N");
                System.out.print("Please enter here: ");

                String userChoice1 = userInput.nextLine();

                if (userChoice1.equalsIgnoreCase("Y")) {
                    System.out.println("\nTaking you to the store...");
                    waiting();
                    mainMenu();
                    break;
                } else if (userChoice1.equalsIgnoreCase("N")) {
                    System.out.println("Okay, thank you for your time.");
                    waiting();
                    userInput.close();
                    return;
                }
                else {
                   System.out.println("Invalid input. Please try again.");
                   divider();
            }
        }
    }




    // THIS METHOD WILL READ THE FILE.
    public static void readTheFileFirst(){

        try(BufferedReader readTheFile = new BufferedReader(new FileReader(logFile))){
            String productLine;
            readTheFile.readLine();

            while((productLine = readTheFile.readLine()) != null){
                String[] productParts = productLine.split("\\|");
                if (productParts.length == 3){
                    int id = Integer.parseInt(productParts[0].trim());
                    String name = productParts[1].trim();
                    double price = Double.parseDouble(productParts[2].trim().replace("$", ""));
                    Product splitProduct = new Product(id, name, price);
                    inventory.add(splitProduct);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}



    // THIS METHOD ALLOWS THE USER TO CHOOSE WHAT TO DO WITH THE FILE.
    public static void mainMenu() {
    divider();
    System.out.println("---------- MAIN MENU ----------");
    System.out.println("1. List all products.");
    System.out.println("2. Look up a product by ID.");
    System.out.println("3. Find all products within a price range.");
    System.out.println("4. Add a new product.");
    System.out.println("5. Exit the application.");
    System.out.print("Enter Here: ");


    String userMenuChoice = userInput.nextLine();

    while (true) {
        switch(userMenuChoice){
        case "1":
                divider();
                listProducts();
            break;

            case "2":
                divider();
                waiting();
                searchById();
            break;
            case "3":
                waiting();
                searchByPrice();
            case "4":
                System.out.println("\nSounds great...");
                waiting();
                divider();
                addProduct();
            break;
            case "5":
                divider();
                System.out.println("\nOkay thank you for your time.");
                waiting();
                userInput.close();
            return;

            default:
                System.out.println("Invalid input...");
                waiting();
        }

    }


}




    // THIS METHOD WILL ALLOW THE USER TO SEE ALL THE LISTED PRODUCTS IN INVENTORY.
    public static void listProducts(){
        divider();
        if(inventory.isEmpty()){
            System.out.println("No products at this time.");
        } else {
            System.out.println("We carry the following inventory:");
            for(Product prod : inventory){
                System.out.printf("%d | %s | $%.2f\n", prod.getId(), prod.getName(), prod.getPrice());
            }
        }
        divider();
        waiting();
        doSomethingElse();
        userInput.close();
    }



    // THIS METHOD WILL ALLOW THE USER TO SEARCH FOR A PRODUCT BY PRICE.
    public static void searchByPrice(){


        divider();

        try {
            System.out.println("Enter minimum price: ");
            double minPrice = Double.parseDouble(userInput.nextLine().trim());
            System.out.println("Enter maximum price: ");
            double maxPrice = Double.parseDouble(userInput.nextLine().trim());

            System.out.println("Please wait...");
            waiting();
            divider();
            System.out.println("----------  RESULTS  ---------- ");

            // We use a boolean variable to let the computer know that the file isn't found yet.
            boolean found = false;

            for (Product prod : inventory) {
                // If the grabbeed price is greater than or equal to the minimum price, and less than or equal to what the maximum price,
                if (prod.getPrice() >= minPrice && prod.getPrice() <= maxPrice) {
                    // then we print out the format of the products we found in that price range.
                    System.out.printf("\n%d | %s | $%.2f\n", prod.getId(), prod.getName(), prod.getPrice());
                    // Now we let the computer know that the file is found and we move forward.
                    found = true;
                }
            }
            // This is a function for when we are unable to find any products.
            if (!found) {
                System.out.println("No products in this price range.");
                mainMenu();
            }


        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        divider();
        waiting();
       doSomethingElse();
       userInput.close();
    }



    // THIS METHOD WILL ALLOW THE USER TO SEARCH FOR A PRODUCT BY ID.
    public static void searchById() {
        while (true) {
            try {
                System.out.println("\nPlease enter an ID ");
                System.out.println("Press 0 to exit at any time.");
                // I use an integer for user input but parse it since the computer is reading the ID as a string.
                int idSearch = Integer.parseInt(userInput.nextLine());

                // If the user enters 0, then we exit and go to the next method.
                if (idSearch == 0) {
                    System.out.println("\nExiting....");
                    waiting();
                    doSomethingElse();
                    break;
                }

                // Always use a boolean value when checking something because we need to let the code know that what we are looking for is not found yet.
                boolean found = false;

                // Similar formula to finding price ranges, but this time
                // in the Product array that we are calling "prod" now instead of "inventory",
                for (Product prod : inventory) {
                    // we just see if the users input is equal to the grabbed ID from the product array.
                    if (idSearch == prod.getId()) {
                        System.out.println("Here are your results:");
                        System.out.printf("\n%d | %s | $%.2f\n", prod.getId(), prod.getName(), prod.getPrice());
                        // let the computer know that we found the product.
                        found = true;
                        // we are going to keep asking for an ID until the user decides to stop looking for products.
                        break;
                    }
                }
                // If we did not find a product that the user input, then we will let the user know that we did not find it.
                if (!found) {
                    System.out.println("Product not found.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            waiting();
        }
    }



    // THIS METHOD ALLOWS THE USER TO CREATE A NEW PRODUCT.
    public static void addProduct(){

        // Let's start asking the user for product information.
      try {
          System.out.println("Product ID: ");
          int id = Integer.parseInt(userInput.nextLine().trim());
          System.out.println("Product Name: ");
          String name = userInput.nextLine().trim();
          System.out.println("Price of product: ");
          double price = Double.parseDouble(userInput.nextLine().trim());

          // We create a new variable for the new product we are about to create.
          Product newProduct = new Product(id, name, price);
          // And we store that new product in the inventory array.
          inventory.add(newProduct);

          // We call the "logActions" method and hand it this print format for when it logs the new product.
          // What we hand it will be called "theAction" which is what we initialized in the parentheses of the "logAction" method.
          logActions(String.format("%d | %s | $%.2f", id, name, price));
          waiting();
          System.out.println("Product added ------>  " + newProduct + "\n");


      } catch (NumberFormatException e) {
          System.out.println("There was an error...");
          throw new RuntimeException(e);
      }
      divider();
      waiting();
      doSomethingElse();
      userInput.close();
    }



    // THIS METHOD ALLOWS THE USERS NEW PRODUCT INPUT TO BE WRITTEN TO THE FILE.
    public static void logActions(String theAction) {
        // Since the logged action of the user is a string, we will initialize a string variable called "theAction" in the parentheses.



        // Adding "true" next to the file means that you are appending the logs.
        // appending the logs just means each log will be logged below the previous one.
        // adding true means the old information stays preserved.
        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(logFile, true))) {

            // Tell the Buffered Writer to write what we brought inside "theAction" variable.
            writer1.write(theAction);
            // This just tells the writer to write on a new line. We have to be very specific with instructions.
            writer1.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    // THIS METHOD IS WHERE THE USER WILL FREQUENTLY GO AFTER COMPLETING A TASK.
    public static void doSomethingElse(){
        System.out.println("Would you like to do something else?");
        System.out.print("Please enter Y or N: ");

        String doSomething = userInput.nextLine().trim().toUpperCase();

        while(true){
            switch (doSomething){
                case "Y":
                    System.out.println("\nOkay, one moment...");
                    waiting();
                    mainMenu();
                    return;
                case "N":
                System.out.println("\nOkay, have a good day!");
                waiting();
                userInput.close();
                System.exit(0);
                break;
                default:
                    System.out.println("Invalid input....");
            }

        }




    }







        // THIS METHOD CREATES A DIVIDER IN BETWEEN PROMPTS.
        public static void divider () {
            System.out.println("-".repeat(10));
        }


        // THIS METHOD IS FOR WAITING IN BETWEEN PROMPTS FOR USER EXPERIENCE.
        public static void waiting () {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Something went wrong while loading...");
            }
        }













    }



