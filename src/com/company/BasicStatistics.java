package com.company;

import java.util.Arrays;
import java.lang.Math;

import static com.company.ProjectConstants.*;

public class BasicStatistics {

    // FIELDS ---------------------------------------------------------------------------------
    private double data[] = new double[MAXDATA];
    private double mode[] = new double[MAXDATA];
    private double displayingSortedDataArray[] = new double[MAXDATA];
    private double median;
    private int numDataItems;
    private int numModes;
    private String userFileName;
    private double average;
    private double medianValueOne;
    private double medianValueTwo;
    private double roundedAverage;
    private double roundedMedian;
    private boolean arraySorted;

    // CONSTRUCTOR FOR numDataItems ---------------------------------------------------------------------------------

    public void BasicStatistics() {
        numDataItems = 0;
    }

    // METHODS ---------------------------------------------------------------------------------

    // SETTING THE USER FILE NAME FOR LATER USE
    public void setUserFileName(String userFileName) {
        this.userFileName = userFileName;
    }

    public int addItem(int data2) {

        // CHECKING IF THE DATA IS WITHIN THE PARAMETERS OF THE PROJECT

        if (data2 >= MINVALUE && data2 <= MAXVALUE) {

            // CHECKING IF THE MAXIMUM NUMBER OF DATA ITEMS HAS ALREADY BEEN SURPASSED

            if (numDataItems >= MAXDATA) {
                System.out.println(SEPARATOR);
                System.out.println("\tWARNING: You cannot store more than 100 values in the array!  Given line has not been added to the array!");
                System.out.println(SEPARATOR);

                // ADDING VALUES TO THE ARRAY

            } else {
                data[numDataItems] = data2;
                numDataItems++;
                System.out.println("SUCCESS: Value " + data2 + " has been added to the array.");
            }

            // IF THE DATA IS NOT WITHIN THE PARAMETERS OF THE ARRAY

        } else {
            System.out.println(SEPARATOR);
            System.out.println("\tWARNING: Item " + data2 + " has not been added to the array.  Values must be between 1 and 50!");
            System.out.println(SEPARATOR);
        }

        return SUCCESS;
    }

    public double checkForBlankArray() {

        // MESSAGE IF THE FIRST POSITION OF THE ARRAY IS ZERO, MEANING THERE IS NOTHING IN THE ARRAY WHICH WILL KILL THE PROGRAM

        if (data[0] == 0) {
            System.out.println("\tCRITICAL ERROR: FILE WITH NO INTEGERS GIVEN!");
            System.out.println("\tCRITICAL ERROR: EXITING PROGRAM!");
            System.out.println(SEPARATOR);
            System.out.println("\tERROR CODE 141: FILE WITH NO INTEGERS GIVEN!");
            System.out.println("\tPLEASE ADD INTEGERS OR SELECT A DIFFERENT FILE!");
            System.out.println(SEPARATOR);
            for (int k = 0; k < MAXDATA; k++) {
                data[k] = FAILIURE;
            }
            System.out.println("\tCRITICAL ERROR: " + Arrays.toString(data));
            System.out.println(SEPARATOR);
            System.out.println("\tCRITICAL ERROR: FILE WITH NO INTEGERS GIVEN");
            System.out.println("\tCRITICAL ERROR: EXITING PROGRAM!");
            System.out.println(SEPARATOR);
            System.out.println("\t💀");
            System.out.println(SEPARATOR);
            System.exit(0);

        }
        return FAILIURE;
    }

    public double calcAverage() {

        double sum = 0;

        // CALCULATING THE AVERAGE IF THE ARRAY IS ALREADY SORTED

        if (arraySorted) {
            for (int i = 0; i < numDataItems; i++) {
                sum += data[i + (MAXDATA - numDataItems)];
            }

            // CALCULATING THE AVERAGE OF THE ARRAY IF IT IS NOT SORTED

        } else if (!arraySorted) {
            for (int i = 0; i < numDataItems; i++) {
                sum += data[i];
            }
        }
        average = sum / numDataItems;

        roundedAverage = Math.round(average * 100.0) / 100.0;
        return roundedAverage;
    }

