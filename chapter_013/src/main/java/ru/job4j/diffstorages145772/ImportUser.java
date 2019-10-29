package ru.job4j.diffstorages145772;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ImportUser {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        UserStorage userStorage = context.getBean(UserStorage.class);
        User user = context.getBean(User.class);
        String result = userStorage.add(user);
        System.out.println(result);

    }
}
