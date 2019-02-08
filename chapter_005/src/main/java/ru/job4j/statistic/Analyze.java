package ru.job4j.statistic;

import java.util.*;
import java.util.stream.Collectors;

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
        Set<Integer> setOfPreviousId = previous.stream().map(e -> e.id).collect(Collectors.toSet());
        current.stream().forEach(each -> {if (!previous.contains(each)){
            if (!setOfPreviousId.add(each.id)) {
                info.changed++;
            } else {
                info.added++;
            }
        }});

        Set<Integer> setOfCurrentId = current.stream().map(e -> e.id).collect(Collectors.toSet());
        previous.stream().forEach(each -> {if (!current.contains(each)){
            if (setOfCurrentId.add(each.id)) {
                info.deleted++;
            }

        }});
         return info;
     }

    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int id;
        String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return added == info.added &&
                    changed == info.changed &&
                    deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }

        @Override
        public String toString() {
            return "Info{" +
                    "added=" + added +
                    ", changed=" + changed +
                    ", deleted=" + deleted +
                    '}';
        }
    }

}
