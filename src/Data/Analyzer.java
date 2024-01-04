package Data;

import Tracker.Expense;
import Tracker.ExpenseManager;

import java.util.List;

public class Analyzer {
    private ExpenseManager data;
    public Analyzer(ExpenseManager data) {
        this.data = data;
    }
    public void getBasicData() {
        System.out.println("Here's some basic data on your current expenses:");
        Expense mostExpensive = findMaxExpense();
        System.out.println("Most Expensive: " + mostExpensive.getDescription() + ", $" + mostExpensive.getCost());
        Expense leastExpensive = findMinExpense();
        System.out.println("Least Expensive: " + leastExpensive.getDescription() + ", $" + leastExpensive.getCost());
        Expense mostFreqEdit = data.getMostFrequentSpend();
        System.out.println("Most Frequent Spend: " + mostFreqEdit.getDescription() + ", " + data.getEditAmount(mostFreqEdit) + " times.");
        System.out.println("Total Expense costs: $" + totalExpenseCosts());
    }
    private Expense findMaxExpense() {
        List<Expense> expenses = data.getListOfExpenses();
        Expense mostExpensive = new Expense("non-existent", 0.0);
        double maxCost = -1;
        for (Expense e : expenses) {
            if (e.getCost() > maxCost) {
                maxCost = e.getCost();
                mostExpensive = e;
            }
        }
        return mostExpensive;
    }
    private Expense findMinExpense() {
        List<Expense> expenses = data.getListOfExpenses();
        Expense mostExpensive = new Expense("non-existent", 0.0);
        double maxCost = Integer.MAX_VALUE;
        for (Expense e : expenses) {
            if (e.getCost() < maxCost) {
                maxCost = e.getCost();
                mostExpensive = e;
            }
        }
        return mostExpensive;
    }

    private double totalExpenseCosts () {
        List<Expense> expenses = data.getListOfExpenses();
        double expenseSum = 0;
        for (Expense e : expenses) {
            expenseSum += e.getCost();
        }
        return expenseSum;
    }
}
