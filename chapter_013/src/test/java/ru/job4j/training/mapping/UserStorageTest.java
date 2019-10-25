package ru.job4j.training.mapping;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.training.componentscan.Storage;
import ru.job4j.training.componentscan.StorageMemory;
import ru.job4j.training.componentscan.User;
import ru.job4j.training.componentscan.UserStorage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserStorageTest {

    @Test
  public void whenImplementMemoryStorageThenSaveToMemory() {
        Storage storage = new StorageMemory();
        UserStorage userStorage = new UserStorage(storage);
        User user = new User();
        userStorage.add(user);
    }

    @Test
    public void whenLoadContextShouldGetBeans() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        UserStorage storageMemory = context.getBean(UserStorage.class);
        storageMemory.add(new User());
        assertNotNull(storageMemory);

    }


}