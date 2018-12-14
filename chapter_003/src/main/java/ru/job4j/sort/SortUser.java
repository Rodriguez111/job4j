package ru.job4j.sort;

import java.util.*;

public class SortUser {

    /**
     *
     * @param listOfUsers - list of users to sort.
     * @return sorted set of users.
     */

    public Set<User> sort(List<User> listOfUsers) {
       return new TreeSet(listOfUsers);
    }


    /**
     * Sort list of users by name length
     * @param listOfUsers - list of users to sort.
     * @return - sorted list of users.
     */
    public List<User> sortNameLength(List<User> listOfUsers) {
        Collections.sort(listOfUsers, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return  Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return listOfUsers;
    }

    /**
     * Sort list of users by name then by age.
     * @param listOfUsers - list of users to sort.
     * @return - sorted list of users.
     */
    public List<User> sortByAllFields(List<User> listOfUsers) {
        Collections.sort(listOfUsers, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
              int result = o1.getName().compareTo(o2.getName());
              return  result != 0 ? result : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return listOfUsers;
    }


}