    public double calcMedian() {

        // SORTING THE ARRAY

        sortDataArray();
        if ((MAXDATA - numDataItems) % 2 == 0) {
            medianValueOne = data[MAXDATA - numDataItems / 2];
            medianValueTwo = data[(MAXDATA - (numDataItems + 2) / 2)];
            median = (medianValueOne + medianValueTwo) / 2;
        } else {
            median = data[MAXDATA - (numDataItems + 1) / 2];
        }

        // ROUNDING THE MEDIAN

        roundedMedian = Math.round(median * 10.0) / 10.0;

        return roundedMedian;
    }

    public int[] calcMode() {

        // LOCAL VARIABLES

        int count = INVALID;
        sortDataArray();
        int tempModeIntegers[] = new int[MAXDATA];
        int countTimes = 0;
        int previousPosition = INVALID;

        // CLEARING THE ARRAYS

        for (int c = 0; c < MAXDATA; c++) {
            tempModeIntegers[c] = 0;
            mode[c] = 0;
        }

        // FIRST FOR LOOP
        for (int j = 0; j < numDataItems; j++) {

            int maxAppearances = 0; // TEMP COUNT STORAGE

            // CHECKING IF THE FOUND MODE IS THE SAME AS THE PREVIOUSLY ENTERED MODE IN THE LOOP

            if (data[j + (MAXDATA - numDataItems)] == previousPosition) {
                tempModeIntegers[countTimes] = 0;

                // THEN DO THE REST OF THE CODE IF IT IS NOT

            } else {

                // CHECKING IF EQUAL AND INCREMENTING TEMPCOUNT ACCORDINGLY

                for (int i = 0; i < numDataItems; i++) {
                    if (data[i + (MAXDATA - numDataItems)] == data[j + (MAXDATA - numDataItems)]) {
                        maxAppearances++;
                    }
                }

                // CHECKING IF THE COUNTER TIMES IS LESS THAN THE MAX NUMBER OF MODES

                // IF THE MAX NUMBER OF APPEARANCES EQUALS THE COUNTER
                // IF SO, POSITION IN ARRAY WILL BE SET AND COUNTTIMES INCREMENTED

                if (maxAppearances == count) {
                    tempModeIntegers[countTimes] = (int) data[j + (MAXDATA - numDataItems)];
                    previousPosition = tempModeIntegers[countTimes];
                    countTimes++;

                    // IF THE MAXAPPEARANCES EQUALS THE CURRENT MAX COUNTER

                } else if (maxAppearances > count) {

                    // CLEARING THE MODE ARRAY
                    for (int k = 0; k < MAXDATA; k++) {
                        tempModeIntegers[k] = 0;
                    }

                    // ADDING VALUES TO THE ARRAY AND RESETTING COUNTTIMES
                    countTimes = 0;
                    tempModeIntegers[countTimes] = (int) data[j + (MAXDATA - numDataItems)];
                    previousPosition = tempModeIntegers[countTimes];
                    countTimes++;
                    count = maxAppearances;
                }
            }
        }

        // IF COUNTTIMES IS GREATER THAN MAXNUMMODES

        if (countTimes >= MAXNUMMODES) {
            System.out.println(SEPARATOR);
            System.out.println("\tCRITICAL ERROR: PROGRAM CAN ONLY STORE FILES WITH 5 MODES!");
            System.out.println("\tCRITICAL ERROR: EXITING PROGRAM!");
            System.out.println(SEPARATOR);
            System.out.println("\tERROR CODE 337: FILE WITH MORE THAN 5 MODES GIVEN!");
            System.out.println("\tPLEASE EDIT THE FILE TO CONTAIN 5 OR LESS MODES OR SELECT A DIFFERENT FILE!");
            System.out.println(SEPARATOR);
            for (int k = 0; k < MAXDATA; k++) {
                tempModeIntegers[k] = FAILIURE;
            }
            System.out.println("\tCRITICAL ERROR: " + Arrays.toString(tempModeIntegers));
            System.out.println(SEPARATOR);
            System.out.println("\tCRITICAL ERROR: PROGRAM CAN ONLY STORE FILES WITH 5 MODES!");
            System.out.println("\tCRITICAL ERROR: EXITING PROGRAM!");
            System.out.println(SEPARATOR);
            System.out.println("\t💀");
            System.out.println(SEPARATOR);
            System.exit(0);
        }


        // SETTING THE NUMBER OF MODES

        numModes = countTimes;

        // ADJUSTING THE NUMBER OF MODES IN THE EVENT THAT IT IS SOMEHOW GREATER THAN 5

        if (numModes > MAXNUMMODES) {
            numModes = 0;
        }

        for (int k = 0; k < MAXDATA; k++) {
            mode[k] = tempModeIntegers[k];
        }

        return tempModeIntegers;
    }

