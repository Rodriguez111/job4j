package ru.job4j.users;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class UserConvert {

    /**
     *
     * @param list = list of users to convert.
     * @return = result HashMap of users.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> users =
         list
                .stream()
                .collect(Collectors.toMap(User::getId, user -> user, (a, b) -> a, HashMap::new));

        return users;
    }
}
