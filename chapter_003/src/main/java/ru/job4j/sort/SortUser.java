package ru.job4j.sort;

import java.util.*;

public class SortUser {

    /**
     *
     * @param listOfUsers - list of users to sort.
     * @return sorted set of users.
     */

    public Set<User> sort (List<User> listOfUsers) {

       return new TreeSet(listOfUsers);
    }



}
