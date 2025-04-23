package com.pluralsight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        // I will put a variable to the file name itself.
        String file1 = "goldilocks.txt";
        String file2 = "hansel_and_gretel.txt";
        String file3 = "mary_had_a_little_lamb.txt";
        // This will create the stream of the file input. Where is the file located?
        FileInputStream fileInput = new FileInputStream("src/main/resources/" + file1);
        FileInputStream fileInput2 = new FileInputStream("src/main/resources/" + file2);
        FileInputStream fileInput3 = new FileInputStream("src/main/resources/" + file3);
        // The scanner will be created to read the text file linn by line.
        Scanner fileScanner = new Scanner(fileInput);
        Scanner fileScanner2 = new Scanner(fileInput2);
        Scanner fileScanner3 = new Scanner(fileInput3);

while (true) {
    // We want to let the scanner know to start counting at 1.
    // But we can change this number to start counting on any line we want
    int howManyLines = 1;
    System.out.println("Which story? 1, 2, or 3.");
    String userChoice = userInput.nextLine();
    switch (userChoice){
        case "1":
            while(fileScanner.hasNextLine()){  // While the filescanner has a new line,
                // Tell that line to enter what the file scanner is reading.
                userChoice = fileScanner.nextLine();
                // and then print out whats on the line until there are no more lines.
                System.out.println(howManyLines + ": " + userChoice);
                // we just want to count up in numerical order:
                howManyLines++;
            //    fileScanner.close();
            } break;
        case "2":
            while(fileScanner2.hasNextLine()){  // While the filescanner has a new line,
                // Tell that line to enter what the file scanner is reading.
               userChoice= fileScanner2.nextLine();
                // and then print out whats on the line until there are no more lines.
                System.out.println(howManyLines + ": " + userChoice);
                // we just want to count up in numerical order:
                howManyLines++;
             //   fileScanner2.close();
            } break;
        case "3":
            while(fileScanner3.hasNextLine()){  // While the filescanner has a new line,
                // Tell that line to enter what the file scanner is reading.
                userChoice = fileScanner3.nextLine();
                // and then print out whats on the line until there are no more lines.
                System.out.println(howManyLines + ": " + userChoice);
                // we just want to count up in numerical order:
                howManyLines++;
            //    fileScanner3.close();
            } break;
    }

}





    }
}