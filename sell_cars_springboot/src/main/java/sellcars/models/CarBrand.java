package sellcars.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car_brand")
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "car_brand", length = 60, nullable = false, unique = true)
    private String carBrand;

    public CarBrand() {
    }

    public CarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarBrand carBrand1 = (CarBrand) o;
        return id == carBrand1.id &&
                Objects.equals(carBrand, carBrand1.carBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carBrand);
    }
}
