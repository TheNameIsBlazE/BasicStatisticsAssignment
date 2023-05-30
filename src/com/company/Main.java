package com.company;

//
// import com.sun.jmx.snmp.SnmpUnknownSubSystemException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.stream.Collectors;

import static com.company.ProjectConstants.*;

public class Main {

    // FIGURE OUT WHY THE ENTIRE ARRAY DELETES ITSELF WITH THE CASE STATEMENT HAHA

    // METHODS -------------------------------------------------------------------------------------------------------------

    // genInfo METHOD ----------------------------------------------------------

    public static void genInfo() {
        System.out.println(SEPARATOR);
        System.out.println("Hello!  This program will read the integer data form a file and then import it it into another class");
        System.out.println("The data will be put into an array and then the mean, median and mode of the data will be calculated");
        System.out.println("PLEASE NOTE: Files with only one integer value on each line will be accepted by the software!");
        System.out.println("PLEASE NOTE: Lines with any spaces will be disregarded and not included in calculations!");
    }  // END genInfo METHOD

    // ERROR MESSAGES --------------------------------------------------------------------------------------------------------------------------

    // noFile METHOD ----------------------------------------------------

    public static void noFile() {
        System.out.println(SEPARATOR);
        System.out.println("\t\tINPUT ERROR: File does not exist.  Please choose an existing file on the system!");
        System.out.println(SEPARATOR);
    } // END noFile METHOD

    // noFile METHOD ----------------------------------------------------

    public static void invalidOptionResponse() {
        System.out.println(SEPARATOR);
        System.out.println("\t\tINPUT ERROR: Entered value MUST be YES or NO!");
        System.out.println(SEPARATOR);
    } // END noFile METHOD

    // noInteger METHOD ------------------------------------------------------------------------------------

    public static void noInteger() {
        System.out.println(SEPARATOR);
        System.out.println("\t\tWARNING: Given line does not contain an integer.  This line has not been added to the array");
        System.out.println(SEPARATOR);
    }

    public static void invalidIntegerCaseStatement() {
        System.out.println("\t\tINPUT ERROR: Entered value MUST be a VALID INTEGER!!");
    } // END invalidIntegerMainMenu METHOD

    // analyticsCaseStatementMainMenu METHOD ------------------------------------------------------------------------------------

    public static void analyticsCaseStatementMainMenu() {
        System.out.println(SEPARATOR);
        System.out.println("Please enter the integer value method you would like to use:");
        System.out.println("1. displayData - Displays all of the data in the data array");
        System.out.println("2. displayStatistics - Displays the file name, the full, sorted data set in the data array, the mean, the median, and the mode/modes");
        System.out.println("3. displayMode - Displays the mode of the data set");
        System.out.println("4. Exit the menu");
    } // END analyticsCaseStatementMainMenu METHOD

    // exitProgram METHOD ----------------------------------------------------------------------------------------

    public static void exitProgram() {
        System.out.println(SEPARATOR);
        System.out.println("Thank you for using this program!");
        System.out.println("Have a good day! 😄");
        System.out.println(SEPARATOR);
    } // END exitProgram METHOD

    // MAIN METHOD ---------------------------------------------------------------

