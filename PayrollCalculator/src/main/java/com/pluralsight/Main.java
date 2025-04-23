package com.pluralsight;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        // Create a file pathway for the csv file
        String employeeCsvFile = "src/main/resources/employees.csv";

        // Create a file pathway for where the JSON file will go.
        String employeeJsonFile = "src/main/resources/employees-new.json";

        // This function is where we store the data that is read from the csv list.
       List<Employee> employeeList = readAllEmployees(employeeCsvFile);;

       // Create a while loop for menu options.
      while(true) {
          System.out.println("Hello..");
          waiting();
          System.out.println("\n----------Please select an option------------");
          System.out.println("1. Display all employees");
          System.out.println("2. Copy CSV file.");
          System.out.println("3. Write to JSON file");
          System.out.println("4. Exit\n");

          // Create a variable for what the user chooses and attach it to the user input.
          // It will be used for the switch statement below.
          String userChoice = userInput.nextLine().toLowerCase();

          // The switch statement stays inside the while loop and determines the users fate based on their choice.
          switch (userChoice){
              case "1":
                  displayEmployees(employeeList);
                  userInput.close();
                  return;
              case "2":
                  copyNewCsv(employeeList, "src/main/resources/employees-copy.csv");
                  userInput.close();
                  return;
              case "3":
                  writeAllEmployees(employeeList, employeeJsonFile);
                  userInput.close();
                  return;
              case "4":
                  System.out.println("Goodbye..");
                  waiting();
                  userInput.close();
                  return;
              default:
                  System.out.println("Please enter a valid choice:");
          }
      }

    }



    // THIS METHOD WILL ONLY READ THE CSV FILE FROM THE LIST WE STORED THE DATA IN ABOVE.
    public static List<Employee> readAllEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();

        // The Buffer reader and FileReader activates the reading of the csv file.
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            // Create a string variable to use for the upcoming splitter.
            String line;

            // This will eat the first line. Skipping the header
            reader.readLine();

            // Tell the reader to keep reading as long as there are lines to read.
            // But while its doing that,
            while ((line = reader.readLine()) != null) {
                // Split what you are reading by the pipe.
                String[] employeeListSplitter = line.split("\\|");

                // Now that we chopped up the meat, put a label on those different pieces of meat.
                int id = Integer.parseInt(employeeListSplitter[0]);
                String name = employeeListSplitter[1];
                double hoursWorked = Double.parseDouble(employeeListSplitter[2]);
                double payRate = Double.parseDouble(employeeListSplitter[3]);

                // Now we add a newly assembled employee to the employees storage list.
                employees.add(new Employee(id, name, hoursWorked, payRate));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    // THIS METHOD WILL PRINT THE READ THE CSV FILE.
    public static void displayEmployees(List<Employee> employees){
        // In the parentheses above, I am adding in the employee list variable that works as the storage.

        // For every Employee that we will now call "emp" that's attached to the employees list,
        for(Employee emp : employees){
            // Print out their information.
            System.out.printf("ID: %d | Name: %s | Hours: %.2f | Gross Pay: $%.2f\n", emp.getEmployeeId(), emp.getName(), emp.getHoursWorked(), emp.getGrossPay());
        }
    }



    // THIS METHOD WILL WRITE THE READ CSV FILE.
    public static void copyNewCsv(List<Employee> employees, String filePath){
        try {
            // Create a new variable for the file that we will end up writing on
            File newFile = new File(filePath);
            // setup the buffered writer and file writer
            BufferedWriter csvWriter = new BufferedWriter((new FileWriter(newFile)));

            // For each Employee we now call emp inside of the employees storage,
            for (Employee emp : employees){
                // tell the writer to write their information
                csvWriter.write(String.format("%d | %s | %.2f | $%.2f\n", emp.getEmployeeId(), emp.getName(), emp.getHoursWorked(), emp.getGrossPay()));
            }

            // close th writer
            csvWriter.close();
            // and let the user know that their file is saved.
            System.out.println("Yur file is saved...\n" + filePath);

        } catch (IOException e){
            System.out.println("Something went wrong...");
            waiting();
        }
    }




    // THIS METHOD WILL READ THE CSV FILE AND WRITE TO A JSON FILE.
    public static void writeAllEmployees(List<Employee> employees, String filePath){
        // Create a JSON array that we will store the information in
        JSONArray jsonArray  = new JSONArray();

        for (Employee emp : employees) {
            //initialize json object
            JSONObject jsonObject = new JSONObject();
            //adds employee data to json object
            // pretty much like chopping up the employee again, but in a different way.
            jsonObject.put("id",emp.getEmployeeId());
            jsonObject.put("name",emp.getName());
            jsonObject.put("grossPay",emp.getGrossPay());
            //adds json object to array
            // add in the chopped up employee in the storage.
            jsonArray.put(jsonObject);
        }
        // Now we do a regular buffered writer process.
        try(BufferedWriter writingJson = new BufferedWriter(new FileWriter(filePath))){
            // This function just adds an indent to all the written information from the array
            writingJson.write(jsonArray.toString(4));
            System.out.println("Created new file: " + filePath);
        }
        catch (IOException e){
            System.out.println("Error writing new file.");
            throw new RuntimeException(e);
        }
        System.out.println("Full file path: " + filePath);
        waiting();
        userInput.close();
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

