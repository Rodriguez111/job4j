package sellcars.persistent;


import sellcars.models.Car;

public interface CarStorage {

    String add(Car car);

    void update(Car car);

    void delete(Car car);

    Car findCarById(int id);

}
