package Tracker;

import Data.Analyzer;

import java.util.Scanner;

// Loop till exit
public class Menu {

    /**
     * Main body of manager. Takes commands from user to track expenses.
     * Program loops until user indicates to exit program.
     *
     */
    public static void startUp() {
        // Creates new manager (can implement multiple managers for different users later).
        ExpenseManager manager = new ExpenseManager();
        boolean exitProgram = false;

        manager.readExpensesFromFile();

        System.out.println();
        System.out.println("Hello and welcome to your personal expense manager!\n" +
                           "This program was designed to keep track of all of your expenses " +
                           "at one place in an efficient and simple manner.\n" +
                           "Enter \"help\" for a list of commands to get started!\n");
        // Check that a command was entered.
        while (!exitProgram) {
            String command = bufferReadLine();
            // Check which command was entered.
            switch (command.toLowerCase()) {
                case "add","add expense": {
                    System.out.println("Enter expense name: ");
                    String tempDesc = bufferReadLine();
                    System.out.println("Enter expense amount: ");
                    String tempCost = bufferReadLine();
                    double doubleCost;
                    try {
                        doubleCost = Double.parseDouble(tempCost);
                        isCommandSuccessful(manager.addExpense(tempDesc, doubleCost), "added");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;
                }
                case "view", "view expenses", "view expense": {
                    manager.getExpenses();
                    break;
                }
                case "delete expense", "delete": {
                    System.out.println("Enter description of expense to delete: ");
                    String tempDesc = bufferReadLine();
                    isCommandSuccessful(manager.deleteExpense(tempDesc), "deleted");
                    break;
                }
                case "delete all": {
                    System.out.println("Are you sure? (yes/no)");
                    if (bufferReadLine().equals("yes"))
                    isCommandSuccessful(manager.deleteAllExpenses(), "all deleted");
                    break;
                }
                case "edit": {
                    System.out.println("Enter description of expense to edit: ");
                    String tempDesc = bufferReadLine();
                    System.out.println("Enter new expense cost: ");
                    String tempCost = bufferReadLine();
                    double doubleCost;
                    try {
                        doubleCost = Double.parseDouble(tempCost);
                        isCommandSuccessful(manager.editExpense(tempDesc, doubleCost), "edited");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;
                }
                case "basic", "basic data": {
                    Analyzer basicData = new Analyzer(manager);
                    basicData.getBasicData();
                    break;
                }
                case "advanced", "advanced data": {
                    //Analyzer basicData = new Analyzer(manager);
                    //basicData.getAdvancedData();
                    System.out.println("WIP.");
                    break;
                }
                case "help": {
                    System.out.println("Commands: \n" +
                            "\"How to use\": Introduces the manager.\n" +
                            "\"Add\": Adds an expense to the expense manager.\n" +
                            "\"Delete\": Choose the expense you want to delete from the manager.\n" +
                            "\"Delete all\": Wipes all data from manager (including local save).\n" +
                            "\"View\": Displays all expense currently in the manager.\n" +
                            "\"Edit\": Edit an existing expense's cost.\n" +
                            "\"Help2\": Page 2 of commands.");

                    break;
                }
                case "help2": {
                    System.out.println("Commands: \n" +
                                       "\"Basic data\": Displays some basic stats about your expenses.\n" +
                                       "\"Advanced data\": Work in progress." +
                                       "\"Exit\": Exit the program.\n");
                    break;
                }
                case "how to use", "how", "how to": {
                    System.out.println("This program aims to help you organize your expense through " +
                                       "some simple commands.\nYou can add, delete, or view your expenses in your manager.\n" +
                                       "Of course the program can do much more, but that's just the basics.\n" +
                                       "This will help you keep track of " +
                                       "what your current expenses are and can provide some data to visualize " +
                                       "your current expenses.");
                    break;
                }
                case "thanks", "thank you":
                    System.out.println("No problem!");
                    break;
                case "exit": {
                    exitProgram = true;
                    System.out.println("Exiting...");
                    manager.writeExpensesToFile();
                    break;
                }
                default: {
                    System.out.println("Unknown command, please try again.");
                    break;
                }
            }
        }
    }

    /**
     * Buffer for reading user console input.
     *
     * @return string of a non-empty/non-null command.
     */
    private static String bufferReadLine() {
        String command = null;
        Scanner bufferScanner = new Scanner(System.in);
        boolean commandDetected = false;
        while (!commandDetected) {
            command = bufferScanner.nextLine();
            if (command != null && !command.isEmpty()) {
                commandDetected = true;
            }
        }
        return command;
    }
    private static double bufferDoubleEntry() {
        String command = null;
        double num = 0;
        Scanner doubleScanner = new Scanner(System.in);
        boolean commandDetected = false;
        while (!commandDetected) {
            command = doubleScanner.nextLine();
            try {
                num = Double.parseDouble(command);
                commandDetected = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Please");
            }
        }
        return num;
    }
    private static void isCommandSuccessful(boolean success, String command) {
        if (success) {
            System.out.println("Expense(s) " + command + " successfully.");
        } else {
            System.out.println("Expense(s) " + command + " unsuccessfully (expense already exists/doesn't exist).");
        }
    }
}
