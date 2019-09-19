package cinema.persistence.storage;

import cinema.persistence.SQLManager;
import cinema.persistence.models.Place;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class DBTicketTest {

    @Test
    public void when() throws Exception {
        TicketStorage ticketStorage = DBTicket.getINSTANCE();
        HallStorage hallStorage = DBHall.getINSTANCE();
        SQLManager sqlManager = SQLManager.getINSTANCE();
        Connection connection = sqlManager.getConnection();

        //освобождаем все места
        hallStorage.clearHall();
        List<Place> listOfPlaces = hallStorage.getHallInfo();


        int row = 10;
        int place = 10;
        String name1 = "Name1";
        String surname1 = "Surname1";
        String phone1 = "123";
        float cost1 = 100;

        Place resultPlace = listOfPlaces.stream().filter(eachPlace ->
                eachPlace.getRow() == row && eachPlace.getPlace() == place
        ).findFirst().get();

        boolean actual = resultPlace.isEngaged();
        boolean expected = false;
        //проверяем, что место свободно
        assertThat(actual, is(expected));

        //занимаем место
        hallStorage.engagePlace(row, place, connection);

        listOfPlaces = hallStorage.getHallInfo();
        resultPlace = listOfPlaces.stream().filter(eachPlace ->
                eachPlace.getRow() == row && eachPlace.getPlace() == place
        ).findFirst().get();

        actual = resultPlace.isEngaged();
        expected = true;
        //проверяем, что место занято
        assertThat(actual, is(expected));

       //пытаемся продать билет на занятое место
      String actualResult = ticketStorage.createTicket(row, place, name1, surname1, phone1, cost1);
      String expectedResult = "Это место уже зарезервировано";
      assertThat(actualResult, is(expectedResult));
    }


}