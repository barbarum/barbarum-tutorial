package com.barbarum.tutorial.hacker.tutorial.day0;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;


public class DataType {

    public static void main(String[] args) {
        int i = 4;
        double d = 4.0;
        String s = "HackerRank ";

        Scanner scan = new Scanner(System.in);

        /* Declare second integer, double, and String variables. */

        /* Read and save an integer, double, and String to your variables.*/
        // Note: If you have trouble reading the entire String, please go back and review the Tutorial closely.

        /* Print the sum of both integer variables on a new line. */

        /* Print the sum of the double variables on a new line. */

        /* Concatenate and print the String variables on a new line;
            the 's' variable above should be printed first. */

        int iplus = scan.nextInt();
        double dplus = scan.nextDouble();

        // Return the reset of current line;
        scan.nextLine();
        String splus = scan.nextLine();

        System.out.println(i + iplus);
        System.out.println(new BigDecimal(d + dplus).setScale(1, BigDecimal.ROUND_HALF_UP).toString());
        System.out.println(s + splus);

        scan.close();
    }
}