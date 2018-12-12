package ru.job4j.users;

import java.util.HashMap;
import java.util.List;


public class UserConvert {

    /**
     *
     * @param list = list of users to convert.
     * @return = result HashMap of users.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> users = new HashMap<>();
        for (User eachUser : list) {
            users.put(eachUser.getId(), eachUser);
        }
        return users;
    }
}
