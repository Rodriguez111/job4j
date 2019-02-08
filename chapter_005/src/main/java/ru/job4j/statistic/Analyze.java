package ru.job4j.statistic;

import java.util.ArrayList;
import java.util.List;

/**
 * Это задание сводиться к определению разницы между начальным состояние массива и измененным. *
 * Например. Дан массива чисел. *
 * Начальное состояние 1 10 13 4 5 *
 * Конечное состояние 1 13 4 *
 * Разница будет будет массив 10 5. *
 * Это элементарный пример. Ваша задача более сложная.*
 *
 *
 * Нужно реализовать класс. *
 * public class Analize { *
 *    public Info diff(List<User> previous, List<User> current) {
 *        return null; *
 *    } *
 *    public static class User {
 *       int id;
 *       String name;
 *    } *
 *    public static class Info { *
 *        int added;
 *        int changed; *
 *        int deleted; *
 *    } *
 * }
 *
 * метод должен возвращать статистику об изменении коллекции. *
 * List<User> previous - начальные данные, *
 * List<User> current - измененные данные.  *
 * Нужно понять: *
 * Сколько добавлено новых пользователей. *
 * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя. а id осталось прежним. *
 * Сколько удалено пользователей. *
 * Обязательно напишите тесты.
 */

public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        //int[] parameters = new int[2];

       // previous.stream().filter()

       // previous.stream().forEach(each -> {if (!current.contains(each)){parameters[1]++;}});
      //  current.stream().forEach(each -> {if (!previous.contains(each)){parameters[0]++;}});



         return null;
     }

    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int id;
        String name;
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        @Override
        public String toString() {
            return "Info{" +
                    "added=" + added +
                    ", changed=" + changed +
                    ", deleted=" + deleted +
                    '}';
        }
    }


    public static void main(String[] args) {
        User user1 = new User(1, "Ivan");
        User user2 = new User(2, "Vasya");
        User user3 = new User(3, "Petya");
        User user4 = new User(4, "Kolya");
        User user5 = new User(5, "Sasha");

        List<User> list1 = new ArrayList<>() ;
        list1.add(user1);
        list1.add(user2);
        list1.add(user3);
        list1.add(user4);
        list1.add(user5);


      User user6 = new User(3, "Anton");
        List<User> list2 = new ArrayList<>() ;
        list1.add(user1);
        list1.add(user2);
        list1.add(user6);
        list1.add(user4);
        list1.add(user5);

        Analyze analyze = new Analyze();
     Info info = analyze.diff(list1, list2);

        System.out.println(info);




    }
}