    public static void main(String[] args) throws FileNotFoundException {

        // STRINGS ----------------------------------------------------------------
        String fileName;
        String garbage;
        String response;
        String analyticsResponse;

        // INTEGERS ----------------------------------------------------------------
        int option;

        // BOOLEANS ---------------------------------------------------------------
        boolean analyticsDone;
        boolean optionDone;
        boolean fileDone;

        // SCANNERS ---------------------------------------------------------------
        Scanner s = new Scanner(System.in);

        // OBJECTS  ---------------------------------------------------------------
        BasicStatistics basicStatistics = new BasicStatistics();

        // MAIN METHOD --> CODE BEGINS HERE ------------------------------------------------------------------------------------------------------------------------------

        genInfo();

        do {
            optionDone = false;
            System.out.println(SEPARATOR);

            // ASKING THE USER IF THEY WOULD LIKE TO CONTINUE  ---------------------------------------------------------------

            System.out.println("Would you like to input a new file? (yes/no)");
            response = s.nextLine().toUpperCase();

            // IF USER ANSWERS YES FOR INITIAL RESPONSE  ---------------------------------------------------------------

            if (response.equals("YES")) {
                System.out.println(SEPARATOR);

                // CLEARING THE ARRAY
                basicStatistics.dataArrayClear();

                // ENTERING FILE NAME ---------------------------------------------------------------

                do {
                    fileDone = false;
                    System.out.println("Please enter the name of the file you want to read");
                    fileName = s.nextLine();
                    basicStatistics.setUserFileName(fileName);
                    System.out.println(SEPARATOR);

                    // FILE CODE  ---------------------------------------------------------------

                    try {

                        // DEFINING FILE ---------------------------------------------------------------
                        File f = new File(fileName + ".txt");

                        // CHECKING IF FILE EXISTS ---------------------------------------------------------------
                        if (f.exists() && !f.isDirectory()) {

                            // CREATING READERS ---------------------------------------------------------------
                            FileReader reader = new FileReader(fileName + ".txt");
                            BufferedReader bufferedreader = new BufferedReader(reader);

                            // LOCAL VARIABLE - STRING ---------------------------------------------------------------
                            String line;
                            // ADDING THE FILE DATA TO AN ARRAY ---------------------------------------------------------------
                            while ((line = bufferedreader.readLine()) != null) {
                                Scanner lineScanner = new Scanner(line);

                                // CHECKING IF THE LINE CONTAINS AN INTEGER ---------------------------------------------------------------

                                // IF THE LINE CONTAINS ANY SPACES -----------------------------------------------------------

                                if (line.contains(" ")) {
                                    noInteger();

                                    // IF THE LINE CONTAINS NO SPACES ---------------------------------------------------

                                } else {

                                    // CHECKING IF THE LINE CONTAINS AN INTEGER ---------------------------------------------------------------

                                    if (lineScanner.hasNextInt()) {
                                        line = line.replaceAll("\\s+", "");
                                        basicStatistics.addItem(Integer.parseInt(line));

                                        // IF THE LINE DOESN'T CONTAIN AN INTEGER ---------------------------------------------------------------

                                    } else {
                                        noInteger();
                                    }
                                }
                            }
                            reader.close();
                            fileDone = true;

                            // ERROR FOR IF FILE DOES NOT EXIST  ---------------------------------------------------------------

                        } else {
                            noFile();
                        }

                        // CATCH STATEMENT --> FOR TRY  ---------------------------------------------------------------

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } while (!fileDone); // END FILE CHECKING

                // ERROR MESSAGE IF THE ARRAY CONTAINS NOTHING ----------------------------------------------------
                basicStatistics.checkForBlankArray();

                // ASKING USER IF THEY WOULD LIKE TO DISPLAY STATISTICS ---------------------------------------------------------------

                do {
                    analyticsDone = false;
                    System.out.println(SEPARATOR);
                    System.out.println("Would you like to display the statistics of the entered data? (yes/no)");
                    analyticsResponse = s.nextLine().toUpperCase();

                    if (analyticsResponse.equals("YES")) {
                        analyticsCaseStatementMainMenu();
                        // CASE STATEMENT ---------------------------------------------------------------

                        if (s.hasNextInt()) {
                            option = s.nextInt();
                            System.out.println(SEPARATOR);

                            switch (option) {

                                // displayData ---------------------------------------------------------------

                                case 1: {
                                    garbage = s.nextLine();
                                    basicStatistics.displayData();
                                    break;
                                } // END CASE 1

                                // displayStatistics ---------------------------------------------------------------

                                case 2: {
                                    garbage = s.nextLine();
                                    basicStatistics.displayStatistics();
                                    break;
                                } // END CASE 2

                                case 3: {
                                    garbage = s.nextLine();
                                    basicStatistics.displayMode();
                                    break;
                                } // END CASE 3

                                case 4: {
                                    garbage = s.nextLine();
                                    analyticsDone = true;
                                    break;
                                }

                                // DEFAULT ---------------------------------------------------------------

                                default: {
                                    System.out.println(SEPARATOR);
                                    invalidIntegerCaseStatement();
                                    garbage = s.nextLine();
                                    break;
                                } // END DEFAULT STATEMENT

                            } // END SWITCH STATEMENT

                            // ELSE STATEMENT - FOR ANALYTICS DISPLAY ---------------------------------------------------------------

                        } else {
                            System.out.println(SEPARATOR);
                            invalidIntegerCaseStatement();
                            garbage = s.nextLine();
                        }

                        // IF USER ANSWERS NO FOR ANALYTICS DISPLAY ---------------------------------------------------------------

                    } else if (analyticsResponse.equals("NO")) {
                        analyticsDone = true;

                    } else {
                        invalidOptionResponse();
                    }
                } while (!analyticsDone);


                // IF USER ANSWERS NO FOR INITIAL RESPONSE  ---------------------------------------------------------------

            } else if (response.equals("NO")) {
                exitProgram();
                optionDone = true;

                // IF USER ANSWERS JUNK FOR INITIAL RESPONSE  ---------------------------------------------------------------

            } else {
                invalidOptionResponse();
            }

        } while (!optionDone);
    }
}
