//package sellcars.persistent;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import sellcars.web.CoreConfig;
//import sellcars.models.User;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//class UserDBTest {
//    private ApplicationContext context = new AnnotationConfigApplicationContext(CoreConfig.class);
//
//    @Test
//    void whenAddUserThenWeCanGetItFromDB() {
//        UserStorage userStorage = context.getBean(UserDB.class);
//        userStorage.add(generateTestUser1());
//        User resultUser = userStorage.findUserByLogin("testLogin");
//        String actual = resultUser.getLogin();
//        String expected = "testLogin";
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenUpdateUserThenWeCanGetUpdatedUserFromDB() {
//        UserStorage userStorage = context.getBean(UserDB.class);
//        userStorage.add(generateTestUser1());
//        User resultUser = userStorage.findUserByLogin("testLogin");
//        resultUser.setName("NewName");
//        userStorage.update(resultUser);
//        resultUser = userStorage.findUserByLogin("testLogin");
//        String actual = resultUser.getName();
//        String expected = "NewName";
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenDeleteUserThenWeCanNotGetItFromDBAndLoginIsNull() {
//        UserStorage userStorage = context.getBean(UserDB.class);
//        userStorage.add(generateTestUser1());
//        User resultUser = userStorage.findUserByLogin("testLogin");
//        userStorage.delete(resultUser);
//        resultUser = userStorage.findUserByLogin("testLogin");
//        String actual = resultUser.getName();
//        String expected = null;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenLoginExistsThenGetUser() {
//        UserStorage userStorage = context.getBean(UserDB.class);
//        userStorage.add(generateTestUser1());
//        User resultUser = userStorage.findUserByLogin("testLogin");
//        String actual = resultUser.getLogin();
//        String expected = "testLogin";
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenLoginNotExistsThenGetEmptyUser() {
//        UserStorage userStorage = context.getBean(UserDB.class);
//        userStorage.add(generateTestUser1());
//        User resultUser = userStorage.findUserByLogin("notExistingLogin");
//        String actual = resultUser.getLogin();
//        String expected = null;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    void whenIdExistsThenGetUser() {
//        UserStorage userStorage = context.getBean(UserDB.class);
//        userStorage.add(generateTestUser1());
//        User resultUser = userStorage.findById(1);
//        String actual = resultUser.getLogin();
//        String expected = "testLogin";
//        assertThat(actual, is(expected));
//    }
//
//    public User generateTestUser1() {
//        String userLogin = "testLogin";
//        String userPass = "testPass";
//        String userName = "testName";
//        String userSurname = "testSurname";
//        String phone = "+123";
//        String email = "test@email.com";
//        return new User(userLogin, userPass, userName, userSurname, phone, email);
//    }
//
//    public User generateTestUser2() {
//        String userLogin = "testLogin2";
//        String userPass = "testPass2";
//        String userName = "testName2";
//        String userSurname = "testSurname2";
//        String phone = "+456";
//        String email = "test2@email.com";
//        return new User(userLogin, userPass, userName, userSurname, phone, email);
//    }
//}