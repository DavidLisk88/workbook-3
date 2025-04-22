package com.pluralsight;

import java.util.Scanner;

public class Main {

    static Scanner userInput = new Scanner(System.in);
    static String[] quotes = {
            "Be yourself; everyone else is already taken.\n- Oscar Wilde",
            "So many books, so little time.\n- Frank Zappa",
            "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.\n- Albert Einstein",
            "A room without books is like a body without a soul.\n- Marcus Tullius Ciero",
            "You know you're in love when you can't fall asleep because reality is finally better than your dreams.\n- Dr. Seuss",
    };
    public static void main(String[] args) {

        boolean something = true;
while (something) {
    try {
        System.out.println("Select a number 1-5 to choose a quote!\n");
        int userChoice = Integer.parseInt(userInput.nextLine());
        if (userChoice >= 1 && userChoice <= 5) {
            System.out.println(quotes[userChoice - 1]);
        } else {
            System.out.println("Invalid input. Please enter a number between 1 -5.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please try again.");
    }
        System.out.println("\nThank you!");
       something = moreOptions(userInput);
}

        userInput.close();

    }

    public static boolean moreOptions(Scanner userInputs){
        System.out.println("\nWould you like to see another quote?\n");
        System.out.println("Press Y to see another quote.");
        System.out.println("Press N to exit.\n");


        String newChoice = userInput.nextLine().trim().toUpperCase();

        return newChoice.equals("Y");
    }
}
