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

        String employeeCsvFile = "src/main/resources/employees.csv";
        String employeeJsonFile = "src/main/resources/employees-new.json";

       List<Employee> employeeList = readAllEmployees(employeeCsvFile);
    //    writeALlEmployees(employeeList, employeeJsonFile);

      while(true) {
          System.out.println("Hello..");
          waiting();
          System.out.println("\n----------Please select an option------------");
          System.out.println("1. Display all employees");
          System.out.println("2. Copy CSV file.");
          System.out.println("3. Write to JSON file");
          System.out.println("4. Exit\n");
          String userChoice = userInput.nextLine().toLowerCase();

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

    public static void copyNewCsv(List<Employee> employees, String output){
        try {
            File newFile = new File(output);
            BufferedWriter csvWriter = new BufferedWriter((new FileWriter(newFile)));

            for (Employee emp : employees){
                csvWriter.write(String.format("%d | %s | %.2f | $%.2f\n", emp.getEmployeeId(), emp.getName(), emp.getHoursWorked(), emp.getGrossPay()));
            }

            csvWriter.close();
            System.out.println("Yur file is saved...\n" + output);

        } catch (IOException e){
            System.out.println("Something went wrong...");
            waiting();
        }
    }

    public static void displayEmployees(List<Employee> employees){
        for(Employee emp : employees){
            System.out.printf("ID: %d | Name: %s | Hours: %.2f | Gross Pay: $%.2f\n", emp.getEmployeeId(), emp.getName(), emp.getHoursWorked(), emp.getGrossPay());
        }
    }

    public static List<Employee> readAllEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] employeeListSplitter = line.split("\\|");

                int id = Integer.parseInt(employeeListSplitter[0]);
                String name = employeeListSplitter[1];
                double hoursWorked = Double.parseDouble(employeeListSplitter[2]);
                double payRate = Double.parseDouble(employeeListSplitter[3]);

                employees.add(new Employee(id, name, hoursWorked, payRate));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }


    public static void writeAllEmployees(List<Employee> employees, String output){
        JSONArray jsonArray  = new JSONArray();

        for (Employee emp : employees) {
            //initialize json object
            JSONObject jsonObject = new JSONObject();
            //adds employee data to json object
            jsonObject.put("id",emp.getEmployeeId());
            jsonObject.put("name",emp.getName());
            jsonObject.put("grossPay",emp.getGrossPay());
            //adds json object to array
            jsonArray.put(jsonObject);
        }
        try(BufferedWriter writingJson = new BufferedWriter(new FileWriter(output))){
            writingJson.write(jsonArray.toString(4));
            System.out.println("Created new file: " + output);
        }
        catch (IOException e){
            System.out.println("Error writing new file.");
            throw new RuntimeException(e);
        }
        System.out.println("Full file path: " + output);
        waiting();
        userInput.close();
    }





 /*   public static void payroll(String filePath) {


        // this try statement starts by telling the output to read the file line by line by using the buffer reader.
        // Again, since we are doing a new thing, we make a new tool (variable) called "employeeReader".
        try (BufferedReader employeeReader = new BufferedReader(new FileReader(filePath))) {

            // this tells the bufferreader to start reading.
            // Gets rid of the header row
            // as we call "eats the line"
            //   employeeReader.readLine();

            // I will create a new variable that is a tool for holding the lines of each employee.
            String employeeLines;

            // This loop will say "As long as the lines are not empty, keep reading lines.
            // I set the employeeLines equal to the reader because the reader is reading the employee lines.
            while ((employeeLines = employeeReader.readLine()) != null) {

                // Create a splitter for the employee lines.
                // since I am using a new tool, I am creating a new variable.
                // I split the employee line by the pipe.
                String[] employeeListSplitter = employeeLines.split("\\|");

                // Another way of skipping the header row.
                // this is saying that if
                if (employeeListSplitter[0].equals("id")){
                    continue;
                }

                // Re-define each element of the employee and use the splitter to determine which element is which part of the string.
                // If it's not a string already, we use the parser to convert.



                /*
                 Now that I just read the file, I have to create a new employee.
                 I am basically saying that we put the employee in the blender and when we take it out,
                 it's a new employee with the same information.

                Employee listedEmployee = new Employee(id, name, hoursWorked, payRate);

                // I am adding the new employees that I just created to the array that I created at the top of the main method.
                // This is like putting a pile of labels in a basket before sticking them onto a new product.
                employeeList.add(listedEmployee);


                // Now we will display the new employee using formatting and also using the getters to get each piece of info from the employees.
                System.out.printf("ID: %d | Name: %s | Gross Pay: $%.2f%n\n", listedEmployee.getEmployeeId(), listedEmployee.getName(), listedEmployee.getGrossPay());
            }

        } catch (FileNotFoundException e) {  // This first catch statement will catch an error if the file could not be found.
            System.out.println("File could not be found.");
            throw new RuntimeException(e);
        } catch (IOException e) { // This second catch statement will catch any other type of error when reading the file.
            System.out.println("An error has occurred.");
            throw new RuntimeException(e);
        }

    }

  */



    public static void waiting(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            System.out.println("Something went wrong while loading...");
        }
    }


}

