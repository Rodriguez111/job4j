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
        User actual = (User) iter.next();
        User expected = user2;
        assertThat(actual, is(user2));
    }

    @Test
    public void sortNameLengthWhenLastUserIsVasili() {
        SortUser sortUser = new SortUser();

        User user1 = new User("Иван", 50);
        User user2 = new User("Ян", 40);
        User user3 = new User("Василий", 45);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        List<User> test = sortUser.sortNameLength(users);
        User actual = test.get(test.size() - 1);
        User expected = user3;
        assertThat(actual, is(expected));
    }


    @Test
    public void sortAllFields() {
        SortUser sortUser = new SortUser();
        User user1 = new User("Сергей", 25);
        User user2 = new User("Иван", 30);
        User user3 = new User("Сергей", 20);
        User user4 = new User("Иван", 25);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        List<User> actual = sortUser.sortByAllFields(users);
        List<User> expected = new ArrayList<>();
        expected.add(user4);
        expected.add(user2);
        expected.add(user3);
        expected.add(user1);
        assertThat(actual, is(expected));
    }




}
