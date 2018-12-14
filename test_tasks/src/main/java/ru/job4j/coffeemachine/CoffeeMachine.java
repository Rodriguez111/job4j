package ru.job4j.coffeemachine;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {

    /**
     *This CoffeeMachine must return change with minimum amount of coins
     *
     *  select for change the biggest coin value,
     *  and if we cannot select one more coin of this value,
     *  increase counter and trying to select the next coin value:     *
     *  if (change - coins[count].getCoinValues() >= 0){
     *                     continue;
     *                 } else {
     *                     count++;
     *                 }     *
     * @param value  - the amount of money placed in the coin acceptor.
     * @param price - coffee price.
     * @return - change
     */

   public int[] changes(int value, int price) {
        int change = value - price;
        List<Integer> listOfCoins = new ArrayList<>();
        Coin[] coins = Coin.values();
        if (change > 0) {
            int count = 0;
            do {
                if (change - coins[count].getCoinValues() >= 0) {
                    listOfCoins.add(coins[count].getCoinValues());
                    change = change - coins[count].getCoinValues();
                } else {
                    count++;
                }
            } while (change != 0);
        }
        return convertListToArray(listOfCoins);
    }

    private int[] convertListToArray(List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

}
