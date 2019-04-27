package ru.job4j.foodstorage.food;

import java.time.LocalDateTime;

public class Food implements FoodInterface {
    private String name;
    private LocalDateTime expireDate;
    private LocalDateTime createDate;
    private double price;
    private int discount;

    public Food(String name, LocalDateTime expireDate, LocalDateTime createDate, double price, int discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name = '" + name + '\''
                + ", expireDate = " + expireDate.toLocalDate()
                + ", createDate = " + createDate.toLocalDate()
                + ", price = " + price
                + ", discount = " + discount
                + '}';
    }
}
