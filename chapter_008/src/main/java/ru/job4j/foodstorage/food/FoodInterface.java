package ru.job4j.foodstorage.food;

import java.time.LocalDateTime;

public interface FoodInterface {
     LocalDateTime getExpireDate();

     LocalDateTime getCreateDate();

     double getPrice();

     void setPrice(double price);

     int getDiscount();

}
