package ru.job4j.bank;

public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setValue(double value) {
        this.value = value;
    }


    /**
     *
     * @param amount - amount of money to transfer.
     * @param destinationAccount - destination account to which we transfer to.
     */
    public boolean transfer(double amount, Account destinationAccount) {
        boolean transferSuccess = true;
        if (this.value >= amount) {
            this.value -= amount;
            destinationAccount.setValue(destinationAccount.getValue() + amount);
        } else {
            transferSuccess = false;
        }
        return transferSuccess;
    }


}
