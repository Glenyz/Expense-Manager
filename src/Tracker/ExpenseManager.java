package Tracker;

import java.io.*;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses;
    private Map<Expense, Integer> editFrequency;
    private String owner;
    public ExpenseManager() {
        this.expenses = new ArrayList<>();
        this.editFrequency = new HashMap<>();
    }
    public ExpenseManager(String owner) {
        this.expenses = new ArrayList<>();
        this.editFrequency = new HashMap<>();
        this.owner = owner;
    }
    public void readExpensesFromFile() {
        try {
            BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\glenz\\IdeaProjects\\Expense Tracker\\Expense Data\\data.txt"));
            String desc;
            double cost;
            while ((desc = read.readLine()) != null) {
                cost = Double.parseDouble(read.readLine());
                addExpense(desc,cost);
            }
            read.close();
        } catch (NullPointerException | FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeExpensesToFile() {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("C:\\Users\\glenz\\IdeaProjects\\Expense Tracker\\Expense Data\\data.txt"));

            for (Expense e : this.expenses) {
                write.write(e.getDescription() + "\n");
                write.write(e.getCost() + "\n");
            }
            write.close();
        } catch (NullPointerException | FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean addExpense(String desc, double cost) {
        for (Expense e : this.expenses) {
            if (e.getDescription().equals(desc)) {
                return false;
            }
        }
        return this.expenses.add(new Expense(desc, cost));
    }
    public boolean deleteExpense(String desc) {
        for (Expense e : this.expenses) {
            if (e.getDescription().equals(desc)) {
                Expense match = new Expense(e.getDescription(), e.getCost());
                return this.expenses.remove(match);
            }
        }
        return false;
    }
    public boolean deleteAllExpenses() {
        for (int i = 0; i < this.expenses.size();) {
            this.expenses.remove(0);
        }
        return true;
    }
    public boolean editExpense(String desc, double newValue) {
        if (expenseExists(desc)) {
            for (Expense e : this.expenses) {
                if (e.getDescription().equals(desc)) {
                    e.setCost(newValue);
                    if (this.editFrequency.get(e) == null) {
                        this.editFrequency.put(e, 1);
                    } else {
                        int increment = this.editFrequency.get(e) + 1;
                        this.editFrequency.put(e, increment);
                    }
                    return true;
                }
            }
        }
        System.out.println("Expense not found.");
        return false;
    }
    public boolean expenseExists(String desc) {
        for (Expense e : this.expenses) {
            if (e.getDescription().equals(desc)) {
                return true;
            }
        }
        return false;
    }
    public void getExpenses() {
        if (this.expenses.isEmpty()) {
            System.out.println("No expenses found (use \"add\" to add expenses to the manager).");
        } else {
            System.out.println("Here are all your expenses:");
            for (Expense e : this.expenses) {
                System.out.println("Description: " + e.getDescription());
                System.out.println("Cost: " + e.getCost());
                System.out.println();
            }
        }
    }

    public List<Expense> getListOfExpenses () {
        return new ArrayList<>(this.expenses);
    }
    public Expense getMostFrequentSpend() {
        Expense mostFrequentlyEdited = new Expense("non-existent", 0.0);
        if (this.editFrequency.isEmpty()) {
            return mostFrequentlyEdited;
        }
        int maxEdits = -1;
        for (int i = 0; i < this.editFrequency.size(); i++) {
            if (editFrequency.get(expenses.get(i)) > maxEdits) {
                maxEdits = editFrequency.get(expenses.get(i));
                mostFrequentlyEdited = expenses.get(i);
            }
        }
        return mostFrequentlyEdited;
    }
    public int getEditAmount (Expense reference) {
        if (this.editFrequency.get(reference) == null) {
            return 0;
        }
        return this.editFrequency.get(reference);
    }

}







