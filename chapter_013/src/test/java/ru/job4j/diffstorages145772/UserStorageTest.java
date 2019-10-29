package ru.job4j.diffstorages145772;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class UserStorageTest {

    @Test
    void whenAddNewUserThenReturnOK() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context_test.xml");
        UserStorage userStorage = context.getBean(UserStorage.class);
        User user = context.getBean(User.class);
        String actual = userStorage.add(user);
        String expected = "OK";
        assertThat(actual, is(expected));
    }

    @Test
    void whenAddUserWithSameLoginThenReturnErrorMessage() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context_test.xml");
        UserStorage userStorage = context.getBean(UserStorage.class);
        User user = context.getBean(User.class);
        userStorage.add(user);
        String actual = userStorage.add(user);
        String expected = "Пользователь с таким логином уже существует.";
        assertThat(actual, is(expected));
    }
}