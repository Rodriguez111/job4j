package ru.job4j.sort;

import java.util.*;
import java.util.stream.Collectors;

public class SortUser {

    /**
     *
     * @param listOfUsers - ru.job4j.list of users to sort.
     * @return sorted set of users.
     */

    public Set<User> sort(List<User> listOfUsers) {
       return new TreeSet(listOfUsers);
    }


    /**
     * Sort ru.job4j.list of users by name length
     * @param listOfUsers - ru.job4j.list of users to sort.
     * @return - sorted ru.job4j.list of users.
     */
    public List<User> sortNameLength(List<User> listOfUsers) {
        return  listOfUsers.stream()
                .sorted((user1, user2) -> Integer.compare(user1.getName().length(), user2.getName().length()))
                .collect(Collectors.toList());
    }

    /**
     * Sort ru.job4j.list of users by name then by age.
     * @param listOfUsers - ru.job4j.list of users to sort.
     * @return - sorted ru.job4j.list of users.
     */
    public List<User> sortByAllFields(List<User> listOfUsers) {
        return listOfUsers.stream()
                .sorted((user1, user2) -> {
                    int result = user1.getName().compareTo(user2.getName());
                    result =  result != 0 ?  result : Integer.compare(user1.getAge(), user2.getAge());
                    return result;
                }).collect(Collectors.toList());
    }


}
