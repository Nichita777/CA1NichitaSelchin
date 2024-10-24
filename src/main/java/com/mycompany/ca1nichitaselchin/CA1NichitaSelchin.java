/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ca1nichitaselchin;

/**
 *
 * @author nichi
 */
public class CA1NichitaSelchin {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    import java.io.*;
import java.util.regex.*;

public class CustomerDiscount {

    // Validate first name (letters only)
    public static boolean validateFirstName(String firstName) {
        return firstName.matches("[a-zA-Z]+");
    }

    // Validate second name (letters and/or numbers)
    public static boolean validateSecondName(String secondName) {
        return secondName.matches("[a-zA-Z0-9]+");
    }
}
