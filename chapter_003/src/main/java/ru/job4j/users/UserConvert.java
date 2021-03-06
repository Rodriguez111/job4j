package ru.job4j.users;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class UserConvert {

    /**
     *
     * @param list = ru.job4j.list of users to convert.
     * @return = result HashMap of users.
     */
    public Map<Integer, User> process(List<User> list) {
//        HashMap<Integer, User> users =
//         ru.job4j.list
//                .stream()
//                .collect(Collectors.toMap(User::getId, user -> user, (a, b) -> a, HashMap::new));
        Map<Integer, User> users =
                list.stream()
                        .collect(Collectors.toMap(User::getId, user -> user));
        return users;
    }
}
