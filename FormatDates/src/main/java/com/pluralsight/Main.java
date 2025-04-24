package com.pluralsight;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.SimpleTimeZone;

public class Main {
    public static void main(String[] args) {


        LocalDate date1 = LocalDate.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(date1.format(format1));
        System.out.println(date1);




        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        System.out.println(date1.format(format2));


        // Create a ZoneDateTime variable for the time zone.
        // In the parentheses, Identify the specific time zone.
        ZonedDateTime newTime = ZonedDateTime.now(ZoneId.of("GMT"));
        // EEEE shows the full day written out.
        LocalDateTime date2 = LocalDateTime.now();
        // Add "Z" optionally to show which timezone you are referring to in the print statement
        DateTimeFormatter format3 = DateTimeFormatter.ofPattern("EEEE" + ", " +  "MMM d, yyyy h:mm");
        System.out.println(newTime.format(format3));


        // Another way of showing the GMT time, but using "z" to display the word GMT will not be allowed.
        LocalDateTime todayAgain = LocalDateTime.now(ZoneId.of("GMT"));
        System.out.println(todayAgain.format(format3));






        // Split the different parts of the date
        // Create a tool for the format --> Create a tool that creates the date as a string -->
        // Create a tool for splitting
        // Print and define the split parts in your statement.
        // 3 M's (MMM) shows the month in abbreviated form.
        DateTimeFormatter format4 = DateTimeFormatter.ofPattern("HH:mm dd-MMM-yyyy");
        String bonusFormat = date2.format(format4);
        String[] dateParts = bonusFormat.split(" ");
        System.out.println(dateParts[0] + " on " + dateParts[1]);

        
        
        
        
        
        
        


    }
}