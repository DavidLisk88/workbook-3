package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SearchEngineLogger {

    // I created static variables to initialize things I will be using in other methods.
    static String searchFile = "src/main/resources/logs.txt";
    static Scanner userInput = new Scanner(System.in);
    static ZonedDateTime zoneDate = ZonedDateTime.now(ZoneId.of("GMT"));
    static DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss");
    static String time = zoneDate.format(dateFormat1);



    // MAIN METHOD
    public static void main(String[] args) {

        // Start with a prompt to see if user wants to start logging. This will not be logged.
       while(true) {
           System.out.println("Hello there, friend...");
           waiting();
           System.out.println("Would you like to start the program or exit? ");
           System.out.println("Y or N");

           String userChoice1 = userInput.nextLine();

           switch (userChoice1.toUpperCase()){
               case "Y":
                   System.out.println("\nTaking you to the application...");
                   waiting();
                   mainApp();
                   break;
               case "N":
                   System.out.println("Okay, thank you for your time.");
                    userInput.close();
                    return;
           }
       }
    }



    // THIS METHOD IS FOR THE USER TO LOG A SEARCH OR LOG AN EXIT OF THE APPLICATION
    public static void mainApp(){

        // Since the user launched the app, we will be brought to the logActions method.
        // The logActions method will write down the text "Launch".
        logActions("Launch");

        // Use a loop for the user search so that the program does not stop after a user logs stuff
        // the user will exit the application and "exit" will be logged if they choose "x".

        while(true){
            System.out.println("---------------------------------");
            System.out.println("Search for a term (or X to exit)");
            String userSearch = userInput.nextLine();

            if (userSearch.equalsIgnoreCase("x")){
                System.out.println("Taking you back...");
                System.out.println("---------------------------------");
                waiting();
                logActions("exit");
                break;
            } else{
                logActions("\nYour Search: " + userSearch);
            }
        }
    }




    // THIS METHOD WILL LOG THE ACTIONS OF THE USER FROM THE METHOD ABOVE.
    public static void logActions(String theAction){
        // Since the logged action of the user is a string, we will initialize a string variable called "theAction" in the parentheses.

        // I make a new variable for the file located in the searchfile stream.
        File logFile = new File(searchFile);

        // Adding "true" next to the file means that you are appending the logs.
        // appending the logs just means each log will be logged below the previous one.
        // adding true means the old information stays preserved.
    try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(logFile, true))) {

       // Tell the Buffered Writer to write the time variable, amd the logged action of the user.
       writer1.write(time + "  ------>  " + theAction);
       // this first new line is for the log
       writer1.newLine();
       // This second new line will be blank.
       writer1.newLine();

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}






    // THIS METHOD IS FOR WAITING IN BETWEEN PROMPTS FOR USER EXPERIENCE.
    public static void waiting(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            System.out.println("Something went wrong while loading...");
        }
    }

}
