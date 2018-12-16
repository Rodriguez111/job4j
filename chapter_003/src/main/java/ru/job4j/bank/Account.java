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
     * @throws InsufficientFundsException
     */
    public void transfer(double amount, Account destinationAccount) throws InsufficientFundsException {
        if (this.value < amount) {
            throw new InsufficientFundsException();
        }
        this.value -= amount;
        destinationAccount.setValue(destinationAccount.getValue() + amount);
    }


}
