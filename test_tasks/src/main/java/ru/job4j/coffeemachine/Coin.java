package ru.job4j.coffeemachine;

public enum Coin {
    ONE(10),
    TWO(5),
    FIVE(2),
    TEN(1);

    private int coinValue;

   Coin(int coinValue) {
       this.coinValue = coinValue;
    }

    public int getCoinValues() {
      return this.coinValue;
    }

}
