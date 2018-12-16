package ru.job4j.bank;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankTest {

    @Before
    public void createInstanceOfTheBank() {
        Bank bank = new Bank();
    }


    @Test
    public void whenAddUserThenDeleteIsTrue() {
        User user = new User("Ivan", "001");
        Bank bank = new Bank();
        bank.addUser(user);
        boolean actual = bank.deleteUser(user);
        assertThat(actual, is(true));
    }

    @Test
    public void whenDeleteWrongUserThenFalse() {
        User user = new User("Ivan", "001");
        User user2 = new User("Vasili", "002");
        Bank bank = new Bank();
        bank.addUser(user);
        boolean actual = bank.deleteUser(user2);
        assertThat(actual, is(false));
    }

    @Test
    public void whenAddAccountThenDeleteAccountIsTrue() {
        User user = new User("Ivan", "001");
        Account account = new Account(100, "001001");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account);
        boolean actual = bank.deleteAccountFromUser("001", account);
        assertThat(actual, is(true));
    }

    @Test
    public void whenDeleteAccountOfWrongUserThenFalse() {
        User user = new User("Ivan", "001");
        Account account = new Account(100, "001001");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account);
        boolean actual = bank.deleteAccountFromUser("002", account);
        assertThat(actual, is(false));
    }

    @Test
    public void whenDeleteWrongAccountThenFalse() {
        User user = new User("Ivan", "001");
        Account account = new Account(100, "001001");
        Account wrongAccount = new Account(100, "001002");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account);
        boolean actual = bank.deleteAccountFromUser("001", wrongAccount);
        assertThat(actual, is(false));
    }

    @Test
    public void whenGetAccountsThenReturnListOfAccounts() {
        User user = new User("Ivan", "001");
        Account account1 = new Account(100, "001001");
        Account account2 = new Account(200, "001002");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account1);
        bank.addAccountToUser(user.getPassport(), account2);
        List<Account> actual = bank.getUserAccounts("001");
        List<Account> expected = new ArrayList<>();
        expected.add(account1);
        expected.add(account2);
        assertThat(actual, is(expected));
    }


    @Test (expected = UserNotFoundException.class)
    public void whenUserNotFoundThenException() throws InsufficientFundsException, AccountNotFoundException, UserNotFoundException {
        User user1 = new User("Ivan", "001");
        User user2 = new User("Vasili", "002");


        Account account1User1 = new Account(100, "001001");
        Account account2User1 = new Account(200, "001002");

        Account account1User2 = new Account(300, "001010");
        Account account2User2 = new Account(400, "001020");


        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addUser(user2);

        bank.addAccountToUser(user1.getPassport(), account1User1);
        bank.addAccountToUser(user1.getPassport(), account2User1);

        bank.addAccountToUser(user2.getPassport(), account1User2);
        bank.addAccountToUser(user2.getPassport(), account2User2);

        boolean actual = bank.transferMoney("099", "001001", "002", "001010", 150);
        Exception expected = new UserNotFoundException();
        assertThat(actual, is(expected));
    }






    @Test (expected = AccountNotFoundException.class)
    public void whenSourceAccountNotFoundThenException() throws InsufficientFundsException, AccountNotFoundException, UserNotFoundException {
        User user1 = new User("Ivan", "001");
        User user2 = new User("Vasili", "002");


        Account account1User1 = new Account(100, "001001");
        Account account2User1 = new Account(200, "001002");

        Account account1User2 = new Account(300, "001010");
        Account account2User2 = new Account(400, "001020");

        Account wrongAccount = new Account(400, "001003");

        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addUser(user2);

        bank.addAccountToUser(user1.getPassport(), account1User1);
        bank.addAccountToUser(user1.getPassport(), account2User1);

        bank.addAccountToUser(user2.getPassport(), account1User2);
        bank.addAccountToUser(user2.getPassport(), account2User2);

        boolean actual = bank.transferMoney("001", "001003", "002", "001010", 150);
        Exception expected = new AccountNotFoundException();
        assertThat(actual, is(expected));
    }


    @Test (expected = AccountNotFoundException.class)
    public void whenDestinationAccountNotFoundThenException() throws InsufficientFundsException, AccountNotFoundException, UserNotFoundException {
        User user1 = new User("Ivan", "001");
        User user2 = new User("Vasili", "002");


        Account account1User1 = new Account(100, "001001");
        Account account2User1 = new Account(200, "001002");

        Account account1User2 = new Account(300, "001010");
        Account account2User2 = new Account(400, "001020");

        Account wrongAccount = new Account(400, "001003");

        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addUser(user2);

        bank.addAccountToUser(user1.getPassport(), account1User1);
        bank.addAccountToUser(user1.getPassport(), account2User1);

        bank.addAccountToUser(user2.getPassport(), account1User2);
        bank.addAccountToUser(user2.getPassport(), account2User2);

        boolean actual = bank.transferMoney("001", "001001", "002", "001003", 150);
        Exception expected = new InsufficientFundsException();
        assertThat(actual, is(expected));
    }



    @Test (expected = InsufficientFundsException.class)
    public void whenInsufficientFundsThenException() throws InsufficientFundsException, AccountNotFoundException, UserNotFoundException {
        User user1 = new User("Ivan", "001");
        User user2 = new User("Vasili", "002");
        Account account1User1 = new Account(100, "001001");
        Account account2User1 = new Account(200, "001002");

        Account account1User2 = new Account(300, "001010");
        Account account2User2 = new Account(400, "001020");

        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addUser(user2);

        bank.addAccountToUser(user1.getPassport(), account1User1);
        bank.addAccountToUser(user1.getPassport(), account2User1);

        bank.addAccountToUser(user2.getPassport(), account1User2);
        bank.addAccountToUser(user2.getPassport(), account2User2);

      boolean actual = bank.transferMoney("001", "001001", "002", "001010", 150);
      Exception expected = new InsufficientFundsException();
       assertThat(actual, is(expected));
    }


    @Test
    public void whenTransferSuccessfulThenTrue() throws InsufficientFundsException, AccountNotFoundException, UserNotFoundException {
        User user1 = new User("Ivan", "001");
        User user2 = new User("Vasili", "002");
        Account account1User1 = new Account(100, "001001");
        Account account2User1 = new Account(200, "001002");

        Account account1User2 = new Account(300, "001010");
        Account account2User2 = new Account(400, "001020");

        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addUser(user2);

        bank.addAccountToUser(user1.getPassport(), account1User1);
        bank.addAccountToUser(user1.getPassport(), account2User1);

        bank.addAccountToUser(user2.getPassport(), account1User2);
        bank.addAccountToUser(user2.getPassport(), account2User2);

        boolean actual = bank.transferMoney("001", "001001", "002", "001010", 100);
        assertThat(actual, is(true));
    }





}
