/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ca1nichitaselchin;

/* sba24331@student.cct.ie / Nichita Selchin
 * 
 * When in opera on, the program will be given a file named “customers.txt” – this contains the details of 
 * (fic ous) customers in the following format:  
 * Line 1 – <First Name> <Second Name> 
 * Line 2 – <Total Purchase> 
 * Line 3 – <Class>≠ 
 * Line 4 - <Last Purchase> 
 * Your task is to:  
 * 1) Read the data from the file and check that it is valid. The file may contain more than one customer, 
 * so an appropriate loop should be used. The data must obey the following rules:  
 * a) the first name must be le ers only;  
 * b) The second name can be le ers and/or numbers and must be separated from the first 
 * name by a single space;  
 * c) the value of purchase of classes must be double and  
 * d) the Class  must be a integer between 1 to 3. 
 * e) Last purchase must be a valid year. 
 * 2) If the data is not valid, you should output a useful error message on screen to the user. 
 * 3) If the data is valid, then you have to calculate the discount to the net value and save a file named 
 * customerdiscount.txt, in the following format: 
 * First name> - <Second Name> 
 * <Final Value> 
 * Where the <Final Value> is determined by the number of classes
 *
 *
 *
 * https://github.com/Nichita777/CA1NichitaSelchin  link to GitHub
 * @author nichi
 */
import java.io.*;

public class CA1NichitaSelchin {

    // Main execution point
    public static void main(String[] args) {
        // Print the curent working directory to help locate the files
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        // Define input and output file paths
        String inputFilePath = "customers.txt";
        String outputFilePath = "customerdiscount.txt";
        // process the input file to calculate and write discounts
        processCustomerFile(inputFilePath, outputFilePath);
    }

    // Validate firstname 
    public static boolean validateFirstName(String firstName) {
        return firstName.matches("[a-zA-Z]+");
    }

    // Validate secondname 
    public static boolean validateSecondName(String secondName) {
        return secondName.matches("[a-zA-Z0-9]+");
    }

    // Validate purchase 
    public static boolean validatePurchase(String purchase) {
        try {
            Double.parseDouble(purchase);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate class 
    public static boolean validateClass(String customerClass) {
        try {
            int cls = Integer.parseInt(customerClass);
            return cls >= 1 && cls <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate year 
    public static boolean validateYear(String year) {
        try {
            int yr = Integer.parseInt(year);
            return yr >= 1900 && yr <= 2024;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    // Calculate discount based on class and last purchase year

    public static double calculateDiscount(double purchase, int customerClass, int lastPurchase) {
        int currentYear = 2024;

        switch (customerClass) {
            case 1:
                if (lastPurchase == currentYear) {
                    return purchase * 0.70; // 30% discount
                } else if (lastPurchase < currentYear && lastPurchase >= currentYear - 5) {
                    return purchase * 0.80; // 20% discount
                } else {
                    return purchase * 0.90; // 10% discount
                }
            case 2:
                if (lastPurchase == currentYear) {
                    return purchase * 0.85; // 15% discount
                } else if (lastPurchase < currentYear && lastPurchase >= currentYear - 5) {
                    return purchase * 0.87; // 13% discount
                } else {
                    return purchase * 0.95; // 5% discount
                }
            case 3:
                if (lastPurchase == currentYear) {
                    return purchase * 0.97; // 3% discount
                } else {
                    return purchase; // No discount
                }
            default:
                return purchase;
        }
    }
    // Main method to process the file and apply discounts

    public static void processCustomerFile(String inputFilePath, String outputFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath)); BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {

            String firstName, secondName, purchase, customerClass, lastPurchase;

            while ((firstName = br.readLine()) != null) {
                System.out.println("Processing customer: " + firstName);  // Debugging: Show customer being processed
                String[] nameParts = firstName.split(" ");
                if (nameParts.length != 2) {
                    System.out.println("Error: Invalid name format for '" + firstName + "'");
                    continue;
                }

                secondName = nameParts[1];
                firstName = nameParts[0];
                purchase = br.readLine();
                customerClass = br.readLine();
                lastPurchase = br.readLine();

                System.out.println("Purchase: " + purchase + ", Class: " + customerClass + ", Last Purchase: " + lastPurchase); // Debugging

                // Validate inputs
                if (!validateFirstName(firstName)) {
                    System.out.println("Error: Invalid first name '" + firstName + "'");
                    continue;
                }
                if (!validateSecondName(secondName)) {
                    System.out.println("Error: Invalid second name '" + secondName + "'");
                    continue;
                }
                if (!validatePurchase(purchase)) {
                    System.out.println("Error: Invalid purchase value '" + purchase + "'");
                    continue;
                }
                if (!validateClass(customerClass)) {
                    System.out.println("Error: Invalid class '" + customerClass + "'");
                    continue;
                }
                if (!validateYear(lastPurchase)) {
                    System.out.println("Error: Invalid last purchase year '" + lastPurchase + "'");
                    continue;
                }
                // Calculate the final value after discount
                double finalValue = calculateDiscount(Double.parseDouble(purchase), Integer.parseInt(customerClass), Integer.parseInt(lastPurchase));

            }catch (IOException e) {
            System.out.println("Error processing the file: " + e.getMessage());
        }
        }

    }
