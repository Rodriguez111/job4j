package ru.job4j.sort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class SortUserTest {

    @Test
    public void firstUserIsJan() {
        SortUser sortUser = new SortUser();

        User user1 = new User("Иван", 50);
        User user2 = new User("Ян", 40);
        User user3 = new User("Василий", 45);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        Set<User> test = sortUser.sort(users);
        Iterator iter = test.iterator();
        User actual = (User)iter.next();
        User expected = user2;
        assertThat(actual, is(user2));
    }




}
