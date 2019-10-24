package sellcars.persistent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sellcars.models.Advert;
import sellcars.models.Car;
import sellcars.models.Photo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class PhotoDBTest {

    @AfterEach
    public void clear() {
        SessionManager.handleQuery(session -> {
            session.createSQLQuery("delete from photos cascade").executeUpdate();
            session.createSQLQuery("delete from adverts cascade").executeUpdate();
            session.createSQLQuery("delete from cars cascade").executeUpdate();
            session.createSQLQuery("delete from users cascade").executeUpdate();

            session.createSQLQuery("ALTER TABLE photos ALTER COLUMN id RESTART WITH 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE adverts ALTER COLUMN id RESTART WITH 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE cars ALTER COLUMN id RESTART WITH 1").executeUpdate();
            session.createSQLQuery("ALTER TABLE users ALTER COLUMN id RESTART WITH 1").executeUpdate();

        });
    }

    @Test
    void whenAddPhotoThenReturnId() {
        PhotoStorage photoStorage = PhotoDB.getINSTANCE();
        Photo photo = new Photo("testFileName");
        Advert advert = addAdvert1ToDBAndGetIt();
        photo.setAdvertId(advert.getId());
        String actual = photoStorage.add(photo);
        String expected = "1";
        assertThat(actual, is(expected));
    }

    @Test
    void whenFindPhotoByAdvertIdThenCanGetPhotoFileName() {
        PhotoStorage photoStorage = PhotoDB.getINSTANCE();
        Photo photo = new Photo("testFileName");
        Advert advert = addAdvert1ToDBAndGetIt();
        photo.setAdvertId(advert.getId());
        photoStorage.add(photo);
        Photo foundPhoto = photoStorage.findPhotoByAdvertId(1);
        String actual = foundPhoto.getFileName();
        String expected = "testFileName";
        assertThat(actual, is(expected));
    }

    @Test
    void whenDeletePhotoThenWeCanNotGetItFromDBAndExceptionIsThrow() {
        PhotoStorage photoStorage = PhotoDB.getINSTANCE();
        Photo photo = new Photo("testFileName");
        Advert advert = addAdvert1ToDBAndGetIt();
        photo.setAdvertId(advert.getId());
        photoStorage.add(photo);
        photoStorage.delete(photo);
        assertThrows(IllegalStateException.class, () -> {
            photoStorage.findPhotoByAdvertId(1);
        });
    }

    public Advert addAdvert1ToDBAndGetIt() {
        AdvertDBTest advertDBTest = new AdvertDBTest();
        Advert advert = advertDBTest.generateAdvert1();
        AdvertStorage advertStorage = AdvertDB.getINSTANCE();
        advertStorage.add(advert);
        return advert;
    }
}