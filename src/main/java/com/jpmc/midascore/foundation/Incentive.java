package com.jpmc.midascore.foundation;




public class Incentive {
    // Accessors and Mutators
    private float amount;

    // Empty Constructor
    public Incentive() {

    }


    // Parameterized Constructor
    public Incentive(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Incentive: [Amount: ]" + amount + "]";
    }
}