    public void dataArrayClear() {

        // CLEARING THE ARRAYS IF THE USER WISHES TO ANALYZE THE DATA OF A NEW FILE
        for (int i = 0; i < MAXDATA; i++) {
            data[i] = 0;
            displayingSortedDataArray[i] = 0;
            numDataItems = 0;
            arraySorted = false;
        }

        for (int i = 0; i < MAXNUMMODES; i++) {
            mode[i] = 0;
        }
    }

    private void sortDataArray() {

        // SORTING THE ARRAY

        Arrays.sort(data);
        arraySorted = true;
    }

    // HOWEVER MANY CHARACTERS ARE ON LAST LINE FOR UNSORTED ARE ON NEW LINE FOR DISPLAYED


    public void displayStatistics() {

        // DISPLAYING THE USER FILE NAME

        System.out.println("File name: " + userFileName);
        System.out.println();

        // DISPLAYING THE STATISTICS --> CALLING THE DISPLAYDATA METHOD

        displayData();

        // CALCULATING THE AVERAGE AND THE MEDIAN

        calcAverage();
        calcMedian();

        // DISPLAYING THE DATA COUNT, AVERAGE AND THE MEDIAN

        System.out.println("Data Count: " + numDataItems);
        System.out.println();
        System.out.println("Mean: " + roundedAverage);
        System.out.println();
        System.out.println("Median: " + roundedMedian);
        System.out.println();

        // CALCULATING AND DISPLAYING THE NUMBER OF MODES, AND MODES

        calcMode();
        System.out.printf("%5s", "Number of modes: ");
        System.out.printf("%5s", +numModes); // DISPLAYING THE NUMBER OF MODES
        System.out.println("\n");

        // DISPLAYING THE MODES

        System.out.printf("%5s", "Modes: ");
        for (int i = 0; i < MAXNUMMODES; i++) {
            if (mode[i] == 0) {
                break;
            }
            System.out.printf("%5s", +(int) mode[i]);
        }
        System.out.println();
    }

    public void displayData() {
        System.out.println("Items stored in the data array:");
        System.out.println();

        // DISPLAY --> IF THE ARRAY IS SORTED
        if (arraySorted) {

            // COPYING THE DATA FROM THE DATA ARRAY TO A NEW ARRAY
            // THIS ELIMINATED ANY POSITIONAL ISSUES WITH THE ARRAY

            for (int j = 0; j < numDataItems; j++) {
                displayingSortedDataArray[j] = data[j + (MAXDATA - numDataItems)];
            }

            // DISPLAYING THE NEWLY COPIED ARRAY

            for (int i = 0; i < numDataItems; i++) {
                if (displayingSortedDataArray[i] == 0) {
                    break;
                }
                System.out.printf("%5s", (int) displayingSortedDataArray[i]);
                if (i % 10 == 9) {
                    System.out.println();
                }
            }

            // DISPLAYING THE DATA IF THE ARRAY IS NOT SORTED

        } else if (!arraySorted) {
            for (int i = 0; i < numDataItems; i++) {
                if (data[i] == 0) {
                    break;
                }
                System.out.printf("%5s", (int) data[i]);
                if (i % 10 == 9) {
                    System.out.println();
                }
            }
        }

        // ADDITIONAL ENTER KEY FOR GOOD MEASURE

        System.out.println("\n");
    }

    public void displayMode() {

        // SORTING THE DATA ARRAY

        sortDataArray();

        // CALCULATING THE MODE

        calcMode();

        // DISPLAYING THE NUMBER OF MODES AND THE MODES

        System.out.printf("%5s", "Number of modes: ");
        System.out.printf("%5s", +numModes);
        System.out.println("\n");
        System.out.printf("%5s", "Modes: ");
        for (int i = 0; i < MAXNUMMODES; i++) {
            if (mode[i] == 0) {
                break;
            }
            System.out.printf("%5s", +(int) mode[i]);
        }
        System.out.println();
    }
} // END BasicStatistics CLASS

