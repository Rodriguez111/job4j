package ru.job4j.training.componentscan;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

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