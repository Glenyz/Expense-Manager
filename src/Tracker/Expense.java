package Tracker;

public class Expense {
    private final String  description;
    private double cost;

    public Expense(String desc, double cost) {
        this.description = desc;
        this.cost = cost;
    }

    public String getDescription() {
        return this.description;
    }
    public double getCost() {
        return this.cost;
    }

    public void setCost(double newCost) {
        this.cost = newCost;
    }
    @Override
    public boolean equals (Object o) {
        if (this == o) { return true;}
        else if (this.getClass() != o.getClass()) { return false;}
        Expense obj = (Expense) o;
        return obj.getDescription().equals(this.description);
    }
}
