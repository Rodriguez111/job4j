package sellcars.persistent;

import org.junit.jupiter.api.Test;
import sellcars.models.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class UserDBTest {

    @Test
    void whenAddUserThenWeCanGetItFromDB() {
        UserStorage userStorage = UserDB.getINSTANCE();
        userStorage.add(generateTestUser1());
        User resultUser = userStorage.findUserByLogin("testLogin");
        String actual = resultUser.getLogin();
        String expected = "testLogin";
        assertThat(actual, is(expected));
    }

    @Test
    void whenUpdateUserThenWeCanGetUpdatedUserFromDB() {
        UserStorage userStorage = UserDB.getINSTANCE();
        userStorage.add(generateTestUser1());
        User resultUser = userStorage.findUserByLogin("testLogin");
        resultUser.setName("NewName");
        userStorage.update(resultUser);
        resultUser = userStorage.findUserByLogin("testLogin");
        String actual = resultUser.getName();
        String expected = "NewName";
        assertThat(actual, is(expected));
    }

    @Test
    void whenDeleteUserThenWeCanNotGetItFromDBAndLoginIsNull() {
        UserStorage userStorage = UserDB.getINSTANCE();
        userStorage.add(generateTestUser1());
        User resultUser = userStorage.findUserByLogin("testLogin");
        userStorage.delete(resultUser);
        resultUser = userStorage.findUserByLogin("testLogin");
        String actual = resultUser.getName();
        String expected = null;
        assertThat(actual, is(expected));
    }

    @Test
    void WhenLoginExistsThenGetUser() {
        UserStorage userStorage = UserDB.getINSTANCE();
        userStorage.add(generateTestUser1());
        User resultUser = userStorage.findUserByLogin("testLogin");
        String actual = resultUser.getLogin();
        String expected = "testLogin";
        assertThat(actual, is(expected));
    }

    @Test
    void WhenLoginNotExistsThenGetEmptyUser() {
        UserStorage userStorage = UserDB.getINSTANCE();
        userStorage.add(generateTestUser1());
        User resultUser = userStorage.findUserByLogin("notExistingLogin");
        String actual = resultUser.getLogin();
        String expected = null;
        assertThat(actual, is(expected));
    }

    @Test
    void WhenIdExistsThenGetUser() {
        UserStorage userStorage = UserDB.getINSTANCE();
        userStorage.add(generateTestUser1());
        User resultUser = userStorage.findById(1);
        String actual = resultUser.getLogin();
        String expected = "testLogin";
        assertThat(actual, is(expected));
    }

    public User generateTestUser1() {
        String userLogin = "testLogin";
        String userPass = "testPass";
        String userName = "testName";
        String userSurname = "testSurname";
        String phone = "+123";
        String email = "test@email.com";
        return new User(userLogin, userPass, userName, userSurname, phone, email);
    }

    public User generateTestUser2() {
        String userLogin = "testLogin2";
        String userPass = "testPass2";
        String userName = "testName2";
        String userSurname = "testSurname2";
        String phone = "+456";
        String email = "test2@email.com";
        return new User(userLogin, userPass, userName, userSurname, phone, email);
    }
}