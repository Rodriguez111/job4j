package sellcars.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "mileage", nullable = false)
    private int mileage;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "vin", length = 17, nullable = false)
    private String vin;

    @Column(name = "color", length = 60, nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "car_brand_id", nullable = false)
    private CarBrand carBrand;

    @Column(name = "car_model", length = 60, nullable = false)
    private String carModel;

    @ManyToOne
    @JoinColumn(name = "body_type_id", nullable = false)
    private CarBody bodyType;

    @ManyToOne
    @JoinColumn(name = "engine_id", nullable = false)
    private Engine engine;

    @Column(name = "engine_volume", length = 5, nullable = false)
    private double engineVolume;

    @ManyToOne
    @JoinColumn(name = "transmission_id", nullable = false)
    private Transmission transmission;


    public Car() {
    }


    public Car(int mileage, int year, String vin, String color, CarBrand carBrand, String carModel,
               CarBody bodyType, Engine engine, double engineVolume, Transmission transmission) {
        this.mileage = mileage;
        this.year = year;
        this.vin = vin;
        this.color = color;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.bodyType = bodyType;
        this.engine = engine;
        this.engineVolume = engineVolume;
        this.transmission = transmission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public CarBody getBodyType() {
        return bodyType;
    }

    public void setBodyType(CarBody bodyType) {
        this.bodyType = bodyType;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return mileage == car.mileage
                && year == car.year
                && vin.equals(car.vin)
                && color.equals(car.color)
                && carBrand.equals(car.carBrand)
                && carModel.equals(car.carModel)
                && bodyType.equals(car.bodyType)
                && engine.equals(car.engine)
                && transmission.equals(car.transmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mileage, year, vin, color, carBrand, carModel, bodyType, engine, transmission);
    }

}
