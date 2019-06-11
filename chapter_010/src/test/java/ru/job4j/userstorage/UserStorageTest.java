package ru.job4j.userstorage;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class UserStorageTest {

    private class MoneyTransfer extends Thread {
       private final UserStorage storage;
       private final User userFrom;
       private final User userTo;


        public MoneyTransfer(final UserStorage storage, final User userFrom, final User userTo) {
            this.storage = storage;
            this.userFrom = userFrom;
            this.userTo = userTo;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                storage.transfer(1, 2, 1);
            }
        }
    }

    @Test
    public void whenTransfer30000InThreeThreadsThenUser1Has0AndUser2Has30000() throws InterruptedException {
        UserStorage userStorage = new UserStorage();
        User user1 = new User(1, 30000);
        User user2 = new User(2, 0);
        userStorage.add(user1);
        userStorage.add(user2);

        Thread thread1 = new MoneyTransfer(userStorage, user1, user2);
        Thread thread2 = new MoneyTransfer(userStorage, user1, user2);
        Thread thread3 = new MoneyTransfer(userStorage, user1, user2);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        Assert.assertThat(user1.getAmount(), is(0));
        Assert.assertThat(user2.getAmount(), is(30000));

    }

}